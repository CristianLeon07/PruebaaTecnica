package com.example.pruebatecnica.Views;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.pruebatecnica.Adapter.AsteroidesAdapter;
import com.example.pruebatecnica.BdLocal.AdminSQLite;
import com.example.pruebatecnica.Global;
import com.example.pruebatecnica.Models.EventDate;
import com.example.pruebatecnica.R;
import com.example.pruebatecnica.Utils.Utils;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    // region declaracion de variables;

    /**
     * Declaracion de variable de tipo view que retornara la vista del fragment
     */
    private View view;

    /**
     * Declaracion de variable de tipo Activity main, donde se soportar el fragment
     */
    private MainActivity mainActivity;

    /**
     * Declaracion de variable de tipo AccesoFragment
     */
    private AccesoFragment accesoFragment;

    /**
     * Declaracion de variable de tipo Utils para tener acceso al dialogo informativo
     */
    private Utils metodoReutilizable = new Utils();


    /**
     * Declaracion de variable de tipo HomeFragment
     */
    private static HomeFragment mInstance;

    /**
     * Declaracion de Adaptador
     */
    public static AsteroidesAdapter asteroidesAdapter;

    private DetallesAsteroide asteroideDetail;

    /**
     * Declaracion de la lista
     */
    public ArrayList<EventDate>infoAsteroides= new ArrayList<EventDate>();

    /**
     * declaracion de la clase AdminSQLite donde esta creada la BD y las consultas
     */
    private AdminSQLite db;

    /**
     * declaracion de variables de elementos visuales de tipo RecyclerView
     */
    private static RecyclerView rvAsteroides;

    /**
     * declaracion de variables de elementos visuales de tipo TexView
     */
    private TextView tvNameUser, tvDateFechaFin,btnCerrarSesion,btnConsuta;

    /**
     * declaracion de variables de elementos visuales de tipo Button
     */
    private Button btnDate;

    /**
     * declaracion de la variable que recibira el correo del usuario proveniente de la consulta
     */
    private String dateFinish = "",email ="";

    // endregion

    // region creacion de instancia del fragment

    public static HomeFragment getmInstance() {
        return mInstance;
    }

    // endregion

    // region constructores
    public HomeFragment() {
    }

    public HomeFragment(MainActivity accesoActivity, AccesoFragment accesoFragment, String email) {
        this.mainActivity = accesoActivity;
        this.accesoFragment = accesoFragment;
        this.email = email;
    }

    // endregion

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstacianeStatus) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        initVariables();
        init();
        initEvents();
        return view;
    }

    // region metodo inicializacion y referenciacion de variables
    private void initVariables() {
        tvNameUser = view.findViewById(R.id.tvNameUser);
        btnCerrarSesion = view.findViewById(R.id.btnCerrarSesion);
        tvDateFechaFin = view.findViewById(R.id.tvDateFechaFin);
        btnDate = view.findViewById(R.id.btnDate);
        rvAsteroides= view.findViewById(R.id.rvAsteroides);
        btnConsuta = view.findViewById(R.id.btnConsuta);
    }
    // endregion

    // region mettodo de logica, consulta al la bd, creacion de estrcutura de reciclerView
    @SuppressLint("SetTextI18n")
    private void init() {
        mInstance = this;
        db = new AdminSQLite(getContext());
        tvNameUser.setText(db.consultUser("first_name",email) + "!");

        rvAsteroides.setHasFixedSize(true);
        rvAsteroides.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        asteroidesAdapter = new AsteroidesAdapter(infoAsteroides, HomeFragment.this);
        rvAsteroides.setAdapter(asteroidesAdapter);

    }
    // endregion

    // region metodo eventos de escucha
    private void initEvents() {
        btnCerrarSesion.setOnClickListener(v -> Utils.loadFragment(mainActivity, new AccesoFragment(mainActivity), "CerraSesion"));

        btnDate.setOnClickListener(v -> datPickerDialogo());

        btnConsuta.setOnClickListener(v -> Utils.loadFragment(mainActivity, new FragmentAsteroidesGuardados(mainActivity, HomeFragment.this),"consulta"));

    }
    private void datPickerDialogo(){
        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),
                (view, year, month, dayOfMonth) -> {

                    dateFinish =String.valueOf(year) + "-" + String.valueOf(month) + "-" + String.valueOf(dayOfMonth);
                    tvDateFechaFin.setText(dateFinish);
                    Global.callEndpoint("getConsultaGloblal");
                    metodoReutilizable.mostrarAlertDialogo("Estamos consultando",true,this);
                },2023, 04, 25);

        datePickerDialog.show();
    }
    // endregion


    // region getter de variables para enviar parametro a la consulta
    public String getDateFinish() {
        return dateFinish;
    }
    // endregion

}
