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

    private int idAlerta;
    private int idCamion;
    private Date fechaAlerta;
    private String responsable;
    private boolean atendida;

    public Alertas() {
    }

    public Alertas(int idAlerta, int idCamion, Date fechaAlerta, String tipoAlerta, boolean atendida) {
        this.idAlerta = idAlerta;
        this.idCamion = idCamion;
        this.fechaAlerta = fechaAlerta;
        this.responsable = tipoAlerta;
        this.atendida = atendida;
    }

    public int getIdAlerta() {
        return idAlerta;
    }

    public void setIdAlerta(int idAlerta) {
        this.idAlerta = idAlerta;
    }

    public int getIdCamion() {
        return idCamion;
    }

    public void setIdCamion(int idCamion) {
        this.idCamion = idCamion;
    }

    public Date getFechaAlerta() {
        return fechaAlerta;
    }

    public void setFechaAlerta(Date fechaAlerta) {
        this.fechaAlerta = fechaAlerta;
    }

    public String getTipoAlerta() {
        return responsable;
    }

    public void setTipoAlerta(String tipoAlerta) {
        this.responsable = tipoAlerta;
    }

    public boolean isAtendida() {
        return atendida;
    }

    public void setAtendida(boolean atendida) {
        this.atendida = atendida;
    }
}
