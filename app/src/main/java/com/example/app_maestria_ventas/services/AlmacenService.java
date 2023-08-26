package com.example.app_maestria_ventas.services;

import com.example.app_maestria_ventas.models.AlmacenModel;
import com.example.app_maestria_ventas.models.RespuestaGenerica;

import retrofit2.Call;
import retrofit2.http.GET;

public interface AlmacenService {
    String API_GET_LISTADO = "Api.php?apicall=verAlmacen";

    @GET(API_GET_LISTADO)
    Call<RespuestaGenerica<AlmacenModel>> getAlmacenes();
}
