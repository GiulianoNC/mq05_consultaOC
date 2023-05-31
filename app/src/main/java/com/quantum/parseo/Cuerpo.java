package com.quantum.parseo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Cuerpo {

    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("Responsable")
    @Expose
    private String responsable;
    @SerializedName("Estado_Cerrado")
    @Expose
    private String estadoCerrado;


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

    public Cuerpo(String username, String password, String responsable, String estadoCerrado) {
        this.username = username;
        this.password = password;
        this.responsable = responsable;
        this.estadoCerrado = estadoCerrado;
    }

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

    //TIPO DE ORDEN Y ASIGNADO


    public Cuerpo(String username, String password, String responsable, String estadoCerrado, String ordenIni, String ordenFin, String asingnadoIni, String asingnadoFin) {
        this.username = username;
        this.password = password;
        this.responsable = responsable;
        this.estadoCerrado = estadoCerrado;
        this.ordenIni = ordenIni;
        this.ordenFin = ordenFin;
        this.asingnadoIni = asingnadoIni;
        this.asingnadoFin = asingnadoFin;
    }

    @SerializedName("MQ0501X_DATAREQ")
    @Expose
    private Mq0501xDatareq mq0501xDatareq;
    @SerializedName("jde__status")
    @Expose
    private String jdeStatus;

    public Mq0501xDatareq getMq0501xDatareq() {
        return mq0501xDatareq;
    }

    public void setMq0501xDatareq(Mq0501xDatareq mq0501xDatareq) {
        this.mq0501xDatareq = mq0501xDatareq;
    }

    public String getJdeStatus() {
        return jdeStatus;
    }

    public void setJdeStatus(String jdeStatus) {
        this.jdeStatus = jdeStatus;
    }



}