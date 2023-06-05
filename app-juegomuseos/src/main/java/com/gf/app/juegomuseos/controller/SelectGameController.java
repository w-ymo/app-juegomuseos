/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gf.app.juegomuseos.controller;

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
        view.dispose();
        //parentView.setVisible(false);
        switch (selected.getName()) {
            case "WH" -> {
                System.out.println("TU madre");
                WhoIsController controllerWH = new WhoIsController(new GUIWhoIs());
            }
            case "TF" -> {
//                GUIMuseumsTF guim = new GUIMuseumsTF();
//                MuseumsTFController controllerMuseum = new MuseumsTFController(guim);
                System.out.println("Tu madre 2");
            }
            case "GF" -> {
//                GUIGregorioFernandez guigf = new GUIGregorioFernandez();
//                GregFernandezController controllerGF = new GregFernandezController(guigf);
                System.out.println("Ru madre 3");
            }
            case "MP" -> {
                System.out.println("tu madre 4");
            }
            default -> {
                System.out.println("no funfa");
            }
        }
//        GUIWhoIs guiwh = new GUIWhoIs();
//        WhoIsController controllerWH = new WhoIsController(guiwh);

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
