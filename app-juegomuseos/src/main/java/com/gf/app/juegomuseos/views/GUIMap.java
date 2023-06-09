/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.gf.app.juegomuseos.views;

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
import org.jxmapviewer.JXMapKit;
import org.jxmapviewer.VirtualEarthTileFactoryInfo;
import org.jxmapviewer.viewer.DefaultTileFactory;

/**
 * GUIMap: vista del controlador {@link MapController} donde se muestra un mapa
 * y una obra de arte. El objetivo es colocar la obra de arte en el mapa.
 *
 * @see MapController
 *
 * @author fercaslu
 */
public class GUIMap extends javax.swing.JFrame {

    /**
     * mapKit: es un mapa de tipo {@link JXMapKit} preconfigurado que muestra un
     * mapa con una barra y dos botones de zoom.
     *
     * @see JXMapKit
     */
    private JXMapKit mapKit = new JXMapKit();

    /**
     * info: informacion detallada del mapa.
     */
    private VirtualEarthTileFactoryInfo info;

    /**
     * tileFactory: fabrica de las baldosas por defecto que utiliza Google Maps
     * como proyeccion de Mercator.
     */
    private DefaultTileFactory tileFactory;

    /**
     * infoPanel: panel que contiene la imagen, el nombre, el autor de la obra y
     * el boton para fijar la posicion del marcador.
     */
    private JPanel infoPanel;

    /**
     * chronoPanel: panel que contiene la {@link JLabel} del cronometro.
     */
    private JPanel chronoPanel;

    /**
     * textTime: es la {@link JLabel} que contendra el cronometro.
     */
    private JLabel textTime;

    /**
     * artworkImage: es la etiqueta que contendra la imagen de la obra.
     */
    private JLabel artworkImage;

    /**
     * artworkLabel: es la etiqueta que contendra el nombre de la obra.
     */
    private JLabel artworkLabel;

    /**
     * authorLabel: es la etiqueta que contendra el nombre del autor de la obra.
     */
    private JLabel authorLabel;

    /**
     * confirmButton: es el boton que bloqueara la posicion que se ha elegido
     * para la obra.
     */
    private JButton confirmButton;

    /**
     * Creates new form GUIMap
     */
    public GUIMap() {
        initComponents();
        setFrame();
    }

    /**
     * setFrame: es el metodo principal que coloca en la vista los componentes y
     * el titulo.
     */
    private void setFrame() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.getContentPane().setLayout(new BorderLayout());
        setMapKit();
        setInfoPanel();
        setTimePanel();
        this.getContentPane().add(mapKit, BorderLayout.CENTER);
        this.getContentPane().add(infoPanel, BorderLayout.WEST);
    }

    /**
     * setTimePanel: panel que situa el cronometro.
     */
    private void setTimePanel() {
        chronoPanel = new JPanel(new FlowLayout());
        textTime = new JLabel();
        chronoPanel.add(textTime);
        this.getContentPane().add(chronoPanel, BorderLayout.NORTH);
    }

    /**
     * setInfoPanel: coloca la etiqueta donde estara la imagen, las etiquetas
     * donde se situaran el nombre de la obra y el autor y el boton de
     * confirmar.
     */
    private void setInfoPanel() {
        infoPanel = new JPanel(new GridLayout(4, 0));
        Font font = new Font(infoPanel.getFont().getName(), Font.BOLD, 25);
        artworkImage = new JLabel();
        artworkLabel = new JLabel();
        authorLabel = new JLabel();
        confirmButton = new JButton("Fijar País");
        artworkLabel.setFont(font);
        authorLabel.setFont(font);
        confirmButton.setFont(font);
  
        artworkLabel.setPreferredSize(new Dimension(artworkImage.getSize().width, (int) (GameConstants.SCREEN_SIZE.height * 0.33)));
        authorLabel.setPreferredSize(new Dimension(artworkImage.getSize().width, (int) (GameConstants.SCREEN_SIZE.height * 0.33)));
        artworkLabel.setHorizontalAlignment((int) CENTER_ALIGNMENT);
        authorLabel.setHorizontalAlignment((int) CENTER_ALIGNMENT);

        infoPanel.add(artworkImage);
        infoPanel.add(artworkLabel);
        infoPanel.add(authorLabel);
        infoPanel.add(confirmButton);
    }

    /**
     * setMapKit: anyade la informacion sobre el mapa recogido, crea las
     * baldosas y ajusta el mapa a la pantalla.
     */
    private void setMapKit() {
        info = new VirtualEarthTileFactoryInfo(VirtualEarthTileFactoryInfo.HYBRID);
        tileFactory = new DefaultTileFactory(info);
        mapKit.setTileFactory(tileFactory);
        mapKit.setZoom(17);
        mapKit.getMainMap().setOverlayPainter(null);
        mapKit.setBounds(0, -65, GameConstants.SCREEN_SIZE.width, GameConstants.SCREEN_SIZE.height);
    }

    //GETTER/SETTER
    /**
     * getMapKit: devuelve un {@link JMapKit} donde se crea el mapa.
     *
     * @return un {@link JMapKit}
     */
    public JXMapKit getMapKit() {
        return mapKit;
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
     * getArtworkImage: devuelve un {@link JLabel} donde se situa la imagen de
     * la obra.
     *
     * @return un {@link JLabel}
     */
    public JLabel getArtworkImage() {
        return artworkImage;
    }

    /**
     * getImageText: devuelve un {@link JLabel} donde se situa el nombre de la
     * obra.
     *
     * @return un {@link JLabel}
     */
    public JLabel getArtworkLabel() {
        return artworkLabel;
    }

    /**
     * getImageText: devuelve un {@link JLabel} donde se situa el nombre del
     * autor de la obra.
     *
     * @return un {@link JLabel}
     */
    public JLabel getAuthorLabel() {
        return authorLabel;
    }

    /**
     * getConfirmButton: devuelve un {@link JButton} que al ser accionado
     * bloquea la posicion que se ha seleccionado sobre el mapa.
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
