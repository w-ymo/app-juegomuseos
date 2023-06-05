/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package com.gf.app.juegomuseos.views;

import com.gf.app.juegomuseos.utils.Colors;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author fercaslu
 */
public class GUIMuseumsTF extends javax.swing.JFrame {

    //com.formdev.flatlaf.intellijthemes.FlatArcDarkOrangeIJTheme -> Claro
    //com.formdev.flatlaf.intellijthemes.FlatArcOrangeIJTheme -> Oscuro
    
    private GUIPrincipal guip;
    private JPanel museumPanel;
    private JPanel buttonsPanel;
    
    private JLabel museumLabel;
    
    private JButton trueButton;
    private JButton falseButton;

    /**
     * Creates new form GUIVFMuseos
     */
    public GUIMuseumsTF() {
        try {
            UIManager.setLookAndFeel("com.formdev.flatlaf.intellijthemes.FlatArcDarkOrangeIJTheme");
            initComponents();     
            setFrame();
            setMuseumPanel();
            setButtonsPanel();
               
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(GUIMuseumsTF.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.setUndecorated(true);
    }

    private void setFrame() {
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.getContentPane().setLayout(new BorderLayout());
    }
    
    private void setMuseumPanel() {     
        museumPanel = new JPanel(new BorderLayout());
        this.getContentPane().add(museumPanel, BorderLayout.CENTER);
        //Label
        museumLabel = new JLabel("Museo");
        Font font = new Font(museumLabel.getFont().getName(), Font.PLAIN, 75);
        museumLabel.setHorizontalAlignment(SwingConstants.CENTER);
        museumLabel.setFont(font); 
        museumPanel.add(museumLabel, BorderLayout.CENTER);
    }
    
    private void setButtonsPanel() {
        buttonsPanel = new JPanel(new GridLayout(1, 2));
        this.getContentPane().add(buttonsPanel, BorderLayout.SOUTH);
        //True button
        trueButton = new JButton("Existe");
        Font font = new Font(trueButton.getFont().getName(), Font.PLAIN, 50);
        trueButton.setPreferredSize(new Dimension(100, 100));
        trueButton.setFont(font);
        trueButton.setForeground(Colors.THEME_ORANGE);
        //False button
        falseButton = new JButton("No existe");
        falseButton.setForeground(Colors.THEME_ORANGE);
        falseButton.setPreferredSize(new Dimension(100, 100));
        falseButton.setFont(font);
        buttonsPanel.add(trueButton, BorderLayout.WEST);
        buttonsPanel.add(falseButton, BorderLayout.EAST);
    }

    public JLabel getMuseumLabel() {
        return museumLabel;
    }

    public void setMuseumLabel(JLabel museumLabel) {
        this.museumLabel = museumLabel;
    }
    
    public JButton getTrueButton() {
        return trueButton;
    }

    public void setTrueButton(JButton trueButton) {
        this.trueButton = trueButton;
    }

    public JButton getFalseButton() {
        return falseButton;
    }

    public void setFalseButton(JButton falseButton) {
        this.falseButton = falseButton;
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

    /**
     * @param args the command line arguments
     */
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
//            java.util.logging.Logger.getLogger(GUIMuseumsTF.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(GUIMuseumsTF.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(GUIMuseumsTF.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(GUIMuseumsTF.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//
//        /* Create and display the dialog */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                GUIMuseumsTF frame = new GUIMuseumsTF();
//                frame.addWindowListener(new java.awt.event.WindowAdapter() {
//                    @Override
//                    public void windowClosing(java.awt.event.WindowEvent e) {
//                        System.exit(0);
//                    }
//                });
//                frame.setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
