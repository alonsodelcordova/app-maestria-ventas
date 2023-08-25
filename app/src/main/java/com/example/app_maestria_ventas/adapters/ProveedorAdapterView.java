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
import java.util.ArrayList;
import java.util.List;

public class ProveedorAdapterView extends RecyclerView.Adapter<ProveedorAdapterView.ViewHolder>{
    private List<ProveedorModel> listdata;
    public ProveedorAdapterView(List<ProveedorModel> listdata) {
        this.listdata = listdata;
    }
    @Override
    public ProveedorAdapterView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.item_proveedor, parent, false);
        ProveedorAdapterView.ViewHolder viewHolder = new ProveedorAdapterView.ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ProveedorAdapterView.ViewHolder holder, int position) {
        final ProveedorModel myListData = listdata.get(position);
        holder.txtId.setText("RUC: "+listdata.get(position).getRuc_proveedor());
        holder.txtNombreProveedor.setText("Nombre: "+listdata.get(position).getRazon_social());
        holder.txtDireccionProveedor.setText("Dirección: "+listdata.get(position).getDireccion_fiscal());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(),"Seleccion : "+myListData.getRazon_social(),Toast.LENGTH_LONG).show();
                //Intent intent = new Intent(CategoriaListActivity.class,CategoriaActivity.class);

            }
        });
    }

    public void swapItems(List<ProveedorModel> appointments) {
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
        public TextView txtNombreProveedor;
        public TextView txtDireccionProveedor;
        public CardView cardView;
        public ViewHolder(View itemView) {
            super(itemView);
            this.txtId = (TextView) itemView.findViewById(R.id.txtIdItemProveedor);
            this.txtNombreProveedor = (TextView) itemView.findViewById(R.id.txtNombreItemProveedor);
            this.txtDireccionProveedor = (TextView) itemView.findViewById(R.id.txtDireccionItemProveedor);
            this.cardView = (CardView) itemView.findViewById(R.id.cardViewProveedor);
        }
    }
}
