package com.example.app_maestria_ventas.services;

import com.example.app_maestria_ventas.models.ProductoModel;
import com.example.app_maestria_ventas.models.RespuestaGenerica;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ProductoService {
    String API_GET_LISTADO = "Api.php?apicall=verproductos";

    @GET(API_GET_LISTADO)
    Call<RespuestaGenerica<ProductoModel>> getProductos();
}
