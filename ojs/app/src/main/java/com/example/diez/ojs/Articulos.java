package com.example.diez.ojs;

/**
 * Created by Diez on 20/2/2018.
 */

public class Articulos {
    String id;
    String titulo;
    String autores;
    String paginas;
    String imagen;

    public Articulos(String id, String titulo, String autores, String paginas, String imagen) {
        this.id = id;
        this.titulo = titulo;
        this.autores = autores;
        this.paginas = paginas;
        this.imagen = imagen;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutores() {
        return autores;
    }

    public void setAutores(String autores) {
        this.autores = autores;
    }

    public String getPaginas() {
        return paginas;
    }

    public void setPaginas(String paginas) {
        this.paginas = paginas;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
}
