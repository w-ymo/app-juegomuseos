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
    
    private Artwork solution;
    
    public GregFernandezController(GUIGregorioFernandez view) {
        this.view = view;
        addListenerButtons();
        launchGame();
    }

    private ActionListener listenerButtons = (e) -> {
        JButton but = (JButton) e.getSource();
        int aw_id = solution.getId_obra();
        try {
            Artwork aw = awDAO.selectId(aw_id);
            Author at = atDAO.selectId(aw.getId_autor());
            if ("Gregorio Fernandez".equals(at.getNombre_autor())) {
                //de puta madre continuas
            } else {
                //la respuesta correcta es
                JOptionPane.showMessageDialog(null, "La solucion era " + at.getNombre_autor());
            }
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
        //while (contador < 5)
        initGame();
    }

    private void initGame() {
        try {
            solution = awDAO.selectNum(1).get(0);
            List<Artwork> artworksNames = new ArrayList<>();
            artworksNames.add(solution);
            List<Artwork> fakeArtwork = awDAO.selectSimilar("campo clave");
            Collections.shuffle(fakeArtwork);
            artworksNames.add(fakeArtwork.get(1));
            Collections.shuffle(artworksNames);
            for (int i = 0; i < view.getImages().size(); i++) {
                view.getImages().get(i).setText(artworksNames.get(i).getNombre_obra()); //susttituir id_obra por URL
            }
            setIcon();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la base de datos");
        }
    }

    public void setIcon() {
        for (JButton image : view.getImages()) {
            ImageIcon i = null;
            try {
                i = new ImageIcon(new URL("https://picsum.photos/700/900"));
            } catch (MalformedURLException ex) {
                Logger.getLogger(GUIGregorioFernandez.class.getName()).log(Level.SEVERE, null, ex);
            }
            Image proportionalImage = i.getImage().getScaledInstance(view.getPanelImages().getWidth() / 2 - 200,
                    view.getPanelImages().getHeight(), Image.SCALE_AREA_AVERAGING);
            image.setIcon(new ImageIcon(proportionalImage));
        }
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
