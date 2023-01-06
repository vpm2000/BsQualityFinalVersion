package com.bsqualityv1;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class AnadirCilindro extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anadircil);

        // Obtenemos la fecha actual y la formateamos
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        EditText fecha = findViewById(R.id.editTextDate);
        fecha.setText(dateFormat.format(new Date()));
        fecha.setEnabled(false);

        // Creamos el Spinner de los Clientes
        Spinner spinnerCli = (Spinner) findViewById(R.id.spinnerCli);
        String[] valores = {"Seleccionar", "Barikit", "Castillo", "Bud Racing", "Motocross Center", "Maños Racing", "Sais Power"};
        spinnerCli.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, valores));

        // Seteamos el Id Correspondiente
        EditText idCil = findViewById(R.id.editTextIdCilindro);
        idCil.setText(generarIdCil());
        idCil.setEnabled(false);

        // Boton Guardar + Inserccion BBDD
        DBAHelper helper = new DBAHelper(this);
        Button guardar = (Button) findViewById(R.id.btnAnterior);

        guardar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

               // Comprobamos la validacion de los campos

                try {
                    Cilindro cilindro = validarDatosYObtenerCilindro();

                    // Insertamos el Objeto
                    helper.insert(cilindro);

                    // Y reseteamos los datos
                    reset();

                }catch (Exception e){
                    Toast toast = new Toast(getApplicationContext());
                    LayoutInflater inflater = getLayoutInflater();

                    View layout = inflater.inflate(R.layout.custom_toast,
                            (ViewGroup) findViewById(R.id.ll_custom_toast));

                    TextView txtMsg = (TextView)layout.findViewById(R.id.txt_Mensaje1);
                    txtMsg.setText(e.getMessage());

                    toast.setGravity(Gravity.CENTER,0 ,0 );
                    toast.setDuration(Toast.LENGTH_LONG);
                    toast.setView(layout);
                    toast.show();

                }

            }
        });

        // Boton Cancelar
        Button cancelar = (Button) findViewById(R.id.btnSiguiente);
        cancelar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent volverMain = new Intent(AnadirCilindro.this, MainActivity.class);
                startActivity(volverMain);
            }
        });

    }

    /**
     * Validamos los datos introducidos si son correctos y devolvemos el cilindro
     * @return
     * @throws Exception
     */
    private Cilindro validarDatosYObtenerCilindro() throws Exception{

        Date fecha = new Date();
        String nombreCli = ((Spinner) findViewById(R.id.spinnerCli)).getSelectedItem().toString();
        String idCil = ((EditText)findViewById(R.id.editTextIdCilindro)).getText().toString();
        String marcaCil = ((EditText)findViewById(R.id.editTextMarca)).getText().toString();
        String obsv = ((EditText)findViewById(R.id.editTextObs)).getText().toString();
        RadioButton esSoldar = findViewById(R.id.rdbtnSi);
        Boolean soldar = false;

        Double medPist = 0.0d;
        Double diamCil = 0.0d;

        if (((EditText)findViewById(R.id.editTextDiam)).getText().toString() != null && !((EditText)findViewById(R.id.editTextDiam)).getText().toString().equals("")) {
            diamCil = Double.parseDouble(((EditText)findViewById(R.id.editTextDiam)).getText().toString());
        }

        if (((EditText)findViewById(R.id.editTextMed)).getText().toString() != null && !((EditText)findViewById(R.id.editTextMed)).getText().toString().equals("")) {
            medPist = Double.parseDouble(((EditText)findViewById(R.id.editTextMed)).getText().toString());
        }

        if(esSoldar.isChecked()){
            soldar = true;
        } else {
            soldar = false;
        }

        System.out.println(soldar);

        Cilindro cilindro = new Cilindro(fecha, nombreCli, idCil, marcaCil, diamCil, medPist, soldar, obsv);
        if(cilindro.getNombreCli().equals("Seleccionar")){
          throw new Exception("Error no se ha seleccionado el cliente");
        }if (cilindro.getMarcaCil().isEmpty()){
            throw new Exception("Error no se ha introducido la marca del cilindro");
        } if (cilindro.getDiamCil() == null || cilindro.getDiamCil() == 0.0d){
            throw new Exception("Error no se ha introducido el diametro del cilindro ");
        } if (cilindro.getMedPist() == null || cilindro.getMedPist() == 0.0d){
            throw new Exception("Error no se ha introducido la medida del pistón");
        }

        return cilindro;
    }

    // Vaciamos los campos los campos
    private void reset(){
        ((Spinner)findViewById(R.id.spinnerCli)).setSelection(0);
        ((EditText)findViewById(R.id.editTextIdCilindro)).setText(generarIdCil());
        ((EditText)findViewById(R.id.editTextMarca)).setText("");
        ((EditText)findViewById(R.id.editTextDiam)).setText("");
        ((EditText)findViewById(R.id.editTextMed)).setText("");
        ((RadioButton) findViewById(R.id.rdbtnNo)).setChecked(true);
        ((EditText)findViewById(R.id.editTextObs)).setText("");

    }

    // Generamos el Id del Cilindro
   private String generarIdCil() {
       // Letras MAYUSC en Ascii de la 65 a la 90

       String idCil = "";

       DBAHelper helper = new DBAHelper(this);
       ArrayList<Cilindro> lstCil = helper.getCilindros();

       // ID 1 = AAA --> 65 65 65
       int id = lstCil.size();
       int sum2num = 0;
       int sum3num = 0;

       Character idPos1 = 65;
       Character idPos2 = 65;
       Character idPos3 = 65;

       Boolean quieroSalir = true;
       String ret = "AAA";

       while (quieroSalir) {
           if (id >= 26) {
               sum2num = sum2num + 1;
               id = id - 26;

               if (sum2num >= 26) {
                   sum3num = sum3num + 1;
                   sum2num = sum2num - 26;

               }
           } else {
               String a3 = Character.toString((char) ((char) idPos3 + id));
               String a2 = Character.toString((char) ((char) idPos2 + sum2num));
               String a1 = Character.toString((char) ((char) idPos1 + sum3num));

               ret = a1 + a2 + a3;

               quieroSalir = false;
           }
       }
       return ret;
   }
}



