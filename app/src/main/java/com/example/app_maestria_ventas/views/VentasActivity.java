package com.example.app_maestria_ventas.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.app_maestria_ventas.R;
import com.example.app_maestria_ventas.api.ConexionAPI;
import com.example.app_maestria_ventas.models.ClienteModel;
import com.example.app_maestria_ventas.models.ProductoModel;
import com.example.app_maestria_ventas.models.RespuestaGenerica;
import com.example.app_maestria_ventas.models.VentasModel;
import com.example.app_maestria_ventas.services.ClienteService;
import com.example.app_maestria_ventas.services.ProductoService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VentasActivity extends AppCompatActivity {
    Spinner spComprobante, spCliente, spProducto;
    EditText txtSerieVenta, txtCorrelativo, txtPrecio, txtCantidad;
    TextView txtSubtotal, txtIgv, txtTotal;
    Button btnGrabar;
    //Spinner spinner;
    Double s,i,t;

    ArrayAdapter<String> adapterClientes;
    ArrayAdapter<String> adapterProductos;
    private List<ClienteModel> listClientes;
    private List<ProductoModel> listProductos;
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
        txtPrecio = findViewById(R.id.editTextPrecioVenta);
        txtCantidad = findViewById(R.id.editTextCantidadVenta);
        txtSubtotal = findViewById(R.id.txtSubtotalVenta);
        txtIgv = findViewById(R.id.txtIgvVenta);
        txtTotal = findViewById(R.id.txtTotalVenta);
        btnGrabar = findViewById(R.id.Grabar);



        //adapterClientes
        adapterClientes = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, new ArrayList<>());
        adapterClientes.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spCliente.setAdapter(adapterClientes);
        //Fin adapterClientes

        //adapterProductos
        adapterProductos = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, new ArrayList<>());
        adapterProductos.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spProducto.setAdapter(adapterProductos);
        //Fin adapterproductos

        getClientes();
        getProductos();


        Intent intent = getIntent();
        String ncliente = intent.getStringExtra("variable1");
    }

    public void onCreateVenta(View view){ postVenta();}

    private void postVenta(){

        VentasModel modal = new VentasModel();
        if(spComprobante.getSelectedItem().toString().equals("Boleta")){
            modal.setId_comprobante("1"); //1 Boleta
        }else{
            modal.setId_comprobante("2"); //2 Factura
        }
        modal.setSerie(txtSerieVenta.getText().toString());
        modal.setCodigo(txtCorrelativo.getText().toString());
        ClienteModel clienteModel = listClientes.get(spCliente.getSelectedItemPosition());
        modal.setId_cliente(clienteModel.getId_cliente());
        ProductoModel productoModel = listProductos.get(spProducto.getSelectedItemPosition());
        modal.setId_producto(productoModel.getId_producto());
        modal.setPrecio_venta(txtPrecio.getText().toString());
        modal.setCantidad(txtCantidad.getText().toString());
        modal.setSubtotal(s.toString());
        modal.setIgv(i.toString());
        modal.setTotal(t.toString());

        Call<RespuestaGenerica<VentasModel>> call = ConexionAPI.getVentaService().createVenta(modal);
        call.enqueue(new Callback<RespuestaGenerica<VentasModel>>() {
            @Override
            public void onResponse(Call<RespuestaGenerica<VentasModel>> call, Response<RespuestaGenerica<VentasModel>> response) {
                txtSerieVenta.setText("");
                txtCorrelativo.setText("");
                txtPrecio.setText("");
                txtCantidad.setText("");
                txtSubtotal.setText("");
                txtIgv.setText("");
                txtTotal.setText("");
                spCliente.requestFocus();
                if(response.body().getContenido()!=null){
                    salir("Venta Agregada!");
                }else{
                    Toast.makeText(VentasActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<RespuestaGenerica<VentasModel>> call, Throwable t) {
                Toast.makeText(VentasActivity.this, "La Venta no se pudo Grabar", Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void onCalculaVenta(View view){
        s = Double.parseDouble(txtPrecio.getText().toString()) * Double.parseDouble(txtCantidad.getText().toString());
        i = s * 0.18;
        t = s + i;
        txtSubtotal.setText(String.format("%.2f",s).toString());
        txtIgv.setText(String.format("%.2f",i).toString());
        txtTotal.setText(String.format("%.2f",t).toString());
    }

    public void salir(String mensaje){
        Intent intent=new Intent();
        intent.putExtra("MESSAGE",mensaje);
        setResult(RESULT_OK,intent);
        finish();//finishing activity
    }


    public void getClientes(){
        ClienteService clienteService = ConexionAPI.getClienteService();
        Call<RespuestaGenerica<ClienteModel>> call = clienteService.getCliente();
        call.enqueue(new Callback<RespuestaGenerica<ClienteModel>>() {
            @Override
            public void onResponse(Call<RespuestaGenerica<ClienteModel>> call, Response<RespuestaGenerica<ClienteModel>> response) {
                listClientes = response.body().getContenido();
                adapterClientes.addAll(listClientes.stream().map(el->el.getRazon_social()).collect(Collectors.toList()));
                Toast.makeText(VentasActivity.this, "Se cargaron Clientes: " + response.body().getContenido().size(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<RespuestaGenerica<ClienteModel>> call, Throwable t) {
                Log.i("Error: ",t.getMessage());
                Toast.makeText(VentasActivity.this, "FALLO LA CONEXION CON EL API.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void getProductos(){
        ProductoService productoService = ConexionAPI.getProductoService();
        Call<RespuestaGenerica<ProductoModel>> call = productoService.getProducto();
        call.enqueue(new Callback<RespuestaGenerica<ProductoModel>>() {
            @Override
            public void onResponse(Call<RespuestaGenerica<ProductoModel>> call, Response<RespuestaGenerica<ProductoModel>> response) {
                listProductos = response.body().getContenido();
                adapterProductos.addAll(listProductos.stream().map(el->el.getDescripcion()).collect(Collectors.toList()));
                Toast.makeText(VentasActivity.this, "Se cargaron Productos: " + response.body().getContenido().size(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<RespuestaGenerica<ProductoModel>> call, Throwable t) {
                Log.i("Error: ",t.getMessage());
                Toast.makeText(VentasActivity.this, "FALLO LA CONEXION CON EL API.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void onCreateCliente(View view){
        postCliente();
    }

    public void postCliente(){

    }
}