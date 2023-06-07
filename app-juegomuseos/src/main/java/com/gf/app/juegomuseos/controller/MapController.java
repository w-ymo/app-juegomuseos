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
import com.gf.app.juegomuseos.views.GUIMap;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.util.HashSet;
import java.util.Set;
import javax.swing.Timer;
import org.jxmapviewer.JXMapKit;
import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.viewer.DefaultWaypoint;
import org.jxmapviewer.viewer.GeoPosition;
import org.jxmapviewer.viewer.WaypointPainter;

/**
 *
 * @author Luis
 */
public class MapController {

    private GameControllers parent;
    private GUIMap view;

    private Set<DefaultWaypoint> waypoints;
    private WaypointPainter<DefaultWaypoint> painter;
    
    private ArtworkDAO awDAO = new ArtworkDAO();
    private AuthorDAO atDAO = new AuthorDAO();
    private CountryDAO cDAO = new CountryDAO();
    private MuseumDAO mDAO = new MuseumDAO();

    private Country cSolution;

    private boolean mode;

    private int counter;
    private int fails;
    private Timer timer;

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
        view.setVisible(true);
    }

    private void initGame() {
        idk();
    }
    
    private static class WaypointRenderer implements org.jxmapviewer.viewer.WaypointRenderer<DefaultWaypoint> {
        @Override
        public void paintWaypoint(Graphics2D g, JXMapViewer map, DefaultWaypoint waypoint) {
            // Convert the waypoint's GeoPosition to a Point2D on the map
            Point2D point = map.convertGeoPositionToPoint(waypoint.getPosition());

            // Draw a red circle at the waypoint's location
            g.setColor(Color.RED);
            int size = 10;
            int x = (int) point.getX() - (size / 2);
            int y = (int) point.getY() - (size / 2);
            g.fillOval(x, y, size, size);
        }
    }

    private void idk() {
        waypoints = new HashSet<>();
        painter = new WaypointPainter();
        painter.setWaypoints(waypoints);
        painter.setRenderer(new WaypointRenderer());
        view.getMapKit().getMainMap().setOverlayPainter(painter);
        
        view.getMapKit().getMainMap().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON3) {
                    Point2D point = new Point2D.Double(e.getPoint().getX(), e.getPoint().getY());

                    GeoPosition clickPosition = view.getMapKit().getMainMap().convertPointToGeoPosition(point);
                    double lat = clickPosition.getLatitude();
                    double lon = clickPosition.getLongitude();

                    DefaultWaypoint waypoint = new DefaultWaypoint(clickPosition);
                    waypoints.add(waypoint);
                    view.getMapKit().getMainMap().repaint();
                }
            }

        });
    }

    public static void main(String[] args) {
        MapController mc = new MapController(new GUIMap(), false);
        mc.initGame();
    }
}
