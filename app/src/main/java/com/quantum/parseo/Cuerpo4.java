package com.quantum.parseo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Cuerpo4 {


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

    //ASIGNADO


    public Cuerpo4(String username, String password, String responsable, String estadoCerrado, String asingnadoIni, String asingnadoFin) {
        this.username = username;
        this.password = password;
        this.responsable = responsable;
        this.estadoCerrado = estadoCerrado;
        this.asingnadoIni = asingnadoIni;
        this.asingnadoFin = asingnadoFin;
    }

    public Cuerpo4(String username, String password, String ordenTipo, String ordenNumero) {
        this.username = username;
        this.password = password;
        this.ordenTipo = ordenTipo;
        this.ordenNumero = ordenNumero;
    }




    public Cuerpo4(String username, String password, String responsable, String estadoCerrado, String fechaIni) {
        this.username = username;
        this.password = password;
        this.responsable = responsable;
        this.estadoCerrado = estadoCerrado;
        this.fechaIni = fechaIni;
    }


    @SerializedName("MQ0502A_DATAREQ")
    @Expose
    private Mq0502aDatareq mq0502aDatareq;

    @SerializedName("jde__status")
    @Expose
    private String jdeStatus;

    public Mq0502aDatareq getMq0502aDatareq() {
        return mq0502aDatareq;
    }

    public void setMq0502aDatareq(Mq0502aDatareq mq0502aDatareq) {
        this.mq0502aDatareq = mq0502aDatareq;
    }

    public String getJdeStatus() {
        return jdeStatus;
    }

    public void setJdeStatus(String jdeStatus) {
        this.jdeStatus = jdeStatus;
    }

    @SerializedName("MQ0501X_DATAREQ")
    @Expose
    private Mq0501xDatareq mq0501xDatareq;


    public Mq0501xDatareq getMq0501xDatareq() {
        return mq0501xDatareq;
    }

    public void setMq0501xDatareq(Mq0501xDatareq mq0501xDatareq) {
        this.mq0501xDatareq = mq0501xDatareq;
    }


}



