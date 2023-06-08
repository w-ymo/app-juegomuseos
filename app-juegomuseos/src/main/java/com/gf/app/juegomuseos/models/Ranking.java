/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gf.app.juegomuseos.models;

/**
 *
 * @author noelp
 */
public class Ranking implements Comparable<Ranking> {

    private int id_ranking;
    private String nombre_usuario;
    private String puntuacion;

    public int getId_ranking() {
        return id_ranking;
    }

    public void setId_ranking(int id_ranking) {
        this.id_ranking = id_ranking;
    }

    public String getNombre_usuario() {
        return nombre_usuario;
    }

    public void setNombre_usuario(String nombre_usuario) {
        this.nombre_usuario = nombre_usuario;
    }

    public String getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(String puntuacion) {
        this.puntuacion = puntuacion;
    }

    @Override
    public String toString() {
        return "Ranking{" + "id_ranking=" + id_ranking + ", nombre_usuario=" + nombre_usuario + ", puntuacion=" + puntuacion + '}';
    }

    @Override
    public int compareTo(Ranking o) {
        return seconds(this.getPuntuacion()) - seconds(o.getPuntuacion());
    }

    private int seconds(String time) {
        String parts[] = time.split(":");
        int min = Integer.parseInt(parts[0]);
        int sec = Integer.parseInt(parts[1]);
        return min * 60 + sec;
    }

}
