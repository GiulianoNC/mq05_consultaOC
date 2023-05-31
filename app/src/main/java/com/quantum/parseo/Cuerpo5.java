package com.quantum.parseo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Cuerpo5 {


    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("Fecha_Solic_Ini")
    @Expose
    private String fechaIni;
    @SerializedName("Responsable")
    @Expose
    private String responsable;
    @SerializedName("Estado_Cerrado")
    @Expose
    private String estadoCerrado;

    public Cuerpo5(String username, String password, String fechaIni, String responsable, String estadoCerrado) {
        this.username = username;
        this.password = password;
        this.fechaIni = fechaIni;
        this.responsable = responsable;
        this.estadoCerrado = estadoCerrado;
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

}
