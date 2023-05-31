package com.quantum.parseo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CuerpoCompleto {

    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("Estado_Cerrado")
    @Expose
    private String estadoCerrado;
    @SerializedName("Responsable")
    @Expose
    private String responsable;
    @SerializedName("Orden_Ini")
    @Expose
    private String ordenIni;
    @SerializedName("Orden_Fin")
    @Expose
    private String ordenFin;
    @SerializedName("Fase_Ini")
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
    @SerializedName("Fecha_Solic_Ini")
    @Expose
    private String fechaSolicIni;

    @SerializedName("Fecha_Prog_Ini")
    @Expose
    private String fechaProgIni;

    @SerializedName("Asignado_Ini")
    @Expose
    private String asingnadoIni;
    @SerializedName("Asignado_Fin")
    @Expose
    private String asingnadoFin;
;

    //Si el cliente llega a seleccionar todos los filtros iniciales


    public CuerpoCompleto(String username, String password, String estadoCerrado, String responsable, String ordenIni, String ordenFin, String faseIni, String faseFin, String mantIni, String mantFin,
                          String fechaSolicIni, String fechaProgIni, String asingnadoIni, String asingnadoFin) {
        this.username = username;
        this.password = password;
        this.estadoCerrado = estadoCerrado;
        this.responsable = responsable;
        this.ordenIni = ordenIni;
        this.ordenFin = ordenFin;
        this.faseIni = faseIni;
        this.faseFin = faseFin;
        this.mantIni = mantIni;
        this.mantFin = mantFin;
        this.fechaSolicIni = fechaSolicIni;
        this.fechaProgIni = fechaProgIni;
        this.asingnadoIni = asingnadoIni;
        this.asingnadoFin = asingnadoFin;
    }

    //FASE


    public CuerpoCompleto(String username, String password, String responsable, String estadoCerrado, String faseIni, String faseFin) {
        this.username = username;
        this.password = password;
        this.responsable = responsable;
        this.estadoCerrado = estadoCerrado;
        this.faseIni = faseIni;
        this.faseFin = faseFin;
    }
    //FASE Y MANTENIMIENTO


    public CuerpoCompleto(String username, String password, String responsable, String estadoCerrado, String faseIni, String faseFin, String mantIni, String mantFin) {
        this.username = username;
        this.password = password;
        this.responsable = responsable;
        this.estadoCerrado = estadoCerrado;
        this.faseIni = faseIni;
        this.faseFin = faseFin;
        this.mantIni = mantIni;
        this.mantFin = mantFin;
    }
    //FASE Y MANTENIMIENTO Y FECHA SOLICITADA INI


    public CuerpoCompleto(String username, String password, String responsable, String estadoCerrado, String faseIni, String faseFin, String mantIni, String mantFin, String fechaSolicIni) {
        this.username = username;
        this.password = password;
        this.responsable = responsable;
        this.estadoCerrado = estadoCerrado;
        this.faseIni = faseIni;
        this.faseFin = faseFin;
        this.mantIni = mantIni;
        this.mantFin = mantFin;
        this.fechaSolicIni = fechaSolicIni;
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

