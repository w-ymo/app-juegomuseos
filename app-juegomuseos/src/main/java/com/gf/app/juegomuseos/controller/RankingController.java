/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gf.app.juegomuseos.controller;

import com.gf.app.juegomuseos.dao.RankingDAO;
import com.gf.app.juegomuseos.models.Ranking;
import com.gf.app.juegomuseos.views.GUIRanking;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * RankingController: es el controlador de la ventana {@link GUIRanking}. Desde
 * este se lanza una vista con una tabla que contiene el ranking almacenado en
 * la base de datos. Muestra los usuarios por orden de tiempo ascendente.
 * Implementa de {@link GameControllers}.
 *
 * @author priparno
 * @author fercaslu
 */
public class RankingController implements GameControllers {

    /**
     * view: es la vista de la ventana.
     */
    private GUIRanking view;
    /**
     * parent: es el controlador padre, el que le llama. En este caso
     * {@link MainController}.
     */
    private MainController parent;

    /**
     * rDAO: es la clase de acceso a los datos del ranking en la base de datos.
     *
     * @see RankingDAO
     */
    private RankingDAO rDAO = new RankingDAO();

    /**
     * listenerButtons: es el escuchador de accion del botón de vuelta. Si se
     * activa muestra el menu principal.
     */
    private ActionListener listenerButtons = (e) -> {
        view.dispose();
        parent.getView().setVisible(true);
    };

    //CONSTRUCTOR
    /**
     * RankingController: es el constructor del controlador. Para que funcione
     * correctamente, necesita la vista del ranking y el controlador padre. En
     * el controlador además se lanza la vista, es decir, se muestra la vista
     * automaticamente.
     *
     * @see GUIRanking
     * @see MainController
     *
     * @param view la vista del ranking {@link GUIRanking}
     * @param parent el controlador padre de tipo {@link MainController}
     */
    public RankingController(GUIRanking view, MainController parent) {
        this.view = view;
        this.parent = parent;
        addListeners();
        launch();
        parent.getView().dispose();
    }

    /**
     * addListeners: aniade a los elementos de la ventana los escuchadores para
     * manejar las acciones del usuario.
     *
     * @see ActionListener
     */
    public void addListeners() {
        view.getExitButton().addActionListener(listenerButtons);
    }

    /**
     * addData: muestra en la tabla la informacion obtenida de la base de datos.
     */
    private void addData() {
        try {
            List<Ranking> list = rDAO.selectAll();
            Collections.sort(list);
            for (Ranking ranking : list) {
                view.getModel().addRow(new String[]{ranking.getNombre_usuario(), ranking.getPuntuacion()});
            }
        } catch (SQLException ex) {
            Logger.getLogger(RankingController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * launch: metodo que se implementa de {@link GameControllers}. En este
     * metodo se lanza el juego y se pone visible la ventana.
     */
    @Override
    public void launch() {
        view.setVisible(true);
        addData();
    }

}
