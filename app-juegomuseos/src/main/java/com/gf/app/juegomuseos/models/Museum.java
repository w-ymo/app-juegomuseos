/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gf.app.juegomuseos.models;

/**
 *
 * @author noelp
 */
public class Museum {
    
    private int id_museo;
    private String nombre_museo;
    private int id_pais;

    public int getId_museo() {
        return id_museo;
    }

    public void setId_museo(int id_museo) {
        this.id_museo = id_museo;
    }

    public String getNombre_museo() {
        return nombre_museo;
    }

    public void setNombre_museo(String nombre_museo) {
        this.nombre_museo = nombre_museo;
    }

    public int getId_pais() {
        return id_pais;
    }

    public void setId_pais(int id_pais) {
        this.id_pais = id_pais;
    }

    @Override
    public String toString() {
        return "Museums{" + "id_museo=" + id_museo + ", nombre_museo=" + nombre_museo + ", id_pais=" + id_pais + '}';
    }
    
}
