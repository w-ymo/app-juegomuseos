/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gf.app.juegomuseos.controller;

import com.gf.app.juegomuseos.dao.ArtworkDAO;
import com.gf.app.juegomuseos.dao.AuthorDAO;
import com.gf.app.juegomuseos.models.Artwork;
import com.gf.app.juegomuseos.models.Author;
import com.gf.app.juegomuseos.utils.GameConstants;
import com.gf.app.juegomuseos.utils.ImagesSize;
import com.gf.app.juegomuseos.views.GUIGregorioFernandez;
import com.gf.app.juegomuseos.views.GUIMuseumsTF;
import com.gf.app.juegomuseos.views.GUIPrincipal;
import com.gf.app.juegomuseos.views.GUIWhoIs;
import com.gf.app.juegomuseos.views.ResultDialog;
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
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.Timer;

/**
 *
 * @author priparno
 */
public class WhoIsController implements GameControllers {

    private GUIWhoIs view;
    private GameControllers parent;

    private ArtworkDAO awDAO = new ArtworkDAO();
    private AuthorDAO atDAO = new AuthorDAO();

    private int counter;
    private int fails;
    private Timer timer;
    private boolean mode;

    private Artwork imageSelected;
    private Author solution;

    private List<Integer> repeatedDB = new ArrayList<>();

    private ActionListener listenerButtons = (e) -> {
        JButton but = (JButton) e.getSource();
        int atId = solution.getId_autor();
        try {
            Author at = atDAO.selectId(atId);
            if (but.getText().equals(at.getNombre_autor())) {
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
            if (counter < 10) {
                initGame();
            } else {
                setGameData();
                if (mode == GameConstants.COMP_MODE) {
                    MuseumsTFController nextGame = new MuseumsTFController(new GUIMuseumsTF(),GameConstants.COMP_MODE);
                }else{
                    openMenu();
                }
                view.dispose();
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "error garrafal");
        }
    };

    public WhoIsController(GUIWhoIs view, GameControllers parent, boolean mode) {
        this.view = view;
        this.parent = parent;
        this.mode = mode;
        this.counter = 0;
        getGameData();
        addListenerButtons();
        launchGame();
    }

    private void openMenu(){
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
        for (JButton option : view.getOptions()) {
            option.addActionListener(listenerButtons);
        }
    }

    private void launchGame() {
        view.setVisible(true);
        initGame();
    }

    private void initGame() {
        try {
            int index;
            if (!this.repeatedDB.isEmpty()) {
                do {
                    imageSelected = awDAO.selectNum(1).get(0);
                    index = imageSelected.getId_obra();
                    if (Collections.binarySearch(repeatedDB, index) < 0) {
                        repeatedDB.add(index);
                    }
                } while (Collections.binarySearch(repeatedDB, index) >= 0);
            } else {
                imageSelected = awDAO.selectNum(1).get(0);
            }
            view.getImageText().setText(imageSelected.getNombre_obra());
            solution = atDAO.selectId(imageSelected.getId_autor());
            List<Author> authorsNames = new ArrayList<>();
            authorsNames.add(solution);
            //distinto de la solucion coge 3 mas
            authorsNames.addAll(atDAO.selectNotEquals(solution.getId_autor(), 3));
            //lo desordena
            Collections.shuffle(authorsNames);
            for (int i = 0; i < view.getOptions().size(); i++) {
                view.getOptions().get(i).setText(authorsNames.get(i).getNombre_autor());
            }
            setIcon();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la base de datos");
            counter = 15;
        }
        view.repaint();
    }

    public void setIcon() {
        //aniadir la imagen
        ImageIcon i = null;
        try {
            //poner url de imageSelected
            i = new ImageIcon(new URL(imageSelected.getImagen_obra()));
        } catch (MalformedURLException ex) {
            Logger.getLogger(GUIGregorioFernandez.class.getName()).log(Level.SEVERE, null, ex);
        }
        Image proportionalImage = ImagesSize.getProportionalDimensionImage(i, view.getPanelImages().getSize());
        view.getImage().setIcon(new ImageIcon(proportionalImage));
    }

    //se me ocurre hacer un bucle (hay que meter un contador y lo de los errores)
    //que lo haga 5 veces
    //y coja 4 obras al puto azar
    //que una de ellas la ponga como imagen
    //y pista (poner el texto en botones)
    //o si hacemos de adivinar autor (me parece más qrious)
    //sacar el autor, añadir otros 3 random y hacer shuffle de la lista de 
    //botones (que ya tienen el nombre de los autores)
}
