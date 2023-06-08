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
import com.gf.app.juegomuseos.models.Country;
import com.gf.app.juegomuseos.models.Museum;
import com.gf.app.juegomuseos.utils.CountryExtractor;
import com.gf.app.juegomuseos.utils.Crono;
import com.gf.app.juegomuseos.utils.GameConstants;
import com.gf.app.juegomuseos.views.GUIGregorioFernandez;
import com.gf.app.juegomuseos.views.GUIMap;
import com.gf.app.juegomuseos.views.ResultDialog;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.Timer;
import org.jxmapviewer.JXMapKit;
import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.viewer.DefaultWaypoint;
import org.jxmapviewer.viewer.GeoPosition;
import org.jxmapviewer.viewer.Waypoint;
import org.jxmapviewer.viewer.WaypointPainter;
import org.jxmapviewer.viewer.WaypointRenderer;

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
    private Artwork artwork;
    //waypoint personalizado
    private Waypoint clickWaypoint;
    //JXMapViewer
    private Set<GeoPosition> positionSet;
    private Set<Waypoint> waypointSet = new HashSet<>();
    private WaypointPainter<Waypoint> waypointPainter = new WaypointPainter<>();
    private GeoPosition clickPosition;
    private GeoPosition artworkPosition;
    private ArtworkDAO awDAO = new ArtworkDAO();

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
        view.getMapKit().getMainMap().setOverlayPainter(waypointPainter);
        view.getConfirmButton().addActionListener(this);
        view.getMapKit().getMainMap().addMouseListener(ma);
        initGame();
    }

    private void initGame() {
            Collections.sort(repeatedDB);
//            do {
//                try {
//                    retrieveAndAsignArtwork();
//                    //cosas--
//                    mSolution = mDAO.selectNum(1).get(0);
//                    if (Collections.binarySearch(repeatedDB, mSolution.getId_museo()) < 0) {
//                        repeatedDB.add(mSolution.getId_museo());
//                    }
//                } catch (SQLException ex) {
//                    ex.printStackTrace();
//                }
//            } while (Collections.binarySearch(repeatedDB, mSolution.getId_museo()) >= 0);
//
//            boolean found;
//            do {
//                int index = rndm.nextInt(0, FAKE_MUSEUMS.length);
//                if (!repeatedFake.contains(index)) {
//                    mSolution.setNombre_museo(FAKE_MUSEUMS[index]);
//                    repeatedFake.add(index);
//                    found = false;
//                } else {
//                    found = true;
//                }
//            } while (found);
    }
    
    //-----------------------------------------------------------------------------------

//    private void setWaypoint() {
//        clickWaypoint = new Waypoint() {
//            @Override
//            public GeoPosition getPosition() {
//
//            }
//        };
//    }
    private void getArtworkCountry() {
        retrieveAndAsignArtwork();  //coge una obra
        double latitude = artwork.getLatitud();
        double longitude = artwork.getLongitud();
        setArtworkPosition(latitude, longitude); //posicion de obra
        artworkCountry = CountryExtractor.getCountryName(latitude, longitude);  //pone el nombre del pais a su variable
    }

    private void retrieveAndAsignArtwork() {
        try {
            List<Artwork> artworkList = awDAO.selectNum(1);
            artwork = artworkList.get(0);
        } catch (SQLException ex) {
            System.err.println("Error en la Base de Datos");
        }
    }

    private void setArtworkPosition(double latitude, double longitude) {
        artworkPosition = new GeoPosition(latitude, longitude);  //GeoPosition de la obra
        positionSet.add(artworkPosition);   //añade posicion de la obra al set de posiciones
        waypointSet.add(new DefaultWaypoint(artworkPosition));  //nuevo dw con posicion de la obra y se añade al set de waypoints
        waypointPainter.setWaypoints(waypointSet);  //pinta todos los waypoints del set de wp
    }

    private void setClickedPosition(double latitude, double longitude) {
        clickPosition = new GeoPosition(latitude, longitude);
        positionSet.add(clickPosition);
        waypointSet.add(new DefaultWaypoint(clickPosition));
        waypointPainter.setWaypoints(waypointSet);
    }

    private MouseAdapter ma = new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            try {
                if (e.getButton() == MouseEvent.BUTTON3) {
                    positionSet = new HashSet<>();
                    waypointSet = new HashSet<>();
                    clickPosition = view.getMapKit().getMainMap()
                            .convertPointToGeoPosition(view.getMapKit().getMainMap().getMousePosition());
                    double latitude = clickPosition.getLatitude();
                    double longitude = clickPosition.getLongitude();
                    setClickedPosition(latitude, longitude);
                    clickedPositionCountry = CountryExtractor.getCountryName(latitude, longitude);
                }
            } catch (NullPointerException ex) {
                System.out.println("No hay posicion de ratón");
                guessedWrong();
            }
        }
    };

    public void initTimer() {
        Thread t = new Thread(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t.start();
    }

    private void guessedRight() {
        ResultDialog rd = new ResultDialog(view, true);
        rd.initTimer();
        rd.setVisible(true);
    }

    private void guessedWrong() {
        ResultDialog rd = new ResultDialog(view, false);
        rd.initTimer();
        rd.setVisible(true);
        fails++;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //al final el overlay

        view.getMapKit().getMainMap().zoomToBestFit(positionSet, 0.7);
        view.getMapKit().getMainMap().setZoom(17);
        clickPosition.getLatitude();
        clickPosition.getLongitude();

        if (artworkCountry.equals(clickedPositionCountry)) {
            guessedRight();
        } else {
            guessedWrong();
        }
        counter++;

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
        waypointSet.remove(artwork);
    }

    public static void main(String[] args) {
        MapController mc = new MapController(new GUIMap(), false);
    }

}
