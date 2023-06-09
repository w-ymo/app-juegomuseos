/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package com.gf.app.juegomuseos.views;

import com.gf.app.juegomuseos.utils.Colors;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * GUIMuseumsTF: vista del controlador {@link MuseumsTFController}
 * donde se muestra un museo y dos botones. El objetivo es acertar que museo
 * existe y cual no existe.
 *
 * @see MuseumsTFController
 * 
 * @author fercaslu
 */
public class GUIMuseumsTF extends javax.swing.JFrame {

    /**
     * museumPanel: panel que contiene la etiqueta con el nombre del museo.
     */
    private JPanel museumPanel;
    
    /**
     * buttonsPanel: panel que contiene los dos botones con "Existe" y "No Existe"
     */
    private JPanel buttonsPanel;

    /**
     * museumLabel: es la {@link JLabel} que contendra el nombre del museo.
     */
    private JLabel museumLabel;

    /**
     * existButton: es el {@link JButton} que sacara que el museo existe.
     */
    private JButton existButton;
    
    /**
     * notExistButton: es el {@link JButton} que sacara que el museo no existe.
     */
    private JButton notExistButton;

    /**
     * textTime: es la {@link JLabel} que contendra el cronometro.
     */
    private JLabel textTime;

    /**
     * Creates new form GUIVFMuseos
     */
    public GUIMuseumsTF() {
        initComponents();
        setFrame();
        setTimePanel();
        setMuseumPanel();
        setButtonsPanel();
    }

    /**
     * setFrame: es el metodo principal que coloca en la vista los botones y el
     * titulo.
     */
    private void setFrame() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.getContentPane().setLayout(new BorderLayout());
    }

    /**
     * setTimePanel: panel que situa el cronometro.
     */
    private void setTimePanel() {
        JPanel extra = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        this.getContentPane().add(extra, BorderLayout.NORTH);
        textTime = new JLabel();
        extra.add(textTime);
    }

    /**
     * setMuseumPanel: coloca la etiqueta donde estara el nombre del museo y la 
     * formatea.
     */
    private void setMuseumPanel() {
        museumPanel = new JPanel(new BorderLayout());
        this.getContentPane().add(museumPanel, BorderLayout.CENTER);
        //etiqueta
        museumLabel = new JLabel("Museo");
        Font font = new Font(museumLabel.getFont().getName(), Font.PLAIN, 75);
        museumLabel.setHorizontalAlignment(SwingConstants.CENTER);
        museumLabel.setFont(font);
        museumPanel.add(museumLabel, BorderLayout.CENTER);
    }

    /**
     * setButtonsPanel: coloca los botones que devolveran existe o no existe.
     */
    private void setButtonsPanel() {
        buttonsPanel = new JPanel(new GridLayout(1, 2));
        this.getContentPane().add(buttonsPanel, BorderLayout.SOUTH);
        //boton existe
        existButton = new JButton("Existe");
        Font font = new Font(existButton.getFont().getName(), Font.PLAIN, 50);
        existButton.setPreferredSize(new Dimension(100, 100));
        existButton.setFont(font);
        existButton.setForeground(Colors.THEME_ORANGE);
        //boton no existe
        notExistButton = new JButton("No existe");
        notExistButton.setForeground(Colors.THEME_ORANGE);
        notExistButton.setPreferredSize(new Dimension(100, 100));
        notExistButton.setFont(font);
        buttonsPanel.add(existButton, BorderLayout.WEST);
        buttonsPanel.add(notExistButton, BorderLayout.EAST);
    }

    //GETTER/SETTER
    /**
     * getMuseumLabel: devuelve un {@link JLabel} donde se situa el nombre del 
     * museo.
     *
     * @return un {@link JLabel}
     */
    public JLabel getMuseumLabel() {
        return museumLabel;
    }

    /**
     * getOptions: devuelve un {@link JButton} que es el boton que dice que el
     * museo existe.
     *
     * @return una lista de {@link JButton}
     */
    public JButton getExistButton() {
        return existButton;
    }

    /**
     * getOptions: devuelve un {@link JButton} que es el boton que dice que el
     * museo no existe.
     *
     * @return una lista de {@link JButton}
     */
    public JButton getNotExistButton() {
        return notExistButton;
    }

    /**
     * getTextTime: devuelve el {@link JLabel} del cronometro.
     *
     * @see Crono
     * 
     * @return un {@link JLabel}
     */
    public JLabel getTextTime() {
        return textTime;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

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
