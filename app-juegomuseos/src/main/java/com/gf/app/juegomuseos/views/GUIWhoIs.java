/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.gf.app.juegomuseos.views;

import com.gf.app.juegomuseos.models.Artwork;
import com.gf.app.juegomuseos.models.Author;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

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

    private static final Color[] colores = {new Color(255, 80, 80), new Color(80, 255, 80), new Color(80, 80, 255), new Color(255, 230, 70)};

    private JLabel image;
    private JLabel imageText;
    private List<JButton> options = new ArrayList<>();

    private Artwork solution;
    
    int correct = 0;

    //net.sourceforge.napkinlaf.NapkinLookAndFeel
    /**
     * Creates new form GUIWhoIs
     */
    public GUIWhoIs() {
        initComponents();
        setFrame();
    }

    private void setFrame() {
        this.setExtendedState(MAXIMIZED_BOTH);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.getContentPane().setSize(screenSize);
        this.getContentPane().setLayout(new BorderLayout());
        setImagePanel();
        setOptionPanel();
        setGame();
    }

    private void setImagePanel() {
        panelImages = new JPanel(new BorderLayout());
        panelImages.setSize(new Dimension(this.getContentPane().getSize().width, (int) (this.getContentPane().getSize().height * 0.6)));
        this.getContentPane().add(panelImages, BorderLayout.CENTER);
        image = new JLabel();
        imageText = new JLabel("Bottom text");
        imageText.setPreferredSize(new Dimension(this.getSize().width, (int) (this.getSize().height * 0.4)));
        imageText.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
        image.setHorizontalAlignment(SwingConstants.CENTER);
        imageText.setHorizontalAlignment(SwingConstants.CENTER);
        panelImages.add(image, BorderLayout.CENTER);
        panelImages.add(imageText, BorderLayout.SOUTH);
    }

    
    //esto en el controlador
    private void setGame() {
        //while (correct < 5) {
            setIcon();
        //}
    }

    public void setIcon() {
        //aniadir la imagen
        ImageIcon i = null;
        try {
            i = new ImageIcon(new URL("https://picsum.photos/900/400"));
        } catch (MalformedURLException ex) {
            Logger.getLogger(GUIGregorioFernandez.class.getName()).log(Level.SEVERE, null, ex);
        }
        Image proportionalImage = i.getImage().getScaledInstance(panelImages.getWidth() - 400, panelImages.getHeight(), Image.SCALE_SMOOTH);
        image.setIcon(new ImageIcon(proportionalImage));

    }

    private void setOptionPanel() {
        panelOptions = new JPanel(new GridLayout(2, 2));
        panelOptions.setPreferredSize(new Dimension(this.getSize().width, (int) (this.getSize().height * 0.7)));
        this.getContentPane().add(panelOptions, BorderLayout.SOUTH);
        initOptions();
        for (JButton option : options) {
            panelOptions.add(option);
        }
    }

    private void initOptions() {
        for (int i = 0; i < 4; i++) {
            JButton but = new JButton("Boton " + i);
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

    public int getCorrect() {
        return correct;
    }

    public void setCorrect(int correct) {
        this.correct = correct;
    }

    public Artwork getSolution() {
        return solution;
    }

    public void setSolution(Artwork solution) {
        this.solution = solution;
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

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GUIWhoIs.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUIWhoIs.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUIWhoIs.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUIWhoIs.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUIWhoIs().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
