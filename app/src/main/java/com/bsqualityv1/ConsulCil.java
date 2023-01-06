package com.bsqualityv1;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;


public class ConsulCil extends AppCompatActivity implements AdapterView.OnItemClickListener {


    private CilindroAdapter adaptador;
    private ArrayList<Cilindro> listaCilindros;
    private DBAHelper db;
    private ListView lv_lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listacil);

        db = new DBAHelper(this); // Conexion a la BBDD

        listaCilindros = new ArrayList<Cilindro>(); // Creamos Array para meter todos cilindros

        listaCilindros = db.getCilindros(); // Consultamos a la Bbdd y nos devuelve la totalidad de los cilindros en el array que hemos creado

        lv_lista = findViewById(R.id.lv_lista);

        adaptador = new CilindroAdapter(this, listaCilindros);

        lv_lista.setAdapter(adaptador);
        lv_lista.setOnItemClickListener(this);

        registerForContextMenu(lv_lista);

    }

    @Override // Obtengo el objeto Cilindro seleccionado y lo paso a la nueva Activity
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

        Cilindro cilindro = listaCilindros.get(position); //Obtenemos el cilindro que ha clicado el usuario y lo mandamos a la siguiente activity

        // Ejecuto la nueva Activity
        Intent visualizarCil = new Intent(ConsulCil.this, VisualizarCilindro.class);

        Bundle bundle = new Bundle();
        bundle.putSerializable("cilindro", cilindro); // Mandamos los datos a otra activity
        visualizarCil.putExtra("longitudLista", listaCilindros.size()); // Mandamos los datos a otra activity
        visualizarCil.putExtras(bundle);

        startActivity(visualizarCil);
    }
}
