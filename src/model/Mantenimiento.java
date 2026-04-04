package model;

import java.util.Date;


public class Mantenimiento {

    private Integer id;
    private int idCamion;
    private Date fecha;
    private String motivo; 
    private String descripcion;

    public Mantenimiento() {
    }

    public Mantenimiento(Integer id, int idCamion, Date fecha, String motivo, String descripcion) {
        this.id = id;
        this.idCamion = idCamion;
        this.fecha = fecha;
        this.motivo = motivo;
        this.descripcion = descripcion;
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

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "Mantenimiento{"
                + "id=" + id
                + ", idCamion=" + idCamion
                + ", fecha=" + fecha
                + ", motivo='" + motivo + '\''
                + ", descripcion='" + descripcion + '\''
                + '}';
    }
}
