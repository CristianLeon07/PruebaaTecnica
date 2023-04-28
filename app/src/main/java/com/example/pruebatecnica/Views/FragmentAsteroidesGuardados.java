package com.example.pruebatecnica.Views;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.pruebatecnica.Adapter.AsteroidesAdapterBD;
import com.example.pruebatecnica.BdLocal.AdminSQLite;
import com.example.pruebatecnica.Models.ModelBD;
import com.example.pruebatecnica.R;
import com.example.pruebatecnica.Utils.Utils;

import java.util.ArrayList;

public class FragmentAsteroidesGuardados  extends Fragment {

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
     * Declaracion de variable de tipo HomeFragment
     */
    private HomeFragment homeFragment;

    private Utils metodoReutilizable = new Utils();


    /**
     * Declaracion de Adaptador
     */
    public static AsteroidesAdapterBD asteroidesAdapter;

    /**
     * Declaracion de la lista
     */
    public ArrayList<ModelBD> infoAsteroides= new ArrayList<ModelBD>();

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
    private TextView btnCancelarConsulta;


    // endregion

    // region constructores
    public FragmentAsteroidesGuardados() {
    }
    public FragmentAsteroidesGuardados(MainActivity accesoActivity,HomeFragment homeFragment) {
        this.mainActivity = accesoActivity;
        this.homeFragment = homeFragment;

    }

    // endregion

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstacianeStatus) {
        view = inflater.inflate(R.layout.fragment_asteroides_guardados, container, false);
        initVariables();
        init();
        initEvents();
        return view;
    }

    // region metodo inicializacion y referenciacion de variables
    private void initVariables() {
        btnCancelarConsulta = view.findViewById(R.id.btnCancelarConsulta);
        rvAsteroides= view.findViewById(R.id.rvAsteroidesConsulta);
    }
    // endregion

    // region mettodo de logica, consulta al la bd, creacion de estrcutura de reciclerView
    @SuppressLint("SetTextI18n")
    private void init() {

        db = new AdminSQLite(getContext());
        infoAsteroides.clear();
        infoAsteroides.addAll(db.consultInfo(getContext(),db.consultUser("email", Utils.emailRegistrado)));
        rvAsteroides.setHasFixedSize(true);
        rvAsteroides.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        asteroidesAdapter = new AsteroidesAdapterBD(infoAsteroides, this );
        rvAsteroides.setAdapter(asteroidesAdapter);
        if (infoAsteroides.size() ==0){
            metodoReutilizable.mostrarAlertDialogo("Usted aun no ha realizado consultas; para realizar una consulta tienes que ingresar una fecha en la vista principa.",false,this);
        }

    }
    // endregion

    // region metodo eventos de escucha
    private void initEvents() {
        btnCancelarConsulta.setOnClickListener(v ->   homeFragment.getActivity().onBackPressed());
    }
    // endregion

}
