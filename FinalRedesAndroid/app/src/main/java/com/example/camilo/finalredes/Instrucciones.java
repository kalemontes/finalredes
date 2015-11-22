package com.example.camilo.finalredes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

public class Instrucciones extends AppCompatActivity {
    private ImageView ImgInstruccion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instrucciones);
        ImgInstruccion = (ImageView) findViewById(R.id.imgInstrucciones);

        String jugador = getIntent().getStringExtra("JUGADOR");
        switch (jugador) {
            case "ROL_JUGADOR:EMPOLLADOR":
                ImgInstruccion.setImageResource(R.drawable.instruccion3);
                break;
            case "ROL_JUGADOR:CERDOKILLER":
                ImgInstruccion.setImageResource(R.drawable.instruccion1);
                break;
            case "ROL_JUGADOR:MONJA":
                ImgInstruccion.setImageResource(R.drawable.instruccion2);
                break;

        }
    }
}

