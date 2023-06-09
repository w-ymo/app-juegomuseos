/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package com.gf.app.juegomuseos.views;

import com.gf.app.juegomuseos.utils.Colors;
import com.gf.app.juegomuseos.utils.GameConstants;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

/**
 * ResultDialog: es una ventana que muestra un mensaje de "CORRECTO" o
 * "INCORRECTO" dependiendo de si acierta o falla la pregunta. Este aparece
 * durante 1 segundo.
 *
 * @author priparno
 */
public class ResultDialog extends javax.swing.JDialog {

    /**
     * msgText: es la etiqueta que contendra el texto dependiendo de si es
     * correcto o no.
     */
    private JLabel msgText;
    /**
     * parent: es la vista padre, desde donde se llama.
     */
    private JFrame parent;
    /**
     * windowSize: el tamanio de la ventana, relativo al tamanio de la pantalla.
     */
    private Dimension windowSize;

    /**
     * Creates new form ResultDialog
     *
     * @param parent una vista
     * @param correct true -> imprime correcto, false -> imprime incorrecto
     */
    public ResultDialog(JFrame parent, boolean correct) {
        this.setUndecorated(true);
        this.setModal(true);
        this.parent = parent;
        initComponents();
        msgText = new JLabel();
        setFrame();
        setLabelStyle();
        if (correct) {
            setCorrect();
        } else {
            setIncorrect();
        }
    }

    /**
     * setFrame: es el metodo principal que coloca en la vista la etiqueta con
     * el texto.
     */
    private void setFrame() {
        this.setResizable(false);
        windowSize = new Dimension((int) (GameConstants.SCREEN_SIZE.width * 0.5), (int) (GameConstants.SCREEN_SIZE.height * 0.3));
        this.setSize(windowSize);
        this.getContentPane().setLayout(new BorderLayout());
        this.getContentPane().add(msgText, BorderLayout.CENTER);
        msgText.setHorizontalAlignment(SwingConstants.CENTER);
        this.setLocationRelativeTo(null);
    }

    /**
     * setCorrect: da valor a la etiqueta y color al fondo. Texto: CORRECTO.
     */
    private void setCorrect() {
        msgText.setText("Correcto");
        this.getContentPane().setBackground(Colors.GREEN);
    }

    /**
     * setIncorrect: da valor a la etiqueta y color al fondo. Texto: INCORRECTO.
     */
    private void setIncorrect() {
        msgText.setText("Incorrecto (+ 5s)");
        this.getContentPane().setBackground(Colors.RED);
    }

    /**
     * setLabelStyle: da estilo a las etiquetas.
     */
    private void setLabelStyle() {
        Font parentFont = parent.getFont();
        msgText.setFont(parentFont.deriveFont(Font.BOLD, 50f));
    }

    /**
     * initTimer: inicia un cronometro en el que en 1 segundo despues de que se
     * inicie se cierra el dialogo.
     */
    public void initTimer() {
        JDialog pane = this;
        Thread t = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.err.println("Se ha interrumpido la operacion.");
            }
            pane.dispose();
        });
        t.start();
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
