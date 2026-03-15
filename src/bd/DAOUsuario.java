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
        oConexion = new Conexion("localhost", "gestion_camiones", "root", "");
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

    public Usuario login(String usuario, String password) throws SQLException {

        String sql = "SELECT * FROM usuario WHERE nombre='"
                + usuario + "' AND password='" + password + "';";

        oConexion.rs = oConexion.ejecutarSelect(sql);

        if (oConexion.rs.next()) {

            Usuario u = new Usuario();

            u.setIdUsuario(oConexion.rs.getInt("id"));
            u.setNombre(oConexion.rs.getString("nombre"));
            u.setCorreo(oConexion.rs.getString("correo"));
            u.setPassword(oConexion.rs.getString("password"));
            u.setRol(oConexion.rs.getString("rol"));

            return u;
        }

        return null;
    }

}
