package bd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.Alertas;

public class DAOAlertas {

    private Conexion oConexion;

    public DAOAlertas() throws SQLException {
        oConexion = Conexion.getInstancia();
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
        String sql = "INSERT INTO Alertas (id_camion, tipo) VALUES (?, ?)";
        try (Connection conn = oConexion.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id_camion);
            ps.setString(2, tipo);
            ps.executeUpdate();
        }
    }

    public List<Alertas> encontrarPorCamion(Integer id_camion, String tipo) throws SQLException {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT id, id_camion, fecha, tipo FROM Alertas ");
        boolean whereAdded = false;
        if (id_camion != null) {
            sb.append("WHERE id_camion = ? ");
            whereAdded = true;
        }
        if (tipo != null && !tipo.isEmpty()) {
            sb.append(whereAdded ? "AND " : "WHERE ").append("tipo = ? ");
            whereAdded = true;
        }
        sb.append("ORDER BY fecha DESC, id DESC");

        List<Alertas> lista = new ArrayList<>();
        try (Connection conn = oConexion.getConnection(); PreparedStatement ps = conn.prepareStatement(sb.toString())) {

            int idx = 1;
            if (id_camion != null) {
                ps.setInt(idx++, id_camion);
            }
            if (tipo != null && !tipo.isEmpty()) {
                ps.setString(idx++, tipo);
            }

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Alertas a = new Alertas();
                    a.setId(rs.getInt("id"));
                    a.setId_camion(rs.getInt("id_camion"));
                    Timestamp ts = rs.getTimestamp("fecha");
                    a.setFecha(ts == null ? null : new Date(ts.getTime()));
                    a.setTipo(rs.getString("tipo"));
                    lista.add(a);
                }
            }
        }
        return lista;
    }

    public List<Alertas> encontrarHistorialPorCamion(int idCamion) throws SQLException {
        String sql = "SELECT id, id_camion, fecha, tipo FROM Alertas WHERE id_camion = ? ORDER BY fecha DESC, id DESC";
        List<Alertas> lista = new ArrayList<>();
        try (Connection conn = oConexion.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idCamion);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Alertas a = new Alertas();
                    a.setId(rs.getInt("id"));
                    a.setId_camion(rs.getInt("id_camion"));
                    Timestamp ts = rs.getTimestamp("fecha");
                    a.setFecha(ts == null ? null : new Date(ts.getTime()));
                    a.setTipo(rs.getString("tipo"));
                    lista.add(a);
                }
            }
        }
        return lista;
    }

    // Devuelve la primera alerta (por fecha ascendente) para el camión.
    public Alertas encontrarPrimeraPorCamion(int id_camion) throws SQLException {
        String sql = "SELECT id, id_camion, fecha, tipo FROM Alertas WHERE id_camion = ? ORDER BY fecha ASC LIMIT 1";
        try (Connection conn = oConexion.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id_camion);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Alertas a = new Alertas();
                    a.setId(rs.getInt("id"));
                    a.setId_camion(rs.getInt("id_camion"));
                    Timestamp ts = rs.getTimestamp("fecha");
                    a.setFecha(ts == null ? null : new Date(ts.getTime()));
                    a.setTipo(rs.getString("tipo"));
                    return a;
                }
            }
        }
        return null;
    }

    // Versión no transaccional: abre su propia conexión
    public void borrarAlerta(int idAlerta) throws SQLException {
        String sql = "DELETE FROM Alertas WHERE id = ?";
        try (Connection conn = oConexion.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idAlerta);
            int affected = ps.executeUpdate();
            if (affected == 0) {
                throw new SQLException("No se borró la alerta (ya inexistente). id=" + idAlerta);
            }
        }
    }

    // Versión transaccional: usar dentro de una transacción abierta (no cierra la Connection)
    public void borrarAlerta(Connection conn, int idAlerta) throws SQLException {
        String sql = "DELETE FROM Alertas WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idAlerta);
            int affected = ps.executeUpdate();
            if (affected == 0) {
                throw new SQLException("No se borró la alerta (ya inexistente). id=" + idAlerta);
            }
        }
    }

    public boolean existeAlertaNoAtendidaPorTipo(int id_camion, String tipo) throws SQLException {
        if (tipo == null) {
            tipo = "";
        }
        String sql = "SELECT 1 FROM Alertas WHERE id_camion = " + id_camion
                + " AND tipo = '" + escape(tipo) + "' AND atendida = FALSE LIMIT 1;";
        System.out.println("DAOAlertas.existeAlertaNoAtendidaPorTipo SQL: " + sql);
        java.sql.ResultSet rs = oConexion.ejecutarSelect(sql);
        try {
            return rs != null && rs.next();
        } finally {
            if (rs != null) {
                rs.close();
            }
        }

    }
}
