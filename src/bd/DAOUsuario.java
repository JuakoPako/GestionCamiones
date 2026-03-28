/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bd;

import java.sql.SQLException;
import java.util.ArrayList;
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

    public Usuario login(String nombre, String password) throws SQLException {

        String sql = "SELECT * FROM usuario WHERE nombre='"
                + nombre + "' AND password='" + password + "';";
        
        System.out.println(sql);

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
    
    public ArrayList<Usuario> getListaUsuarios() throws SQLException {
        ArrayList<Usuario> lista = new ArrayList<>();
        String sql = "SELECT id, nombre, correo, rol from usuario;";
        
        oConexion.rs = oConexion.ejecutarSelect(sql);
        
        while (oConexion.rs.next()) {
            Usuario u = new Usuario();
            u.setIdUsuario(oConexion.rs.getInt(1));
            u.setNombre(oConexion.rs.getString(2));
            u.setCorreo(oConexion.rs.getString(3));
            u.setRol(oConexion.rs.getString(4));
            lista.add(u);
        }
        oConexion.rs.close();
        
        return lista;
    }

}
