package com.example.pruebatecnica.Views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.pruebatecnica.R;
import com.example.pruebatecnica.Utils.Utils;

public class MainActivity extends AppCompatActivity {

    /**
     * Declaracion de variable de tipo AccesoFragment es el primer fragment que se mostrara
     */
    private AccesoFragment accesoFragment1 = new AccesoFragment(this);

    /**
     * Declaracion de constructor vacio
     */
    public MainActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    // region metod que cargara el fragment inicial
    private void init() {
        Utils.loadFragment(this, accesoFragment1, "home");
    }
    // endregion
}