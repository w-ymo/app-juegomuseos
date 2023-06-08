/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gf.app.juegomuseos.utils;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import javax.swing.Timer;
import javax.swing.JLabel;

/**
 * Crono: es el cronometro del juego cuando es modo competitivo.
 *
 * @author priparno
 */
public class Crono {

    /**
     * timer: objeto de tipo {@link Timer} que actualiza a cada segundo el
     * tiempo.
     */
    private Timer timer;
    /**
     * time: el numero de segundos que transcurren
     */
    private int time;
    /**
     * textTime: el componente donde se pondra el cronometro.
     */
    private JLabel textTime;

    /**
     * timeListener: es la accion que realiza {@link #timer}. Esta es aumentar
     * el numero de tiempo en uno y poner el tiempo formateado con
     * {@link #getFormattedTime()}.
     */
    private ActionListener timeListener = (e) -> {
        time++;
        getTextTime().setText(getFormattedTime());
    };

    /**
     * Crono: es el contructor de un cronometro que utiliza {@link Timer}. Cada
     * segundo añade 1 segundo al cronometro.
     */
    public Crono() {
        timer = new Timer(1000, timeListener);
        timer.start();
    }

    /**
     * getFormattedTime: formatea la cantidad de tiempo en segundos a 'mm:ss'.
     *
     * @return una cadena con el tiempo formateado en 'mm:ss'
     */
    public String getFormattedTime() {
        int hour, min, sec;
        hour = (time / 3600);
        min = ((time - hour * 3600) / 60);
        sec = time - (hour * 3600 + min * 60);
        DecimalFormat df = new DecimalFormat("00");
        return df.format(min) + ":" + df.format(sec);
    }

    //GETTER/SETTER
    /**
     * getTextTime: devuelve el componente de tipo {@link JLabel} donde se
     * pondra el texto.
     *
     * @return un componente {@link JLabel}
     */
    public JLabel getTextTime() {
        return textTime;
    }

    /**
     * setTextTime: actualiza el componente con el pasado por parametro y le
     * pone un tamanio de fuente de 20 pixeles.
     *
     * @param textTime un componente de tipo {@link JLabel}
     */
    public void setTextTime(JLabel textTime) {
        this.textTime = textTime;
        textTime.setFont(textTime.getFont().deriveFont(Font.BOLD, 20f));
    }

    /**
     * getTime: devuelve el numero de segundos.
     *
     * @return un entero que representa el numero de segundos
     */
    public int getTime() {
        return time;
    }

    /**
     * setTime: actualiza el numero de segundos.
     *
     * @param time un entero que representa el nuevo numero de segundos.
     */
    public void setTime(int time) {
        this.time = time;
    }
}
