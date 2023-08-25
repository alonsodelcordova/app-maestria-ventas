package com.example.app_maestria_ventas.services;

import com.example.app_maestria_ventas.models.CategoriaModel;
import com.example.app_maestria_ventas.models.RespuestaGenerica;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface CategoriaService {
    String API_GET_LISTADO = "Api.php?apicall=verCategorias";
    String API_POST_CREATE = "Api.php?apicall=createcategoria";

    @GET(API_GET_LISTADO)
    Call<RespuestaGenerica<CategoriaModel>> getCategorias();

    @POST(API_POST_CREATE)
    Call<RespuestaGenerica<CategoriaModel>> createCategory(@Body CategoriaModel dataModal);

}
