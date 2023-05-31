package com.quantum.parseo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Cuerpo6 {

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



    @SerializedName("Fecha_Solic_Fin")
    @Expose
    private String fechaFin;

    public Cuerpo6(String username, String password, String responsable, String estadoCerrado, String fechaFin) {
        this.username = username;
        this.password = password;
        this.responsable = responsable;
        this.estadoCerrado = estadoCerrado;
        this.fechaFin = fechaFin;
    }

}
