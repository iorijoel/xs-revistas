package com.example.diez.ojs;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.diez.ojs.webService.Asynchtask;
import com.example.diez.ojs.webService.WebService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

//ESTO ES PARA TODOS LOS NUMEROS

public class Fragment_todos_los_numeros extends Fragment implements Asynchtask {

    globales global;
    View view;

    public Fragment_todos_los_numeros() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view =inflater.inflate(R.layout.fragment_todos_los_numeros, container, false);
        global =(globales)getActivity().getApplicationContext();//contexto de la clase global

        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(global.getNombrerevista());

        TextView cabec=(TextView) ((AppCompatActivity) getActivity()).findViewById(R.id.titulocabecera);
        cabec.setText(global.getNombrerevista());

        ImageView imagenCabe=(ImageView) ((AppCompatActivity) getActivity()).findViewById(R.id.imageViewCabecera);
        String uno="1";
        if (global.getIdrevista().equals(uno))
        {
            imagenCabe.setImageDrawable(getResources().getDrawable(R.drawable.f));
        }else
        {
            imagenCabe.setImageDrawable(getResources().getDrawable(R.drawable.l));
        }





        ConectWSIssue();
        return view;
    }

    private void ConectWSIssue() {
        Map<String, String> datos = new HashMap<String, String>();
        WebService ws= new WebService("http://revistas.uteq.edu.ec/wsFinal/obtenertodosnumeros.php?journal_id="+global.getIdrevista()+"&locale="+global.getIdioma()+"", datos,view.getContext(),Fragment_todos_los_numeros.this);
        ws.execute("");
    }

   @Override
    public void processFinish(String result)  throws JSONException {
       JSONObject jsonIssues = new JSONObject(result);
       JSONArray jsonArrayIssues = jsonIssues.getJSONArray("todosLosNumeros");
       Issue[] ListaIssues=new Issue[jsonArrayIssues.length()];
       for (  int i=0;i<jsonArrayIssues.length();i++)
       {
           JSONObject objIssue=jsonArrayIssues.getJSONObject(i);
           ListaIssues[i]=new Issue(global.getIdrevista(),objIssue.getString("id"),objIssue.getString("volumen"),objIssue.getString("numero"),objIssue.getString("year"),
                   objIssue.getString("fechapublicidad"),"http://revistas.uteq.edu.ec//public//journals//"+global.getIdrevista()+"//"+objIssue.getString("imagen"),objIssue.getString("periodo"));
       }
       RVAIssuesAdapter adaptadorIssues =new RVAIssuesAdapter(view.getContext(),ListaIssues);
       ListView lisOpciones=(ListView) view.findViewById(R.id.lv_todoslosnumeros);
       lisOpciones.setAdapter(adaptadorIssues);
       lisOpciones.setOnItemClickListener(new AdapterView.OnItemClickListener() {

           public void onItemClick(AdapterView<?> a, View v, int position, long id) {
               global.setIdissue(((Issue)a.getItemAtPosition(position)).getIdissue().toString());
               global.setImagenissue(((Issue)a.getItemAtPosition(position)).getImagen().toString());
               global.setTituloArticulos("Vol. "+((Issue)a.getItemAtPosition(position)).getVolumen().toString()+" Num. "+((Issue)a.getItemAtPosition(position)).getNumero().toString()+" ("+((Issue) a.getItemAtPosition(position)).getAño().toString()+")");

               Fragment_articulos fragment = new Fragment_articulos();
               FragmentTransaction ft = getFragmentManager().beginTransaction();
               ft.replace(R.id.content_frame, fragment).addToBackStack(null);
               ft.commit();
           }
       });
    }
}
