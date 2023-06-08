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

/**
 *
 * @author priparno
 */
public class RankingController {

    private GUIRanking view;
    private GameControllers parent;

    private RankingDAO rDAO = new RankingDAO();

    private ActionListener al = (e) -> {
        if (!view.getFieldName().getText().isEmpty()) {
            Ranking r = new Ranking();
            r.setNombre_usuario(view.getFieldName().getText());
            r.setPuntuacion(((MainController) parent).getTimer().getFormattedTime());
            try {
                if (rDAO.insert(r)) {
                    view.dispose();
                    openMenu();
                }
            } catch (SQLException ex) {
                System.err.println("Error al acceder a la base de datos.");
            }
        }
    };

    public RankingController(GUIRanking view, GameControllers parent) {
        this.view = view;
        addActionListener();
        launchView();
    }

    private void addActionListener() {
        view.getConfirmButton().addActionListener(al);
    }

    private void launchView() {
        this.view.setVisible(true);
        setData();
    }

    private void setData() {
        if (parent instanceof MainController parentC) {
            view.getRealTime().setText(parentC.getTimer().getFormattedTime());
            view.getPenalties().setText("+" + parentC.getFails() + "s.");
            parentC.getTimer().setTime(parentC.getTimer().getTime() + parentC.getFails());
            view.getTotalTime().setText("TOTAL: " + parentC.getTimer().getFormattedTime());
        }
    }

    private void openMenu() {
        if (parent instanceof MainController parentC) {
            parentC.getView().setVisible(true);
        }
    }

}
