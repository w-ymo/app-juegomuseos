/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.gf.app.juegomuseos.views;

import com.gf.app.juegomuseos.utils.Colors;
import com.gf.app.juegomuseos.utils.GameConstants;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * GUIWhoIs: vista del controlador {@link WhoIsController} donde se muestran 4
 * botones y una imagen y se debe escoger cual es el autor de la imagen.
 *
 * @see WhoIsController
 *
 * @author priparno
 */
public class GUIWhoIs extends javax.swing.JFrame {

    /**
     * panelImages: un panel que contiene la imagen y el nombre de la obra.
     */
    private JPanel imagesPanel;
    /**
     * panelOptions: un panel que contiene los 4 botones de las opciones.
     */
    private JPanel optionsPanel;

    /**
     * image: es la etiqueta que contendra la imagen.
     */
    private JLabel image;
    /**
     * imageText: la etiqueta que contendra el nombre de la obra.
     */
    private JLabel imageText;
    /**
     * options: una lista de {@link JButton} que tendran el nombre de los
     * autores para adivinarlo.
     */
    private List<JButton> options = new ArrayList<>();
    /**
     * textTime: es la {@link JLabel} que contendra el cronometro.
     */
    private JLabel textTime;

    /**
     * Creates new form GUIWhoIs
     */
    public GUIWhoIs() {
        initComponents();
        setFrame();
    }

    /**
     * setFrame: es el metodo principal que coloca en la vista los botones y el
     * titulo.
     */
    private void setFrame() {
        this.setExtendedState(MAXIMIZED_BOTH);
        this.getContentPane().setSize(GameConstants.SCREEN_SIZE);
        this.getContentPane().setLayout(new BorderLayout());
        setTimePanel();
        setImagePanel();
        setOptionPanel();
    }

    /**
     * setTimePanel: panel que situa el cronometro.
     */
    private void setTimePanel() {
        JPanel extra = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        textTime = new JLabel();
        extra.add(textTime);
        this.getContentPane().add(extra, BorderLayout.NORTH);
    }

    /**
     * setImagePanel: coloca la etiqueta donde estara la imagen y la etiqueta
     * donde se situara el nombre de la obra.
     */
    private void setImagePanel() {
        imagesPanel = new JPanel(new BorderLayout());
        imagesPanel.setSize(new Dimension(this.getContentPane().getWidth(), (int) (this.getContentPane().getHeight() * 0.8)));
        this.getContentPane().add(imagesPanel, BorderLayout.CENTER);
        image = new JLabel();
        imageText = new JLabel("Bottom text");
        imageText.setPreferredSize(new Dimension(this.getSize().width, (int) (this.getSize().height * 0.2)));
        imageText.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
        image.setHorizontalAlignment(SwingConstants.CENTER);
        imageText.setHorizontalAlignment(SwingConstants.CENTER);
        imagesPanel.add(image, BorderLayout.CENTER);
        imagesPanel.add(imageText, BorderLayout.SOUTH);
    }

    /**
     * setOptionPanel: coloca los botones que incluiran el nombre de los
     * autores.
     */
    private void setOptionPanel() {
        optionsPanel = new JPanel(new GridLayout(2, 2));
        optionsPanel.setPreferredSize(new Dimension(this.getSize().width, (int) (this.getSize().height * 0.5)));
        this.getContentPane().add(optionsPanel, BorderLayout.SOUTH);
        initOptions();
        for (JButton option : options) {
            optionsPanel.add(option);
        }
    }

    /**
     * initOptions: crea los botones.
     */
    private void initOptions() {
        for (int i = 0; i < 4; i++) {
            JButton but = new JButton("button");
            but.setForeground(Colors.ONYX);
            but.setFont(this.getFont().deriveFont(Font.BOLD, 18f));
            but.setBackground(Colors.BUTTONS_COLORS[i]);
            options.add(but);
        }
    }

    //GETTER/SETTER
    /**
     * getImage: devuelve un {@link JLabel} donde se situa la imagen.
     *
     * @return un {@link JLabel}
     */
    public JLabel getImage() {
        return image;
    }

    /**
     * getImageText: devuelve un {@link JLabel} donde se situa el nombre de la
     * obra.
     *
     * @return un {@link JLabel}
     */
    public JLabel getImageText() {
        return imageText;
    }

    /**
     * getOptions: devuelve una lista de {@link JButton} que son los botones que
     * incluiran los nombres de los autores.
     *
     * @return una lista de {@link JButton}
     */
    public List<JButton> getOptions() {
        return options;
    }

    /**
     * getPanelImages: devuelve un panel {@link JPanel} que contiene la imagen.
     *
     * @return un {@link JPanel}
     */
    public JPanel getPanelImages() {
        return imagesPanel;
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
