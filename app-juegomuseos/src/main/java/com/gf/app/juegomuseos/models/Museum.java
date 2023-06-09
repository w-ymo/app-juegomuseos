/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gf.app.juegomuseos.models;

/**
 * Museum: clase que refleja la entidad del museo obtenido de la base de datos.
 * 
 * @author priparno
 * @author fercaslu
 */
public class Museum {
    
    /**
     * id_museo: entero que representa el atributo del id del museo en la tabla
     * museos de la base de datos.
     */
    private int id_museo;
    
    /**
     * nombre_museo: cadena de caracteres que representa el atributo del nombre
     * del museo en la tabla museos de la base de datos.
     */
    private String nombre_museo;
    
    /**
     * id_pais: entero que representa el atributo del id del pais en el 
     * que se encuentra el museo de la tabla paises de la base de datos.
     */
    private int id_pais;

    //GETTER/SETTER
    /**
     * getId_museo: devuelve un entero que representa el id del museo.
     * 
     * @return id_museo un entero
     */
    public int getId_museo() {
        return id_museo;
    }

    /**
     * setId_museo: actualiza el entero que representa el id del museo con el 
     * pasado como parametro.
     * 
     * @param id_museo un entero
     */
    public void setId_museo(int id_museo) {
        this.id_museo = id_museo;
    }

    /**
     * getNombre_museo: devuelve una cadena de caracteres que representa el nombre
     * del museo.
     * 
     * @return nombre_museo un entero
     */
    public String getNombre_museo() {
        return nombre_museo;
    }

    /**
     * setNombre_museo: actualiza la cadena de caracteres que representa el nombre
     * del museo con el pasado por parametro.
     * 
     * @param nombre_museo un entero
     */
    public void setNombre_museo(String nombre_museo) {
        this.nombre_museo = nombre_museo;
    }

    /**
     * getId_pais: devuelve un entero que representa el pais en el que se encuentra 
     * ubicado el museo.
     * 
     * @return id_pais un entero
     */
    public int getId_pais() {
        return id_pais;
    }

    /**
     * setId_pais: actualiza el entero que representa el id del pais en el que
     * se encuentra el museo con el pasado como parameto.
     * 
     * @param id_pais un entero
     */
    public void setId_pais(int id_pais) {
        this.id_pais = id_pais;
    }

    /**
     * getId_museo: devuelve un entero que representa el id del autor de la obra.
     * 
     * @return id_autor un entero
     */
    @Override
    public String toString() {
        return "Museums{" + "id_museo=" + id_museo + ", nombre_museo=" + nombre_museo + ", id_pais=" + id_pais + '}';
    }
    
}
