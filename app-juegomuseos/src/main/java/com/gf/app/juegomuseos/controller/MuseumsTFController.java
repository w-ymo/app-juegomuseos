/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gf.app.juegomuseos.controller;

import com.gf.app.juegomuseos.dao.MuseumDAO;
import com.gf.app.juegomuseos.models.Museum;
import com.gf.app.juegomuseos.utils.Colors;
import com.gf.app.juegomuseos.views.GUIMuseumsTF;
import com.gf.app.juegomuseos.views.ResultDialog;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Random;
import javax.swing.JButton;

/**
 *
 * @author fercaslu
 */
public class MuseumsTFController implements ActionListener{

    GUIMuseumsTF view;
    Random rndm = new Random();
    MuseumDAO mDAO = new MuseumDAO();
    
    private int counter;
    private int fails;
    private boolean proceed = true;
            
    private boolean accessType; //true -> recoge de BD, false -> recoge de array
    private Museum mSolution;
    
    private static final String[] FALSE_MUSEUMS = {"Museo de las Pastillas", 
        "Museo de los Ladrillos", "Museo de las Vibraciones", "Museo Antropológico de Murcia",
        "Museo de la Etnomusicología 'Don Rodrigo Díaz de Carreras'", "Pinacoteca de Liberty City",
        "Galería de Arte de Central City", "Museo de Automoción Francesco Virgolini", 
        "Museo de Escultura Samuel de Luque", "Museo de las Patas de Mueble"};
    
    public MuseumsTFController(GUIMuseumsTF view){
        this.view = view;
        this.counter = 0;
        this.fails = 0;
        addListenerButtons();
        launchGame();
    }
    
    private void initGame() {
        accessType = rndm.nextBoolean();
        proceed = false;
        mSolution = new Museum();
        if (accessType) {
            try {
                mSolution = mDAO.selectNum(1).get(0);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } else {
            mSolution.setNombre_museo(FALSE_MUSEUMS[rndm.nextInt(0, FALSE_MUSEUMS.length)]);
        }
        view.getMuseumLabel().setText(mSolution.getNombre_museo()); 
    }
    
    private void launchGame() {
        view.setVisible(true);
        while (counter < 10) {
            if (proceed) {
                initGame();
            } 
        }
    }
    
    private void addListenerButtons(){
        view.getTrueButton().addActionListener(this);
        view.getFalseButton().addActionListener(this);
    }
    
    private void guessedRight(JButton button) {
        try {
            Color prevColor = button.getBackground();
            
            button.setBackground(Color.GREEN);
            Thread.sleep(2000);
            button.setBackground(prevColor);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
    
    private void guessedWrong(JButton button) {
        try {
            Color prevColor = button.getBackground();
            JButton correctButton = null;
            
            if (button.equals(view.getTrueButton())) {
                correctButton = view.getFalseButton();       
            } else if (button.equals(view.getFalseButton())) {
                correctButton = view.getTrueButton();       
            }
            
            correctButton.setBackground(Color.GREEN);
            button.setBackground(Color.RED);
            Thread.sleep(2000);
            button.setBackground(prevColor);
            correctButton.setBackground(prevColor);
            
            fails++;
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        ResultDialog rd = new ResultDialog(view, true, true);
        rd.setVisible(true);
        JButton b = (JButton) e.getSource();
        boolean gtOne = mSolution.getId_museo() >= 1;
        if (b.equals(view.getTrueButton())) {
            if (gtOne) {
                guessedRight(b);
            } else {
                guessedWrong(b);
            }
        } else {
            if (!gtOne) {
                guessedRight(b);
            } else {
                guessedWrong(b);
            }
        }
        proceed = true;
        counter++;
    }
    
}
