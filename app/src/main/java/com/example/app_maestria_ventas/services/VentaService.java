package com.example.app_maestria_ventas.services;

import com.example.app_maestria_ventas.models.ClienteModel;
import com.example.app_maestria_ventas.models.ComprobanteModel;
import com.example.app_maestria_ventas.models.ProductoModel;
import com.example.app_maestria_ventas.models.RespuestaGenerica;
import com.example.app_maestria_ventas.models.VentasModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface VentaService {

    String API_GET_LISTADO_COM = "Api.php?apicall=tipoComprobante";
    String API_GET_LISTADO_CLI = "Api.php?apicall=verClientes";
    String API_GET_LISTADO_PRO = "Api.php?apicall=verproductos";
    String API_POST_CREATE = "Api.php?apicall=grabarVenta";
    @GET(API_GET_LISTADO_COM)
    Call<RespuestaGenerica<ComprobanteModel>> getComprobante();
    @GET(API_GET_LISTADO_CLI)
    Call<RespuestaGenerica<ClienteModel>> getCliente();
    @GET(API_GET_LISTADO_PRO)
    Call<RespuestaGenerica<ProductoModel>> getProducto();
    @POST(API_POST_CREATE)
    Call<RespuestaGenerica<VentasModel>> createVenta(@Body VentasModel dataModal);
}
