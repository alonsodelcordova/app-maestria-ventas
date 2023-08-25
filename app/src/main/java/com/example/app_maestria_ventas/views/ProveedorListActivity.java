package com.example.app_maestria_ventas.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.app_maestria_ventas.R;
import com.example.app_maestria_ventas.adapters.ProveedorAdapterView;
import com.example.app_maestria_ventas.api.ConexionAPI;
import com.example.app_maestria_ventas.models.CategoriaModel;
import com.example.app_maestria_ventas.models.ProveedorModel;
import com.example.app_maestria_ventas.models.RespuestaGenerica;
import com.example.app_maestria_ventas.services.CategoriaService;
import com.example.app_maestria_ventas.services.ProveedorService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProveedorListActivity extends AppCompatActivity {
    public final static int OPINION_REQUEST_CODE = 1;
    RecyclerView recyclerView ;
    ProveedorAdapterView proveedorAdapterView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proveedor_list);
        recyclerView = (RecyclerView)  findViewById(R.id.reciclerProveedores);
        proveedorAdapterView = new ProveedorAdapterView(new ArrayList<>());
        recyclerView.setAdapter(proveedorAdapterView);
        getProveedores();
    }

    public void irAgregarProveedor(View view){
        Intent intent = new Intent(this, ProveedorActivity.class);
        startActivityForResult(intent,OPINION_REQUEST_CODE);
    }

    public void getProveedores(){
        ProveedorService proveedorService = ConexionAPI.getProveedorService();
        Call<RespuestaGenerica<ProveedorModel>> call = proveedorService.getProveedores();
        call.enqueue(new Callback<RespuestaGenerica<ProveedorModel>>() {
            @Override
            public void onResponse(Call<RespuestaGenerica<ProveedorModel>> call, Response<RespuestaGenerica<ProveedorModel>> response) {
                proveedorAdapterView.swapItems(response.body().getContenido());
                Toast.makeText(ProveedorListActivity.this, "Se cargaron proveedores : " + response.body().getContenido().size(), Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onFailure(Call<RespuestaGenerica<ProveedorModel>> call, Throwable t) {
                Log.i("Error: ",t.getMessage());
                Toast.makeText(ProveedorListActivity.this, "FALLO LA CONEXION CON EL API.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            String result=data.getStringExtra("MESSAGE");
            Toast.makeText(this,result,Toast.LENGTH_LONG).show();
            getProveedores();
        }


    }
}