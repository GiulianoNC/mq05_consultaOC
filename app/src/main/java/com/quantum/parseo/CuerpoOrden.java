package com.quantum.parseo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CuerpoOrden {
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("password")
    @Expose
    private String password;

    @SerializedName("Orden_Tipo")
    @Expose
    private String ordenTipo;
    @SerializedName("Orden_Numero")
    @Expose
    private String ordenNumero;

    @SerializedName("Responsable")
    @Expose
    private String responsable;
    @SerializedName("Estado_Cerrado")
    @Expose
    private String estadoCerrado;


    @SerializedName("Orden_Ini")
    @Expose
    private String ordenIni;
    @SerializedName("Orden_Fin")
    @Expose
    private String ordenFin;
    @SerializedName(" Fase_Ini")
    @Expose
    private String faseIni;
    @SerializedName("Fase_Fin")
    @Expose
    private String faseFin;
    @SerializedName("Mant_Ini")
    @Expose
    private String mantIni;
    @SerializedName("Mant_Fin")
    @Expose
    private String mantFin;
    @SerializedName("Asignado_Ini")
    @Expose
    private String asingnadoIni;
    @SerializedName("Asignado_Fin")
    @Expose
    private String asingnadoFin;
    @SerializedName("Fecha_Solic_Ini")
    @Expose
    private String fechaIni;
    @SerializedName("Fecha_Prog_Ini")
    @Expose
    private String fechaProgIni;

    //mantenimiento

    public CuerpoOrden(String username, String password, String responsable, String estadoCerrado, String mantIni, String mantFin) {
        this.username = username;
        this.password = password;
        this.responsable = responsable;
        this.estadoCerrado = estadoCerrado;
        this.mantIni = mantIni;
        this.mantFin = mantFin;
    }

    //mantenimiento y asignado


    public CuerpoOrden(String username, String password, String responsable, String estadoCerrado, String mantIni, String mantFin, String asingnadoIni, String asingnadoFin) {
        this.username = username;
        this.password = password;
        this.responsable = responsable;
        this.estadoCerrado = estadoCerrado;
        this.mantIni = mantIni;
        this.mantFin = mantFin;
        this.asingnadoIni = asingnadoIni;
        this.asingnadoFin = asingnadoFin;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    public String getEstadoCerrado() {
        return estadoCerrado;
    }

    public void setEstadoCerrado(String estadoCerrado) {
        this.estadoCerrado = estadoCerrado;
    }

    public String getOrdenIni() {
        return ordenIni;
    }

    public void setOrdenIni(String ordenIni) {
        this.ordenIni = ordenIni;
    }

    public String getOrdenFin() {
        return ordenFin;
    }

    public void setOrdenFin(String ordenFin) {
        this.ordenFin = ordenFin;
    }

    @SerializedName("MQ0501X_DATAREQ")
    @Expose
    private Mq0501xDatareq mq0501xDatareq;
    @SerializedName("jde__status")
    @Expose
    private String jdeStatus;

    @SerializedName("MQ0501C_DATAREQ")
    @Expose
    private Mq0501cDatareq mq0501cDatareq;

    public Mq0501cDatareq getMq0501cDatareq() {
        return mq0501cDatareq;
    }

    public void setMq0501cDatareq(Mq0501cDatareq mq0501cDatareq) {
        this.mq0501cDatareq = mq0501cDatareq;
    }

    public String getJdeStatus() {
        return jdeStatus;
    }

    public void setJdeStatus(String jdeStatus) {
        this.jdeStatus = jdeStatus;
    }
}
