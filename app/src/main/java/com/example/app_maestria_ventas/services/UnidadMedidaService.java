package com.example.app_maestria_ventas.services;
import com.example.app_maestria_ventas.models.RespuestaGenerica;
import com.example.app_maestria_ventas.models.UnidadMedidaModel;

import retrofit2.Call;
import retrofit2.http.GET;

public interface UnidadMedidaService {
    String API_GET_LISTADO = "Api.php?apicall=umedida";

    @GET(API_GET_LISTADO)
    Call<RespuestaGenerica<UnidadMedidaModel>> getUnidadesMedida();

}
