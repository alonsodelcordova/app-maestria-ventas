package com.example.app_maestria_ventas.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.app_maestria_ventas.R;
import com.example.app_maestria_ventas.models.ProductoModel;

import java.util.ArrayList;
import java.util.List;

public class ProductoAdapterView extends RecyclerView.Adapter<ProductoAdapterView.ViewHolder>{
    private List<ProductoModel> listdata;
    public ProductoAdapterView(List<ProductoModel> listdata) {
        this.listdata = listdata;
    }
    @Override
    public ProductoAdapterView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.item_producto, parent, false);
        ProductoAdapterView.ViewHolder viewHolder = new ProductoAdapterView.ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ProductoAdapterView.ViewHolder holder, int position) {
        final ProductoModel myListData = listdata.get(position);
        holder.txtId.setText(myListData.getId_producto()+" : "+myListData.getCodigo());
        holder.txtNombre.setText("NOMBRE: "+myListData.getNombre());
        holder.txtPrecioVenta.setText("Precio Venta: "+myListData.getPrecio_venta());
        holder.txtStock.setText("STOCK: "+myListData.getStock());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(),"Seleccion : "+myListData.getCodigo(),Toast.LENGTH_LONG).show();
                //Intent intent = new Intent(CategoriaListActivity.class,CategoriaActivity.class);

            }
        });
    }

    public void swapItems(List<ProductoModel> appointments) {
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
        public TextView txtNombre;
        public TextView txtPrecioVenta;
        public TextView txtStock;
        public CardView cardView;
        public ViewHolder(View itemView) {
            super(itemView);
            this.txtId = (TextView) itemView.findViewById(R.id.txtIdItemProducto);
            this.txtNombre = (TextView) itemView.findViewById(R.id.txtNombreItemProducto);
            this.txtPrecioVenta = (TextView) itemView.findViewById(R.id.txtPrecioVentaItemProducto);
            this.txtStock = (TextView) itemView.findViewById(R.id.txtStockItemProducto);
            this.cardView = (CardView) itemView.findViewById(R.id.cardViewProducto);
        }
    }
}


