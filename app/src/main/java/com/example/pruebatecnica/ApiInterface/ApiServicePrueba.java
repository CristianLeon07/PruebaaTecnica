package com.example.pruebatecnica.ApiInterface;

import static com.example.pruebatecnica.Global.URL_BASE;

import com.example.pruebatecnica.Models.ModelResponseEndpoint;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface ApiServicePrueba {
    @GET(URL_BASE)
    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    Call<ModelResponseEndpoint> getConsulta(
            @Query("start_date") String start_date,
            @Query("end_date") String end_date,
            @Query("api_key") String api_key);
}
