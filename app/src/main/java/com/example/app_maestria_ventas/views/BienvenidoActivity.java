package com.example.app_maestria_ventas.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.app_maestria_ventas.R;

public class BienvenidoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bienvenido);
    }


    public void irCategorias(View view){
        Intent intent = new Intent(this, CategoriaListActivity.class);
        startActivity(intent);
    }

    public void irClientes(View view){
        Intent intent = new Intent(this, ClienteListActivity.class);
        startActivity(intent);
    }

    public void irProveedores(View view){
        Intent intent = new Intent(this, ProveedorListActivity.class);
        startActivity(intent);
    }

    public void irProductos(View view){
        Intent intent = new Intent(this, ProductoActivity.class);
        startActivity(intent);
    }

    public void irVentas(View view){
        Intent intent = new Intent(this, VentasActivity.class);
        startActivity(intent);
    }


}