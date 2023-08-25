package com.example.app_maestria_ventas.api;

import com.example.app_maestria_ventas.services.CategoriaService;
import com.example.app_maestria_ventas.services.ClienteService;
import com.example.app_maestria_ventas.services.ProveedorService;
import com.example.app_maestria_ventas.services.TipoGasService;
import com.example.app_maestria_ventas.services.UsuarioService;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ConexionAPI {

    public static Retrofit getConexion(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.100.7:80/api-maestria-ventas/")
                .addConverterFactory(GsonConverterFactory.create(
                        new GsonBuilder().serializeNulls().create()
                ))
                .build();
        return retrofit;
    }


    public static TipoGasService getTipoGasService(){
        return getConexion().create(TipoGasService.class);
    }

    public static CategoriaService getCategoryService(){
        return getConexion().create(CategoriaService.class);
    }

    public static UsuarioService getUsuarioService(){
        return getConexion().create(UsuarioService.class);
    }

    public static ClienteService getClienteService(){
        return getConexion().create(ClienteService.class);
    }

    public static ProveedorService getProveedorService(){
        return getConexion().create(ProveedorService.class);
    }


}
