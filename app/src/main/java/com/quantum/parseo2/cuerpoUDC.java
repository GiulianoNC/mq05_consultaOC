package com.quantum.parseo2;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class cuerpoUDC {

    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("UDC")
    @Expose
    private String udc;


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


    public cuerpoUDC(String username, String password, String udc) {
        this.username = username;
        this.password = password;
        this.udc = udc;
    }

    @SerializedName("MQ0503A_FORMREQ_1")
    @Expose
    private Mq0503aFormreq1 mq0503aFormreq1;
    @SerializedName("jde__status")
    @Expose
    private String jdeStatus;

    public Mq0503aFormreq1 getMq0503aFormreq1() {
        return mq0503aFormreq1;
    }

    public void setMq0503aFormreq1(Mq0503aFormreq1 mq0503aFormreq1) {
        this.mq0503aFormreq1 = mq0503aFormreq1;
    }

    public String getJdeStatus() {
        return jdeStatus;
    }

    public void setJdeStatus(String jdeStatus) {
        this.jdeStatus = jdeStatus;
    }

}

