package com.example.app_maestria_ventas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.app_maestria_ventas.api.ConexionAPI;
import com.example.app_maestria_ventas.models.RespuestaGenerica;
import com.example.app_maestria_ventas.models.TipoGas;
import com.example.app_maestria_ventas.services.TipoGasService;
import com.example.app_maestria_ventas.views.BienvenidoActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    TextView txtRespuesta;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtRespuesta = (TextView) findViewById(R.id.txtResptMain);
        getTipos();
    }

    public void iraHome(View view){
        Intent intent = new Intent(this, BienvenidoActivity.class);
        startActivity(intent);
    }

    private List<TipoGas> getTipos(){
        List<TipoGas> lista = new ArrayList<>();
        TipoGasService postService = ConexionAPI.getConexion().create(TipoGasService.class);
        Call<RespuestaGenerica<TipoGas>> call = postService.getPost();
        call.enqueue(new Callback<RespuestaGenerica<TipoGas>>() {
            @Override
            public void onResponse(Call<RespuestaGenerica<TipoGas>> call, Response<RespuestaGenerica<TipoGas>> response) {
                for(TipoGas post : response.body().getContenido()) {
                    lista.add(post);
                }
                txtRespuesta.setText("Se cargaron: " + lista.size());
                Toast.makeText(MainActivity.this, "Se cargaron: " + lista.size(), Toast.LENGTH_SHORT).show();
                //Toast.makeText(getBaseContext(),"Se cargaron: " + lista.size(), Toast.LENGTH_SHORT ).show();
            }

            @Override
            public void onFailure(Call<RespuestaGenerica<TipoGas>> call, Throwable t) {
                txtRespuesta.setText("Error en cargar datos "+t.getMessage());
                Log.i("Error",t.getMessage());
                Toast.makeText(MainActivity.this, "Fail to get the data..", Toast.LENGTH_SHORT).show();
            }
        });

        return lista;
    }

}