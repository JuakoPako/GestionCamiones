package bd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import model.Mantenimiento;


public class DAOMantenimiento {

    private ConexionBD oConexion;

    public DAOMantenimiento() throws SQLException {
        oConexion = ConexionBD.getInstancia();
    }

    private String escape(String s) {
        if (s == null) {
            return "";
        }
        return s.replace("'", "''");
    }


    public void crearMantenimiento(Mantenimiento m) throws SQLException {
        if (m == null) throw new SQLException("Objeto Mantenimiento nulo.");

        String sql = "INSERT INTO Mantenimiento (id_camion, fecha, motivo, descripcion, id_alerta_original) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = oConexion.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, m.getIdCamion());
            if (m.getFecha() != null) ps.setDate(2, new Date(m.getFecha().getTime()));
            else ps.setNull(2, Types.DATE);

            ps.setString(3, m.getMotivo());
            if (m.getDescripcion() != null && !m.getDescripcion().isEmpty()) ps.setString(4, m.getDescripcion());
            else ps.setNull(4, Types.VARCHAR);

            // Si tu modelo tiene id_alerta_original; si no, se inserta NULL
            try {
                Integer idAlerta = (Integer) Mantenimiento.class.getMethod("getId_alerta_original").invoke(m);
                if (idAlerta != null) ps.setInt(5, idAlerta);
                else ps.setNull(5, Types.INTEGER);
            } catch (NoSuchMethodException nsme) {
                ps.setNull(5, Types.INTEGER);
            } catch (Exception e) {
                // si la reflexión falla, dejar NULL
                ps.setNull(5, Types.INTEGER);
            }

            ps.executeUpdate();
            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) {
                    m.setId(keys.getInt(1));
                }
            }
        }
    }

    /**
     * Actualiza un mantenimiento (no transaccional).
     */
    public void actualizarMantenimiento(Mantenimiento m) throws SQLException {
        if (m == null) throw new SQLException("Objeto Mantenimiento nulo.");
        if (m.getId() == null || m.getId() <= 0) throw new SQLException("ID de mantenimiento inválido para actualizar.");

        String sql = "UPDATE Mantenimiento SET id_camion = ?, fecha = ?, motivo = ?, descripcion = ? WHERE id = ?";
        try (Connection conn = oConexion.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, m.getIdCamion());
            if (m.getFecha() != null) ps.setDate(2, new Date(m.getFecha().getTime()));
            else ps.setNull(2, Types.DATE);

            ps.setString(3, m.getMotivo());
            if (m.getDescripcion() != null && !m.getDescripcion().isEmpty()) ps.setString(4, m.getDescripcion());
            else ps.setNull(4, Types.VARCHAR);

            ps.setInt(5, m.getId());

            int affected = ps.executeUpdate();
            if (affected == 0) {
                throw new SQLException("No se actualizó el mantenimiento (id no encontrado): " + m.getId());
            }
        }
    }

    public Mantenimiento encontrarPorId(int id) throws SQLException {
        String sql = "SELECT id, id_camion, fecha, motivo, descripcion FROM Mantenimiento WHERE id = ? LIMIT 1";
        try (Connection conn = oConexion.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Mantenimiento m = new Mantenimiento();
                    m.setId(rs.getInt("id"));
                    m.setIdCamion(rs.getInt("id_camion"));
                    java.sql.Date fecha = rs.getDate("fecha");
                    m.setFecha(fecha == null ? null : new java.util.Date(fecha.getTime()));
                    m.setMotivo(rs.getString("motivo"));
                    m.setDescripcion(rs.getString("descripcion"));
                    return m;
                }
            }
        }
        return null;
    }

    /**
     * Devuelve el último mantenimiento por camión (no transaccional).
     */
    public Mantenimiento encontrarPorCamion(int idCamion) throws SQLException {
        String sql = "SELECT id, id_camion, fecha, motivo, descripcion FROM Mantenimiento WHERE id_camion = ? ORDER BY fecha DESC LIMIT 1";
        try (Connection conn = oConexion.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idCamion);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Mantenimiento m = new Mantenimiento();
                    m.setId(rs.getInt("id"));
                    m.setIdCamion(rs.getInt("id_camion"));
                    java.sql.Date fecha = rs.getDate("fecha");
                    m.setFecha(fecha == null ? null : new java.util.Date(fecha.getTime()));
                    m.setMotivo(rs.getString("motivo"));
                    m.setDescripcion(rs.getString("descripcion"));
                    return m;
                }
            }
        }
        return null;
    }

    /**
     * Lista todos los mantenimientos o los de un camión (no transaccional).
     */
    public List<Mantenimiento> encontrarTodos(Integer idCamion) throws SQLException {
        String sql;
        if (idCamion == null) {
            sql = "SELECT id, id_camion, fecha, motivo, descripcion FROM Mantenimiento ORDER BY fecha DESC";
            try (Connection conn = oConexion.getConnection();
                 PreparedStatement ps = conn.prepareStatement(sql);
                 ResultSet rs = ps.executeQuery()) {
                List<Mantenimiento> lista = new ArrayList<>();
                while (rs.next()) {
                    Mantenimiento m = new Mantenimiento();
                    m.setId(rs.getInt("id"));
                    m.setIdCamion(rs.getInt("id_camion"));
                    java.sql.Date fecha = rs.getDate("fecha");
                    m.setFecha(fecha == null ? null : new java.util.Date(fecha.getTime()));
                    m.setMotivo(rs.getString("motivo"));
                    m.setDescripcion(rs.getString("descripcion"));
                    lista.add(m);
                }
                return lista;
            }
        } else {
            sql = "SELECT id, id_camion, fecha, motivo, descripcion FROM Mantenimiento WHERE id_camion = ? ORDER BY fecha DESC";
            try (Connection conn = oConexion.getConnection();
                 PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setInt(1, idCamion);
                try (ResultSet rs = ps.executeQuery()) {
                    List<Mantenimiento> lista = new ArrayList<>();
                    while (rs.next()) {
                        Mantenimiento m = new Mantenimiento();
                        m.setId(rs.getInt("id"));
                        m.setIdCamion(rs.getInt("id_camion"));
                        java.sql.Date fecha = rs.getDate("fecha");
                        m.setFecha(fecha == null ? null : new java.util.Date(fecha.getTime()));
                        m.setMotivo(rs.getString("motivo"));
                        m.setDescripcion(rs.getString("descripcion"));
                        lista.add(m);
                    }
                    return lista;
                }
            }
        }
    }

    /**
     * Borra mantenimiento (no transaccional).
     */
    public void borrarMantenimiento(int id) throws SQLException {
        String sql = "DELETE FROM Mantenimiento WHERE id = ?";
        try (Connection conn = oConexion.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            int affected = ps.executeUpdate();
            if (affected == 0) {
                throw new SQLException("No se borró el mantenimiento (id no encontrado): " + id);
            }
        }
    }

    public void insertarMantenimiento(Connection conn, Mantenimiento m) throws SQLException {
        String sql = "INSERT INTO Mantenimiento (id_camion, fecha, motivo, descripcion, id_alerta_original) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, m.getIdCamion());
            if (m.getFecha() != null) {
                ps.setDate(2, new Date(m.getFecha().getTime())); // java.sql.Date
            } else {
                ps.setDate(2, new Date(System.currentTimeMillis()));
            }
            ps.setString(3, m.getMotivo());
            ps.setString(4, m.getDescripcion());
            // id_alerta_original puede ser null
            try {
                Integer idAlerta = (Integer) Mantenimiento.class.getMethod("getId_alerta_original").invoke(m);
                if (idAlerta != null) ps.setInt(5, idAlerta);
                else ps.setNull(5, Types.INTEGER);
            } catch (NoSuchMethodException nsme) {
                ps.setNull(5, Types.INTEGER);
            } catch (Exception e) {
                ps.setNull(5, Types.INTEGER);
            }
            ps.executeUpdate();
            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) m.setId(keys.getInt(1));
            }
        }
    }
}
