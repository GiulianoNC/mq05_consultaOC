package com.quantum;

import static com.quantum.aprobacionesapp.configuracion.ResponsableGlobal;
import static com.quantum.aprobacionesapp.configuracion.direc;
import static com.quantum.aprobacionesapp.configuracion.estadoCerradoGlobal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.quantum.aprobacionesapp.R;
import com.quantum.aprobacionesapp.SegundoActivity;
import com.quantum.aprobacionesapp.configuracion;
import com.quantum.conectividad.Conexion;
import com.quantum.parseo.Cuerpo;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class InicioActivity extends AppCompatActivity {
    private TextView user, informeConexion, urls, moneda,contraseña,qtm;
    public static String usuarioGlobal = null;
    public static String contraseñaGlobal = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        user = findViewById(R.id.Usuario);
        contraseña = findViewById(R.id.contras);
        informeConexion= findViewById(R.id.informeConexion);


        //statusBar
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        getWindow().setStatusBarColor(Color.rgb(102,45,145));  //Define color

        //guardar


        SharedPreferences preferences = getSharedPreferences("datos", Context.MODE_PRIVATE);
        user.setText(preferences.getString("usuario",""));
        contraseña.setText(preferences.getString("password",""));

        //LO QUE TRAE DE CONFIGURACION
        urls = findViewById(R.id.dir);

        String direccion = getIntent().getStringExtra("direcciones");
        urls.setText(direccion);

        qtm = findViewById(R.id.QTMtitulo);
        qtm.setText("   QTM - CONSULTA" + "\n" + "ORDEN DE TRABAJO" );

        //Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }
    public void Logearse(View view ) {

        String user2 = user.getText().toString();
        String contraseña2 = contraseña.getText().toString();


        String direccion = getIntent().getStringExtra("direcciones");
        urls.setText(direccion);


        if (urls.length() == 0)  {

            Intent siguiente = new Intent(InicioActivity.this, configuracion.class);
            startActivity(siguiente);
        }else{

            if (user2.length() == 0 && contraseña2.length() == 0) {
                Toast.makeText(this, "Debes ingresar un usuario y contraseña", Toast.LENGTH_LONG).show();
            }
            if (user2.length() != 0 && contraseña2.length() != 0) {

                Toast.makeText(this, "Procesando", Toast.LENGTH_LONG).show();


                usuarioGlobal = user.getText().toString();
                contraseñaGlobal = contraseña.getText().toString();

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(direc)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                Conexion conexion = retrofit.create(Conexion.class);

                Cuerpo logerse = new Cuerpo(usuarioGlobal, contraseñaGlobal,ResponsableGlobal, estadoCerradoGlobal);


                Call<Cuerpo> call1 = conexion.getData(logerse);
                call1.enqueue(new Callback<Cuerpo>() {

                    @Override
                    public void onResponse(Call<Cuerpo> call, Response<Cuerpo> response) {
                        int statusCode = response.code();

                        if (response.isSuccessful()){

                            Cuerpo cuerpo =  response.body();

                            if(statusCode <= 200){

                                Intent siguiente = new Intent(InicioActivity.this, SegundoActivity.class);


                                startActivity(siguiente);
                                Toast.makeText(InicioActivity.this, "EXITO", Toast.LENGTH_LONG).show();



                            }
                            else{
                                Toast.makeText(InicioActivity.this,"error", Toast.LENGTH_LONG).show();

                            }
                        }
                    }
                    @Override
                    public void onFailure(Call<Cuerpo> call, Throwable t) {
                        Toast.makeText(InicioActivity.this ,"Login failed", Toast.LENGTH_LONG).show();
                        informeConexion.setText(t.getMessage());

                    }
                });

            }
        }

        SharedPreferences preferecias =  getSharedPreferences("datos",Context.MODE_PRIVATE);
        SharedPreferences.Editor Obj_editor = preferecias.edit();
        Obj_editor.putString("usuario", user.getText().toString());
        Obj_editor.putString("password", contraseña.getText().toString());

        Obj_editor.commit();

    }

    //menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    //acciones de menu
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.action_privacidad:

                String url = "https://quantumconsulting.com.ar/politicas-de-privacidad/";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);

                break;

            case R.id.action_configuracion:
                Intent siguiente = new Intent(InicioActivity.this, configuracion.class);


                startActivity(siguiente);
                break;


        }
        return super.onOptionsItemSelected(item);
    }

}