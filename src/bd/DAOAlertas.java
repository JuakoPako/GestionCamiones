package bd;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Alertas;

/**
 * DAO para la tabla Alertas
 */
public class DAOAlertas {

    private Conexion oConexion;

    public DAOAlertas() throws SQLException {
        // Ajusta credenciales si tu proyecto las maneja distinto
        oConexion = new Conexion("localhost", "gestion_camiones", "root", "");
    }

    private String escape(String s) {
        if (s == null) {
            return "";
        }
        return s.replace("'", "''");
    }

    /**
     * Inserta una alerta simple en la tabla Alertas. Responsable puede ser ""
     * si la crea el sistema.
     */
    public void insertarAlerta(int id_camion, String responsable) throws SQLException {
        String sql = "INSERT INTO Alertas (id_camion, fecha, responsable, atendida) VALUES ("
                + id_camion + ", CURDATE(), '" + escape(responsable) + "', FALSE);";
        System.out.println("DAOAlertas.insertarAlerta SQL: " + sql);
        oConexion.ejecutar(sql);
    }

    /**
     * Devuelve true si existe alguna alerta NO atendida para el camión.
     */
    public boolean existeAlertaNoAtendida(int id_camion) throws SQLException {
        String sql = "SELECT 1 FROM Alertas WHERE id_camion = " + id_camion + " AND atendida = FALSE LIMIT 1;";
        ResultSet rs = oConexion.ejecutarSelect(sql);
        try {
            return rs != null && rs.next();
        } finally {
            if (rs != null) {
                rs.close();
            }
        }
    }

    /**
     * Marca una alerta como atendida y guarda el responsable (puede usarse para
     * pasar de no atendida a atendida).
     */
    public void marcarAtendida(int idAlerta, String responsable) throws SQLException {
        String sql = "UPDATE Alertas SET atendida = TRUE, responsable = '" + escape(responsable)
                + "' WHERE id = " + idAlerta + ";";
        System.out.println("DAOAlertas.marcarAtendida SQL: " + sql);
        oConexion.ejecutar(sql);
    }

    /**
     * Obtiene las alertas de un camión. Si id_camion == null devuelve todas. Si
     * soloNoAtendidas == true filtra por atendida = FALSE.
     */
    public List<Alertas> findByCamion(Integer id_camion, boolean soloNoAtendidas) throws SQLException {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT id, id_camion, fecha, responsable, atendida ");
        sb.append("FROM Alertas ");
        boolean whereAdded = false;
        if (id_camion != null) {
            sb.append("WHERE id_camion = ").append(id_camion).append(" ");
            whereAdded = true;
        }
        if (soloNoAtendidas) {
            sb.append(whereAdded ? "AND " : "WHERE ").append("atendida = FALSE ");
        }
        sb.append("ORDER BY id DESC;");

        String sql = sb.toString();
        System.out.println("DAOAlertas.findByCamion SQL: " + sql);

        ResultSet rs = oConexion.ejecutarSelect(sql);
        try {
            List<Alertas> lista = new ArrayList<>();
            while (rs != null && rs.next()) {
                Alertas a = new Alertas();
                a.setId(rs.getInt("id"));
                a.setId_camion(rs.getInt("id_camion"));
                a.setFecha(rs.getDate("fecha"));
                a.setResponsable(rs.getString("responsable"));
                a.setAtendida(rs.getBoolean("atendida"));
                lista.add(a);
            }
            return lista;
        } finally {
            if (rs != null) {
                rs.close();
            }
        }
    }

    public void insertarAlertaConPatente(int id_camion) throws SQLException {
        // Usamos una subconsulta para traer la patente del camión y guardarla en responsable
        String sql = "INSERT INTO Alertas (id_camion, fecha, responsable, atendida) "
                + "SELECT c.id, CURDATE(), c.patente, FALSE "
                + "FROM Camion c WHERE c.id = " + id_camion + ";";
        System.out.println("DAOAlertas.insertarAlertaConPatente SQL: " + sql);
        oConexion.ejecutar(sql);
    }

    public java.util.List<model.Alertas> findHistoryByCamion(int idCamion) throws SQLException {
        java.util.ArrayList<model.Alertas> lista = new java.util.ArrayList<>();
        String sql = "SELECT id, id_camion, fecha, responsable, atendida "
                + "FROM Alertas WHERE id_camion = " + idCamion + " "
                + "ORDER BY fecha DESC, id DESC;";
        System.out.println("DAOAlertas.findHistoryByCamion SQL: " + sql);
        oConexion.rs = oConexion.ejecutarSelect(sql);
        try {
            while (oConexion.rs.next()) {
                model.Alertas a = new model.Alertas();
                a.setId(oConexion.rs.getInt("id"));
                a.setId_camion(oConexion.rs.getInt("id_camion"));
                a.setFecha(oConexion.rs.getDate("fecha"));
                a.setResponsable(oConexion.rs.getString("responsable"));
                a.setAtendida(oConexion.rs.getBoolean("atendida"));
                lista.add(a);
            }
        } finally {
            if (oConexion.rs != null) {
                try {
                    oConexion.rs.close();
                } catch (SQLException e) {
                }
                oConexion.rs = null;
            }
        }
        return lista;
    }

}
