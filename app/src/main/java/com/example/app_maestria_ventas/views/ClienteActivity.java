package com.example.app_maestria_ventas.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.app_maestria_ventas.R;
import com.example.app_maestria_ventas.api.ConexionAPI;
import com.example.app_maestria_ventas.models.CategoriaModel;
import com.example.app_maestria_ventas.models.ClienteModel;
import com.example.app_maestria_ventas.models.RespuestaGenerica;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ClienteActivity extends AppCompatActivity {


    private TextView txtDocumento;
    private TextView txtRazon;

    private RadioButton radioButtonDni;

    private RadioButton radioButtonRuc;
    private TextView txtDireccion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente);
        txtDocumento = (TextView) findViewById(R.id.txtDocumentoCliente);
        txtRazon = (TextView) findViewById(R.id.txtRazonSocialCliente);
        txtDireccion = (TextView) findViewById(R.id.txtDireccionCliente);
        radioButtonRuc = (RadioButton) findViewById(R.id.radioButtonRuc);
        radioButtonDni = (RadioButton) findViewById(R.id.radioButtonDni);


        Intent intent = getIntent();
        String name = intent.getStringExtra("variable1");

    }

    public void onCreateCliente(View view){
        postCliente();
    }


    public void salir(String mensaje){
        Intent intent=new Intent();
        intent.putExtra("MESSAGE",mensaje);
        setResult(RESULT_OK,intent);
        finish();//finishing activity
    }

    private void postCliente() {
        ClienteModel modal = new ClienteModel();
        modal.setDireccion(txtDireccion.getText().toString());
        modal.setRazon_social(txtRazon.getText().toString());
        modal.setRuc(txtDocumento.getText().toString());
        if (radioButtonDni.isChecked()){
            modal.setDocumento("DNI");
        }
        if (radioButtonRuc.isChecked()){
            modal.setDocumento("RUC");
        }

        Call<RespuestaGenerica<ClienteModel>> call = ConexionAPI.getClienteService().createCliente(modal);
        call.enqueue(new Callback<RespuestaGenerica<ClienteModel>>() {
            @Override
            public void onResponse(Call<RespuestaGenerica<ClienteModel>> call, Response<RespuestaGenerica<ClienteModel>> response) {
                txtDocumento.setText("");
                txtDireccion.setText("");
                txtRazon.setText("");
                radioButtonDni.isChecked();
                if(response.body().getContenido()!=null){
                    salir("Cliente Agregado!! ");
                }else{
                    Toast.makeText(ClienteActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<RespuestaGenerica<ClienteModel>> call, Throwable t) {
                Toast.makeText(ClienteActivity.this, "Cliente no pudo Agregar", Toast.LENGTH_SHORT).show();
            }
        });
    }
}







