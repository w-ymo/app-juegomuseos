/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.gf.app.juegomuseos.views;

import com.gf.app.juegomuseos.utils.GameConstants;
import java.awt.BorderLayout;
import java.awt.Dimension;
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
import org.jxmapviewer.JXMapKit;
import org.jxmapviewer.OSMTileFactoryInfo;
import org.jxmapviewer.VirtualEarthTileFactoryInfo;
import org.jxmapviewer.viewer.DefaultTileFactory;
import org.jxmapviewer.viewer.TileFactoryInfo;

/**
 *
 * @author Luis
 */
public class GUIMap extends javax.swing.JFrame {
    
    private JXMapKit mapKit = new JXMapKit();
    private VirtualEarthTileFactoryInfo info;
    private DefaultTileFactory tileFactory;
    private JPanel infoPanel;
    private JLabel artworkImage;
    private JLabel artworkLabel;
    private JLabel authorLabel;
    private JButton confirmButton;

    /**
     * Creates new form GUIMap
     */
    public GUIMap() {
        try {
            UIManager.setLookAndFeel("com.formdev.flatlaf.intellijthemes.FlatArcDarkOrangeIJTheme");
            initComponents();
            setFrame();
            this.setVisible(true);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(GUIMuseumsTF.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void setFrame() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.getContentPane().setLayout(new BorderLayout());
        setMapKit();
        setInfoPanel();
        this.getContentPane().add(mapKit, BorderLayout.CENTER);
        this.getContentPane().add(infoPanel, BorderLayout.WEST);
    }
    
    private void setInfoPanel() {
        infoPanel = new JPanel(new GridLayout(4, 0));
        artworkImage = new JLabel("Buenos dias soy una jlabel");
        artworkLabel = new JLabel("Hola");
        authorLabel = new JLabel("Jelou mansana");
        confirmButton = new JButton("Fijar País");
        
        artworkLabel.setPreferredSize(new Dimension(artworkImage.getSize().width, (int) (GameConstants.SCREEN_SIZE.height * 0.33)));
        authorLabel.setPreferredSize(new Dimension(artworkImage.getSize().width, (int) (GameConstants.SCREEN_SIZE.height * 0.33)));
        artworkLabel.setHorizontalAlignment((int) CENTER_ALIGNMENT);
        authorLabel.setHorizontalAlignment((int) CENTER_ALIGNMENT);
        
        infoPanel.add(artworkImage);
        infoPanel.add(artworkLabel);
        infoPanel.add(authorLabel);
        infoPanel.add(confirmButton);
    }
    
    private void setMapKit() {
        info = new VirtualEarthTileFactoryInfo(VirtualEarthTileFactoryInfo.HYBRID);
        tileFactory = new DefaultTileFactory(info);
        mapKit.setTileFactory(tileFactory);
        mapKit.setZoom(17);
        mapKit.getMainMap().setOverlayPainter(null);
        mapKit.setBounds(0, -65, GameConstants.SCREEN_SIZE.width, GameConstants.SCREEN_SIZE.height);
    }
    
    public JXMapKit getMapKit() {
        return mapKit;
    }
    
    public void setMapKit(JXMapKit mapKit) {
        this.mapKit = mapKit;
    }
    
    public JPanel getInfoPanel() {
        return infoPanel;
    }
    
    public void setInfoPanel(JPanel infoPanel) {
        this.infoPanel = infoPanel;
    }
    
    public JLabel getArtworkImage() {
        return artworkImage;
    }
    
    public void setArtworkImage(JLabel artworkImage) {
        this.artworkImage = artworkImage;
    }
    
    public JLabel getArtworkLabel() {
        return artworkLabel;
    }
    
    public void setArtworkLabel(JLabel artworkLabel) {
        this.artworkLabel = artworkLabel;
    }
    
    public JLabel getAuthorLabel() {
        return authorLabel;
    }
    
    public void setAuthorLabel(JLabel authorLabel) {
        this.authorLabel = authorLabel;
    }

    public JButton getConfirmButton() {
        return confirmButton;
    }

    public void setConfirmButton(JButton confirmButton) {
        this.confirmButton = confirmButton;
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
//            java.util.logging.Logger.getLogger(GUIMap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(GUIMap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(GUIMap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(GUIMap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new GUIMap().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
