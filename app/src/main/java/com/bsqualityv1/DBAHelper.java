package com.bsqualityv1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import static android.provider.BaseColumns._ID;

import java.util.ArrayList;


class DBAHelper extends SQLiteOpenHelper {
      public static final String DATABASE_NAME = "BsQuality.db";
      public static final String CILINDROS_TABLE_NAME = "Cilindros";
      public DBAHelper(Context context) {
          super(context,DATABASE_NAME,null,1);
      }

        @Override // Creamos la tabla de la Base de datos
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(
                    " CREATE TABLE " + CILINDROS_TABLE_NAME + " ( id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                                                              " fecha DATE NOT NULL," +
                                                              " nombreCli VARCHAR(50) NOT NULL, " +
                                                              " idCilindro VARCHAR(20) NOT NULL," +
                                                              " marcaCil VARCHAR(50) NOT NULL," +
                                                              " diamCil DOUBLE NOT NULL," +
                                                              " medPist DOUBLE NOT NULL," +
                                                              " soldar BOOLEAN NOT NULL, " +
                                                              " obsv VARCHAR(200) )"
            );
        }

        public ArrayList <Cilindro> getCilindros() { // Devolvemos todos los cilindros mediante una consulta, para posteriormente mostrarlos por pantalla
          SQLiteDatabase db = getReadableDatabase();
          final String[] SELECT = {"id", "fecha", "nombreCli", "idCilindro",  "marcaCil", "diamCil", "medPist", "soldar", "obsv"}; // Aqui ponemos lo que queremos que nos muestre
          Cursor c = db.query(CILINDROS_TABLE_NAME, SELECT, null, null,null, null,null,null); // Hacemos la query
          ArrayList<Cilindro> listaCilindros = new ArrayList<Cilindro>(); // Creamos el Array donde almacenaremos los Cilindros
          Cilindro cilindro= null; // Creamos el cilindro vacio para posteriormente llenarlos y guardarlos
          while(c.moveToNext()){ // Añadimos todos los cilindros hasta que ya no haya siguiente
              cilindro = new Cilindro(c.getInt(0),c.getString(1), c.getString(2), c.getString(3), c.getString(4), c.getDouble(5), c.getDouble(6), c.getString(7),c.getString(8));

              listaCilindros.add(cilindro);
          }
          c.close();
          db.close();

          return listaCilindros; // Devolvemos el total de Objetos cilindro
        }

    public Cilindro getCilindro(int id) {
        SQLiteDatabase db = getReadableDatabase();
        Cilindro cilindro= null;
        Cursor c = db.rawQuery("SELECT * FROM Cilindros WHERE id = " + id, null);
        while(c.moveToNext()){
            cilindro = new Cilindro(c.getInt(0),c.getString(1), c.getString(2), c.getString(3), c.getString(4), c.getDouble(5), c.getDouble(6), c.getString(7),c.getString(8));
        }

        c.close();
        db.close();

        return cilindro;
    }




        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS "+CILINDROS_TABLE_NAME);
            onCreate(db);
        }

        public boolean insert(Cilindro cil) {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put("idCilindro", cil.getIdCil());
            contentValues.put("nombreCli", cil.getNombreCli());
            contentValues.put("fecha", String.valueOf(cil.getFecha()));
            contentValues.put("marcaCil", cil.getMarcaCil());
            contentValues.put("diamCil", cil.getDiamCil());
            contentValues.put("medPist", cil.getMedPist());
            contentValues.put("soldar", cil.getSoldar());
            contentValues.put("obsv", cil.getObsv());
            db.insertOrThrow(CILINDROS_TABLE_NAME, null, contentValues);
            return true;
        }
    }

