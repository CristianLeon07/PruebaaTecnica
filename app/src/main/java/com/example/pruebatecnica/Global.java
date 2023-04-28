package com.example.pruebatecnica;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.pruebatecnica.ApiInterface.ApiServicePrueba;
import com.example.pruebatecnica.BdLocal.AdminSQLite;
import com.example.pruebatecnica.Models.EventDate;
import com.example.pruebatecnica.Models.ModelResponseEndpoint;
import com.example.pruebatecnica.Utils.Utils;
import com.example.pruebatecnica.Views.HomeFragment;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.Iterator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Global {
    /**
     * creacion constructor vacio de la clase
     */
    public Global() {
    }

    //region declaracion de variables finales

    /**
     * url base para el consumo de la api
     */
    public static final String URL_BASE = "http://api.nasa.gov/neo/rest/v1/feed/";

    /**
     * Valor Api_key asignada
     */
    private static final String API_KEY = "sOS7Tgy1R1NqWJYygzTMacg0bmMCsoR8dcV32gf9";

    /**
     * Valor fecha inicial, para esta prueba es la fecha en la que se me envio la prueba tecnica
     */
    private static final String star_date = "2023-4-25";

    private Utils metodoReutilizable = new Utils();

    //endregion

    //region estructura inicial de retrofit, para hacer la consula

    public static ApiServicePrueba servicios() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(URL_BASE)
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().serializeNulls().create()
                )).build();
        return retrofit.create(ApiServicePrueba.class);
    }
    // endregion

    // region metodo cuerpo del retrofit, retorna respuesta

    public static void callEndpoint(String endpoint) {
        try {
            Call<ModelResponseEndpoint> call = null;
            switch (endpoint) {
                case "getConsultaGloblal": {
                    call = servicios().getConsulta(star_date, HomeFragment.getmInstance().getDateFinish(), API_KEY);
                    call.request().url().toString();
                    break;
                }
            }
            call.enqueue(new Callback<ModelResponseEndpoint>() {
                @Override
                public void onResponse(Call<ModelResponseEndpoint> call, Response<ModelResponseEndpoint> response) {
                    if (response.isSuccessful()) {

                        Gson gson = new Gson();
                        ArrayList<EventDate> listaEventDate = new ArrayList<>();
                        JsonObject jsonObject = new JsonObject();
                        jsonObject = gson.toJsonTree(response.body().getNear_earth_objects()).getAsJsonObject();
                        Iterator<String> iter = jsonObject.keySet().iterator();
                        while (iter.hasNext()) {
                            JsonArray valor = jsonObject.getAsJsonArray(iter.next());
                            for (int i = 0; i < valor.size(); i++) {
                                listaEventDate.add(new Gson().fromJson(valor.get(i).getAsJsonObject(), EventDate.class));
                            }
                        }
                        HomeFragment.getmInstance().infoAsteroides.addAll(listaEventDate);
                        HomeFragment.getmInstance().asteroidesAdapter.notifyDataSetChanged();

                        Thread segundoHilo = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                HomeFragment.getmInstance().infoAsteroides.addAll(listaEventDate);
                                HomeFragment.getmInstance().asteroidesAdapter.notifyDataSetChanged();
                                saveAsteroides(listaEventDate);
                            }
                        });
                        segundoHilo.start();

                    }
                }
                @Override
                public void onFailure(Call<ModelResponseEndpoint> call, Throwable t) {
                    Log.e("endpoint", endpoint + " " + t.getMessage());

                }
            });
        } catch (Exception e) {
            Log.i("endpoint", endpoint + " " + e.getMessage());
            System.out.println("");
        }

    }
    // endregion

    //region metodo de respuesta retrofit

    public static void saveAsteroides(ArrayList<EventDate> eventDates) {
        AdminSQLite db = new AdminSQLite(HomeFragment.getmInstance().getContext());
        for (EventDate evenDate : eventDates) {
            db.registerAteroids(evenDate.getId(), evenDate.getName(), evenDate.getEstimated_diameter().getMeters().getEstimated_diameter_max(), evenDate.isIs_potentially_hazardous_asteroid() ? 1 : 0);
        }
    }

    // endregion

}
