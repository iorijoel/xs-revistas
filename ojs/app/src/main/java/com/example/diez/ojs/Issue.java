package com.example.diez.ojs;

/**
 * Created by joel_ on 15/2/2018.
 */

public class Issue {

    String idrevista;
    String idissue;
    String volumen;
    String numero;
    String año;
    String fechapublicacion;
    String imagen;
    String perido;

    public Issue(String idrevista, String idissue, String volumen, String numero, String año, String fechapublicacion, String imagen,String periodo) {
        this.idrevista=idrevista;
        this.idissue=idissue;
        this.volumen=volumen;
        this.numero=numero;
        this.año=año;
        this.fechapublicacion=fechapublicacion;
        this.imagen=imagen;
        this.perido=periodo;
    }

    public String getIdrevista() {
        return idrevista;
    }

    public void setIdrevista(String idrevista) {
        this.idrevista = idrevista;
    }

    public String getIdissue() {
        return idissue;
    }

    public void setIdissue(String idissue) {
        this.idissue = idissue;
    }

    public String getVolumen() {
        return volumen;
    }

    public void setVolumen(String volumen) {
        this.volumen = volumen;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getAño() {
        return año;
    }

    public void setAño(String año) {
        this.año = año;
    }

    public String getFechapublicacion() {
        return fechapublicacion;
    }

    public void setFechapublicacion(String fechapublicacion) {
        this.fechapublicacion = fechapublicacion;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getPerido() {
        return perido;
    }

    public void setPerido(String perido) {
        this.perido = perido;
    }
}
