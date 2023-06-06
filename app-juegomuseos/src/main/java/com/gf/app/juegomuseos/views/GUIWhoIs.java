/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.gf.app.juegomuseos.views;

import com.gf.app.juegomuseos.utils.Colors;
import com.gf.app.juegomuseos.utils.GameConstants;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author priparno
 */
public class GUIWhoIs extends javax.swing.JFrame {

    private GUIPrincipal guip;

    //aniadir crono
    //aniadir boton info?
    private JPanel panelImages;
    private JPanel panelOptions;

    private static final Color[] colores = Colors.BUTTONS_COLORS;

    private JLabel image;
    private JLabel imageText;
    private List<JButton> options = new ArrayList<>();

    //net.sourceforge.napkinlaf.NapkinLookAndFeel
    /**
     * Creates new form GUIWhoIs
     */
    public GUIWhoIs() {
//        try {
//            UIManager.setLookAndFeel("com.formdev.flatlaf.intellijthemes.FlatArcDarkOrangeIJTheme");
//            this.setUndecorated(true);
            initComponents();
            setFrame();
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(GUIMuseumsTF.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            Logger.getLogger(GUIMuseumsTF.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            Logger.getLogger(GUIMuseumsTF.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (UnsupportedLookAndFeelException ex) {
//            Logger.getLogger(GUIMuseumsTF.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }

    private void setFrame() {
        this.setExtendedState(MAXIMIZED_BOTH);
        this.getContentPane().setSize(GameConstants.SCREEN_SIZE);
        this.getContentPane().setLayout(new BorderLayout());
        setImagePanel();
        setOptionPanel();
        //setGame();
    }

    private void setImagePanel() {
        panelImages = new JPanel(new BorderLayout());
        panelImages.setSize(new Dimension(this.getContentPane().getWidth(), (int) (this.getContentPane().getHeight() * 0.8)));
        this.getContentPane().add(panelImages, BorderLayout.CENTER);
        image = new JLabel();
        imageText = new JLabel("Bottom text");
        imageText.setPreferredSize(new Dimension(this.getSize().width, (int) (this.getSize().height * 0.2)));
        imageText.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
        image.setHorizontalAlignment(SwingConstants.CENTER);
        imageText.setHorizontalAlignment(SwingConstants.CENTER);
        panelImages.add(image, BorderLayout.CENTER);
        panelImages.add(imageText, BorderLayout.SOUTH);
    }

    private void setOptionPanel() {
        panelOptions = new JPanel(new GridLayout(2, 2));
        panelOptions.setPreferredSize(new Dimension(this.getSize().width, (int) (this.getSize().height * 0.5)));
        this.getContentPane().add(panelOptions, BorderLayout.SOUTH);
        initOptions();
        for (JButton option : options) {
            panelOptions.add(option);
        }
    }

    private void initOptions() {
        for (int i = 0; i < 4; i++) {
            JButton but = new JButton("button");
            but.setForeground(Colors.ONYX);
            but.setFont(this.getFont().deriveFont(Font.BOLD, 18f));
            but.setBackground(colores[i]);
            options.add(but);
        }
    }

    public GUIPrincipal getGuip() {
        return guip;
    }

    public void setGuip(GUIPrincipal guip) {
        this.guip = guip;
    }

    public JLabel getImage() {
        return image;
    }

    public void setImage(JLabel image) {
        this.image = image;
    }

    public JLabel getImageText() {
        return imageText;
    }

    public void setImageText(JLabel imageText) {
        this.imageText = imageText;
    }

    public List<JButton> getOptions() {
        return options;
    }

    public void setOptions(List<JButton> options) {
        this.options = options;
    }

    public JPanel getPanelImages() {
        return panelImages;
    }

    public void setPanelImages(JPanel panelImages) {
        this.panelImages = panelImages;
    }

    public JPanel getPanelOptions() {
        return panelOptions;
    }

    public void setPanelOptions(JPanel panelOptions) {
        this.panelOptions = panelOptions;
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
//
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
//            java.util.logging.Logger.getLogger(GUIWhoIs.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(GUIWhoIs.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(GUIWhoIs.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(GUIWhoIs.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new GUIWhoIs().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
