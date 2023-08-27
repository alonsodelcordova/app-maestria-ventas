package com.example.app_maestria_ventas.services;


import com.example.app_maestria_ventas.models.CategoriaModel;
import com.example.app_maestria_ventas.models.ClienteModel;
import com.example.app_maestria_ventas.models.RespuestaGenerica;
import com.example.app_maestria_ventas.models.UsuarioModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;


public interface ClienteService {
    String API_GET_LISTADO = "Api.php?apicall=verClientes";

    String API_POST_CREATE = "Api.php?apicall=grabaCliente";

    String DELETECLIENTE = "Api.php?apicall=deleteCliente";

    @GET(API_GET_LISTADO)
    Call<RespuestaGenerica<ClienteModel>> getCliente();
    @POST(API_POST_CREATE)
    Call<RespuestaGenerica<ClienteModel>> createCliente(@Body ClienteModel dataModal);

    @POST(DELETECLIENTE)
    Call<RespuestaGenerica<ClienteModel>> eliminarCliente(@Body ClienteModel dataModal);


}
