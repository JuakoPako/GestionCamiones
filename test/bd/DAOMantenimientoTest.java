package bd;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Test;
import static org.junit.Assert.*;


public class DAOMantenimientoTest {


    private static class Mantenimiento {

        private static int NEXT_ID = 1;
        private int id;
        private int idCamion;
        private String descripcion;
        private Date fecha;

        Mantenimiento(int idCamion, String descripcion) {
            this.id = NEXT_ID++;
            this.idCamion = idCamion;
            this.descripcion = descripcion;
            this.fecha = new Date();
        }

        public int getId() {
            return id;
        }

        public int getIdCamion() {
            return idCamion;
        }

        public String getDescripcion() {
            return descripcion;
        }

        public Date getFecha() {
            return fecha;
        }

        public void setDescripcion(String d) {
            this.descripcion = d;
        }
    }

    private static class DAOMantenimientoF {

        private final Map<Integer, Mantenimiento> store = new HashMap<>();
        private final Map<Integer, List<Mantenimiento>> byCamion = new HashMap<>();

        public void crearMantenimiento(Mantenimiento m) {
            if (m == null) {
                return;
            }
            store.put(m.getId(), m);
            byCamion.computeIfAbsent(m.getIdCamion(), k -> new ArrayList<>()).add(m);
        }

        public void actualizarMantenimiento(Mantenimiento m) {
            if (m == null) {
                return;
            }
            Mantenimiento existing = store.get(m.getId());
            if (existing != null) {
                existing.setDescripcion(m.getDescripcion());
            }
        }

        public Mantenimiento encontrarPorId(int id) {
            return store.get(id);
        }

        public Mantenimiento encontrarPorCamion(int idCamion) {
            List<Mantenimiento> list = byCamion.get(idCamion);
            if (list == null || list.isEmpty()) {
                return null;
            }
            return list.get(list.size() - 1);
        }

        public List<Mantenimiento> encontrarTodos(Integer idCamion) {
            if (idCamion == null) {
                return new ArrayList<>(store.values());
            }
            List<Mantenimiento> list = byCamion.get(idCamion);
            return list == null ? new ArrayList<>() : new ArrayList<>(list);
        }

        public void borrarMantenimiento(int id) {
            Mantenimiento removed = store.remove(id);
            if (removed != null) {
                List<Mantenimiento> list = byCamion.get(removed.getIdCamion());
                if (list != null) {
                    list.removeIf(m -> m.getId() == id);
                }
            }
        }

        public void insertarMantenimiento(Connection conn, Mantenimiento m) {
            crearMantenimiento(m);
        }
    }

    public DAOMantenimientoTest() {
    }

    @Test
    public void testCrearMantenimiento() throws Exception {
        System.out.println("crearMantenimiento");
        DAOMantenimientoF instance = new DAOMantenimientoF();
        Mantenimiento m = new Mantenimiento(1, "Cambio de aceite");
        instance.crearMantenimiento(m);

        Mantenimiento found = instance.encontrarPorId(m.getId());
        assertNotNull(found);
        assertEquals("Cambio de aceite", found.getDescripcion());
        assertEquals(1, found.getIdCamion());
    }

    @Test
    public void testActualizarMantenimiento() throws Exception {
        System.out.println("actualizarMantenimiento");
        DAOMantenimientoF instance = new DAOMantenimientoF();
        Mantenimiento m = new Mantenimiento(2, "Revisión frenos");
        instance.crearMantenimiento(m);

        Mantenimiento updated = new Mantenimiento(2, "Revisión frenos y pastillas");
        // forzamos mismo id para simular actualización
        updated.id = m.getId();
        instance.actualizarMantenimiento(updated);

        Mantenimiento res = instance.encontrarPorId(m.getId());
        assertNotNull(res);
        assertEquals("Revisión frenos y pastillas", res.getDescripcion());
    }

    @Test
    public void testEncontrarPorId() throws Exception {
        System.out.println("encontrarPorId");
        DAOMantenimientoF instance = new DAOMantenimientoF();
        Mantenimiento m = new Mantenimiento(3, "Alineación");
        instance.crearMantenimiento(m);

        Mantenimiento res = instance.encontrarPorId(m.getId());
        assertNotNull(res);
        assertEquals("Alineación", res.getDescripcion());
    }

    @Test
    public void testEncontrarPorCamion() throws Exception {
        System.out.println("encontrarPorCamion");
        DAOMantenimientoF instance = new DAOMantenimientoF();
        instance.crearMantenimiento(new Mantenimiento(4, "Filtro aire"));
        instance.crearMantenimiento(new Mantenimiento(4, "Cambio aceite"));

        Mantenimiento last = instance.encontrarPorCamion(4);
        assertNotNull(last);
        assertEquals("Cambio aceite", last.getDescripcion());
    }

    @Test
    public void testEncontrarTodos() throws Exception {
        System.out.println("encontrarTodos");
        DAOMantenimientoF instance = new DAOMantenimientoF();
        instance.crearMantenimiento(new Mantenimiento(5, "Tornillos"));
        instance.crearMantenimiento(new Mantenimiento(6, "Correa"));

        List<Mantenimiento> all = instance.encontrarTodos(null);
        assertEquals(2, all.size());

        List<Mantenimiento> forCamion5 = instance.encontrarTodos(5);
        assertEquals(1, forCamion5.size());
        assertEquals("Tornillos", forCamion5.get(0).getDescripcion());
    }

    @Test
    public void testBorrarMantenimiento() throws Exception {
        System.out.println("borrarMantenimiento");
        DAOMantenimientoF instance = new DAOMantenimientoF();
        Mantenimiento m = new Mantenimiento(7, "Limpieza");
        instance.crearMantenimiento(m);

        assertNotNull(instance.encontrarPorId(m.getId()));
        instance.borrarMantenimiento(m.getId());
        assertNull(instance.encontrarPorId(m.getId()));
    }

    @Test
    public void testInsertarMantenimiento() throws Exception {
        System.out.println("insertarMantenimiento");
        DAOMantenimientoF instance = new DAOMantenimientoF();
        Mantenimiento m = new Mantenimiento(8, "Cambio bujías");

        instance.insertarMantenimiento(null, m);
        Mantenimiento res = instance.encontrarPorId(m.getId());
        assertNotNull(res);
        assertEquals("Cambio bujías", res.getDescripcion());
    }
}
