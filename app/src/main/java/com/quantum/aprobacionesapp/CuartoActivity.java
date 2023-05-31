package com.quantum.aprobacionesapp;



import static com.quantum.InicioActivity.contraseñaGlobal;
import static com.quantum.InicioActivity.usuarioGlobal;
import static com.quantum.aprobacionesapp.configuracion.direc;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.quantum.conectividad.Conexion;
import com.quantum.parseo.Cuerpo7;
import com.quantum.parseo.Mq0502aDatareq;
import com.quantum.parseo.Rowset2;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CuartoActivity extends AppCompatActivity {
    TextView prueba,descFalla1,disponibilidad1, info,qtm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cuarto);

        prueba = findViewById(R.id.prueba);
        info = findViewById(R.id.info);

        descFalla1= findViewById(R.id.descFalla);
        disponibilidad1= findViewById(R.id.disponibilidad);
        disponibilidad();

        qtm = findViewById(R.id.QTM4);
        qtm.setText("   QTM - CONSULTA" + "\n" + "ORDEN DE TRABAJO" );

        //statusBar
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        getWindow().setStatusBarColor(Color.rgb(102,45,145));  //Define color
    }

    public void disponibilidad(){



        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(direc)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Conexion conexion = retrofit.create(Conexion.class);

        String numero = getIntent().getStringExtra("dato1");


        String tipo = getIntent().getStringExtra("dato2");
        if(tipo.equals("")){
            tipo= "WM";
        }

        String descripcion = getIntent().getStringExtra("dato");


        descFalla1.setText(tipo + " - "+ numero +" \n"
                           + descripcion );

        Cuerpo7 login3 = new Cuerpo7(usuarioGlobal, contraseñaGlobal, tipo, numero);
        Call<Cuerpo7> call2 = conexion.getFinal(login3);
        call2.enqueue(new Callback<Cuerpo7>() {
            @Override
            public void onResponse(Call<Cuerpo7> call, Response<Cuerpo7> response) {
                int statusCode = response.code();

                Mq0502aDatareq contactList =  response.body().getMq0502aDatareq();

                if(statusCode == 200) {


                    for(int e = 0; e<contactList.getRowset().size(); e++) {

                        ArrayList<Rowset2> rowset1 = (ArrayList<Rowset2>) contactList.getRowset();
                        String item = rowset1.get(e).getCodigoITEM();
                        String descripcion = rowset1.get(e).getDescripcion1();
                        String estim = rowset1.get(e).getCantidadEstimada();
                        String real = rowset1.get(e).getCantidadReal();
                        String disp = rowset1.get(e).getExistencias();
                        String um = rowset1.get(e).getUm();


                        String content = ( item + "    " + descripcion + "\n"+
                                "Cantidad Estimada: " +estim + " " + um + "\n"+
                                  "Cantidad Real: " + real + " \n\n" );

                        String content1 = (disp + "\n\n\n\n\n");

                        disponibilidad1.append(content1);

                        info.append(content);





                    }
                }
                if(statusCode == 500) {

                    prueba.setText("No hay datos");


                }

                }

            @Override
            public void onFailure(Call<Cuerpo7> call, Throwable t) {
                Toast.makeText(CuartoActivity.this, "error", Toast.LENGTH_LONG).show();
                prueba.append("ERROR");

            }
        });
    }

    public void Flecha (View view){


        Intent e = new Intent(CuartoActivity.this,SegundoActivity.class);


        startActivity(e);

    }

    public void Salir2(View v){
        new AlertDialog.Builder(this)
                //.setIcon(R.drawable.alacran)
                .setTitle("¿Realmente desea cerrar la aplicación?")
                .setCancelable(false)
                .setNegativeButton(android.R.string.cancel, null)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {// un listener que al pulsar, cierre la aplicacion
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //android.os.Process.killProcess(android.os.Process.myPid()); //Su funcion es algo similar a lo que se llama cuando se presiona el botón "Forzar Detención" o "Administrar aplicaciones", lo cuál mata la aplicación
                        finishAffinity();;
                    }
                }).show();
    }


}