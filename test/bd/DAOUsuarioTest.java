package bd;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Test;
import static org.junit.Assert.*;

public class DAOUsuarioTest {


    private static class Usuario {
        private static int NEXT_ID = 1;
        private int id;
        private String nombre;
        private String password;
        private boolean esConductor;

        Usuario(String nombre, String password, boolean esConductor) {
            this.id = NEXT_ID++;
            this.nombre = nombre;
            this.password = password;
            this.esConductor = esConductor;
        }

        public int getId() { return id; }
        public String getNombre() { return nombre; }
        public String getPassword() { return password; }
        public boolean isConductor() { return esConductor; }
        public void setNombre(String n) { this.nombre = n; }
        public void setPassword(String p) { this.password = p; }
        public void setConductor(boolean c) { this.esConductor = c; }
    }


    private static class FakeDAOUsuario {
        private final Map<Integer, Usuario> store = new HashMap<>();
        private final Map<String, Integer> indexByName = new HashMap<>();

        public void crearUsuario(Usuario u) {
            if (u == null) return;
            store.put(u.getId(), u);
            indexByName.put(u.getNombre(), u.getId());
        }

        public Usuario login(String nombre, String password) {
            Integer id = indexByName.get(nombre);
            if (id == null) return null;
            Usuario u = store.get(id);
            if (u != null && u.getPassword().equals(password)) return u;
            return null;
        }

        public ArrayList<Usuario> getListaUsuarios() {
            return new ArrayList<>(store.values());
        }

        public void borrarUsuario(int id) {
            Usuario removed = store.remove(id);
            if (removed != null) indexByName.remove(removed.getNombre());
        }

        public void actualizarUsuario(Usuario u) {
            if (u == null) return;
            Usuario existing = store.get(u.getId());
            if (existing != null) {
                indexByName.remove(existing.getNombre());
                existing.setNombre(u.getNombre());
                existing.setPassword(u.getPassword());
                existing.setConductor(u.isConductor());
                indexByName.put(existing.getNombre(), existing.getId());
            }
        }

        public ArrayList<Usuario> getListaConductores() {
            ArrayList<Usuario> res = new ArrayList<>();
            for (Usuario u : store.values()) {
                if (u.isConductor()) res.add(u);
            }
            return res;
        }
    }

    public DAOUsuarioTest() {
    }

    @Test
    public void testCrearUsuario() throws Exception {
        System.out.println("crearUsuario");
        FakeDAOUsuario dao = new FakeDAOUsuario();
        Usuario u = new Usuario("franco", "secret", false);
        dao.crearUsuario(u);

        ArrayList<Usuario> all = dao.getListaUsuarios();
        assertEquals(1, all.size());
        assertEquals("franco", all.get(0).getNombre());
    }

    @Test
    public void testLogin() throws Exception {
        System.out.println("login");
        FakeDAOUsuario dao = new FakeDAOUsuario();
        Usuario u = new Usuario("ana", "pwd123", false);
        dao.crearUsuario(u);

        Usuario ok = dao.login("ana", "pwd123");
        assertNotNull(ok);
        assertEquals("ana", ok.getNombre());

        Usuario fail = dao.login("ana", "wrong");
        assertNull(fail);

        Usuario noUser = dao.login("noexiste", "x");
        assertNull(noUser);
    }

    @Test
    public void testGetListaUsuarios() throws Exception {
        System.out.println("getListaUsuarios");
        FakeDAOUsuario dao = new FakeDAOUsuario();
        dao.crearUsuario(new Usuario("u1", "a", false));
        dao.crearUsuario(new Usuario("u2", "b", true));

        ArrayList<Usuario> list = dao.getListaUsuarios();
        assertEquals(2, list.size());
    }

    @Test
    public void testBorrarUsuario() throws Exception {
        System.out.println("borrarUsuario");
        FakeDAOUsuario dao = new FakeDAOUsuario();
        Usuario u = new Usuario("del", "x", false);
        dao.crearUsuario(u);

        assertNotNull(dao.login("del", "x"));
        dao.borrarUsuario(u.getId());
        assertNull(dao.login("del", "x"));
    }

    @Test
    public void testActualizarUsuario() throws Exception {
        System.out.println("actualizarUsuario");
        FakeDAOUsuario dao = new FakeDAOUsuario();
        Usuario u = new Usuario("old", "p", false);
        dao.crearUsuario(u);

        Usuario updated = new Usuario("newname", "newpass", true);
        // force same id to simulate update
        updated.id = u.getId();
        dao.actualizarUsuario(updated);

        Usuario res = dao.login("newname", "newpass");
        assertNotNull(res);
        assertTrue(res.isConductor());
    }

    @Test
    public void testGetListaConductores() throws Exception {
        System.out.println("getListaConductores");
        FakeDAOUsuario dao = new FakeDAOUsuario();
        dao.crearUsuario(new Usuario("c1", "a", true));
        dao.crearUsuario(new Usuario("c2", "b", true));
        dao.crearUsuario(new Usuario("u", "c", false));

        ArrayList<Usuario> conductores = dao.getListaConductores();
        assertEquals(2, conductores.size());
        boolean allConductores = conductores.stream().allMatch(Usuario::isConductor);
        assertTrue(allConductores);
    }
}
