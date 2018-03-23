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
 * Created by Diez on 13/2/2018.
 */

public class RVARevistasAdapter extends ArrayAdapter<Revistas> {

    public RVARevistasAdapter(Context context, Revistas[] datos) {
        super(context, R.layout.ly_revistas, datos);
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(getContext());
        View item = inflater.inflate(R.layout.ly_revistas, null);

        TextView nombre = (TextView) item.findViewById(R.id.nombre_revista);
        TextView descripcion= (TextView) item.findViewById(R.id.descripcion_revista);
        ImageView imageView = (ImageView) item.findViewById(R.id.imagen_revista);

        nombre.setText(getItem(position).getNombre());
        Glide.with(this.getContext()).
                load(getItem(position).
                getImagen()).
                into(imageView);
        return(item);
    }
}
