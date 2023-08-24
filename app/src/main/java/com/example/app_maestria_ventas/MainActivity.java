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
import com.example.app_maestria_ventas.models.UsuarioModel;
import com.example.app_maestria_ventas.services.UsuarioService;
import com.example.app_maestria_ventas.views.BienvenidoActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    TextView txtRespuesta, txtUsuario, txtPassword;
    List<UsuarioModel> usuarioModels = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtRespuesta = (TextView) findViewById(R.id.txtResptMain);
        txtUsuario = (TextView) findViewById(R.id.txtUsuarioMain);
        txtPassword = (TextView) findViewById(R.id.txtPasswordMain);
        getUsuarios();

    }

    public void iraHome(View view){
        String usuario = txtUsuario.getText().toString();
        String password = txtPassword.getText().toString();
        Optional<UsuarioModel> usuarioModel = usuarioModels.stream().filter(el-> el.getUsuario().equals(usuario)).findFirst();
        if(usuarioModel.isPresent()){
            if(password.equals(usuarioModel.get().getPassword())){
                Toast.makeText(MainActivity.this, "Bienvenido!!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, BienvenidoActivity.class);
                startActivity(intent);
                limpiar();
            }else{
                Toast.makeText(MainActivity.this, "Contraseña incorrecta ", Toast.LENGTH_SHORT).show();
                txtPassword.requestFocus();
            }
        }else{
            Toast.makeText(MainActivity.this, "Usuario no existe ", Toast.LENGTH_SHORT).show();
            limpiar();
        }

    }

    public void limpiar(){
        txtUsuario.setText("");
        txtPassword.setText("");
    }


    private void getUsuarios(){
        UsuarioService usuarioService = ConexionAPI.getConexion().create(UsuarioService.class);
        Call<RespuestaGenerica<UsuarioModel>> call = usuarioService.getUsuarios();
        call.enqueue(new Callback<RespuestaGenerica<UsuarioModel>>() {
            @Override
            public void onResponse(Call<RespuestaGenerica<UsuarioModel>> call, Response<RespuestaGenerica<UsuarioModel>> response) {
                for(UsuarioModel user : response.body().getContenido()) {
                    usuarioModels.add(user);
                }
                Toast.makeText(MainActivity.this, "Se cargaron usuarios : " + usuarioModels.size(), Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onFailure(Call<RespuestaGenerica<UsuarioModel>> call, Throwable t) {
                Log.i("Error",t.getMessage());
                Toast.makeText(MainActivity.this, "FALLO LA CONEXION CON EL API.", Toast.LENGTH_SHORT).show();
            }
        });
    }

}