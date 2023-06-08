/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package com.gf.app.juegomuseos.views;

import com.gf.app.juegomuseos.utils.GameData;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JEditorPane;
import javax.swing.JScrollPane;

/**
 * GUIInfo: vista del controlador {@link InfoController} donde se muestra la
 * informacion sacada de AppInfo en {@link GameData#getInfoHtml()}.
 *
 * @see InfoController
 * @see GameData
 *
 * @author priparno
 */
public class GUIInfo extends javax.swing.JDialog {

    /**
     * 
     */
    private JEditorPane textArea;
    /**
     * 
     */
    private JScrollPane scrollPanel;

    /**
     * Creates new form GUIInfo
     */
    public GUIInfo(GUIPrincipal parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setFrame();
    }

    /**
     * 
     */
    private void setFrame() {
        textArea = new JEditorPane();
        scrollPanel = new JScrollPane(textArea);
        textArea.setContentType("text/html");
        textArea.setText(GameData.getInfoHtml());
        textArea.setEditable(false);
        this.setResizable(false);
        this.getContentPane().setLayout(new BorderLayout());
        this.getContentPane().add(scrollPanel);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setPreferredSize(new Dimension((int) (screenSize.width * 0.6), (int) (screenSize.height * 0.7)));
        this.pack();
        this.setLocationRelativeTo(null);
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
