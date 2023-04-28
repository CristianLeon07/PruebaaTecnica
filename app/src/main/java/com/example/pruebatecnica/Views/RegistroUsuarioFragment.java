package com.example.pruebatecnica.Views;

import android.os.Bundle;
import android.os.Handler;
import android.util.Patterns;
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

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegistroUsuarioFragment extends Fragment {

    // region declaracion de variables
    /**
     * Declaracion de variable de tipo view que retornara la vista del fragment
     */
    View view;
    /**
     * Declaracion de variable de tipo Utils para tener acceso al dialogo informativo
     */
    private Utils metodoReutilizable = new Utils();

    /**
     * Declaracion de variable de tipo MainActivity donde se soportara el fragment
     */
    private MainActivity mainActivity;

    /**
     * Declaracion de variable de tipo AccesoFragment para obtener la referencia del fragment a donde se redirige una ves echo el registro
     */
    private  AccesoFragment accesoFragment;

    /**
     * Declaracion de variable de tipo AdminSQLite para accedeer y consultar la BD y registrar usuario.
     */
    private AdminSQLite db;

    /**
     * Declaracion de variable de tipo EditText.
     */
    private EditText etEmail, etPass, etName, etFirstName, etIdentification;

    /**
     * Declaracion de variable de tipo Button.
     */
    private Button btnRegister;

    /**
     * Declaracion de variable de tipo TextView.
     */
    private TextView btnAtras;

    /**
     * Declaracion de variable de tipo String.
     */
    String currentDateandTime = "";

    // endregion

    // region creacion de costructores del fragment

    public RegistroUsuarioFragment(MainActivity mainActivity,AccesoFragment accesoFragment) {
        this.mainActivity = mainActivity;
        this.accesoFragment =accesoFragment;
    }

    public RegistroUsuarioFragment() {
    }

    // endregion

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstacianeStatus) {
        view = inflater.inflate(R.layout.fragment_registro_usuario, container, false);
        initVariables();
        initEvents();
        return view;
    }

    // region metodo inicializacion de variables y obtencion de la fecha en la que se hace el registro
    private void initVariables() {
        btnAtras = view.findViewById(R.id.btnAtras);
        etName = view.findViewById(R.id.etName);
        etEmail = view.findViewById(R.id.etEmail);
        etPass = view.findViewById(R.id.etPass);
        etFirstName = view.findViewById(R.id.etFirstName);
        etIdentification = view.findViewById(R.id.etIdentification);
        btnRegister = view.findViewById(R.id.btnRegister);

        btnAtras.setText("< Cancelar");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        currentDateandTime = simpleDateFormat.format(new Date());

    }
    // endregion

    // region metodo de eventos de escucha
    private void initEvents() {
        btnAtras.setOnClickListener(v -> Utils.loadFragment(mainActivity, new AccesoFragment(mainActivity), "Atras"));

        btnRegister.setOnClickListener(v -> registrar());

    }
    // endregion

    // region metodo de registro, donde se inserta datos a la bd y hacen las validacions correspondiente

    private void registrar() {
        db = new AdminSQLite(getContext());
        String regEmail = etEmail.getText().toString().trim();
        String regPass = etPass.getText().toString().trim();
        String regName = etName.getText().toString().trim();
        String regFirstName = etFirstName.getText().toString().trim();
        String regIdentification = etIdentification.getText().toString();
        String regCreateAd = currentDateandTime;

        try {
            Pattern patternName = Pattern.compile("[a-z A-Z]{1,30}");
            Matcher matcherName = patternName.matcher(regName);
            boolean nombreRegex = matcherName.matches(); // Devuelve true si el textToSearch coincide exactamente con la expresión regular


            if (regEmail.equals("") || regPass.equals("") || regName.equals("") || regFirstName.equals("") || regIdentification.equals("")) {
                metodoReutilizable.mostrarAlertDialogo("Campos vacios, por favor ingrese los datos", false, this);
                return;
            } else {
                if (!nombreRegex) {
                    metodoReutilizable.mostrarAlertDialogo("El campo Nombre no admite valores numericos", false, this);
                    throw new Exception("No se puede guardar este nombre");
                }
                boolean verificar = db.verifyUser(regEmail);
                if (this.isEmail(etEmail.getText().toString())) {
                    if (etPass.length() >= 5) {
                        if (!verificar) {
                            boolean insert = db.registeruser(regEmail, regPass, regName, regFirstName, regIdentification, regCreateAd, regCreateAd);

                            if (insert) {
                                metodoReutilizable.mostrarAlertDialogo("Registro Exitoso", true, RegistroUsuarioFragment.this);

                                etEmail.setText("");
                                etPass.setText("");
                                etName.setText("");
                                etFirstName.setText("");
                                etIdentification.setText("");
                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        Utils.loadFragment(mainActivity, new AccesoFragment(mainActivity), "Acceso");
                                    }
                                }, 3500);


                            } else {
                                metodoReutilizable.mostrarAlertDialogo("Este Usuario no pudo ser registrado intente mas tarde", false, this);
                            }
                        } else {
                            metodoReutilizable.mostrarAlertDialogo("Este Usuario ya eta registrado, intenta con otro cuenta", false, this);
                        }

                    } else {
                        metodoReutilizable.mostrarAlertDialogo("Ingrese una contraseña mas larga", false, this);
                    }
                } else {
                    metodoReutilizable.mostrarAlertDialogo("Ingrese un Correo electronico valido", false, this);
                }
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

    }
    // endregion

    // region metodo para valicar que el correo ingresado
    public boolean isEmail(String cadena) {
        boolean resultado;
        if (Patterns.EMAIL_ADDRESS.matcher(cadena).matches()) {
            resultado = true;
        } else {
            resultado = false;
        }
        return resultado;
    }
    // endregion

}
