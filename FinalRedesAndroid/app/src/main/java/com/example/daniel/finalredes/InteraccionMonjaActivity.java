package com.example.daniel.finalredes;

import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.gesture.Prediction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by Daniel on 22/11/2015.
 */

//http://stackoverflow.com/a/23424366/665823
public class InteraccionMonjaActivity extends AppCompatActivity  implements GestureOverlayView.OnGesturePerformedListener, Observer {
    private GestureOverlayView zonaArrastre;
    private GestureLibrary gestureLib;
    private ImageView imgHuevo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interaccion_monja);
        Comunicacion.getInstance().addObserver(this);

        imgHuevo = (ImageView) findViewById(R.id.imgHuevo);
        zonaArrastre = (GestureOverlayView) findViewById(R.id.zonaArrastre);
        zonaArrastre.addOnGesturePerformedListener(this);
        gestureLib = GestureLibraries.fromRawResource(this, R.raw.gestures);
        gestureLib.load();
    }

    @Override
    public void onGesturePerformed(GestureOverlayView overlay, Gesture gesture) {
        ArrayList<Prediction> predictions = gestureLib.recognize(gesture);
        for (Prediction prediction : predictions) {
            if (prediction.score > 2.0) {
                switch (prediction.name) {
                    case "ir_volver" :
                        Toast.makeText(getApplicationContext(), "Recoger huevo", Toast.LENGTH_SHORT).show();
                        Comunicacion.getInstance().enviar("ACCION_JUGADOR:MONJA:RESCATAR");
                        break;
                    case "infinito" :
                        Toast.makeText(getApplicationContext(), "Guardar huevo", Toast.LENGTH_SHORT).show();
                        //No hacemos nada en el servidor todavia
                        break;
                    case "zig_zag" :
                        Toast.makeText(getApplicationContext(), "Esquivar cerdo", Toast.LENGTH_SHORT).show();
                        //No hacemos nada en el servidor todavia
                        break;
                }
            }
        }
        imgHuevo.setVisibility(View.INVISIBLE);
    }

    @Override
    public void update(Observable observable, Object data) {
        String respuestaServidor = (String) data;
        Log.i("MonjaActivity", respuestaServidor);

        switch (respuestaServidor) {
            case "ACCION_MONJA:HUEVO_RESCATADO" :
                mostrarImagenHuevo(R.drawable.huevodos);
                break;
            case "ACCION_MONJA:HUEVO_PERDIDO" :
                mostrarImagenHuevo(R.drawable.nohuevo);
                break;
        }
    }

    private void mostrarImagenHuevo(final int idImagen) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                imgHuevo.setImageResource(idImagen);
                imgHuevo.setVisibility(View.VISIBLE);
            }
        });
    }
}
