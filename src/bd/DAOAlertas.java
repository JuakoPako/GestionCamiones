/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bd;

import java.sql.SQLException;
import model.Alertas;

/**
 *
 * @author Franco
 */
public class DAOAlertas {

    private Conexion oConexion;

    public DAOAlertas() throws SQLException {
        oConexion = new Conexion("localhost", "gestion_camiones", "root", "");
    }

    public void crearAlerta(Alertas a) throws SQLException {
        String sql = "INSERT INTO Alertas VALUES(null,"
                + a.getIdCamion() + ",'"
                + a.getFechaAlerta() + "','"
                + a.getTipoAlerta() + "',"
                + (a.isAtendida() ? "TRUE" : "FALSE") + ");";
        oConexion.ejecutar(sql);
        System.out.println(sql);
    }
}
