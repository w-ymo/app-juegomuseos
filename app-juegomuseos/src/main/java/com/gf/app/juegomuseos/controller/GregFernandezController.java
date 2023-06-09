/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gf.app.juegomuseos.controller;

import com.gf.app.juegomuseos.dao.ArtworkDAO;
import com.gf.app.juegomuseos.dao.AuthorDAO;
import com.gf.app.juegomuseos.models.Artwork;
import com.gf.app.juegomuseos.utils.Crono;
import com.gf.app.juegomuseos.utils.GameConstants;
import com.gf.app.juegomuseos.utils.ImagesSize;
import com.gf.app.juegomuseos.views.GUIGregorioFernandez;
import com.gf.app.juegomuseos.views.GUIMap;
import com.gf.app.juegomuseos.views.ResultDialog;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;

/**
 * GregFernandezController: es el controlador de la ventana
 * {@link GUIGregorioFernandez}. Desde este se lanza el juego y se controlan los
 * fallos y aciertos. Implementa de {@link GameControllers}. Se muestra una
 * ventana con dos botones donde hay una imagen en cada uno. El objetivo es
 * seleccionar la imagen de la obra perteneciente a Gregorio Fernandez.
 *
 * @see GUIGregorioFernandez
 * @see GameControllers
 *
 * @author priparno
 */
public class GregFernandezController implements GameControllers {

    /**
     * view: es la vista del juego.
     */
    private GUIGregorioFernandez view;
    /**
     * parent: es el controlador padre, el que le llama.
     */
    private GameControllers parent;

    /**
     * awDAO: es la clase de acceso a la base de datos para obtener las obras de
     * arte.
     *
     * @see ArtworkDAO
     */
    private ArtworkDAO awDAO = new ArtworkDAO();
    /**
     * atDAO: es la clase de acceso a la base de datos para obtener los autores.
     *
     * @see AuthorDAO
     */
    private AuthorDAO atDAO = new AuthorDAO();

    /**
     * counter: es el contador de las veces que se repetira el juego.
     */
    private int counter;
    /**
     * fails: es el contador de fallos que posteriormente se aniadira al tiempo.
     */
    private int fails;
    /**
     * timer: de tipo {@link Crono}, es el cronometro que nos marcará el tiempo.
     */
    private Crono timer;
    /**
     * mode: es un booleano que indica cual es el modo de juego.
     * {@link GameConstants#COMP_MODE} si es el modo competitivo y
     * {@link GameConstants#FREE_MODE} si es el modo libre.
     */
    private boolean mode;

    /**
     * solution: es la solucion, es decir, la obra de Gregorio Fernandez.
     */
    private Artwork solution;
    /**
     * gregorioArtwork: recoge todas las obras de Gregorio Fernandez para no
     * repetir.
     */
    private List<Artwork> gregorioArtwork;

    /**
     * listenerButtons: es el escuchador de accion de los botones. De estos se
     * coge el nombre del botón y si coincide con {@link #solution} da por bueno
     * el intento y lanza otro.
     */
    private ActionListener listenerButtons = (e) -> {
        JButton but = (JButton) e.getSource();
        try {
            //dependiendo de si la solucion es correcta o no, muestra un JDialog por 1 segundo que indica correcto o incorrecto.
            if (but.getName().equals(String.valueOf(atDAO.getIdGregorioFernandez()))) {
                ResultDialog rd = new ResultDialog(view, true);
                rd.initTimer();
                rd.setVisible(true);
            } else {
                ResultDialog rd = new ResultDialog(view, false);
                rd.initTimer();
                rd.setVisible(true);
                fails++;
            }
            counter++;
            updateRound();
            //se repite el juego hasta que pasen 5 rondas.
            if (counter < 5) {
                initGame();
            } else {
                //se actualiza los valores globales del intento si es modo competitivo y pasa al siguiente juego, en este caso MapController.
                if (mode == GameConstants.COMP_MODE) {
                    setGameData();
                    MapController mc = new MapController(new GUIMap(), parent, GameConstants.COMP_MODE);
                } else {
                    //si no es modo competitivo vuelve a mostrar el menu.
                    openMenu();
                }
                view.dispose();
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(view, "Error de sintaxis", "Error", JOptionPane.ERROR_MESSAGE);
        }
    };

    //CONSTRUCTOR
    /**
     * GregFernandezController: es el constructor del controlador. Para que
     * funcione correctamente, necesita la vista del juego, el controlador padre
     * y a que modo corresponde. En el controlador además se lanza la vista, es
     * decir, se muestra la vista automáticamente.
     *
     * @see GUIGregorioFernandez
     * @see GameControllers
     * @see MainController
     * @see SelectGameController
     * @see GameConstants
     *
     * @param view la vista del juego {@link GUIGregorioFernandez}
     * @param parent el controlador padre {@link GameControllers}, que siempre
     * sera {@link MainController} o {@link SelectGameController}
     * @param mode un booleano que indica en que modo de juego estamos:
     * {@link GameConstants#COMP_MODE} para el modo competitivo y
     * {@link GameConstants#FREE_MODE}
     */
    public GregFernandezController(GUIGregorioFernandez view, GameControllers parent, boolean mode) {
        this.view = view;
        this.parent = parent;
        this.mode = mode;
        this.counter = 0;
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
     * closeParentView: cierro la ventana del controlador padre.
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
     * addListeners: aniade a los elementos de la ventana los escuchadores para
     * manejar las acciones del usuario.
     *
     * @see ActionListener
     */
    private void addListeners() {
        for (JButton option : view.getImages()) {
            option.addActionListener(listenerButtons);
        }
    }

    /**
     * initGame: aniade datos a la ventana y permitir el juego.
     */
    private void initGame() {
        try {
            updateRound();
            solution = gregorioArtwork.get(0);
            //elimina de la lista con todas las obras para no repetir.
            gregorioArtwork.remove(0);
            List<Artwork> artworksNames = new ArrayList<>();
            artworksNames.add(solution);
            //el campo clave sera el de la solucion.
            List<Artwork> fakeArtwork = awDAO.selectSimilar(solution.getClave_obra(), atDAO.getIdGregorioFernandez());
            Collections.shuffle(fakeArtwork);
            artworksNames.add(fakeArtwork.get(0));
            Collections.shuffle(artworksNames);
            for (int i = 0; i < view.getImages().size(); i++) {
                setIcon(artworksNames.get(i).getImagen_obra(), view.getImages().get(i));
                view.getImages().get(i).setName(String.valueOf(artworksNames.get(i).getId_autor()));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(view, "Error de sintaxis", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * setIcon: metodo que coloca una imagen proporcional que consigue de la url
     * pasada como parametro. Utilizo la utilidad de
     * {@link ImagesSize#getProportionalDimensionImage(javax.swing.ImageIcon, java.awt.Dimension)}
     *
     * @see ImagesSize
     *
     * @param url la url de la imagen.
     * @param image el boton donde se situa la imagen.
     */
    public void setIcon(String url, JButton image) {
        ImageIcon i = null;
        try {
            i = new ImageIcon(new URL(url));
        } catch (MalformedURLException ex) {
            JOptionPane.showMessageDialog(view, "URL no encontrada", "Error", JOptionPane.ERROR_MESSAGE);
        }
        Image proportionalImage = ImagesSize.getProportionalDimensionImage(i,
                new Dimension(GameConstants.SCREEN_SIZE.width / 2, view.getPanelImages().getSize().height), true);
        image.setIcon(new ImageIcon(proportionalImage));
    }
    
    /**
     * updateRound: actualiza en la ventana el contador de rondas.
     */
    private void updateRound() {
        view.getRoundText().setText("RONDAS: " + (counter + 1) + "/5");
    }

    /**
     * launch: metodo que se implementa de {@link GameControllers}. En este
     * metodo se lanza el juego y se pone visible la ventana.
     */
    @Override
    public void launch() {
        try {
            gregorioArtwork = awDAO.selectIdAuthor(atDAO.getIdGregorioFernandez());
            Collections.shuffle(gregorioArtwork);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(view, "Error de sintaxis", "Error", JOptionPane.ERROR_MESSAGE);
        }
        initGame();
        closeParentView();
        view.setVisible(true);
    }
}
