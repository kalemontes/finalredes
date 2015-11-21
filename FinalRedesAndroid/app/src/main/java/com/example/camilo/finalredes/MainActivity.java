package com.example.camilo.finalredes;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import java.util.Observable;
import java.util.Observer;

public class MainActivity extends AppCompatActivity implements Observer {
    private ImageButton bnPlay;
    private Button bnQuit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Comunicacion.getInstance().addObserver(this);

        bnPlay = (ImageButton) findViewById(R.id.bnPlay);
        bnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentFullScreen = new Intent(getApplicationContext(), FullscreenActivity.class);
                startActivity(intentFullScreen);
            }
        });

        bnQuit = (Button) findViewById(R.id.bnQuit);
        bnQuit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void update(Observable observable, Object data) {
        Log.i("MainActivity", data.toString());
    }
}
