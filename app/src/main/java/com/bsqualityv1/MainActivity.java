package com.bsqualityv1;


import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;

import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Declaracion Elementos
        Button anadir = (Button) findViewById(R.id.anadirCil);
        Button consul = (Button) findViewById(R.id.consulCil);


        // Abrimos ventada añadir cilindro si pulsamos el boton.
        anadir.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Mostramos la ventana Añadir Cilindro
                Intent anadirCilView = new Intent(MainActivity.this, AnadirCilindro.class);
                startActivity(anadirCilView);
            }
        });

        // Abrimos ventada consultar cilindro al pulsar el boton.
        consul.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Mostramos la ventana Consultar Cilindros
                Intent consulCil = new Intent(MainActivity.this, ConsulCil.class);
                startActivity(consulCil);
            }
        });

    }
}