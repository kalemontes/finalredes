package com.example.daniel.finalredes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

/**
 * Created by Daniel on 22/11/2015.
 */
public class InstruccionesActivity extends AppCompatActivity {
    private ImageView ImgInstruccion;
    private Button bnOkInstrucciones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instrucciones);
        final String jugador = getIntent().getStringExtra("JUGADOR");

        ImgInstruccion = (ImageView) findViewById(R.id.imgInstrucciones);
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

        bnOkInstrucciones = (Button) findViewById(R.id.bnOkInstrucciones);
        bnOkInstrucciones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (jugador) {
                    case "ROL_JUGADOR:EMPOLLADOR":
                        Intent intentEmpollador = new Intent(getApplicationContext(), InteraccionEmpolladorActivity.class);
                        startActivity(intentEmpollador);
                        break;
                    case "ROL_JUGADOR:CERDOKILLER":
                        Intent intentCerdokiller = new Intent(getApplicationContext(), InteraccionCerdokillerActivity.class);
                        startActivity(intentCerdokiller);
                        break;
                    case "ROL_JUGADOR:MONJA":
                        Intent intentMonja = new Intent(getApplicationContext(), InteraccionMonjaActivity.class);
                        startActivity(intentMonja);
                        break;

                }
            }
        });
    }
}

