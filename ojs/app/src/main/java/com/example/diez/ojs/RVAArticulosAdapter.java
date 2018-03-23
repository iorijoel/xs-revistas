package com.example.diez.ojs;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by Diez on 20/2/2018.
 */

public class RVAArticulosAdapter extends ArrayAdapter<Articulos> {

    public RVAArticulosAdapter(Context context, Articulos[] datos) {
        super(context, R.layout.ly_articulos, datos);
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(getContext());
        View item = inflater.inflate(R.layout.ly_articulos, null);

        TextView titulo, autores, paginas;
        ImageView imagen_articulo;

        titulo = (TextView) item.findViewById(R.id.titulo_compuesto);
        autores = (TextView) item.findViewById(R.id.autores);
        paginas = (TextView) item.findViewById(R.id.paginas);
        imagen_articulo = (ImageView) item.findViewById(R.id.imagen_articulos);

        titulo.setText(getItem(position).getTitulo());
        autores.setText(getItem(position).getAutores());
        paginas.setText(getItem(position).getPaginas());
        Glide.with(this.getContext()).
                load(getItem(position).getImagen()).
                into(imagen_articulo);
        return (item);
    }
}
