package com.example.pruebatecnica.Utils;

import static java.lang.String.format;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.example.pruebatecnica.Models.EventDate;
import com.example.pruebatecnica.Views.AlertDialogo;
import com.example.pruebatecnica.R;

import java.text.DecimalFormat;

public class Utils {

    public  static EventDate asteroideSelect = new EventDate();
    public  static  String emailRegistrado = "";

    // region metodo donde esta la estructura de cargar los fragments

    public static void loadFragment (FragmentActivity activity, Fragment fragment, String tag) {
        if (fragment != null) {
            activity.getSupportFragmentManager().beginTransaction()
                    .replace(R.id.contenedor_acceso, fragment, tag)
                    .addToBackStack(tag)
                    .commitAllowingStateLoss();
        }
    }
    //endregion

    // region metodo que hace llamado al dialogo informativo

    public void mostrarAlertDialogo(String titulo, boolean exitoso, Fragment fragment) {
        AlertDialogo alertDialogo = new AlertDialogo(titulo, exitoso);
        alertDialogo.setCancelable(true);
        alertDialogo.show(fragment.getFragmentManager(), "opciones dialog");
    }
    // endregion

    // region metodo para acortar un numero double muy extenso, se imprime dos caracteres despues del punto

    public String printDecimal(double valor){
        DecimalFormat format = new DecimalFormat();
        format.setMaximumFractionDigits(2); //Define 2 decimales.
        return format.format(valor);
    }
    // endregion

    // region metodo para acortar una cadena muy extenso, se imprime dos caracteres despues del punto

    public String printDecimalString(String valor){
        int indicePunto = valor.indexOf(".");
        String caracteresDespuesDelPunto = valor.substring(indicePunto,indicePunto+3);
        caracteresDespuesDelPunto = valor.split("\\.")[0]+caracteresDespuesDelPunto;

        return  caracteresDespuesDelPunto;
    }
    // endregion
}
