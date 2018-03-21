package com.example.diez.ojs;

import android.app.Application;

/**
 * Created by Diez on 16/3/2018.
 */

public class globales extends Application {
    private String idrevista;
    private String nombrerevista;
    private String tituloArticulos;
    private String idioma;
    private String idissue;
    private String imagenissue;
    private String idSubmission;
    private String UrlPdf;
    private String abreviaturaRevista;

    public String getUrlPdf() {
        return UrlPdf;
    }

    public void setUrlPdf(String urlPdf) {
        UrlPdf = urlPdf;
    }

    public String getAbreviaturaRevista() {
        return abreviaturaRevista;
    }

    public void setAbreviaturaRevista(String abreviaturaRevista) {
        this.abreviaturaRevista = abreviaturaRevista;
    }

    public String getIdSubmission() {
        return idSubmission;
    }

    public void setIdSubmission(String idSubmission) {
        this.idSubmission = idSubmission;
    }

    public String getTituloArticulos() {
        return tituloArticulos;
    }

    public void setTituloArticulos(String tituloArticulos) {
        this.tituloArticulos = tituloArticulos;
    }

    public String getNombrerevista() {
        return nombrerevista;
    }

    public void setNombrerevista(String nombrerevista) {
        this.nombrerevista = nombrerevista;
    }

    public String getImagenissue() {
        return imagenissue;
    }

    public void setImagenissue(String imagenissue) {
        this.imagenissue = imagenissue;
    }

    public String getIdissue() {
        return idissue;
    }

    public void setIdissue(String idissue) {
        this.idissue = idissue;
    }

    public String getIdrevista() {
        return idrevista;
    }

    public void setIdrevista(String idrevista) {
        this.idrevista = idrevista;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }
}
