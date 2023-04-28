package com.example.pruebatecnica.BdLocal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.pruebatecnica.Models.ModelBD;
import com.example.pruebatecnica.R;
import com.example.pruebatecnica.Utils.DBManager;

import java.util.ArrayList;
import java.util.List;

public class AdminSQLite extends SQLiteOpenHelper {
    /**
     * Variable que captura el numero de version de la base de datos
     */
    private static final int DATABASE_VERSION = 2;
    /**
     * Variable que contiene el nombre de la base de datos
     */
    private static final String DATABASE_NAME = "Prueba.bd";

    /**
     * inicia la base de datos
     *
     * @param context contexto de la aplicacion
     */
    public AdminSQLite(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.i(context.getString(R.string.Tag), "DATABASE_VERSION " + DATABASE_VERSION);
    }


    public void onCreate(SQLiteDatabase db) {
        try {
            createDB(db);
        } catch (Exception e) {
            Log.e("prueba", DATABASE_VERSION + e.getMessage());
        }

    }

    public void createDB(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table users(id integer primary key autoincrement, email text, encrypted_password text , first_name text, last_name text,  identification text, created_at text, updated_at text)");
        sqLiteDatabase.execSQL("create table Asteroids(id integer primary key autoincrement , id_Asteroid text, name, diametro_Metros text, peligro INTEGER DEFAULT 0)");
        sqLiteDatabase.execSQL("create table AsteroidsPorUser(idUser integer , idAsteroid text)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }

    // region metodo para registrar los datos del usuario
    public boolean registeruser(String email, String encrypted_password, String first_name, String last_name, String identification, String created_at, String updated_at) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues register = new ContentValues();
        register.put("email", email);
        register.put("encrypted_password", encrypted_password);
        register.put("first_name", first_name);
        register.put("last_name", last_name);
        register.put("identification", identification);
        register.put("created_at", created_at);
        register.put("updated_at", updated_at);

        Long results = db.insert("users", null, register);
        if (results == -1) {
            return false;
        } else
            return true;
    }

    // endregion

    // region metodo para verificar el correo que se esta registrando,  no este registrado.

    public boolean verifyUser(String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from users where email=?", new String[]{email});
        if (cursor.getCount() > 0) {
            return true;
        } else {
            return false;
        }
    }
    // endregion

    // region metodo para validar que las credenciales que se ingresan sean las correctas

    public boolean verifyCredential(String email, String encrypted_password) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from users where email=? and encrypted_password =? ", new String[]{email, encrypted_password});
        if (cursor.getCount() > 0) {
            return true;
        } else {
            return false;
        }
    }
    // endregion

    // region metodo para obtener el nombre del usuario que ingreso al sistema

    public String consultUser(String campo, String email) {
        try {
            String retorno = "";
            SQLiteDatabase db = this.getWritableDatabase();

            Cursor c = db.rawQuery("SELECT "+campo+" FROM users WHERE email = '" + email + "'", null);
            if (c.getCount() > 0) {
                c.moveToFirst();
                retorno = c.getString(0);
            }
            return retorno;

        } catch (Exception e) {
            return "0";
        }
    }
    // endregion

    // region metodo para registrar los datos del usuario
    public boolean registerAteroids(String id_Asteroid, String name, double diametro_Metros, int peligro) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues registerAsteroid = new ContentValues();
        registerAsteroid.put("id_Asteroid", id_Asteroid);
        registerAsteroid.put("name", name);
        registerAsteroid.put("diametro_Metros", diametro_Metros);
        registerAsteroid.put("peligro", peligro);

        Long results = db.insert("Asteroids", null, registerAsteroid);
        if (results == -1) {
            return false;
        } else
            return true;
    }
    // endregion
    public boolean userAndAsteroid(int idUser, String idAsteroid) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues registerInfo = new ContentValues();
        registerInfo.put("idUser", idUser);
        registerInfo.put("idAsteroid", idAsteroid);

        Long results = db.insert("AsteroidsPorUser", null, registerInfo);
        if (results == -1) {
            return false;
        } else
            return true;
    }

    public List<ModelBD> consultInfo(Context context, String email) {
        try {
            List<ModelBD> retorno = new ArrayList<>();
            SQLiteDatabase db = this.getWritableDatabase();

            // SELECT DISTINCT A.id_Asteroid, A.name, A.diametro_Metros, A.peligro FROM  Asteroids A, users U INNER JOIN AsteroidsPorUser AU ON AU.idAsteroid = A.id_Asteroid WHERE U.email = 'cristianvelasques.07@gmail.com'

            Cursor c = db.rawQuery("SELECT DISTINCT A.id_Asteroid, A.name, A.diametro_Metros, A.peligro FROM  Asteroids A, users U INNER JOIN AsteroidsPorUser AU ON AU.idAsteroid = A.id_Asteroid WHERE U.email = '" + email + "'", null);
            if (c != null && c.moveToFirst()) {
                do {
                    retorno.add(new ModelBD(c.getString(0),c.getString(1),c.getDouble(2),c.getInt(3)));
                } while (c.moveToNext());
            }

            return retorno;

        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

}
