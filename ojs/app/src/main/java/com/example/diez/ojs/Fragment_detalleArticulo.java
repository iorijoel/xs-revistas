package com.example.diez.ojs;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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
public class Fragment_detalleArticulo extends Fragment implements Asynchtask {

    globales global;
    View view;

    //String urlPdf;
    TextView doi, titulo_articulo, fecha_publicado, palabras_claves, autores, resumen;
    ImageView imagen_articulo;
    Button botonDescarga,botonVerPdf;


    public Fragment_detalleArticulo() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_detalle_articulo, container, false);
        global =(globales)getActivity().getApplicationContext();//contexto de la clase global

/*        Toast toast1 =
                Toast.makeText(getActivity().getApplicationContext(),
                        global.getIdSubmission(), Toast.LENGTH_SHORT);

        toast1.show();*/

        doi = (TextView) view.findViewById(R.id.doi);
        imagen_articulo=(ImageView) view.findViewById(R.id.imagen_articulo);
        titulo_articulo = (TextView) view.findViewById(R.id.titulo_articulo);
        fecha_publicado = (TextView) view.findViewById(R.id.fecha_publicado);
        palabras_claves = (TextView) view.findViewById(R.id.palabras_claves);
        autores = (TextView) view.findViewById(R.id.autores);
        resumen = (TextView) view.findViewById(R.id.resumen);

        Glide.with(this)
                .load(global.getImagenissue())
                .error(R.drawable.imgnotfound)
                .into(imagen_articulo);

        ConectWSArticulos();


        botonDescarga= (Button) view.findViewById(R.id.btnDescargarPdf);
        botonDescarga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

/*                Toast toast1 =
                        Toast.makeText(getActivity().getApplicationContext(),
                                urlPdf, Toast.LENGTH_SHORT);

                toast1.show();*/

                Uri uri = Uri.parse(global.getUrlPdf());
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });



        botonVerPdf= (Button) view.findViewById(R.id.btnVerPdf);
        botonVerPdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

/*                Toast toast1 =
                        Toast.makeText(getActivity().getApplicationContext(),
                                global.getUrlPdf(), Toast.LENGTH_SHORT);

                toast1.show();*/

                FragmentVisorPdf fragment = new FragmentVisorPdf();
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.content_frame, fragment).addToBackStack(null);
                ft.commit();

            }
        });



        return view;
    }

    private void ConectWSArticulos() {
        Map<String, String> datos = new HashMap<String, String>();
        WebService ws= new WebService("http://revistas.uteq.edu.ec/wsFinal/obtenerDetalleArticulo.php?locale="+global.getIdioma()+"&idSubmission="+global.getIdSubmission()+"", datos,view.getContext(),Fragment_detalleArticulo.this);
        ws.execute("");
    }

    @Override
    public void processFinish(String result)  throws JSONException {
        JSONObject jsonArticulo = new JSONObject(result);
        JSONArray jsonArrayArticulo = jsonArticulo.getJSONArray("detalleArticulo");

        JSONObject objArt=jsonArrayArticulo.getJSONObject(0);

        doi.setText(objArt.getString("doi"));
        titulo_articulo.setText(objArt.getString("nombre"));
        fecha_publicado.setText(objArt.getString("fechaPublicacion"));
        palabras_claves.setText(objArt.getString("keyword"));
        autores.setText(objArt.getString("autores"));
        //resumen.setText(objArt.getString("resumen"));

        resumen.setText(Html.fromHtml(objArt.getString("resumen")));
        //urlPdf=objArt.getString("pdf");

        global.setUrlPdf(objArt.getString("pdf"));
    }
}
