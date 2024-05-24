package com.cesar.toursolvermobile2;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        ImageView imageView = findViewById(R.id.imageView);
        // Definir la animación de escala
        final Animation animation = new ScaleAnimation(
                1f, 1.5f, // Escala inicial y final en X
                1f, 1.5f, // Escala inicial y final en Y
                Animation.RELATIVE_TO_SELF, 0.5f, // Pivote X
                Animation.RELATIVE_TO_SELF, 0.5f); // Pivote Y
        animation.setDuration(550); // Duración de la animación en milisegundos
        animation.setRepeatCount(3); // Repetir la animación dos veces
        animation.setRepeatMode(Animation.REVERSE); // Modo de repetición: revertir

        // Ejecutar la animación en la imagen
        imageView.startAnimation(animation);

        // Configurar un temporizador para iniciar la siguiente actividad después de que la animación haya finalizado
        TimerTask tarea = new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(Splash.this, Login.class);
                startActivity(intent);
                finish();
            }
        };
        Timer tiempo = new Timer();
        tiempo.schedule(tarea, 2000); // Retraso de 3500ms para permitir que la animación se complete antes de iniciar Login

    }
}