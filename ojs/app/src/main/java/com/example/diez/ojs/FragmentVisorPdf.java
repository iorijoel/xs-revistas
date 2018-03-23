package com.example.diez.ojs;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Toast;



public class FragmentVisorPdf extends Fragment {

    globales global;
    View view;

    public FragmentVisorPdf() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_visor_pdf, container, false);
        global =(globales)getActivity().getApplicationContext();//contexto de la clase global

        try {
            if (global.getUrlPdf().equals(""))
            {
                Toast toast1 =
                        Toast.makeText(getActivity().getApplicationContext(),
                                "LO SENTIMOS, NO HEMOS ENCONTRADO EL ARCHIVO", Toast.LENGTH_SHORT);
                toast1.show();
            }else   {
                String url="http://revistas.uteq.edu.ec/plugins/generic/pdfJsViewer/pdf.js/web/viewer.html?file="+global.getUrlPdf();
                WebView viewpdf=(WebView) view.findViewById(R.id.webviewPDF);
                viewpdf.getSettings().setJavaScriptEnabled(true);
                viewpdf.loadUrl(url);
            }
        }catch (Exception e){
            Toast toast1 =
                    Toast.makeText(getActivity().getApplicationContext(),
                            "HEMOS ENCONTRADO UN ERROR EN ESTE ARCHIVO", Toast.LENGTH_SHORT);
            toast1.show();
        };

        return view;
    }
}
