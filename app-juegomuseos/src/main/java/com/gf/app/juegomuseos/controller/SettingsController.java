/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gf.app.juegomuseos.controller;

import com.gf.app.juegomuseos.utils.GameData;
import static com.gf.app.juegomuseos.utils.GameData.STYLE;
import com.gf.app.juegomuseos.utils.Music;
import com.gf.app.juegomuseos.views.GUISettings;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JSlider;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.event.ChangeListener;

/**
 *
 * @author priparno
 */
public class SettingsController implements GameControllers {

    private MainController parent;
    private GUISettings view;

    private ChangeListener cl = (e) -> {
        int volume = ((JSlider) e.getSource()).getValue();
        Music.setVolume(volume);
        GameData.updateInfoVolume("Volume%"+volume);
    };

    private ActionListener al = (e) -> {
        JButton but = (JButton) e.getSource();
        if (but.getText().equals("Modo oscuro")) {
            GameData.updateInfoStyle("Modo claro%" + GameData.LIGHT_LAF);
            but.setText("Modo claro");
        } else {
            GameData.updateInfoStyle("Modo oscuro%" + GameData.DARK_LAF);
            but.setText("Modo oscuro");
        }
        try {
            UIManager.setLookAndFeel(GameData.getInfoStyle()[1]);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        SwingUtilities.updateComponentTreeUI(parent.getView());
        this.view.dispose();
    };

    public SettingsController(GUISettings view, MainController parent) {
        this.view = view;
        this.parent = parent;
        addListeners();
        launch();
    }

    private void addListeners() {
        view.getStyleButton().addActionListener(al);
        view.getVolumeSlider().addChangeListener(cl);
    }

    @Override
    public void launch() {
        view.setVisible(true);
    }

}
