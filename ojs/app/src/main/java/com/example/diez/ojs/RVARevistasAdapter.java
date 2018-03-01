package com.example.diez.ojs;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by Diez on 13/2/2018.
 */

public class RVARevistasAdapter extends RecyclerView.Adapter<RVARevistasAdapter.ViewHolder> {

    private Context context;
    private List<Revistas> my_data;

    public RVARevistasAdapter(Context context, List<Revistas> my_data) {
        this.context = context;
        this.my_data = my_data;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.ly_revistas,parent,false);
        return new ViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.nombre.setText(my_data.get(position).getNombre());
        holder.descripcion.setText(my_data.get(position).getDescripcion());
        Glide.with(context).load(my_data.get(position).getImagen()).into(holder.imageView);
    }
    @Override
    public int getItemCount() {
        return my_data.size();
    }

    public  class ViewHolder extends  RecyclerView.ViewHolder{

        public TextView nombre,descripcion;
        public ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            nombre = (TextView) itemView.findViewById(R.id.nombre_revista);
            descripcion= (TextView) itemView.findViewById(R.id.descripcion_revista);
            imageView = (ImageView) itemView.findViewById(R.id.imagen_revista);
        }
    }





}
