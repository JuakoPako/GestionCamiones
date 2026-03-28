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
        String sql = "INSERT INTO Mantenimiento (id_camion, fecha, tipo, descripcion, kilometraje) VALUES ("
                + m.getIdCamion() + ", "
                + "'" + new java.sql.Date(m.getFecha().getTime()) + "', "
                + "'" + (m.getTipo() == null ? "" : m.getTipo().replace("'", "''")) + "', "
                + (m.getDescripcion() == null ? "NULL" : "'" + m.getDescripcion().replace("'", "''") + "'") + ", "
                + (m.getKilometraje() == null ? "NULL" : m.getKilometraje())
                + ");";
        oConexion.ejecutar(sql);
    }

    public void actualizarMantenimiento(model.Mantenimiento m) throws SQLException {
        String sql = "UPDATE Mantenimiento SET "
                + "id_camion = " + m.getIdCamion() + ", "
                + "fecha = '" + new java.sql.Date(m.getFecha().getTime()) + "', "
                + "tipo = '" + (m.getTipo() == null ? "" : m.getTipo().replace("'", "''")) + "', "
                + "descripcion = " + (m.getDescripcion() == null ? "NULL" : "'" + m.getDescripcion().replace("'", "''") + "'") + ", "
                + "kilometraje = " + (m.getKilometraje() == null ? "NULL" : m.getKilometraje())
                + " WHERE id = " + m.getIdMantenimiento() + ";";
        oConexion.ejecutar(sql);
        System.out.println(sql);
    }


}
