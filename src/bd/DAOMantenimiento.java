/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bd;

import java.sql.SQLException;
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

    public void crearMantenimiento(Mantenimiento m) throws SQLException {
        String sql = "INSERT INTO Mantenimiento VALUES(null,"
                + m.getIdCamion() + ",'"
                + m.getFecha() + "','"
                + m.getTipo() + "','"
                + m.getDescripcion() + "',"
                + m.getKilometraje() + ");";
        oConexion.ejecutar(sql);
        System.out.println(sql);
    }
}
