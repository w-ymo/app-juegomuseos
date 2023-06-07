/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gf.app.juegomuseos.controller;

import com.gf.app.juegomuseos.utils.GameConstants;
import com.gf.app.juegomuseos.utils.Music;
import com.gf.app.juegomuseos.views.GUIInfo;
import com.gf.app.juegomuseos.views.GUIPrincipal;
import com.gf.app.juegomuseos.views.GUISelectGame;
import com.gf.app.juegomuseos.views.GUISettings;
import com.gf.app.juegomuseos.views.GUIWhoIs;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.Timer;

/**
 *
 * @author priparno
 */
public class MainController implements GameControllers {

    private GUIPrincipal view;

    private Timer timer;
    private int fails;

    private ActionListener al = (e) -> {
        List<JButton> list = view.getOptions();
        JButton selected = (JButton) e.getSource();
        switch (selected.getText()) {
            case "MODO YINCANA" -> {
                view.dispose();
                GUIWhoIs guiwh = new GUIWhoIs();
                WhoIsController controllerWH = new WhoIsController(guiwh, this, GameConstants.COMP_MODE);
            }
            case "MODO LIBRE" -> {
                view.dispose();
                GUISelectGame guisg = new GUISelectGame();
                SelectGameController controllerSelectGame = new SelectGameController(guisg, this);
            }
            case "INFORMACION" -> {
                InfoController controllerInfo = new InfoController(new GUIInfo(view, true));
            }
            case "AJUSTES" -> {
                SettingsController controllerSettings = new SettingsController(new GUISettings(view, true));
            }
            case "SALIR" -> {
                Music.stop();
                view.dispose();
            }
            default ->
                throw new AssertionError();
        }
    };

    public MainController(GUIPrincipal view) {
        this.view = view;
        addActionListener();
        launchView();
        Music.start();
    }

    private void addActionListener() {
        for (JButton option : view.getOptions()) {
            option.addActionListener(al);
        }
    }

    private void launchView() {
        view.setVisible(true);
    }

    public Timer getTimer() {
        return timer;
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }

    public int getFails() {
        return fails;
    }

    public void setFails(int fails) {
        this.fails = fails;
    }

    public GUIPrincipal getView() {
        return view;
    }

    public void setView(GUIPrincipal view) {
        this.view = view;
    }

}
