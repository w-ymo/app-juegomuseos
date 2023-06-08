/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gf.app.juegomuseos.controller;

import com.gf.app.juegomuseos.dao.ArtworkDAO;
import com.gf.app.juegomuseos.dao.AuthorDAO;
import com.gf.app.juegomuseos.dao.CountryDAO;
import com.gf.app.juegomuseos.dao.MuseumDAO;
import com.gf.app.juegomuseos.models.Artwork;
import com.gf.app.juegomuseos.models.Author;
import com.gf.app.juegomuseos.models.Country;
import com.gf.app.juegomuseos.models.Museum;
import com.gf.app.juegomuseos.utils.CountryExtractor;
import com.gf.app.juegomuseos.utils.Crono;
import com.gf.app.juegomuseos.utils.GameConstants;
import com.gf.app.juegomuseos.utils.ImagesSize;
import com.gf.app.juegomuseos.views.GUIGregorioFernandez;
import com.gf.app.juegomuseos.views.GUIMap;
import com.gf.app.juegomuseos.views.ResultDialog;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import org.jxmapviewer.JXMapKit;
import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.viewer.DefaultWaypoint;
import org.jxmapviewer.viewer.GeoPosition;
import org.jxmapviewer.viewer.Waypoint;
import org.jxmapviewer.viewer.WaypointPainter;

/**
 *
 * @author Luis
 */
public class MapController implements ActionListener {

    private GameControllers parent;
    private GUIMap view;
    //repetidos
    private ArrayList<Integer> repeatedDB = new ArrayList<>();
    //nombre de paises
    private String artworkCountry;
    private String clickedPositionCountry;
    //pojo
    private Artwork artworkSolution;
    private Author authorSolution;
    //waypoint personalizado
    private Waypoint clickWaypoint;
    //JXMapViewer
    private Set<GeoPosition> positionSet = new HashSet<>();
    private Set<Waypoint> waypointSet = new HashSet<>();
    private WaypointPainter<Waypoint> waypointPainter = new WaypointPainter<>();
    private GeoPosition clickedPosition;
    private GeoPosition artworkPosition;
    private ArtworkDAO awDAO = new ArtworkDAO();
    private AuthorDAO atDAO = new AuthorDAO();

    private boolean mode;
    private int counter;
    private int fails;
    private Crono timer;

    public MapController(GUIMap view, boolean mode) {
        this.view = view;
        this.counter = 0;
        this.mode = mode;
        getGameData();
        launchGame();
    }

    private void openMenu() {
        if (parent instanceof SelectGameController parentC) {
            parentC.getMainController().getView().setVisible(true);
        }
    }

    private void getGameData() {
        if (parent instanceof MainController parentC) {
            this.fails = parentC.getFails();
            this.timer = parentC.getTimer();
        }
    }

    private void setGameData() {
        if (parent instanceof MainController parentC) {
            parentC.setFails(fails);
        }
    }

    private void launchGame() {
        view.getConfirmButton().addActionListener(this);
        view.getMapKit().getMainMap().addMouseListener(ma);
        initGame();
    }

    private void initGame() {
        Collections.sort(repeatedDB);
        try {
            do {

                artworkSolution = awDAO.selectNum(1).get(0);
                if (Collections.binarySearch(repeatedDB, artworkSolution.getId_obra()) < 0) {
                    repeatedDB.add(artworkSolution.getId_obra());
                }

            } while (Collections.binarySearch(repeatedDB, artworkSolution.getId_obra()) >= 0);

            view.getArtworkLabel().setText(artworkSolution.getNombre_obra());

            authorSolution = atDAO.selectId(artworkSolution.getId_autor());
            view.getAuthorLabel().setText(authorSolution.getNombre_autor());

        } catch (SQLException ex) {
            System.err.println("Error de Base de Datos");
        }
        setIcon();

        artworkPosition = new GeoPosition(artworkSolution.getLatitud(), artworkSolution.getLongitud());
        artworkCountry = CountryExtractor.getCountryName(artworkSolution.getLatitud(), artworkSolution.getLongitud());
    }

    private void setArtworkPosition() {
        positionSet.add(artworkPosition);   //añade posicion de la obra al set de posiciones
        waypointSet.add(new DefaultWaypoint(artworkPosition));  //nuevo dw con posicion de la obra y se añade al set de waypoints
        waypointPainter.setWaypoints(waypointSet);  //pinta todos los waypoints del set de wp
    }

    public void setIcon() {
        ImageIcon imageIcon = null;
        try {
            imageIcon = new ImageIcon(new URL(artworkSolution.getImagen_obra()));
        } catch (MalformedURLException ex) {
            System.err.println("Imagen mal formada.");
        }
        Image proportionalImage = ImagesSize.getProportionalDimensionImage(imageIcon, view.getArtworkImage().getSize());
        view.getArtworkImage().setIcon(new ImageIcon(proportionalImage));
    }

    private MouseAdapter ma = new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            positionSet.clear();
            waypointSet.clear();
            try {
                if (e.getButton() == MouseEvent.BUTTON3) {
                    clickedPosition = view.getMapKit().getMainMap()
                            .convertPointToGeoPosition(view.getMapKit().getMainMap().getMousePosition());
                    positionSet.add(clickedPosition);
                    waypointSet.add(new DefaultWaypoint(clickedPosition));
                    waypointPainter.setWaypoints(waypointSet);
                    view.getMapKit().getMainMap().setOverlayPainter(waypointPainter);
                    clickedPositionCountry = CountryExtractor
                            .getCountryName(clickedPosition.getLatitude(), clickedPosition.getLongitude());
                }
            } catch (NullPointerException ex) {
                System.out.println("No hay posicion de ratón");
                guessedWrong();
            }
        }
    };

    public void initTimer() {
        Timer t = new Timer(2000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                positionSet.clear();
                waypointSet.clear();
                view.getMapKit().getMainMap().setOverlayPainter(null);
            }
        });
        t.start();
        t.setRepeats(false);
    }

    private void guessedRight() {
        ResultDialog rd = new ResultDialog(view, true);
        rd.setOpacity(0.5f);
        rd.initTimer();
        rd.setVisible(true);
    }

    private void guessedWrong() {
        ResultDialog rd = new ResultDialog(view, false);
        rd.setOpacity(0.5f);
        rd.initTimer();
        rd.setVisible(true);
        fails++;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (artworkCountry == null || clickedPositionCountry == null) {
            JOptionPane.showMessageDialog(view, "Debes seleccionar una posicion", "Error", JOptionPane.INFORMATION_MESSAGE);
        } else {
            setArtworkPosition();
            view.getMapKit().getMainMap().zoomToBestFit(positionSet, 0.7);
            view.getMapKit().getMainMap().setZoom(15);
            if (artworkCountry.equals(clickedPositionCountry)) {
                guessedRight();
            } else {
                guessedWrong();
            }
            counter++;

            artworkCountry = null;
            clickedPositionCountry = null;

            if (counter < 10) {
                initGame();
            } else {
                if (mode == GameConstants.COMP_MODE) {
                    setGameData();
                    GregFernandezController nextGame = new GregFernandezController(new GUIGregorioFernandez(), parent, mode);
                } else {
                    openMenu();
                }
                view.dispose();
            }
            waypointSet.remove(artworkSolution);
        }
    }

    public static void main(String[] args) {
        MapController mc = new MapController(new GUIMap(), false);
    }

}
