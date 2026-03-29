/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bd;

import com.mysql.jdbc.PreparedStatement;
import java.util.List;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Camion;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Franco
 */
public class DAOCamion {

    private Conexion oConexion;

    public DAOCamion() throws SQLException {
        oConexion = new Conexion("localhost", "gestion_camiones", "root", "");
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
        // Normalizar filtro para búsqueda parcial
        String like = "%" + filtro + "%";

        // Construir SQL concatenado (estilo actual del proyecto)
        String sql = "SELECT c.id, c.patente, c.marca, c.modelo, c.anio, c.kilometraje, c.id_conductor, u.nombre AS nombre_conductor "
                + "FROM Camion c LEFT JOIN Usuario u ON c.id_conductor = u.id "
                + "WHERE c.patente LIKE '" + like + "' OR c.id LIKE '" + like + "' "
                + "ORDER BY c.patente;";

        // Ejecutar y mapear resultados
        oConexion.rs = oConexion.ejecutarSelect(sql);
        try {
            while (oConexion.rs.next()) {
                Camion c = new Camion();
                c.setIdCamion(oConexion.rs.getInt("id"));
                c.setPatenteCamion(oConexion.rs.getString("patente"));
                c.setMarca(oConexion.rs.getString("marca"));
                c.setModelo(oConexion.rs.getString("modelo"));
                c.setAnio(oConexion.rs.getInt("anio"));
                c.setKilometraje(oConexion.rs.getInt("kilometraje"));
                int idConductor = oConexion.rs.getInt("id_conductor");
                if (oConexion.rs.wasNull()) {
                    c.setIdConductor(0);
                } else {
                    c.setIdConductor(idConductor);
                }
                lista.add(c);
            }
        } finally {
            if (oConexion.rs != null) {
                try {
                    oConexion.rs.close();
                } catch (SQLException e) {
                    /* log si quieres */ }
                oConexion.rs = null;
            }
        }

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

    public ArrayList<Camion> getListaCamiones() throws SQLException {

        ArrayList<Camion> listaCamion = new ArrayList<>();

        String sql = "SELECT camion.id, camion.patente, camion.marca, camion.modelo, camion.anio, camion.kilometraje, camion.id_conductor FROM camion;";

        oConexion.ejecutarSelect(sql);

        while (oConexion.rs.next()) {

            Camion c = new Camion();

            c.setIdCamion(oConexion.rs.getInt(1));
            c.setPatenteCamion(oConexion.rs.getString(2));
            c.setMarca(oConexion.rs.getString(3));
            c.setModelo(oConexion.rs.getString(4));
            c.setAnio(oConexion.rs.getInt(5));
            c.setKilometraje(oConexion.rs.getInt(6));
            c.setIdConductor(oConexion.rs.getInt(7));

            listaCamion.add(c);
        }

        oConexion.rs.close();

        return listaCamion;
    }

    public void borrarCamion(int id) throws SQLException {
        String sql = "DELETE FROM camion where id =" + id + ";";
        oConexion.ejecutar(sql);
        System.out.println(sql);
    }

    public void actualizarCamion(Camion oCamion) throws SQLException {
        String sql = "UPDATE Camion SET "
                + "patente = '" + oCamion.getPatenteCamion() + "', "
                + "marca = " + (oCamion.getMarca() == null ? "NULL" : "'" + oCamion.getMarca() + "'") + ", "
                + "modelo = " + (oCamion.getModelo() == null ? "NULL" : "'" + oCamion.getModelo() + "'") + ", "
                + "anio = " + oCamion.getAnio() + ", "
                + "kilometraje = " + oCamion.getKilometraje() + ", "
                + "id_conductor = " + (oCamion.getIdConductor() > 0 ? oCamion.getIdConductor() : "NULL") + " "
                + "WHERE id = " + oCamion.getIdCamion() + ";";

        oConexion.ejecutar(sql);
        System.out.println(sql);
    }

    public boolean patenteExisteExceptoId(String patente, int idExcepto) throws SQLException {
        String sql = "SELECT COUNT(*) AS cnt FROM Camion WHERE patente = '" + patente + "' AND id <> " + idExcepto + ";";
        oConexion.rs = oConexion.ejecutarSelect(sql);
        try {
            if (oConexion.rs.next()) {
                return oConexion.rs.getInt("cnt") > 0;
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
        return false;
    }

    public model.Camion findById(int id) throws SQLException {
        String sql = "SELECT id, patente, marca, modelo, anio, kilometraje, id_conductor FROM Camion WHERE id = " + id + " LIMIT 1;";
        bd.Conexion con = bd.Conexion.getInstancia();
        ResultSet rs = con.ejecutarSelect(sql);
        try {
            if (rs != null && rs.next()) {
                model.Camion c = new model.Camion();
                c.setIdCamion(rs.getInt("id"));
                c.setPatenteCamion(rs.getString("patente"));
                c.setMarca(rs.getString("marca"));
                c.setModelo(rs.getString("modelo"));
                c.setAnio(rs.getInt("anio"));
                int km = rs.getInt("kilometraje");
                c.setKilometraje(rs.wasNull() ? null : km);
                c.setIdConductor(rs.getInt("id_conductor"));
                return c;
            }
            return null;
        } finally {
            if (rs != null) {
                rs.close();
            }
        }
    }

    public ArrayList<Camion> getListaCamionesPorConductor(int idConductor) throws SQLException {
        ArrayList<Camion> lista = new ArrayList<>();

        String sql = "SELECT c.*, u.nombre AS nombre_conductor "
                + "FROM camion c "
                + "INNER JOIN usuario u ON c.id_conductor = u.id "
                + "WHERE c.id_conductor = " + idConductor + ";";

        oConexion.rs = oConexion.ejecutarSelect(sql);

        while (oConexion.rs.next()) {
            Camion c = new Camion();
            c.setIdCamion(oConexion.rs.getInt("id"));
            c.setPatenteCamion(oConexion.rs.getString("patente"));
            c.setMarca(oConexion.rs.getString("marca"));
            c.setModelo(oConexion.rs.getString("modelo"));
            c.setAnio(oConexion.rs.getInt("anio"));
            c.setKilometraje(oConexion.rs.getInt("kilometraje"));
            c.setIdConductor(oConexion.rs.getInt("id_conductor"));

            lista.add(c);
        }
        oConexion.rs.close();

        return lista; // <--- ESTO ES LO QUE TE FALTA
    }

    public void sumarKilometraje(int idCamion, int kmsNuevos) throws SQLException {
        // SQL que suma directamente en la base de datos
        String sql = "UPDATE camion SET "
                + "kilometraje = kilometraje + " + kmsNuevos + " "
                + "WHERE id = " + idCamion + ";";

        System.out.println(sql); // Para que veas la consulta en consola
        oConexion.ejecutar(sql);
    }

}
