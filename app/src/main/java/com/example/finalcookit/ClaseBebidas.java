package com.example.finalcookit;

public class ClaseBebidas {
    private String Titulo;
    private String imagen;
    public String getTitulo() {
        return Titulo;
    }

    public void setTitulo(String titulo) {
        Titulo = titulo;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public ClaseBebidas(String titulo, String imagen) {
        Titulo = titulo;
        this.imagen = imagen;
    }
    public ClaseBebidas(){

    }





}
