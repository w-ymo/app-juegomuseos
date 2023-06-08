/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gf.app.juegomuseos.controller;

import com.gf.app.juegomuseos.utils.GameConstants;
import com.gf.app.juegomuseos.views.GUIGregorioFernandez;
import com.gf.app.juegomuseos.views.GUIMap;
import com.gf.app.juegomuseos.views.GUIMuseumsTF;
import com.gf.app.juegomuseos.views.GUISelectGame;
import com.gf.app.juegomuseos.views.GUIWhoIs;
import java.awt.event.ActionListener;
import javax.swing.JButton;

/**
 *
 * @author noelp
 */
public class SelectGameController implements GameControllers {

    private MainController parent;
    private GUISelectGame view;

    private ActionListener al = (e) -> {
        JButton selected = (JButton) e.getSource();
        parent.getView().dispose();
        switch (selected.getName()) {
            case GameConstants.WHOIS_CODE -> {
                WhoIsController controllerWH = new WhoIsController(new GUIWhoIs(), this, GameConstants.FREE_MODE);
            }
            case GameConstants.TRUEFALSE_CODE -> {
                MuseumsTFController controllerMTF = new MuseumsTFController(new GUIMuseumsTF(), this, GameConstants.FREE_MODE);
            }
            case GameConstants.GREGORIO_CODE -> {
                GUIGregorioFernandez guigf = new GUIGregorioFernandez();
                GregFernandezController controllerGF = new GregFernandezController(guigf, this, GameConstants.FREE_MODE);
            }
            case GameConstants.MAPGAME_CODE -> {
                MapController controllerMP = new MapController(new GUIMap(), this, GameConstants.FREE_MODE);
            }
            default -> {
                System.out.println("Error");
            }
        }
    };

    private ActionListener exitActionListener = (e) -> {
        parent.getView().setVisible(true);
        view.dispose();
    };
    
    public SelectGameController(GUISelectGame view, MainController parent) {
        this.view = view;
        this.parent = parent;
        addListeners();
        launch();
    }

    private void addListeners() {
        for (JButton option : view.getOptions()) {
            option.addActionListener(al);
        }
        view.getExitButton().addActionListener(exitActionListener);
    }

    public MainController getMainController() {
        return (MainController) parent;
    }

    public GUISelectGame getView() {
        return view;
    }

    public void setView(GUISelectGame view) {
        this.view = view;
    }
    
    @Override
    public void launch() {
        view.setVisible(true);
    }

}
