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

    private GUIWhoIs view;

    private ArtworkDAO awDAO = new ArtworkDAO();
    private AuthorDAO atDAO = new AuthorDAO();

    private int counter;

    private Artwork imageSelected;
    private Author solution;

    public WhoIsController(GUIWhoIs view) {
        this.view = view;
        addListenerButtons();
        launchGame();
    }

    private ActionListener listenerButtons = (e) -> {
        JButton but = (JButton) e.getSource();
        int awId = view.getSolution().getId_obra();
        try {
            Artwork aw = awDAO.selectId(awId);
            Author at = atDAO.selectId(aw.getId_autor());
            if (but.getText().equals(at.getNombre_autor())) {
                view.setCorrect(view.getCorrect() + 1);
            } else {
                JOptionPane.showMessageDialog(null, "La solucion era " + at.getNombre_autor());
            }
            counter++;
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
        //while (contador < 5)
        initGame();
    }

    private void initGame() {
        try {
            imageSelected = awDAO.selectNum(1).get(0);
            solution = atDAO.selectId(imageSelected.getId_autor());
            List<Author> authorsNames = new ArrayList<>();
            authorsNames.add(solution);
            authorsNames.addAll(atDAO.selectNotEquals(imageSelected.getId_autor(), 3));
            //comprobar que no se repite
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
            i = new ImageIcon(new URL(select));
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
