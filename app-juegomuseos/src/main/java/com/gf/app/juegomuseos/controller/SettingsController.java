/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gf.app.juegomuseos.controller;

import com.gf.app.juegomuseos.utils.GameData;
import com.gf.app.juegomuseos.views.GUISettings;
import java.awt.event.ActionListener;
import javax.swing.JButton;

/**
 *
 * @author priparno
 */
public class SettingsController implements GameControllers {
    
    private GUISettings view;
    
    private ActionListener al = (e) -> {
        JButton but = (JButton) e.getSource();
        if (but.getText().equals("Modo oscuro")) {
            GameData.updateInfoStyle("Modo claro-" + GameData.LIGHT_LAF);
            but.setText("Modo claro");
        } else {
            GameData.updateInfoStyle("Modo oscuro-" + GameData.DARK_LAF);
            but.setText("Modo oscuro");
        }
    };
    
    public SettingsController(GUISettings view) {
        this.view = view;
        addActionListener();
        launchView();
    }
    
    private void launchView() {
        view.setVisible(true);
    }
    
    private void addActionListener() {
        view.getStyleButton().addActionListener(al);
    }
    
}