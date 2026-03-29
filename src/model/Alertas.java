/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
 * @author Franco
 */

public class Alertas {

    private int id;
    private int id_camion;
    private Date fecha;
    private String responsable;
    private boolean atendida;

    public Alertas() {
    }

    public Alertas(int id, int id_camion, Date fecha, String responsable, boolean atendida) {
        this.id = id;
        this.id_camion = id_camion;
        this.fecha = fecha;
        this.responsable = responsable;
        this.atendida = atendida;
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

    public void setId(int id) {
        this.id = id;
    }

    public void setId_camion(int id_camion) {
        this.id_camion = id_camion;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    
    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    public boolean isAtendida() {
        return atendida;
    }

    public void setAtendida(boolean atendida) {
        this.atendida = atendida;
    }
}
