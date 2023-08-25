package com.example.app_maestria_ventas.services.facturacion;

import com.example.app_maestria_ventas.models.ProveedorFacturacionModel;
import com.example.app_maestria_ventas.models.RespuestaGenerica;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ProveedorFacturacionService {

    String API_ROUTE = "api.php";

    @GET(API_ROUTE)
    Call<ProveedorFacturacionModel> getProveedores(@Query("rucCliente") String ruc);


}
