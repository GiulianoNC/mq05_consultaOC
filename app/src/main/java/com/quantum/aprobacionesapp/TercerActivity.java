package com.quantum.aprobacionesapp;


import static com.quantum.InicioActivity.contraseñaGlobal;
import static com.quantum.InicioActivity.usuarioGlobal;
import static com.quantum.aprobacionesapp.SegundoActivity.VCGlobal;;
import static com.quantum.aprobacionesapp.configuracion.ResponsableGlobal;
import static com.quantum.aprobacionesapp.configuracion.direc;
import static com.quantum.aprobacionesapp.configuracion.estadoCerradoGlobal;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.quantum.InicioActivity;
import com.quantum.adaptador.AdapterDatos;
import com.quantum.adaptador.AdapterDatos1;
import com.quantum.adaptador.AdapterDatos2;
import com.quantum.adaptador.AdapterDatos3;
import com.quantum.conectividad.Conexion;
import com.quantum.parseo.Cuerpo;
import com.quantum.parseo.Cuerpo1;
import com.quantum.parseo.Cuerpo2;
import com.quantum.parseo.Cuerpo3;
import com.quantum.parseo.Cuerpo4;
import com.quantum.parseo.Cuerpo5;
import com.quantum.parseo.CuerpoAsignado;
import com.quantum.parseo.CuerpoCerrado;
import com.quantum.parseo.CuerpoCompleto;
import com.quantum.parseo.CuerpoFiltros;
import com.quantum.parseo.CuerpoOrden;
import com.quantum.parseo.Mq0501cDatareq;
import com.quantum.parseo.Mq0501xDatareq;
import com.quantum.parseo.Rowset;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TercerActivity extends AppCompatActivity {


    ArrayList<String> listDatos;
    ArrayList<String> listDatos1;
    ArrayList<String> listDatos2;

    RecyclerView recycler,recycler1, recycler2;
    TextView prueba, numero,tipo1, numero1, descripcion1, asignado1, asignado2,qtm, filtros;

    public static String tipoGlobal = null;
    public static String numeroGlobal = null;
    public static String descripcionGlobal = null;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tercer);

        recycler= (RecyclerView) findViewById(R.id.recyclerId4);
        recycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

       recycler1= (RecyclerView) findViewById(R.id.recycler5);
        recycler1.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        recycler2= (RecyclerView) findViewById(R.id.recyclerId6);
        recycler2.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        prueba= (TextView) findViewById(R.id.pruebaCerradas);

        numero = (TextView) findViewById(R.id.numero);
        numero1 = (TextView) findViewById(R.id.Numero1);
        tipo1 = (TextView) findViewById(R.id.Tipo1);
        descripcion1=(TextView) findViewById(R.id.descripcion1);

        if(VCGlobal.equals("V")){
        Vigentes();}else{
        Cerradas();}

        qtm = findViewById(R.id.QTM3);
        qtm.setText("   QTM - CONSULTA" + "\n" + "ORDEN DE TRABAJO" );

        //statusBar
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        getWindow().setStatusBarColor(Color.rgb(102,45,145));  //Define color
    }



    public void Vigentes(){



        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(direc)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Conexion conexion = retrofit.create(Conexion.class);


        //FILTRO TIPO DE ORDEN
        String dato = getIntent().getStringExtra("dato");
        //FILTRO TIPO DE FASE
        String dato1 = getIntent().getStringExtra("dato1");
        //FILTRO TIPO DE MANTENIMIENTO
        String dato2 = getIntent().getStringExtra("dato2");

        //FILTRO FECHA SOLICITADA
        String dato3 = getIntent().getStringExtra("dato3");
        //FILTRO FECHA SOLICITADA2
        String dato4 = getIntent().getStringExtra("dato4");

        //FILTRO FECHA PROGRAMADA
        String dato5 = getIntent().getStringExtra("dato5");
        //FILTRO FECHA PROGRAMADA2
        String dato6 = getIntent().getStringExtra("dato6");

        //FILTRO ASIGNADO
        String dato7 = getIntent().getStringExtra("dato7");



        //SI NO SE SELECCIONADA NADA

        if(dato.length()== 0  && dato1.length() == 0 && dato2.length() == 0 && dato3.length() == 0 && dato4.length() == 0
                && dato5.length() == 0 && dato6.length() == 0 && dato7.length() == 0){

            Cuerpo3 login3 = new Cuerpo3(usuarioGlobal, contraseñaGlobal,ResponsableGlobal,estadoCerradoGlobal);
            Call<Cuerpo3> call2 = conexion.getVigentes(login3);
            call2.enqueue(new Callback<Cuerpo3>() {
                @Override
                public void onResponse(Call<Cuerpo3> call2, Response<Cuerpo3> response) {
                    int statusCode = response.code();

                    if (response.isSuccessful()){

                        Mq0501xDatareq contactList =  response.body().getMq0501xDatareq();

                        listDatos =new ArrayList<>();
                        listDatos1 =new ArrayList<>();
                        listDatos2 =new ArrayList<>();




                        for(int e = 0; e<contactList.getRowset().size(); e++) {

                            ArrayList<Rowset> rowset1 = (ArrayList<Rowset>) contactList.getRowset();
                            String caso = rowset1.get(e).getCaso();
                            String descEquipo = rowset1.get(e).getDescEquipo();
                            String ordenNumero = rowset1.get(e).getOrdenNumero();
                            String tipo = rowset1.get(e).getOrdenTipo();
                            String fecha = rowset1.get(e).getFechaSolicitud();
                            String estado= rowset1.get(e).getEstado();
                            String idEquipo= rowset1.get(e).getIDEquipo();
                            String descripcion= rowset1.get(e).getDescripcion();
                            String fechaPro= rowset1.get(e).getFechaProgramacion();


                            listDatos.add( ordenNumero );
                            String content = ( descripcion +  "\n" +
                                    idEquipo + " | "   + descEquipo + "\n"+
                                    "F. Solicitada: " + fecha + "\n"+
                                    "F. Programada: " + fechaPro ) + "\n\n" ;
                            prueba.append(content);
                            AdapterDatos3 adapter = new AdapterDatos3(listDatos);
                            recycler.setAdapter(adapter);

                            listDatos1.add( tipo);
                            AdapterDatos1 adapter2 = new AdapterDatos1(listDatos1);
                            recycler1.setAdapter(adapter2);


                            listDatos2.add(estado);
                            AdapterDatos2 adapter3 = new AdapterDatos2(listDatos2);
                            recycler2.setAdapter(adapter3);

                            adapter.setOnclickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    numero.setText(listDatos.get(recycler.getChildAdapterPosition(view)));


                                    tipoGlobal = tipo;
                                    numeroGlobal  = ordenNumero;


                                    Retrofit retrofit = new Retrofit.Builder()
                                            .baseUrl(direc)
                                            .addConverterFactory(GsonConverterFactory.create())
                                            .build();

                                    Conexion conexion = retrofit.create(Conexion.class);


                                    Call<Cuerpo3> call3 = conexion.getVigentes(login3);
                                    call3.enqueue(new Callback<Cuerpo3>() {
                                        @Override
                                        public void onResponse(Call<Cuerpo3> call, Response<Cuerpo3> response) {
                                            if(response.body() != null) {


                                                for(int e = 0; e<contactList.getRowset().size(); e++) {

                                                    ArrayList<Rowset> rowset1 = (ArrayList<Rowset>) contactList.getRowset();
                                                    String caso = rowset1.get(e).getCaso();
                                                    String descEquipo = rowset1.get(e).getDescEquipo();
                                                    String ordenNumero = rowset1.get(e).getOrdenNumero();
                                                    String tipo = rowset1.get(e).getOrdenTipo();
                                                    String fecha = rowset1.get(e).getFechaSolicitud();
                                                    String estado= rowset1.get(e).getEstado();
                                                    String idEquipo= rowset1.get(e).getIDEquipo();
                                                    String descripcion= rowset1.get(e).getDescripcion();
                                                    String fechaPro= rowset1.get(e).getFechaProgramacion();

                                                    String numeroOrden2 = numero.getText().toString();


                                                    if (rowset1.get(e).getOrdenNumero().equals(numeroOrden2)){


                                                        tipo1.setText(tipo );
                                                        numero1.setText(ordenNumero);
                                                        descripcion1.setText(descripcion);

                                                        tipoGlobal = tipo1.getText().toString();
                                                        numeroGlobal  = numero1.getText().toString();


                                                    }
                                                    if(rowset1.get(e).getOrdenNumero().equals(numeroOrden2)){


                                                        Intent i = new Intent(TercerActivity.this, CuartoActivity.class);
                                                        i.putExtra("dato2", tipo1.getText().toString());
                                                        i.putExtra("dato1", numero1.getText().toString());
                                                        i.putExtra("dato", descripcion1.getText().toString());




                                                        startActivity(i);



                                                    }
                                                }

                                            }
                                        }

                                        @Override
                                        public void onFailure(Call<Cuerpo3> call, Throwable t) {
                                            Toast.makeText(TercerActivity.this, "error ", Toast.LENGTH_LONG).show();

                                        }
                                    });



                                }
                            });


                        }

                    }
                    else{

                        Toast.makeText(TercerActivity.this, "ERROR general " + statusCode , Toast.LENGTH_LONG).show();
                        String dato2 = getIntent().getStringExtra("dato2");

                        prueba.setText(dato2);
                    }
                }

                @Override
                public void onFailure(Call<Cuerpo3> call2, Throwable t) {
                    Toast.makeText(TercerActivity.this, "error", Toast.LENGTH_LONG).show();

                }


            });
        }
        else if(dato.length() != 0 && dato1.length() == 0  && dato2.length() != 0 && dato3.length() == 0
                && dato4.length() == 0 && dato5.length() == 0 && dato6.length() == 0 && dato7.length() == 0){

            //TIPO DE ORDEN
            Cuerpo1 login3 = new Cuerpo1(usuarioGlobal, contraseñaGlobal,ResponsableGlobal,estadoCerradoGlobal,dato, dato);
            Call<Cuerpo1> call2 = conexion.getorden(login3);
            call2.enqueue(new Callback<Cuerpo1>() {
                @Override
                public void onResponse(Call<Cuerpo1> call2, Response<Cuerpo1> response) {
                    int statusCode = response.code();

                    if (response.isSuccessful()){
                        Toast.makeText(TercerActivity.this, "entró", Toast.LENGTH_LONG).show();


                        Mq0501xDatareq contactList =  response.body().getMq0501xDatareq();

                        listDatos =new ArrayList<>();
                        listDatos1 =new ArrayList<>();
                        listDatos2 =new ArrayList<>();




                        for(int e = 0; e<contactList.getRowset().size(); e++) {

                            ArrayList<Rowset> rowset1 = (ArrayList<Rowset>) contactList.getRowset();
                            String caso = rowset1.get(e).getCaso();
                            String descEquipo = rowset1.get(e).getDescEquipo();
                            String ordenNumero = rowset1.get(e).getOrdenNumero();
                            String tipo = rowset1.get(e).getOrdenTipo();
                            String fecha = rowset1.get(e).getFechaSolicitud();
                            String estado= rowset1.get(e).getEstado();
                            String idEquipo= rowset1.get(e).getIDEquipo();
                            String descripcion= rowset1.get(e).getDescripcion();
                            String fechaPro= rowset1.get(e).getFechaProgramacion();


                            listDatos.add( ordenNumero );
                            String content = ( descripcion +  "\n" +
                                    idEquipo + " | "   + descEquipo + "\n"+
                                    "F. Solicitada: " + fecha + "\n"+
                                    "F. Programada: " + fechaPro ) + "\n\n" ;

                            prueba.append(content);

                            AdapterDatos3 adapter = new AdapterDatos3(listDatos);
                            recycler.setAdapter(adapter);

                            listDatos1.add(tipo);

                            AdapterDatos1 adapter2 = new AdapterDatos1(listDatos1);
                            recycler1.setAdapter(adapter2);


                            listDatos2.add(estado);
                            AdapterDatos2 adapter3 = new AdapterDatos2(listDatos2);
                            recycler2.setAdapter(adapter3);

                            adapter.setOnclickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {



                                    numero.setText(listDatos.get(recycler.getChildAdapterPosition(view)));


                                    tipoGlobal = tipo;
                                    numeroGlobal  = ordenNumero;


                                    Retrofit retrofit = new Retrofit.Builder()
                                            .baseUrl(direc)
                                            .addConverterFactory(GsonConverterFactory.create())
                                            .build();

                                    Conexion conexion = retrofit.create(Conexion.class);


                                    Call<Cuerpo1> call3 = conexion.getorden(login3);
                                    call3.enqueue(new Callback<Cuerpo1>() {
                                        @Override
                                        public void onResponse(Call<Cuerpo1> call, Response<Cuerpo1> response) {
                                            if(response.body() != null) {


                                                for(int e = 0; e<contactList.getRowset().size(); e++) {

                                                    ArrayList<Rowset> rowset1 = (ArrayList<Rowset>) contactList.getRowset();
                                                    String caso = rowset1.get(e).getCaso();
                                                    String descEquipo = rowset1.get(e).getDescEquipo();
                                                    String ordenNumero = rowset1.get(e).getOrdenNumero();
                                                    String tipo = rowset1.get(e).getOrdenTipo();
                                                    String fecha = rowset1.get(e).getFechaSolicitud();
                                                    String estado= rowset1.get(e).getEstado();
                                                    String idEquipo= rowset1.get(e).getIDEquipo();
                                                    String descripcion= rowset1.get(e).getDescripcion();
                                                    String fechaPro= rowset1.get(e).getFechaProgramacion();

                                                    String numeroOrden2 = numero.getText().toString();


                                                    if (rowset1.get(e).getOrdenNumero().equals(numeroOrden2)){


                                                        tipo1.setText(tipo );
                                                        numero1.setText(ordenNumero);
                                                        descripcion1.setText(descripcion);

                                                        tipoGlobal = tipo1.getText().toString();
                                                        numeroGlobal  = numero1.getText().toString();
                                                        descripcionGlobal = descripcion1.getText().toString();




                                                    }
                                                    if(rowset1.get(e).getOrdenNumero().equals(numeroOrden2)){


                                                        Intent i = new Intent(TercerActivity.this, CuartoActivity.class);
                                                        i.putExtra("dato2", tipo1.getText().toString());
                                                        i.putExtra("dato1", numero1.getText().toString());
                                                        i.putExtra("dato", descripcion1.getText().toString());




                                                        startActivity(i);



                                                    }
                                                }

                                            }
                                        }

                                        @Override
                                        public void onFailure(Call<Cuerpo1> call, Throwable t) {
                                            Toast.makeText(TercerActivity.this, "error ", Toast.LENGTH_LONG).show();

                                        }
                                    });



                                }
                            });


                        }

                    }
                    else{

                        Toast.makeText(TercerActivity.this, "ERROR orden y fase " + statusCode , Toast.LENGTH_LONG).show();
                        String dato2 = getIntent().getStringExtra("dato2");

                        prueba.setText(dato2);
                    }
                }

                @Override
                public void onFailure(Call<Cuerpo1> call2, Throwable t) {
                    Toast.makeText(TercerActivity.this, "error", Toast.LENGTH_LONG).show();

                }
            });
        }else if(dato.length()!= 0  && dato1.length() != 0 && dato2.length() == 0 && dato3.length() == 0
                && dato4.length() == 0 && dato5.length() == 0 && dato6.length() == 0 && dato7.length() == 0 ){

            //TIPO DE ORDEN Y FASE
            Cuerpo1 login3 = new Cuerpo1(usuarioGlobal, contraseñaGlobal,ResponsableGlobal,estadoCerradoGlobal,dato, dato,dato1,dato1);
            Call<Cuerpo1> call2 = conexion.getorden(login3);
            call2.enqueue(new Callback<Cuerpo1>() {
                @Override
                public void onResponse(Call<Cuerpo1> call2, Response<Cuerpo1> response) {
                    int statusCode = response.code();

                    if (response.isSuccessful()){

                        Mq0501xDatareq contactList =  response.body().getMq0501xDatareq();

                        listDatos =new ArrayList<>();
                        listDatos1 =new ArrayList<>();
                        listDatos2 =new ArrayList<>();

                        for(int e = 0; e<contactList.getRowset().size(); e++) {

                            ArrayList<Rowset> rowset1 = (ArrayList<Rowset>) contactList.getRowset();
                            String caso = rowset1.get(e).getCaso();
                            String descEquipo = rowset1.get(e).getDescEquipo();
                            String ordenNumero = rowset1.get(e).getOrdenNumero();
                            String tipo = rowset1.get(e).getOrdenTipo();
                            String fecha = rowset1.get(e).getFechaSolicitud();
                            String estado= rowset1.get(e).getEstado();
                            String idEquipo= rowset1.get(e).getIDEquipo();
                            String descripcion= rowset1.get(e).getDescripcion();
                            String fechaPro= rowset1.get(e).getFechaProgramacion();


                            listDatos.add( ordenNumero );
                            String content = ( descripcion +  "\n" +
                                    idEquipo + " | "   + descEquipo + "\n"+
                                    "F. Solicitada: " + fecha + "\n"+
                                    "F. Programada: " + fechaPro ) + "\n\n" ;

                            prueba.append(content);

                            AdapterDatos3 adapter = new AdapterDatos3(listDatos);
                            recycler.setAdapter(adapter);

                            listDatos1.add(tipo);

                            AdapterDatos1 adapter2 = new AdapterDatos1(listDatos1);
                            recycler1.setAdapter(adapter2);


                            listDatos2.add(estado);
                            AdapterDatos2 adapter3 = new AdapterDatos2(listDatos2);
                            recycler2.setAdapter(adapter3);

                            adapter.setOnclickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {



                                    numero.setText(listDatos.get(recycler.getChildAdapterPosition(view)));


                                    tipoGlobal = tipo;
                                    numeroGlobal  = ordenNumero;


                                    Retrofit retrofit = new Retrofit.Builder()
                                            .baseUrl(direc)
                                            .addConverterFactory(GsonConverterFactory.create())
                                            .build();

                                    Conexion conexion = retrofit.create(Conexion.class);


                                    Call<Cuerpo1> call3 = conexion.getorden(login3);
                                    call3.enqueue(new Callback<Cuerpo1>() {
                                        @Override
                                        public void onResponse(Call<Cuerpo1> call, Response<Cuerpo1> response) {
                                            if(response.body() != null) {


                                                for(int e = 0; e<contactList.getRowset().size(); e++) {

                                                    ArrayList<Rowset> rowset1 = (ArrayList<Rowset>) contactList.getRowset();
                                                    String caso = rowset1.get(e).getCaso();
                                                    String descEquipo = rowset1.get(e).getDescEquipo();
                                                    String ordenNumero = rowset1.get(e).getOrdenNumero();
                                                    String tipo = rowset1.get(e).getOrdenTipo();
                                                    String fecha = rowset1.get(e).getFechaSolicitud();
                                                    String estado= rowset1.get(e).getEstado();
                                                    String idEquipo= rowset1.get(e).getIDEquipo();
                                                    String descripcion= rowset1.get(e).getDescripcion();
                                                    String fechaPro= rowset1.get(e).getFechaProgramacion();

                                                    String numeroOrden2 = numero.getText().toString();


                                                    if (rowset1.get(e).getOrdenNumero().equals(numeroOrden2)){


                                                        tipo1.setText(tipo );
                                                        numero1.setText(ordenNumero);
                                                        descripcion1.setText(descripcion);

                                                        tipoGlobal = tipo1.getText().toString();
                                                        numeroGlobal  = numero1.getText().toString();
                                                        descripcionGlobal = descripcion1.getText().toString();




                                                    }
                                                    if(rowset1.get(e).getOrdenNumero().equals(numeroOrden2)){


                                                        Intent i = new Intent(TercerActivity.this, CuartoActivity.class);
                                                        i.putExtra("dato2", tipo1.getText().toString());
                                                        i.putExtra("dato1", numero1.getText().toString());
                                                        i.putExtra("dato", descripcion1.getText().toString());




                                                        startActivity(i);



                                                    }
                                                }

                                            }
                                        }

                                        @Override
                                        public void onFailure(Call<Cuerpo1> call, Throwable t) {
                                            Toast.makeText(TercerActivity.this, "error ", Toast.LENGTH_LONG).show();

                                        }
                                    });



                                }
                            });


                        }

                    }
                    else{

                        Toast.makeText(TercerActivity.this, "ERROR fase y mant " + statusCode , Toast.LENGTH_LONG).show();
                        String dato2 = getIntent().getStringExtra("dato2");

                        prueba.setText(dato2);
                    }
                }

                @Override
                public void onFailure(Call<Cuerpo1> call2, Throwable t) {
                    Toast.makeText(TercerActivity.this, "error", Toast.LENGTH_LONG).show();

                }
            });
        }else if(dato.length()!= 0 && dato1.length() != 0 && dato2.length() != 0 && dato3.length() == 0
                && dato4.length() == 0 && dato5.length() == 0 && dato6.length() == 0 && dato7.length() == 0){

            //TIPO DE ORDEN ,FASE, MANTENIMIENTO
            Cuerpo1 login3 = new Cuerpo1(usuarioGlobal, contraseñaGlobal,ResponsableGlobal,estadoCerradoGlobal,dato, dato,dato1,dato1,dato2,dato2);
            Call<Cuerpo1> call2 = conexion.getorden(login3);
            call2.enqueue(new Callback<Cuerpo1>() {
                @Override
                public void onResponse(Call<Cuerpo1> call2, Response<Cuerpo1> response) {
                    int statusCode = response.code();

                    if (response.isSuccessful()){

                        Mq0501xDatareq contactList =  response.body().getMq0501xDatareq();

                        listDatos =new ArrayList<>();
                        listDatos1 =new ArrayList<>();
                        listDatos2 =new ArrayList<>();




                        for(int e = 0; e<contactList.getRowset().size(); e++) {

                            ArrayList<Rowset> rowset1 = (ArrayList<Rowset>) contactList.getRowset();
                            String caso = rowset1.get(e).getCaso();
                            String descEquipo = rowset1.get(e).getDescEquipo();
                            String ordenNumero = rowset1.get(e).getOrdenNumero();
                            String tipo = rowset1.get(e).getOrdenTipo();
                            String fecha = rowset1.get(e).getFechaSolicitud();
                            String estado= rowset1.get(e).getEstado();
                            String idEquipo= rowset1.get(e).getIDEquipo();
                            String descripcion= rowset1.get(e).getDescripcion();
                            String fechaPro= rowset1.get(e).getFechaProgramacion();


                            listDatos.add( ordenNumero );
                            String content = ( descripcion +  "\n" +
                                    idEquipo + " | "   + descEquipo + "\n"+
                                    "F. Solicitada: " + fecha + "\n"+
                                    "F. Programada: " + fechaPro ) + "\n\n" ;

                            prueba.append(content);

                            AdapterDatos3 adapter = new AdapterDatos3(listDatos);
                            recycler.setAdapter(adapter);

                            listDatos1.add(tipo);

                            AdapterDatos1 adapter2 = new AdapterDatos1(listDatos1);
                            recycler1.setAdapter(adapter2);


                            listDatos2.add(estado);
                            AdapterDatos2 adapter3 = new AdapterDatos2(listDatos2);
                            recycler2.setAdapter(adapter3);

                            adapter.setOnclickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {



                                    numero.setText(listDatos.get(recycler.getChildAdapterPosition(view)));


                                    tipoGlobal = tipo;
                                    numeroGlobal  = ordenNumero;


                                    Retrofit retrofit = new Retrofit.Builder()
                                            .baseUrl(direc)
                                            .addConverterFactory(GsonConverterFactory.create())
                                            .build();

                                    Conexion conexion = retrofit.create(Conexion.class);


                                    Call<Cuerpo1> call3 = conexion.getorden(login3);
                                    call3.enqueue(new Callback<Cuerpo1>() {
                                        @Override
                                        public void onResponse(Call<Cuerpo1> call, Response<Cuerpo1> response) {
                                            if(response.body() != null) {


                                                for(int e = 0; e<contactList.getRowset().size(); e++) {

                                                    ArrayList<Rowset> rowset1 = (ArrayList<Rowset>) contactList.getRowset();
                                                    String caso = rowset1.get(e).getCaso();
                                                    String descEquipo = rowset1.get(e).getDescEquipo();
                                                    String ordenNumero = rowset1.get(e).getOrdenNumero();
                                                    String tipo = rowset1.get(e).getOrdenTipo();
                                                    String fecha = rowset1.get(e).getFechaSolicitud();
                                                    String estado= rowset1.get(e).getEstado();
                                                    String idEquipo= rowset1.get(e).getIDEquipo();
                                                    String descripcion= rowset1.get(e).getDescripcion();
                                                    String fechaPro= rowset1.get(e).getFechaProgramacion();

                                                    String numeroOrden2 = numero.getText().toString();


                                                    if (rowset1.get(e).getOrdenNumero().equals(numeroOrden2)){


                                                        tipo1.setText(tipo );
                                                        numero1.setText(ordenNumero);
                                                        descripcion1.setText(descripcion);

                                                        tipoGlobal = tipo1.getText().toString();
                                                        numeroGlobal  = numero1.getText().toString();
                                                        descripcionGlobal = descripcion1.getText().toString();




                                                    }
                                                    if(rowset1.get(e).getOrdenNumero().equals(numeroOrden2)){


                                                        Intent i = new Intent(TercerActivity.this, CuartoActivity.class);
                                                        i.putExtra("dato2", tipo1.getText().toString());
                                                        i.putExtra("dato1", numero1.getText().toString());
                                                        i.putExtra("dato", descripcion1.getText().toString());




                                                        startActivity(i);



                                                    }
                                                }

                                            }
                                        }

                                        @Override
                                        public void onFailure(Call<Cuerpo1> call, Throwable t) {
                                            Toast.makeText(TercerActivity.this, "error ", Toast.LENGTH_LONG).show();

                                        }
                                    });



                                }
                            });


                        }

                    }
                    else{

                        Toast.makeText(TercerActivity.this, "ERROR fase, mant y asig " + statusCode , Toast.LENGTH_LONG).show();
                        String dato2 = getIntent().getStringExtra("dato2");

                        prueba.setText(dato2);
                    }
                }

                @Override
                public void onFailure(Call<Cuerpo1> call2, Throwable t) {
                    Toast.makeText(TercerActivity.this, "error", Toast.LENGTH_LONG).show();

                }
            });
        }else if(dato.length()!= 0  && dato1.length() != 0 && dato2.length() != 0 && dato3.length() == 0
                && dato4.length() == 0 && dato5.length() == 0 && dato6.length() == 0 && dato7.length() != 0){

            //TIPO DE ORDEN ,FASE, MANTENIMIENTO, ASIGNADO
            Cuerpo1 login3 = new Cuerpo1(usuarioGlobal, contraseñaGlobal,ResponsableGlobal,estadoCerradoGlobal,dato, dato,dato1,dato1,dato2,dato2,dato7,dato7);
            Call<Cuerpo1> call2 = conexion.getorden(login3);
            call2.enqueue(new Callback<Cuerpo1>() {
                @Override
                public void onResponse(Call<Cuerpo1> call2, Response<Cuerpo1> response) {
                    int statusCode = response.code();

                    if (response.isSuccessful()){

                        Mq0501xDatareq contactList =  response.body().getMq0501xDatareq();

                        listDatos =new ArrayList<>();
                        listDatos1 =new ArrayList<>();
                        listDatos2 =new ArrayList<>();




                        for(int e = 0; e<contactList.getRowset().size(); e++) {

                            ArrayList<Rowset> rowset1 = (ArrayList<Rowset>) contactList.getRowset();
                            String caso = rowset1.get(e).getCaso();
                            String descEquipo = rowset1.get(e).getDescEquipo();
                            String ordenNumero = rowset1.get(e).getOrdenNumero();
                            String tipo = rowset1.get(e).getOrdenTipo();
                            String fecha = rowset1.get(e).getFechaSolicitud();
                            String estado= rowset1.get(e).getEstado();
                            String idEquipo= rowset1.get(e).getIDEquipo();
                            String descripcion= rowset1.get(e).getDescripcion();
                            String fechaPro= rowset1.get(e).getFechaProgramacion();


                            listDatos.add( ordenNumero );
                            String content = ( descripcion +  "\n" +
                                    idEquipo + " | "   + descEquipo + "\n"+
                                    "F. Solicitada: " + fecha + "\n"+
                                    "F. Programada: " + fechaPro ) + "\n\n" ;

                            prueba.append(content);

                            AdapterDatos3 adapter = new AdapterDatos3(listDatos);
                            recycler.setAdapter(adapter);

                            listDatos1.add(tipo);

                            AdapterDatos1 adapter2 = new AdapterDatos1(listDatos1);
                            recycler1.setAdapter(adapter2);


                            listDatos2.add(estado);
                            AdapterDatos2 adapter3 = new AdapterDatos2(listDatos2);
                            recycler2.setAdapter(adapter3);

                            adapter.setOnclickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {



                                    numero.setText(listDatos.get(recycler.getChildAdapterPosition(view)));


                                    tipoGlobal = tipo;
                                    numeroGlobal  = ordenNumero;


                                    Retrofit retrofit = new Retrofit.Builder()
                                            .baseUrl(direc)
                                            .addConverterFactory(GsonConverterFactory.create())
                                            .build();

                                    Conexion conexion = retrofit.create(Conexion.class);


                                    Call<Cuerpo1> call3 = conexion.getorden(login3);
                                    call3.enqueue(new Callback<Cuerpo1>() {
                                        @Override
                                        public void onResponse(Call<Cuerpo1> call, Response<Cuerpo1> response) {
                                            if(response.body() != null) {


                                                for(int e = 0; e<contactList.getRowset().size(); e++) {

                                                    ArrayList<Rowset> rowset1 = (ArrayList<Rowset>) contactList.getRowset();
                                                    String caso = rowset1.get(e).getCaso();
                                                    String descEquipo = rowset1.get(e).getDescEquipo();
                                                    String ordenNumero = rowset1.get(e).getOrdenNumero();
                                                    String tipo = rowset1.get(e).getOrdenTipo();
                                                    String fecha = rowset1.get(e).getFechaSolicitud();
                                                    String estado= rowset1.get(e).getEstado();
                                                    String idEquipo= rowset1.get(e).getIDEquipo();
                                                    String descripcion= rowset1.get(e).getDescripcion();
                                                    String fechaPro= rowset1.get(e).getFechaProgramacion();

                                                    String numeroOrden2 = numero.getText().toString();


                                                    if (rowset1.get(e).getOrdenNumero().equals(numeroOrden2)){


                                                        tipo1.setText(tipo );
                                                        numero1.setText(ordenNumero);
                                                        descripcion1.setText(descripcion);

                                                        tipoGlobal = tipo1.getText().toString();
                                                        numeroGlobal  = numero1.getText().toString();
                                                        descripcionGlobal = descripcion1.getText().toString();




                                                    }
                                                    if(rowset1.get(e).getOrdenNumero().equals(numeroOrden2)){


                                                        Intent i = new Intent(TercerActivity.this, CuartoActivity.class);
                                                        i.putExtra("dato2", tipo1.getText().toString());
                                                        i.putExtra("dato1", numero1.getText().toString());
                                                        i.putExtra("dato", descripcion1.getText().toString());




                                                        startActivity(i);



                                                    }
                                                }

                                            }
                                        }

                                        @Override
                                        public void onFailure(Call<Cuerpo1> call, Throwable t) {
                                            Toast.makeText(TercerActivity.this, "error ", Toast.LENGTH_LONG).show();

                                        }
                                    });



                                }
                            });


                        }

                    }
                    else{

                        Toast.makeText(TercerActivity.this, "ERROR 954  " + statusCode , Toast.LENGTH_LONG).show();
                        String dato2 = getIntent().getStringExtra("dato2");

                        prueba.setText(dato2);
                    }
                }

                @Override
                public void onFailure(Call<Cuerpo1> call2, Throwable t) {
                    Toast.makeText(TercerActivity.this, "error", Toast.LENGTH_LONG).show();

                }
            });
        }else if(dato.length()!= 0 && dato1.length() != 0 && dato2.length() != 0 && dato3.length() != 0
                && dato4.length() == 0 && dato5.length() == 0 && dato6.length() == 0 && dato7.length() != 0){

            //TIPO DE ORDEN ,FASE, MANTENIMIENTO, ASIGNADO, FECHA SOLICITADA INICIAL
            Cuerpo1 login3 = new Cuerpo1(usuarioGlobal, contraseñaGlobal,ResponsableGlobal,estadoCerradoGlobal,dato, dato,dato1,dato1,dato2,dato2,dato7,dato7,dato3);
            Call<Cuerpo1> call2 = conexion.getorden(login3);
            call2.enqueue(new Callback<Cuerpo1>() {
                @Override
                public void onResponse(Call<Cuerpo1> call2, Response<Cuerpo1> response) {
                    int statusCode = response.code();

                    if (response.isSuccessful()){

                        Mq0501xDatareq contactList =  response.body().getMq0501xDatareq();

                        listDatos =new ArrayList<>();
                        listDatos1 =new ArrayList<>();
                        listDatos2 =new ArrayList<>();




                        for(int e = 0; e<contactList.getRowset().size(); e++) {

                            ArrayList<Rowset> rowset1 = (ArrayList<Rowset>) contactList.getRowset();
                            String caso = rowset1.get(e).getCaso();
                            String descEquipo = rowset1.get(e).getDescEquipo();
                            String ordenNumero = rowset1.get(e).getOrdenNumero();
                            String tipo = rowset1.get(e).getOrdenTipo();
                            String fecha = rowset1.get(e).getFechaSolicitud();
                            String estado= rowset1.get(e).getEstado();
                            String idEquipo= rowset1.get(e).getIDEquipo();
                            String descripcion= rowset1.get(e).getDescripcion();
                            String fechaPro= rowset1.get(e).getFechaProgramacion();


                            listDatos.add( ordenNumero );
                            String content = ( descripcion +  "\n" +
                                    idEquipo + " | "   + descEquipo + "\n"+
                                    "F. Solicitada: " + fecha + "\n"+
                                    "F. Programada: " + fechaPro ) + "\n\n" ;

                            prueba.append(content);

                            AdapterDatos3 adapter = new AdapterDatos3(listDatos);
                            recycler.setAdapter(adapter);

                            listDatos1.add(tipo);

                            AdapterDatos1 adapter2 = new AdapterDatos1(listDatos1);
                            recycler1.setAdapter(adapter2);


                            listDatos2.add(estado);
                            AdapterDatos2 adapter3 = new AdapterDatos2(listDatos2);
                            recycler2.setAdapter(adapter3);

                            adapter.setOnclickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {



                                    numero.setText(listDatos.get(recycler.getChildAdapterPosition(view)));


                                    tipoGlobal = tipo;
                                    numeroGlobal  = ordenNumero;


                                    Retrofit retrofit = new Retrofit.Builder()
                                            .baseUrl(direc)
                                            .addConverterFactory(GsonConverterFactory.create())
                                            .build();

                                    Conexion conexion = retrofit.create(Conexion.class);


                                    Call<Cuerpo1> call3 = conexion.getorden(login3);
                                    call3.enqueue(new Callback<Cuerpo1>() {
                                        @Override
                                        public void onResponse(Call<Cuerpo1> call, Response<Cuerpo1> response) {
                                            if(response.body() != null) {


                                                for(int e = 0; e<contactList.getRowset().size(); e++) {

                                                    ArrayList<Rowset> rowset1 = (ArrayList<Rowset>) contactList.getRowset();
                                                    String caso = rowset1.get(e).getCaso();
                                                    String descEquipo = rowset1.get(e).getDescEquipo();
                                                    String ordenNumero = rowset1.get(e).getOrdenNumero();
                                                    String tipo = rowset1.get(e).getOrdenTipo();
                                                    String fecha = rowset1.get(e).getFechaSolicitud();
                                                    String estado= rowset1.get(e).getEstado();
                                                    String idEquipo= rowset1.get(e).getIDEquipo();
                                                    String descripcion= rowset1.get(e).getDescripcion();
                                                    String fechaPro= rowset1.get(e).getFechaProgramacion();

                                                    String numeroOrden2 = numero.getText().toString();


                                                    if (rowset1.get(e).getOrdenNumero().equals(numeroOrden2)){


                                                        tipo1.setText(tipo );
                                                        numero1.setText(ordenNumero);
                                                        descripcion1.setText(descripcion);

                                                        tipoGlobal = tipo1.getText().toString();
                                                        numeroGlobal  = numero1.getText().toString();
                                                        descripcionGlobal = descripcion1.getText().toString();




                                                    }
                                                    if(rowset1.get(e).getOrdenNumero().equals(numeroOrden2)){


                                                        Intent i = new Intent(TercerActivity.this, CuartoActivity.class);
                                                        i.putExtra("dato2", tipo1.getText().toString());
                                                        i.putExtra("dato1", numero1.getText().toString());
                                                        i.putExtra("dato", descripcion1.getText().toString());




                                                        startActivity(i);



                                                    }
                                                }

                                            }
                                        }

                                        @Override
                                        public void onFailure(Call<Cuerpo1> call, Throwable t) {
                                            Toast.makeText(TercerActivity.this, "error ", Toast.LENGTH_LONG).show();

                                        }
                                    });



                                }
                            });


                        }

                    }
                    else{

                        Toast.makeText(TercerActivity.this, "ERROR 1122" + statusCode , Toast.LENGTH_LONG).show();
                        String dato2 = getIntent().getStringExtra("dato2");

                        prueba.setText(dato2);
                    }
                }

                @Override
                public void onFailure(Call<Cuerpo1> call2, Throwable t) {
                    Toast.makeText(TercerActivity.this, "error", Toast.LENGTH_LONG).show();

                }
            });
        }else if(dato.length()!= 0 && dato1.length() != 0 && dato2.length() != 0 && dato3.length() != 0
                && dato4.length() == 0 && dato5.length() != 0 && dato6.length() == 0 && dato7.length() != 0){

            //TIPO DE ORDEN ,FASE, MANTENIMIENTO, ASIGNADO, FECHA SOLICITADA INICIAL, FECHA PROGRAMADA INICIAL
            Cuerpo1 login3 = new Cuerpo1(usuarioGlobal, contraseñaGlobal,ResponsableGlobal,estadoCerradoGlobal,dato, dato,dato1,dato1,dato2,dato2,dato7,dato7,dato3,dato5);
            Call<Cuerpo1> call2 = conexion.getorden(login3);
            call2.enqueue(new Callback<Cuerpo1>() {
                @Override
                public void onResponse(Call<Cuerpo1> call2, Response<Cuerpo1> response) {
                    int statusCode = response.code();

                    if (response.isSuccessful()){

                        Mq0501xDatareq contactList =  response.body().getMq0501xDatareq();

                        listDatos =new ArrayList<>();
                        listDatos1 =new ArrayList<>();
                        listDatos2 =new ArrayList<>();




                        for(int e = 0; e<contactList.getRowset().size(); e++) {

                            ArrayList<Rowset> rowset1 = (ArrayList<Rowset>) contactList.getRowset();
                            String caso = rowset1.get(e).getCaso();
                            String descEquipo = rowset1.get(e).getDescEquipo();
                            String ordenNumero = rowset1.get(e).getOrdenNumero();
                            String tipo = rowset1.get(e).getOrdenTipo();
                            String fecha = rowset1.get(e).getFechaSolicitud();
                            String estado= rowset1.get(e).getEstado();
                            String idEquipo= rowset1.get(e).getIDEquipo();
                            String descripcion= rowset1.get(e).getDescripcion();
                            String fechaPro= rowset1.get(e).getFechaProgramacion();


                            listDatos.add( ordenNumero );
                            String content = ( descripcion +  "\n" +
                                    idEquipo + " | "   + descEquipo + "\n"+
                                    "F. Solicitada: " + fecha + "\n"+
                                    "F. Programada: " + fechaPro ) + "\n\n" ;

                            prueba.append(content);

                            AdapterDatos3 adapter = new AdapterDatos3(listDatos);
                            recycler.setAdapter(adapter);

                            listDatos1.add(tipo);

                            AdapterDatos1 adapter2 = new AdapterDatos1(listDatos1);
                            recycler1.setAdapter(adapter2);


                            listDatos2.add(estado);
                            AdapterDatos2 adapter3 = new AdapterDatos2(listDatos2);
                            recycler2.setAdapter(adapter3);

                            adapter.setOnclickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {



                                    numero.setText(listDatos.get(recycler.getChildAdapterPosition(view)));


                                    tipoGlobal = tipo;
                                    numeroGlobal  = ordenNumero;


                                    Retrofit retrofit = new Retrofit.Builder()
                                            .baseUrl(direc)
                                            .addConverterFactory(GsonConverterFactory.create())
                                            .build();

                                    Conexion conexion = retrofit.create(Conexion.class);


                                    Call<Cuerpo1> call3 = conexion.getorden(login3);
                                    call3.enqueue(new Callback<Cuerpo1>() {
                                        @Override
                                        public void onResponse(Call<Cuerpo1> call, Response<Cuerpo1> response) {
                                            if(response.body() != null) {


                                                for(int e = 0; e<contactList.getRowset().size(); e++) {

                                                    ArrayList<Rowset> rowset1 = (ArrayList<Rowset>) contactList.getRowset();
                                                    String caso = rowset1.get(e).getCaso();
                                                    String descEquipo = rowset1.get(e).getDescEquipo();
                                                    String ordenNumero = rowset1.get(e).getOrdenNumero();
                                                    String tipo = rowset1.get(e).getOrdenTipo();
                                                    String fecha = rowset1.get(e).getFechaSolicitud();
                                                    String estado= rowset1.get(e).getEstado();
                                                    String idEquipo= rowset1.get(e).getIDEquipo();
                                                    String descripcion= rowset1.get(e).getDescripcion();
                                                    String fechaPro= rowset1.get(e).getFechaProgramacion();

                                                    String numeroOrden2 = numero.getText().toString();


                                                    if (rowset1.get(e).getOrdenNumero().equals(numeroOrden2)){


                                                        tipo1.setText(tipo );
                                                        numero1.setText(ordenNumero);
                                                        descripcion1.setText(descripcion);

                                                        tipoGlobal = tipo1.getText().toString();
                                                        numeroGlobal  = numero1.getText().toString();
                                                        descripcionGlobal = descripcion1.getText().toString();




                                                    }
                                                    if(rowset1.get(e).getOrdenNumero().equals(numeroOrden2)){


                                                        Intent i = new Intent(TercerActivity.this, CuartoActivity.class);
                                                        i.putExtra("dato2", tipo1.getText().toString());
                                                        i.putExtra("dato1", numero1.getText().toString());
                                                        i.putExtra("dato", descripcion1.getText().toString());




                                                        startActivity(i);



                                                    }
                                                }

                                            }
                                        }

                                        @Override
                                        public void onFailure(Call<Cuerpo1> call, Throwable t) {
                                            Toast.makeText(TercerActivity.this, "error ", Toast.LENGTH_LONG).show();

                                        }
                                    });



                                }
                            });


                        }

                    }
                    else{

                        Toast.makeText(TercerActivity.this, "ERROR 1290" + statusCode , Toast.LENGTH_LONG).show();
                        String dato2 = getIntent().getStringExtra("dato2");

                        prueba.setText(dato2);
                    }
                }

                @Override
                public void onFailure(Call<Cuerpo1> call2, Throwable t) {
                    Toast.makeText(TercerActivity.this, "error", Toast.LENGTH_LONG).show();

                }
            });
        } else if(dato.length()!= 0 && dato1.length() != 0 && dato2.length() != 0 && dato3.length() != 0
                && dato4.length() == 0 && dato5.length() != 0 && dato6.length() == 0 && dato7.length() != 0){

            //TIPO DE ORDEN ,FASE, MANTENIMIENTO, ASIGNADO, FECHA SOLICITADA INICIAL, FECHA PROGRAMADA INICIAL
            Cuerpo1 login3 = new Cuerpo1(usuarioGlobal, contraseñaGlobal,ResponsableGlobal,estadoCerradoGlobal,dato, dato,dato1,dato1,dato2,dato2,dato7,dato7,dato3,dato5);
            Call<Cuerpo1> call2 = conexion.getorden(login3);
            call2.enqueue(new Callback<Cuerpo1>() {
                @Override
                public void onResponse(Call<Cuerpo1> call2, Response<Cuerpo1> response) {
                    int statusCode = response.code();

                    if (response.isSuccessful()){

                        Mq0501xDatareq contactList =  response.body().getMq0501xDatareq();

                        listDatos =new ArrayList<>();
                        listDatos1 =new ArrayList<>();
                        listDatos2 =new ArrayList<>();




                        for(int e = 0; e<contactList.getRowset().size(); e++) {

                            ArrayList<Rowset> rowset1 = (ArrayList<Rowset>) contactList.getRowset();
                            String caso = rowset1.get(e).getCaso();
                            String descEquipo = rowset1.get(e).getDescEquipo();
                            String ordenNumero = rowset1.get(e).getOrdenNumero();
                            String tipo = rowset1.get(e).getOrdenTipo();
                            String fecha = rowset1.get(e).getFechaSolicitud();
                            String estado= rowset1.get(e).getEstado();
                            String idEquipo= rowset1.get(e).getIDEquipo();
                            String descripcion= rowset1.get(e).getDescripcion();
                            String fechaPro= rowset1.get(e).getFechaProgramacion();


                            listDatos.add( ordenNumero );
                            String content = ( descripcion +  "\n" +
                                    idEquipo + " | "   + descEquipo + "\n"+
                                    "F. Solicitada: " + fecha + "\n"+
                                    "F. Programada: " + fechaPro ) + "\n\n" ;

                            prueba.append(content);

                            AdapterDatos3 adapter = new AdapterDatos3(listDatos);
                            recycler.setAdapter(adapter);

                            listDatos1.add(tipo);

                            AdapterDatos1 adapter2 = new AdapterDatos1(listDatos1);
                            recycler1.setAdapter(adapter2);


                            listDatos2.add(estado);
                            AdapterDatos2 adapter3 = new AdapterDatos2(listDatos2);
                            recycler2.setAdapter(adapter3);

                            adapter.setOnclickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {



                                    numero.setText(listDatos.get(recycler.getChildAdapterPosition(view)));


                                    tipoGlobal = tipo;
                                    numeroGlobal  = ordenNumero;


                                    Retrofit retrofit = new Retrofit.Builder()
                                            .baseUrl(direc)
                                            .addConverterFactory(GsonConverterFactory.create())
                                            .build();

                                    Conexion conexion = retrofit.create(Conexion.class);


                                    Call<Cuerpo1> call3 = conexion.getorden(login3);
                                    call3.enqueue(new Callback<Cuerpo1>() {
                                        @Override
                                        public void onResponse(Call<Cuerpo1> call, Response<Cuerpo1> response) {
                                            if(response.body() != null) {


                                                for(int e = 0; e<contactList.getRowset().size(); e++) {

                                                    ArrayList<Rowset> rowset1 = (ArrayList<Rowset>) contactList.getRowset();
                                                    String caso = rowset1.get(e).getCaso();
                                                    String descEquipo = rowset1.get(e).getDescEquipo();
                                                    String ordenNumero = rowset1.get(e).getOrdenNumero();
                                                    String tipo = rowset1.get(e).getOrdenTipo();
                                                    String fecha = rowset1.get(e).getFechaSolicitud();
                                                    String estado= rowset1.get(e).getEstado();
                                                    String idEquipo= rowset1.get(e).getIDEquipo();
                                                    String descripcion= rowset1.get(e).getDescripcion();
                                                    String fechaPro= rowset1.get(e).getFechaProgramacion();

                                                    String numeroOrden2 = numero.getText().toString();


                                                    if (rowset1.get(e).getOrdenNumero().equals(numeroOrden2)){


                                                        tipo1.setText(tipo );
                                                        numero1.setText(ordenNumero);
                                                        descripcion1.setText(descripcion);

                                                        tipoGlobal = tipo1.getText().toString();
                                                        numeroGlobal  = numero1.getText().toString();
                                                        descripcionGlobal = descripcion1.getText().toString();




                                                    }
                                                    if(rowset1.get(e).getOrdenNumero().equals(numeroOrden2)){


                                                        Intent i = new Intent(TercerActivity.this, CuartoActivity.class);
                                                        i.putExtra("dato2", tipo1.getText().toString());
                                                        i.putExtra("dato1", numero1.getText().toString());
                                                        i.putExtra("dato", descripcion1.getText().toString());




                                                        startActivity(i);



                                                    }
                                                }

                                            }
                                        }

                                        @Override
                                        public void onFailure(Call<Cuerpo1> call, Throwable t) {
                                            Toast.makeText(TercerActivity.this, "error ", Toast.LENGTH_LONG).show();

                                        }
                                    });



                                }
                            });


                        }

                    }
                    else{

                        Toast.makeText(TercerActivity.this, "ERROR 1290" + statusCode , Toast.LENGTH_LONG).show();
                        String dato2 = getIntent().getStringExtra("dato2");

                        prueba.setText(dato2);
                    }
                }

                @Override
                public void onFailure(Call<Cuerpo1> call2, Throwable t) {
                    Toast.makeText(TercerActivity.this, "error", Toast.LENGTH_LONG).show();

                }
            });
        } else if(dato.length()!= 0  && dato1.length() == 0 && dato2.length() == 0 && dato3.length() == 0
                && dato4.length() == 0 && dato5.length() == 0 && dato6.length() == 0 && dato7.length() != 0 ){

            //TIPO DE ORDEN Y ASIGNADO
            Cuerpo login3 = new Cuerpo(usuarioGlobal, contraseñaGlobal,ResponsableGlobal,estadoCerradoGlobal,dato, dato,dato7,dato7);
            Call<Cuerpo> call2 = conexion.getorden2(login3);
            call2.enqueue(new Callback<Cuerpo>() {
                @Override
                public void onResponse(Call<Cuerpo> call2, Response<Cuerpo> response) {
                    int statusCode = response.code();

                    if (response.isSuccessful()){

                        Mq0501xDatareq contactList =  response.body().getMq0501xDatareq();

                        listDatos =new ArrayList<>();
                        listDatos1 =new ArrayList<>();
                        listDatos2 =new ArrayList<>();

                        for(int e = 0; e<contactList.getRowset().size(); e++) {

                            ArrayList<Rowset> rowset1 = (ArrayList<Rowset>) contactList.getRowset();
                            String caso = rowset1.get(e).getCaso();
                            String descEquipo = rowset1.get(e).getDescEquipo();
                            String ordenNumero = rowset1.get(e).getOrdenNumero();
                            String tipo = rowset1.get(e).getOrdenTipo();
                            String fecha = rowset1.get(e).getFechaSolicitud();
                            String estado= rowset1.get(e).getEstado();
                            String idEquipo= rowset1.get(e).getIDEquipo();
                            String descripcion= rowset1.get(e).getDescripcion();
                            String fechaPro= rowset1.get(e).getFechaProgramacion();


                            listDatos.add( ordenNumero );
                            String content = ( descripcion +  "\n" +
                                    idEquipo + " | "   + descEquipo + "\n"+
                                    "F. Solicitada: " + fecha + "\n"+
                                    "F. Programada: " + fechaPro ) + "\n\n" ;

                            prueba.append(content);

                            AdapterDatos3 adapter = new AdapterDatos3(listDatos);
                            recycler.setAdapter(adapter);

                            listDatos1.add(tipo);

                            AdapterDatos1 adapter2 = new AdapterDatos1(listDatos1);
                            recycler1.setAdapter(adapter2);


                            listDatos2.add(estado);
                            AdapterDatos2 adapter3 = new AdapterDatos2(listDatos2);
                            recycler2.setAdapter(adapter3);

                            adapter.setOnclickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {



                                    numero.setText(listDatos.get(recycler.getChildAdapterPosition(view)));


                                    tipoGlobal = tipo;
                                    numeroGlobal  = ordenNumero;


                                    Retrofit retrofit = new Retrofit.Builder()
                                            .baseUrl(direc)
                                            .addConverterFactory(GsonConverterFactory.create())
                                            .build();

                                    Conexion conexion = retrofit.create(Conexion.class);


                                    Call<Cuerpo> call3 = conexion.getorden2(login3);
                                    call3.enqueue(new Callback<Cuerpo>() {
                                        @Override
                                        public void onResponse(Call<Cuerpo> call, Response<Cuerpo> response) {
                                            if(response.body() != null) {


                                                for(int e = 0; e<contactList.getRowset().size(); e++) {

                                                    ArrayList<Rowset> rowset1 = (ArrayList<Rowset>) contactList.getRowset();
                                                    String caso = rowset1.get(e).getCaso();
                                                    String descEquipo = rowset1.get(e).getDescEquipo();
                                                    String ordenNumero = rowset1.get(e).getOrdenNumero();
                                                    String tipo = rowset1.get(e).getOrdenTipo();
                                                    String fecha = rowset1.get(e).getFechaSolicitud();
                                                    String estado= rowset1.get(e).getEstado();
                                                    String idEquipo= rowset1.get(e).getIDEquipo();
                                                    String descripcion= rowset1.get(e).getDescripcion();
                                                    String fechaPro= rowset1.get(e).getFechaProgramacion();

                                                    String numeroOrden2 = numero.getText().toString();


                                                    if (rowset1.get(e).getOrdenNumero().equals(numeroOrden2)){


                                                        tipo1.setText(tipo );
                                                        numero1.setText(ordenNumero);
                                                        descripcion1.setText(descripcion);

                                                        tipoGlobal = tipo1.getText().toString();
                                                        numeroGlobal  = numero1.getText().toString();
                                                        descripcionGlobal = descripcion1.getText().toString();




                                                    }
                                                    if(rowset1.get(e).getOrdenNumero().equals(numeroOrden2)){


                                                        Intent i = new Intent(TercerActivity.this, CuartoActivity.class);
                                                        i.putExtra("dato2", tipo1.getText().toString());
                                                        i.putExtra("dato1", numero1.getText().toString());
                                                        i.putExtra("dato", descripcion1.getText().toString());




                                                        startActivity(i);



                                                    }
                                                }

                                            }
                                        }

                                        @Override
                                        public void onFailure(Call<Cuerpo> call, Throwable t) {
                                            Toast.makeText(TercerActivity.this, "error ", Toast.LENGTH_LONG).show();

                                        }
                                    });



                                }
                            });


                        }

                    }
                    else{

                        Toast.makeText(TercerActivity.this, "ERROR fase y mant " + statusCode , Toast.LENGTH_LONG).show();
                        String dato2 = getIntent().getStringExtra("dato2");

                        prueba.setText(dato2);
                    }
                }

                @Override
                public void onFailure(Call<Cuerpo> call2, Throwable t) {
                    Toast.makeText(TercerActivity.this, "error", Toast.LENGTH_LONG).show();

                }
            });
        }else if(dato.length()== 0 && dato1.length() != 0 && dato2.length() == 0 && dato3.length() == 0
                && dato4.length() == 0 && dato5.length() == 0 && dato6.length() == 0 && dato7.length() == 0){
            //FASE
            Cuerpo2 login3 = new Cuerpo2(usuarioGlobal, contraseñaGlobal,ResponsableGlobal,estadoCerradoGlobal,dato1, dato1);
            Call<Cuerpo2> call2 = conexion.getFase(login3);
            call2.enqueue(new Callback<Cuerpo2>() {
                @Override
                public void onResponse(Call<Cuerpo2> call2, Response<Cuerpo2> response) {
                    int statusCode = response.code();

                    if (response.isSuccessful()){

                        Mq0501xDatareq contactList =  response.body().getMq0501xDatareq();

                        listDatos =new ArrayList<>();
                        listDatos1 =new ArrayList<>();
                        listDatos2 =new ArrayList<>();




                        for(int e = 0; e<contactList.getRowset().size(); e++) {

                            ArrayList<Rowset> rowset1 = (ArrayList<Rowset>) contactList.getRowset();
                            String caso = rowset1.get(e).getCaso();
                            String descEquipo = rowset1.get(e).getDescEquipo();
                            String ordenNumero = rowset1.get(e).getOrdenNumero();
                            String tipo = rowset1.get(e).getOrdenTipo();
                            String fecha = rowset1.get(e).getFechaSolicitud();
                            String estado= rowset1.get(e).getEstado();
                            String idEquipo= rowset1.get(e).getIDEquipo();
                            String descripcion= rowset1.get(e).getDescripcion();
                            String fechaPro= rowset1.get(e).getFechaProgramacion();


                            listDatos.add( ordenNumero );
                            String content = ( descripcion +  "\n" +
                                    idEquipo + " | "   + descEquipo + "\n"+
                                    "F. Solicitada: " + fecha + "\n"+
                                    "F. Programada: " + fechaPro ) + "\n\n" ;

                            prueba.append(content);

                            AdapterDatos3 adapter = new AdapterDatos3(listDatos);
                            recycler.setAdapter(adapter);

                            listDatos1.add(tipo);

                            AdapterDatos1 adapter2 = new AdapterDatos1(listDatos1);
                            recycler1.setAdapter(adapter2);


                            listDatos2.add(estado);
                            AdapterDatos2 adapter3 = new AdapterDatos2(listDatos2);
                            recycler2.setAdapter(adapter3);

                            adapter.setOnclickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {



                                    numero.setText(listDatos.get(recycler.getChildAdapterPosition(view)));


                                    tipoGlobal = tipo;
                                    numeroGlobal  = ordenNumero;


                                    Retrofit retrofit = new Retrofit.Builder()
                                            .baseUrl(direc)
                                            .addConverterFactory(GsonConverterFactory.create())
                                            .build();

                                    Conexion conexion = retrofit.create(Conexion.class);


                                    Call<Cuerpo2> call3 = conexion.getFase(login3);
                                    call3.enqueue(new Callback<Cuerpo2>() {
                                        @Override
                                        public void onResponse(Call<Cuerpo2> call, Response<Cuerpo2> response) {
                                            if(response.body() != null) {


                                                for(int e = 0; e<contactList.getRowset().size(); e++) {

                                                    ArrayList<Rowset> rowset1 = (ArrayList<Rowset>) contactList.getRowset();
                                                    String caso = rowset1.get(e).getCaso();
                                                    String descEquipo = rowset1.get(e).getDescEquipo();
                                                    String ordenNumero = rowset1.get(e).getOrdenNumero();
                                                    String tipo = rowset1.get(e).getOrdenTipo();
                                                    String fecha = rowset1.get(e).getFechaSolicitud();
                                                    String estado= rowset1.get(e).getEstado();
                                                    String idEquipo= rowset1.get(e).getIDEquipo();
                                                    String descripcion= rowset1.get(e).getDescripcion();
                                                    String fechaPro= rowset1.get(e).getFechaProgramacion();

                                                    String numeroOrden2 = numero.getText().toString();


                                                    if (rowset1.get(e).getOrdenNumero().equals(numeroOrden2)){


                                                        tipo1.setText(tipo );
                                                        numero1.setText(ordenNumero);
                                                        descripcion1.setText(descripcion);

                                                        tipoGlobal = tipo1.getText().toString();
                                                        numeroGlobal  = numero1.getText().toString();
                                                        descripcionGlobal = descripcion1.getText().toString();




                                                    }
                                                    if(rowset1.get(e).getOrdenNumero().equals(numeroOrden2)){


                                                        Intent i = new Intent(TercerActivity.this, CuartoActivity.class);
                                                        i.putExtra("dato2", tipo1.getText().toString());
                                                        i.putExtra("dato1", numero1.getText().toString());
                                                        i.putExtra("dato", descripcion1.getText().toString());




                                                        startActivity(i);



                                                    }
                                                }

                                            }
                                        }

                                        @Override
                                        public void onFailure(Call<Cuerpo2> call, Throwable t) {
                                            Toast.makeText(TercerActivity.this, "error ", Toast.LENGTH_LONG).show();

                                        }
                                    });



                                }
                            });


                        }

                    }
                    else{

                        Toast.makeText(TercerActivity.this, "ERROR 1458" + statusCode , Toast.LENGTH_LONG).show();
                        String dato2 = getIntent().getStringExtra("dato2");

                        prueba.setText(dato2);
                    }
                }

                @Override
                public void onFailure(Call<Cuerpo2> call2, Throwable t) {
                    Toast.makeText(TercerActivity.this, "error", Toast.LENGTH_LONG).show();

                }
            });
        }else if(dato.length()== 0 && dato1.length() != 0 && dato2.length() != 0 && dato3.length() != 0
                && dato4.length() == 0 && dato5.length() == 0 && dato6.length() == 0 && dato7.length() == 0){
            //FASE , MANTENIMIENTO, FECHA INICIO
            Cuerpo2 login3 = new Cuerpo2(usuarioGlobal, contraseñaGlobal,ResponsableGlobal,estadoCerradoGlobal,dato1, dato1,dato2,dato2,dato3);
            Call<Cuerpo2> call2 = conexion.getFase(login3);
            call2.enqueue(new Callback<Cuerpo2>() {
                @Override
                public void onResponse(Call<Cuerpo2> call2, Response<Cuerpo2> response) {
                    int statusCode = response.code();

                    if (response.isSuccessful()){

                        Mq0501xDatareq contactList =  response.body().getMq0501xDatareq();

                        listDatos =new ArrayList<>();
                        listDatos1 =new ArrayList<>();
                        listDatos2 =new ArrayList<>();




                        for(int e = 0; e<contactList.getRowset().size(); e++) {

                            ArrayList<Rowset> rowset1 = (ArrayList<Rowset>) contactList.getRowset();
                            String caso = rowset1.get(e).getCaso();
                            String descEquipo = rowset1.get(e).getDescEquipo();
                            String ordenNumero = rowset1.get(e).getOrdenNumero();
                            String tipo = rowset1.get(e).getOrdenTipo();
                            String fecha = rowset1.get(e).getFechaSolicitud();
                            String estado= rowset1.get(e).getEstado();
                            String idEquipo= rowset1.get(e).getIDEquipo();
                            String descripcion= rowset1.get(e).getDescripcion();
                            String fechaPro= rowset1.get(e).getFechaProgramacion();


                            listDatos.add( ordenNumero );
                            String content = ( descripcion +  "\n" +
                                    idEquipo + " | "   + descEquipo + "\n"+
                                    "F. Solicitada: " + fecha + "\n"+
                                    "F. Programada: " + fechaPro ) + "\n\n" ;

                            prueba.append(content);

                            AdapterDatos3 adapter = new AdapterDatos3(listDatos);
                            recycler.setAdapter(adapter);

                            listDatos1.add(tipo);

                            AdapterDatos1 adapter2 = new AdapterDatos1(listDatos1);
                            recycler1.setAdapter(adapter2);


                            listDatos2.add(estado);
                            AdapterDatos2 adapter3 = new AdapterDatos2(listDatos2);
                            recycler2.setAdapter(adapter3);

                            adapter.setOnclickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {



                                    numero.setText(listDatos.get(recycler.getChildAdapterPosition(view)));


                                    tipoGlobal = tipo;
                                    numeroGlobal  = ordenNumero;


                                    Retrofit retrofit = new Retrofit.Builder()
                                            .baseUrl(direc)
                                            .addConverterFactory(GsonConverterFactory.create())
                                            .build();

                                    Conexion conexion = retrofit.create(Conexion.class);


                                    Call<Cuerpo2> call3 = conexion.getFase(login3);
                                    call3.enqueue(new Callback<Cuerpo2>() {
                                        @Override
                                        public void onResponse(Call<Cuerpo2> call, Response<Cuerpo2> response) {
                                            if(response.body() != null) {


                                                for(int e = 0; e<contactList.getRowset().size(); e++) {

                                                    ArrayList<Rowset> rowset1 = (ArrayList<Rowset>) contactList.getRowset();
                                                    String caso = rowset1.get(e).getCaso();
                                                    String descEquipo = rowset1.get(e).getDescEquipo();
                                                    String ordenNumero = rowset1.get(e).getOrdenNumero();
                                                    String tipo = rowset1.get(e).getOrdenTipo();
                                                    String fecha = rowset1.get(e).getFechaSolicitud();
                                                    String estado= rowset1.get(e).getEstado();
                                                    String idEquipo= rowset1.get(e).getIDEquipo();
                                                    String descripcion= rowset1.get(e).getDescripcion();
                                                    String fechaPro= rowset1.get(e).getFechaProgramacion();

                                                    String numeroOrden2 = numero.getText().toString();


                                                    if (rowset1.get(e).getOrdenNumero().equals(numeroOrden2)){


                                                        tipo1.setText(tipo );
                                                        numero1.setText(ordenNumero);
                                                        descripcion1.setText(descripcion);

                                                        tipoGlobal = tipo1.getText().toString();
                                                        numeroGlobal  = numero1.getText().toString();
                                                        descripcionGlobal = descripcion1.getText().toString();




                                                    }
                                                    if(rowset1.get(e).getOrdenNumero().equals(numeroOrden2)){


                                                        Intent i = new Intent(TercerActivity.this, CuartoActivity.class);
                                                        i.putExtra("dato2", tipo1.getText().toString());
                                                        i.putExtra("dato1", numero1.getText().toString());
                                                        i.putExtra("dato", descripcion1.getText().toString());




                                                        startActivity(i);



                                                    }
                                                }

                                            }
                                        }

                                        @Override
                                        public void onFailure(Call<Cuerpo2> call, Throwable t) {
                                            Toast.makeText(TercerActivity.this, "error ", Toast.LENGTH_LONG).show();

                                        }
                                    });



                                }
                            });


                        }

                    }
                    else{

                        Toast.makeText(TercerActivity.this, "ERROR " + statusCode , Toast.LENGTH_LONG).show();
                        String dato2 = getIntent().getStringExtra("dato2");

                        prueba.setText(dato2);
                    }
                }

                @Override
                public void onFailure(Call<Cuerpo2> call2, Throwable t) {
                    Toast.makeText(TercerActivity.this, "error", Toast.LENGTH_LONG).show();

                }
            });
        }else if(dato.length()== 0 && dato1.length() != 0 && dato2.length() == 0 && dato3.length() == 0
                && dato4.length() == 0 && dato5.length() == 0 && dato6.length() == 0 && dato7.length() != 0){
            //FASE Y ASIGNADO
            Cuerpo3 login3 = new Cuerpo3(usuarioGlobal, contraseñaGlobal,ResponsableGlobal,estadoCerradoGlobal,dato1, dato1,dato7,dato7);
            Call<Cuerpo3> call2 = conexion.getFase2(login3);
            call2.enqueue(new Callback<Cuerpo3>() {
                @Override
                public void onResponse(Call<Cuerpo3> call2, Response<Cuerpo3> response) {
                    int statusCode = response.code();

                    if (response.isSuccessful()){

                        Mq0501xDatareq contactList =  response.body().getMq0501xDatareq();

                        listDatos =new ArrayList<>();
                        listDatos1 =new ArrayList<>();
                        listDatos2 =new ArrayList<>();




                        for(int e = 0; e<contactList.getRowset().size(); e++) {

                            ArrayList<Rowset> rowset1 = (ArrayList<Rowset>) contactList.getRowset();
                            String caso = rowset1.get(e).getCaso();
                            String descEquipo = rowset1.get(e).getDescEquipo();
                            String ordenNumero = rowset1.get(e).getOrdenNumero();
                            String tipo = rowset1.get(e).getOrdenTipo();
                            String fecha = rowset1.get(e).getFechaSolicitud();
                            String estado= rowset1.get(e).getEstado();
                            String idEquipo= rowset1.get(e).getIDEquipo();
                            String descripcion= rowset1.get(e).getDescripcion();
                            String fechaPro= rowset1.get(e).getFechaProgramacion();


                            listDatos.add( ordenNumero );
                            String content = ( descripcion +  "\n" +
                                    idEquipo + " | "   + descEquipo + "\n"+
                                    "F. Solicitada: " + fecha + "\n"+
                                    "F. Programada: " + fechaPro ) + "\n\n" ;

                            prueba.append(content);

                            AdapterDatos3 adapter = new AdapterDatos3(listDatos);
                            recycler.setAdapter(adapter);

                            listDatos1.add(tipo);

                            AdapterDatos1 adapter2 = new AdapterDatos1(listDatos1);
                            recycler1.setAdapter(adapter2);


                            listDatos2.add(estado);
                            AdapterDatos2 adapter3 = new AdapterDatos2(listDatos2);
                            recycler2.setAdapter(adapter3);

                            adapter.setOnclickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {



                                    numero.setText(listDatos.get(recycler.getChildAdapterPosition(view)));


                                    tipoGlobal = tipo;
                                    numeroGlobal  = ordenNumero;


                                    Retrofit retrofit = new Retrofit.Builder()
                                            .baseUrl(direc)
                                            .addConverterFactory(GsonConverterFactory.create())
                                            .build();

                                    Conexion conexion = retrofit.create(Conexion.class);


                                    Call<Cuerpo3> call3 = conexion.getFase2(login3);
                                    call3.enqueue(new Callback<Cuerpo3>() {
                                        @Override
                                        public void onResponse(Call<Cuerpo3> call, Response<Cuerpo3> response) {
                                            if(response.body() != null) {


                                                for(int e = 0; e<contactList.getRowset().size(); e++) {

                                                    ArrayList<Rowset> rowset1 = (ArrayList<Rowset>) contactList.getRowset();
                                                    String caso = rowset1.get(e).getCaso();
                                                    String descEquipo = rowset1.get(e).getDescEquipo();
                                                    String ordenNumero = rowset1.get(e).getOrdenNumero();
                                                    String tipo = rowset1.get(e).getOrdenTipo();
                                                    String fecha = rowset1.get(e).getFechaSolicitud();
                                                    String estado= rowset1.get(e).getEstado();
                                                    String idEquipo= rowset1.get(e).getIDEquipo();
                                                    String descripcion= rowset1.get(e).getDescripcion();
                                                    String fechaPro= rowset1.get(e).getFechaProgramacion();

                                                    String numeroOrden2 = numero.getText().toString();


                                                    if (rowset1.get(e).getOrdenNumero().equals(numeroOrden2)){


                                                        tipo1.setText(tipo );
                                                        numero1.setText(ordenNumero);
                                                        descripcion1.setText(descripcion);

                                                        tipoGlobal = tipo1.getText().toString();
                                                        numeroGlobal  = numero1.getText().toString();
                                                        descripcionGlobal = descripcion1.getText().toString();




                                                    }
                                                    if(rowset1.get(e).getOrdenNumero().equals(numeroOrden2)){


                                                        Intent i = new Intent(TercerActivity.this, CuartoActivity.class);
                                                        i.putExtra("dato2", tipo1.getText().toString());
                                                        i.putExtra("dato1", numero1.getText().toString());
                                                        i.putExtra("dato", descripcion1.getText().toString());




                                                        startActivity(i);



                                                    }
                                                }

                                            }
                                        }

                                        @Override
                                        public void onFailure(Call<Cuerpo3> call, Throwable t) {
                                            Toast.makeText(TercerActivity.this, "error ", Toast.LENGTH_LONG).show();

                                        }
                                    });



                                }
                            });


                        }

                    }
                    else{

                        Toast.makeText(TercerActivity.this, "ERROR 1458" + statusCode , Toast.LENGTH_LONG).show();
                        String dato2 = getIntent().getStringExtra("dato2");

                        prueba.setText(dato2);
                    }
                }

                @Override
                public void onFailure(Call<Cuerpo3> call2, Throwable t) {
                    Toast.makeText(TercerActivity.this, "error", Toast.LENGTH_LONG).show();

                }
            });
        }else if(dato.length()== 0 && dato1.length() == 0 && dato2.length() != 0 && dato3.length() == 0
                && dato4.length() == 0 && dato5.length() == 0 && dato6.length() == 0 && dato7.length() == 0){
            //MANTENIMIENTO
            Cuerpo3 login3 = new Cuerpo3(usuarioGlobal, contraseñaGlobal,ResponsableGlobal,estadoCerradoGlobal,dato2,dato2);
            Call<Cuerpo3> call2 = conexion.getMantenimiento(login3);
            call2.enqueue(new Callback<Cuerpo3>() {
                @Override
                public void onResponse(Call<Cuerpo3> call2, Response<Cuerpo3> response) {
                    int statusCode = response.code();

                    if (response.isSuccessful()){

                        Mq0501xDatareq contactList =  response.body().getMq0501xDatareq();

                        listDatos =new ArrayList<>();
                        listDatos1 =new ArrayList<>();
                        listDatos2 =new ArrayList<>();


                        for(int e = 0; e<contactList.getRowset().size(); e++) {

                            ArrayList<Rowset> rowset1 = (ArrayList<Rowset>) contactList.getRowset();
                            String caso = rowset1.get(e).getCaso();
                            String descEquipo = rowset1.get(e).getDescEquipo();
                            String ordenNumero = rowset1.get(e).getOrdenNumero();
                            String tipo = rowset1.get(e).getOrdenTipo();
                            String fecha = rowset1.get(e).getFechaSolicitud();
                            String estado= rowset1.get(e).getEstado();
                            String idEquipo= rowset1.get(e).getIDEquipo();
                            String descripcion= rowset1.get(e).getDescripcion();
                            String fechaPro= rowset1.get(e).getFechaProgramacion();


                            listDatos.add( ordenNumero );
                            String content = ( descripcion +  "\n" +
                                    idEquipo + " | "   + descEquipo + "\n"+
                                    "F. Solicitada: " + fecha + "\n"+
                                    "F. Programada: " + fechaPro ) + "\n\n" ;

                            prueba.append(content);

                            AdapterDatos3 adapter = new AdapterDatos3(listDatos);
                            recycler.setAdapter(adapter);

                            listDatos1.add(tipo);

                            AdapterDatos1 adapter2 = new AdapterDatos1(listDatos1);
                            recycler1.setAdapter(adapter2);


                            listDatos2.add(estado);
                            AdapterDatos2 adapter3 = new AdapterDatos2(listDatos2);
                            recycler2.setAdapter(adapter3);

                            adapter.setOnclickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {



                                    numero.setText(listDatos.get(recycler.getChildAdapterPosition(view)));


                                    tipoGlobal = tipo;
                                    numeroGlobal  = ordenNumero;


                                    Retrofit retrofit = new Retrofit.Builder()
                                            .baseUrl(direc)
                                            .addConverterFactory(GsonConverterFactory.create())
                                            .build();

                                    Conexion conexion = retrofit.create(Conexion.class);


                                    Call<Cuerpo3> call3 = conexion.getMantenimiento(login3);
                                    call3.enqueue(new Callback<Cuerpo3>() {
                                        @Override
                                        public void onResponse(Call<Cuerpo3> call, Response<Cuerpo3> response) {
                                            if(response.body() != null) {


                                                for(int e = 0; e<contactList.getRowset().size(); e++) {

                                                    ArrayList<Rowset> rowset1 = (ArrayList<Rowset>) contactList.getRowset();
                                                    String caso = rowset1.get(e).getCaso();
                                                    String descEquipo = rowset1.get(e).getDescEquipo();
                                                    String ordenNumero = rowset1.get(e).getOrdenNumero();
                                                    String tipo = rowset1.get(e).getOrdenTipo();
                                                    String fecha = rowset1.get(e).getFechaSolicitud();
                                                    String estado= rowset1.get(e).getEstado();
                                                    String idEquipo= rowset1.get(e).getIDEquipo();
                                                    String descripcion= rowset1.get(e).getDescripcion();
                                                    String fechaPro= rowset1.get(e).getFechaProgramacion();

                                                    String numeroOrden2 = numero.getText().toString();


                                                    if (rowset1.get(e).getOrdenNumero().equals(numeroOrden2)){


                                                        tipo1.setText(tipo );
                                                        numero1.setText(ordenNumero);
                                                        descripcion1.setText(descripcion);

                                                        tipoGlobal = tipo1.getText().toString();
                                                        numeroGlobal  = numero1.getText().toString();
                                                        descripcionGlobal = descripcion1.getText().toString();




                                                    }
                                                    if(rowset1.get(e).getOrdenNumero().equals(numeroOrden2)){


                                                        Intent i = new Intent(TercerActivity.this, CuartoActivity.class);
                                                        i.putExtra("dato2", tipo1.getText().toString());
                                                        i.putExtra("dato1", numero1.getText().toString());
                                                        i.putExtra("dato", descripcion1.getText().toString());




                                                        startActivity(i);



                                                    }
                                                }

                                            }
                                        }

                                        @Override
                                        public void onFailure(Call<Cuerpo3> call, Throwable t) {
                                            Toast.makeText(TercerActivity.this, "error ", Toast.LENGTH_LONG).show();

                                        }
                                    });



                                }
                            });


                        }

                    }
                    else{

                        Toast.makeText(TercerActivity.this, "ERROR 1458" + statusCode , Toast.LENGTH_LONG).show();
                        String dato2 = getIntent().getStringExtra("dato2");

                        prueba.setText(dato2);
                    }
                }

                @Override
                public void onFailure(Call<Cuerpo3> call2, Throwable t) {
                    Toast.makeText(TercerActivity.this, "error", Toast.LENGTH_LONG).show();

                }
            });
        }else if(dato.length()== 0 && dato1.length() != 0 && dato2.length() != 0 && dato3.length() != 0
                && dato4.length() == 0 && dato5.length() == 0 && dato6.length() == 0 && dato7.length() != 0){
            //MANTENIMIENTO y ASIGNADO y FECHA ASIGNADO INICIAL
            Cuerpo3 login3 = new Cuerpo3(usuarioGlobal, contraseñaGlobal,ResponsableGlobal,estadoCerradoGlobal,dato2,dato2,dato3,dato7,dato7);
            Call<Cuerpo3> call2 = conexion.getMantenimiento(login3);
            call2.enqueue(new Callback<Cuerpo3>() {
                @Override
                public void onResponse(Call<Cuerpo3> call2, Response<Cuerpo3> response) {
                    int statusCode = response.code();

                    if (response.isSuccessful()){

                        Mq0501xDatareq contactList =  response.body().getMq0501xDatareq();

                        listDatos =new ArrayList<>();
                        listDatos1 =new ArrayList<>();
                        listDatos2 =new ArrayList<>();


                        for(int e = 0; e<contactList.getRowset().size(); e++) {

                            ArrayList<Rowset> rowset1 = (ArrayList<Rowset>) contactList.getRowset();
                            String caso = rowset1.get(e).getCaso();
                            String descEquipo = rowset1.get(e).getDescEquipo();
                            String ordenNumero = rowset1.get(e).getOrdenNumero();
                            String tipo = rowset1.get(e).getOrdenTipo();
                            String fecha = rowset1.get(e).getFechaSolicitud();
                            String estado= rowset1.get(e).getEstado();
                            String idEquipo= rowset1.get(e).getIDEquipo();
                            String descripcion= rowset1.get(e).getDescripcion();
                            String fechaPro= rowset1.get(e).getFechaProgramacion();


                            listDatos.add( ordenNumero );
                            String content = ( descripcion +  "\n" +
                                    idEquipo + " | "   + descEquipo + "\n"+
                                    "F. Solicitada: " + fecha + "\n"+
                                    "F. Programada: " + fechaPro ) + "\n\n" ;

                            prueba.append(content);

                            AdapterDatos3 adapter = new AdapterDatos3(listDatos);
                            recycler.setAdapter(adapter);

                            listDatos1.add(tipo);

                            AdapterDatos1 adapter2 = new AdapterDatos1(listDatos1);
                            recycler1.setAdapter(adapter2);


                            listDatos2.add(estado);
                            AdapterDatos2 adapter3 = new AdapterDatos2(listDatos2);
                            recycler2.setAdapter(adapter3);

                            adapter.setOnclickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {



                                    numero.setText(listDatos.get(recycler.getChildAdapterPosition(view)));


                                    tipoGlobal = tipo;
                                    numeroGlobal  = ordenNumero;


                                    Retrofit retrofit = new Retrofit.Builder()
                                            .baseUrl(direc)
                                            .addConverterFactory(GsonConverterFactory.create())
                                            .build();

                                    Conexion conexion = retrofit.create(Conexion.class);


                                    Call<Cuerpo3> call3 = conexion.getMantenimiento(login3);
                                    call3.enqueue(new Callback<Cuerpo3>() {
                                        @Override
                                        public void onResponse(Call<Cuerpo3> call, Response<Cuerpo3> response) {
                                            if(response.body() != null) {


                                                for(int e = 0; e<contactList.getRowset().size(); e++) {

                                                    ArrayList<Rowset> rowset1 = (ArrayList<Rowset>) contactList.getRowset();
                                                    String caso = rowset1.get(e).getCaso();
                                                    String descEquipo = rowset1.get(e).getDescEquipo();
                                                    String ordenNumero = rowset1.get(e).getOrdenNumero();
                                                    String tipo = rowset1.get(e).getOrdenTipo();
                                                    String fecha = rowset1.get(e).getFechaSolicitud();
                                                    String estado= rowset1.get(e).getEstado();
                                                    String idEquipo= rowset1.get(e).getIDEquipo();
                                                    String descripcion= rowset1.get(e).getDescripcion();
                                                    String fechaPro= rowset1.get(e).getFechaProgramacion();

                                                    String numeroOrden2 = numero.getText().toString();


                                                    if (rowset1.get(e).getOrdenNumero().equals(numeroOrden2)){


                                                        tipo1.setText(tipo );
                                                        numero1.setText(ordenNumero);
                                                        descripcion1.setText(descripcion);

                                                        tipoGlobal = tipo1.getText().toString();
                                                        numeroGlobal  = numero1.getText().toString();
                                                        descripcionGlobal = descripcion1.getText().toString();




                                                    }
                                                    if(rowset1.get(e).getOrdenNumero().equals(numeroOrden2)){


                                                        Intent i = new Intent(TercerActivity.this, CuartoActivity.class);
                                                        i.putExtra("dato2", tipo1.getText().toString());
                                                        i.putExtra("dato1", numero1.getText().toString());
                                                        i.putExtra("dato", descripcion1.getText().toString());




                                                        startActivity(i);



                                                    }
                                                }

                                            }
                                        }

                                        @Override
                                        public void onFailure(Call<Cuerpo3> call, Throwable t) {
                                            Toast.makeText(TercerActivity.this, "error ", Toast.LENGTH_LONG).show();

                                        }
                                    });



                                }
                            });


                        }

                    }
                    else{

                        Toast.makeText(TercerActivity.this, "ERROR 1458" + statusCode , Toast.LENGTH_LONG).show();
                        String dato2 = getIntent().getStringExtra("dato2");

                        prueba.setText(dato2);
                    }
                }

                @Override
                public void onFailure(Call<Cuerpo3> call2, Throwable t) {
                    Toast.makeText(TercerActivity.this, "error", Toast.LENGTH_LONG).show();

                }
            });
        }else if(dato.length()== 0 && dato1.length() == 0 && dato2.length() == 0 && dato3.length() == 0
                && dato4.length() == 0 && dato5.length() == 0 && dato6.length() == 0 && dato7.length() != 0){
            //ASIGNADO
            Cuerpo4 login3 = new Cuerpo4(usuarioGlobal, contraseñaGlobal,ResponsableGlobal,estadoCerradoGlobal,dato7,dato7);
            Call<Cuerpo4> call2 = conexion.getAsignado(login3);
            call2.enqueue(new Callback<Cuerpo4>() {
                @Override
                public void onResponse(Call<Cuerpo4> call2, Response<Cuerpo4> response) {
                    int statusCode = response.code();

                    if (response.isSuccessful()){

                        Mq0501xDatareq contactList =  response.body().getMq0501xDatareq();

                        listDatos =new ArrayList<>();
                        listDatos1 =new ArrayList<>();
                        listDatos2 =new ArrayList<>();


                        for(int e = 0; e<contactList.getRowset().size(); e++) {

                            ArrayList<Rowset> rowset1 = (ArrayList<Rowset>) contactList.getRowset();
                            String caso = rowset1.get(e).getCaso();
                            String descEquipo = rowset1.get(e).getDescEquipo();
                            String ordenNumero = rowset1.get(e).getOrdenNumero();
                            String tipo = rowset1.get(e).getOrdenTipo();
                            String fecha = rowset1.get(e).getFechaSolicitud();
                            String estado= rowset1.get(e).getEstado();
                            String idEquipo= rowset1.get(e).getIDEquipo();
                            String descripcion= rowset1.get(e).getDescripcion();
                            String fechaPro= rowset1.get(e).getFechaProgramacion();


                            listDatos.add( ordenNumero );
                            String content = ( descripcion +  "\n" +
                                    idEquipo + " | "   + descEquipo + "\n"+
                                    "F. Solicitada: " + fecha + "\n"+
                                    "F. Programada: " + fechaPro ) + "\n\n" ;

                            prueba.append(content);

                            AdapterDatos3 adapter = new AdapterDatos3(listDatos);
                            recycler.setAdapter(adapter);

                            listDatos1.add(tipo);

                            AdapterDatos1 adapter2 = new AdapterDatos1(listDatos1);
                            recycler1.setAdapter(adapter2);


                            listDatos2.add(estado);
                            AdapterDatos2 adapter3 = new AdapterDatos2(listDatos2);
                            recycler2.setAdapter(adapter3);

                            adapter.setOnclickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {



                                    numero.setText(listDatos.get(recycler.getChildAdapterPosition(view)));


                                    tipoGlobal = tipo;
                                    numeroGlobal  = ordenNumero;


                                    Retrofit retrofit = new Retrofit.Builder()
                                            .baseUrl(direc)
                                            .addConverterFactory(GsonConverterFactory.create())
                                            .build();

                                    Conexion conexion = retrofit.create(Conexion.class);


                                    Call<Cuerpo4> call3 = conexion.getAsignado(login3);
                                    call3.enqueue(new Callback<Cuerpo4>() {
                                        @Override
                                        public void onResponse(Call<Cuerpo4> call, Response<Cuerpo4> response) {
                                            if(response.body() != null) {


                                                for(int e = 0; e<contactList.getRowset().size(); e++) {

                                                    ArrayList<Rowset> rowset1 = (ArrayList<Rowset>) contactList.getRowset();
                                                    String caso = rowset1.get(e).getCaso();
                                                    String descEquipo = rowset1.get(e).getDescEquipo();
                                                    String ordenNumero = rowset1.get(e).getOrdenNumero();
                                                    String tipo = rowset1.get(e).getOrdenTipo();
                                                    String fecha = rowset1.get(e).getFechaSolicitud();
                                                    String estado= rowset1.get(e).getEstado();
                                                    String idEquipo= rowset1.get(e).getIDEquipo();
                                                    String descripcion= rowset1.get(e).getDescripcion();
                                                    String fechaPro= rowset1.get(e).getFechaProgramacion();

                                                    String numeroOrden2 = numero.getText().toString();


                                                    if (rowset1.get(e).getOrdenNumero().equals(numeroOrden2)){


                                                        tipo1.setText(tipo );
                                                        numero1.setText(ordenNumero);
                                                        descripcion1.setText(descripcion);

                                                        tipoGlobal = tipo1.getText().toString();
                                                        numeroGlobal  = numero1.getText().toString();
                                                        descripcionGlobal = descripcion1.getText().toString();




                                                    }
                                                    if(rowset1.get(e).getOrdenNumero().equals(numeroOrden2)){


                                                        Intent i = new Intent(TercerActivity.this, CuartoActivity.class);
                                                        i.putExtra("dato2", tipo1.getText().toString());
                                                        i.putExtra("dato1", numero1.getText().toString());
                                                        i.putExtra("dato", descripcion1.getText().toString());




                                                        startActivity(i);



                                                    }
                                                }

                                            }
                                        }

                                        @Override
                                        public void onFailure(Call<Cuerpo4> call, Throwable t) {
                                            Toast.makeText(TercerActivity.this, "error ", Toast.LENGTH_LONG).show();

                                        }
                                    });



                                }
                            });


                        }

                    }
                    else{

                        Toast.makeText(TercerActivity.this, "ERROR 1458" + statusCode , Toast.LENGTH_LONG).show();
                        String dato2 = getIntent().getStringExtra("dato2");

                        prueba.setText(dato2);
                    }
                }

                @Override
                public void onFailure(Call<Cuerpo4> call2, Throwable t) {
                    Toast.makeText(TercerActivity.this, "error", Toast.LENGTH_LONG).show();

                }
            });
        }






    }

    //CERRADAS

    public void Cerradas(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(direc)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Conexion conexion = retrofit.create(Conexion.class);


        //FILTRO TIPO DE ORDEN
        String dato = getIntent().getStringExtra("dato");
        //FILTRO TIPO DE FASE
        String dato1 = getIntent().getStringExtra("dato1");
        //FILTRO TIPO DE MANTENIMIENTO
        String dato2 = getIntent().getStringExtra("dato2");

        //FILTRO FECHA SOLICITADA
        String dato3 = getIntent().getStringExtra("dato3");
        //FILTRO FECHA SOLICITADA2
        String dato4 = getIntent().getStringExtra("dato4");

        //FILTRO FECHA PROGRAMADA
        String dato5 = getIntent().getStringExtra("dato5");
        //FILTRO FECHA PROGRAMADA2
        String dato6 = getIntent().getStringExtra("dato6");

        //FILTRO ASIGNADO
        String dato7 = getIntent().getStringExtra("dato7");

        Toast.makeText(TercerActivity.this , dato2, Toast.LENGTH_SHORT).show();

        // SI SE SELECCIONADA TIPO DE ORDEN, FASE, MANTENIMIENTO, FECHA SOLICITADA INI Y PROGAMA INI, ASIGNADO
        if(dato.length()!= 0  && dato1.length() != 0 && dato2.length() != 0 && dato3.length() != 0
                && dato5.length() != 0 && dato7.length() != 0 && dato4.length() != 0 && dato6.length() == 0){

            CuerpoCompleto login = new CuerpoCompleto(usuarioGlobal, contraseñaGlobal,estadoCerradoGlobal,ResponsableGlobal,
                    dato,dato,dato1,dato1,dato2,dato2,dato3,dato5,dato7,dato7 );

            Call<CuerpoCompleto> call2 = conexion.getCompletoIniCerradas(login);
            call2.enqueue(new Callback<CuerpoCompleto>() {
                @Override
                public void onResponse(Call<CuerpoCompleto> call, Response<CuerpoCompleto> response) {


                    int statusCode = response.code();

                    if (response.isSuccessful()){

                        Mq0501cDatareq contactList =  response.body().getMq0501cDatareq();

                        listDatos =new ArrayList<>();
                        listDatos1 =new ArrayList<>();
                        listDatos2 =new ArrayList<>();


                        for(int e = 0; e<contactList.getRowset().size(); e++) {

                            ArrayList<Rowset> rowset1 = (ArrayList<Rowset>) contactList.getRowset();
                            String caso = rowset1.get(e).getCaso();
                            String descEquipo = rowset1.get(e).getDescEquipo();
                            String ordenNumero = rowset1.get(e).getOrdenNumero();
                            String tipo = rowset1.get(e).getOrdenTipo();
                            String fecha = rowset1.get(e).getFechaSolicitud();
                            String estado= rowset1.get(e).getEstado();
                            String idEquipo= rowset1.get(e).getIDEquipo();
                            String descripcion= rowset1.get(e).getDescripcion();
                            String fechaPro= rowset1.get(e).getFechaProgramacion();


                            listDatos.add( ordenNumero );
                            String content = ( descripcion +  "\n" +
                                    idEquipo + " | "   + descEquipo + "\n"+
                                    "F. Solicitada: " + fecha + "\n"+
                                    "F. Programada: " + fechaPro ) + "\n\n" ;

                            prueba.append(content);

                            AdapterDatos3 adapter = new AdapterDatos3(listDatos);
                            recycler.setAdapter(adapter);

                            listDatos1.add(tipo);

                            AdapterDatos1 adapter2 = new AdapterDatos1(listDatos1);
                            recycler1.setAdapter(adapter2);


                            listDatos2.add(estado);
                            AdapterDatos2 adapter3 = new AdapterDatos2(listDatos2);
                            recycler2.setAdapter(adapter3);

                            adapter.setOnclickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {



                                    numero.setText(listDatos.get(recycler.getChildAdapterPosition(view)));


                                    tipoGlobal = tipo;
                                    numeroGlobal  = ordenNumero;


                                    Retrofit retrofit = new Retrofit.Builder()
                                            .baseUrl(direc)
                                            .addConverterFactory(GsonConverterFactory.create())
                                            .build();

                                    Conexion conexion = retrofit.create(Conexion.class);


                                    Call<CuerpoCompleto> call3 = conexion.getCompletoIniCerradas(login);
                                    call3.enqueue(new Callback<CuerpoCompleto>() {
                                        @Override
                                        public void onResponse(Call<CuerpoCompleto> call, Response<CuerpoCompleto> response) {
                                            if(response.body() != null) {


                                                for(int e = 0; e<contactList.getRowset().size(); e++) {

                                                    ArrayList<Rowset> rowset1 = (ArrayList<Rowset>) contactList.getRowset();
                                                    String caso = rowset1.get(e).getCaso();
                                                    String descEquipo = rowset1.get(e).getDescEquipo();
                                                    String ordenNumero = rowset1.get(e).getOrdenNumero();
                                                    String tipo = rowset1.get(e).getOrdenTipo();
                                                    String fecha = rowset1.get(e).getFechaSolicitud();
                                                    String estado= rowset1.get(e).getEstado();
                                                    String idEquipo= rowset1.get(e).getIDEquipo();
                                                    String descripcion= rowset1.get(e).getDescripcion();
                                                    String fechaPro= rowset1.get(e).getFechaProgramacion();

                                                    String numeroOrden2 = numero.getText().toString();


                                                    if (rowset1.get(e).getOrdenNumero().equals(numeroOrden2)){


                                                        tipo1.setText(tipo );
                                                        numero1.setText(ordenNumero);
                                                        descripcion1.setText(descripcion);

                                                        tipoGlobal = tipo1.getText().toString();
                                                        numeroGlobal  = numero1.getText().toString();
                                                        descripcionGlobal = descripcion1.getText().toString();




                                                    }
                                                    if(rowset1.get(e).getOrdenNumero().equals(numeroOrden2)){


                                                        Intent i = new Intent(TercerActivity.this, CuartoActivity.class);
                                                        i.putExtra("dato2", tipo1.getText().toString());
                                                        i.putExtra("dato1", numero1.getText().toString());
                                                        i.putExtra("dato", descripcion1.getText().toString());




                                                        startActivity(i);



                                                    }
                                                }

                                            }
                                        }

                                        @Override
                                        public void onFailure(Call<CuerpoCompleto> call, Throwable t) {
                                            Toast.makeText(TercerActivity.this, "error ", Toast.LENGTH_LONG).show();

                                        }
                                    });



                                }
                            });


                        }

                    }
                    else{

                        Toast.makeText(TercerActivity.this, "ERROR " + statusCode , Toast.LENGTH_LONG).show();
                        String dato2 = getIntent().getStringExtra("dato2");

                        prueba.setText(dato2);
                    }
                }
                @Override
                public void onFailure(Call<CuerpoCompleto> call, Throwable t) {
                    Toast.makeText(TercerActivity.this, "error ", Toast.LENGTH_LONG).show();

                }
            });


        }else if(dato.length()!= 0 && dato1.length() == 0 && dato2.length() == 0 && dato3.length() == 0 && dato4.length() == 0
                && dato5.length() == 0 && dato6.length() == 0 && dato7.length() == 0){

            //TIPO DE ORDEN
            CuerpoCerrado login3 = new CuerpoCerrado(usuarioGlobal, contraseñaGlobal,ResponsableGlobal,estadoCerradoGlobal,dato,dato);
            Call<CuerpoCerrado> call9 = conexion.getCerradas(login3);
            call9.enqueue(new Callback<CuerpoCerrado>() {
                @Override
                public void onResponse(Call<CuerpoCerrado> call9, Response<CuerpoCerrado> response) {
                    int statusCode = response.code();

                    if (response.isSuccessful()){

                        Mq0501cDatareq contactList =  response.body().getMq0501cDatareq();

                        listDatos =new ArrayList<>();
                        listDatos1 =new ArrayList<>();
                        listDatos2 =new ArrayList<>();




                        for(int e = 0; e<contactList.getRowset().size(); e++) {

                            ArrayList<Rowset> rowset1 = (ArrayList<Rowset>) contactList.getRowset();
                            String caso = rowset1.get(e).getCaso();
                            String descEquipo = rowset1.get(e).getDescEquipo();
                            String ordenNumero = rowset1.get(e).getOrdenNumero();
                            String tipo = rowset1.get(e).getOrdenTipo();
                            String fecha = rowset1.get(e).getFechaSolicitud();
                            String estado= rowset1.get(e).getEstado();
                            String idEquipo= rowset1.get(e).getIDEquipo();
                            String descripcion= rowset1.get(e).getDescripcion();
                            String fechaPro= rowset1.get(e).getFechaProgramacion();


                            listDatos.add( ordenNumero );
                            String content = ( descripcion +  "\n" +
                                    idEquipo + " | "   + descEquipo + "\n"+
                                    "F. Solicitada: " + fecha + "\n"+
                                    "F. Programada: " + fechaPro ) + "\n\n" ;
                            prueba.append(content);

                            AdapterDatos3 adapter = new AdapterDatos3(listDatos);
                            recycler.setAdapter(adapter);

                            listDatos1.add(" "+ "\n" +
                                    " "+   tipo + "\n" +
                                    "  " +"\n\n");
                            AdapterDatos1 adapter2 = new AdapterDatos1(listDatos1);
                            recycler1.setAdapter(adapter2);


                            listDatos2.add(  estado  );
                            AdapterDatos2 adapter3 = new AdapterDatos2(listDatos2);
                            recycler2.setAdapter(adapter3);

                            adapter.setOnclickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    numero.setText(listDatos.get(recycler.getChildAdapterPosition(view)));


                                    tipoGlobal = tipo;
                                    numeroGlobal  = ordenNumero;


                                    Retrofit retrofit = new Retrofit.Builder()
                                            .baseUrl(direc)
                                            .addConverterFactory(GsonConverterFactory.create())
                                            .build();

                                    Conexion conexion = retrofit.create(Conexion.class);


                                    Call<CuerpoCerrado> call3 = conexion.getCerradas(login3);
                                    call3.enqueue(new Callback<CuerpoCerrado>() {
                                        @Override
                                        public void onResponse(Call<CuerpoCerrado> call, Response<CuerpoCerrado> response) {
                                            if(response.body() != null) {


                                                for(int e = 0; e<contactList.getRowset().size(); e++) {

                                                    ArrayList<Rowset> rowset1 = (ArrayList<Rowset>) contactList.getRowset();
                                                    String caso = rowset1.get(e).getCaso();
                                                    String descEquipo = rowset1.get(e).getDescEquipo();
                                                    String ordenNumero = rowset1.get(e).getOrdenNumero();
                                                    String tipo = rowset1.get(e).getOrdenTipo();
                                                    String fecha = rowset1.get(e).getFechaSolicitud();
                                                    String estado= rowset1.get(e).getEstado();
                                                    String idEquipo= rowset1.get(e).getIDEquipo();
                                                    String descripcion= rowset1.get(e).getDescripcion();
                                                    String fechaPro= rowset1.get(e).getFechaProgramacion();

                                                    String numeroOrden2 = numero.getText().toString();


                                                    if (rowset1.get(e).getOrdenNumero().equals(numeroOrden2)){


                                                        tipo1.setText(tipo );
                                                        numero1.setText(ordenNumero);
                                                        descripcion1.setText(descripcion);

                                                        tipoGlobal = tipo1.getText().toString();
                                                        numeroGlobal  = numero1.getText().toString();


                                                    }
                                                    if(rowset1.get(e).getOrdenNumero().equals(numeroOrden2)){


                                                        Intent i = new Intent(TercerActivity.this, CuartoActivity.class);
                                                        i.putExtra("dato2", tipo1.getText().toString());
                                                        i.putExtra("dato1", numero1.getText().toString());
                                                        i.putExtra("dato", descripcion1.getText().toString());




                                                        startActivity(i);



                                                    }
                                                }

                                            }
                                        }

                                        @Override
                                        public void onFailure(Call<CuerpoCerrado> call, Throwable t) {
                                            Toast.makeText(TercerActivity.this, "error ", Toast.LENGTH_LONG).show();

                                        }
                                    });



                                }
                            });


                        }

                    }
                    else{

                        Toast.makeText(TercerActivity.this, "ERROR CHEQUEAR FILTROS" + statusCode , Toast.LENGTH_LONG).show();
                        String dato2 = getIntent().getStringExtra("dato2");

                        prueba.setText(dato2);
                    }
                }

                @Override
                public void onFailure(Call<CuerpoCerrado> call2, Throwable t) {
                    Toast.makeText(TercerActivity.this, "error ", Toast.LENGTH_LONG).show();

                }
            });
        } else if(dato.length()!= 0 && dato1.length() != 0 && dato2.length() == 0 && dato3.length() == 0 && dato4.length() == 0
                && dato5.length() == 0 && dato6.length() == 0 && dato7.length() == 0){

            //TIPO DE ORDEN y Fase
            CuerpoCerrado login3 = new CuerpoCerrado(usuarioGlobal, contraseñaGlobal,ResponsableGlobal,estadoCerradoGlobal,dato,dato);
            Call<CuerpoCerrado> call9 = conexion.getCerradas(login3);
            call9.enqueue(new Callback<CuerpoCerrado>() {
                @Override
                public void onResponse(Call<CuerpoCerrado> call9, Response<CuerpoCerrado> response) {
                    int statusCode = response.code();

                    if (response.isSuccessful()){

                        Mq0501cDatareq contactList =  response.body().getMq0501cDatareq();

                        listDatos =new ArrayList<>();
                        listDatos1 =new ArrayList<>();
                        listDatos2 =new ArrayList<>();




                        for(int e = 0; e<contactList.getRowset().size(); e++) {

                            ArrayList<Rowset> rowset1 = (ArrayList<Rowset>) contactList.getRowset();
                            String caso = rowset1.get(e).getCaso();
                            String descEquipo = rowset1.get(e).getDescEquipo();
                            String ordenNumero = rowset1.get(e).getOrdenNumero();
                            String tipo = rowset1.get(e).getOrdenTipo();
                            String fecha = rowset1.get(e).getFechaSolicitud();
                            String estado= rowset1.get(e).getEstado();
                            String idEquipo= rowset1.get(e).getIDEquipo();
                            String descripcion= rowset1.get(e).getDescripcion();
                            String fechaPro= rowset1.get(e).getFechaProgramacion();


                            listDatos.add( ordenNumero );
                            String content = ( descripcion +  "\n" +
                                    idEquipo + " | "   + descEquipo + "\n"+
                                    "F. Solicitada: " + fecha + "\n"+
                                    "F. Programada: " + fechaPro ) + "\n\n" ;
                            prueba.append(content);

                            AdapterDatos3 adapter = new AdapterDatos3(listDatos);
                            recycler.setAdapter(adapter);

                            listDatos1.add(" "+ "\n" +
                                    " "+   tipo + "\n" +
                                    "  " +"\n\n");
                            AdapterDatos1 adapter2 = new AdapterDatos1(listDatos1);
                            recycler1.setAdapter(adapter2);


                            listDatos2.add(  estado  );
                            AdapterDatos2 adapter3 = new AdapterDatos2(listDatos2);
                            recycler2.setAdapter(adapter3);

                            adapter.setOnclickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    numero.setText(listDatos.get(recycler.getChildAdapterPosition(view)));


                                    tipoGlobal = tipo;
                                    numeroGlobal  = ordenNumero;


                                    Retrofit retrofit = new Retrofit.Builder()
                                            .baseUrl(direc)
                                            .addConverterFactory(GsonConverterFactory.create())
                                            .build();

                                    Conexion conexion = retrofit.create(Conexion.class);


                                    Call<CuerpoCerrado> call3 = conexion.getCerradas(login3);
                                    call3.enqueue(new Callback<CuerpoCerrado>() {
                                        @Override
                                        public void onResponse(Call<CuerpoCerrado> call, Response<CuerpoCerrado> response) {
                                            if(response.body() != null) {


                                                for(int e = 0; e<contactList.getRowset().size(); e++) {

                                                    ArrayList<Rowset> rowset1 = (ArrayList<Rowset>) contactList.getRowset();
                                                    String caso = rowset1.get(e).getCaso();
                                                    String descEquipo = rowset1.get(e).getDescEquipo();
                                                    String ordenNumero = rowset1.get(e).getOrdenNumero();
                                                    String tipo = rowset1.get(e).getOrdenTipo();
                                                    String fecha = rowset1.get(e).getFechaSolicitud();
                                                    String estado= rowset1.get(e).getEstado();
                                                    String idEquipo= rowset1.get(e).getIDEquipo();
                                                    String descripcion= rowset1.get(e).getDescripcion();
                                                    String fechaPro= rowset1.get(e).getFechaProgramacion();

                                                    String numeroOrden2 = numero.getText().toString();


                                                    if (rowset1.get(e).getOrdenNumero().equals(numeroOrden2)){


                                                        tipo1.setText(tipo );
                                                        numero1.setText(ordenNumero);
                                                        descripcion1.setText(descripcion);

                                                        tipoGlobal = tipo1.getText().toString();
                                                        numeroGlobal  = numero1.getText().toString();


                                                    }
                                                    if(rowset1.get(e).getOrdenNumero().equals(numeroOrden2)){


                                                        Intent i = new Intent(TercerActivity.this, CuartoActivity.class);
                                                        i.putExtra("dato2", tipo1.getText().toString());
                                                        i.putExtra("dato1", numero1.getText().toString());
                                                        i.putExtra("dato", descripcion1.getText().toString());




                                                        startActivity(i);



                                                    }
                                                }

                                            }
                                        }

                                        @Override
                                        public void onFailure(Call<CuerpoCerrado> call, Throwable t) {
                                            Toast.makeText(TercerActivity.this, "error ", Toast.LENGTH_LONG).show();

                                        }
                                    });



                                }
                            });


                        }

                    }
                    else{

                        Toast.makeText(TercerActivity.this, "ERROR CHEQUEAR FILTROS" + statusCode , Toast.LENGTH_LONG).show();
                        String dato2 = getIntent().getStringExtra("dato2");

                        prueba.setText(dato2);
                    }
                }

                @Override
                public void onFailure(Call<CuerpoCerrado> call2, Throwable t) {
                    Toast.makeText(TercerActivity.this, "error ", Toast.LENGTH_LONG).show();

                }
            });
        } else if(dato.length()!= 0 && dato1.length() != 0 && dato2.length() != 0 && dato3.length() == 0 && dato4.length() == 0
                && dato5.length() == 0 && dato6.length() == 0 && dato7.length() == 0){

            //TIPO DE ORDEN,FASE Y MANTENIMIENTO
            CuerpoCerrado login3 = new CuerpoCerrado(usuarioGlobal, contraseñaGlobal,ResponsableGlobal,estadoCerradoGlobal,dato,dato,dato1,dato1,dato,dato2);
            Call<CuerpoCerrado> call9 = conexion.getCerradas(login3);
            call9.enqueue(new Callback<CuerpoCerrado>() {
                @Override
                public void onResponse(Call<CuerpoCerrado> call9, Response<CuerpoCerrado> response) {
                    int statusCode = response.code();

                    if (response.isSuccessful()){

                        Mq0501cDatareq contactList =  response.body().getMq0501cDatareq();

                        listDatos =new ArrayList<>();
                        listDatos1 =new ArrayList<>();
                        listDatos2 =new ArrayList<>();




                        for(int e = 0; e<contactList.getRowset().size(); e++) {

                            ArrayList<Rowset> rowset1 = (ArrayList<Rowset>) contactList.getRowset();
                            String caso = rowset1.get(e).getCaso();
                            String descEquipo = rowset1.get(e).getDescEquipo();
                            String ordenNumero = rowset1.get(e).getOrdenNumero();
                            String tipo = rowset1.get(e).getOrdenTipo();
                            String fecha = rowset1.get(e).getFechaSolicitud();
                            String estado= rowset1.get(e).getEstado();
                            String idEquipo= rowset1.get(e).getIDEquipo();
                            String descripcion= rowset1.get(e).getDescripcion();
                            String fechaPro= rowset1.get(e).getFechaProgramacion();


                            listDatos.add( ordenNumero );
                            String content = ( descripcion +  "\n" +
                                    idEquipo + " | "   + descEquipo + "\n"+
                                    "F. Solicitada: " + fecha + "\n"+
                                    "F. Programada: " + fechaPro ) + "\n\n" ;
                            prueba.append(content);

                            AdapterDatos3 adapter = new AdapterDatos3(listDatos);
                            recycler.setAdapter(adapter);

                            listDatos1.add(" "+ "\n" +
                                    " "+   tipo + "\n" +
                                    "  " +"\n\n");
                            AdapterDatos1 adapter2 = new AdapterDatos1(listDatos1);
                            recycler1.setAdapter(adapter2);


                            listDatos2.add(  estado  );
                            AdapterDatos2 adapter3 = new AdapterDatos2(listDatos2);
                            recycler2.setAdapter(adapter3);

                            adapter.setOnclickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    numero.setText(listDatos.get(recycler.getChildAdapterPosition(view)));


                                    tipoGlobal = tipo;
                                    numeroGlobal  = ordenNumero;


                                    Retrofit retrofit = new Retrofit.Builder()
                                            .baseUrl(direc)
                                            .addConverterFactory(GsonConverterFactory.create())
                                            .build();

                                    Conexion conexion = retrofit.create(Conexion.class);


                                    Call<CuerpoCerrado> call3 = conexion.getCerradas(login3);
                                    call3.enqueue(new Callback<CuerpoCerrado>() {
                                        @Override
                                        public void onResponse(Call<CuerpoCerrado> call, Response<CuerpoCerrado> response) {
                                            if(response.body() != null) {


                                                for(int e = 0; e<contactList.getRowset().size(); e++) {

                                                    ArrayList<Rowset> rowset1 = (ArrayList<Rowset>) contactList.getRowset();
                                                    String caso = rowset1.get(e).getCaso();
                                                    String descEquipo = rowset1.get(e).getDescEquipo();
                                                    String ordenNumero = rowset1.get(e).getOrdenNumero();
                                                    String tipo = rowset1.get(e).getOrdenTipo();
                                                    String fecha = rowset1.get(e).getFechaSolicitud();
                                                    String estado= rowset1.get(e).getEstado();
                                                    String idEquipo= rowset1.get(e).getIDEquipo();
                                                    String descripcion= rowset1.get(e).getDescripcion();
                                                    String fechaPro= rowset1.get(e).getFechaProgramacion();

                                                    String numeroOrden2 = numero.getText().toString();


                                                    if (rowset1.get(e).getOrdenNumero().equals(numeroOrden2)){


                                                        tipo1.setText(tipo );
                                                        numero1.setText(ordenNumero);
                                                        descripcion1.setText(descripcion);

                                                        tipoGlobal = tipo1.getText().toString();
                                                        numeroGlobal  = numero1.getText().toString();


                                                    }
                                                    if(rowset1.get(e).getOrdenNumero().equals(numeroOrden2)){


                                                        Intent i = new Intent(TercerActivity.this, CuartoActivity.class);
                                                        i.putExtra("dato2", tipo1.getText().toString());
                                                        i.putExtra("dato1", numero1.getText().toString());
                                                        i.putExtra("dato", descripcion1.getText().toString());




                                                        startActivity(i);



                                                    }
                                                }

                                            }
                                        }

                                        @Override
                                        public void onFailure(Call<CuerpoCerrado> call, Throwable t) {
                                            Toast.makeText(TercerActivity.this, "error ", Toast.LENGTH_LONG).show();

                                        }
                                    });



                                }
                            });


                        }

                    }
                    else{

                        Toast.makeText(TercerActivity.this, "ERROR CHEQUEAR FILTROS" + statusCode , Toast.LENGTH_LONG).show();
                        String dato2 = getIntent().getStringExtra("dato2");

                        prueba.setText(dato2);
                    }
                }

                @Override
                public void onFailure(Call<CuerpoCerrado> call2, Throwable t) {
                    Toast.makeText(TercerActivity.this, "error ", Toast.LENGTH_LONG).show();

                }
            });
        } else if(dato.length()!= 0 && dato1.length() != 0 && dato2.length() != 0 && dato3.length() == 0 && dato4.length() == 0
                && dato5.length() == 0 && dato6.length() == 0 && dato7.length() != 0){

            //TIPO DE ORDEN,FASE Y MANTENIMIENTO y asignado
            CuerpoCerrado login3 = new CuerpoCerrado(usuarioGlobal, contraseñaGlobal,ResponsableGlobal,estadoCerradoGlobal,dato,dato,dato1,dato1,dato,dato2,dato7,dato7);
            Call<CuerpoCerrado> call9 = conexion.getCerradas(login3);
            call9.enqueue(new Callback<CuerpoCerrado>() {
                @Override
                public void onResponse(Call<CuerpoCerrado> call9, Response<CuerpoCerrado> response) {
                    int statusCode = response.code();

                    if (response.isSuccessful()){

                        Mq0501cDatareq contactList =  response.body().getMq0501cDatareq();

                        listDatos =new ArrayList<>();
                        listDatos1 =new ArrayList<>();
                        listDatos2 =new ArrayList<>();




                        for(int e = 0; e<contactList.getRowset().size(); e++) {

                            ArrayList<Rowset> rowset1 = (ArrayList<Rowset>) contactList.getRowset();
                            String caso = rowset1.get(e).getCaso();
                            String descEquipo = rowset1.get(e).getDescEquipo();
                            String ordenNumero = rowset1.get(e).getOrdenNumero();
                            String tipo = rowset1.get(e).getOrdenTipo();
                            String fecha = rowset1.get(e).getFechaSolicitud();
                            String estado= rowset1.get(e).getEstado();
                            String idEquipo= rowset1.get(e).getIDEquipo();
                            String descripcion= rowset1.get(e).getDescripcion();
                            String fechaPro= rowset1.get(e).getFechaProgramacion();


                            listDatos.add( ordenNumero );
                            String content = ( descripcion +  "\n" +
                                    idEquipo + " | "   + descEquipo + "\n"+
                                    "F. Solicitada: " + fecha + "\n"+
                                    "F. Programada: " + fechaPro ) + "\n\n" ;
                            prueba.append(content);

                            AdapterDatos3 adapter = new AdapterDatos3(listDatos);
                            recycler.setAdapter(adapter);

                            listDatos1.add(" "+ "\n" +
                                    " "+   tipo + "\n" +
                                    "  " +"\n\n");
                            AdapterDatos1 adapter2 = new AdapterDatos1(listDatos1);
                            recycler1.setAdapter(adapter2);


                            listDatos2.add(  estado  );
                            AdapterDatos2 adapter3 = new AdapterDatos2(listDatos2);
                            recycler2.setAdapter(adapter3);

                            adapter.setOnclickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    numero.setText(listDatos.get(recycler.getChildAdapterPosition(view)));


                                    tipoGlobal = tipo;
                                    numeroGlobal  = ordenNumero;


                                    Retrofit retrofit = new Retrofit.Builder()
                                            .baseUrl(direc)
                                            .addConverterFactory(GsonConverterFactory.create())
                                            .build();

                                    Conexion conexion = retrofit.create(Conexion.class);


                                    Call<CuerpoCerrado> call3 = conexion.getCerradas(login3);
                                    call3.enqueue(new Callback<CuerpoCerrado>() {
                                        @Override
                                        public void onResponse(Call<CuerpoCerrado> call, Response<CuerpoCerrado> response) {
                                            if(response.body() != null) {


                                                for(int e = 0; e<contactList.getRowset().size(); e++) {

                                                    ArrayList<Rowset> rowset1 = (ArrayList<Rowset>) contactList.getRowset();
                                                    String caso = rowset1.get(e).getCaso();
                                                    String descEquipo = rowset1.get(e).getDescEquipo();
                                                    String ordenNumero = rowset1.get(e).getOrdenNumero();
                                                    String tipo = rowset1.get(e).getOrdenTipo();
                                                    String fecha = rowset1.get(e).getFechaSolicitud();
                                                    String estado= rowset1.get(e).getEstado();
                                                    String idEquipo= rowset1.get(e).getIDEquipo();
                                                    String descripcion= rowset1.get(e).getDescripcion();
                                                    String fechaPro= rowset1.get(e).getFechaProgramacion();

                                                    String numeroOrden2 = numero.getText().toString();


                                                    if (rowset1.get(e).getOrdenNumero().equals(numeroOrden2)){


                                                        tipo1.setText(tipo );
                                                        numero1.setText(ordenNumero);
                                                        descripcion1.setText(descripcion);

                                                        tipoGlobal = tipo1.getText().toString();
                                                        numeroGlobal  = numero1.getText().toString();


                                                    }
                                                    if(rowset1.get(e).getOrdenNumero().equals(numeroOrden2)){


                                                        Intent i = new Intent(TercerActivity.this, CuartoActivity.class);
                                                        i.putExtra("dato2", tipo1.getText().toString());
                                                        i.putExtra("dato1", numero1.getText().toString());
                                                        i.putExtra("dato", descripcion1.getText().toString());




                                                        startActivity(i);



                                                    }
                                                }

                                            }
                                        }

                                        @Override
                                        public void onFailure(Call<CuerpoCerrado> call, Throwable t) {
                                            Toast.makeText(TercerActivity.this, "error ", Toast.LENGTH_LONG).show();

                                        }
                                    });



                                }
                            });


                        }

                    }
                    else{

                        Toast.makeText(TercerActivity.this, "ERROR CHEQUEAR FILTROS" + statusCode , Toast.LENGTH_LONG).show();
                        String dato2 = getIntent().getStringExtra("dato2");

                        prueba.setText(dato2);
                    }
                }

                @Override
                public void onFailure(Call<CuerpoCerrado> call2, Throwable t) {
                    Toast.makeText(TercerActivity.this, "error ", Toast.LENGTH_LONG).show();

                }
            });
        } else if(dato.length()!= 0 && dato1.length() != 0 && dato2.length() != 0 && dato3.length() != 0 && dato4.length() == 0
                && dato5.length() == 0 && dato6.length() == 0 && dato7.length() != 0){

            //TIPO DE ORDEN,FASE Y MANTENIMIENTO,asignado y fecha solictada inicial
            CuerpoCerrado login3 = new CuerpoCerrado(usuarioGlobal, contraseñaGlobal,ResponsableGlobal,estadoCerradoGlobal,dato,dato,dato1,dato1,dato,dato2,dato7,dato7,dato3);
            Call<CuerpoCerrado> call9 = conexion.getCerradas(login3);
            call9.enqueue(new Callback<CuerpoCerrado>() {
                @Override
                public void onResponse(Call<CuerpoCerrado> call9, Response<CuerpoCerrado> response) {
                    int statusCode = response.code();

                    if (response.isSuccessful()){

                        Mq0501cDatareq contactList =  response.body().getMq0501cDatareq();

                        listDatos =new ArrayList<>();
                        listDatos1 =new ArrayList<>();
                        listDatos2 =new ArrayList<>();




                        for(int e = 0; e<contactList.getRowset().size(); e++) {

                            ArrayList<Rowset> rowset1 = (ArrayList<Rowset>) contactList.getRowset();
                            String caso = rowset1.get(e).getCaso();
                            String descEquipo = rowset1.get(e).getDescEquipo();
                            String ordenNumero = rowset1.get(e).getOrdenNumero();
                            String tipo = rowset1.get(e).getOrdenTipo();
                            String fecha = rowset1.get(e).getFechaSolicitud();
                            String estado= rowset1.get(e).getEstado();
                            String idEquipo= rowset1.get(e).getIDEquipo();
                            String descripcion= rowset1.get(e).getDescripcion();
                            String fechaPro= rowset1.get(e).getFechaProgramacion();


                            listDatos.add( ordenNumero );
                            String content = ( descripcion +  "\n" +
                                    idEquipo + " | "   + descEquipo + "\n"+
                                    "F. Solicitada: " + fecha + "\n"+
                                    "F. Programada: " + fechaPro ) + "\n\n" ;
                            prueba.append(content);

                            AdapterDatos3 adapter = new AdapterDatos3(listDatos);
                            recycler.setAdapter(adapter);

                            listDatos1.add(" "+ "\n" +
                                    " "+   tipo + "\n" +
                                    "  " +"\n\n");
                            AdapterDatos1 adapter2 = new AdapterDatos1(listDatos1);
                            recycler1.setAdapter(adapter2);


                            listDatos2.add(  estado  );
                            AdapterDatos2 adapter3 = new AdapterDatos2(listDatos2);
                            recycler2.setAdapter(adapter3);

                            adapter.setOnclickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    numero.setText(listDatos.get(recycler.getChildAdapterPosition(view)));


                                    tipoGlobal = tipo;
                                    numeroGlobal  = ordenNumero;


                                    Retrofit retrofit = new Retrofit.Builder()
                                            .baseUrl(direc)
                                            .addConverterFactory(GsonConverterFactory.create())
                                            .build();

                                    Conexion conexion = retrofit.create(Conexion.class);


                                    Call<CuerpoCerrado> call3 = conexion.getCerradas(login3);
                                    call3.enqueue(new Callback<CuerpoCerrado>() {
                                        @Override
                                        public void onResponse(Call<CuerpoCerrado> call, Response<CuerpoCerrado> response) {
                                            if(response.body() != null) {


                                                for(int e = 0; e<contactList.getRowset().size(); e++) {

                                                    ArrayList<Rowset> rowset1 = (ArrayList<Rowset>) contactList.getRowset();
                                                    String caso = rowset1.get(e).getCaso();
                                                    String descEquipo = rowset1.get(e).getDescEquipo();
                                                    String ordenNumero = rowset1.get(e).getOrdenNumero();
                                                    String tipo = rowset1.get(e).getOrdenTipo();
                                                    String fecha = rowset1.get(e).getFechaSolicitud();
                                                    String estado= rowset1.get(e).getEstado();
                                                    String idEquipo= rowset1.get(e).getIDEquipo();
                                                    String descripcion= rowset1.get(e).getDescripcion();
                                                    String fechaPro= rowset1.get(e).getFechaProgramacion();

                                                    String numeroOrden2 = numero.getText().toString();


                                                    if (rowset1.get(e).getOrdenNumero().equals(numeroOrden2)){


                                                        tipo1.setText(tipo );
                                                        numero1.setText(ordenNumero);
                                                        descripcion1.setText(descripcion);

                                                        tipoGlobal = tipo1.getText().toString();
                                                        numeroGlobal  = numero1.getText().toString();


                                                    }
                                                    if(rowset1.get(e).getOrdenNumero().equals(numeroOrden2)){


                                                        Intent i = new Intent(TercerActivity.this, CuartoActivity.class);
                                                        i.putExtra("dato2", tipo1.getText().toString());
                                                        i.putExtra("dato1", numero1.getText().toString());
                                                        i.putExtra("dato", descripcion1.getText().toString());




                                                        startActivity(i);



                                                    }
                                                }

                                            }
                                        }

                                        @Override
                                        public void onFailure(Call<CuerpoCerrado> call, Throwable t) {
                                            Toast.makeText(TercerActivity.this, "error ", Toast.LENGTH_LONG).show();

                                        }
                                    });



                                }
                            });


                        }

                    }
                    else{

                        Toast.makeText(TercerActivity.this, "ERROR CHEQUEAR FILTROS" + statusCode , Toast.LENGTH_LONG).show();
                        String dato2 = getIntent().getStringExtra("dato2");

                        prueba.setText(dato2);
                    }
                }

                @Override
                public void onFailure(Call<CuerpoCerrado> call2, Throwable t) {
                    Toast.makeText(TercerActivity.this, "error ", Toast.LENGTH_LONG).show();

                }
            });
        }else if(dato.length()== 0 && dato1.length() != 0 && dato2.length() == 0 && dato3.length() == 0 && dato4.length() == 0
                && dato5.length() == 0 && dato6.length() == 0 && dato7.length() == 0){

            //FASE
            CuerpoCompleto login3 = new CuerpoCompleto(usuarioGlobal, contraseñaGlobal,ResponsableGlobal,estadoCerradoGlobal,dato1,dato1);
            Call<CuerpoCompleto> call9 = conexion.getCompletoIniCerradas(login3);
            call9.enqueue(new Callback<CuerpoCompleto>() {
                @Override
                public void onResponse(Call<CuerpoCompleto> call9, Response<CuerpoCompleto> response) {
                    int statusCode = response.code();

                    if (response.isSuccessful()){

                        Mq0501cDatareq contactList =  response.body().getMq0501cDatareq();

                        listDatos =new ArrayList<>();
                        listDatos1 =new ArrayList<>();
                        listDatos2 =new ArrayList<>();




                        for(int e = 0; e<contactList.getRowset().size(); e++) {

                            ArrayList<Rowset> rowset1 = (ArrayList<Rowset>) contactList.getRowset();
                            String caso = rowset1.get(e).getCaso();
                            String descEquipo = rowset1.get(e).getDescEquipo();
                            String ordenNumero = rowset1.get(e).getOrdenNumero();
                            String tipo = rowset1.get(e).getOrdenTipo();
                            String fecha = rowset1.get(e).getFechaSolicitud();
                            String estado= rowset1.get(e).getEstado();
                            String idEquipo= rowset1.get(e).getIDEquipo();
                            String descripcion= rowset1.get(e).getDescripcion();
                            String fechaPro= rowset1.get(e).getFechaProgramacion();


                            listDatos.add( ordenNumero );
                            String content = ( descripcion +  "\n" +
                                    idEquipo + " | "   + descEquipo + "\n"+
                                    "F. Solicitada: " + fecha + "\n"+
                                    "F. Programada: " + fechaPro ) + "\n\n" ;
                            prueba.append(content);

                            AdapterDatos3 adapter = new AdapterDatos3(listDatos);
                            recycler.setAdapter(adapter);

                            listDatos1.add(" "+ "\n" +
                                    " "+   tipo + "\n" +
                                    "  " +"\n\n");
                            AdapterDatos1 adapter2 = new AdapterDatos1(listDatos1);
                            recycler1.setAdapter(adapter2);


                            listDatos2.add(  estado  );
                            AdapterDatos2 adapter3 = new AdapterDatos2(listDatos2);
                            recycler2.setAdapter(adapter3);

                            adapter.setOnclickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    numero.setText(listDatos.get(recycler.getChildAdapterPosition(view)));


                                    tipoGlobal = tipo;
                                    numeroGlobal  = ordenNumero;


                                    Retrofit retrofit = new Retrofit.Builder()
                                            .baseUrl(direc)
                                            .addConverterFactory(GsonConverterFactory.create())
                                            .build();

                                    Conexion conexion = retrofit.create(Conexion.class);


                                    Call<CuerpoCompleto> call3 = conexion.getCompletoIniCerradas(login3);
                                    call3.enqueue(new Callback<CuerpoCompleto>() {
                                        @Override
                                        public void onResponse(Call<CuerpoCompleto> call, Response<CuerpoCompleto> response) {
                                            if(response.body() != null) {


                                                for(int e = 0; e<contactList.getRowset().size(); e++) {

                                                    ArrayList<Rowset> rowset1 = (ArrayList<Rowset>) contactList.getRowset();
                                                    String caso = rowset1.get(e).getCaso();
                                                    String descEquipo = rowset1.get(e).getDescEquipo();
                                                    String ordenNumero = rowset1.get(e).getOrdenNumero();
                                                    String tipo = rowset1.get(e).getOrdenTipo();
                                                    String fecha = rowset1.get(e).getFechaSolicitud();
                                                    String estado= rowset1.get(e).getEstado();
                                                    String idEquipo= rowset1.get(e).getIDEquipo();
                                                    String descripcion= rowset1.get(e).getDescripcion();
                                                    String fechaPro= rowset1.get(e).getFechaProgramacion();

                                                    String numeroOrden2 = numero.getText().toString();


                                                    if (rowset1.get(e).getOrdenNumero().equals(numeroOrden2)){


                                                        tipo1.setText(tipo );
                                                        numero1.setText(ordenNumero);
                                                        descripcion1.setText(descripcion);

                                                        tipoGlobal = tipo1.getText().toString();
                                                        numeroGlobal  = numero1.getText().toString();


                                                    }
                                                    if(rowset1.get(e).getOrdenNumero().equals(numeroOrden2)){


                                                        Intent i = new Intent(TercerActivity.this, CuartoActivity.class);
                                                        i.putExtra("dato2", tipo1.getText().toString());
                                                        i.putExtra("dato1", numero1.getText().toString());
                                                        i.putExtra("dato", descripcion1.getText().toString());




                                                        startActivity(i);



                                                    }
                                                }

                                            }
                                        }

                                        @Override
                                        public void onFailure(Call<CuerpoCompleto> call, Throwable t) {
                                            Toast.makeText(TercerActivity.this, "error ", Toast.LENGTH_LONG).show();

                                        }
                                    });



                                }
                            });


                        }

                    }
                    else{

                        Toast.makeText(TercerActivity.this, "ERROR CHEQUEAR FILTROS" + statusCode , Toast.LENGTH_LONG).show();
                        String dato2 = getIntent().getStringExtra("dato2");

                        prueba.setText(dato2);
                    }
                }

                @Override
                public void onFailure(Call<CuerpoCompleto> call2, Throwable t) {
                    Toast.makeText(TercerActivity.this, "error ", Toast.LENGTH_LONG).show();

                }
            });
        } else if(dato.length()== 0 && dato1.length() != 0 && dato2.length() != 0 && dato3.length() == 0 && dato4.length() == 0
                && dato5.length() == 0 && dato6.length() == 0 && dato7.length() == 0){

            //FASE y Mantenimiento
            CuerpoCompleto login3 = new CuerpoCompleto(usuarioGlobal, contraseñaGlobal,ResponsableGlobal,estadoCerradoGlobal,dato1,dato1,dato2,dato2);
            Call<CuerpoCompleto> call9 = conexion.getCompletoIniCerradas(login3);
            call9.enqueue(new Callback<CuerpoCompleto>() {
                @Override
                public void onResponse(Call<CuerpoCompleto> call9, Response<CuerpoCompleto> response) {
                    int statusCode = response.code();

                    if (response.isSuccessful()){

                        Mq0501cDatareq contactList =  response.body().getMq0501cDatareq();

                        listDatos =new ArrayList<>();
                        listDatos1 =new ArrayList<>();
                        listDatos2 =new ArrayList<>();




                        for(int e = 0; e<contactList.getRowset().size(); e++) {

                            ArrayList<Rowset> rowset1 = (ArrayList<Rowset>) contactList.getRowset();
                            String caso = rowset1.get(e).getCaso();
                            String descEquipo = rowset1.get(e).getDescEquipo();
                            String ordenNumero = rowset1.get(e).getOrdenNumero();
                            String tipo = rowset1.get(e).getOrdenTipo();
                            String fecha = rowset1.get(e).getFechaSolicitud();
                            String estado= rowset1.get(e).getEstado();
                            String idEquipo= rowset1.get(e).getIDEquipo();
                            String descripcion= rowset1.get(e).getDescripcion();
                            String fechaPro= rowset1.get(e).getFechaProgramacion();


                            listDatos.add( ordenNumero );
                            String content = ( descripcion +  "\n" +
                                    idEquipo + " | "   + descEquipo + "\n"+
                                    "F. Solicitada: " + fecha + "\n"+
                                    "F. Programada: " + fechaPro ) + "\n\n" ;
                            prueba.append(content);

                            AdapterDatos3 adapter = new AdapterDatos3(listDatos);
                            recycler.setAdapter(adapter);

                            listDatos1.add(" "+ "\n" +
                                    " "+   tipo + "\n" +
                                    "  " +"\n\n");
                            AdapterDatos1 adapter2 = new AdapterDatos1(listDatos1);
                            recycler1.setAdapter(adapter2);


                            listDatos2.add(  estado  );
                            AdapterDatos2 adapter3 = new AdapterDatos2(listDatos2);
                            recycler2.setAdapter(adapter3);

                            adapter.setOnclickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    numero.setText(listDatos.get(recycler.getChildAdapterPosition(view)));


                                    tipoGlobal = tipo;
                                    numeroGlobal  = ordenNumero;


                                    Retrofit retrofit = new Retrofit.Builder()
                                            .baseUrl(direc)
                                            .addConverterFactory(GsonConverterFactory.create())
                                            .build();

                                    Conexion conexion = retrofit.create(Conexion.class);


                                    Call<CuerpoCompleto> call3 = conexion.getCompletoIniCerradas(login3);
                                    call3.enqueue(new Callback<CuerpoCompleto>() {
                                        @Override
                                        public void onResponse(Call<CuerpoCompleto> call, Response<CuerpoCompleto> response) {
                                            if(response.body() != null) {


                                                for(int e = 0; e<contactList.getRowset().size(); e++) {

                                                    ArrayList<Rowset> rowset1 = (ArrayList<Rowset>) contactList.getRowset();
                                                    String caso = rowset1.get(e).getCaso();
                                                    String descEquipo = rowset1.get(e).getDescEquipo();
                                                    String ordenNumero = rowset1.get(e).getOrdenNumero();
                                                    String tipo = rowset1.get(e).getOrdenTipo();
                                                    String fecha = rowset1.get(e).getFechaSolicitud();
                                                    String estado= rowset1.get(e).getEstado();
                                                    String idEquipo= rowset1.get(e).getIDEquipo();
                                                    String descripcion= rowset1.get(e).getDescripcion();
                                                    String fechaPro= rowset1.get(e).getFechaProgramacion();

                                                    String numeroOrden2 = numero.getText().toString();


                                                    if (rowset1.get(e).getOrdenNumero().equals(numeroOrden2)){


                                                        tipo1.setText(tipo );
                                                        numero1.setText(ordenNumero);
                                                        descripcion1.setText(descripcion);

                                                        tipoGlobal = tipo1.getText().toString();
                                                        numeroGlobal  = numero1.getText().toString();


                                                    }
                                                    if(rowset1.get(e).getOrdenNumero().equals(numeroOrden2)){


                                                        Intent i = new Intent(TercerActivity.this, CuartoActivity.class);
                                                        i.putExtra("dato2", tipo1.getText().toString());
                                                        i.putExtra("dato1", numero1.getText().toString());
                                                        i.putExtra("dato", descripcion1.getText().toString());




                                                        startActivity(i);



                                                    }
                                                }

                                            }
                                        }

                                        @Override
                                        public void onFailure(Call<CuerpoCompleto> call, Throwable t) {
                                            Toast.makeText(TercerActivity.this, "error ", Toast.LENGTH_LONG).show();

                                        }
                                    });



                                }
                            });


                        }

                    }
                    else{

                        Toast.makeText(TercerActivity.this, "ERROR CHEQUEAR FILTROS" + statusCode , Toast.LENGTH_LONG).show();
                        String dato2 = getIntent().getStringExtra("dato2");

                        prueba.setText(dato2);
                    }
                }

                @Override
                public void onFailure(Call<CuerpoCompleto> call2, Throwable t) {
                    Toast.makeText(TercerActivity.this, "error ", Toast.LENGTH_LONG).show();

                }
            });
        } else if(dato.length()== 0 && dato1.length() == 0 && dato2.length() != 0 && dato3.length() == 0 && dato4.length() == 0
                && dato5.length() == 0 && dato6.length() == 0 && dato7.length() == 0){

            // mantenimiento,
            CuerpoOrden login3 = new CuerpoOrden(usuarioGlobal, contraseñaGlobal,ResponsableGlobal,estadoCerradoGlobal,dato2,dato2);
            Call<CuerpoOrden> call9 = conexion.getMantenimiento(login3);
            call9.enqueue(new Callback<CuerpoOrden>() {
                @Override
                public void onResponse(Call<CuerpoOrden> call9, Response<CuerpoOrden> response) {
                    int statusCode = response.code();

                    if (response.isSuccessful()){

                        Mq0501cDatareq contactList =  response.body().getMq0501cDatareq();

                        listDatos =new ArrayList<>();
                        listDatos1 =new ArrayList<>();
                        listDatos2 =new ArrayList<>();




                        for(int e = 0; e<contactList.getRowset().size(); e++) {

                            ArrayList<Rowset> rowset1 = (ArrayList<Rowset>) contactList.getRowset();
                            String caso = rowset1.get(e).getCaso();
                            String descEquipo = rowset1.get(e).getDescEquipo();
                            String ordenNumero = rowset1.get(e).getOrdenNumero();
                            String tipo = rowset1.get(e).getOrdenTipo();
                            String fecha = rowset1.get(e).getFechaSolicitud();
                            String estado= rowset1.get(e).getEstado();
                            String idEquipo= rowset1.get(e).getIDEquipo();
                            String descripcion= rowset1.get(e).getDescripcion();
                            String fechaPro= rowset1.get(e).getFechaProgramacion();


                            listDatos.add( ordenNumero );
                            String content = ( descripcion +  "\n" +
                                    idEquipo + " | "   + descEquipo + "\n"+
                                    "F. Solicitada: " + fecha + "\n"+
                                    "F. Programada: " + fechaPro ) + "\n\n" ;
                            prueba.append(content);

                            AdapterDatos3 adapter = new AdapterDatos3(listDatos);
                            recycler.setAdapter(adapter);

                            listDatos1.add(" "+ "\n" +
                                    " "+   tipo + "\n" +
                                    "  " +"\n\n");
                            AdapterDatos1 adapter2 = new AdapterDatos1(listDatos1);
                            recycler1.setAdapter(adapter2);


                            listDatos2.add(  estado  );
                            AdapterDatos2 adapter3 = new AdapterDatos2(listDatos2);
                            recycler2.setAdapter(adapter3);

                            adapter.setOnclickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    numero.setText(listDatos.get(recycler.getChildAdapterPosition(view)));


                                    tipoGlobal = tipo;
                                    numeroGlobal  = ordenNumero;


                                    Retrofit retrofit = new Retrofit.Builder()
                                            .baseUrl(direc)
                                            .addConverterFactory(GsonConverterFactory.create())
                                            .build();

                                    Conexion conexion = retrofit.create(Conexion.class);


                                    Call<CuerpoOrden> call3 = conexion.getMantenimiento(login3);
                                    call3.enqueue(new Callback<CuerpoOrden>() {
                                        @Override
                                        public void onResponse(Call<CuerpoOrden> call, Response<CuerpoOrden> response) {
                                            if(response.body() != null) {


                                                for(int e = 0; e<contactList.getRowset().size(); e++) {

                                                    ArrayList<Rowset> rowset1 = (ArrayList<Rowset>) contactList.getRowset();
                                                    String caso = rowset1.get(e).getCaso();
                                                    String descEquipo = rowset1.get(e).getDescEquipo();
                                                    String ordenNumero = rowset1.get(e).getOrdenNumero();
                                                    String tipo = rowset1.get(e).getOrdenTipo();
                                                    String fecha = rowset1.get(e).getFechaSolicitud();
                                                    String estado= rowset1.get(e).getEstado();
                                                    String idEquipo= rowset1.get(e).getIDEquipo();
                                                    String descripcion= rowset1.get(e).getDescripcion();
                                                    String fechaPro= rowset1.get(e).getFechaProgramacion();

                                                    String numeroOrden2 = numero.getText().toString();


                                                    if (rowset1.get(e).getOrdenNumero().equals(numeroOrden2)){


                                                        tipo1.setText(tipo );
                                                        numero1.setText(ordenNumero);
                                                        descripcion1.setText(descripcion);

                                                        tipoGlobal = tipo1.getText().toString();
                                                        numeroGlobal  = numero1.getText().toString();


                                                    }
                                                    if(rowset1.get(e).getOrdenNumero().equals(numeroOrden2)){


                                                        Intent i = new Intent(TercerActivity.this, CuartoActivity.class);
                                                        i.putExtra("dato2", tipo1.getText().toString());
                                                        i.putExtra("dato1", numero1.getText().toString());
                                                        i.putExtra("dato", descripcion1.getText().toString());




                                                        startActivity(i);



                                                    }
                                                }

                                            }
                                        }

                                        @Override
                                        public void onFailure(Call<CuerpoOrden> call, Throwable t) {
                                            Toast.makeText(TercerActivity.this, "error ", Toast.LENGTH_LONG).show();

                                        }
                                    });



                                }
                            });


                        }

                    }
                    else{

                        Toast.makeText(TercerActivity.this, "ERROR CHEQUEAR FILTROS" + statusCode , Toast.LENGTH_LONG).show();
                        String dato2 = getIntent().getStringExtra("dato2");

                        prueba.setText(dato2);
                    }
                }

                @Override
                public void onFailure(Call<CuerpoOrden> call2, Throwable t) {
                    Toast.makeText(TercerActivity.this, "error ", Toast.LENGTH_LONG).show();

                }
            });
        }else if(dato.length()== 0 && dato1.length() == 0 && dato2.length() != 0 && dato3.length() == 0 && dato4.length() == 0
                && dato5.length() == 0 && dato6.length() == 0 && dato7.length() != 0){

            // mantenimiento,  y asignado
            CuerpoOrden login3 = new CuerpoOrden(usuarioGlobal, contraseñaGlobal,ResponsableGlobal,estadoCerradoGlobal,dato2,dato2,dato7,dato7);
            Call<CuerpoOrden> call9 = conexion.getMantenimiento(login3);
            call9.enqueue(new Callback<CuerpoOrden>() {
                @Override
                public void onResponse(Call<CuerpoOrden> call9, Response<CuerpoOrden> response) {
                    int statusCode = response.code();

                    if (response.isSuccessful()){

                        Mq0501cDatareq contactList =  response.body().getMq0501cDatareq();

                        listDatos =new ArrayList<>();
                        listDatos1 =new ArrayList<>();
                        listDatos2 =new ArrayList<>();




                        for(int e = 0; e<contactList.getRowset().size(); e++) {

                            ArrayList<Rowset> rowset1 = (ArrayList<Rowset>) contactList.getRowset();
                            String caso = rowset1.get(e).getCaso();
                            String descEquipo = rowset1.get(e).getDescEquipo();
                            String ordenNumero = rowset1.get(e).getOrdenNumero();
                            String tipo = rowset1.get(e).getOrdenTipo();
                            String fecha = rowset1.get(e).getFechaSolicitud();
                            String estado= rowset1.get(e).getEstado();
                            String idEquipo= rowset1.get(e).getIDEquipo();
                            String descripcion= rowset1.get(e).getDescripcion();
                            String fechaPro= rowset1.get(e).getFechaProgramacion();


                            listDatos.add( ordenNumero );
                            String content = ( descripcion +  "\n" +
                                    idEquipo + " | "   + descEquipo + "\n"+
                                    "F. Solicitada: " + fecha + "\n"+
                                    "F. Programada: " + fechaPro ) + "\n\n" ;
                            prueba.append(content);

                            AdapterDatos3 adapter = new AdapterDatos3(listDatos);
                            recycler.setAdapter(adapter);

                            listDatos1.add(" "+ "\n" +
                                    " "+   tipo + "\n" +
                                    "  " +"\n\n");
                            AdapterDatos1 adapter2 = new AdapterDatos1(listDatos1);
                            recycler1.setAdapter(adapter2);


                            listDatos2.add(  estado  );
                            AdapterDatos2 adapter3 = new AdapterDatos2(listDatos2);
                            recycler2.setAdapter(adapter3);

                            adapter.setOnclickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    numero.setText(listDatos.get(recycler.getChildAdapterPosition(view)));


                                    tipoGlobal = tipo;
                                    numeroGlobal  = ordenNumero;


                                    Retrofit retrofit = new Retrofit.Builder()
                                            .baseUrl(direc)
                                            .addConverterFactory(GsonConverterFactory.create())
                                            .build();

                                    Conexion conexion = retrofit.create(Conexion.class);


                                    Call<CuerpoOrden> call3 = conexion.getMantenimiento(login3);
                                    call3.enqueue(new Callback<CuerpoOrden>() {
                                        @Override
                                        public void onResponse(Call<CuerpoOrden> call, Response<CuerpoOrden> response) {
                                            if(response.body() != null) {


                                                for(int e = 0; e<contactList.getRowset().size(); e++) {

                                                    ArrayList<Rowset> rowset1 = (ArrayList<Rowset>) contactList.getRowset();
                                                    String caso = rowset1.get(e).getCaso();
                                                    String descEquipo = rowset1.get(e).getDescEquipo();
                                                    String ordenNumero = rowset1.get(e).getOrdenNumero();
                                                    String tipo = rowset1.get(e).getOrdenTipo();
                                                    String fecha = rowset1.get(e).getFechaSolicitud();
                                                    String estado= rowset1.get(e).getEstado();
                                                    String idEquipo= rowset1.get(e).getIDEquipo();
                                                    String descripcion= rowset1.get(e).getDescripcion();
                                                    String fechaPro= rowset1.get(e).getFechaProgramacion();

                                                    String numeroOrden2 = numero.getText().toString();


                                                    if (rowset1.get(e).getOrdenNumero().equals(numeroOrden2)){


                                                        tipo1.setText(tipo );
                                                        numero1.setText(ordenNumero);
                                                        descripcion1.setText(descripcion);

                                                        tipoGlobal = tipo1.getText().toString();
                                                        numeroGlobal  = numero1.getText().toString();


                                                    }
                                                    if(rowset1.get(e).getOrdenNumero().equals(numeroOrden2)){


                                                        Intent i = new Intent(TercerActivity.this, CuartoActivity.class);
                                                        i.putExtra("dato2", tipo1.getText().toString());
                                                        i.putExtra("dato1", numero1.getText().toString());
                                                        i.putExtra("dato", descripcion1.getText().toString());




                                                        startActivity(i);



                                                    }
                                                }

                                            }
                                        }

                                        @Override
                                        public void onFailure(Call<CuerpoOrden> call, Throwable t) {
                                            Toast.makeText(TercerActivity.this, "error ", Toast.LENGTH_LONG).show();

                                        }
                                    });



                                }
                            });


                        }

                    }
                    else{

                        Toast.makeText(TercerActivity.this, "ERROR CHEQUEAR FILTROS" + statusCode , Toast.LENGTH_LONG).show();
                        String dato2 = getIntent().getStringExtra("dato2");

                        prueba.setText(dato2);
                    }
                }

                @Override
                public void onFailure(Call<CuerpoOrden> call2, Throwable t) {
                    Toast.makeText(TercerActivity.this, "error ", Toast.LENGTH_LONG).show();

                }
            });
        }else if(dato.length()== 0  && dato1.length() == 0 && dato2.length() == 0 && dato3.length() == 0 && dato4.length() == 0
                && dato5.length() == 0 && dato6.length() == 0 && dato7.length() == 0){

            //si no se selecciona nada
            CuerpoCerrado login3 = new CuerpoCerrado(usuarioGlobal, contraseñaGlobal,ResponsableGlobal,estadoCerradoGlobal);
            Call<CuerpoCerrado> call9 = conexion.getCerradas(login3);
            call9.enqueue(new Callback<CuerpoCerrado>() {
                @Override
                public void onResponse(Call<CuerpoCerrado> call9, Response<CuerpoCerrado> response) {
                    int statusCode = response.code();

                    if (response.isSuccessful()){

                        Mq0501cDatareq contactList =  response.body().getMq0501cDatareq();

                        listDatos =new ArrayList<>();
                        listDatos1 =new ArrayList<>();
                        listDatos2 =new ArrayList<>();




                        for(int e = 0; e<contactList.getRowset().size(); e++) {

                            ArrayList<Rowset> rowset1 = (ArrayList<Rowset>) contactList.getRowset();
                            String caso = rowset1.get(e).getCaso();
                            String descEquipo = rowset1.get(e).getDescEquipo();
                            String ordenNumero = rowset1.get(e).getOrdenNumero();
                            String tipo = rowset1.get(e).getOrdenTipo();
                            String fecha = rowset1.get(e).getFechaSolicitud();
                            String estado= rowset1.get(e).getEstado();
                            String idEquipo= rowset1.get(e).getIDEquipo();
                            String descripcion= rowset1.get(e).getDescripcion();
                            String fechaPro= rowset1.get(e).getFechaProgramacion();


                            listDatos.add( ordenNumero );
                            String content = ( descripcion +  "\n" +
                                    idEquipo + " | "   + descEquipo + "\n"+
                                    "F. Solicitada: " + fecha + "\n"+
                                    "F. Programada: " + fechaPro ) + "\n\n" ;
                            prueba.append(content);

                            AdapterDatos3 adapter = new AdapterDatos3(listDatos);
                            recycler.setAdapter(adapter);

                            listDatos1.add(" "+ "\n" +
                                    " "+   tipo + "\n" +
                                    "  " +"\n\n");
                            AdapterDatos1 adapter2 = new AdapterDatos1(listDatos1);
                            recycler1.setAdapter(adapter2);


                            listDatos2.add(  estado  );
                            AdapterDatos2 adapter3 = new AdapterDatos2(listDatos2);
                            recycler2.setAdapter(adapter3);

                            adapter.setOnclickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    numero.setText(listDatos.get(recycler.getChildAdapterPosition(view)));


                                    tipoGlobal = tipo;
                                    numeroGlobal  = ordenNumero;


                                    Retrofit retrofit = new Retrofit.Builder()
                                            .baseUrl(direc)
                                            .addConverterFactory(GsonConverterFactory.create())
                                            .build();

                                    Conexion conexion = retrofit.create(Conexion.class);


                                    Call<CuerpoCerrado> call3 = conexion.getCerradas(login3);
                                    call3.enqueue(new Callback<CuerpoCerrado>() {
                                        @Override
                                        public void onResponse(Call<CuerpoCerrado> call, Response<CuerpoCerrado> response) {
                                            if(response.body() != null) {


                                                for(int e = 0; e<contactList.getRowset().size(); e++) {

                                                    ArrayList<Rowset> rowset1 = (ArrayList<Rowset>) contactList.getRowset();
                                                    String caso = rowset1.get(e).getCaso();
                                                    String descEquipo = rowset1.get(e).getDescEquipo();
                                                    String ordenNumero = rowset1.get(e).getOrdenNumero();
                                                    String tipo = rowset1.get(e).getOrdenTipo();
                                                    String fecha = rowset1.get(e).getFechaSolicitud();
                                                    String estado= rowset1.get(e).getEstado();
                                                    String idEquipo= rowset1.get(e).getIDEquipo();
                                                    String descripcion= rowset1.get(e).getDescripcion();
                                                    String fechaPro= rowset1.get(e).getFechaProgramacion();

                                                    String numeroOrden2 = numero.getText().toString();


                                                    if (rowset1.get(e).getOrdenNumero().equals(numeroOrden2)){


                                                        tipo1.setText(tipo );
                                                        numero1.setText(ordenNumero);
                                                        descripcion1.setText(descripcion);

                                                        tipoGlobal = tipo1.getText().toString();
                                                        numeroGlobal  = numero1.getText().toString();


                                                    }
                                                    if(rowset1.get(e).getOrdenNumero().equals(numeroOrden2)){


                                                        Intent i = new Intent(TercerActivity.this, CuartoActivity.class);
                                                        i.putExtra("dato2", tipo1.getText().toString());
                                                        i.putExtra("dato1", numero1.getText().toString());
                                                        i.putExtra("dato", descripcion1.getText().toString());




                                                        startActivity(i);



                                                    }
                                                }

                                            }
                                        }

                                        @Override
                                        public void onFailure(Call<CuerpoCerrado> call, Throwable t) {
                                            Toast.makeText(TercerActivity.this, "error ", Toast.LENGTH_LONG).show();

                                        }
                                    });



                                }
                            });


                        }

                    }
                    else{

                        Toast.makeText(TercerActivity.this, "ERROR CHEQUEAR FILTROS" + statusCode , Toast.LENGTH_LONG).show();
                        String dato2 = getIntent().getStringExtra("dato2");

                        prueba.setText(dato2);
                    }
                }

                @Override
                public void onFailure(Call<CuerpoCerrado> call2, Throwable t) {
                    Toast.makeText(TercerActivity.this, "error ", Toast.LENGTH_LONG).show();

                }
            });
        }
    }

    public void Siguiente (View view){


        Intent e = new Intent(TercerActivity.this,SegundoActivity.class);


        startActivity(e);
    }
    public void Salir(View v){
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
