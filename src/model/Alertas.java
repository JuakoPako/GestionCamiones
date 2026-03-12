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
    private String tipoAlerta;
    
    public Alertas (){
        
    }

    public Alertas(int idAlerta, int idCamion, Date fechaAlerta, String tipoAlerta) {
        this.idAlerta = idAlerta;
        this.idCamion = idCamion;
        this.fechaAlerta = fechaAlerta;
        this.tipoAlerta = tipoAlerta;
    }

    public int getIdAlerta() {
        return idAlerta;
    }

    public int getIdCamion() {
        return idCamion;
    }

    public Date getFechaAlerta() {
        return fechaAlerta;
    }

    public String getTipoAlerta() {
        return tipoAlerta;
    }

    public void setIdAlerta(int idAlerta) {
        this.idAlerta = idAlerta;
    }

    public void setIdCamion(int idCamion) {
        this.idCamion = idCamion;
    }

    public void setFechaAlerta(Date fechaAlerta) {
        this.fechaAlerta = fechaAlerta;
    }

    public void setTipoAlerta(String tipoAlerta) {
        this.tipoAlerta = tipoAlerta;
    }
    
}
