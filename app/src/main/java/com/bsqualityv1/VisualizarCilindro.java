package com.bsqualityv1;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;


import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;



public class VisualizarCilindro extends AppCompatActivity {

    private DBAHelper helper = null;
    private int posicionActual = 0;
    private int longitudLista = 0;
    Bundle objetoEnviado = null;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizar_cilindro);

        helper = new DBAHelper(this);

        objetoEnviado = getIntent().getExtras();

        // Pasamos de la otra activity lo necesario para poder mostrar los datos en esta
        Cilindro cilindro = (Cilindro) objetoEnviado.getSerializable("cilindro");
        longitudLista = objetoEnviado.getInt("longitudLista");

        posicionActual = cilindro.getId();
        rellenarCampos(posicionActual);


        // Creamos los bones Siguiente y Anterior.
        Button sig = (Button) findViewById(R.id.btnSiguiente);
        Button ant = (Button) findViewById(R.id.btnAnterior);
        Button menu = (Button) findViewById(R.id.btnMenu);

        sig.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                posicionActual += 1;
                rellenarCampos(posicionActual);
            }
        });

        ant.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                posicionActual -= 1;
                rellenarCampos(posicionActual);
            }
        });

        menu.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent irMenu = new Intent(VisualizarCilindro.this, MainActivity.class);
                startActivity(irMenu);
            }
        });

        }

        public void rellenarCampos(int id){

            // Evito que se puedan tocar todos los campos
            Cilindro cilindro = helper.getCilindro(id);

            // Fecha formateada
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            EditText fecha = findViewById(R.id.editTextDate);
            fecha.setText(dateFormat.format(cilindro.getFecha()));


            // Seteo los Edit Text y los pongo disabled para no poder modificarlos
            fecha.setEnabled(false);
            findViewById(R.id.editTextNombreCli).setEnabled(false);
            findViewById(R.id.editTextIdCilindro).setEnabled(false);
            findViewById(R.id.editTextMarca).setEnabled(false);
            findViewById(R.id.editTextDiam).setEnabled(false);
            findViewById(R.id.editTextMed).setEnabled(false);
            findViewById(R.id.rdbtnSi).setEnabled(false);
            findViewById(R.id.rdbtnNo).setEnabled(false);
            findViewById(R.id.editTextObs).setEnabled(false);

            // Relleno los demas campos
            ((EditText)findViewById(R.id.editTextNombreCli)).setText(cilindro.getNombreCli());
            ((EditText)findViewById(R.id.editTextIdCilindro)).setText(cilindro.getIdCil());
            ((EditText)findViewById(R.id.editTextMarca)).setText(cilindro.getMarcaCil());
            ((EditText)findViewById(R.id.editTextDiam)).setText(cilindro.getDiamCil().toString());
            ((EditText)findViewById(R.id.editTextMed)).setText(cilindro.getMedPist().toString());

            // Aqui se rellena el radioGroup
            if(cilindro.getSoldar()){
                ((RadioButton) findViewById(R.id.rdbtnNo)).setChecked(false);
                ((RadioButton) findViewById(R.id.rdbtnSi)).setChecked(true);
            } else {
                ((RadioButton) findViewById(R.id.rdbtnNo)).setChecked(true);
                ((RadioButton) findViewById(R.id.rdbtnSi)).setChecked(false);
            }

            ((EditText)findViewById(R.id.editTextObs)).setText(cilindro.getObsv());

            // Evito que le puedas dar a siguiente si ya no hay mas cilindros

            Button sig = (Button) findViewById(R.id.btnSiguiente);
            Button ant = (Button) findViewById(R.id.btnAnterior);

            System.out.println(longitudLista);
            System.out.println(cilindro.getId());

            if (longitudLista == cilindro.getId()){
                sig.setEnabled(false);
                ant.setEnabled(true);
            } else if (cilindro.getId() == 1){
                sig.setEnabled(true);
                ant.setEnabled(false);
            } else {
                sig.setEnabled(true);
                ant.setEnabled(true);
            }
        }
    }

