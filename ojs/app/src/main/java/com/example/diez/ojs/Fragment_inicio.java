package com.example.diez.ojs;



import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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
import java.util.Locale;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_inicio extends Fragment implements Asynchtask{

    View view;
    //para localidad - cambio de idioma
    globales global;

    private Locale locale;
    private Configuration config = new Configuration();
    private String imagenrevista;

    public Fragment_inicio() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)  {

        view = inflater.inflate(R.layout.fragment_inicio, container, false);
        global =(globales)getActivity().getApplicationContext();//contexto de la clase global

        //no mostrar menu
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(R.string.app_name);


        RadioButton rbesp = (RadioButton) view.findViewById(R.id.idiomaespa);
        RadioButton rbing = (RadioButton) view.findViewById(R.id.idiomaingl);

        if (Locale.getDefault().getDisplayLanguage().toString().equals("español")){
            rbesp.setChecked(true);
            global.setIdioma("es_ES");

        }else if (Locale.getDefault().getDisplayLanguage().toString().equals("English")){
            rbing.setChecked(true);
            global.setIdioma("en_US");
        }

        RadioGroup radioGroup = (RadioGroup) view.findViewById(R.id.gruporadioidioma);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId) {
                    case R.id.idiomaespa:
                        locale = new Locale("es");
                        config.locale =locale;
                        break;
                    case R.id.idiomaingl:
                        locale = new Locale("en");
                        config.locale =locale;
                        break;
                }
                Locale.setDefault(locale);
                getResources().updateConfiguration(config, null);
                Intent refresh = new Intent(getActivity(), MainActivity.class);
                startActivity(refresh);
            }
        });
        ConectWSrevistas();
        return view;
    }

    private void ConectWSrevistas() {
        Map<String, String> datos = new HashMap<String, String>();
        WebService ws= new WebService("http://revistas.uteq.edu.ec/wsFinal/obtenerRevistas.php?locale="+global.getIdioma()+"", datos,view.getContext(),Fragment_inicio.this);
        ws.execute("");
    }

    @Override
    public void processFinish(String result) throws JSONException {

        JSONObject jsonJournal = new JSONObject(result);
        JSONArray jsonArrayJournal = jsonJournal.getJSONArray("Revistas");
        Revistas[] ListaRevistas=new Revistas[jsonArrayJournal.length()];
        for (  int i=0;i<jsonArrayJournal.length();i++)
        {
            JSONObject objJournal=jsonArrayJournal.getJSONObject(i);
            //buscar obtener de otra forma la imagen de las revistas
            String idrev=objJournal.getString("id");
            String uno="1";
            String dos="2";
            if (idrev.equals(dos))
            {
                imagenrevista="http://revistas.uteq.edu.ec/public/journals/2/journalThumbnail_es_ES.jpg";
            }else if (idrev.equals(uno))
            {
                imagenrevista="http://revistas.uteq.edu.ec/public/journals/1/journalThumbnail_es_ES.png";
            }
            ListaRevistas[i]=new Revistas(objJournal.getString("id"),objJournal.getString("nombre"),imagenrevista,objJournal.getString("descrip"));
        }

        RVARevistasAdapter adaptadorRevistas =new RVARevistasAdapter(view.getContext(),ListaRevistas);
        ListView lisOpciones=(ListView) view.findViewById(R.id.lv_revistas);
        lisOpciones.setAdapter(adaptadorRevistas);
        lisOpciones.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {

                global.setIdrevista(((Revistas)a.getItemAtPosition(position)).getIdrevista().toString());
                global.setNombrerevista(((Revistas)a.getItemAtPosition(position)).getNombre().toString());
                Fragment_todos_los_numeros fragment = new Fragment_todos_los_numeros();
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.content_frame, fragment).addToBackStack(null);
                ft.commit();
            }
        });
    }
}
