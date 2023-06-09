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
 * MapController: es el controlador de la ventana {@link GUIMap}. Desde ella se
 * lanza el juego y se controlan los fallos y aciertos. Implementa de
 * {@link GameControllers} y {@link ActionListener}. Se muestra una ventana con
 * un mapa politico y una obra y su autor. El objetivo seleccionar el pais que
 * expone dicha obra.
 *
 * @see GUIMap
 * @see GameControllers
 * @see ActionListener
 *
 * @author fercaslu
 */
public class MapController implements ActionListener, GameControllers {

    /**
     * view: es la vista del juego.
     */
    private GUIMap view;

    /**
     * parent: es el controlador padre, el que le llama.
     */
    private GameControllers parent;

    /**
     * repeatedDB: lista de enteros que almacena el id de la obra recogida de la
     * base de datos.
     */
    private ArrayList<Integer> repeatedDB = new ArrayList<>();

    /**
     * artworkCountry: almacena el nombre del pais donde se encuentra la obra
     * gracias a las coordenadas utilizando
     * {@link CountryExtractor#getCountryName(double, double)}
     *
     * @see CountryExtractor
     */
    private String artworkCountry;

    /**
     * clickedPositionCountry: almacena el nombre del pais donde se ha hecho
     * click gracias a las coordenadas utilizando
     * {@link CountryExtractor#getCountryName(double, double)}
     *
     * @see CountryExtractor
     */
    private String clickedPositionCountry;

    /**
     * artworkSolution: de tipo {@link Artwork}, obra recogida de la base de
     * datos la cual se mostrara en la ventana.
     *
     * @see Artwork
     */
    private Artwork artworkSolution;

    /**
     * authorSolution: de tipo {@link Author}, autor de la obra recogida de la
     * base de datos el cual se mostrara en la ventana.
     *
     * @see Author
     */
    private Author authorSolution;

    /**
     * awDAO: es la clase de acceso a la base de datos para obtener las obras.
     */
    private ArtworkDAO awDAO = new ArtworkDAO();

    /**
     * atDAO: es la clase de acceso a la base de datos para obtener los autores.
     */
    private AuthorDAO atDAO = new AuthorDAO();

    /**
     * positionSet: conjunto que recoge las posiciones de la obra y del click de
     * tipo {@link GeoPosition} para realizar zoom sobre todas ellas.
     *
     * @see GeoPosition
     */
    private Set<GeoPosition> positionSet = new HashSet<>();

    /**
     * waypointSet: conjunto que recoge las posiciones de la obra y del click de
     * tipo {@link GeoPosition}.
     *
     * @see GeoPosition
     */
    private Set<Waypoint> waypointSet = new HashSet<>();

    /**
     * waypointPainter: de tipo {@link WaypointPainter} pinta sobre el mapa las
     * posiciones recogidas en un conjunto de tipo {@link Waypoint}.
     *
     * @see WaypointPainter
     * @see Waypoint
     */
    private WaypointPainter<Waypoint> waypointPainter = new WaypointPainter<>();

    /**
     * artworkPosition: posicion del mapa de tipo {@link GeoPosition} sobre la
     * que se situa la obra.
     *
     * @see GeoPosition
     */
    private GeoPosition artworkPosition;

    /**
     * clickedPosition: posicion del mapa de tipo {@link GeoPosition} sobre la
     * que se ha realizado click derecho.
     *
     * @see GeoPosition
     */
    private GeoPosition clickedPosition;

    /**
     * track: lista que recoge las posiciones del click y de la obra para que
     * las recoja {@link RoutePainter}.
     *
     * @see RoutePainter
     */
    private List<GeoPosition> track;

    /**
     * mode: es un booleano que indica cual es el modo de juego.
     * {@link GameConstants#COMP_MODE} si es el modo competitivo y
     * {@link GameConstants#FREE_MODE} si es el modo libre.
     *
     * @see GameConstants
     */
    private boolean mode;

    /**
     * counter: las veces que se repetira el juego.
     */
    private int counter;

    /**
     * fails: es el contrador de fallos que posteriormente se anyadira al
     * tiempo.
     */
    private int fails;

    /**
     * timer: de tipo {@link Crono}, es el cronometro que marcara el tiempo.
     */
    private Crono timer;

    //CONSTRUCTOR
    /**
     * MapController: es el constructor del controlador. Para que funcione
     * correctamente necesita, la vista del juego, el controlador padre y a que
     * modo corresponde.
     *
     * @see GUIMap
     * @see GameControllers
     * @see MainController
     * @see SelectGameController
     * @see GameConstants
     *
     * @param view la vista del juego {@link GUIMuseumsTF}
     * @param parent el controlador padre {@link GameControllers}, que siempre
     * sera {@link MainController} o {@link SelectGameController}
     * @param mode un booleano que indica el modo de juego en el que se esta
     * llamando al controlador: {@link GameConstants#COMP_MODE} para el modo
     * competitivo y {@link GameConstants#FREE_MODE} para el modo libre.
     */
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

    /**
     * openMenu: muestra el menu inicial de la aplicacion.
     */
    private void openMenu() {
        if (parent instanceof SelectGameController parentC) {
            parentC.getMainController().getView().setVisible(true);
        }
    }

    /**
     * closeParentView: cierra la ventana del controlador padre.
     */
    private void closeParentView() {
        if (parent instanceof SelectGameController parentC) {
            parentC.getView().setVisible(false);
        } else if (parent instanceof MainController parentC) {
            parentC.getView().setVisible(false);
        }
    }

    /**
     * getGameData: recoge los datos del controlador padre, que son los datos de
     * la partida.
     */
    private void getGameData() {
        if (parent instanceof MainController parentC) {
            this.fails = parentC.getFails();
            this.timer = parentC.getTimer();
        }
    }

    /**
     * setGameData: actualiza los datos del controlador padre para que los
     * siguientes juegos puedan acceder a ellos.
     */
    private void setGameData() {
        if (parent instanceof MainController parentC) {
            parentC.setFails(fails);
        }
    }

    /**
     * addListener: anyade a los componentes de la ventana los escuchadores para
     * manejar las acciones del usuario.
     *
     * @see ActionListener
     * @see MouseListener
     */
    private void addListeners() {
        view.getConfirmButton().addActionListener(this);
        view.getMapKit().getMainMap().addMouseListener(mouseAdapterOne);
        view.getArtworkImage().addMouseListener(mouseAdapterTwo);
    }

    /**
     * initGame: anyade datos a la ventana y permite el juego.
     */
    private void initGame() {
        Collections.sort(repeatedDB);
        try {
            //mientras coincida la obra recogida y la lista de obras repetidas recoger obras de la base de datos
            do {
                artworkSolution = awDAO.selectNum(1).get(0);
                if (Collections.binarySearch(repeatedDB, artworkSolution.getId_obra()) < 0) {
                    repeatedDB.add(artworkSolution.getId_obra());
                }
            } while (Collections.binarySearch(repeatedDB, artworkSolution.getId_obra()) >= 0);

            //asignacion de parametros a las JLabel de la vista
            view.getArtworkLabel().setText(artworkSolution.getNombre_obra());
            authorSolution = atDAO.selectId(artworkSolution.getId_autor());
            view.getAuthorLabel().setText(authorSolution.getNombre_autor());
            setIcon();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(view, "Error de sintaxis", "Error", JOptionPane.ERROR_MESSAGE);;
        }
        //asignacion de posicion y de pais de la obra
        artworkPosition = new GeoPosition(artworkSolution.getLatitud(), artworkSolution.getLongitud());
        artworkCountry = CountryExtractor.getCountryName(artworkSolution.getLatitud(), artworkSolution.getLongitud());
    }

    /**
     * setIcon: metodo que coloca una imagen proporcional que consigue de la
     * base de datos. Utiliza la utilidad de
     * {@link ImagesSize#getProportionalDimensionImage(javax.swing.ImageIcon, java.awt.Dimension)}
     *
     * @see ImagesSize
     */
    private void setIcon() {
        ImageIcon imageIcon = null;
        try {
            imageIcon = new ImageIcon(new URL(artworkSolution.getImagen_obra()));
        } catch (MalformedURLException ex) {
            JOptionPane.showMessageDialog(view, "URL no encontrada", "Error", JOptionPane.ERROR_MESSAGE);
        }
        Image proportionalImage = ImagesSize.getProportionalDimensionImage(imageIcon, view.getArtworkImage().getSize(), false);
        view.getArtworkImage().setIcon(new ImageIcon(proportionalImage));
    }

    /**
     * setArtworkPosition: anyade la posicion de la obra a las colecciones de
     * {@link GeoPosition} y de {@link Waypoint} y utiliza
     * {@link WaypointPainter} para pintarla.
     *
     * @see GeoPosition
     * @see Waypoint
     * @see WaypointPainter
     */
    private void setArtworkPosition() {
        positionSet.add(artworkPosition);
        waypointSet.add(new DefaultWaypoint(artworkPosition));
        waypointPainter.setWaypoints(waypointSet);
    }

    /**
     * initTimer: espera 2 segundos para vaciar el contenido pintado sobre el
     * mapa.
     */
    public void initTimer() {
        Timer t = new Timer(2000, new ActionListener() {
            /**
             * actionPerformed: vacia los conjuntos de posiciones de tipo
             * {@link GeoPosition} y situa el pintor del mapa a nulo.
             *
             * @see GeoPosition
             * @see Painter
             *
             * @param ActionEvent
             */
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

    /**
     * guessedRight: si se acierta el pais de la obra aparecera un
     * {@link ResultDialog} con el texto "Correcto".
     *
     * @see ResultDialog
     */
    private void guessedRight() {
        ResultDialog rd = new ResultDialog(view, true);
        rd.initTimer();
        rd.setVisible(true);
    }

    /**
     * guessedWrong: si no se acierta el pais de la obra aparecera un
     * {@link ResultDialog} con el texto "Incorrecto".
     *
     * @see ResultDialog
     */
    private void guessedWrong() {
        ResultDialog rd = new ResultDialog(view, false);
        rd.initTimer();
        rd.setVisible(true);
        fails++;
    }

    /**
     * createRoute: hace zoom sobre el conjunto de posiciones de tipo
     * {@link GeoPosition}, crea una lista de posiciones de tipo
     * {@link GeoPosition} y las pasa como parametro al pintor de tipo
     * {@link RoutePainter}.
     * <br>
     * Crea otra lista de tipo {@link List} de {@link Painter} a la que anyade
     * {@link RoutePainter} y {@link WaypointPainter}. Dicha lista es utilizada
     * para poder crear un pintor de tipo {@link CompoundPainter} el cual sera
     * el pintor del mapa.
     *
     * @see GeoPosition
     * @see Painter
     * @see RoutePainter
     * @see WaypointPainter
     * @see CompoundPainter
     */
    private void createRoute() {
        setArtworkPosition();
        view.getMapKit().getMainMap().zoomToBestFit(positionSet, 0.7);
        track = Arrays.asList(artworkPosition, clickedPosition);
        RoutePainter routePainter = new RoutePainter(track);
        List<Painter<JXMapViewer>> painters = new ArrayList<Painter<JXMapViewer>>();
        painters.add(routePainter);
        painters.add(waypointPainter);
        CompoundPainter<JXMapViewer> compPainter = new CompoundPainter<JXMapViewer>(painters);
        view.getMapKit().getMainMap().setOverlayPainter(compPainter);
    }

    /**
     * actionPerformed: pulsado el boton comprueba si los nombres de los paises
     * estan recogidos, si es asi, comprueba si el pais de la obra recogida y el
     * pais de la posicion del click son el mismo y muestra si es correcto o
     * incorrecto.
     *
     * @param e un {@link ActionEvent}
     */
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
            //llama a initGame si no se ha llegado a 10 intentos, si se ha llegado dependiendo del modo se llamara al siguiente juego o al menu
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

    /**
     * mouseAdapterOne: crea un {@link DefaultWaypoint} en la posicion del raton
     * sobre la que se ha realizado click derecho.
     *
     * @see DefaultWaypoint
     */
    private MouseAdapter mouseAdapterOne = new MouseAdapter() {
        /**
         * mouseClicked: al realizar click derecho limpia los conjuntos de
         * posiciones de tipo {@link GeoPosition}, anyade la posicion del raton
         * a las colecciones de {@link GeoPosition} y de {@link Waypoint},
         * utiliza {@link WaypointPainter} para pintarla y
         * {@link CountryExtractor} para recuperar el pais.
         *
         * @see GeoPosition
         * @see Waypoint
         * @see WaypointPainter
         * @see CountryExtractor
         *
         * @param e un {@link MouseEvent}
         */
        @Override
        public void mouseClicked(MouseEvent e) {
            try {
                if (e.getButton() == MouseEvent.BUTTON3) {
                    positionSet.clear();
                    waypointSet.clear();
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
                JOptionPane.showMessageDialog(view, "Debes seleccionar una posicion", "Error", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    };

    /**
     * mouseAdapterTwo: visualiza la imagen de la obra de forma ampliada en una
     * ventana de tipo {@link VisualizeImage}.
     */
    private MouseAdapter mouseAdapterTwo = new MouseAdapter() {
        /**
         * mouseClicked: al hacer doble click sobre la imagen de la obra se crea
         * una {@link VisualizeImage} que contiene la imagen en un tamanyo
         * ampliado el nombre de la obra y el autor.
         *
         * @param e un {@link MouseEvent}
         */
        @Override
        public void mouseClicked(MouseEvent e) {
            if (e.getClickCount() == 2) {
                VisualizeImage vi = new VisualizeImage(view, true);
                ImageIcon i = null;
                try {
                    i = new ImageIcon(new URL(artworkSolution.getImagen_obra()));
                } catch (MalformedURLException ex) {
                    JOptionPane.showMessageDialog(view, "URL no encontrada", "Error", JOptionPane.ERROR_MESSAGE);
                }
                vi.getImage().setIcon(new ImageIcon(ImagesSize.getProportionalDimensionImage(i,
                        new Dimension((int) (GameConstants.SCREEN_SIZE.width * 0.9), (int) (GameConstants.SCREEN_SIZE.height * 0.8)), false)));
                vi.getAuthor().setText(authorSolution.getNombre_autor());
                vi.getArtwork().setText(artworkSolution.getNombre_obra());
                vi.getContentPane().setPreferredSize(vi.getImage().getPreferredSize());
                vi.pack();
                vi.setVisible(true);
            }
        }
    };

    /**
     * launch: metodo que se implemente de {@link GameControllers}. En este
     * metodo se lanza el juego y se pone visible la ventana
     */
    @Override
    public void launch() {
        view.setVisible(true);
        closeParentView();
        initGame();
    }
}
