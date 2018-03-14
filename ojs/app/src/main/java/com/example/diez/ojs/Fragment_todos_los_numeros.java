package com.example.diez.ojs;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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


public class Fragment_todos_los_numeros extends Fragment implements Asynchtask {

    private RecyclerView recyclerView;
    private GridLayoutManager gridLayoutManager;
    private RVAIssuesAdapter adapter;
    private List<Issue> issue=new ArrayList<>();

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

       /* text = view.findViewById(R.id.htmlprueba);
        text.setTextAlignment(TextAlignment.JUSTIFIED);*/
        //text.setText(Html.fromHtml("<h2>titulo</2><br><br><p>hola buenos dias ajsdhgjashd</p>"));

        //Bundle bundle = getActivity().getIntent().getExtras();

        Bundle bundle =getArguments();

        String nombrerevista,idrevista;

        //Cogemos  los datos enviados
        nombrerevista =bundle.getString("nombrerevista");
        idrevista =bundle.getString("idrevista");

        Toast toast1 =
                Toast.makeText(view.getContext(),"revista"+nombrerevista+"id: "+idrevista, Toast.LENGTH_LONG);
        toast1.show();

        recyclerView = (RecyclerView) view.findViewById(R.id.rv_todos_los_numeros);
        ConectWSIssue();
        return view;
    }

    private void ConectWSIssue() {
        Map<String, String> datos = new HashMap<String, String>();
        WebService ws= new WebService("http://revistas.uteq.edu.ec/WsRevista/obtener_todos_numeros.php", datos,view.getContext(),Fragment_todos_los_numeros.this);
        //WebService ws= new WebService("http://www.json-generator.com/api/json/get/bUXOmoZJwy?indent=2", datos,view.getContext(),Fragment_todos_los_numeros.this);
        ws.execute("");
    }

    @Override
    public void processFinish(String result)  throws JSONException {
        JSONObject jsonIssues = new JSONObject(result);
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
        recyclerView.setAdapter(adapter);
    }

}
