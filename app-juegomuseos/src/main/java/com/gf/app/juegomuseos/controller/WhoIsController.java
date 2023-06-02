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
import com.gf.app.juegomuseos.views.GUIWhoIs;
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
public class WhoIsController {

    //habria que meter en el controlador la pagina principal
    private GUIWhoIs view;

    private ArtworkDAO awDAO = new ArtworkDAO();
    private AuthorDAO atDAO = new AuthorDAO();

    private int counter;
    private int fails;

    private boolean proceed = true;

    private Artwork imageSelected;
    private Author solution;

    public WhoIsController(GUIWhoIs view) {
        this.view = view;
        this.counter = 0;
        this.fails = 0;
        addListenerButtons();
        launchGame();
    }

    private ActionListener listenerButtons = (e) -> {
        JButton but = (JButton) e.getSource();
        int atId = solution.getId_autor();
        try {
            Author at = atDAO.selectId(atId);
            if (but.getText().equals(at.getNombre_autor())) {
                JOptionPane.showMessageDialog(null, "La solución es correcta");
            } else {
                JOptionPane.showMessageDialog(null, "La solucion era " + at.getNombre_autor());
                fails++;
            }
            counter++;
            proceed = true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "error garrafal");
        }
    };

    private void addListenerButtons() {
        for (JButton option : view.getOptions()) {
            option.addActionListener(listenerButtons);
        }
    }

    private void launchGame() {
        view.setVisible(true);
        while (counter < 10) {
            if (proceed) {
                initGame();
            }
        }
        JOptionPane.showMessageDialog(null, "Siguiente jogo");
    }

    private void initGame() {
        try {
            proceed = false;
            imageSelected = awDAO.selectNum(1).get(0);
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
        }
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
        Image proportionalImage = i.getImage().getScaledInstance(view.getPanelImages().getWidth() - 400,
                view.getPanelImages().getHeight(), Image.SCALE_SMOOTH);
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
