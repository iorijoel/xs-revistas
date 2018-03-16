package com.example.diez.ojs;

/**
 * Created by Diez on 13/2/2018.
 */

public class Revistas {
    String idrevista;
    String nombre;
    String imagen;
    String descripcion;

    public Revistas(String idrevista,String nombre, String imagen, String descripcion) {
        this.idrevista = idrevista;
        this.nombre = nombre;
        this.imagen = imagen;
        this.descripcion = descripcion;
    }

    public String getIdrevista() {
        return idrevista;
    }

    public void setIdrevista(String idrevista) {
        this.idrevista = idrevista;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
