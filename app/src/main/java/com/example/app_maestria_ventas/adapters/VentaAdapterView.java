package com.example.app_maestria_ventas.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_maestria_ventas.R;
import com.example.app_maestria_ventas.models.ProveedorModel;
import com.example.app_maestria_ventas.models.VentasModel;

import java.util.ArrayList;
import java.util.List;

public class VentaAdapterView extends RecyclerView.Adapter<VentaAdapterView.ViewHolder>{
    private List<VentasModel> listdata;
    public VentaAdapterView(List<VentasModel> listdata) {
        this.listdata = listdata;
    }
    @Override
    public VentaAdapterView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.item_venta, parent, false);
        VentaAdapterView.ViewHolder viewHolder = new VentaAdapterView.ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(VentaAdapterView.ViewHolder holder, int position) {
        final VentasModel myListData = listdata.get(position);
        holder.txtId.setText("CODIGO: "+myListData.getSerie()+"-"+myListData.getCodigo());
        holder.txtClienteVenta.setText("Cliente: "+myListData.getNombre_cliente());
        holder.txtProductoVenta.setText("Producto: "+myListData.getNombre_producto());
        holder.txtPrecioVenta.setText("Precio : S/."+myListData.getPrecio_venta()+"    Cantidad: #"+myListData.getCantidad());
        holder.txtTotalVenta.setText("Total: S/."+myListData.getTotal());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(),"Seleccion : "+myListData.getNombre_producto() + " "+myListData.getNombre_cliente(),Toast.LENGTH_LONG).show();
                //Intent intent = new Intent(CategoriaListActivity.class,CategoriaActivity.class);

            }
        });
    }

    public void swapItems(List<VentasModel> appointments) {
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
        public TextView txtClienteVenta;
        public TextView txtProductoVenta;
        public TextView txtPrecioVenta;
        public TextView txtTotalVenta;
        public CardView cardView;
        public ViewHolder(View itemView) {
            super(itemView);
            this.txtId = (TextView) itemView.findViewById(R.id.txtIdItemVenta);
            this.txtClienteVenta = (TextView) itemView.findViewById(R.id.txtClienteItemVenta);
            this.txtProductoVenta = (TextView) itemView.findViewById(R.id.txtProductoItemVenta);
            this.txtPrecioVenta = (TextView) itemView.findViewById(R.id.txtPrecioVentaItemVenta);
            this.txtTotalVenta = (TextView) itemView.findViewById(R.id.txtTotalItemVenta);
            this.cardView = (CardView) itemView.findViewById(R.id.cardViewVenta);
        }
    }
}
