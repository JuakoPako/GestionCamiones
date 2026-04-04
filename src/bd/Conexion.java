package bd;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Conexion {

    private Connection con;                
    private MysqlDataSource dataSource;   
    public Statement sen;
    public ResultSet rs;

    public Conexion(String server, String bd, String user, String pass) throws SQLException {
        dataSource = new MysqlDataSource();
        dataSource.setServerName(server);
        dataSource.setDatabaseName(bd);
        dataSource.setUser(user);
        dataSource.setPassword(pass);

        con = dataSource.getConnection();
    }


    public void ejecutar(String sql) throws SQLException {
        sen = con.createStatement();
        sen.executeUpdate(sql);
        sen.close();
    }

    public ResultSet ejecutarSelect(String select) throws SQLException {
        sen = con.createStatement();
        rs = sen.executeQuery(select);
        return rs;
    }

    private static Conexion instancia = null;

    public static Conexion getInstancia() throws SQLException {
        if (instancia == null) {
            String server = "localhost";
            String bd = "gestion_camiones";
            String user = "root";
            String pass = "1997";
            instancia = new Conexion(server, bd, user, pass);
        }
        return instancia;
    }

    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    public void close() {
        try {
            if (rs != null && !rs.isClosed()) rs.close();
        } catch (Exception ignore) {}
        try {
            if (sen != null) sen.close();
        } catch (Exception ignore) {}
        try {
            if (con != null && !con.isClosed()) con.close();
        } catch (Exception ignore) {}
        con = null;
    }
}
