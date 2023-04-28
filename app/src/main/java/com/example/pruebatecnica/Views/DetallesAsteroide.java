package com.example.pruebatecnica.Views;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.pruebatecnica.Models.EventDate;
import com.example.pruebatecnica.R;
import com.example.pruebatecnica.Utils.Utils;

public class DetallesAsteroide extends Fragment {

    // region declaracion de variables;

    /**
     * Declaracion de variable de tipo view que retornara la vista del fragment
     */
    private View view;

    /**
     * Declaracion de variable de tipo AccesoFragment
     */
    private HomeFragment homeFragment;


    private Utils metodoReutilizable = new Utils();

    /**
     * Declaracion de variable de tipo HomeFragment
     */
    private static DetallesAsteroide mInstance;

    /**
     * Declaracion de la lista
     */
    private EventDate infoAsteroides= new EventDate();

    /**
     * declaracion de variables de elementos visuales de tipo TexView
     */
    private TextView nombreAsteroide, tvmagnitudAbsoluta,tvDiametroEstimado,tvVelocidad,tvDistancia,tvFecha,tvPeligro,btnAtras;

    // endregion

    // region constructores
    public DetallesAsteroide() {
    }

    public DetallesAsteroide(HomeFragment homeFragment) {
        this.homeFragment = homeFragment;
    }

    // endregion

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstacianeStatus) {
        view = inflater.inflate(R.layout.fragment_detail_asteroide, container, false);
        initVariables();
        init();
        initEvents();
        return view;
    }

    // region metodo inicializacion y referenciacion de variables
    private void initVariables() {

        nombreAsteroide = view.findViewById(R.id.nombreAsteroide);
        tvmagnitudAbsoluta = view.findViewById(R.id.tvmagnitudAbsoluta);
        tvDiametroEstimado = view.findViewById(R.id.tvDiametroEstimado);
        tvVelocidad= view.findViewById(R.id.tvVelocidad);
        tvDistancia= view.findViewById(R.id.tvDistancia);
        tvFecha = view.findViewById(R.id.tvFecha);
        tvPeligro = view.findViewById(R.id.tvPeligro);
        btnAtras = view.findViewById(R.id.btnAtras);

    }
    // endregion

    // region mettodo de logica, consulta al variable global que trae la info de los asteroides.

    @SuppressLint("SetTextI18n")
    private void init() {
        mInstance = this;
        btnAtras.setText("< Regresar");

        infoAsteroides = Utils.asteroideSelect;
        nombreAsteroide.setText(infoAsteroides.getName());
        tvmagnitudAbsoluta.setText(String.valueOf(infoAsteroides.getAbsolute_magnitude_h()));
        tvDiametroEstimado.setText(metodoReutilizable.printDecimal(infoAsteroides.getEstimated_diameter().getMeters().getEstimated_diameter_max())+" Metros");
        tvVelocidad.setText(metodoReutilizable.printDecimalString(infoAsteroides.getClose_approach_data().get(0).getRelative_velocity().getKilometers_per_hour())+" Km/H");
        tvDistancia.setText(metodoReutilizable.printDecimalString(infoAsteroides.getClose_approach_data().get(0).getMiss_distance().getKilometers())+" Km");
        tvFecha.setText(infoAsteroides.getClose_approach_data().get(0).getClose_approach_date_full());
        if (infoAsteroides.isIs_potentially_hazardous_asteroid()){
            tvPeligro.setText("Potencialmente peligroso");
        }else {
            tvPeligro.setText("No representa peligro a la tierra");
        }


    }
    // endregion

    // region metodo eventos de escucha
    private void initEvents() {
        btnAtras.setOnClickListener(v ->
                homeFragment.getActivity().onBackPressed());
    }
    // endregion

}

