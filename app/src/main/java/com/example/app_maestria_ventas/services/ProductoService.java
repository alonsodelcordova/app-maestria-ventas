package com.example.app_maestria_ventas.services;

import com.example.app_maestria_ventas.models.ClienteModel;
import com.example.app_maestria_ventas.models.ProductoModel;
import com.example.app_maestria_ventas.models.RespuestaGenerica;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ProductoService {

    String API_GET_LISTADO = "Api.php?apicall=verproductos";

    String API_POST_CREATE = "Api.php?apicall=grabaProducto";

    @GET(API_GET_LISTADO)
    Call<RespuestaGenerica<ProductoModel>> getProducto();
    @POST(API_POST_CREATE)
    Call<RespuestaGenerica<ProductoModel>> createProducto(@Body ProductoModel dataModal);
}
