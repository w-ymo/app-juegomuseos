/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.gf.app.juegomuseos.views;

import com.gf.app.juegomuseos.controller.MainController;
import com.gf.app.juegomuseos.utils.GameConstants;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 *
 * GUIInputRanking: vista del controlador {@link InputRankingController} donde
 * se muestra la informacion de la partida y los componentes para introducir al
 * ranking. 
 * 
 * @see InputRankingController
 *
 * @author fercaslu
 * @author priparno
 */
public class GUIInputRanking extends javax.swing.JFrame {

    /**
     * gameData: un panel para la informacion de la partida.
     */
    private JPanel gameData;
    /**
     * userData: un panel para la introduccion del usuario.
     */
    private JPanel userData;

    /**
     * realTime: una etiqueta que muestra el tiempo que se ha tardado.
     */
    private JLabel realTime;
    /**
     * penalties: una etiqueta que muestra el tiempo aniadido por
     * penalizaciones.
     *
     * @see MainController
     */
    private JLabel penalties;
    /**
     * totalTime: una etiqueta que muestra el total del tiempo real y el
     * aniadido.
     */
    private JLabel totalTime;

    /**
     * fieldName: una caja de texto para introducir el nombre del usuario.
     */
    private JTextField fieldName;
    /**
     * confirmButton: un boton que permite guardar el ranking en la base de
     * datos.
     */
    private JButton confirmButton;

    /**
     * Creates new form GUIRanking
     */
    public GUIInputRanking() {
        initComponents();
        setFrame();
    }

    /**
     * setFrame: es el metodo principal que coloca en la vista la informacion de
     * la partida y para introducir la informacion del usuario.
     */
    private void setFrame() {
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.getContentPane().setLayout(new BorderLayout());
        setGameData();
        setUserData();
    }

    /**
     * setGameData: crea y coloca en la vista las etiquetas para mostrar los
     * tiempos.
     */
    private void setGameData() {
        gameData = new JPanel(new BorderLayout());
        JPanel extra = new JPanel(new GridLayout(0, 3));
        gameData.setPreferredSize(new Dimension(GameConstants.SCREEN_SIZE.width, (int) (GameConstants.SCREEN_SIZE.height * 0.4)));
        realTime = new JLabel();
        realTime.setFont(this.getFont().deriveFont(Font.BOLD, 25f));
        realTime.setHorizontalAlignment(SwingConstants.CENTER);
        penalties = new JLabel();
        penalties.setFont(this.getFont().deriveFont(Font.BOLD, 25f));
        penalties.setHorizontalAlignment(SwingConstants.CENTER);
        totalTime = new JLabel();
        totalTime.setFont(this.getFont().deriveFont(Font.BOLD, 25f));
        totalTime.setHorizontalAlignment(SwingConstants.CENTER);
        extra.add(realTime);
        extra.add(penalties);
        extra.add(totalTime);
        gameData.add(extra);
        this.getContentPane().add(gameData, BorderLayout.NORTH);
    }

    /**
     * setUserData: crea y coloca en la vista los componentes para introducir el
     * nombre de usuario e insertar.
     */
    private void setUserData() {
        userData = new JPanel(new FlowLayout(FlowLayout.CENTER));
        userData.setPreferredSize(new Dimension(GameConstants.SCREEN_SIZE.width, (int) (GameConstants.SCREEN_SIZE.height * 0.4)));
        fieldName = new JTextField();
        fieldName.setPreferredSize(new Dimension(400, 40));
        confirmButton = new JButton("Confirmar");
        userData.add(fieldName);
        userData.add(confirmButton);
        this.getContentPane().add(userData, BorderLayout.CENTER);
    }

    //GETTER/SETTER
    /**
     * getRealTime: devuelve un {@link JLabel} que tendra el tiempo real.
     *
     * @return un {@link JLabel}
     */
    public JLabel getRealTime() {
        return realTime;
    }

    /**
     * getPenalties: devuelve un {@link JLabel} que tendra el tiempo a aniadir.
     *
     * @return un {@link JLabel}
     */
    public JLabel getPenalties() {
        return penalties;
    }

    /**
     * getTotalTime: devuelve un {@link JLabel} que tendra el tiempo total.
     *
     * @return un {@link JLabel}
     */
    public JLabel getTotalTime() {
        return totalTime;
    }

    /**
     * getFieldName: devuelve un {@link JTextField} que contendra el nombre de
     * usuario introducido por el jugador.
     *
     * @return un {@link JTextField}
     */
    public JTextField getFieldName() {
        return fieldName;
    }

    /**
     * getConfirmButton: devuelve un {@link JButton} para confirmar la
     * insercion.
     *
     * @return un {@link JButton}
     */
    public JButton getConfirmButton() {
        return confirmButton;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
