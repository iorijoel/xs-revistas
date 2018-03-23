package com.example.diez.ojs;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.diez.ojs.webService.Asynchtask;
import com.example.diez.ojs.webService.WebService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_Contactos extends Fragment implements Asynchtask {

    globales global;
    View view;

    String imagenrevista="";
    TextView nombre,correo,telefono;
    ImageView imageView;

    public Fragment_Contactos() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view =inflater.inflate(R.layout.fragment_contactos, container, false);
        global =(globales)getActivity().getApplicationContext();//contexto de la clase global
        //((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(global.getTituloArticulos());

        nombre = (TextView) view.findViewById(R.id.nombre);
        correo = (TextView) view.findViewById(R.id.correo);
        telefono = (TextView) view.findViewById(R.id.telefono);

        imageView = (ImageView) view.findViewById(R.id.imagen_revista);

        String idrev=global.getIdrevista();
        String uno="1";
        String dos="2";
        if (idrev.equals(dos))
        {
            imagenrevista="http://revistas.uteq.edu.ec/public/journals/2/journalThumbnail_es_ES.jpg";
        }else if (idrev.equals(uno))
        {
            imagenrevista="http://revistas.uteq.edu.ec/public/journals/1/journalThumbnail_es_ES.png";
        }
        Glide.with(this.getContext()).
                load(imagenrevista).
                into(imageView);


        ConectWSArticulos();
        return view;
    }

    private void ConectWSArticulos() {
        Map<String, String> datos = new HashMap<String, String>();
        WebService ws= new WebService("http://revistas.uteq.edu.ec/wsFinal/obtenerContactos.php?locale="+global.getIdioma()+"&journal_id="+global.getIdrevista()+"", datos,view.getContext(),Fragment_Contactos.this);
        ws.execute("");
    }

    @Override
    public void processFinish(String result)  throws JSONException {
        JSONObject jsonanuncios = new JSONObject(result);
        JSONArray jsonArrayAnuncio = jsonanuncios.getJSONArray("contacto");

        JSONObject objAnun=jsonArrayAnuncio.getJSONObject(0);

        nombre.setText(objAnun.getString("nombre"));
        correo.setText(objAnun.getString("email"));
        telefono.setText(objAnun.getString("telefono"));
    }
}
