/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bd;

import java.sql.SQLException;
import model.Camion;

/**
 *
 * @author Franco
 */
public class DAOCamion {

    private Conexion oConexion;

    public DAOCamion() throws SQLException {
        oConexion = new Conexion("localhost", "gestion_camiones", "root", "1997");
    }

    public void crearCamion(Camion oCamion) throws SQLException {
        String sql = "INSERT INTO Camion VALUES(null,'"
                + oCamion.getPatenteCamion() + "','"
                + oCamion.getMarca() + "','"
                + oCamion.getModelo() + "',"
                + oCamion.getAnio() + ","
                + oCamion.getKilometraje() + ","
                + (oCamion.getIdConductor() > 0 ? oCamion.getIdConductor() : "NULL")
                + ");";
        System.out.println(sql);
    }
}
