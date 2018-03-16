package com.example.diez.ojs;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
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

    String idioma,idissue,imagenissue;

    View view;

    public Fragment_articulos() {
        // Required empty public constructor
    }

    //para pasar informacion por bundle desde el otro fragment
    public static Fragment_articulos newInstance(Bundle arguments){
        Fragment_articulos f = new Fragment_articulos();
        if(arguments != null){
            f.setArguments(arguments);
        }
        return f;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view =inflater.inflate(R.layout.fragment_articulos, container, false);

        //text = view.findViewById(R.id.htmlprueba);

        //text.setText(Html.fromHtml("<h2>titulo</2><br><br><p>hola buenos dias ajsdhgjashd</p>"));

        Bundle bundle =getArguments();



        //Cogemos  los datos enviados
        idioma=bundle.getString("idioma");
        idissue =bundle.getString("idissue");
        imagenissue=bundle.getString("imagenissue");

/*
        Toast toast1 =
                Toast.makeText(view.getContext(),"volumen "+volumen+"    Num: "+numero+"  Imagen:"+imagen, Toast.LENGTH_LONG);
        toast1.show();
*/


        //recyclerView = (RecyclerView) view.findViewById(R.id.rv_articulos);
        ConectWSArticulos();

        return view;

    }

    private void ConectWSArticulos() {
        Map<String, String> datos = new HashMap<String, String>();
        WebService ws= new WebService("http://revistas.uteq.edu.ec/wsFinal/obtenerListadoArticulos.php?locale="+idioma+"&issue="+idissue+"", datos,view.getContext(),Fragment_articulos.this);
        //WebService ws= new WebService("http://www.json-generator.com/api/json/get/cgiaoaFBNK?indent=2", datos,view.getContext(),Fragment_articulos.this);
        ws.execute("");
    }

    @Override
    public void processFinish(String result)  throws JSONException {

        JSONObject jsonArticulos = new JSONObject(result);
        JSONArray jsonArrayArticulos = jsonArticulos.getJSONArray("listaArticulos");

        Articulos[] ListaArticulos = new Articulos[jsonArrayArticulos.length()];
        for (int i = 0; i < jsonArrayArticulos.length(); i++) {
            JSONObject objArticulo = jsonArrayArticulos.getJSONObject(i);
            ListaArticulos[i] = new Articulos(objArticulo.getString("id"),objArticulo.getString("title"),objArticulo.getString("autores"),objArticulo.getString("pages"),imagenissue);
        }

        RVAArticulosAdapter adaptadorArticulos = new RVAArticulosAdapter(view.getContext(), ListaArticulos);

        ListView lisOpciones = (ListView) view.findViewById(R.id.lv_Articulos);

        lisOpciones.setAdapter(adaptadorArticulos);

        lisOpciones.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> a, View v, int position, long id) {

/*                Bundle b = new Bundle();
                String volumen = ((Issue) a.getItemAtPosition(position)).getVolumen().toString();
                String numero = ((Issue) a.getItemAtPosition(position)).getNumero().toString();
                String imagen = "http://revistas.uteq.edu.ec//public//journals//1//" + ((Issue) a.getItemAtPosition(position)).getImagen().toString();


                b.putString("volumen", volumen);
                b.putString("numero", numero);
                b.putString("imagen", imagen);
                *//*         Toast toast1 =
                        Toast.makeText(view.getContext(),"hola", Toast.LENGTH_SHORT);
                        toast1.show();
                *//*
                Fragment_articulos fragment = Fragment_articulos.newInstance(b);
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.content_frame, fragment);
                ft.commit();*/

            }
        });


    }

}
