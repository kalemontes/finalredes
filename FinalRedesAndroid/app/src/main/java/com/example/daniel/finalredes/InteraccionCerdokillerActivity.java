package com.example.daniel.finalredes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

/**
 * Created by Daniel on 22/11/2015.
 */
public class InteraccionCerdokillerActivity extends AppCompatActivity {
    private ImageView Interaccioncerdokiller;
    private ImageButton botonRojo, botonAzul, botonAmarillo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interaccion_cerdokiller);
        botonRojo = (ImageButton) findViewById(R.id.botonrojo);
        botonRojo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Comunicacion.getInstance().enviar("ACCION_JUGADOR:CERDOKILLER:LANZAR_ROJO");
            }
        });
        botonAmarillo = (ImageButton) findView

    }
}
