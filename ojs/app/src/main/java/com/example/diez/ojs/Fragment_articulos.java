package com.example.diez.ojs;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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


    private RecyclerView recyclerView;
    private GridLayoutManager gridLayoutManager;
    private RVAArticulosAdapter adapter;
    private List<Articulos> articulos=new ArrayList<>();

    View view;
    //TextView text;

    public Fragment_articulos() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view =inflater.inflate(R.layout.fragment_articulos, container, false);

        //text = view.findViewById(R.id.htmlprueba);

        //text.setText(Html.fromHtml("<h2>titulo</2><br><br><p>hola buenos dias ajsdhgjashd</p>"));

        recyclerView = (RecyclerView) view.findViewById(R.id.rv_articulos);
        ConectWSArticulos();

        return view;

    }

    private void ConectWSArticulos() {
        Map<String, String> datos = new HashMap<String, String>();
        WebService ws= new WebService("http://www.json-generator.com/api/json/get/cgiaoaFBNK?indent=2", datos,view.getContext(),Fragment_articulos.this);
        ws.execute("");
    }

    @Override
    public void processFinish(String result)  throws JSONException {
        JSONObject jsonArticulos = new JSONObject(result);
        JSONArray jsonArrayArticulos= jsonArticulos.getJSONArray("articulos");
        for(int i=0; i< jsonArrayArticulos.length();i++)
        {
            JSONObject objArticulo = jsonArrayArticulos.getJSONObject(i);
            Articulos data= new Articulos(objArticulo.getString("id"),objArticulo.getString("title"),objArticulo.getString("last_name"),objArticulo.getString("pages"),objArticulo.getString("last_name"));
            articulos.add(data);
        }
        gridLayoutManager = new GridLayoutManager(view.getContext(),1);
        recyclerView.setLayoutManager(gridLayoutManager);
        adapter = new RVAArticulosAdapter(view.getContext(),articulos);
        recyclerView.setAdapter(adapter);
    }


}
