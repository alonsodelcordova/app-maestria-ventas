package com.example.app_maestria_ventas.api;

import com.example.app_maestria_ventas.services.TipoGasService;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ConexionAPI {

    public static Retrofit getConexion(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.130:80/api-maestria-ventas/")
                .addConverterFactory(GsonConverterFactory.create(
                        new GsonBuilder().serializeNulls().create()
                ))
                .build();
        return retrofit;
    }


}
