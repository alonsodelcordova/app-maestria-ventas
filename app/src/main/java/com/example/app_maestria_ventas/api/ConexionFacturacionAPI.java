package com.example.app_maestria_ventas.api;

import com.example.app_maestria_ventas.services.TipoGasService;
import com.example.app_maestria_ventas.services.facturacion.ProveedorFacturacionService;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ConexionFacturacionAPI {
    public static Retrofit getConexion(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://apifacturacion.com/")
                .addConverterFactory(GsonConverterFactory.create(
                        new GsonBuilder().serializeNulls().create()
                ))
                .build();
        return retrofit;
    }

    public static ProveedorFacturacionService getProveedorFacturacionService(){
        return getConexion().create(ProveedorFacturacionService.class);
    }
}
