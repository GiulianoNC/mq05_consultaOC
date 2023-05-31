package com.quantum.aprobacionesapp;



import static com.quantum.InicioActivity.contraseñaGlobal;
import static com.quantum.InicioActivity.usuarioGlobal;
import static com.quantum.aprobacionesapp.configuracion.direc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.DatePicker;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.quantum.adaptador.AdapterDatos;
import com.quantum.conectividad.Conexion;
import com.quantum.parseo.Mq0501xDatareq;
import com.quantum.parseo2.Mq0503aFormreq1;
import com.quantum.parseo2.Rowset1;
import com.quantum.parseo2.cuerpoUDC;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SegundoActivity extends AppCompatActivity {

    private TextView prueba, fechaSol,fechaSol2, fechaProg, fechaProg2,qtm;
    private TextView op1r,op2r,op3r,opC,p1r,p2r,p3r,asignado1;
    private ScrollView sc1;


    public static String descripcionGlobal = null;
    public static String tipoGlobal = null;
    public static String fechaGlobal = null;
    public static String idEquipoGlobal = null;
    public static String casoGlobal = null;
    public static String descEquipoGlobal = null;
    public static String estadoGlobal = null;
    public static String VCGlobal = null;


    //FILTROS GLOBALES

    public static String TipoOrdenGlobal = null;
    public static String FaseGlobal = null;
    public static String MantenimientoGlobal = null;
    public static String FechaSolicitadaInicialGlobal = null;
    public static String FechaSolicitadaFinalGlobal = null;
    public static String FechaProgramadaInicialGlobal = null;
    public static String FechaProgramadaFinalGlobal = null;
    public static String AsignadoGlobal = null;









    private ProgressBar progresBar;

    Mq0501xDatareq reqRowset;

    ArrayList<String> listDatos;
    ArrayList<String> listDatos1;
    ArrayList<String> listDatos2;



    RecyclerView recycler,recycler1,recycler2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segundo);

        //statusBar
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        getWindow().setStatusBarColor(Color.rgb(102,45,145));  //Define color

        sc1= findViewById(R.id.scTipoOrden);

        sc1.setScrollbarFadingEnabled(false);
        sc1.setVerticalScrollBarEnabled(true);
        sc1.setVerticalFadingEdgeEnabled(false);

        recycler= (RecyclerView) findViewById(R.id.recyclerId4);
        recycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));


        recycler1= (RecyclerView) findViewById(R.id.recyclerId1);
        recycler1.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        recycler2= (RecyclerView) findViewById(R.id.recyclerId2);
        recycler2.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));


        op1r = (TextView) findViewById(R.id.op1);
         opC= findViewById(R.id.codigo1);
         p1r= findViewById(R.id.p1);

        op2r = (TextView) findViewById(R.id.op2);
        p2r= findViewById(R.id.p2);

        op3r = (TextView) findViewById(R.id.op3);
        p3r= findViewById(R.id.p3);


        asignado1 = (TextView) findViewById(R.id.Asignado);

        fechaSol= findViewById(R.id.fechaSolcitada);
        fechaSol2= findViewById(R.id.fechaSolcitada2);

        fechaProg= findViewById(R.id.fechaProgramada);
        fechaProg2= findViewById(R.id.fechaProgramada2);

        prueba= findViewById(R.id.recuperacion);


        Prueba();
        Limpiar2();

        qtm = findViewById(R.id.QTM2);
        qtm.setText("   QTM - CONSULTA" + "\n" + "ORDEN DE TRABAJO" );

    }


    public void abrirCalendario(View view){


        Calendar cal = Calendar.getInstance();
        int year=  cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int date= cal.get(Calendar.DATE);
        Locale localeSpanish = new Locale("es", "ES");


        DatePickerDialog dpd = new DatePickerDialog(SegundoActivity.this, new DatePickerDialog.OnDateSetListener() {


            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {


                String fecha = dayOfMonth + "/" + (month + 1) + "/" + year;
                fechaSol.setText(fecha);
            }
        },year, month,date);
        dpd.show();

    }

    public void abrirCalendario2(View view){
        Calendar cal = Calendar.getInstance();
        int year=  cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int date= cal.get(Calendar.DATE);

        DatePickerDialog dpd = new DatePickerDialog(SegundoActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {

                String fecha = dayOfMonth + "/" +(month + 1) + "/" + year;
                fechaProg.setText(fecha);
            }
        },year, month,date);
        dpd.show();

    }

    public void abrirCalendario3(View view){
        Calendar cal = Calendar.getInstance();
        int year=  cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int date= cal.get(Calendar.DATE);

        DatePickerDialog dpd = new DatePickerDialog(SegundoActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {

                String fecha = dayOfMonth + "/" + (month + 1) + "/" + year;
                fechaProg2.setText(fecha);
            }
        },year, month,date);
        dpd.show();

    }
    public void abrirCalendario4(View view){
        Calendar cal = Calendar.getInstance();
        int year=  cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int date= cal.get(Calendar.DATE);

        DatePickerDialog dpd = new DatePickerDialog(SegundoActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {

                String fecha = dayOfMonth + "/" + (month + 1) + "/" + year;
                fechaSol2.setText(fecha);
            }
        },year, month,date);
        dpd.show();

    }


    public void Prueba( ){


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(direc)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Conexion conexion = retrofit.create(Conexion.class);


        String udc = "DT";

        cuerpoUDC logerse3 = new cuerpoUDC(usuarioGlobal, contraseñaGlobal, "DT" );
        Call<cuerpoUDC> call1 = conexion.getUDC(logerse3);
        call1.enqueue(new Callback<cuerpoUDC>() {
            @Override
            public void onResponse(Call<cuerpoUDC> call, Response<cuerpoUDC> response) {
                int statusCode = response.code();

                if(statusCode == 200) {

                    Mq0503aFormreq1 contactList =  response.body().getMq0503aFormreq1();

                    listDatos =new ArrayList<>();


                    for(int e = 0; e<contactList.getRowset().size(); e++) {

                        ArrayList<Rowset1> rowset1 = (ArrayList<Rowset1>) contactList.getRowset();
                        String codigo = rowset1.get(e).getCDigos();
                        String descripcion= rowset1.get(e).getDescripcion();




                        listDatos.add( codigo  );

                        String content = (descripcion+ "\n\n" );
                        p1r.append(content);

                        AdapterDatos adapter = new AdapterDatos(listDatos);
                        recycler.setAdapter(adapter);


                        adapter.setOnclickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {



                                op1r.setText(listDatos.get(recycler.getChildAdapterPosition(view)));


                            }
                        });

                    }


                }else {
                    Toast.makeText(SegundoActivity.this, "ERROR " + statusCode, Toast.LENGTH_LONG).show();
                }


            }
            @Override
            public void onFailure(Call<cuerpoUDC> call, Throwable t) {
                Toast.makeText(SegundoActivity.this, "Verificar VPN " , Toast.LENGTH_LONG).show();


            }


        });

        cuerpoUDC logerse4 = new cuerpoUDC(usuarioGlobal, contraseñaGlobal, "W1" );
        Call<cuerpoUDC> call2 = conexion.getUDC(logerse4);
        call2.enqueue(new Callback<cuerpoUDC>() {
            @Override
            public void onResponse(Call<cuerpoUDC> call2, Response<cuerpoUDC> response) {
                int statusCode = response.code();

                if(statusCode == 200) {

                    Mq0503aFormreq1 contactList =  response.body().getMq0503aFormreq1();

                    listDatos1 =new ArrayList<>();


                    for(int e = 0; e<contactList.getRowset().size(); e++) {

                        ArrayList<Rowset1> rowset1 = (ArrayList<Rowset1>) contactList.getRowset();
                        String codigo = rowset1.get(e).getCDigos();
                        String descripcion= rowset1.get(e).getDescripcion();




                        listDatos1.add( codigo );


                        String content = (descripcion+ "\n\n" );
                        p2r.append(content);

                        AdapterDatos adapter1 = new AdapterDatos(listDatos1);
                        recycler1.setAdapter(adapter1);


                        adapter1.setOnclickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {




                                op2r.setText(listDatos1.get(recycler1.getChildAdapterPosition(view)));


                            }
                        });

                    }


                }else {
                    Toast.makeText(SegundoActivity.this, "ERROR " + statusCode, Toast.LENGTH_LONG).show();
                }


            }
            @Override
            public void onFailure(Call<cuerpoUDC> call, Throwable t) {
                Toast.makeText(SegundoActivity.this, "Verificar VPN " , Toast.LENGTH_LONG).show();


            }


        });


        cuerpoUDC logerse5 = new cuerpoUDC(usuarioGlobal, contraseñaGlobal, "W5" );
        Call<cuerpoUDC> call3 = conexion.getUDC(logerse5);
        call3.enqueue(new Callback<cuerpoUDC>() {
            @Override
            public void onResponse(Call<cuerpoUDC> call3, Response<cuerpoUDC> response) {
                int statusCode = response.code();

                if(statusCode == 200) {

                    Mq0503aFormreq1 contactList =  response.body().getMq0503aFormreq1();

                    listDatos2 =new ArrayList<>();


                    for(int e = 0; e<contactList.getRowset().size(); e++) {

                        ArrayList<Rowset1> rowset1 = (ArrayList<Rowset1>) contactList.getRowset();
                        String codigo = rowset1.get(e).getCDigos();
                        String descripcion= rowset1.get(e).getDescripcion();




                        listDatos2.add( codigo );


                        String content = (descripcion+ "\n\n" );
                        p3r.append(content);

                        AdapterDatos adapter2 = new AdapterDatos(listDatos2);
                        recycler2.setAdapter(adapter2);


                        adapter2.setOnclickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                                op3r.setText(listDatos2.get(recycler2.getChildAdapterPosition(view)));

                            }
                        });

                    }


                }else {
                    Toast.makeText(SegundoActivity.this, "ERROR " + statusCode, Toast.LENGTH_LONG).show();
                }


            }
            @Override
            public void onFailure(Call<cuerpoUDC> call, Throwable t) {
                Toast.makeText(SegundoActivity.this, "Verificar VPN " , Toast.LENGTH_LONG).show();


            }


        });



    }

    public static String ord1Global = null;
    public static String fechaProgGlobal= null;


    public void vigentes(View v){


        Intent u = new Intent(this,TercerActivity.class);
        u.putExtra("dato", op1r.getText().toString());
        u.putExtra("dato1", op2r.getText().toString());
        u.putExtra("dato2", op3r.getText().toString());
        u.putExtra("dato3", fechaSol.getText().toString());
        u.putExtra("dato4", fechaSol2.getText().toString());
        u.putExtra("dato5", fechaProg.getText().toString());
        u.putExtra("dato6", fechaProg2.getText().toString());
        u.putExtra("dato7", asignado1.getText().toString());

        VCGlobal = "V";


        startActivity(u);



    }

    public void cerradas(View v){

        Intent u = new Intent(this,TercerActivity.class);
        u.putExtra("dato", op1r.getText().toString());
        u.putExtra("dato1", op2r.getText().toString());
        u.putExtra("dato2", op3r.getText().toString());
        u.putExtra("dato3", fechaSol.getText().toString());
        u.putExtra("dato4", fechaSol2.getText().toString());
        u.putExtra("dato5", fechaProg.getText().toString());
        u.putExtra("dato6", fechaProg2.getText().toString());
        u.putExtra("dato7", asignado1.getText().toString());


        VCGlobal = "C";



        startActivity(u);



    }

    public void Limpiar(View v){


        op1r.setText("");
        op2r.setText("");
        op3r.setText("");


        asignado1.setText("");

        fechaSol.setText("");
        fechaSol2.setText("");

        fechaProg.setText("");
        fechaProg2.setText("");

    }

    public void Limpiar2(){


        op1r.setText("");
        op2r.setText("");
        op3r.setText("");


        asignado1.setText("");

        fechaSol.setText("");
        fechaSol2.setText("");

        fechaProg.setText("");
        fechaProg2.setText("");

    }
}