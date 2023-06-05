/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gf.app.juegomuseos.controller;

import com.gf.app.juegomuseos.views.GUIPrincipal;
import com.gf.app.juegomuseos.views.GUISelectGame;
import com.gf.app.juegomuseos.views.GUIWhoIs;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;

/**
 *
 * @author priparno
 */
public class MainController {

    private GUIPrincipal view;

    private ActionListener al = (e) -> {
        List<JButton> list = view.getOptions();
        JButton selected = (JButton) e.getSource();
        switch (selected.getText()) {
            case "MODO YINCANA" -> {
                GUIWhoIs guiwh = new GUIWhoIs();
                WhoIsController controllerWH = new WhoIsController(guiwh);
            }
            case "MODO LIBRE" -> {
                GUISelectGame guisg = new GUISelectGame();
                SelectGameController controllerSelectGame = new SelectGameController(guisg, view);
            }
            case "INFORMACION" -> {
                
            }
            case "AJUSTES" -> {

            }
            default ->
                throw new AssertionError();
        }
    };

    public MainController(GUIPrincipal view) {
        this.view = view;
        addActionListener();
        launchView();
    }

    private void addActionListener() {
        for (JButton option : view.getOptions()) {
            option.addActionListener(al);
        }
    }

    private void launchView() {
        view.setVisible(true);
    }

}
