/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gf.app.juegomuseos.controller;

import com.gf.app.juegomuseos.utils.ConnectionDB;
import com.gf.app.juegomuseos.utils.Crono;
import com.gf.app.juegomuseos.utils.GameConstants;
import com.gf.app.juegomuseos.utils.GameData;
import com.gf.app.juegomuseos.utils.Music;
import com.gf.app.juegomuseos.views.GUIInfo;
import com.gf.app.juegomuseos.views.GUIPrincipal;
import com.gf.app.juegomuseos.views.GUIRanking;
import com.gf.app.juegomuseos.views.GUISelectGame;
import com.gf.app.juegomuseos.views.GUISettings;
import com.gf.app.juegomuseos.views.GUIWhoIs;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JOptionPane;

/**
 * MainController: es el controlador que contiene el menu principal, de la
 * ventana {@link GUIPrincipal}. Desde esta clase se lanzan los modos de juego,
 * el ranking, la informacion y los ajustes. Implementa de
 * {@link GameControllers}.
 *
 * @author priparno
 * @author fercaslu
 */
public class MainController implements GameControllers {

    /**
     * view: es la vista del menu.
     */
    private GUIPrincipal view;

    /**
     * fails: es el contador de fallos que posteriormente se aniadira al tiempo.
     */
    private int fails = 0;
    /**
     * timer: de tipo {@link Crono}, es el cronometro que nos marcará el tiempo.
     */
    private Crono timer;

    /**
     * listenerButtons: es el escuchador de accion de los botones. Dependiendo
     * del texto del boton se lanzara una vista u otra.
     * <ul>
     * <li>MODO YINCANA: es el modo competitivo de juego. Se muestran todos los
     * juegos seguidos, se cuentan los fallos y el tiempo.</li>
     * <li>MODO LIBRE: es el modo libre de juego. Lanza
     * {@link SelectGameController} con la vista {@link GUISelectGame}.</li>
     * <li>RANKING: muestra el ranking de juego. Lanza {@link RankingController}
     * con la vista {@link GUIRanking}.</li>
     * <li>INFORMACION: muestra la informacion del juego. Lanza
     * {@link InfoController} con la vista {@link GUIInfo}.</li>
     * <li>AJUSTES: muestra los ajustes del juego. Lanza
     * {@link SettingsController} con la vista {@link GUISettings}</li>
     * <li>SALIR: cierra la ventana y termina la aplicacion.</li>
     * </ul>
     */
    private ActionListener listenerButtons = (e) -> {
        List<JButton> list = view.getOptions();
        JButton selected = (JButton) e.getSource();
        switch (selected.getText()) {
            case "MODO YINCANA" -> {
                timer = new Crono();
                GUIWhoIs guiwh = new GUIWhoIs();
                WhoIsController controllerWH = new WhoIsController(guiwh, this, GameConstants.COMP_MODE);
            }
            case "MODO LIBRE" -> {
                GUISelectGame guisg = new GUISelectGame();
                SelectGameController controllerSelectGame = new SelectGameController(guisg, this);
            }
            case "RANKING" -> {
                RankingController controllerRanking = new RankingController(new GUIRanking(), this);
            }
            case "INFORMACION" -> {
                InfoController controllerInfo = new InfoController(new GUIInfo(view, true));
            }
            case "AJUSTES" -> {
                SettingsController controllerSettings = new SettingsController(new GUISettings(view, true), this);
            }
            case "SALIR" -> {
                Music.stop();
                view.dispose();
            }
            default ->
                JOptionPane.showMessageDialog(view, "Error de acceso.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    };

    /**
     * MainController: es el constructor del controlador. Para que funcione
     * correctamente, necesita la vista principal. En el controlador además se
     * lanza la vista, es decir, se muestra la vista automaticamente.
     *
     * @see GUIPrincipal
     *
     * @param view la vista del menu principal {@link GUIPrincipal}
     */
    public MainController(GUIPrincipal view) {
        this.view = view;
        addListeners();
        if (GameData.isValid() && Music.isValid() && ConnectionDB.isValid()) {
            launch();
            Music.start();
        } else {
            System.exit(0);
        }
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
    }

    //GETTER/SETTER
    /**
     * getTimer: devuelve el cronometro en el modo competitivo.
     *
     * @see Crono
     *
     * @return timer de tipo {@link Crono} que representa el cronometro
     */
    public Crono getTimer() {
        return timer;
    }

    /**
     * setTimer: actualiza {@link #timer} al pasado como parametro.
     *
     * @see Crono
     *
     * @param timer el cronometro {@link Crono}
     */
    public void setTimer(Crono timer) {
        this.timer = timer;
    }

    /**
     * getFails: devuelve el numero de fallos.
     *
     * @return un entero que representa el numero de fallos
     */
    public int getFails() {
        return fails;
    }

    /**
     * setFails: actualiza el valor de los fallos.
     *
     * @param fails entero que representa el numero de fallos
     */
    public void setFails(int fails) {
        this.fails = fails;
    }

    /**
     * getView: devuelve la vista de tipo {@link GUIPrincipal}.
     *
     * @return una vista de tipo {@link GUIPrincipal}
     */
    public GUIPrincipal getView() {
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
