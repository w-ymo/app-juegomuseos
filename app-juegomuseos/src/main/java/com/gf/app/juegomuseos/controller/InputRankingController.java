/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gf.app.juegomuseos.controller;

import com.gf.app.juegomuseos.dao.RankingDAO;
import com.gf.app.juegomuseos.models.Ranking;
import com.gf.app.juegomuseos.views.GUIInputRanking;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 * InputRankingController: es el controlador de la ventana
 * {@link GUIInputRanking}. Implementa de {@link GameControllers}. Se muestra
 * una ventana con la informacion del tiempo y una caja de texto donde insertar
 * tu nombre. Despues de pulsar el boton se aniadira a la base de datos.
 *
 * @author priparno
 * @author fercaslu
 */
public class InputRankingController implements GameControllers {

    /**
     * view: es la vista de la ventana.
     */
    private GUIInputRanking view;
    /**
     * parent: es el controlador padre, el que le llama.
     */
    private GameControllers parent;

    /**
     * rDAO: es la clase de acceso a los datos del ranking en la base de datos.
     *
     * @see RankingDAO
     */
    private RankingDAO rDAO = new RankingDAO();

    /**
     * listenerButtons: es el escuchador de accion del botón de confirmar datos.
     * Si pulsa el boton se aniadira a la base de datos tu puntuacion con tu
     * usuario.
     */
    private ActionListener listenerButtons = (e) -> {
        if (!view.getFieldName().getText().isEmpty()) {
            Ranking r = new Ranking();
            r.setNombre_usuario(view.getFieldName().getText());
            if (r.getNombre_usuario().length() < 50) {
                //pone el tiempo en String formateado
                r.setPuntuacion(((MainController) parent).getTimer().getFormattedTime());
                try {
                    if (rDAO.insert(r)) {
                        view.dispose();
                        openMenu();
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(view, "Error de sintaxis", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(view, "El nombre no puede superar los 50 caracteres", "Error", JOptionPane.ERROR_MESSAGE);
            }

        }
    };

    //CONSTRUCTOR
    /**
     * InputRankingController: es el constructor del controlador. Para que
     * funcione correctamente, necesita la vista del input para el ranking y el
     * controlador padre. En el controlador además se lanza la vista, es decir,
     * se muestra la vista automáticamente.
     *
     * @see GUIInputRanking
     * @see GameControllers
     * @see MainController
     *
     * @param view la vista del input para el ranking {@link GUIInputRanking}
     * @param parent el controlador padre de tipo {@link GameControllers}, en
     * este caso sera {@link MainController}
     */
    public InputRankingController(GUIInputRanking view, GameControllers parent) {
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
        view.getConfirmButton().addActionListener(listenerButtons);
    }

    /**
     * setData: este metodo muestra en las etiquetas de la ventana la
     * informacion de la partida. Esta informacion es el tiempo real, la suma al
     * tiempo por fallos y el tiempo total despues de la suma.
     */
    private void setData() {
        if (parent instanceof MainController parentC) {
            parentC.getTimer().stop();
            view.getRealTime().setText(parentC.getTimer().getFormattedTime());
            view.getPenalties().setText("+" + (parentC.getFails() * 5) + "s.");
            parentC.getTimer().setTime(parentC.getTimer().getTime() + (parentC.getFails() * 5));
            view.getTotalTime().setText("TOTAL: " + parentC.getTimer().getFormattedTime());
        }
    }

    /**
     * openMenu: muestra el menu inicial de la aplicacion.
     */
    private void openMenu() {
        if (parent instanceof MainController parentC) {
            parentC.getView().setVisible(true);
        }
    }

    /**
     * launch: metodo que se implementa de {@link GameControllers}. En este
     * metodo se lanza el juego y se pone visible la ventana.
     */
    @Override
    public void launch() {
        this.view.setVisible(true);
        setData();
    }

}
