package com.example.app_maestria_ventas.views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.app_maestria_ventas.R;
import com.example.app_maestria_ventas.api.ConexionAPI;
import com.example.app_maestria_ventas.models.AlmacenModel;
import com.example.app_maestria_ventas.models.CategoriaModel;
import com.example.app_maestria_ventas.models.ClienteModel;
import com.example.app_maestria_ventas.models.RespuestaGenerica;
import com.example.app_maestria_ventas.models.UnidadMedidaModel;
import com.example.app_maestria_ventas.services.AlmacenService;
import com.example.app_maestria_ventas.services.CategoriaService;
import com.example.app_maestria_ventas.services.ClienteService;
import com.example.app_maestria_ventas.services.UnidadMedidaService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductoActivity extends AppCompatActivity {
    Spinner spAlmacenes, spCategorias, spUnidadesMedida;
    TextView txtCodigoProducto, txtNombreProducto, txtStock, txtPrecioVenta;
    List<AlmacenModel> listAlmacens =   new ArrayList<>();
    List<CategoriaModel> listCategorias = new ArrayList<>();
    List<UnidadMedidaModel> listUnidadMedida = new ArrayList<>();
    ArrayAdapter<String> adapterAlmacenes;
    ArrayAdapter<String> adapterCategorias;
    ArrayAdapter<String> adapterUnidadesDeMedida;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_producto);
        spAlmacenes = (Spinner) findViewById(R.id.spinerAlmanecensProducto);
        spCategorias = (Spinner) findViewById(R.id.spinerCategorysProducto);
        spUnidadesMedida = (Spinner) findViewById(R.id.spinerUnidMedidaProducto);

        txtCodigoProducto = (TextView) findViewById(R.id.txtCodProducto);
        txtNombreProducto = (TextView) findViewById(R.id.txtNombreProducto);
        txtStock = (TextView) findViewById(R.id.txtStockInicialProducto);
        txtPrecioVenta = (TextView) findViewById(R.id.txtPrecioVentaProducto);

        // adapter Almacen
        adapterAlmacenes = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, new ArrayList<>()
        );
        adapterAlmacenes.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spAlmacenes.setAdapter(adapterAlmacenes);

        // adapter Categorias
        adapterCategorias = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, new ArrayList<>()
        );
        adapterCategorias.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spCategorias.setAdapter(adapterCategorias);

        // adapter Unidad de Medidas
        adapterUnidadesDeMedida = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, new ArrayList<>()
        );
        adapterUnidadesDeMedida.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spUnidadesMedida.setAdapter(adapterUnidadesDeMedida);

        //llenado de datos
        getAlmacenes();
        getCategorias();
        getUnidadesMedida();
    }


    private void getAlmacenes(){
        AlmacenService almacenService = ConexionAPI.getAlmacenService();
        Call<RespuestaGenerica<AlmacenModel>> call = almacenService.getAlmacenes();
        call.enqueue(new Callback<RespuestaGenerica<AlmacenModel>>() {
            @Override
            public void onResponse(Call<RespuestaGenerica<AlmacenModel>> call, Response<RespuestaGenerica<AlmacenModel>> response) {
                listAlmacens = response.body().getContenido();
                adapterAlmacenes.addAll(listAlmacens.stream().map(el-> el.getAlmacen()).collect(Collectors.toList()));
                adapterAlmacenes.notifyDataSetChanged();
                Toast.makeText(ProductoActivity.this, "Se cargaron Almacenes : " + response.body().getContenido().size(), Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onFailure(Call<RespuestaGenerica<AlmacenModel>> call, Throwable t) {
                Log.i("Error: ",t.getMessage());
                Toast.makeText(ProductoActivity.this, "FALLO LA CONEXION CON EL API.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getCategorias(){
        CategoriaService categoriaService = ConexionAPI.getCategoryService();
        Call<RespuestaGenerica<CategoriaModel>> call = categoriaService.getCategorias();
        call.enqueue(new Callback<RespuestaGenerica<CategoriaModel>>() {
            @Override
            public void onResponse(Call<RespuestaGenerica<CategoriaModel>> call, Response<RespuestaGenerica<CategoriaModel>> response) {
                listCategorias = response.body().getContenido();
                adapterCategorias.addAll(listCategorias.stream().map(el-> el.getCategoria()).collect(Collectors.toList()));
                adapterCategorias.notifyDataSetChanged();
                Toast.makeText(ProductoActivity.this, "Se cargaron Categorias : " + response.body().getContenido().size(), Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onFailure(Call<RespuestaGenerica<CategoriaModel>> call, Throwable t) {
                Log.i("Error: ",t.getMessage());
                Toast.makeText(ProductoActivity.this, "FALLO LA CONEXION CON EL API.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getUnidadesMedida(){
        UnidadMedidaService unidadMedidaService = ConexionAPI.getUnidadMedidaService();
        Call<RespuestaGenerica<UnidadMedidaModel>> call = unidadMedidaService.getUnidadesMedida();
        call.enqueue(new Callback<RespuestaGenerica<UnidadMedidaModel>>() {
            @Override
            public void onResponse(Call<RespuestaGenerica<UnidadMedidaModel>> call, Response<RespuestaGenerica<UnidadMedidaModel>> response) {
                listUnidadMedida = response.body().getContenido();
                adapterUnidadesDeMedida.addAll(listUnidadMedida.stream().map(el-> el.getUnidad()).collect(Collectors.toList()));
                adapterUnidadesDeMedida.notifyDataSetChanged();
                Toast.makeText(ProductoActivity.this, "Se cargaron Unidades : " + response.body().getContenido().size(), Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onFailure(Call<RespuestaGenerica<UnidadMedidaModel>> call, Throwable t) {
                Log.i("Error: ",t.getMessage());
                Toast.makeText(ProductoActivity.this, "FALLO LA CONEXION CON EL API.", Toast.LENGTH_SHORT).show();
            }
        });
    }

}