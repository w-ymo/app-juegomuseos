/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gf.app.juegomuseos.controller;

import com.gf.app.juegomuseos.dao.ArtworkDAO;
import com.gf.app.juegomuseos.dao.AuthorDAO;
import com.gf.app.juegomuseos.dao.CountryDAO;
import com.gf.app.juegomuseos.dao.MuseumDAO;
import com.gf.app.juegomuseos.models.Country;
import com.gf.app.juegomuseos.models.Museum;
import com.gf.app.juegomuseos.utils.Crono;
import com.gf.app.juegomuseos.views.GUIMap;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
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

//    private List<GeoPosition> gpList;
    private Set<Waypoint> waypoints;
    private WaypointPainter<Waypoint> painter;

    private GeoPosition position;
    private ArtworkDAO awDAO = new ArtworkDAO();
    private AuthorDAO atDAO = new AuthorDAO();
    private CountryDAO cDAO = new CountryDAO();
    private MuseumDAO mDAO = new MuseumDAO();

    private Country cSolution;

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
        initGame();
    }

    private void initGame() {
        nose();
        System.out.println("Tu prima");
    }
    
    private MouseAdapter ma = new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            GeoPosition clickPosition = view.getMapKit().getMainMap().convertPointToGeoPosition(view.getMapKit().getMainMap().getMousePosition());
            double lat = clickPosition.getLatitude();
            double lon = clickPosition.getLongitude();
            position = new GeoPosition(lat, lon);
            waypoints = new HashSet<>();
            waypoints.add(new DefaultWaypoint(position));
            painter.setWaypoints(waypoints);
            view.getMapKit().getMainMap().setOverlayPainter(painter);
            System.out.println(view.getMapKit().getMainMap()    );
        }
    };

    private void nose() {
        painter = new WaypointPainter<>();
        view.getMapKit().getMainMap().addMouseListener(ma);
    }

    public static void main(String[] args) {
        MapController mc = new MapController(new GUIMap(), false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
    }
}
