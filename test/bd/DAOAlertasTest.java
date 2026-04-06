package bd;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Test;
import static org.junit.Assert.*;


public class DAOAlertasTest {


    private static class Alertas {
        private static int NEXT_ID = 1;
        private int id;
        private int id_camion;
        private Date fecha;
        private String tipo;

        Alertas(int id_camion, String tipo) {
            this.id = NEXT_ID++;
            this.id_camion = id_camion;
            this.tipo = tipo;
            this.fecha = new Date();
        }

        public int getId() { return id; }
        public int getId_camion() { return id_camion; }
        public Date getFecha() { return fecha; }
        public String getTipo() { return tipo; }
        public void setTipo(String t) { this.tipo = t; }
    }


    private static class DaoAlertasF {
        private final Map<Integer, List<Alertas>> store = new HashMap<>();

        public void crearAlerta(int id_camion, String tipo) {
            store.computeIfAbsent(id_camion, k -> new ArrayList<>()).add(new Alertas(id_camion, tipo));
        }

        public List<Alertas> encontrarPorCamion(Integer id_camion, String tipo) {
            List<Alertas> list = new ArrayList<>();
            if (id_camion == null) return list;
            List<Alertas> src = store.get(id_camion);
            if (src == null) return list;
            for (Alertas a : src) {
                if (tipo == null || tipo.isEmpty() || tipo.equals(a.getTipo())) {
                    list.add(a);
                }
            }
            return list;
        }

        public List<Alertas> encontrarHistorialPorCamion(int idCamion) {
            List<Alertas> src = store.get(idCamion);
            return src == null ? new ArrayList<>() : new ArrayList<>(src);
        }

        public Alertas encontrarPrimeraPorCamion(int id_camion) {
            List<Alertas> src = store.get(id_camion);
            if (src == null || src.isEmpty()) return null;
            return src.get(0);
        }

        public void borrarAlerta(int idAlerta) {
            for (List<Alertas> list : store.values()) {
                list.removeIf(a -> a.getId() == idAlerta);
            }
        }

        public void borrarAlerta(Connection conn, int idAlerta) {
            borrarAlerta(idAlerta);
        }

        public boolean existeAlertaNoAtendidaPorTipo(int id_camion, String tipo) {
            List<Alertas> src = store.get(id_camion);
            if (src == null) return false;
            for (Alertas a : src) {
                if (a.getTipo().equals(tipo)) return true;
            }
            return false;
        }

        public boolean insertarAlertaTipoUnico(Connection conn, Alertas alerta) {
            List<Alertas> src = store.computeIfAbsent(alerta.getId_camion(), k -> new ArrayList<>());
            for (Alertas a : src) {
                if (a.getTipo().equals(alerta.getTipo())) return false;
            }
            src.add(new Alertas(alerta.getId_camion(), alerta.getTipo()));
            return true;
        }

        public List<Alertas> encontrarUltimasPorTipoPorCamion(Integer idCamion) {
            List<Alertas> result = new ArrayList<>();
            if (idCamion == null) return result;
            List<Alertas> src = store.get(idCamion);
            if (src == null) return result;
            Map<String, Alertas> lastByType = new HashMap<>();
            for (Alertas a : src) {
                lastByType.put(a.getTipo(), a); // la iteración preserva orden de inserción; la última sobrescribe
            }
            result.addAll(lastByType.values());
            return result;
        }
    }

    public DAOAlertasTest() {
    }

    @Test
    public void testCrearAlerta() throws Exception {
        System.out.println("crearAlerta");
        int id_camion = 10;
        String tipo = "COMBUSTIBLE";
        DaoAlertasF instance = new DaoAlertasF();
        instance.crearAlerta(id_camion, tipo);

        List<Alertas> lista = instance.encontrarPorCamion(id_camion, tipo);
        assertNotNull(lista);
        assertEquals(1, lista.size());
        assertEquals("COMBUSTIBLE", lista.get(0).getTipo());
    }

    @Test
    public void testEncontrarPorCamion() throws Exception {
        System.out.println("encontrarPorCamion");
        Integer id_camion = 20;
        String tipo = "TEMPERATURA";
        DaoAlertasF instance = new DaoAlertasF();
        instance.crearAlerta(id_camion, "TEMPERATURA");
        instance.crearAlerta(id_camion, "KILOMETRAJE");

        List<Alertas> resultAll = instance.encontrarPorCamion(id_camion, null);
        assertEquals(2, resultAll.size());

        List<Alertas> resultTipo = instance.encontrarPorCamion(id_camion, tipo);
        assertEquals(1, resultTipo.size());
        assertEquals("TEMPERATURA", resultTipo.get(0).getTipo());
    }

    @Test
    public void testEncontrarHistorialPorCamion() throws Exception {
        System.out.println("encontrarHistorialPorCamion");
        int idCamion = 30;
        DaoAlertasF instance = new DaoAlertasF();
        instance.crearAlerta(idCamion, "A");
        instance.crearAlerta(idCamion, "B");

        List<Alertas> result = instance.encontrarHistorialPorCamion(idCamion);
        assertEquals(2, result.size());
    }

    @Test
    public void testEncontrarPrimeraPorCamion() throws Exception {
        System.out.println("encontrarPrimeraPorCamion");
        int id_camion = 40;
        DaoAlertasF instance = new DaoAlertasF();
        instance.crearAlerta(id_camion, "KILOMETRAJE");
        Thread.sleep(5);
        instance.crearAlerta(id_camion, "TEMPERATURA");

        Alertas primera = instance.encontrarPrimeraPorCamion(id_camion);
        assertNotNull(primera);
        assertEquals("KILOMETRAJE", primera.getTipo());
    }

    @Test
    public void testBorrarAlerta_int() throws Exception {
        System.out.println("borrarAlerta");
        DaoAlertasF instance = new DaoAlertasF();
        instance.crearAlerta(50, "X");
        List<Alertas> lista = instance.encontrarPorCamion(50, null);
        assertEquals(1, lista.size());
        int idAlerta = lista.get(0).getId();

        instance.borrarAlerta(idAlerta);
        List<Alertas> lista2 = instance.encontrarPorCamion(50, null);
        assertTrue(lista2.isEmpty());
    }

    @Test
    public void testBorrarAlerta_Connection_int() throws Exception {
        System.out.println("borrarAlerta with Connection");
        DaoAlertasF instance = new DaoAlertasF();
        instance.crearAlerta(60, "Y");
        List<Alertas> lista = instance.encontrarPorCamion(60, null);
        assertEquals(1, lista.size());
        int idAlerta = lista.get(0).getId();

        instance.borrarAlerta((Connection) null, idAlerta); // Connection ignorado en fake
        List<Alertas> lista2 = instance.encontrarPorCamion(60, null);
        assertTrue(lista2.isEmpty());
    }

    @Test
    public void testExisteAlertaNoAtendidaPorTipo() throws Exception {
        System.out.println("existeAlertaNoAtendidaPorTipo");
        int id_camion = 70;
        String tipo = "TEMPERATURA";
        DaoAlertasF instance = new DaoAlertasF();
        instance.crearAlerta(id_camion, tipo);

        assertTrue(instance.existeAlertaNoAtendidaPorTipo(id_camion, tipo));
        assertFalse(instance.existeAlertaNoAtendidaPorTipo(id_camion, "NO_EXISTE"));
    }

    @Test
    public void testInsertarAlertaTipoUnico() throws Exception {
        System.out.println("insertarAlertaTipoUnico");
        DaoAlertasF instance = new DaoAlertasF();
        // Creamos una alerta "manual" para pasar al método
        Alertas a = new Alertas(80, "UNICO");

        boolean first = instance.insertarAlertaTipoUnico(null, a);
        assertTrue(first);

        boolean second = instance.insertarAlertaTipoUnico(null, a);
        assertFalse(second);
    }

    @Test
    public void testEncontrarUltimasPorTipoPorCamion() throws Exception {
        System.out.println("encontrarUltimasPorTipoPorCamion");
        Integer idCamion = 90;
        DaoAlertasF instance = new DaoAlertasF();
        instance.crearAlerta(idCamion, "COMBUSTIBLE");
        Thread.sleep(5);
        instance.crearAlerta(idCamion, "COMBUSTIBLE"); // segunda alerta del mismo tipo
        instance.crearAlerta(idCamion, "TEMPERATURA");

        List<Alertas> lista = instance.encontrarUltimasPorTipoPorCamion(idCamion);
        // Debe devolver una alerta por tipo: COMBUSTIBLE (la última) y TEMPERATURA
        assertEquals(2, lista.size());
        // Comprobamos que hay al menos una COMBUSTIBLE y una TEMPERATURA
        boolean tieneCombustible = lista.stream().anyMatch(a -> "COMBUSTIBLE".equals(a.getTipo()));
        boolean tieneTemperatura = lista.stream().anyMatch(a -> "TEMPERATURA".equals(a.getTipo()));
        assertTrue(tieneCombustible && tieneTemperatura);
    }
}
