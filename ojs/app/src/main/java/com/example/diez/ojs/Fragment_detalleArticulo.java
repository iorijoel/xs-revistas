package com.example.diez.ojs;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_detalleArticulo extends Fragment {

    globales global;
    View view;

    public Fragment_detalleArticulo() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_detalle_articulo, container, false);
        global =(globales)getActivity().getApplicationContext();//contexto de la clase global

        Toast toast1 =
                Toast.makeText(getActivity().getApplicationContext(),
                        global.getIdSubmission(), Toast.LENGTH_SHORT);

        toast1.show();
        return view;
    }



}
