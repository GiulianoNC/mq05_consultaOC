package com.quantum.conectividad;

import com.quantum.parseo.Cuerpo;
import com.quantum.parseo.Cuerpo1;
import com.quantum.parseo.Cuerpo2;
import com.quantum.parseo.Cuerpo3;
import com.quantum.parseo.Cuerpo4;
import com.quantum.parseo.Cuerpo5;
import com.quantum.parseo.Cuerpo7;
import com.quantum.parseo.CuerpoAsignado;
import com.quantum.parseo.CuerpoCerrado;
import com.quantum.parseo.CuerpoCompleto;
import com.quantum.parseo.CuerpoFiltros;
import com.quantum.parseo.CuerpoOrden;
import com.quantum.parseo2.cuerpoUDC;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface Conexion {

    //inicio
    @POST("jderest/v3/orchestrator/MQ0503A_ORCH")
    Call<Cuerpo> getData(@Body Cuerpo users );

    //udc
    @POST("jderest/v3/orchestrator/MQ0503A_ORCH")
    Call<cuerpoUDC> getUDC(@Body cuerpoUDC users );

    //desplegables
    @POST("jderest/v3/orchestrator/MQ0501A_ORCH")
    Call<Cuerpo2> getInfo(@Body Cuerpo2 users );

    //vigentes
    @POST("jderest/v3/orchestrator/MQ0501A_ORCH")
    Call<Cuerpo3> getVigentes(@Body Cuerpo3 users );

    //ORDEN
    @POST("jderest/v3/orchestrator/MQ0501A_ORCH")
    Call<Cuerpo1> getorden(@Body Cuerpo1 users );

    //ORDEN2
    @POST("jderest/v3/orchestrator/MQ0501A_ORCH")
    Call<Cuerpo> getorden2(@Body Cuerpo users );

    //FASE
    @POST("jderest/v3/orchestrator/MQ0501A_ORCH")
    Call<Cuerpo2> getFase(@Body Cuerpo2 users );

    //FASE2
    @POST("jderest/v3/orchestrator/MQ0501A_ORCH")
    Call<Cuerpo3> getFase2(@Body Cuerpo3 users );

    //MANTENIMIENTO
    @POST("jderest/v3/orchestrator/MQ0501A_ORCH")
    Call<Cuerpo3> getMantenimiento(@Body Cuerpo3 users );

    //ASIGNADO
    @POST("jderest/v3/orchestrator/MQ0501A_ORCH")
    Call<Cuerpo4> getAsignado(@Body Cuerpo4 users );

    //vigentes  filtros COMPLETOS
    @POST("jderest/v3/orchestrator/MQ0501A_ORCH")
    Call<CuerpoCompleto> getCompletoIni(@Body CuerpoCompleto users );

    //vigentes  filtros principales tipo de orden, fase y mantenimiento
    @POST("jderest/v3/orchestrator/MQ0501A_ORCH")
    Call<CuerpoFiltros> getPrincipales(@Body CuerpoFiltros users );

    //vigentes  filtros principales tipo de orden, fase y mantenimiento  y asignado
    @POST("jderest/v3/orchestrator/MQ0501A_ORCH")
    Call<CuerpoAsignado> getAsignados(@Body CuerpoAsignado users );

    //vigentes  filtros principales tipo de orden, fase y mantenimiento  y asignado
    @POST("jderest/v3/orchestrator/MQ0501A_ORCH")
    Call<Cuerpo5> getFechas(@Body Cuerpo5 users );

    //disponibilidad de piezas
    @POST("jderest/v3/orchestrator/MQ0501A_ORCH")
    Call<Cuerpo4> getDisponibildiad(@Body Cuerpo4 users );


    //disponibilidad de piezas
    @POST("jderest/v3/orchestrator/MQ0502A_ORCH")
    Call<Cuerpo7> getFinal(@Body Cuerpo7 users );


    //cerradas
    @POST("jderest/v3/orchestrator/MQ0501C_ORCH")
    Call<CuerpoCerrado> getCerradas(@Body CuerpoCerrado users );

    //cerradas  filtros COMPLETOS
    @POST("jderest/v3/orchestrator/MQ0501C_ORCH")
    Call<CuerpoCompleto> getCompletoIniCerradas(@Body CuerpoCompleto users );

    //cerradas  filtros principales tipo de orden, fase y mantenimiento
    @POST("jderest/v3/orchestrator/MQ0501C_ORCH")
    Call<CuerpoFiltros> getPrincipalesCerradas(@Body CuerpoFiltros users );

    //cerradas  filtros principales tipo de orden, fase y mantenimiento  y asignado
    @POST("jderest/v3/orchestrator/MQ0501C_ORCH")
    Call<CuerpoAsignado> getAsignadosCerradas(@Body CuerpoAsignado users );

    //cerradas
    @POST("jderest/v3/orchestrator/MQ0501C_ORCH")
    Call<CuerpoOrden> getMantenimiento(@Body CuerpoOrden users );


}
