package com.example.app_maestria_ventas.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.app_maestria_ventas.R;
import com.example.app_maestria_ventas.adapters.VentaAdapterView;
import com.example.app_maestria_ventas.api.ConexionAPI;
import com.example.app_maestria_ventas.models.RespuestaGenerica;
import com.example.app_maestria_ventas.models.VentasModel;
import com.example.app_maestria_ventas.services.VentaService;
import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VentaListActivity extends AppCompatActivity {
    public final static int OPINION_REQUEST_CODE = 1;

    RecyclerView recyclerView ;
    VentaAdapterView ventaAdapterView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_venta_list);
        recyclerView = (RecyclerView) findViewById(R.id.reciclerVentas);
        ventaAdapterView = new VentaAdapterView(new ArrayList<>());
        recyclerView.setAdapter(ventaAdapterView);
        getVentas();
    }

    public void getVentas(){
        VentaService ventaService = ConexionAPI.getVentaService();
        Call<RespuestaGenerica<VentasModel>> call = ventaService.getVentas();
        call.enqueue(new Callback<RespuestaGenerica<VentasModel>>() {
            @Override
            public void onResponse(Call<RespuestaGenerica<VentasModel>> call, Response<RespuestaGenerica<VentasModel>> response) {
                ventaAdapterView.swapItems(response.body().getContenido());
                Toast.makeText(VentaListActivity.this, "Se cargaron ventas : " + response.body().getContenido().size(), Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onFailure(Call<RespuestaGenerica<VentasModel>> call, Throwable t) {
                Log.i("Error: ",t.getMessage());
                Toast.makeText(VentaListActivity.this, "FALLO LA CONEXION CON EL API.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void irAgregarVenta(View view){
        Intent intent = new Intent(this, VentasActivity.class);
        intent.putExtra("variable1","pet1.jpg");
        startActivityForResult(intent,OPINION_REQUEST_CODE);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            String result=data.getStringExtra("MESSAGE");
            Toast.makeText(this,result,Toast.LENGTH_LONG).show();
            getVentas();
        }


    }
}