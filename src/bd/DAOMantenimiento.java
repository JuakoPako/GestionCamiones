/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bd;

import java.sql.SQLException;
import java.util.List;
import model.Mantenimiento;

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
        if (m.getId()== null || m.getId()<= 0) {
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
}
