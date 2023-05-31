package com.quantum.parseo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Cuerpo1 {
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



    //constructor solo para tipo de orden
    public Cuerpo1(String username, String password, String responsable, String estadoCerrado, String ordenIni, String ordenFin) {
        this.username = username;
        this.password = password;
        this.responsable = responsable;
        this.estadoCerrado = estadoCerrado;
        this.ordenIni = ordenIni;
        this.ordenFin = ordenFin;
    }

    //constructor solo para tipo de orden y fase
    public Cuerpo1(String username, String password, String responsable, String estadoCerrado, String ordenIni, String ordenFin, String faseIni, String faseFin) {
        this.username = username;
        this.password = password;
        this.responsable = responsable;
        this.estadoCerrado = estadoCerrado;
        this.ordenIni = ordenIni;
        this.ordenFin = ordenFin;
        this.faseIni = faseIni;
        this.faseFin = faseFin;
    }
    //constructor solo para tipo de orden fase y mantenimiento
    public Cuerpo1(String username, String password, String responsable, String estadoCerrado, String ordenIni, String ordenFin, String faseIni, String faseFin, String mantIni, String mantFin) {
        this.username = username;
        this.password = password;
        this.responsable = responsable;
        this.estadoCerrado = estadoCerrado;
        this.ordenIni = ordenIni;
        this.ordenFin = ordenFin;
        this.faseIni = faseIni;
        this.faseFin = faseFin;
        this.mantIni = mantIni;
        this.mantFin = mantFin;
    }
    //constructor solo para tipo de orden fase mantenimiento  asignado y fecha solicitada inicial
    public Cuerpo1(String username, String password, String responsable, String estadoCerrado, String ordenIni, String ordenFin, String faseIni, String faseFin, String mantIni, String mantFin, String asingnadoIni, String asingnadoFin, String fechaIni) {
        this.username = username;
        this.password = password;
        this.responsable = responsable;
        this.estadoCerrado = estadoCerrado;
        this.ordenIni = ordenIni;
        this.ordenFin = ordenFin;
        this.faseIni = faseIni;
        this.faseFin = faseFin;
        this.mantIni = mantIni;
        this.mantFin = mantFin;
        this.asingnadoIni = asingnadoIni;
        this.asingnadoFin = asingnadoFin;
        this.fechaIni = fechaIni;
    }
    //constructor solo para tipo de orden fase mantenimiento  asignado y fecha solicitada inicial fecha programada inicial


    public Cuerpo1(String username, String password, String responsable, String estadoCerrado, String ordenIni, String ordenFin, String faseIni, String faseFin, String mantIni, String mantFin, String asingnadoIni, String asingnadoFin, String fechaIni, String fechaProgIni) {
        this.username = username;
        this.password = password;
        this.responsable = responsable;
        this.estadoCerrado = estadoCerrado;
        this.ordenIni = ordenIni;
        this.ordenFin = ordenFin;
        this.faseIni = faseIni;
        this.faseFin = faseFin;
        this.mantIni = mantIni;
        this.mantFin = mantFin;
        this.asingnadoIni = asingnadoIni;
        this.asingnadoFin = asingnadoFin;
        this.fechaIni = fechaIni;
        this.fechaProgIni = fechaProgIni;
    }

    //constructor solo para tipo de orden fase mantenimiento y asignado
    public Cuerpo1(String username, String password, String responsable, String estadoCerrado, String ordenIni, String ordenFin, String faseIni, String faseFin, String mantIni, String mantFin, String asingnadoIni, String asingnadoFin) {
        this.username = username;
        this.password = password;
        this.responsable = responsable;
        this.estadoCerrado = estadoCerrado;
        this.ordenIni = ordenIni;
        this.ordenFin = ordenFin;
        this.faseIni = faseIni;
        this.faseFin = faseFin;
        this.mantIni = mantIni;
        this.mantFin = mantFin;
        this.asingnadoIni = asingnadoIni;
        this.asingnadoFin = asingnadoFin;



    }



    @Expose
    private String jdeStatus;

    @SerializedName("MQ0501X_DATAREQ")
    @Expose
    private Mq0501xDatareq mq0501xDatareq;
    @SerializedName("jde__status")

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

    //PARA PY
    @SerializedName("MQ0501C_DATAREQ")
    @Expose
    private Mq0501cDatareq mq0501cDatareq;

    public Mq0501cDatareq getMq0501cDatareq() {
        return mq0501cDatareq;
    }

    public void setMq0501cDatareq(Mq0501cDatareq mq0501cDatareq) {
        this.mq0501cDatareq = mq0501cDatareq;
    }
}
