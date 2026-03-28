/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

public class Mantenimiento {

    private Integer id;
    private int idCamion;
    private Date fecha;
    private String tipo;
    private String descripcion;
    private Integer kilometraje;

    public Mantenimiento() {
    }

    public Mantenimiento(Integer id, int idCamion, Date fecha, String tipo, String descripcion, Integer kilometraje) {
        this.id = id;
        this.idCamion = idCamion;
        this.fecha = fecha;
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.kilometraje = kilometraje;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getIdCamion() {
        return idCamion;
    }

    public void setIdCamion(int idCamion) {
        this.idCamion = idCamion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getKilometraje() {
        return kilometraje;
    }

    public void setKilometraje(Integer kilometraje) {
        this.kilometraje = kilometraje;
    }

    public String toString() {
        return "Mantenimiento{"
                + "id=" + id
                + ", idCamion=" + idCamion
                + ", fecha=" + fecha
                + ", tipo='" + tipo + '\''
                + ", descripcion='" + descripcion + '\''
                + ", kilometraje=" + kilometraje
                + '}';
    }
}
