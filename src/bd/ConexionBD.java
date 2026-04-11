package bd;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConexionBD {

    private Connection con;
    private MysqlDataSource dataSource;
    public Statement sen;
    public ResultSet rs;

    public ConexionBD(String server, String bd, String user, String pass) throws SQLException {
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

    private static ConexionBD instancia = null;

    public static ConexionBD getInstancia() throws SQLException {
        if (instancia == null) {
            String server = "localhost";
            String bd = "gestion_camiones";
            String user = "root";
            String pass = "";
            instancia = new ConexionBD(server, bd, user, pass);
        }
        return instancia;
    }

    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    public void close() {
        try {
            if (rs != null && !rs.isClosed()) {
                rs.close();
            }
        } catch (Exception ignore) {
        }
        try {
            if (sen != null) {
                sen.close();
            }
        } catch (Exception ignore) {
        }
        try {
            if (con != null && !con.isClosed()) {
                con.close();
            }
        } catch (Exception ignore) {
        }
        con = null;
    }

    public static Connection getTestConnection() throws SQLException {
        // Leer propiedades de sistema (tests deben establecerlas antes)
        String url = System.getProperty("DB_URL", "jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1");
        String user = System.getProperty("DB_USER", "sa");
        String pass = System.getProperty("DB_PASS", "");

        // Intentar cargar drivers (no falla si no están)
        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException ignore) {
        }
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ignore) {
        }

        return java.sql.DriverManager.getConnection(url, user, pass);
    }
}
