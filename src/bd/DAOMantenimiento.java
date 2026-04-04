package bd;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Mantenimiento;

public class DAOMantenimiento {

    private Conexion oConexion;

    public DAOMantenimiento() throws SQLException {
        oConexion = new Conexion("localhost", "gestion_camiones", "root", "1997");
    }

    private String escape(String s) {
        if (s == null) {
            return "";
        }
        return s.replace("'", "''");
    }

    public void crearMantenimiento(Mantenimiento m) throws SQLException {
        if (m == null) {
            throw new SQLException("Objeto Mantenimiento nulo.");
        }

        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO Mantenimiento (id_camion, fecha, motivo, descripcion) VALUES (");

        sql.append(m.getIdCamion()).append(", ");

        if (m.getFecha() != null) {
            java.sql.Date d = new java.sql.Date(m.getFecha().getTime());
            sql.append("'").append(d.toString()).append("', ");
        } else {
            sql.append("NULL, ");
        }

        sql.append("'").append(escape(m.getMotivo())).append("', ");

        if (m.getDescripcion() != null && !m.getDescripcion().isEmpty()) {
            sql.append("'").append(escape(m.getDescripcion())).append("'");
        } else {
            sql.append("NULL");
        }

        sql.append(");");

        System.out.println("DAOMantenimiento.crearMantenimiento SQL: " + sql.toString());
        oConexion.ejecutar(sql.toString());
    }

    public void actualizarMantenimiento(Mantenimiento m) throws SQLException {
        if (m == null) {
            throw new SQLException("Objeto Mantenimiento nulo.");
        }
        if (m.getId() == null || m.getId() <= 0) {
            throw new SQLException("ID de mantenimiento inválido para actualizar.");
        }

        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE Mantenimiento SET ");

        sql.append("id_camion = ").append(m.getIdCamion()).append(", ");

        if (m.getFecha() != null) {
            java.sql.Date d = new java.sql.Date(m.getFecha().getTime());
            sql.append("fecha = '").append(d.toString()).append("', ");
        } else {
            sql.append("fecha = NULL, ");
        }

        sql.append("motivo = '").append(escape(m.getMotivo())).append("', ");

        if (m.getDescripcion() != null && !m.getDescripcion().isEmpty()) {
            sql.append("descripcion = '").append(escape(m.getDescripcion())).append("' ");
        } else {
            sql.append("descripcion = NULL ");
        }

        sql.append("WHERE id = ").append(m.getId()).append(";");

        System.out.println("DAOMantenimiento.actualizarMantenimiento SQL: " + sql.toString());
        oConexion.ejecutar(sql.toString());
    }


    public Mantenimiento encontrarPorId(int id) throws SQLException {
        String sql = "SELECT id, id_camion, fecha, motivo, descripcion FROM Mantenimiento WHERE id = " + id + " LIMIT 1;";
        ResultSet rs = oConexion.ejecutarSelect(sql);
        try {
            if (rs != null && rs.next()) {
                Mantenimiento m = new Mantenimiento();
                m.setId(rs.getInt("id"));
                m.setIdCamion(rs.getInt("id_camion"));
                java.sql.Date fecha = rs.getDate("fecha");
                m.setFecha(fecha);
                m.setMotivo(rs.getString("motivo"));
                m.setDescripcion(rs.getString("descripcion"));
                return m;
            }
            return null;
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    /* ignore */ }
            }
        }
    }


    public Mantenimiento encontrarPorCamion(int idCamion) throws SQLException {
        String sql = "SELECT id, id_camion, fecha, motivo, descripcion "
                + "FROM Mantenimiento WHERE id_camion = " + idCamion
                + " ORDER BY fecha DESC LIMIT 1;";
        ResultSet rs = oConexion.ejecutarSelect(sql);
        try {
            if (rs != null && rs.next()) {
                Mantenimiento m = new Mantenimiento();
                m.setId(rs.getInt("id"));
                m.setIdCamion(rs.getInt("id_camion"));
                java.sql.Date fecha = rs.getDate("fecha");
                m.setFecha(fecha);
                m.setMotivo(rs.getString("motivo"));
                m.setDescripcion(rs.getString("descripcion"));
                return m;
            }
            return null;
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    /* ignore */ }
            }
        }
    }


    public List<Mantenimiento> encontrarTodos(Integer idCamion) throws SQLException {
        String sql;
        if (idCamion == null) {
            sql = "SELECT id, id_camion, fecha, motivo, descripcion FROM Mantenimiento ORDER BY fecha DESC;";
        } else {
            sql = "SELECT id, id_camion, fecha, motivo, descripcion "
                    + "FROM Mantenimiento WHERE id_camion = " + idCamion + " ORDER BY fecha DESC;";
        }

        ResultSet rs = oConexion.ejecutarSelect(sql);
        try {
            List<Mantenimiento> lista = new ArrayList<>();
            while (rs != null && rs.next()) {
                Mantenimiento m = new Mantenimiento();
                m.setId(rs.getInt("id"));
                m.setIdCamion(rs.getInt("id_camion"));
                java.sql.Date fecha = rs.getDate("fecha");
                m.setFecha(fecha);
                m.setMotivo(rs.getString("motivo"));
                m.setDescripcion(rs.getString("descripcion"));
                lista.add(m);
            }
            return lista;
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                }
            }
        }
    }

    public void borrarMantenimiento(int id) throws SQLException {
        String sql = "DELETE FROM Mantenimiento WHERE id = " + id + ";";
        System.out.println("DAOMantenimiento.borrarMantenimiento SQL: " + sql);
        oConexion.ejecutar(sql);
    }
}
