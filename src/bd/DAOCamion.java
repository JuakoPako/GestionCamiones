package bd;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.math.BigDecimal;
import model.Camion;


public class DAOCamion {

    private ConexionBD oConexion;

    public DAOCamion() throws SQLException {
        oConexion = new ConexionBD("localhost", "gestion_camiones", "root", "1997");
    }

    public void crearCamion(Camion oCamion) throws SQLException {
        String sql = "INSERT INTO Camion (patente, marca, modelo, anio, kilometraje, id_conductor) VALUES('"
                + escape(oCamion.getPatenteCamion()) + "',"
                + (oCamion.getMarca() == null ? "NULL" : "'" + escape(oCamion.getMarca()) + "'") + ","
                + (oCamion.getModelo() == null ? "NULL" : "'" + escape(oCamion.getModelo()) + "'") + ","
                + oCamion.getAnio() + ","
                + oCamion.getKilometraje() + ","
                + (oCamion.getIdConductor() > 0 ? oCamion.getIdConductor() : "NULL")
                + ");";
        System.out.println(sql);
        oConexion.ejecutar(sql);
    }

    public boolean patenteExiste(String patente) throws SQLException {
        String sql = "SELECT COUNT(*) AS cnt FROM Camion WHERE patente = '" + escape(patente) + "';";
        ResultSet rs = oConexion.ejecutarSelect(sql);
        try {
            if (rs.next()) {
                return rs.getInt("cnt") > 0;
            }
            return false;
        } finally {
            closeRs();
        }
    }

    public List<Camion> getCamiones(String filtro) throws SQLException {
        ArrayList<Camion> lista = new ArrayList<>();
        if (filtro == null) filtro = "";
        String like = "%" + filtro + "%";

        String sql = "SELECT c.id, c.patente, c.marca, c.modelo, c.anio, c.kilometraje, c.combustible_actual, c.id_conductor, u.nombre AS nombre_conductor "
                + "FROM Camion c LEFT JOIN Usuario u ON c.id_conductor = u.id "
                + "WHERE c.patente LIKE '" + escape(like) + "' OR c.id LIKE '" + escape(like) + "' "
                + "ORDER BY c.patente;";

        oConexion.rs = oConexion.ejecutarSelect(sql);
        try {
            while (oConexion.rs.next()) {
                Camion c = new Camion();
                c.setIdCamion(oConexion.rs.getInt("id"));
                c.setPatenteCamion(oConexion.rs.getString("patente"));
                c.setMarca(oConexion.rs.getString("marca"));
                c.setModelo(oConexion.rs.getString("modelo"));
                c.setAnio(oConexion.rs.getInt("anio"));
                c.setKilometraje(oConexion.rs.getInt("kilometraje"));
                BigDecimal comb = oConexion.rs.getBigDecimal("combustible_actual");
                c.setCombustibleActual(comb == null ? BigDecimal.ZERO : comb);
                int idConductor = oConexion.rs.getInt("id_conductor");
                if (oConexion.rs.wasNull()) {
                    c.setIdConductor(0);
                } else {
                    c.setIdConductor(idConductor);
                }
                lista.add(c);
            }
        } finally {
            closeRs();
        }
        return lista;
    }

    public Camion obtenerPorPatente(String patente) throws SQLException {
        String sql = "SELECT id, patente, marca, modelo, anio, kilometraje, combustible_actual, id_conductor FROM Camion WHERE patente = '"
                + escape(patente) + "' LIMIT 1;";
        oConexion.rs = oConexion.ejecutarSelect(sql);
        try {
            if (oConexion.rs.next()) {
                Camion c = new Camion();
                c.setIdCamion(oConexion.rs.getInt("id"));
                c.setPatenteCamion(oConexion.rs.getString("patente"));
                c.setMarca(oConexion.rs.getString("marca"));
                c.setModelo(oConexion.rs.getString("modelo"));
                c.setAnio(oConexion.rs.getInt("anio"));
                c.setKilometraje(oConexion.rs.getInt("kilometraje"));
                BigDecimal comb = oConexion.rs.getBigDecimal("combustible_actual");
                c.setCombustibleActual(comb == null ? BigDecimal.ZERO : comb);
                c.setIdConductor(oConexion.rs.getInt("id_conductor"));
                return c;
            }
            return null;
        } finally {
            closeRs();
        }
    }

    public ArrayList<Camion> getListaCamiones() throws SQLException {
        ArrayList<Camion> listaCamion = new ArrayList<>();
        String sql = "SELECT id, patente, marca, modelo, anio, kilometraje, combustible_actual, id_conductor FROM Camion;";
        oConexion.rs = oConexion.ejecutarSelect(sql);
        try {
            while (oConexion.rs.next()) {
                Camion c = new Camion();
                c.setIdCamion(oConexion.rs.getInt("id"));
                c.setPatenteCamion(oConexion.rs.getString("patente"));
                c.setMarca(oConexion.rs.getString("marca"));
                c.setModelo(oConexion.rs.getString("modelo"));
                c.setAnio(oConexion.rs.getInt("anio"));
                c.setKilometraje(oConexion.rs.getInt("kilometraje"));
                BigDecimal comb = oConexion.rs.getBigDecimal("combustible_actual");
                c.setCombustibleActual(comb == null ? BigDecimal.ZERO : comb);
                c.setIdConductor(oConexion.rs.getInt("id_conductor"));
                listaCamion.add(c);
            }
        } finally {
            closeRs();
        }
        return listaCamion;
    }

    public void borrarCamion(int id) throws SQLException {
        String sql = "DELETE FROM Camion WHERE id = " + id + ";";
        System.out.println(sql);
        oConexion.ejecutar(sql);
    }

    public void actualizarCamion(Camion oCamion) throws SQLException {
        String sql = "UPDATE Camion SET "
                + "patente = '" + escape(oCamion.getPatenteCamion()) + "', "
                + "marca = " + (oCamion.getMarca() == null ? "NULL" : "'" + escape(oCamion.getMarca()) + "'") + ", "
                + "modelo = " + (oCamion.getModelo() == null ? "NULL" : "'" + escape(oCamion.getModelo()) + "'") + ", "
                + "anio = " + oCamion.getAnio() + ", "
                + "kilometraje = " + oCamion.getKilometraje() + ", "
                + "id_conductor = " + (oCamion.getIdConductor() > 0 ? oCamion.getIdConductor() : "NULL") + " "
                + "WHERE id = " + oCamion.getIdCamion() + ";";
        System.out.println(sql);
        oConexion.ejecutar(sql);
    }

    public boolean patenteExisteExceptoId(String patente, int idExcepto) throws SQLException {
        String sql = "SELECT COUNT(*) AS cnt FROM Camion WHERE patente = '" + escape(patente) + "' AND id <> " + idExcepto + ";";
        oConexion.rs = oConexion.ejecutarSelect(sql);
        try {
            if (oConexion.rs.next()) {
                return oConexion.rs.getInt("cnt") > 0;
            }
            return false;
        } finally {
            closeRs();
        }
    }

    public Camion findById(int id) throws SQLException {
        String sql = "SELECT id, patente, marca, modelo, anio, kilometraje, combustible_actual, id_conductor FROM Camion WHERE id = " + id + " LIMIT 1;";
        oConexion.rs = oConexion.ejecutarSelect(sql);
        try {
            if (oConexion.rs.next()) {
                Camion c = new Camion();
                c.setIdCamion(oConexion.rs.getInt("id"));
                c.setPatenteCamion(oConexion.rs.getString("patente"));
                c.setMarca(oConexion.rs.getString("marca"));
                c.setModelo(oConexion.rs.getString("modelo"));
                c.setAnio(oConexion.rs.getInt("anio"));
                int km = oConexion.rs.getInt("kilometraje");
                c.setKilometraje(oConexion.rs.wasNull() ? 0 : km);
                BigDecimal comb = oConexion.rs.getBigDecimal("combustible_actual");
                c.setCombustibleActual(comb == null ? BigDecimal.ZERO : comb);
                c.setIdConductor(oConexion.rs.getInt("id_conductor"));
                return c;
            }
            return null;
        } finally {
            closeRs();
        }
    }

    public ArrayList<Camion> getListaCamionesPorConductor(int idConductor) throws SQLException {
        ArrayList<Camion> lista = new ArrayList<>();
        String sql = "SELECT c.id, c.patente, c.marca, c.modelo, c.anio, c.kilometraje, c.combustible_actual, c.id_conductor, u.nombre AS nombre_conductor "
                + "FROM Camion c INNER JOIN Usuario u ON c.id_conductor = u.id WHERE c.id_conductor = " + idConductor + ";";
        oConexion.rs = oConexion.ejecutarSelect(sql);
        try {
            while (oConexion.rs.next()) {
                Camion c = new Camion();
                c.setIdCamion(oConexion.rs.getInt("id"));
                c.setPatenteCamion(oConexion.rs.getString("patente"));
                c.setMarca(oConexion.rs.getString("marca"));
                c.setModelo(oConexion.rs.getString("modelo"));
                c.setAnio(oConexion.rs.getInt("anio"));
                c.setKilometraje(oConexion.rs.getInt("kilometraje"));
                BigDecimal comb = oConexion.rs.getBigDecimal("combustible_actual");
                c.setCombustibleActual(comb == null ? BigDecimal.ZERO : comb);
                c.setIdConductor(oConexion.rs.getInt("id_conductor"));
                lista.add(c);
            }
        } finally {
            closeRs();
        }
        return lista;
    }

    public void applyDeltaKmAndConsume(int idCamion, int deltaKm) throws SQLException {
        if (deltaKm <= 0) return;

        final int MAX_RETRIES = 3;
        int attempt = 0;
        boolean updated = false;

        while (attempt < MAX_RETRIES && !updated) {
            attempt++;

            // 1) Leer valores actuales
            String sel = "SELECT kilometraje, combustible_actual FROM Camion WHERE id = " + idCamion + " LIMIT 1;";
            oConexion.rs = oConexion.ejecutarSelect(sel);
            int kmActual;
            BigDecimal combustibleActual;
            try {
                if (!oConexion.rs.next()) {
                    return; // no existe
                }
                kmActual = oConexion.rs.getInt("kilometraje");
                combustibleActual = oConexion.rs.getBigDecimal("combustible_actual");
                if (combustibleActual == null) combustibleActual = BigDecimal.ZERO;
            } finally {
                closeRs();
            }

            int nuevoKm = kmActual + deltaKm;
            BigDecimal consumo = new BigDecimal(deltaKm).multiply(new BigDecimal("0.38"));
            BigDecimal nuevoCombustible = combustibleActual.subtract(consumo);
            if (nuevoCombustible.compareTo(BigDecimal.ZERO) < 0) {
                nuevoCombustible = BigDecimal.ZERO;
            }

            // 2) Intentar UPDATE condicionando por los valores leídos (optimistic lock)
            // Comparaciones exactas con DECIMAL: usamos toPlainString para construir el literal.
            String upd = "UPDATE Camion SET kilometraje = " + nuevoKm
                    + ", combustible_actual = " + nuevoCombustible.toPlainString()
                    + " WHERE id = " + idCamion
                    + " AND kilometraje = " + kmActual
                    + " AND (combustible_actual = " + combustibleActual.toPlainString() + " OR (combustible_actual IS NULL AND " + combustibleActual.toPlainString() + " = 0))"
                    + ";";

            System.out.println("Attempt " + attempt + " - " + upd);
            oConexion.ejecutar(upd);

            // Comprobar si la fila fue actualizada consultando el nuevo estado
            String chk = "SELECT kilometraje, combustible_actual FROM Camion WHERE id = " + idCamion + " LIMIT 1;";
            oConexion.rs = oConexion.ejecutarSelect(chk);
            try {
                if (oConexion.rs.next()) {
                    int kmAfter = oConexion.rs.getInt("kilometraje");
                    BigDecimal combAfter = oConexion.rs.getBigDecimal("combustible_actual");
                    if (combAfter == null) combAfter = BigDecimal.ZERO;
                    // Si los valores coinciden con lo que intentamos escribir, consideramos éxito
                    if (kmAfter == nuevoKm && combAfter.compareTo(nuevoCombustible) == 0) {
                        updated = true;
                        // Crear alerta si corresponde
                        if (nuevoCombustible.compareTo(new BigDecimal("25.00")) <= 0) {
                            try {
                                DAOAlertas daoA = new DAOAlertas();
                                daoA.crearAlerta(idCamion, "COMBUSTIBLE_BAJO");
                            } catch (Exception ex) {
                                // No detener la operación por fallo al crear alerta; loguear si hace falta
                                System.err.println("Error creando alerta: " + ex.getMessage());
                            }
                        }
                    } else {
                        // otro proceso cambió la fila; reintentar
                        updated = false;
                    }
                } else {
                    // fila desapareció inesperadamente
                    return;
                }
            } finally {
                closeRs();
            }

            if (!updated) {
                // pequeña espera antes de reintentar para reducir contención
                try { Thread.sleep(50); } catch (InterruptedException ie) { Thread.currentThread().interrupt(); }
            }
        }

        if (!updated) {
            throw new SQLException("No se pudo actualizar Camion " + idCamion + " tras " + MAX_RETRIES + " intentos (conflicto concurrente).");
        }
    }

    /**
     * Método legacy: delega a applyDeltaKmAndConsume para mantener compatibilidad.
     */
    public void sumarKilometraje(int idCamion, int kmsNuevos) throws SQLException {
        applyDeltaKmAndConsume(idCamion, kmsNuevos);
    }

    /**
     * Registrar repostaje: aumenta combustible_actual y opcionalmente inserta en tabla Repostaje.
     * Implementado sin transacción global (usa la API de Conexion disponible).
     */
    public void registrarRepostaje(int idCamion, BigDecimal litros, String usuario) throws SQLException {
        if (litros == null || litros.compareTo(BigDecimal.ZERO) <= 0) return;

        String upd = "UPDATE Camion SET combustible_actual = combustible_actual + " + litros.toPlainString()
                + " WHERE id = " + idCamion + ";";
        System.out.println(upd);
        oConexion.ejecutar(upd);

        // Intentar insertar en Repostaje si la tabla existe
        String ins = "INSERT INTO Repostaje (id_camion, fecha, litros, registrado_por) VALUES ("
                + idCamion + ", NOW(), " + litros.toPlainString() + ", '" + escape(usuario) + "');";
        try {
            oConexion.ejecutar(ins);
        } catch (SQLException ex) {
            // Si la tabla no existe o falla, ignoramos la inserción (no crítico)
            System.err.println("No se pudo insertar en Repostaje (posiblemente no existe): " + ex.getMessage());
        }
    }

    // -------------------------
    // Utilidades privadas
    // -------------------------

    private void closeRs() {
        if (oConexion.rs != null) {
            try {
                oConexion.rs.close();
            } catch (SQLException e) {
                /* ignore */
            }
            oConexion.rs = null;
        }
    }

    private String escape(String s) {
        if (s == null) return null;
        return s.replace("'", "''");
    }
}
