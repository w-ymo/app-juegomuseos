/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gf.app.juegomuseos.controller;

import com.gf.app.juegomuseos.views.GUIInfo;

/**
 * InfoController: es el controlador de la ventana {@link GUIInfo}. Desde esta
 * se muestra una ventana emergente que muestra el contenido del archivo
 * AppInfo.html conservando su formato html. Implementa de
 * {@link GameControllers}
 *
 * @author priparno
 * @author fercaslu
 */
public class InfoController implements GameControllers {

    /**
     * view: es la vista de la ventana.
     */
    private GUIInfo view;

    //CONSTRUCTOR
    /**
     * InfoController: es el constructor del controlador. Para que funcione
     * correctamente, necesota la vista de la informacion. En el controlador
     * además se lanza la vista, es decir, se musestra la vista automaticamente.
     *
     * @see GUIInfo
     *
     * @param view la vista de la informacion {@link GUIInfo}
     */
    public InfoController(GUIInfo view) {
        this.view = view;
        launch();
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
