package com.example.daniel.finalredes;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by Daniel on 22/11/2015.
 */
public class MainActivity extends AppCompatActivity implements Observer {
    private ImageButton bnPlay,bnPlay2,bnPlay3;
    private ImageButton salir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Comunicacion.getInstance().addObserver(this);

        bnPlay = (ImageButton) findViewById(R.id.bnPlay);
        bnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Comunicacion.getInstance().enviar("JUGADOR:Empollador");
                Comunicacion.getInstance().enviar("CONEXION_JUGADOR:JUGADOR_1:");
            }
        });

        bnPlay2 = (ImageButton) findViewById(R.id.bnPlay2);
        bnPlay2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Comunicacion.getInstance().enviar("CONEXION_JUGADOR:JUGADOR_2:");
            }
        });

        bnPlay3 = (ImageButton) findViewById(R.id.bnPlay3);
        bnPlay3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Comunicacion.getInstance().enviar("CONEXION_JUGADOR:JUGADOR_3:");
            }
        });

        salir = (ImageButton) findViewById(R.id.salir);
        salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void update(Observable observable, Object data) {
        String respuestaServidor = (String) data;
        Log.i("MainActivity", respuestaServidor);

        switch (respuestaServidor) {
            case "EMPOLLADOR":
                Intent intentInstrucciones = new Intent(getApplicationContext(), InstruccionesActivity.class);
                intentInstrucciones.putExtra("JUGADOR","ROL_JUGADOR:EMPOLLADOR");
                startActivity(intentInstrucciones);
                break;
            case "MONJA":
                Intent intentInstrucciones2 = new Intent(getApplicationContext(), InstruccionesActivity.class);
                intentInstrucciones2.putExtra("JUGADOR","ROL_JUGADOR:MONJA");
                startActivity(intentInstrucciones2);
                break;
            case "CERDOKILLER":
                Intent intentInstrucciones3 = new Intent(getApplicationContext(), InstruccionesActivity.class);
                intentInstrucciones3.putExtra("JUGADOR","ROL_JUGADOR:CERDOKILLER");
                startActivity(intentInstrucciones3);
                break;
            default:
                break;
        }
    }
}