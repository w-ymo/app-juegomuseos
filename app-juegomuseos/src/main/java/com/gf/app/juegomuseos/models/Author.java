/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gf.app.juegomuseos.models;

/**
 * Author: clase que refleja la entidad del autor obtenida de la base de datos.
 * 
 * @author priparno
 * @author fercaslu
 */
public class Author {
    
    /**
     * id_autor: entero que representa el atributo del id del autor en la tabla
     * autores de la base de datos.
     */
    private int id_autor;
    
    /**
     * nombre_autor: cadena de caracteres que representa el atributo del nombre
     * del autor en la tabla autores de la base de datos.
     */
    private String nombre_autor;
    
    /**
     * id_pais: entero que representa el atributo del id del pais al que pertence
     * el autor.
     */
    private int id_pais;

    //GETTER/SETTER
    /**
     * getId_museo: devuelve un entero que representa el id del autor.
     * 
     * @return id_autor un entero
     */
    public int getId_autor() {
        return id_autor;
    }

    /**
     * setId_autor: actualiza el entero que representa el id del autor con el 
     * pasado como parametro.
     * 
     * @param id_autor un entero
     */
    public void setId_autor(int id_autor) {
        this.id_autor = id_autor;
    }

    /**
     * getNombre_autor: devuelve una cadena de caracteres que reoresebta el nombre
     * de la obra.
     * 
     * @return nombre_autor una cadena de caracteres
     */
    public String getNombre_autor() {
        return nombre_autor;
    }
    
    /**
     * setNombre_autor: actyaliza la cadena de caracteres que represeta el nombre
     * del autor con el pasado como parametro.
     * 
     * @param nombre_autor una cadena de caracteres
     */
    public void setNombre_autor(String nombre_autor) {
        this.nombre_autor = nombre_autor;
    }

    /**
     * getId_paid: devuelve un entero que representa el id del pais del autor.
     * 
     * @return id_pais un entero
     */
    public int getId_pais() {
        return id_pais;
    }

    /**
     * setId_pais: actualiza el entero que representa el id del pais del autor 
     * con el pasado como parametro.
     * 
     * @param id_pais un entero
     */
    public void setId_pais(int id_pais) {
        this.id_pais = id_pais;
    }

    /**
     * toString
     * 
     * @return toString una cadena de caracteres
     */
    @Override
    public String toString() {
        return "Authors{" + "id_autor=" + id_autor + ", nombre_autor=" + nombre_autor + ", id_pais=" + id_pais + '}';
    }
}
