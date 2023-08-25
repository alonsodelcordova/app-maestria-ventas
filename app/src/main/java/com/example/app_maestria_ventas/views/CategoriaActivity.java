package com.example.app_maestria_ventas.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.app_maestria_ventas.R;
import com.example.app_maestria_ventas.api.ConexionAPI;
import com.example.app_maestria_ventas.models.CategoriaModel;
import com.example.app_maestria_ventas.models.RespuestaGenerica;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoriaActivity extends AppCompatActivity {

    private ProgressBar loadingPB;
    private TextView txtCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categoria);
        loadingPB = (ProgressBar) findViewById(R.id.idLoadingPB);
        txtCategory = (TextView) findViewById(R.id.editTextCategoria);

        // ejecmplo
        //Obteniendo la instancia del Intent
        Intent intent = getIntent();
        String name = intent.getStringExtra("variable1");

    }

    public void onCreateCategory(View view){
        postCategory(txtCategory.getText().toString());
    }
    public void salir(String mensaje){
        Intent intent=new Intent();
        intent.putExtra("MESSAGE",mensaje);
        setResult(RESULT_OK,intent);
        finish();//finishing activity
    }


    private void postCategory(String category) {

        loadingPB.setVisibility(View.VISIBLE);
        CategoriaModel modal = new CategoriaModel();
        modal.setCategoria(category);

        Call<RespuestaGenerica<CategoriaModel>> call = ConexionAPI.getCategoryService().createCategory(modal);
        call.enqueue(new Callback<RespuestaGenerica<CategoriaModel>>() {
            @Override
            public void onResponse(Call<RespuestaGenerica<CategoriaModel>> call, Response<RespuestaGenerica<CategoriaModel>> response) {
                //Toast.makeText(CategoriaActivity.this, "Categoria Agregada", Toast.LENGTH_SHORT).show();
                loadingPB.setVisibility(View.GONE);
                txtCategory.setText("");

                if(response.body().getContenido()!=null){
                    salir("Categoria Agregada!! ");
                }else{
                    Toast.makeText(CategoriaActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<RespuestaGenerica<CategoriaModel>> call, Throwable t) {
                Toast.makeText(CategoriaActivity.this, "Categoria Agregada", Toast.LENGTH_SHORT).show();
                loadingPB.setVisibility(View.GONE);
            }
        });
    }
}