/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bd;

import java.sql.SQLException;
import java.util.List;
import javax.xml.transform.Result;
import model.Mantenimiento;
import java.sql.ResultSet;

/**
 *
 * @author Franco
 */
public class DAOMantenimiento {

    private Conexion oConexion;

    public DAOMantenimiento() throws SQLException {
        oConexion = new Conexion("localhost", "gestion_camiones", "root", "1997");
    }

    public void crearMantenimiento(model.Mantenimiento m) throws SQLException {
        if (m == null) {
            throw new SQLException("Objeto Mantenimiento nulo.");
        }

        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO Mantenimiento (id_camion, fecha, tipo, descripcion, kilometraje) VALUES (");

        // id_camion (no nulo según esquema)
        sql.append(m.getIdCamion()).append(", ");

        // fecha (date)
        if (m.getFecha() != null) {
            java.sql.Date d = new java.sql.Date(m.getFecha().getTime());
            sql.append("'").append(d.toString()).append("', ");
        } else {
            sql.append("NULL, ");
        }

        // tipo (varchar not null en esquema)
        sql.append("'").append(escape(m.getTipo())).append("', ");

        // descripcion (text, nullable)
        if (m.getDescripcion() != null && !m.getDescripcion().isEmpty()) {
            sql.append("'").append(escape(m.getDescripcion())).append("', ");
        } else {
            sql.append("NULL, ");
        }

        // kilometraje (int, nullable)
        if (m.getKilometraje() != null) {
            sql.append(m.getKilometraje());
        } else {
            sql.append("NULL");
        }

        sql.append(");");

        // Ejecutar
        oConexion.ejecutar(sql.toString());
    }

    /**
     * Actualiza un mantenimiento existente identificado por m.getId().
     * Actualiza únicamente las columnas existentes: id_camion, fecha, tipo,
     * descripcion, kilometraje. Lanza SQLException si id inválido.
     */
    public void actualizarMantenimiento(model.Mantenimiento m) throws SQLException {
        if (m == null) {
            throw new SQLException("Objeto Mantenimiento nulo.");
        }
        if (m.getId() == null || m.getId() <= 0) {
            throw new SQLException("ID de mantenimiento inválido para actualizar.");
        }

        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE Mantenimiento SET ");

        // id_camion (si quieres impedir su cambio, pasa el mismo id_camion original en el objeto)
        sql.append("id_camion = ").append(m.getIdCamion()).append(", ");

        // fecha
        if (m.getFecha() != null) {
            java.sql.Date d = new java.sql.Date(m.getFecha().getTime());
            sql.append("fecha = '").append(d.toString()).append("', ");
        } else {
            sql.append("fecha = NULL, ");
        }

        // tipo
        sql.append("tipo = '").append(escape(m.getTipo())).append("', ");

        // descripcion
        if (m.getDescripcion() != null && !m.getDescripcion().isEmpty()) {
            sql.append("descripcion = '").append(escape(m.getDescripcion())).append("', ");
        } else {
            sql.append("descripcion = NULL, ");
        }

        // kilometraje
        if (m.getKilometraje() != null) {
            sql.append("kilometraje = ").append(m.getKilometraje()).append(" ");
        } else {
            sql.append("kilometraje = NULL ");
        }

        sql.append("WHERE id = ").append(m.getId()).append(";");

        // Ejecutar
        oConexion.ejecutar(sql.toString());
    }

    /**
     * Helper para escapar comillas simples en cadenas (mantener estilo del
     * repo). Mejor migrar a PreparedStatement cuando sea posible.
     */
    private String escape(String s) {
        if (s == null) {
            return "";
        }
        return s.replace("'", "''");
    }

    public model.Mantenimiento findById(int id) throws SQLException {
        String sql = "SELECT id, id_camion, fecha, tipo, descripcion, kilometraje FROM Mantenimiento WHERE id = " + id + " LIMIT 1;";
        bd.Conexion con = bd.Conexion.getInstancia();
        ResultSet rs = con.ejecutarSelect(sql);
        try {
            if (rs != null && rs.next()) {
                model.Mantenimiento m = new model.Mantenimiento();
                m.setId(rs.getInt("id"));
                m.setIdCamion(rs.getInt("id_camion"));
                java.sql.Date fecha = rs.getDate("fecha");
                m.setFecha(fecha);
                m.setTipo(rs.getString("tipo"));
                m.setDescripcion(rs.getString("descripcion"));
                int km = rs.getInt("kilometraje");
                m.setKilometraje(rs.wasNull() ? null : km);
                return m;
            }
            return null;
        } finally {
            if (rs != null) {
                rs.close();
            }
        }
    }

// Devuelve el último Mantenimiento por id_camion (o null si no existe)
    public model.Mantenimiento findByCamion(int idCamion) throws SQLException {
        String sql = "SELECT id, id_camion, fecha, tipo, descripcion, kilometraje "
                + "FROM Mantenimiento WHERE id_camion = " + idCamion
                + " ORDER BY fecha DESC LIMIT 1;";
        bd.Conexion con = bd.Conexion.getInstancia();
        ResultSet rs = con.ejecutarSelect(sql);
        try {
            if (rs != null && rs.next()) {
                model.Mantenimiento m = new model.Mantenimiento();
                m.setId(rs.getInt("id"));
                m.setIdCamion(rs.getInt("id_camion"));
                java.sql.Date fecha = rs.getDate("fecha");
                m.setFecha(fecha);
                m.setTipo(rs.getString("tipo"));
                m.setDescripcion(rs.getString("descripcion"));
                int km = rs.getInt("kilometraje");
                m.setKilometraje(rs.wasNull() ? null : km);
                return m;
            }
            return null;
        } finally {
            if (rs != null) {
                rs.close();
            }
        }
    }

// Actualiza un mantenimiento existente (lanza SQLException si falla)
    public void update(model.Mantenimiento m) throws SQLException {
        if (m == null || m.getId() == null) {
            throw new IllegalArgumentException("Mantenimiento o id nulo en update");
        }

        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE Mantenimiento SET ");
        sb.append("fecha = ").append(m.getFecha() != null ? ("'" + m.getFecha().toString() + "'") : "NULL").append(", ");
        sb.append("tipo = ").append(m.getTipo() != null ? ("'" + m.getTipo().replace("'", "''") + "'") : "NULL").append(", ");
        sb.append("descripcion = ").append(m.getDescripcion() != null ? ("'" + m.getDescripcion().replace("'", "''") + "'") : "NULL").append(", ");
        sb.append("kilometraje = ").append(m.getKilometraje() != null ? m.getKilometraje() : "NULL");
        sb.append(" WHERE id = ").append(m.getId()).append(";");

        bd.Conexion con = bd.Conexion.getInstancia();
        con.ejecutar(sb.toString());
    }
}
