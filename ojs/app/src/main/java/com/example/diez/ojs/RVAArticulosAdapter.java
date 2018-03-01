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
 * Created by Diez on 20/2/2018.
 */

public class RVAArticulosAdapter extends RecyclerView.Adapter<RVAArticulosAdapter.ViewHolder>   {

    private Context context;
    private List<Articulos> my_data;

    public RVAArticulosAdapter(Context context, List<Articulos> my_data) {
        this.context = context;
        this.my_data = my_data;
    }
    @Override
    public RVAArticulosAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.ly_articulos,parent,false);
        return new RVAArticulosAdapter.ViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(RVAArticulosAdapter.ViewHolder holder, int position) {
        holder.titulo.setText(my_data.get(position).getTitulo());
        holder.autores.setText(my_data.get(position).getAutores());
        holder.paginas.setText(my_data.get(position).getPaginas());
        Glide.with(context).load(my_data.get(position).getImagen()).into(holder.imagen_articulo);
    }
    @Override
    public int getItemCount() {
        return my_data.size();
    }

    public  class ViewHolder extends  RecyclerView.ViewHolder{

        public TextView titulo,autores,paginas;
        public ImageView imagen_articulo;

        public ViewHolder(View itemView) {
            super(itemView);
            titulo = (TextView) itemView.findViewById(R.id.titulo_compuesto);
            autores= (TextView) itemView.findViewById(R.id.autores);
            paginas= (TextView) itemView.findViewById(R.id.paginas);
            imagen_articulo = (ImageView) itemView.findViewById(R.id.imagen_articulos);
        }
    }
}
