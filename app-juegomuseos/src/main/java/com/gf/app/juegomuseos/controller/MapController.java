/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gf.app.juegomuseos.controller;

import com.gf.app.juegomuseos.dao.ArtworkDAO;
import com.gf.app.juegomuseos.dao.AuthorDAO;
import com.gf.app.juegomuseos.models.Artwork;
import com.gf.app.juegomuseos.models.Author;
import com.gf.app.juegomuseos.utils.CountryExtractor;
import com.gf.app.juegomuseos.utils.Crono;
import com.gf.app.juegomuseos.utils.GameConstants;
import com.gf.app.juegomuseos.utils.ImagesSize;
import com.gf.app.juegomuseos.utils.RoutePainter;
import com.gf.app.juegomuseos.views.GUIGregorioFernandez;
import com.gf.app.juegomuseos.views.GUIInputRanking;
import com.gf.app.juegomuseos.views.GUIMap;
import com.gf.app.juegomuseos.views.ResultDialog;
import com.gf.app.juegomuseos.views.VisualizeImage;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.painter.CompoundPainter;
import org.jxmapviewer.painter.Painter;
import org.jxmapviewer.viewer.DefaultWaypoint;
import org.jxmapviewer.viewer.GeoPosition;
import org.jxmapviewer.viewer.Waypoint;
import org.jxmapviewer.viewer.WaypointPainter;

/**
 *
 * @author Luis
 */
public class MapController implements ActionListener, GameControllers {

    private GameControllers parent;
    private GUIMap view;

    //repetidos
    private ArrayList<Integer> repeatedDB = new ArrayList<>();

    //nombre de paises
    private String artworkCountry;
    private String clickedPositionCountry;

    //pojos
    private Artwork artworkSolution;
    private Author authorSolution;

    //JXMapViewer
    private Set<GeoPosition> positionSet = new HashSet<>();
    private Set<Waypoint> waypointSet = new HashSet<>();
    private WaypointPainter<Waypoint> waypointPainter = new WaypointPainter<>();
    private GeoPosition clickedPosition;
    private GeoPosition artworkPosition;
    private ArtworkDAO awDAO = new ArtworkDAO();
    private AuthorDAO atDAO = new AuthorDAO();
    private List<GeoPosition> track;

    private boolean mode;
    private int counter;
    private int fails;
    private Crono timer;

    public MapController(GUIMap view, GameControllers parent, boolean mode) {
        this.view = view;
        this.counter = 0;
        this.mode = mode;
        this.parent = parent;
        getGameData();
        if (mode == GameConstants.COMP_MODE) {
            this.timer.setTextTime(view.getTextTime());
        }
        addListeners();
        launch();
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
        rd.initTimer();
        rd.setVisible(true);
    }

    private void guessedWrong() {
        ResultDialog rd = new ResultDialog(view, false);
        rd.initTimer();
        rd.setVisible(true);
        fails++;
    }

    private void createRoute() {
        setArtworkPosition();
        view.getMapKit().getMainMap().zoomToBestFit(positionSet, 0.7);
        track = Arrays.asList(artworkPosition, clickedPosition);
        RoutePainter rp = new RoutePainter(track);
        List<Painter<JXMapViewer>> painters = new ArrayList<Painter<JXMapViewer>>();
        painters.add(rp);
        painters.add(waypointPainter);
        CompoundPainter<JXMapViewer> compPainter = new CompoundPainter<JXMapViewer>(painters);
        view.getMapKit().getMainMap().setOverlayPainter(compPainter);
    }

    private void addListeners() {
        view.getConfirmButton().addActionListener(this);
        view.getMapKit().getMainMap().addMouseListener(ma1);
        view.getArtworkImage().addMouseListener(ma2);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (artworkCountry == null || clickedPositionCountry == null) {
            JOptionPane.showMessageDialog(view, "Debes seleccionar una posicion", "Error", JOptionPane.INFORMATION_MESSAGE);
        } else {
            createRoute();
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
                    InputRankingController nextGame = new InputRankingController(new GUIInputRanking(), parent);
                } else {
                    openMenu();
                }
                view.dispose();
            }
            waypointSet.remove(artworkSolution);
        }
    }

    private void closeParentView() {
        if (parent instanceof SelectGameController parentC) {
            parentC.getView().setVisible(false);
        } else if (parent instanceof MainController parentC) {
            parentC.getView().setVisible(false);
        }
    }

    private MouseAdapter ma1 = new MouseAdapter() {
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

    private MouseAdapter ma2 = new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            if (e.getClickCount() == 2) {
                VisualizeImage vi = new VisualizeImage(view, true);
                ImageIcon i = null;
                try {
                    i = new ImageIcon(new URL(artworkSolution.getImagen_obra()));
                } catch (MalformedURLException ex) {
                    System.err.println("Imagen mal formada.");
                }
                vi.getImage().setIcon(new ImageIcon(ImagesSize.getProportionalDimensionImage(i, new Dimension((int) (GameConstants.SCREEN_SIZE.width * 0.9), (int) (GameConstants.SCREEN_SIZE.height * 0.8)))));
                vi.getContentPane().setPreferredSize(vi.getImage().getPreferredSize());
                vi.pack();
                vi.setVisible(true);
            }
        }
    };

    @Override
    public void launch() {
        view.setVisible(true);
        closeParentView();
        initGame();
    }
}
