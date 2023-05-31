package com.quantum.parseo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Rowset2 {
    @SerializedName("Compromiso_Flexible")
    @Expose
    private String compromisoFlexible;
    @SerializedName("Comprimiso_Firme")
    @Expose
    private String comprimisoFirme;
    @SerializedName("Existencias")
    @Expose
    private String existencias;
    @SerializedName("Codigo_ITEM")
    @Expose
    private String codigoITEM;
    @SerializedName("Deposito")
    @Expose
    private String deposito;
    @SerializedName("Descripcion1")
    @Expose
    private String descripcion1;
    @SerializedName("Descripcion2")
    @Expose
    private String descripcion2;
    @SerializedName("Cantidad_Estimada")
    @Expose
    private String cantidadEstimada;
    @SerializedName("Cantidad_Real")
    @Expose
    private String cantidadReal;

    @SerializedName("UM")
    @Expose
    private String um;


    public String getUm() {
        return um;
    }

    public void setUm(String um) {
        this.um = um;
    }

    public String getCompromisoFlexible() {
        return compromisoFlexible;
    }

    public void setCompromisoFlexible(String compromisoFlexible) {
        this.compromisoFlexible = compromisoFlexible;
    }

    public String getComprimisoFirme() {
        return comprimisoFirme;
    }

    public void setComprimisoFirme(String comprimisoFirme) {
        this.comprimisoFirme = comprimisoFirme;
    }

    public String getExistencias() {
        return existencias;
    }

    public void setExistencias(String existencias) {
        this.existencias = existencias;
    }

    public String getCodigoITEM() {
        return codigoITEM;
    }

    public void setCodigoITEM(String codigoITEM) {
        this.codigoITEM = codigoITEM;
    }

    public String getDeposito() {
        return deposito;
    }

    public void setDeposito(String deposito) {
        this.deposito = deposito;
    }

    public String getDescripcion1() {
        return descripcion1;
    }

    public void setDescripcion1(String descripcion1) {
        this.descripcion1 = descripcion1;
    }

    public String getDescripcion2() {
        return descripcion2;
    }

    public void setDescripcion2(String descripcion2) {
        this.descripcion2 = descripcion2;
    }

    public String getCantidadEstimada() {
        return cantidadEstimada;
    }

    public void setCantidadEstimada(String cantidadEstimada) {
        this.cantidadEstimada = cantidadEstimada;
    }

    public String getCantidadReal() {
        return cantidadReal;
    }

    public void setCantidadReal(String cantidadReal) {
        this.cantidadReal = cantidadReal;
    }

}
