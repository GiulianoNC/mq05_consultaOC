package com.quantum.aprobacionesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.widget.VideoView;

import com.quantum.InicioActivity;


public class MainActivity extends AppCompatActivity {

    VideoView videoview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //statusBar
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        getWindow().setStatusBarColor(Color.rgb(102,45,145));  //Define color

        //video
        videoview  = (VideoView)findViewById(R.id.videoView);
        String uriPath = "android.resource://com.quantum.aprobacionesapp/"+R.raw.inicioisotipocodt;
        Uri uri = Uri.parse(uriPath);
        videoview.setVideoURI(uri);
        videoview.requestFocus();
        videoview.start();

        //full screem la q con
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //splash Screem
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        setTheme(R.style.Theme_AprobacionesAPP);

        //tiempo de la pantalla Q
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, InicioActivity.class);
                startActivity(intent);
                finish();
            }
        },5000);

    }

}