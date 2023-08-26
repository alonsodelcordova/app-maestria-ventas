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
import com.example.app_maestria_ventas.services.ClienteService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductoActivity extends AppCompatActivity {
    Spinner spAlmacenes, spCategorias, spUnidadesMedida;
    TextView txtCodigoProducto, txtNombreProducto, txtStock, txtPrecioCompra, txtPrecioVenta;
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
        txtStock = (TextView) findViewById(R.id.txtStockInicial);
        txtPrecioCompra = (TextView) findViewById(R.id.txtPrecioCompraProducto);
        txtPrecioVenta = (TextView) findViewById(R.id.txtPrecioVentaProducto);

        // adapter Almacen
        adapterAlmacenes = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, new ArrayList<>()
        );
        adapterAlmacenes.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spAlmacenes.setAdapter(adapterAlmacenes);

        // adapter Categorias


        // adapter Unidad de Medidas

        //llenado de datos
        getAlmacenes();
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

}