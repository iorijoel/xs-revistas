package com.example.diez.ojs;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
public class Fragment_Anuncios extends Fragment implements Asynchtask {

    globales global;
    View view;

    TextView contenido,titulo;
    public Fragment_Anuncios() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view =inflater.inflate(R.layout.fragment_anuncios, container, false);
        global =(globales)getActivity().getApplicationContext();//contexto de la clase global
        //((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(global.getTituloArticulos());

        contenido = (TextView) view.findViewById(R.id.contenido_anuncio);
        titulo = (TextView) view.findViewById(R.id.titulo_anuncio);

        ConectWSArticulos();
        return view;

    }

    private void ConectWSArticulos() {
        Map<String, String> datos = new HashMap<String, String>();
        WebService ws= new WebService("http://revistas.uteq.edu.ec/wsFinal/obtenerAnuncio.php?locale="+global.getIdioma()+"&journal_id="+global.getIdrevista()+"", datos,view.getContext(),Fragment_Anuncios.this);
        ws.execute("");
    }

    @Override
    public void processFinish(String result)  throws JSONException {
        JSONObject jsonanuncios = new JSONObject(result);
        JSONArray jsonArrayAnuncio = jsonanuncios.getJSONArray("anuncio");

        JSONObject objAnun=jsonArrayAnuncio.getJSONObject(0);

        contenido.setText(Html.fromHtml(objAnun.getString("despCorta")));
        titulo.setText(objAnun.getString("titulo"));
    }

}