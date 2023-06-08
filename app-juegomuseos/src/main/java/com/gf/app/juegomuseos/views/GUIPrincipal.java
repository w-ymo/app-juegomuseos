/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.gf.app.juegomuseos.views;

import com.gf.app.juegomuseos.utils.GameConstants;
import com.gf.app.juegomuseos.utils.GameData;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import static java.awt.Frame.MAXIMIZED_BOTH;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author priparno
 */
public class GUIPrincipal extends javax.swing.JFrame {

    private JPanel panelLogo;
    private JPanel panelOptions;

    private JLabel labelLogo;
    private List<JButton> options = new ArrayList<>();

    /**
     * Creates new form GUIPrincipal
     */
    public GUIPrincipal() {
        try {
            UIManager.setLookAndFeel(GameData.STYLE);
            this.setUndecorated(true);
            initComponents();
            setFrame();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(GUIMuseumsTF.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void setFrame() {
        this.setExtendedState(MAXIMIZED_BOTH);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.getContentPane().setSize(screenSize);
        this.getContentPane().setLayout(new BorderLayout());
        setLogo();
        setButtons();
    }

    private void setLogo() {
        panelLogo = new JPanel(new FlowLayout(FlowLayout.CENTER));
        labelLogo = new JLabel("HEY");
        panelLogo.add(labelLogo);
        this.getContentPane().add(panelLogo, BorderLayout.NORTH);
    }

    private void setButtons() {
        panelOptions = new JPanel(new FlowLayout());
        panelOptions.setSize((int) (this.getContentPane().getWidth() * 0.7), (int) (this.getContentPane().getHeight() * 0.3));
        JPanel gridPanel = new JPanel(new GridLayout(GameConstants.MAIN_MENU_OPTIONS.length, 0));
        for (int i = 0; i < GameConstants.MAIN_MENU_OPTIONS.length; i++) {
            JButton but = new JButton(GameConstants.MAIN_MENU_OPTIONS[i]);
            // but.setPreferredSize(new Dimension(panelOptions.getWidth(), panelOptions.getHeight() / 4));
            options.add(but);
            gridPanel.add(but);
        }
        panelOptions.add(gridPanel);
        this.getContentPane().add(panelOptions, BorderLayout.CENTER);
    }

    public JLabel getLabelLogo() {
        return labelLogo;
    }

    public void setLabelLogo(JLabel labelLogo) {
        this.labelLogo = labelLogo;
    }

    public List<JButton> getOptions() {
        return options;
    }

    public void setOptions(List<JButton> options) {
        this.options = options;
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
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.PAGE_AXIS));

        pack();
    }// </editor-fold>//GEN-END:initComponents

//    /**
//     * @param args the command line arguments
//     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(GUIPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(GUIPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(GUIPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(GUIPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new GUIPrincipal().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
