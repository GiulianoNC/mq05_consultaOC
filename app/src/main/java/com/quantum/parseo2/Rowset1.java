package com.quantum.parseo2;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Rowset1 {
    @SerializedName("Códigos")
    @Expose
    private String cDigos;
    @SerializedName("Descripcion")
    @Expose
    private String descripcion;

    public String getCDigos() {
        return cDigos;
    }

    public void setCDigos(String cDigos) {
        this.cDigos = cDigos;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

}
