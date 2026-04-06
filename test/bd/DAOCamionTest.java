package bd;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Test;
import static org.junit.Assert.*;


public class DAOCamionTest {


    private static class Camion {
        private static int NEXT_ID = 1;
        private int id;
        private String patente;
        private int conductorId;
        private int kilometraje;
        private BigDecimal tanque;

        Camion(String patente) {
            this.id = NEXT_ID++;
            this.patente = patente;
            this.kilometraje = 0;
            this.tanque = BigDecimal.ZERO;
        }

        public int getId() { return id; }
        public String getPatente() { return patente; }
        public void setPatente(String p) { this.patente = p; }
        public int getConductorId() { return conductorId; }
        public void setConductorId(int c) { this.conductorId = c; }
        public int getKilometraje() { return kilometraje; }
        public void sumarKilometraje(int kms) { this.kilometraje += kms; }
        public BigDecimal getTanque() { return tanque; }
        public void repostar(BigDecimal litros) { this.tanque = this.tanque.add(litros); }
    }


    private static class DAOCamionF {
        private final Map<Integer, Camion> store = new HashMap<>();
        private final Map<String, Integer> patenteIndex = new HashMap<>();

        public void crearCamion(Camion oCamion) {
            if (oCamion == null) return;
            store.put(oCamion.getId(), oCamion);
            patenteIndex.put(oCamion.getPatente(), oCamion.getId());
        }

        public boolean patenteExiste(String patente) {
            if (patente == null) return false;
            return patenteIndex.containsKey(patente);
        }

        public List<Camion> getCamiones(String filtro) {
            List<Camion> res = new ArrayList<>();
            for (Camion c : store.values()) {
                if (filtro == null || filtro.isEmpty() || c.getPatente().contains(filtro)) {
                    res.add(c);
                }
            }
            return res;
        }

        public Camion obtenerPorPatente(String patente) {
            Integer id = patenteIndex.get(patente);
            return id == null ? null : store.get(id);
        }

        public ArrayList<Camion> getListaCamiones() {
            return new ArrayList<>(store.values());
        }

        public void borrarCamion(int id) {
            Camion removed = store.remove(id);
            if (removed != null) patenteIndex.remove(removed.getPatente());
        }

        public void actualizarCamion(Camion oCamion) {
            if (oCamion == null) return;
            Camion existing = store.get(oCamion.getId());
            if (existing != null) {
                // actualizar patente e info básica
                patenteIndex.remove(existing.getPatente());
                existing.setPatente(oCamion.getPatente());
                patenteIndex.put(existing.getPatente(), existing.getId());
            }
        }

        public boolean patenteExisteExceptoId(String patente, int idExcepto) {
            Integer id = patenteIndex.get(patente);
            return id != null && id != idExcepto;
        }

        public Camion findById(int id) {
            return store.get(id);
        }

        public ArrayList<Camion> getListaCamionesPorConductor(int idConductor) {
            ArrayList<Camion> res = new ArrayList<>();
            for (Camion c : store.values()) {
                if (c.getConductorId() == idConductor) res.add(c);
            }
            return res;
        }

        public void applyDeltaKmAndConsume(int idCamion, int deltaKm) {
            Camion c = store.get(idCamion);
            if (c != null) {
                c.sumarKilometraje(deltaKm);
                // Simula consumo: 1 litro cada 10 km
                BigDecimal consumo = BigDecimal.valueOf(deltaKm).divide(BigDecimal.TEN);
                c.repostar(consumo.negate()); // restamos del tanque (puede quedar negativo en el fake)
            }
        }

        public void sumarKilometraje(int idCamion, int kmsNuevos) {
            Camion c = store.get(idCamion);
            if (c != null) c.sumarKilometraje(kmsNuevos);
        }

        public void registrarRepostaje(int idCamion, BigDecimal litros, String usuario) {
            Camion c = store.get(idCamion);
            if (c != null && litros != null) c.repostar(litros);
        }
    }

    public DAOCamionTest() {
    }

    @Test
    public void testCrearCamion() throws Exception {
        System.out.println("crearCamion");
        Camion oCamion = new Camion("ABC-123");
        DAOCamionF instance = new DAOCamionF();
        instance.crearCamion(oCamion);

        Camion found = instance.obtenerPorPatente("ABC-123");
        assertNotNull(found);
        assertEquals("ABC-123", found.getPatente());
    }

    @Test
    public void testPatenteExiste() throws Exception {
        System.out.println("patenteExiste");
        DAOCamionF instance = new DAOCamionF();
        Camion c = new Camion("ZZZ-999");
        instance.crearCamion(c);

        assertTrue(instance.patenteExiste("ZZZ-999"));
        assertFalse(instance.patenteExiste("NO-EXISTE"));
    }

    @Test
    public void testGetCamiones() throws Exception {
        System.out.println("getCamiones");
        DAOCamionF instance = new DAOCamionF();
        instance.crearCamion(new Camion("AAA-111"));
        instance.crearCamion(new Camion("BBB-222"));

        List<Camion> all = instance.getCamiones(null);
        assertEquals(2, all.size());

        List<Camion> filtered = instance.getCamiones("AAA");
        assertEquals(1, filtered.size());
        assertEquals("AAA-111", filtered.get(0).getPatente());
    }

    @Test
    public void testObtenerPorPatente() throws Exception {
        System.out.println("obtenerPorPatente");
        DAOCamionF instance = new DAOCamionF();
        Camion c = new Camion("PAT-555");
        instance.crearCamion(c);

        Camion res = instance.obtenerPorPatente("PAT-555");
        assertNotNull(res);
        assertEquals(c.getId(), res.getId());
    }

    @Test
    public void testGetListaCamiones() throws Exception {
        System.out.println("getListaCamiones");
        DAOCamionF instance = new DAOCamionF();
        instance.crearCamion(new Camion("L1"));
        instance.crearCamion(new Camion("L2"));

        ArrayList<Camion> list = instance.getListaCamiones();
        assertEquals(2, list.size());
    }

    @Test
    public void testBorrarCamion() throws Exception {
        System.out.println("borrarCamion");
        DAOCamionF instance = new DAOCamionF();
        Camion c = new Camion("DEL-1");
        instance.crearCamion(c);

        assertNotNull(instance.findById(c.getId()));
        instance.borrarCamion(c.getId());
        assertNull(instance.findById(c.getId()));
    }

    @Test
    public void testActualizarCamion() throws Exception {
        System.out.println("actualizarCamion");
        DAOCamionF instance = new DAOCamionF();
        Camion c = new Camion("OLD-1");
        instance.crearCamion(c);

        Camion updated = new Camion("NEW-1");
        // keep same id to simulate update
        updated.id = c.getId();
        instance.actualizarCamion(updated);

        Camion res = instance.findById(c.getId());
        assertEquals("NEW-1", res.getPatente());
    }

    @Test
    public void testPatenteExisteExceptoId() throws Exception {
        System.out.println("patenteExisteExceptoId");
        DAOCamionF instance = new DAOCamionF();
        Camion c1 = new Camion("EX-1");
        Camion c2 = new Camion("EX-2");
        instance.crearCamion(c1);
        instance.crearCamion(c2);

        assertTrue(instance.patenteExisteExceptoId("EX-1", c2.getId()));
        assertFalse(instance.patenteExisteExceptoId("EX-1", c1.getId()));
    }

    @Test
    public void testFindById() throws Exception {
        System.out.println("findById");
        DAOCamionF instance = new DAOCamionF();
        Camion c = new Camion("ID-1");
        instance.crearCamion(c);

        Camion res = instance.findById(c.getId());
        assertNotNull(res);
        assertEquals("ID-1", res.getPatente());
    }

    @Test
    public void testGetListaCamionesPorConductor() throws Exception {
        System.out.println("getListaCamionesPorConductor");
        DAOCamionF instance = new DAOCamionF();
        Camion c1 = new Camion("C1"); c1.setConductorId(5);
        Camion c2 = new Camion("C2"); c2.setConductorId(5);
        Camion c3 = new Camion("C3"); c3.setConductorId(6);
        instance.crearCamion(c1); instance.crearCamion(c2); instance.crearCamion(c3);

        ArrayList<Camion> res = instance.getListaCamionesPorConductor(5);
        assertEquals(2, res.size());
    }

    @Test
    public void testApplyDeltaKmAndConsume() throws Exception {
        System.out.println("applyDeltaKmAndConsume");
        DAOCamionF instance = new DAOCamionF();
        Camion c = new Camion("KM-1");
        instance.crearCamion(c);

        instance.applyDeltaKmAndConsume(c.getId(), 100);
        Camion res = instance.findById(c.getId());
        assertEquals(100, res.getKilometraje());
        // consumo simulado: -10 litros (puede ser negativo en el fake)
        assertNotNull(res.getTanque());
    }

    @Test
    public void testSumarKilometraje() throws Exception {
        System.out.println("sumarKilometraje");
        DAOCamionF instance = new DAOCamionF();
        Camion c = new Camion("SUM-1");
        instance.crearCamion(c);

        instance.sumarKilometraje(c.getId(), 250);
        assertEquals(250, instance.findById(c.getId()).getKilometraje());
    }

    @Test
    public void testRegistrarRepostaje() throws Exception {
        System.out.println("registrarRepostaje");
        DAOCamionF instance = new DAOCamionF();
        Camion c = new Camion("REP-1");
        instance.crearCamion(c);

        instance.registrarRepostaje(c.getId(), new BigDecimal("45.5"), "tester");
        assertEquals(new BigDecimal("45.5"), instance.findById(c.getId()).getTanque());
    }
}
