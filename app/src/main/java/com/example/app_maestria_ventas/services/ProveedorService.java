package com.example.app_maestria_ventas.services;

import com.example.app_maestria_ventas.models.ProveedorModel;
import com.example.app_maestria_ventas.models.RespuestaGenerica;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ProveedorService {

    String API_ROUTE = "Api.php?apicall=verProveedor";
    String API_ROUTE_POST = "Api.php?apicall=grabaProveedor";

    @GET(API_ROUTE)
    Call<RespuestaGenerica<ProveedorModel>> getProveedores();

    @POST(API_ROUTE_POST)
    Call<RespuestaGenerica<ProveedorModel>> guardarProveedor(ProveedorModel input);
}
