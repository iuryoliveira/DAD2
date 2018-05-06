package com.example.iury.gerenciadordeenergiaeltrica;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RelativeLayout;

public class SplashScreen extends AppCompatActivity implements Runnable {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        RelativeLayout splashScreen = (RelativeLayout) findViewById(R.id.layoutSplash);
//        splashScreen.setBackgroundResource(R.drawable.energia);

        Handler handler = new Handler();
        handler.postDelayed(this, 2000);
    }

    @Override
    public void run() {
//        Dessa forma é possível passar parâmetros
//        Intent intent = new Intent(this, MainActivity.class);
//        startActivity(intent);
//        finish();
        startActivity(new Intent(this, PerfilConsumo.class));
        finish();
    }
}
