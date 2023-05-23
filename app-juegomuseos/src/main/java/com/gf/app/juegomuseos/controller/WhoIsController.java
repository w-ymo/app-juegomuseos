/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gf.app.juegomuseos.controller;

import com.gf.app.juegomuseos.dao.ArtworkDAO;
import com.gf.app.juegomuseos.dao.AuthorDAO;
import com.gf.app.juegomuseos.models.Artwork;
import com.gf.app.juegomuseos.models.Author;
import com.gf.app.juegomuseos.views.GUIWhoIs;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JOptionPane;

/**
 *
 * @author priparno
 */
public class WhoIsController {

    private GUIWhoIs view;

    private ArtworkDAO awDAO = new ArtworkDAO();
    private AuthorDAO atDAO = new AuthorDAO();

    public WhoIsController(GUIWhoIs view) {
        this.view = view;
        addListenerButtons();
        launchGame();
    }
    
    private ActionListener listenerButtons = (e) -> {
        JButton but = (JButton) e.getSource();
        int aw_id = view.getSolution().getId_obra();
        try {
            Artwork aw = awDAO.selectId(aw_id);
            Author at = atDAO.selectId(aw.getId_autor());
            if (but.getText().equals(at.getNombre_autor())) {
                view.setCorrect(view.getCorrect() + 1);
            } else {
                //la respuesta correcta es
                JOptionPane.showMessageDialog(null, "La solucion era " + at.getNombre_autor());
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "error garrafal");
        }
    };

    private void addListenerButtons(){
        for (JButton option : view.getOptions()) {
            option.addActionListener(listenerButtons);
        }
    }
    
    private void launchGame(){
        view.setVisible(true);
        //while (contador < 5)
        view.setIcon();
    }
    
    //lanzar juego
    
}