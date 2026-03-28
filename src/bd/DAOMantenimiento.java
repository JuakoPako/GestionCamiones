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

}
