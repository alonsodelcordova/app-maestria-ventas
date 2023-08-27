package com.example.app_maestria_ventas.api;

import com.example.app_maestria_ventas.services.AlmacenService;
import com.example.app_maestria_ventas.services.CategoriaService;
import com.example.app_maestria_ventas.services.ClienteService;
import com.example.app_maestria_ventas.services.ProductoService;
import com.example.app_maestria_ventas.services.ProveedorService;
import com.example.app_maestria_ventas.services.TipoGasService;
import com.example.app_maestria_ventas.services.UnidadMedidaService;
import com.example.app_maestria_ventas.services.UsuarioService;
import com.example.app_maestria_ventas.services.VentaService;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ConexionAPI {

    public static Retrofit getConexion(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.130:80/api-maestria-ventas/")
                .addConverterFactory(GsonConverterFactory.create(
                        new GsonBuilder().serializeNulls().create()
                ))
                .build();
        return retrofit;
    }


    public static TipoGasService getTipoGasService(){
        return getConexion().create(TipoGasService.class);
    }

    public static CategoriaService getCategoryService(){
        return getConexion().create(CategoriaService.class);
    }

    public static UsuarioService getUsuarioService(){
        return getConexion().create(UsuarioService.class);
    }

    public static ClienteService getClienteService(){
        return getConexion().create(ClienteService.class);
    }

    public static ProveedorService getProveedorService(){
        return getConexion().create(ProveedorService.class);
    }

    public static ProductoService getProductoService(){
        return getConexion().create(ProductoService.class);
    }

    public static VentaService getVentaService(){
        return getConexion().create(VentaService.class);
    }

    public static AlmacenService getAlmacenService(){
        return getConexion().create(AlmacenService.class);
    }

    public static UnidadMedidaService getUnidadMedidaService(){
        return getConexion().create(UnidadMedidaService.class);
    }
    public static ProductoService getProductosService(){
        return getConexion().create(ProductoService.class);
    }
}
