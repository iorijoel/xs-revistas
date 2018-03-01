package com.example.diez.ojs;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class Fragment_ultimo_numero extends Fragment {


    View view;
    public Fragment_ultimo_numero() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view =inflater.inflate(R.layout.fragment_ultimo_numero, container, false);

        TextView text = (TextView) view.findViewById(R.id.idiomaespa);

        //text.setText();

        return view;
    }
}
