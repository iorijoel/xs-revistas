package com.example.diez.ojs;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

/**
 * Created by Diez on 21/3/2018.
 */

public class RVAUltimoNumero extends ArrayAdapter<Issue> {

    public RVAUltimoNumero(Context context, Issue[] datos) {
        super(context, R.layout.ly_ultimo_numero, datos);
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(getContext());
        View item = inflater.inflate(R.layout.ly_ultimo_numero, null);

        //declaracion de variables
        TextView titulo_compuesto,volumen,numero,fecha_publicacion,periodo;
        ImageView imagen_isssue;

        //relacion con objeto
        titulo_compuesto = (TextView) item.findViewById(R.id.titulo_compuesto);
        volumen= (TextView) item.findViewById(R.id.volumen);
        numero= (TextView) item.findViewById(R.id.numero);
        fecha_publicacion= (TextView) item.findViewById(R.id.fecha_publicacion);
        periodo= (TextView) item.findViewById(R.id.periodo);
        imagen_isssue = (ImageView) item.findViewById(R.id.imagen_isssue);

        titulo_compuesto.setText("Vol. "+getItem(position).getVolumen()+","+" Núm. "+getItem(position).getNumero()+" ("+getItem(position).getAño()+")");
        volumen.setText(getItem(position).getVolumen());
        numero.setText(getItem(position).getNumero());
        fecha_publicacion.setText(getItem(position).getFechapublicacion());
        periodo.setText(getItem(position).getPerido());
        Glide.with(this.getContext()).
                load(getItem(position).
                        getImagen()).
                into(imagen_isssue);

        return(item);
    }
}
