package com.example.app_maestria_ventas.views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.app_maestria_ventas.R;

public class VentasActivity extends AppCompatActivity {

    Spinner spComprobante, spCliente, spProducto;

    EditText txtSerieVenta, txtCorrelativo, txtProducto, txtPrecio, txtCantidad;
    TextView txtSubtotal, txtIgv, txtTotal;

    Button btnGrabar;
    private ProgressBar loadingPB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ventas);
        spComprobante = findViewById(R.id.spComprobanteVenta);
        spCliente = findViewById(R.id.spClienteVenta);
        spProducto = findViewById(R.id.spProductoVenta);
        txtSerieVenta = findViewById(R.id.editTextSerieVenta);
        txtCorrelativo = findViewById(R.id.editTextCorrelativoVenta);
        txtProducto = findViewById(R.id.editTextProductoVenta);
        txtPrecio = findViewById(R.id.editTextPrecioVenta);
        txtCantidad = findViewById(R.id.editTextCantidadVenta);
        txtSubtotal = findViewById(R.id.txtSubtotalVenta);
        txtIgv = findViewById(R.id.txtIgvVenta);
        txtTotal = findViewById(R.id.txtTotalVenta);
        btnGrabar = findViewById(R.id.Grabar);
    }
}