/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author bevod
 */
public class Conductor {
    
    private int idConductor;
    private String nombre;
    private String licencia;
    private int telefono;
    private int idCamionAsignado;

    public Conductor() {
    }

    public Conductor(int idConductor, String nombre, String licencia, int telefono, int idCamionAsignado) {
        this.idConductor = idConductor;
        this.nombre = nombre;
        this.licencia = licencia;
        this.telefono = telefono;
        this.idCamionAsignado = idCamionAsignado;
    }

    public int getIdConductor() {
        return idConductor;
    }

    public void setIdConductor(int idConductor) {
        this.idConductor = idConductor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getLicencia() {
        return licencia;
    }

    public void setLicencia(String licencia) {
        this.licencia = licencia;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public int getIdCamionAsignado() {
        return idCamionAsignado;
    }

    public void setIdCamionAsignado(int idCamionAsignado) {
        this.idCamionAsignado = idCamionAsignado;
    }
    
    
    
}
