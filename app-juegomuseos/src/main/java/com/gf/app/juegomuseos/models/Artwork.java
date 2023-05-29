/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gf.app.juegomuseos.models;

/**
 *
 * @author noelp
 */
public class Artwork {

    private int id_obra;
    private String nombre_obra;
    private String descripcion_obra;
    private String clave_obra;
    private String disciplina;
    private String imagen_obra;
    private int id_museo;
    private int id_autor;

    public int getId_obra() {
        return id_obra;
    }

    public void setId_obra(int id_obra) {
        this.id_obra = id_obra;
    }

    public String getClave_obra() {
        return clave_obra;
    }

    public void setClave_obra(String clave_obra) {
        this.clave_obra = clave_obra;
    }

    public String getNombre_obra() {
        return nombre_obra;
    }

    public void setNombre_obra(String nombre_obra) {
        this.nombre_obra = nombre_obra;
    }

    public String getDescripcion_obra() {
        return descripcion_obra;
    }

    public void setDescripcion_obra(String descripcion_obra) {
        this.descripcion_obra = descripcion_obra;
    }

    public String getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(String disciplina) {
        this.disciplina = disciplina;
    }

    public String getImagen_obra() {
        return imagen_obra;
    }

    public void setImagen_obra(String imagen_obra) {
        this.imagen_obra = imagen_obra;
    }

    public int getId_museo() {
        return id_museo;
    }

    public void setId_museo(int id_museo) {
        this.id_museo = id_museo;
    }

    public int getId_autor() {
        return id_autor;
    }

    public void setId_autor(int id_autor) {
        this.id_autor = id_autor;
    }

    @Override
    public String toString() {
        return "Artwork{" + "id_obra=" + id_obra + ", nombre_obra=" + nombre_obra + ", descripcion_obra=" + descripcion_obra + ", disciplina=" + disciplina + ", id_museo=" + id_museo + ", id_autor=" + id_autor + '}';
    }

}
