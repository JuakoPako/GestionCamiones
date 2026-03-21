/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bd;

import java.awt.List;
import java.sql.SQLException;
import java.util.ArrayList;
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
        oConexion.ejecutar(sql);
    }

    public boolean patenteExiste(String patente) throws SQLException {
        String sql = "SELECT COUNT(*) AS cnt FROM Camion WHERE patente = '" + patente + "';";
        java.sql.ResultSet rs = oConexion.ejecutarSelect(sql);
        if (rs.next()) {
            int cnt = rs.getInt("cnt");
            return cnt > 0;
        }
        return false;
    }

    public List<Camion> getCamiones(String filtro) throws SQLException {
        ArrayList<Camion> lista = new ArrayList<>();

        if (filtro == null) {
            filtro = "";
        }
        String sql = "SELECT c.id, c.patente, c.marca, c.modelo, c.anio, c.kilometraje, c.id_conductor, u.nombre AS nombre_conductor "
                + "FROM Camion c LEFT JOIN Usuario u ON c.id_conductor = u.id "
                + "WHERE c.patente LIKE '%" + filtro + "%' OR c.id LIKE '%" + filtro + "%' "
                + "ORDER BY c.patente;";

        oConexion.rs = oConexion.ejecutarSelect(sql);

        while (oConexion.rs.next()) {
            Camion c = new Camion();
            c.setIdCamion(oConexion.rs.getInt("id"));
            c.setPatenteCamion(oConexion.rs.getString("patente"));
            c.setMarca(oConexion.rs.getString("marca"));
            c.setModelo(oConexion.rs.getString("modelo"));
            c.setAnio(oConexion.rs.getInt("anio"));
            c.setKilometraje(oConexion.rs.getInt("kilometraje"));
            // si tu modelo tiene campo idConductor y nombreConductor
            lista.add(c);
        }
        oConexion.rs.close();
        return lista;
    }

    public Camion obtenerPorPatente(String patente) throws SQLException {
        String sql = "SELECT id, patente, marca, modelo, anio, kilometraje, id_conductor FROM Camion WHERE patente = '" + patente + "';";
        oConexion.rs = oConexion.ejecutarSelect(sql);
        if (oConexion.rs.next()) {
            Camion c = new Camion();
            c.setIdCamion(oConexion.rs.getInt("id"));
            c.setPatenteCamion(oConexion.rs.getString("patente"));
            c.setMarca(oConexion.rs.getString("marca"));
            c.setModelo(oConexion.rs.getString("modelo"));
            c.setAnio(oConexion.rs.getInt("anio"));
            c.setKilometraje(oConexion.rs.getInt("kilometraje"));
            c.setIdConductor(oConexion.rs.getInt("id_conductor"));
            oConexion.rs.close();
            return c;
        }
        oConexion.rs.close();
        return null;
    }
}
