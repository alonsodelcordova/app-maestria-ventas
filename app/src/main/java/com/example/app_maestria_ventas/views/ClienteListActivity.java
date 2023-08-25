package com.example.app_maestria_ventas.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.app_maestria_ventas.R;

public class ClienteListActivity extends AppCompatActivity {

    public final static int OPINION_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente_list);
    }

    public void irAgregarCliente(View view){
        Intent intent = new Intent(this, ClienteActivity.class);
        startActivityForResult(intent,OPINION_REQUEST_CODE);
    }

}