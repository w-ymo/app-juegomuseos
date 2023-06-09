/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gf.app.juegomuseos.models;

/**
 * Artwork: clase que refleja la entidad de la obra obtenida de la base de datos.
 * 
 * @author priparno
 * @author fercaslu
 */
public class Artwork {

    /**
     * id_obra: entero que representa el atributo del id de la obra en la tabla 
     * obras de la base de datos.
     */
    private int id_obra;
    
    /**
     * nombre_obra: cadena de caracteres que representa el atributo del nombre 
     * de la obra en la tabla obras de la base de datos.
     */
    private String nombre_obra;
    
    /**
     * descripcion_obra: cadena de caracteres que representa el atributo del nombre 
     * de la obra en la tabla obras de la base de datos.
     */
    private String descripcion_obra;
    
    /**
     * id_obra: entero que representa el atributo del id de la tabla obras de 
     * la base de datos.
     */
    private String clave_obra;
    
    /**
     * id_obra: entero que representa el atributo del id de la tabla obras de 
     * la base de datos.
     */
    private String disciplina;
    
    /**
     * id_obra: entero que representa el atributo del id de la tabla obras de 
     * la base de datos.
     */
    private String imagen_obra;
    
    /**
     * id_obra: entero que representa el atributo del id de la tabla obras de 
     * la base de datos.
     */
    private int id_museo;
    
    /**
     * id_obra: entero que representa el atributo del id de la tabla obras de 
     * la base de datos.
     */
    private int id_autor;
    
    /**
     * id_obra: entero que representa el atributo del id de la tabla obras de 
     * la base de datos.
     */
    private double longitud;
    
    /**
     * id_obra: entero que representa el atributo del id de la tabla obras de 
     * la base de datos.
     */
    private double latitud;

    //GETTER/SETTER
    /**
     * getId_obra: devuelve un entero que representa el id de la obra.
     * 
     * @return id_obra
     */
    public int getId_obra() {
        return id_obra;
    }

    /**
     * setId_obra: actualiza el entero que representa id de la obra con el pasado como 
     * parameto.
     * 
     * @param id_obra 
     */
    public void setId_obra(int id_obra) {
        this.id_obra = id_obra;
    }

    /**
     * getClave_obra: devuelve una cadena de caracteres que representa la palabra 
     * clave de la obra.
     * 
     * @return clave_obra una cadena de caracteres que representa la palabra clave
     * de la obra
     */
    public String getClave_obra() {
        return clave_obra;
    }

    /**
     * setClave_obra: actualiza la cadena de caracteres que representa la palabra 
     * clave de la obra con la pasada como parameto.
     * 
     * @param clave_obra una cadena de caracteres
     */
    public void setClave_obra(String clave_obra) {
        this.clave_obra = clave_obra;
    }

    /**
     * geNombre_obra: devuelve una cadena de caracteres que representa el nombre 
     * de la obra.
     * 
     * @return nombre_obra una cadena de caracteres
     */
    public String getNombre_obra() {
        return nombre_obra;
    }

    /**
     * setNombre_obra: actualiza la cadena de caracteres que representa el 
     * nombre de la obra con el pasado como parameto.
     * 
     * @param nombre_obra una cadena de caracteres
     */
    public void setNombre_obra(String nombre_obra) {
        this.nombre_obra = nombre_obra;
    }

    /**
     * getDescripcion_obra: devuelve una cadena de caracteres que representa la 
     * descripcion de la obra.
     * 
     * @return descripcion_obra una cadena de caracteres
     */
    public String getDescripcion_obra() {
        return descripcion_obra;
    }

    /**
     * setDescipcion_obra: actualiza la cadena de caracteres que representa la 
     * descripcion de la obra con la pasada como parameto.
     * 
     * @param descripcion_obra una cadena de caracteres
     */
    public void setDescripcion_obra(String descripcion_obra) {
        this.descripcion_obra = descripcion_obra;
    }

    /**
     * getDisciplina: devuelve una cadena de caracteres que representa la 
     * disciplina de la obra.
     * 
     * @return disciplina una cadena de caracteres
     */
    public String getDisciplina() {
        return disciplina;
    }

    /**
     * setDisciplina: actualiza la cadena de caracteres que representa la 
     * disciplina de la obra con la pasada como parameto.
     * 
     * @param disciplina una cadena de caracteres
     */
    public void setDisciplina(String disciplina) {
        this.disciplina = disciplina;
    }

    /**
     * getImagen_obra: devuelve una cadena de caracteres que representa la url
     * de la imagen de la obra.
     * 
     * @return imagen_obra una cadena de caracteres que representa la url de la 
     * imagen
     */
    public String getImagen_obra() {
        return imagen_obra;
    }

    /**
     * setImagen_obra: actualiza la cadena de caracteres que representa la 
     * url de la obra con la pasada como parameto.
     * 
     * @param imagen_obra una cadena de caracteres que representa la url de la 
     * imagen
     */
    public void setImagen_obra(String imagen_obra) {
        this.imagen_obra = imagen_obra;
    }

    /**
     * getId_museo: devuelve un entero que representa el entero
     * 
     * @return id_museo un entero
     */
    public int getId_museo() {
        return id_museo;
    }

    /**
     * setId_museo: actualiza el entero que representa el id del museo en el que
     * se encuentra la obra con el pasado como parameto.
     * 
     * @param id_museo un entero
     */
    public void setId_museo(int id_museo) {
        this.id_museo = id_museo;
    }

    public int getId_autor() {
        return id_autor;
    }

    /**
     * setId_museo: actualiza el entero que representa el id del autor que ha 
     * realizado la obra con el pasado como parameto.
     * 
     * @param id_autor un entero
     */
    public void setId_autor(int id_autor) {
        this.id_autor = id_autor;
    }

    /**
     * getLongitud: devuelve una decimal que representa la longitud de la posicion 
     * en la que se encuentra la obra.
     * 
     * @return longitud un numero decimal
     */
    public double getLongitud() {
        return longitud;
    }

    /**
     * setLongitud: actualiza el decimal que representa la longitud de la posicion
     * en la que se encuentra la obra
     * 
     * @param longitud un numero decimal
     */
    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    /**
     * 
     * 
     * @return latitud un numero decimal
     */
    public double getLatitud() {
        return latitud;
    }

    /**
     * setLatitud: actualiza el decimal que representa la latitud de la posicion
     * en la que se encuentra la obra
     * 
     * @param longitud un numero decimal
     */
    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    
    
    @Override
    public String toString() {
        return "Artwork{" + "id_obra=" + id_obra + ", nombre_obra=" + nombre_obra + ", descripcion_obra=" + descripcion_obra + ", disciplina=" + disciplina + ", id_museo=" + id_museo + ", id_autor=" + id_autor + '}';
    }

}
