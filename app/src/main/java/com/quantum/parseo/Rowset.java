package com.quantum.parseo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Rowset {
    @SerializedName("Desc_Equipo")
    @Expose
    private String descEquipo;
    @SerializedName("Orden_Numero")
    @Expose
    private String ordenNumero;
    @SerializedName("Descripcion")
    @Expose
    private String descripcion;
    @SerializedName("Estado")
    @Expose
    private String estado;
    @SerializedName("OrdenTipo")
    @Expose
    private String ordenTipo;
    @SerializedName("Fecha_Programacion")
    @Expose
    private String fechaProgramacion;
    @SerializedName("Fecha_Solicitud")
    @Expose
    private String fechaSolicitud;
    @SerializedName("Caso")
    @Expose
    private String caso;
    @SerializedName("ID_Equipo")
    @Expose
    private String iDEquipo;

    public String getDescEquipo() {
        return descEquipo;
    }

    public void setDescEquipo(String descEquipo) {
        this.descEquipo = descEquipo;
    }

    public String getOrdenNumero() {
        return ordenNumero;
    }

    public void setOrdenNumero(String ordenNumero) {
        this.ordenNumero = ordenNumero;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getOrdenTipo() {
        return ordenTipo;
    }

    public void setOrdenTipo(String ordenTipo) {
        this.ordenTipo = ordenTipo;
    }

    public String getFechaProgramacion() {
        return fechaProgramacion;
    }

    public void setFechaProgramacion(String fechaProgramacion) {
        this.fechaProgramacion = fechaProgramacion;
    }

    public String getFechaSolicitud() {
        return fechaSolicitud;
    }

    public void setFechaSolicitud(String fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }

    public String getCaso() {
        return caso;
    }

    public void setCaso(String caso) {
        this.caso = caso;
    }

    public String getIDEquipo() {
        return iDEquipo;
    }

    public void setIDEquipo(String iDEquipo) {
        this.iDEquipo = iDEquipo;
    }

}