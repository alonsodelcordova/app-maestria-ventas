package com.example.app_maestria_ventas.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.app_maestria_ventas.R;
import com.example.app_maestria_ventas.api.ConexionAPI;
import com.example.app_maestria_ventas.api.ConexionFacturacionAPI;
import com.example.app_maestria_ventas.models.CategoriaModel;
import com.example.app_maestria_ventas.models.ProveedorFacturacionModel;
import com.example.app_maestria_ventas.models.ProveedorModel;
import com.example.app_maestria_ventas.models.RespuestaGenerica;
import com.example.app_maestria_ventas.services.CategoriaService;
import com.example.app_maestria_ventas.services.facturacion.ProveedorFacturacionService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProveedorActivity extends AppCompatActivity {
    TextView txtNroRuc, txtRazonSocial, txtDireccion,
        txtEstadoProveedor, txtCondicionProveedor;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proveedor);

        txtNroRuc = (TextView) findViewById(R.id.txtNroRucProveedor);
        txtRazonSocial = (TextView) findViewById(R.id.txtRazonSocialProveedor);
        txtDireccion = (TextView) findViewById(R.id.txtDireccionProveedor);
        txtEstadoProveedor = (TextView) findViewById(R.id.txtEstadoProveedor);
        txtCondicionProveedor = (TextView) findViewById(R.id.txtCondicionProveedor);
        progressBar = (ProgressBar) findViewById(R.id.idLoadingPBFormProveedor);
    }


    public void searchProveedorByRUC(View view){
        progressBar.setVisibility(View.VISIBLE);
        String ruc = txtNroRuc.getText().toString();
        ProveedorFacturacionService proveedorFacturacionService = ConexionFacturacionAPI.getProveedorFacturacionService();
        Call<ProveedorFacturacionModel> call = proveedorFacturacionService.getProveedores(ruc.trim());
        call.enqueue(new Callback<ProveedorFacturacionModel>() {
            @Override
            public void onResponse(Call<ProveedorFacturacionModel> call, Response<ProveedorFacturacionModel> response) {
                // encontrado
                ProveedorFacturacionModel model = response.body();
                if(model!=null){
                    Toast.makeText(ProveedorActivity.this, "Se encontró : " + response.body().getRazon_social(), Toast.LENGTH_SHORT).show();
                    txtRazonSocial.setText(model.getRazon_social());
                    txtDireccion.setText(model.getDireccion());
                    txtEstadoProveedor.setText(model.getEstado());
                    txtCondicionProveedor.setText(model.getCondicion());
                }else{
                    Toast.makeText(ProveedorActivity.this, "Error, no encontrado ", Toast.LENGTH_SHORT).show();
                    limpiar();
                }
                progressBar.setVisibility(View.GONE);
            }
            @Override
            public void onFailure(Call<ProveedorFacturacionModel> call, Throwable t) {
                Log.i("Error: ",t.getMessage());
                Toast.makeText(ProveedorActivity.this, "Datos NO encontrados, ERROR.", Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.GONE);
                limpiar();
            }
        });
    }

    public void limpiar(){
        txtRazonSocial.setText("");
        txtDireccion.setText("");
        txtEstadoProveedor.setText("");
        txtCondicionProveedor.setText("");
    }

    public void guardarProveedor(View view){
        progressBar.setVisibility(View.VISIBLE);
       save();
    }

    private void save(){
        ProveedorModel modal = new ProveedorModel();
        modal.setRuc_proveedor(txtNroRuc.getText().toString());
        modal.setRazon_social(txtRazonSocial.getText().toString());
        modal.setDireccion_fiscal(txtDireccion.getText().toString());
        modal.setEstado_empresa(txtEstadoProveedor.getText().toString());
        modal.setCondicion_empresa(txtCondicionProveedor.getText().toString());

        Call<RespuestaGenerica<ProveedorModel>> call = ConexionAPI.getProveedorService().guardarProveedor(modal);
        call.enqueue(new Callback<RespuestaGenerica<ProveedorModel>>() {
            @Override
            public void onResponse(Call<RespuestaGenerica<ProveedorModel>> call, Response<RespuestaGenerica<ProveedorModel>> response) {

                if(response.body().getContenido()!=null){
                    salir("Proveedor Agregado!! ");
                }else{
                    Toast.makeText(ProveedorActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
                progressBar.setVisibility(View.GONE);
                limpiar();
            }

            @Override
            public void onFailure(Call<RespuestaGenerica<ProveedorModel>> call, Throwable t) {
                Toast.makeText(ProveedorActivity.this, "Proveedor No Agregado", Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.GONE);
            }
        });
    }

    public void salir(String mensaje){
        Intent intent=new Intent();
        intent.putExtra("MESSAGE",mensaje);
        setResult(RESULT_OK,intent);
        finish();//finishing activity
    }

}