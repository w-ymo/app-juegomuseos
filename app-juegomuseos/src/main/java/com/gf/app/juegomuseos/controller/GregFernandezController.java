/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gf.app.juegomuseos.controller;

import com.gf.app.juegomuseos.dao.ArtworkDAO;
import com.gf.app.juegomuseos.dao.AuthorDAO;
import com.gf.app.juegomuseos.models.Artwork;
import com.gf.app.juegomuseos.utils.GameConstants;
import com.gf.app.juegomuseos.utils.ImagesSize;
import com.gf.app.juegomuseos.views.GUIGregorioFernandez;
import com.gf.app.juegomuseos.views.GUIMuseumsTF;
import com.gf.app.juegomuseos.views.ResultDialog;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.Timer;

/**
 *
 * @author priparno
 */
public class GregFernandezController implements GameControllers {

    private GUIGregorioFernandez view;
    private GameControllers parent;

    private ArtworkDAO awDAO = new ArtworkDAO();
    private AuthorDAO atDAO = new AuthorDAO();

    private boolean mode;

    private int counter;
    private int fails;
    private Timer timer;

    private Artwork solution;

    public GregFernandezController(GUIGregorioFernandez view, GameControllers parent, boolean mode) {
        this.view = view;
        this.parent = parent;
        this.mode = mode;
        this.counter = 0;
        getGameData();
        addListenerButtons();
        launchGame();
    }

    private ActionListener listenerButtons = (e) -> {
        JButton but = (JButton) e.getSource();
        try {
            if (but.getName().equals(String.valueOf(atDAO.getIdGregorioFernandez()))) {
                ResultDialog rd = new ResultDialog(view, true);
                rd.initTimer();
                rd.setVisible(true);
            } else {
                ResultDialog rd = new ResultDialog(view, false);
                rd.initTimer();
                rd.setVisible(true);
                fails++;
            }
            counter++;
            if (counter < 5) {
                initGame();
            } else {
                JOptionPane.showMessageDialog(null, "Siguiente jogo");
                setGameData();
                if (mode == GameConstants.COMP_MODE) {
                    //map controller
                    System.out.println("map");
                } else {
                    openMenu();
                }
                view.dispose();
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "error garrafal");
        }
    };

    private void openMenu() {
        if (parent instanceof SelectGameController parentC) {
            parentC.getMainController().getView().setVisible(true);
        }
    }

    private void getGameData() {
        if (parent instanceof MainController parentC) {
            this.fails = parentC.getFails();
            this.timer = parentC.getTimer();
        }
    }

    private void setGameData() {
        if (parent instanceof MainController parentC) {
            parentC.setFails(fails);
        }
    }

    private void addListenerButtons() {
        for (JButton option : view.getImages()) {
            option.addActionListener(listenerButtons);
        }
    }

    private void launchGame() {
        view.setVisible(true);
        initGame();
    }

    private void initGame() {
        try {
            List<Artwork> gregorioArtwork = awDAO.selectIdAuthor(atDAO.getIdGregorioFernandez());
            Collections.shuffle(gregorioArtwork);
            solution = gregorioArtwork.get(0);
            List<Artwork> artworksNames = new ArrayList<>();
            artworksNames.add(solution);
            //el campo clave sera el de la solucion
            List<Artwork> fakeArtwork = awDAO.selectSimilar(solution.getClave_obra(), atDAO.getIdGregorioFernandez());
            Collections.shuffle(fakeArtwork);
            artworksNames.add(fakeArtwork.get(0));
            Collections.shuffle(artworksNames);
            for (int i = 0; i < view.getImages().size(); i++) {
                setIcon(artworksNames.get(i).getImagen_obra(), view.getImages().get(i));
                view.getImages().get(i).setName(String.valueOf(artworksNames.get(i).getId_autor()));
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
            JOptionPane.showMessageDialog(null, "URL MALISIMA");
        }
        Image proportionalImage = ImagesSize.getProportionalDimensionImage(i,
                new Dimension(view.getPanelImages().getSize().width / 2, view.getPanelImages().getSize().height));
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
