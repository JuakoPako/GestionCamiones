/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bd;

import java.sql.SQLException;
import model.Usuario;

/**
 *
 * @author A
 *
 * @author Zemekis
 */
public class DAOUsuario {

    private Conexion oConexion;


    /*
     constructor de DAOUsuario
     Genera la conexion entregando los datos
     */
    public DAOUsuario() throws SQLException {
        oConexion = new Conexion("localhost", "gestion_camiones", "root", "1997");
    }

    public void crearUsuario(Usuario oUsuario) throws SQLException {
        //INSERT INTO usuario VALUES(null,'juan','juanperez@gmal.com', 'juan123', 'CONDUCTOR');
        String sql = "INSERT INTO usuario VALUES(null,'"
                + oUsuario.getNombre() + "','"
                + oUsuario.getCorreo() + "','"
                + oUsuario.getPassword() + "','"
                + oUsuario.getRol() + "');";
        oConexion.ejecutar(sql);
        System.out.println(sql);

    }

}
