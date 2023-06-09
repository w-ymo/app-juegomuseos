/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gf.app.juegomuseos.models;

/**
 * Country: clase que refleja la entidad del pais obtenido de la base de datos.
 * 
 * @author priparno
 * @author fercaslu
 */
public class Country {
    
    /**
     * id_pais: entero que representa el atributo del id del pais en la tabla de
     * paises de la base de datos.
     */
    private int id_pais;
    
    /**
     * nombre_pais: cadena de caracteres que representa el atributo del nombre
     * de la obra en la tabla de paises de la base de datos.
     */
    private String nombre_pais;

    //GETTER/SETTER
    /**
     * getId_pais: devuelve un entero que representa el id del pais.
     * 
     * @return id_pais
     */
    public int getId_pais() {
        return id_pais;
    }

    /**
     * setId_pais: actualiza el entero que representa el id del pais con el 
     * pasado como parametro.
     * 
     * @param id_pais un entero
     */
    public void setId_pais(int id_pais) {
        this.id_pais = id_pais;
    }

    /**
     * getNombre_pais: devuelve una cadena de caracteres que representa el nombre
     * del pais.
     * 
     * @return nombre_pais una cadena de caracteres
     */
    public String getNombre_pais() {
        return nombre_pais;
    }

    /**
     * setNombre_pais: actualiza la cadena de caracteres que representa el nombre
     * del pais con el pasado como parametro.
     * 
     * @param nombre_pais una cadena de caracteres
     */
    public void setNombre_pais(String nombre_pais) {
        this.nombre_pais = nombre_pais;
    }

    /**
     * toString
     * 
     * @return toString una cadena de caracteres
     */
    @Override
    public String toString() {
        return "Country{" + "id_pais=" + id_pais + ", nombre_pais=" + nombre_pais + '}';
    }
 
    
    
}
