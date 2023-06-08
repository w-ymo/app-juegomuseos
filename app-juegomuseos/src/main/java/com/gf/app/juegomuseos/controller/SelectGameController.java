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
 * SelectGameController: es el controlador de la ventana
 * {@link SelectGameController}. Desde esta se lanza una vista con un menu donde
 * escoger los modos de juego que se recogen en {@link GameConstants}.
 *
 * @author priparno
 * @author fercaslu
 */
public class SelectGameController implements GameControllers {

    /**
     * view: es la vista de la ventana.
     */
    private GUISelectGame view;
    /**
     * parent: es el controlador padre, el que le llama. En este caso
     * {@link MainController}.
     */
    private MainController parent;

    /**
     * listenerButtons: es el escuchador de accion de los botones de seleccion.
     * Dependiendo del nombre del boton, que corresponde a uno de
     * {@link GameConstants#GAMES_CODES}, lanza un juego u otro.
     *
     * @see GameConstants
     */
    private ActionListener listenerButtons = (e) -> {
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

    /**
     * exitListener: es el escuchador de accion del botón de vuelta. Si se
     * activa muestra el menu principal.
     */
    private ActionListener exitListener = (e) -> {
        parent.getView().setVisible(true);
        view.dispose();
    };

    /**
     * SelectGameController: es el constructor del controlador. Para que
     * funcione correctamente, necesita la vista del seleccion de juego y el
     * controlador padre. En el controlador además se lanza la vista, es decir,
     * se muestra la vista automaticamente.s
     *
     * @see GUISelectGame
     * @see MainController
     *
     * @param view la vista de seleccion {@link GUISelectGame}
     * @param parent el controlador padre de tipo {@link MainController}
     */
    public SelectGameController(GUISelectGame view, MainController parent) {
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
     */
    private void addListeners() {
        for (JButton option : view.getOptions()) {
            option.addActionListener(listenerButtons);
        }
        view.getExitButton().addActionListener(exitListener);
    }

    //GETTER/SETTER
    /**
     * getMainController: devuelve el controlador padre de tipo
     * {@link MainController}.
     *
     * @return el controlador tipo {@link MainController}
     */
    public MainController getMainController() {
        return (MainController) parent;
    }

    /**
     * getView: devuelve la vista de tipo {@link GUISelectGame}.
     * 
     * @return la vista tipo {@link GUISelectGame}
     */
    public GUISelectGame getView() {
        return view;
    }

    /**
     * launch: metodo que se implementa de {@link GameControllers}. En este
     * metodo se pone visible la ventana.
     */
    @Override
    public void launch() {
        view.setVisible(true);
    }

}
