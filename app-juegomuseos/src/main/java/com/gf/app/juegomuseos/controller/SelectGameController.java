/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gf.app.juegomuseos.controller;

import com.gf.app.juegomuseos.utils.GameConstants;
import com.gf.app.juegomuseos.views.GUIGregorioFernandez;
import com.gf.app.juegomuseos.views.GUIMuseumsTF;
import com.gf.app.juegomuseos.views.GUIPrincipal;
import com.gf.app.juegomuseos.views.GUISelectGame;
import com.gf.app.juegomuseos.views.GUIWhoIs;
import java.awt.event.ActionListener;
import javax.swing.JButton;

/**
 *
 * @author noelp
 */
public class SelectGameController {

    private GUIPrincipal parentView;
    private GUISelectGame view;

    private ActionListener al = (e) -> {
        JButton selected = (JButton) e.getSource();
        parentView.dispose();
        view.dispose();
        //parentView.setVisible(false);
        switch (selected.getName()) {
            case GameConstants.WHOIS_CODE -> {
                WhoIsController controllerWH = new WhoIsController(new GUIWhoIs(), parentView, GameConstants.FREE_MODE);
            }
            case GameConstants.TRUEFALSE_CODE -> {
//                GUIMuseumsTF guim = new GUIMuseumsTF();
//                MuseumsTFController controllerMuseum = new MuseumsTFController(guim);
            }
            case GameConstants.GREGORIO_CODE -> {
                GUIGregorioFernandez guigf = new GUIGregorioFernandez();
                GregFernandezController controllerGF = new GregFernandezController(guigf, parentView, GameConstants.FREE_MODE);
            }
            case GameConstants.MAPGAME_CODE -> {
                System.out.println("map");
            }
            default -> {
                System.out.println("error");
            }
        }
    };

    public SelectGameController(GUISelectGame view) {
        this.view = view;
        addActionListener();
        launchView();
    }

    public SelectGameController(GUISelectGame view, GUIPrincipal parentView) {
        this.view = view;
        this.parentView = parentView;
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
