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
import com.example.app_maestria_ventas.adapters.ClienteAdapterView;
import com.example.app_maestria_ventas.api.ConexionAPI;
import com.example.app_maestria_ventas.models.CategoriaModel;
import com.example.app_maestria_ventas.models.ClienteModel;
import com.example.app_maestria_ventas.models.RespuestaGenerica;
import com.example.app_maestria_ventas.services.CategoriaService;
import com.example.app_maestria_ventas.services.ClienteService;
import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import com.example.app_maestria_ventas.R;

public class ClienteListActivity extends AppCompatActivity {

    public final static int OPINION_REQUEST_CODE = 1;
    RecyclerView recyclerView ;
    ClienteAdapterView ClienteAdapterView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente_list);
        recyclerView = (RecyclerView) findViewById(R.id.reciclerClientes);
        ClienteAdapterView = new ClienteAdapterView(new ArrayList<>());
        recyclerView.setAdapter(ClienteAdapterView);
        getClientes();
    }

    public void getClientes(){
        ClienteService clienteService = ConexionAPI.getClienteService();
        Call<RespuestaGenerica<ClienteModel>> call = clienteService.getCliente();
        call.enqueue(new Callback<RespuestaGenerica<ClienteModel>>() {
            @Override
            public void onResponse(Call<RespuestaGenerica<ClienteModel>> call, Response<RespuestaGenerica<ClienteModel>> response) {
                ClienteAdapterView.swapItems(response.body().getContenido());
                Toast.makeText(ClienteListActivity.this, "Se cargaron Clientes : " + response.body().getContenido().size(), Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onFailure(Call<RespuestaGenerica<ClienteModel>> call, Throwable t) {
                Log.i("Error: ",t.getMessage());
                Toast.makeText(ClienteListActivity.this, "FALLO LA CONEXION CON EL API.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void irAgregarCliente(View view){
        Intent intent = new Intent(this, ClienteActivity.class);
        startActivityForResult(intent,OPINION_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            String result=data.getStringExtra("MESSAGE");
            Toast.makeText(this,result,Toast.LENGTH_LONG).show();
            getClientes();
        }

    }
}