package com.quantum.parseo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Cuerpo7 {


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

    public Cuerpo7(String username, String password, String ordenTipo, String ordenNumero) {
        this.username = username;
        this.password = password;
        this.ordenTipo = ordenTipo;
        this.ordenNumero = ordenNumero;
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
}
