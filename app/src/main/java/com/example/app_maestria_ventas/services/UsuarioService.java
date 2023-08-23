package com.example.app_maestria_ventas.services;

import com.example.app_maestria_ventas.models.RespuestaGenerica;
import com.example.app_maestria_ventas.models.TipoGas;
import com.example.app_maestria_ventas.models.UsuarioModel;

import retrofit2.Call;
import retrofit2.http.GET;

public interface UsuarioService {
    String API_ROUTE = "Api.php?apicall=readusuarios";

    @GET(API_ROUTE)
    Call<RespuestaGenerica<UsuarioModel>> getUsuarios();
}
