/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bd;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
        oConexion = new Conexion("localhost", "pruebaC", "root", "");
    }

    public void crearUsuario(Usuario oUsuario) throws SQLException {
        //INSERT INTO usuario VALUES(null,'juan','1234');
        String sql = "INSERT INTO usuario VALUES(null,'" + oUsuario.getNombre() + "','" + oUsuario.getPass() + "');";
        oConexion.ejecutar(sql);
        System.out.println(sql);

    }

//delete from usuario where id=7
//
//UPDATE usuario set nombre='qqqqqq' ,pass='9876' where id=6
    public void borrarUsuario(int id) throws SQLException {
        String sql = "delete from usuario where id=" + id + "";
        oConexion.ejecutar(sql);
        System.out.println(sql);

    }

    public void actualizarUsuario(Usuario oUsuario) throws SQLException {
        String sql = "UPDATE usuario set nombre='" + oUsuario.getNombre() + "' ,pass='" + oUsuario.getPass() + "' where id=" + oUsuario.getId() + "";
        oConexion.ejecutar(sql);
        System.out.println(sql);
    }

    //funcion para validar el log in
    //entregamos dos parametros siendo nombre y contraseña para realizar el posterior select
    public Usuario esUsuario(String nombre, String pass) throws SQLException {
        //SELECT * from usuario where nombre='' and pass=''
        //reemplazamos dentro del where estas variables
        String sql = "SELECT * from usuario where nombre='" + nombre + "' and pass='" + pass + "'";
        //ejecutamos la consulta y la almacenamos dentro de rs
        oConexion.rs = oConexion.ejecutarSelect(sql);
        //creamos un objeto vacio
        Usuario oUsuario;
        //atraves de la consulta IF revisamos dentro de rs para encontrar algun resultado 
        //si existe la funcion next indicara verdadero
        if (oConexion.rs.next()) {
            //inicializamos el objeto usuario
            oUsuario = new Usuario();
            //añadimos los componentes dependiendo del tipo y el nombre de la columna
            oUsuario.setId(oConexion.rs.getInt("id"));
            oUsuario.setNombre(oConexion.rs.getString("nombre"));
            oUsuario.setPass(oConexion.rs.getString("pass"));
            //cerramos la conexion
            oConexion.rs.close();
            //retornamos el objeto con datos para utilizarlo cuando llamemos a la funcion
            return oUsuario;
            //si se da el caso donde no se encuentre el el objeto se retornara nulo
        } else {
            oConexion.rs.close();
            return null;
        }

    }

    public List<Usuario> getUsuarios(String filtro) throws SQLException {
        ArrayList<Usuario> listaUsuarios = new ArrayList<>();

//SELECT * from usuario where nombre='' and pass=''
        String sql = "SELECT * from usuario where nombre like  '%" + filtro + "%' or id like'%"+filtro+"%' ";
        //se ejecuta y se guarda dentro de rs
        oConexion.rs = oConexion.ejecutarSelect(sql);
        //se crea un objeto usuario vacio
        Usuario oUsuario;
        //el ciclo revisa si existe algun dato y de ser asi es verdadero
        while (oConexion.rs.next()) {
            //se inicializa el objeto
            oUsuario = new Usuario();
            //se llena el objeto 
            oUsuario.setId(oConexion.rs.getInt("id"));
            oUsuario.setNombre(oConexion.rs.getString("nombre"));
            oUsuario.setPass(oConexion.rs.getString("pass"));
            //se añade a la lista
            listaUsuarios.add(oUsuario);

        }
        //se cierra la conexion para asegurar el programa
        oConexion.rs.close();
        //se retorna la lista de usuarios
        return listaUsuarios;

    }

}
