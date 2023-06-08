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
 * Crono: 
 * 
 * @author priparno
 */
public class Crono {

    private Timer timer;
    private int time;
    private JLabel textTime;

    private ActionListener al = (e) -> {
        time++;
        textTime.setText(getFormattedTime());
    };

    public Crono() {
        timer = new Timer(1000, al);
        timer.start();
    }

    public JLabel getTextTime() {
        return textTime;
    }

    public void setTextTime(JLabel textTime) {
        this.textTime = textTime;
        textTime.setFont(textTime.getFont().deriveFont(Font.BOLD, 20f));
    }

    public String getFormattedTime() {
        int hour, min, sec;
        hour = (time / 3600);
        min = ((time - hour * 3600) / 60);
        sec = time - (hour * 3600 + min * 60);
        DecimalFormat df = new DecimalFormat("00");
        return df.format(min) + ":" + df.format(sec);
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
}
