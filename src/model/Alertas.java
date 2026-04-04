
package model;

import java.util.Date;


public class Alertas {

    private int id;
    private int id_camion;
    private Date fecha;
    private String tipo;

    public Alertas() {
    }

    public Alertas(int id, int id_camion, Date fecha, String tipo) {
        this.id = id;
        this.id_camion = id_camion;
        this.fecha = fecha;
        this.tipo = tipo;
    }

    public int getId() {
        return id;
    }

    public int getId_camion() {
        return id_camion;
    }

    public Date getFecha() {
        return fecha;
    }

    public String getTipo() {
        return tipo;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setId_camion(int id_camion) {
        this.id_camion = id_camion;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
