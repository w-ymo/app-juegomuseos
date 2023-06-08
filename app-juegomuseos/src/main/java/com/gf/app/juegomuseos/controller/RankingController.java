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
 *
 * @author noelp
 */
public class RankingController implements GameControllers {

    private GUIRanking view;
    private MainController parent;

    private RankingDAO rDAO = new RankingDAO();

    public RankingController(GUIRanking view, MainController parent) {
        this.view = view;
        this.parent = parent;
        addListeners();
        launch();
        parent.getView().dispose();
    }

    private ActionListener al = (e) -> {
        view.dispose();
        parent.getView().setVisible(true);
    };

    public void addListeners() {
        view.getExitButton().addActionListener(al);
    }

    @Override
    public void launch() {
        view.setVisible(true);
        addData();
    }

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

}
