package com.example.pruebatecnica.Views;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.pruebatecnica.BdLocal.AdminSQLite;
import com.example.pruebatecnica.R;
import com.example.pruebatecnica.Utils.Utils;

public class AccesoFragment extends Fragment {

    //region declaracion de variables

    /**
     * Declaracion de variable de tipo view que retornara la vista del fragment
     */
    private View view;

    /**
     * declaracion de la clase AdminSQLite donde esta creada la BD y las consultas
     */
    private AdminSQLite db;

    /**
     * declaracion de la clase Util, para poder acceder al metodo de mostrar dialogo.
     */
    private Utils metodoReutilizable = new Utils();

    /**
     * declaracion de la variable que recibira el correo del usuario proveniente de la consulta
     */
    public static String emailUser = "";

    /**
     * delaracion de un boolean para validar datos
     */
    private boolean verifyData = false;

    /**
     * declaracion de la ActivityMain donde se carga los fragment
     */
    private MainActivity mainActivity;

    /**
     * declaracion de variables de elementos visuales de tipo Editext
     */
    private EditText etEmail, etPass;

    /**
     * declaracion de variables de elementos visuales de tipo Button
     */
    private Button btnLogin;
    /**
     * declaracion de variables de elementos visuales de tipo TextView
     */
    private TextView btnRegister;

    // endregion

    //region declaracion de constructores

    /**
     * declaracion de constructor del fragment vacio
     */
    public AccesoFragment() {
    }

    /**
     * declaracion del constructor con parametros del fragment
     */

    public AccesoFragment(MainActivity accesoActivity) {
        this.mainActivity = accesoActivity;
    }
    //endregion

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstacianeStatus) {
        view = inflater.inflate(R.layout.fragment_acceso, container, false);
        initVariables();
        initEvents();
        return view;
    }

    //region metodo donde se inicializan y bindean elementos visuales
    private void initVariables() {
        etEmail = view.findViewById(R.id.etEmail);
        etPass = view.findViewById(R.id.etPass);
        btnLogin = view.findViewById(R.id.btnLogin);
        btnRegister = view.findViewById(R.id.btnRegister);
    }
    // endregion

    // region metodo init, donde esta la logica de las consultas, validaciones etc.
    private void init() {
        db = new AdminSQLite(getContext());
        String email = etEmail.getText().toString();
        String pass = etPass.getText().toString();
        if (email.equals("") || pass.equals("")) {
            metodoReutilizable.mostrarAlertDialogo("Campos vacios, por favor ingrese los datos", false, this);

        } else {
            verifyData = db.verifyCredential(email, pass);
            if (verifyData) {
                emailUser = email;
                metodoReutilizable.mostrarAlertDialogo("Ingreso Exitoso!!", true, this);
                Utils.emailRegistrado =email;
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Utils.loadFragment(mainActivity, new HomeFragment(mainActivity, AccesoFragment.this, emailUser), "home");
                    }
                }, 3000);

                etEmail.setText("");
                etPass.setText("");
            } else if (email != emailUser) {

                metodoReutilizable.mostrarAlertDialogo("Verifica los datos ingresados. E intente nuevamente", false, this);

            }
        }
    }
    // endregion

    // region metodo donde estan todos los eventos de escucha ejemplo motones

    private void initEvents() {
        btnLogin.setOnClickListener(v -> {
            init();
        });

        btnRegister.setOnClickListener(v -> {
            Utils.loadFragment(mainActivity, new RegistroUsuarioFragment(mainActivity, AccesoFragment.this), "IrRegistro");
        });
    }
    // endregion

}
