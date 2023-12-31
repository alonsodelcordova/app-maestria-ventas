package com.example.app_maestria_ventas.services;

import com.example.app_maestria_ventas.models.RespuestaGenerica;
import com.example.app_maestria_ventas.models.TipoGas;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface TipoGasService {

    String API_ROUTE = "Api.php?apicall=tipoGas";

    @GET(API_ROUTE)
    Call<RespuestaGenerica<TipoGas>> getPost();

    @POST(API_ROUTE)
    Call<RespuestaGenerica> guardarTipoGas(@Body TipoGas envio);

}
