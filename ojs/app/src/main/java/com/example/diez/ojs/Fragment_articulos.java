package com.example.diez.ojs;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.diez.ojs.webService.Asynchtask;
import com.example.diez.ojs.webService.WebService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_articulos extends Fragment implements Asynchtask {

    globales global;
    View view;

    public Fragment_articulos() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view =inflater.inflate(R.layout.fragment_articulos, container, false);
        global =(globales)getActivity().getApplicationContext();//contexto de la clase global
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(global.getTituloArticulos());

        ConectWSArticulos();
        return view;
    }

    private void ConectWSArticulos() {
        Map<String, String> datos = new HashMap<String, String>();
        WebService ws= new WebService("http://revistas.uteq.edu.ec/wsFinal/obtenerListadoArticulos.php?locale="+global.getIdioma()+"&issue="+global.getIdissue()+"", datos,view.getContext(),Fragment_articulos.this);
        ws.execute("");
    }

    @Override
    public void processFinish(String result)  throws JSONException {

        JSONObject jsonArticulos = new JSONObject(result);
        JSONArray jsonArrayArticulos = jsonArticulos.getJSONArray("listaArticulos");

        Articulos[] ListaArticulos = new Articulos[jsonArrayArticulos.length()];
        for (int i = 0; i < jsonArrayArticulos.length(); i++) {
            JSONObject objArticulo = jsonArrayArticulos.getJSONObject(i);
            ListaArticulos[i] = new Articulos(objArticulo.getString("id"),objArticulo.getString("title"),objArticulo.getString("autores"),objArticulo.getString("pages"),global.getImagenissue());
        }

        RVAArticulosAdapter adaptadorArticulos = new RVAArticulosAdapter(view.getContext(), ListaArticulos);
        ListView lisOpciones = (ListView) view.findViewById(R.id.lv_Articulos);
        lisOpciones.setAdapter(adaptadorArticulos);
        lisOpciones.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {

                global.setIdSubmission(((Articulos)a.getItemAtPosition(position)).getId().toString());

                Fragment_detalleArticulo fragment = new Fragment_detalleArticulo();
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.content_frame, fragment).addToBackStack(null);
                ft.commit();
            }
        });
    }
}
