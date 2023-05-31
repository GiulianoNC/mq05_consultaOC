package com.quantum.parseo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CuerpoCerrado {
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

    public CuerpoCerrado(String username, String password, String responsable, String estadoCerrado) {
        this.username = username;
        this.password = password;
        this.responsable = responsable;
        this.estadoCerrado = estadoCerrado;
    }

    //constructor solo para tipo de orden
    public CuerpoCerrado(String username, String password, String responsable, String estadoCerrado, String ordenIni, String ordenFin) {
        this.username = username;
        this.password = password;
        this.responsable = responsable;
        this.estadoCerrado = estadoCerrado;
        this.ordenIni = ordenIni;
        this.ordenFin = ordenFin;
    }

    //constructor solo para tipo de orden y fase
    public CuerpoCerrado(String username, String password, String responsable, String estadoCerrado, String ordenIni, String ordenFin, String faseIni, String faseFin) {
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
    public CuerpoCerrado(String username, String password, String responsable, String estadoCerrado, String ordenIni, String ordenFin, String faseIni, String faseFin, String mantIni, String mantFin) {
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
    public CuerpoCerrado(String username, String password, String responsable, String estadoCerrado,
                         String ordenIni, String ordenFin, String faseIni, String faseFin, String mantIni, String mantFin, String asingnadoIni, String asingnadoFin, String fechaSolicIni) {
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
        this.fechaSolicIni = fechaSolicIni;
    }
    //constructor solo para tipo de orden fase mantenimiento  asignado y fecha solicitada inicial fecha programada inicial


    public CuerpoCerrado(String username, String password, String responsable, String estadoCerrado,
                         String ordenIni, String ordenFin, String faseIni, String faseFin, String mantIni, String mantFin,
                         String asingnadoIni, String asingnadoFin, String fechaSolicIni, String fechaProgIni) {
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
        this.fechaSolicIni = fechaSolicIni;
        this.fechaProgIni = fechaProgIni;
    }

    //constructor solo para tipo de orden fase mantenimiento y asignado
    public CuerpoCerrado(String username, String password, String responsable, String estadoCerrado, String ordenIni, String ordenFin, String faseIni, String faseFin, String mantIni, String mantFin, String asingnadoIni, String asingnadoFin) {
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

    @SerializedName("MQ0501C_DATAREQ")
    @Expose
    private Mq0501cDatareq mq0501cDatareq;
    @SerializedName("jde__status")
    @Expose
    private String jdeStatus;

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