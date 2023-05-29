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
import java.awt.Image;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
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
    
    private int counter;
    private int fails;
    
    private Artwork solution;
    
    public GregFernandezController(GUIGregorioFernandez view) {
        this.view = view;
        this.counter = 0;
        this.fails = 0;
        addListenerButtons();
        launchGame();
    }
    
    private ActionListener listenerButtons = (e) -> {
        JButton but = (JButton) e.getSource();
        try {
            if (but.getName().equals(atDAO.getIdGregorioFernandez())) {
                JOptionPane.showMessageDialog(null, "Todo gucci");
            } else {
                JOptionPane.showMessageDialog(null, "La solucion era la otra");
                fails++;
            }
            counter++;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "error garrafal");
        }
    };
    
    private void addListenerButtons() {
        for (JButton option : view.getImages()) {
            option.addActionListener(listenerButtons);
        }
    }
    
    private void launchGame() {
        view.setVisible(true);
        while (counter < 5) {
            initGame();
        }
    }
    
    private void initGame() {
        try {
            solution = awDAO.selectNum(1).get(0);
            List<Artwork> artworksNames = new ArrayList<>();
            artworksNames.add(solution);
            //el campo clave sera el de la solucion
            List<Artwork> fakeArtwork = awDAO.selectSimilar(solution.getClave_obra(), atDAO.getIdGregorioFernandez());
            Collections.shuffle(fakeArtwork);
            artworksNames.add(fakeArtwork.get(0));
            Collections.shuffle(artworksNames);
            for (int i = 0; i < view.getImages().size(); i++) {
                setIcon(artworksNames.get(0).getImagen_obra(), view.getImages().get(i));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la base de datos");
        }
    }
    
    public void setIcon(String url, JButton image) {
        ImageIcon i = null;
        try {
            i = new ImageIcon(new URL(url));
        } catch (MalformedURLException ex) {
            Logger.getLogger(GUIGregorioFernandez.class.getName()).log(Level.SEVERE, null, ex);
        }
        Image proportionalImage = i.getImage().getScaledInstance(view.getPanelImages().getWidth() / 2 - 200,
                view.getPanelImages().getHeight(), Image.SCALE_AREA_AVERAGING);
        image.setIcon(new ImageIcon(proportionalImage));
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
