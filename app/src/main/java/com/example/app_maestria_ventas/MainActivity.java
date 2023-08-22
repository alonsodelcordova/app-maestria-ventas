package com.example.app_maestria_ventas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.app_maestria_ventas.views.BienvenidoActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void iraHome(View view){
        Intent intent = new Intent(this, BienvenidoActivity.class);
        startActivity(intent);
    }
}