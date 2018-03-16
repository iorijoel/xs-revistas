package com.example.diez.ojs;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
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

//ESTO ES PARA TODOS LOS NUMEROS

public class Fragment_todos_los_numeros extends Fragment implements Asynchtask {

    String idrevista,idioma;
    Toolbar toolbar;
    View view;
    TextView text;

    public Fragment_todos_los_numeros() {
        // Required empty public constructor
    }

    //para pasar informacion por bundle desde el otro fragment
    public static Fragment_todos_los_numeros newInstance(Bundle arguments){
        Fragment_todos_los_numeros f = new Fragment_todos_los_numeros();
        if(arguments != null){
            f.setArguments(arguments);
        }
        return f;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view =inflater.inflate(R.layout.fragment_todos_los_numeros, container, false);

        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle bundle =getArguments();

        //Cogemos  los datos enviados
        idrevista =bundle.getString("idrevista");
        idioma =bundle.getString("idioma");

/*        Toast toast1 =
                Toast.makeText(view.getContext(),"revista "+nombrerevista+"    id: "+idrevista, Toast.LENGTH_LONG);
        toast1.show();*/


        //recyclerView = (RecyclerView) view.findViewById(R.id.rv_todos_los_numeros);
        ConectWSIssue();
        return view;
    }

    private void ConectWSIssue() {
        Map<String, String> datos = new HashMap<String, String>();
        WebService ws= new WebService("http://revistas.uteq.edu.ec/wsFinal/obtenertodosnumeros.php?journal_id="+idrevista+"&locale="+idioma+"", datos,view.getContext(),Fragment_todos_los_numeros.this);
        //WebService ws= new WebService("http://www.json-generator.com/api/json/get/bUXOmoZJwy?indent=2", datos,view.getContext(),Fragment_todos_los_numeros.this);
        ws.execute("");
    }

   @Override
    public void processFinish(String result)  throws JSONException {
/*        JSONObject jsonIssues = new JSONObject(result);
        JSONArray jsonArrayIssues = jsonIssues.getJSONArray("metas");
        for(int i=0; i< jsonArrayIssues.length();i++)
        {
            JSONObject objIssue = jsonArrayIssues.getJSONObject(i);
            Issue data= new Issue(objIssue.getString("id"),objIssue.getString("id"),objIssue.getString("volumen"),objIssue.getString("numero"),objIssue.getString("year"),
                    objIssue.getString("fechapublicidad"),"http://revistas.uteq.edu.ec//public//journals//1//"+objIssue.getString("imagen"),objIssue.getString("titulo"));
            issue.add(data);
        }
        gridLayoutManager = new GridLayoutManager(view.getContext(),1);
        recyclerView.setLayoutManager(gridLayoutManager);
        adapter = new RVAIssuesAdapter(view.getContext(),issue);
        recyclerView.setAdapter(adapter);*/

        //nuevo - con listview

       JSONObject jsonIssues = new JSONObject(result);
       JSONArray jsonArrayIssues = jsonIssues.getJSONArray("todosLosNumeros");

       Issue[] ListaIssues=new Issue[jsonArrayIssues.length()];
       for (  int i=0;i<jsonArrayIssues.length();i++)
       {
           JSONObject objIssue=jsonArrayIssues.getJSONObject(i);
           ListaIssues[i]=new Issue(idrevista,objIssue.getString("id"),objIssue.getString("volumen"),objIssue.getString("numero"),objIssue.getString("year"),
                   objIssue.getString("fechapublicidad"),"http://revistas.uteq.edu.ec//public//journals//"+idrevista+"//"+objIssue.getString("imagen"),objIssue.getString("periodo"));
       }

       RVAIssuesAdapter adaptadorIssues =new RVAIssuesAdapter(view.getContext(),ListaIssues);

       ListView lisOpciones=(ListView) view.findViewById(R.id.lv_todoslosnumeros);

       lisOpciones.setAdapter(adaptadorIssues);

       lisOpciones.setOnItemClickListener(new AdapterView.OnItemClickListener() {

           public void onItemClick(AdapterView<?> a, View v, int position, long id) {

               Bundle b = new Bundle();
     /*          String volumen=((Issue)a.getItemAtPosition(position)).getVolumen().toString();
               String numero=((Issue)a.getItemAtPosition(position)).getNumero().toString();
               String imagen="http://revistas.uteq.edu.ec//public//journals//"+idrevista+"//"+((Issue)a.getItemAtPosition(position)).getImagen().toString();*/


               b.putString("idioma",idioma);
               b.putString("idissue",((Issue)a.getItemAtPosition(position)).getIdissue().toString());
               b.putString("imagenissue",((Issue)a.getItemAtPosition(position)).getImagen().toString());
                /*         Toast toast1 =
                        Toast.makeText(view.getContext(),"hola", Toast.LENGTH_SHORT);
                        toast1.show();
                */
               Fragment_articulos fragment = Fragment_articulos.newInstance(b);
               FragmentTransaction ft = getFragmentManager().beginTransaction();
               ft.replace(R.id.content_frame, fragment);
               ft.commit();

           }
       });




    }

}
