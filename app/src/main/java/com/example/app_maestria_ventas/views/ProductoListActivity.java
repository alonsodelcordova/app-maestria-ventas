package com.example.app_maestria_ventas.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.app_maestria_ventas.R;
import com.example.app_maestria_ventas.adapters.ProductoAdapterView;
import com.example.app_maestria_ventas.api.ConexionAPI;
import com.example.app_maestria_ventas.models.ProductoModel;
import com.example.app_maestria_ventas.models.ProveedorModel;
import com.example.app_maestria_ventas.models.RespuestaGenerica;
import com.example.app_maestria_ventas.services.ProductoService;
import com.example.app_maestria_ventas.services.ProveedorService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductoListActivity extends AppCompatActivity {
    public final static int OPINION_REQUEST_CODE = 1;
    RecyclerView recyclerView ;
    ProductoAdapterView productoAdapterView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_producto_list);
        recyclerView = (RecyclerView) findViewById(R.id.reciclerProductos);
        productoAdapterView = new ProductoAdapterView(new ArrayList<>());
        recyclerView.setAdapter(productoAdapterView);
        getProductos();
    }

    public void irAgregarProducto(View view){
        Intent intent = new Intent(this, ProductoActivity.class);
        startActivityForResult(intent,OPINION_REQUEST_CODE);
    }

    public void getProductos(){
        ProductoService productoService = ConexionAPI.getProductosService();
        Call<RespuestaGenerica<ProductoModel>> call = productoService.getProducto();
        call.enqueue(new Callback<RespuestaGenerica<ProductoModel>>() {
            @Override
            public void onResponse(Call<RespuestaGenerica<ProductoModel>> call, Response<RespuestaGenerica<ProductoModel>> response) {
                productoAdapterView.swapItems(response.body().getContenido());
                Toast.makeText(ProductoListActivity.this, "Se cargaron productos : " + response.body().getContenido().size(), Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onFailure(Call<RespuestaGenerica<ProductoModel>> call, Throwable t) {
                Log.i("Error: ",t.getMessage());
                Toast.makeText(ProductoListActivity.this, "FALLO LA CONEXION CON EL API.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            String result=data.getStringExtra("MESSAGE");
            Toast.makeText(this,result,Toast.LENGTH_LONG).show();
            getProductos();
        }
    }
}