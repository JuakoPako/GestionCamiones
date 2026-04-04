// src/bd/DAOAlertas.java
package bd;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import model.Alertas;

public class DAOAlertas {

    private Conexion oConexion;

    public DAOAlertas() throws SQLException {
        oConexion = new Conexion("localhost", "gestion_camiones", "root", "1997");
    }

    private String escape(String s) {
        if (s == null) {
            return "";
        }
        return s.replace("'", "''");
    }

    public void crearAlerta(int id_camion, String tipo) throws SQLException {
        if (tipo == null) {
            tipo = "KILOMETRAJE";
        }
        String sql = "INSERT INTO Alertas (id_camion, tipo) VALUES ("
                + id_camion + ", '" + escape(tipo) + "');";
        System.out.println("DAOAlertas.crearAlerta SQL: " + sql);
        oConexion.ejecutar(sql);
    }

    public List<Alertas> encontrarPorCamion(Integer id_camion, String tipo) throws SQLException {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT id, id_camion, fecha, tipo FROM Alertas ");
        boolean whereAdded = false;
        if (id_camion != null) {
            sb.append("WHERE id_camion = ").append(id_camion).append(" ");
            whereAdded = true;
        }
        if (tipo != null && !tipo.isEmpty()) {
            sb.append(whereAdded ? "AND " : "WHERE ").append("tipo = '").append(escape(tipo)).append("' ");
            whereAdded = true;
        }
        sb.append("ORDER BY fecha DESC, id DESC;");

        String sql = sb.toString();
        System.out.println("DAOAlertas.encontrarPorCamion SQL: " + sql);

        List<Alertas> lista = new ArrayList<>();
        oConexion.rs = oConexion.ejecutarSelect(sql);
        try {
            while (oConexion.rs != null && oConexion.rs.next()) {
                Alertas a = new Alertas();
                a.setId(oConexion.rs.getInt("id"));
                a.setId_camion(oConexion.rs.getInt("id_camion"));
                java.sql.Timestamp ts = oConexion.rs.getTimestamp("fecha");
                a.setFecha(ts == null ? null : new Date(ts.getTime()));
                a.setTipo(oConexion.rs.getString("tipo"));
                lista.add(a);
            }
        } finally {
            if (oConexion.rs != null) {
                try {
                    oConexion.rs.close();
                } catch (SQLException e) {
                    /* ignore */ }
                oConexion.rs = null;
            }
        }
        return lista;
    }

    public List<Alertas> encontrarHistorialPorCamion(int idCamion) throws SQLException {
        String sql = "SELECT id, id_camion, fecha, tipo FROM Alertas WHERE id_camion = " + idCamion
                + " ORDER BY fecha DESC, id DESC;";
        System.out.println("DAOAlertas.encontrarHistorialPorCamion SQL: " + sql);

        List<Alertas> lista = new ArrayList<>();
        oConexion.rs = oConexion.ejecutarSelect(sql);
        try {
            while (oConexion.rs != null && oConexion.rs.next()) {
                Alertas a = new Alertas();
                a.setId(oConexion.rs.getInt("id"));
                a.setId_camion(oConexion.rs.getInt("id_camion"));
                java.sql.Timestamp ts = oConexion.rs.getTimestamp("fecha");
                a.setFecha(ts == null ? null : new Date(ts.getTime()));
                a.setTipo(oConexion.rs.getString("tipo"));
                lista.add(a);
            }
        } finally {
            if (oConexion.rs != null) {
                try {
                    oConexion.rs.close();
                } catch (SQLException e) {
                    /* ignore */ }
                oConexion.rs = null;
            }
        }
        return lista;
    }

    /**
     * Elimina una alerta por id (útil para pruebas o limpieza).
     */
    public void borrarAlerta(int idAlerta) throws SQLException {
        String sql = "DELETE FROM Alertas WHERE id = " + idAlerta + ";";
        System.out.println("DAOAlertas.borrarAlerta SQL: " + sql);
        oConexion.ejecutar(sql);
    }
}
