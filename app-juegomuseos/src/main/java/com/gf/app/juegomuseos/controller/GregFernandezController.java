/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gf.app.juegomuseos.controller;

import com.gf.app.juegomuseos.dao.ArtworkDAO;
import com.gf.app.juegomuseos.dao.AuthorDAO;
import com.gf.app.juegomuseos.models.Artwork;
import com.gf.app.juegomuseos.models.Author;
import com.gf.app.juegomuseos.views.GUIGregorioFernandez;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JOptionPane;

/**
 *
 * @author priparno
 */
public class GregFernandezController {

    private GUIGregorioFernandez view;

    private ArtworkDAO awDAO = new ArtworkDAO();
    private AuthorDAO atDAO = new AuthorDAO();

    public GregFernandezController(GUIGregorioFernandez view) {
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
            if ("Gregorio Fernandez".equals(at.getNombre_autor())) {
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
        for (JButton option : view.getImages()) {
            option.addActionListener(listenerButtons);
        }
    }
    
    private void launchGame(){
        view.setVisible(true);
        //while (contador < 5)
        view.setIcon();
    }
    
    
    //setIcons (busco una imagen y luego cojo otra random (una de gregorio y otra no))
    //se me ocurre coger todas las imagenes de gregorio fernandez
    //caparlo a 5 porque asi salen a veces distintas
    //pillar una random de la tabla para poner al otro lado (no hace falta que sean similares)
    //o
    //pillar por nombre, si coge rollo una piedad random, que busque en las obras una que ponga
    //piedad para que sea similar
    //o
    //random tambien, pero que pille las obras que sean escultura y que no sean de gregorio fernandez

}
