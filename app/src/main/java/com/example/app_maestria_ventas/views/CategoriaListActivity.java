package com.example.app_maestria_ventas.views;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import com.example.app_maestria_ventas.R;
import com.example.app_maestria_ventas.adapters.CategoriaAdapterView;
import com.example.app_maestria_ventas.api.ConexionAPI;
import com.example.app_maestria_ventas.models.CategoriaModel;
import com.example.app_maestria_ventas.models.RespuestaGenerica;
import com.example.app_maestria_ventas.services.CategoriaService;
import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CategoriaListActivity extends AppCompatActivity {
    public final static int OPINION_REQUEST_CODE = 1;

    RecyclerView recyclerView ;
    CategoriaAdapterView categoriaAdapterView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categoria_list);
        recyclerView = (RecyclerView) findViewById(R.id.reciclerCategorias);
        categoriaAdapterView = new CategoriaAdapterView(new ArrayList<>());
        recyclerView.setAdapter(categoriaAdapterView);
        getCategorias();
    }

    public void getCategorias(){
        CategoriaService categoriaService = ConexionAPI.getCategoryService();
        Call<RespuestaGenerica<CategoriaModel>> call = categoriaService.getCategorias();
        call.enqueue(new Callback<RespuestaGenerica<CategoriaModel>>() {
            @Override
            public void onResponse(Call<RespuestaGenerica<CategoriaModel>> call, Response<RespuestaGenerica<CategoriaModel>> response) {
                categoriaAdapterView.swapItems(response.body().getContenido());
                Toast.makeText(CategoriaListActivity.this, "Se cargaron categorias : " + response.body().getContenido().size(), Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onFailure(Call<RespuestaGenerica<CategoriaModel>> call, Throwable t) {
                Log.i("Error: ",t.getMessage());
                Toast.makeText(CategoriaListActivity.this, "FALLO LA CONEXION CON EL API.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void irAgregarCategoria(View view){
        Intent intent = new Intent(this, CategoriaActivity.class);
        intent.putExtra("variable1","pet1.jpg");
        startActivityForResult(intent,OPINION_REQUEST_CODE);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            String result=data.getStringExtra("MESSAGE");
            Toast.makeText(this,result,Toast.LENGTH_LONG).show();
            getCategorias();
        }


    }
}