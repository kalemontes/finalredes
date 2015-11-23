package com.example.daniel.finalredes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

/**
 * Created by Daniel on 22/11/2015.
 */
public class InteraccionEmpolladorActivity extends AppCompatActivity {
    private ImageButton interaccionempollador;
    private int contadorClicks;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interaccion_empollador);
        interaccionempollador = (ImageButton) findViewById(R.id.botonhuevo);
        interaccionempollador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contadorClicks +=1;
                if (contadorClicks > 10) {
                    Comunicacion.getInstance().enviar("ACCION_JUGADOR:EMPOLLADOR:DESMPOLLAR");
                }
            }
        });
    }


}
