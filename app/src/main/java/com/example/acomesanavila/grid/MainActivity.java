package com.example.acomesanavila.grid;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button boton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Button start = (Button) findViewById(R.id.start);
        start.setOnClickListener(this);
        Button rojo = (Button) findViewById(R.id.rojo);
        rojo.setOnClickListener(this);
        Button amarillo = (Button) findViewById(R.id.amarillo);
        amarillo.setOnClickListener(this);
        Button azul = (Button) findViewById(R.id.azul);
        azul.setOnClickListener(this);
        Button verde = (Button) findViewById(R.id.verde);
        verde.setOnClickListener(this);
        Button comprobar = (Button) findViewById(R.id.comprobar);
        comprobar.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    ArrayList colores = new ArrayList();
    ArrayList jugador = new ArrayList();

    public void onClick(View v) {
        a=1;
        switch (v.getId()) {
            case R.id.start:
                for (int i = 0; i < colores.size(); i++) {
                    if (colores.get(i).equals("azul")) {
                        boton = (Button) findViewById(R.id.azul);
                        delay("#04f8fc", "#00989b", boton);
                    } else if (colores.get(i).equals("rojo")) {
                        boton = (Button) findViewById(R.id.rojo);
                        delay("#ff0000", "#b10000", boton);
                    } else if (colores.get(i).equals("amarillo")) {
                        boton = (Button) findViewById(R.id.amarillo);
                        delay("#fff600", "#c6bf00", boton);
                    } else {
                        boton = (Button) findViewById(R.id.verde);
                        delay("#04fc22", "#009b13", boton);
                    }
                }
                juego();
                break;
            case R.id.amarillo:
                jugador.add("amarillo");
                break;
            case R.id.rojo:
                jugador.add("rojo");
                break;
            case R.id.azul:
                jugador.add("azul");
                break;
            case R.id.verde:
                jugador.add("verde");
                break;
            case R.id.comprobar:
                comprobar();
                break;

        }
    }

    protected void animateColor(View v, String propertyName, int startColor, int endColor) {
        // Initialize a new value animator of type int
        ValueAnimator valueAnimator = ObjectAnimator.ofInt(
                v, // Target object
                propertyName, // Property name
                startColor, // Value
                endColor);// Value
        valueAnimator.start();
    }

    int x = 0;

    public void comprobar() {
        TextView text = (TextView) findViewById(R.id.puntuacion);
        for (int i = 0; i < colores.size(); i++) {
            if (colores.get(i).equals(jugador.get(i))) {
                x++;
                text.setText("Puntuacion :" + x);
            } else {
                jugador = new ArrayList();
                colores = new ArrayList();
                text.setText("Has perdido");
                x=0;
            }
        }
        jugador = new ArrayList();
    }
    int a=1;
    public void delay(final String color, final String color2, final Button boton) {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                animateColor(boton, "backgroundColor", Color.parseColor(color), Color.parseColor(color2));
            }
        }, 500 * a );
        a++;
    }

    public void juego() {
        int x = 1;
        Handler handler2 = new Handler();
        handler2.postDelayed(new Runnable() {
            @Override
            public void run() {
                switch ((int) Math.floor(Math.random() * 4)) {
                    case 0:
                        boton = (Button) findViewById(R.id.azul);
                        delay("#04f8fc", "#00989b", boton);
                        colores.add("azul");
                        break;
                    case 1:
                        boton = (Button) findViewById(R.id.verde);
                        delay("#04fc22", "#009b13", boton);
                        colores.add("verde");
                        break;
                    case 2:
                        boton = (Button) findViewById(R.id.amarillo);
                        delay("#fff600", "#c6bf00", boton);
                        colores.add("amarillo");
                        break;
                    case 3:
                        boton = (Button) findViewById(R.id.rojo);
                        delay("#ff0000", "#b10000", boton);
                        colores.add("rojo");
                        break;
                }
            }
        }, 500);
    }
}


