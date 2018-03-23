package com.example.diez.ojs;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;

import static android.media.MediaCodec.MetricsConstants.MIME_TYPE;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentIndexaciones extends Fragment {

    globales global;
    View view;

    TextView textohtml;
    WebView web_view_load_data;

    public FragmentIndexaciones() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view =inflater.inflate(R.layout.fragment_indexaciones, container, false);
        global =(globales)getActivity().getApplicationContext();//contexto de la clase global
        //((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(global.getTituloArticulos());
        String HtmlString;
        String uno="1";
        if (global.getIdrevista().equals(uno))
        {
            HtmlString = ""
                    + "<html>"
                    + "  <head>"
                    + "  </head>"
                    + "  <body>"
                    +"<p><a title=\"Indexada Latindex\" href=\"http://www.latindex.org/latindex/ficha?folio=20085\" target=\"_blank\"><img style=\"width: 180px; display: block; margin-left: auto; margin-right: auto;\" src=\"http://revistas.uteq.edu.ec/public/site/images/rcyt/latindex.png\" alt=\"\"></a></p>\n"
                    +" <p><a title=\"REDIB (Red Iberoamericana de Innovación y Conocimiento Científico)\" href=\"https://www.redib.org/recursos/Record/oai_revista2002-revista-ciencia-tecnologia\" target=\"_blank\"><img style=\"display: block; margin-left: auto; margin-right: auto; width: 180px;\" src=\"http://revistas.uteq.edu.ec/public/site/images/rcyt/redib1.png\" alt=\"\"></a></p>\n"
                    +" <p><a title=\"The Directory of Open Access Journals\" href=\"https://doaj.org/toc/1390-4043?source=%7B%22query%22%3A%7B%22filtered%22%3A%7B%22filter%22%3A%7B%22bool%22%3A%7B%22must%22%3A%5B%7B%22term%22%3A%7B%22index.issn.exact%22%3A%221390-4043%22%7D%7D%2C%7B%22term%22%3A%7B%22_type%22%3A%22article%22%7D%7D%5D%7D%7D%2C%22query%22%3A%7B%22match_all%22%3A%7B%7D%7D%7D%7D%2C%22from%22%3A0%2C%22size%22%3A100%7D\" target=\"_blank\"><img style=\"width: 180px; display: block; margin-left: auto; margin-right: auto;\" src=\"http://revistas.uteq.edu.ec/public/site/images/rcyt/doaj.png\" alt=\"\"></a></p>\n"
                    +" <p><a title=\"MIAR\" href=\"http://miar.ub.edu/issn/1390-4051\" target=\"_blank\"><img style=\"width: 180px; display: block; margin-left: auto; margin-right: auto;\" src=\"http://revistas.uteq.edu.ec/public/site/images/rcyt/miar.png\" alt=\"\"></a></p>\n"
                    +" <p><img style=\"width: 150px; display: block; margin-left: auto; margin-right: auto;\" src=\"http://revistas.uteq.edu.ec/public/site/images/rcyt/ebsco_ok.png\" alt=\"\"></p>\n"
                    +" <p><a title=\"DIALNET\" href=\"https://dialnet.unirioja.es/servlet/revista?codigo=19691\" target=\"_blank\"><img style=\"width: 180px; display: block; margin-left: auto; margin-right: auto;\" src=\"http://revistas.uteq.edu.ec/public/site/images/rcyt/Dialnet-logo.png\" alt=\"\"></a></p>\n"
                    +" <p><a title=\"OAJI\" href=\"http://oaji.net/journal-detail.html?number=2488\" target=\"_blank\"><img style=\"width: 180px; display: block; margin-left: auto; margin-right: auto;\" src=\"http://revistas.uteq.edu.ec/public/site/images/rcyt/OAJI.png\" alt=\"\"></a></p>\n"
                    +" <p><img style=\"width: 180px; display: block; margin-left: auto; margin-right: auto;\" src=\"http://revistas.uteq.edu.ec/public/site/images/rcyt/erevistas.png\" alt=\"\"></p>\n"
                    +" <p><a title=\"OAJI\" href=\"http://www.citrevistas.cl/actualidad/b2b_g14.htm\" target=\"_blank\"> <img style=\"display: block; margin-left: auto; margin-right: auto; width: 200px;\" src=\"http://revistas.uteq.edu.ec/public/site/images/rcyt/logo_actualidad_iberoamericana.png\" alt=\"\"></a></p>\n"
                    +" <p><a title=\"PERIODICA. Índice de Revistas Latinoamericanas en Ciencias\" href=\"http://132.248.9.1:8991/F/3GTAK62MQDCKVUIYP8M3XR4XD275XSYDH5AYQCUQ7YF2CGYPKV-35179?func=find-acc&amp;acc_sequence=001224261\" target=\"_blank\"> <img style=\"display: block; margin-left: auto; margin-right: auto; width: 120px;\" src=\"http://revistas.uteq.edu.ec/public/site/images/rcyt/Periodica.png\" alt=\"\"></a></p>"
                    +"  </body>"
                    + "</html>";
        }else
        {
            HtmlString = "";
        }

        web_view_load_data = (WebView) view.findViewById(R.id.wevindex);
        WebSettings webSettings = web_view_load_data.getSettings();
        webSettings.setJavaScriptEnabled(true);
        web_view_load_data.loadData(HtmlString,"text/html", "UTF-8");

        return view;
    }
}
