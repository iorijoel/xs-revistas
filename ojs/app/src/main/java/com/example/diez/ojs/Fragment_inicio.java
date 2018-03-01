package com.example.diez.ojs;



import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

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
    //para rv crd
    private RecyclerView recyclerView;
    private GridLayoutManager gridLayoutManager;
    private RVARevistasAdapter adapter;
    private List<Revistas> revistas =new ArrayList<>();
    View view;
    //para localidad - cambio de idioma
    private Locale locale;
    private Configuration config = new Configuration();

    public Fragment_inicio() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)  {

        view = inflater.inflate(R.layout.fragment_inicio, container, false);

        RadioButton rbesp = (RadioButton) view.findViewById(R.id.idiomaespa);
        RadioButton rbing = (RadioButton) view.findViewById(R.id.idiomaingl);

        if (Locale.getDefault().getDisplayLanguage().toString().equals("español")){
            rbesp.setChecked(true);
        }else if (Locale.getDefault().getDisplayLanguage().toString().equals("English")){
            rbing.setChecked(true);
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
        //para mostar las revistas
        recyclerView = (RecyclerView) view.findViewById(R.id.rv_revistas);
        ConectWSrevistas();
        return view;
    }

    private void ConectWSrevistas() {
        Map<String, String> datos = new HashMap<String, String>();
        WebService ws= new WebService("http://www.json-generator.com/api/json/get/crdjDxQwaG", datos,view.getContext(),Fragment_inicio.this);
        ws.execute("");
    }

    @Override
    public void processFinish(String result)  throws JSONException {
        JSONObject jsonJournal = new JSONObject(result);
        JSONArray jsonArrayJournal = jsonJournal.getJSONArray("revistas");
        for(int i=0; i< jsonArrayJournal.length();i++)
        {
            JSONObject objJournal = jsonArrayJournal.getJSONObject(i);
            Revistas data= new Revistas(objJournal.getString("nombre"),objJournal.getString("img"),objJournal.getString("url"));
            revistas.add(data);
        }
        gridLayoutManager = new GridLayoutManager(view.getContext(),1);
        recyclerView.setLayoutManager(gridLayoutManager);
        adapter = new RVARevistasAdapter(view.getContext(),revistas);
        recyclerView.setAdapter(adapter);
    }
}
