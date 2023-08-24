package com.example.app_maestria_ventas.services;

import com.example.app_maestria_ventas.models.CategoriaModel;
import com.example.app_maestria_ventas.models.RespuestaGenerica;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CategoriaService {
    String API_ROUTE = "Api.php?apicall=verCategorias";

    @GET(API_ROUTE)
    Call<RespuestaGenerica<CategoriaModel>> getCategorias();
}
