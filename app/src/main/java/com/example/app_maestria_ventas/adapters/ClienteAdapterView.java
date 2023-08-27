package com.example.app_maestria_ventas.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.app_maestria_ventas.R;
import com.example.app_maestria_ventas.models.CategoriaModel;
import com.example.app_maestria_ventas.models.ClienteModel;

import java.util.ArrayList;
import java.util.List;

public class ClienteAdapterView extends RecyclerView.Adapter<ClienteAdapterView.ViewHolder>{
    private List<ClienteModel> listdata;
    public ClienteAdapterView(List<ClienteModel> listdata) {
        this.listdata = listdata;
    }
    @Override
    public ClienteAdapterView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.item_cliente, parent, false);
        ClienteAdapterView.ViewHolder viewHolder = new ClienteAdapterView.ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ClienteAdapterView.ViewHolder holder, int position) {
        final ClienteModel myListData = listdata.get(position);
        holder.txtId.setText(listdata.get(position).getDocumento()+" : "+listdata.get(position).getRuc());
        holder.txtRazon.setText("NOMBRE: "+listdata.get(position).getRazon_social());
        holder.linerLayoutClienteItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(),"Seleccion : "+myListData.getRazon_social(),Toast.LENGTH_LONG).show();
                //Intent intent = new Intent(CategoriaListActivity.class,CategoriaActivity.class);

            }
        });
    }

    public void swapItems(List<ClienteModel> appointments) {
        if (appointments == null) {
            listdata = new ArrayList<>(0);
        } else {
            listdata = appointments;
        }
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return listdata.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txtId;
        public TextView txtRazon;
        public LinearLayout linerLayoutClienteItem;
        public ViewHolder(View itemView) {
            super(itemView);
            this.txtId = (TextView) itemView.findViewById(R.id.txtIdItemCliente);
            this.txtRazon = (TextView) itemView.findViewById(R.id.txtNombreItemCliente);
            this.linerLayoutClienteItem = (LinearLayout) itemView.findViewById(R.id.linerLayoutClienteItem);
        }
    }
}


