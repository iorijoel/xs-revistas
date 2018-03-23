package com.example.diez.ojs;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
public class Fragment_Instrucciones extends Fragment implements Asynchtask {

    globales global;
    View view;

    TextView contenido;
    Button botonDescarga,botonVerPdf;

    public Fragment_Instrucciones() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view =inflater.inflate(R.layout.fragment__instrucciones, container, false);
        global =(globales)getActivity().getApplicationContext();//contexto de la clase global
        //((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(global.getTituloArticulos());

        contenido = (TextView) view.findViewById(R.id.contenido_instruciones);

        botonDescarga= (Button) view.findViewById(R.id.btnDescargarPdf);
        botonDescarga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    Uri uri = Uri.parse(global.getUrlPdf());
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent);
                }catch (Exception e){
                    Toast toast1 =
                            Toast.makeText(getActivity().getApplicationContext(),
                                    "HEMOS ENCONTRADO UN ERROR EN ESTE ARCHIVO", Toast.LENGTH_SHORT);
                    toast1.show();
                };
            }
        });

        botonVerPdf= (Button) view.findViewById(R.id.btnVerPdf);
        botonVerPdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    FragmentVisorPdf fragment = new FragmentVisorPdf();
                    FragmentTransaction ft = getFragmentManager().beginTransaction();
                    ft.replace(R.id.content_frame, fragment).addToBackStack(null);
                    ft.commit();
                }catch (Exception e){
                    Toast toast1 =
                            Toast.makeText(getActivity().getApplicationContext(),
                                    "HEMOS ENCONTRADO UN ERROR EN ESTE ARCHIVO", Toast.LENGTH_SHORT);
                    toast1.show();
                };
            }
        });

        ConectWSArticulos();
        return view;

    }

    private void ConectWSArticulos() {
        Map<String, String> datos = new HashMap<String, String>();
        WebService ws= new WebService("http://revistas.uteq.edu.ec/wsFinal/obtenerInstricciones.php?locale="+global.getIdioma()+"", datos,view.getContext(),Fragment_Instrucciones.this);
        ws.execute("");
    }

    @Override
    public void processFinish(String result)  throws JSONException {
       JSONObject jsonInstrucciones = new JSONObject(result);
        JSONArray jsonArrayInstr = jsonInstrucciones.getJSONArray("instriccion");

        JSONObject objINS=jsonArrayInstr.getJSONObject(0);

        contenido.setText(Html.fromHtml(objINS.getString("instrucciones")));

        global.setUrlPdf("http://revistas.uteq.edu.ec/formatos/instrucciones%20para%20autores.pdf");
    }

}
