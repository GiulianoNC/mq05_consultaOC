package com.quantum.aprobacionesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.quantum.InicioActivity;

public class configuracion extends AppCompatActivity {
    private TextView  direccion, solicitante,estAp,qtm;

    public static String direc = null;
    public static String ResponsableGlobal = null;
    public static String estadoCerradoGlobal = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracion);


        direccion= findViewById(R.id.direccion);
        solicitante= findViewById(R.id.solicitanteRespuesta);
        estAp= findViewById(R.id.estadoAprobadoRespuesta);



        SharedPreferences preferences = getSharedPreferences("dato", Context.MODE_PRIVATE);
        direccion.setText(preferences.getString("direcciones",""));
        solicitante.setText(preferences.getString("solicitante",""));
        estAp.setText(preferences.getString("estAp",""));

        direc = direccion.getText().toString();
        ResponsableGlobal=solicitante.getText().toString();
        estadoCerradoGlobal=estAp.getText().toString();

        //statusBar
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        getWindow().setStatusBarColor(Color.rgb(102,45,145));  //Define color

        qtm = findViewById(R.id.QTM);
        qtm.setText("   QTM - CONSULTA" + "\n" + "ÓRDEN DE TRABAJO" );

    }


    public void guardar (View view){
        SharedPreferences preferecias =  getSharedPreferences("dato",Context.MODE_PRIVATE);
        SharedPreferences.Editor Obj_editor = preferecias.edit();

        Obj_editor.putString("direcciones", direccion.getText().toString());
        Obj_editor.putString("solicitante",ResponsableGlobal=solicitante.getText().toString());
        Obj_editor.putString("estAp",estadoCerradoGlobal=estAp.getText().toString());




        Obj_editor.commit();


        Intent siguiente = new Intent(configuracion.this, InicioActivity.class);


        siguiente.putExtra("direcciones", direccion.getText().toString());

        startActivity(siguiente);


    }



}