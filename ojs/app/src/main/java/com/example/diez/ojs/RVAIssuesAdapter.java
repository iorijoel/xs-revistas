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
 * Created by joel_ on 15/2/2018.
 */

public class RVAIssuesAdapter extends RecyclerView.Adapter<RVAIssuesAdapter.ViewHolder>  {


    private Context context;
    private List<Issue> my_data;

    public RVAIssuesAdapter(Context context, List<Issue> my_data) {
        this.context = context;
        this.my_data = my_data;
    }
    @Override
    public RVAIssuesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.ly_todos_los_numeros,parent,false);
        return new RVAIssuesAdapter.ViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(RVAIssuesAdapter.ViewHolder holder, int position) {
        holder.titulo_compuesto.setText("Vol. "+my_data.get(position).getVolumen()+","+" Núm. "+my_data.get(position).getNumero()+" ("+my_data.get(position).getAño()+")");
        holder.volumen.setText(my_data.get(position).getVolumen());
        holder.numero.setText(my_data.get(position).getNumero());
        holder.fecha_publicacion.setText(my_data.get(position).getFechapublicacion());
        holder.periodo.setText(my_data.get(position).getPerido());
        Glide.with(context).load(my_data.get(position).getImagen()).into(holder.imagen_isssue);
    }
    @Override
    public int getItemCount() {
        return my_data.size();
    }

    public  class ViewHolder extends  RecyclerView.ViewHolder{

        public TextView titulo_compuesto,volumen,numero,fecha_publicacion,periodo;
        public ImageView imagen_isssue;

        public ViewHolder(View itemView) {
            super(itemView);
            titulo_compuesto = (TextView) itemView.findViewById(R.id.titulo_compuesto);
            volumen= (TextView) itemView.findViewById(R.id.volumen);
            numero= (TextView) itemView.findViewById(R.id.numero);
            fecha_publicacion= (TextView) itemView.findViewById(R.id.fecha_publicacion);
            periodo= (TextView) itemView.findViewById(R.id.periodo);
            imagen_isssue = (ImageView) itemView.findViewById(R.id.imagen_isssue);
        }
    }




}
