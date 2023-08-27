package com.example.app_maestria_ventas.services;

import com.example.app_maestria_ventas.models.RespuestaGenerica;
import com.example.app_maestria_ventas.models.VentasModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface VentaService {

    String API_GET_LISTADO = "Api.php?apicall=verVentas";
    String API_POST_CREATE = "Api.php?apicall=grabarVenta";
    @GET(API_GET_LISTADO)
    Call<RespuestaGenerica<VentasModel>> getVentas();
    @POST(API_POST_CREATE)
    Call<RespuestaGenerica<VentasModel>> createVenta(@Body VentasModel dataModal);
}
