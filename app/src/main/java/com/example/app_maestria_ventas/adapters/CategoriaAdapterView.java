package com.example.app_maestria_ventas.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.recyclerview.widget.RecyclerView;
import com.example.app_maestria_ventas.R;
import com.example.app_maestria_ventas.models.CategoriaModel;
import java.util.ArrayList;
import java.util.List;

public class CategoriaAdapterView  extends RecyclerView.Adapter<CategoriaAdapterView.ViewHolder>{
    private List<CategoriaModel> listdata;
    public CategoriaAdapterView(List<CategoriaModel> listdata) {
        this.listdata = listdata;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.item_category, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final CategoriaModel myListData = listdata.get(position);
        holder.txtId.setText(listdata.get(position).getId_categoria());
        holder.txtCategoria.setText(listdata.get(position).getCategoria());
        holder.linerLayoutCategoryItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(),"Seleccion : "+myListData.getCategoria(),Toast.LENGTH_LONG).show();
                //Intent intent = new Intent(CategoriaListActivity.class,CategoriaActivity.class);

            }
        });
    }

    public void swapItems(List<CategoriaModel> appointments) {
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
        public TextView txtCategoria;
        public LinearLayout linerLayoutCategoryItem;

        public ViewHolder(View itemView) {
            super(itemView);
            this.txtId = (TextView) itemView.findViewById(R.id.txtIdItemCategoria);
            this.txtCategoria = (TextView) itemView.findViewById(R.id.txtNombreItemCategoria);
            this.linerLayoutCategoryItem = (LinearLayout) itemView.findViewById(R.id.linerLayoutCategoryItem);

        }
    }
}
