/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gf.app.juegomuseos.controller;

import com.gf.app.juegomuseos.utils.GameConstants;
import com.gf.app.juegomuseos.utils.GameData;
import com.gf.app.juegomuseos.utils.Music;
import com.gf.app.juegomuseos.views.GUISettings;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JSlider;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.event.ChangeListener;

/**
 * SettingsController: es el controlador de la ventana {@link GUISettings}.
 * Desde este se lanza una vista con dos apartados de ajustes: ajustes de estilo
 * y ajustes de volumen. Ajustes de estilo consta de un boton que alterna un
 * estilo claro y uno oscuro y en ajustes de volumen se puede seleccionar el
 * volumen deseado.
 *
 * @author priparno
 * @author fercaslu
 */
public class SettingsController implements GameControllers {

    /**
     * view: es la vista de la ventana.
     */
    private GUISettings view;
    /**
     * parent: es el controlador padre, el que le llama. En este caso
     * {@link MainController}.
     */
    private MainController parent;

    /**
     * listenerSlider: es el escuchador de accion de la barra de volumen.
     * escoges el volumen y almacena en un archivo de texto el volumen que se ha
     * seleccionado por ultima vez.
     *
     * @see Music
     * @see GameData
     * @see GameConstants
     */
    private ChangeListener listenerSlider = (e) -> {
        int volume = ((JSlider) e.getSource()).getValue();
        Music.setVolume(volume);
        GameData.updateInfoVolume("Volume%" + volume);
    };

    /**
     * listenerButtons: es el escuchador de accion de los botones. Alterna entre
     * Modo oscuro y Modo claro, actualizando el LookAndFeel de la aplicacion.
     * Además almacena en un archivo de texto el ultimo modo seleccionado.
     *
     * @see GameConstants
     * @see GameData
     */
    private ActionListener listenerButtons = (e) -> {
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

    //CONSTRUCTOR
    /**
     * SettingsController: es el constructor del controlador. Para que funcione
     * correctamente, necesita la vista de los ajustes y el controlador padre.
     * En el controlador además se lanza la vista, es decir, se muestra la vista
     * automaticamente.
     *
     * @see GUISettings
     * @see MainController
     *
     * @param view la vista de los ajustes {@link GUISettings}
     * @param parent el controlador padre de tipo {@link MainController}
     */
    public SettingsController(GUISettings view, MainController parent) {
        this.view = view;
        this.parent = parent;
        addListeners();
        launch();
    }

    /**
     * addListeners: aniade a los elementos de la ventana los escuchadores para
     * manejar las acciones del usuario.
     *
     * @see ActionListener
     * @see ChangeListener
     */
    private void addListeners() {
        view.getStyleButton().addActionListener(listenerButtons);
        view.getVolumeSlider().addChangeListener(listenerSlider);
    }

    /**
     * launch: metodo que se implementa de {@link GameControllers}. En este
     * metodo se lanza el juego y se pone visible la ventana.
     */
    @Override
    public void launch() {
        view.setVisible(true);
    }

}
