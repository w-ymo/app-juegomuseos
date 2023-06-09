/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gf.app.juegomuseos.controller;

import com.gf.app.juegomuseos.dao.ArtworkDAO;
import com.gf.app.juegomuseos.dao.AuthorDAO;
import com.gf.app.juegomuseos.models.Artwork;
import com.gf.app.juegomuseos.models.Author;
import com.gf.app.juegomuseos.utils.Crono;
import com.gf.app.juegomuseos.utils.GameConstants;
import com.gf.app.juegomuseos.utils.ImagesSize;
import com.gf.app.juegomuseos.views.GUIMuseumsTF;
import com.gf.app.juegomuseos.views.GUIWhoIs;
import com.gf.app.juegomuseos.views.ResultDialog;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
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
 * WhoIsController: es el controlador de la ventana {@link GUIWhoIs}. Desde este
 * se lanza el juego y se controlan los fallos y aciertos. Implementa de
 * {@link GameControllers}. Se muestra una ventana con 4 botones y una imagen.
 * El objetivo es pulsar el boton que corresponda al autor de la imagen
 * mostrada.
 *
 * @see GUIWhoIs
 * @see GameControllers
 *
 * @author priparno
 */
public class WhoIsController implements GameControllers {

    /**
     * view: es la vista del juego.
     */
    private GUIWhoIs view;
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
     * imageSelected: es la obra de arte que se selecciona.
     */
    private Artwork imageSelected;
    /**
     * solution: es la solucion, es decir, el autor de la obra seleccionada.
     */
    private Author solution;

    /**
     * repeatedDB: es una lista que almacena las obras que ya se han mostrado
     * para evitar repeticiones.
     */
    private List<Integer> repeatedDB = new ArrayList<>();

    /**
     * listenerButtons: es el escuchador de accion de los botones. De estos se
     * coge el texto del botón y si coincide con {@link #solution} da por bueno
     * el intento y lanza otro.
     */
    private ActionListener listenerButtons = (e) -> {
        JButton but = (JButton) e.getSource();
        int atId = solution.getId_autor();
        try {
            Author at = atDAO.selectId(atId);
            //dependiendo de si la solucion es correcta o no, muestra un JDialog por 1 segundo que indica correcto o incorrecto.
            if (but.getText().equals(at.getNombre_autor())) {
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
            //se repite el juego hasta que pasen 10 rondas.
            if (counter < 10) {
                initGame();
            } else {
                //se actualiza los valores globales del intento si es modo competitivo y pasa al siguiente juego, en este caso MuseumsTFController.
                if (mode == GameConstants.COMP_MODE) {
                    setGameData();
                    MuseumsTFController nextGame = new MuseumsTFController(new GUIMuseumsTF(), parent, GameConstants.COMP_MODE);
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
     * WhoIsController: es el constructor del controlador. Para que funcione
     * correctamente, necesita la vista del juego, el controlador padre y a que
     * modo corresponde. En el controlador además se lanza la vista, es decir,
     * se muestra la vista automáticamente.
     *
     * @see GUIWhoIs
     * @see GameControllers
     * @see MainController
     * @see SelectGameController
     * @see GameConstants
     *
     * @param view la vista del juego {@link GUIWhoIs}
     * @param parent el controlador padre {@link GameControllers}, que siempre
     * sera {@link MainController} o {@link SelectGameController}
     * @param mode un booleano que indica en que modo de juego estamos:
     * {@link GameConstants#COMP_MODE} para el modo competitivo y
     * {@link GameConstants#FREE_MODE}
     */
    public WhoIsController(GUIWhoIs view, GameControllers parent, boolean mode) {
        this.view = view;
        this.parent = parent;
        this.mode = mode;
        this.counter = 0;
        //recogemos los datos globales de la partida
        getGameData();
        //si es modo competitivo se incluirá el cronometro
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
     * @see MouseAdapter
     */
    private void addListeners() {
        for (JButton option : view.getOptions()) {
            option.addActionListener(listenerButtons);
        }
    }

    /**
     * initGame: aniade datos a la ventana y permitir el juego.
     */
    private void initGame() {
        try {
            //ordena la lista
            Collections.sort(repeatedDB);
            //busca en la base de datos hasta que consiga uno que no haya salido.
            do {
                try {
                    imageSelected = awDAO.selectNum(1).get(0);
                    if (Collections.binarySearch(repeatedDB, imageSelected.getId_obra()) < 0) {
                        repeatedDB.add(imageSelected.getId_obra());
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(view, "Error de sintaxis", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } while (Collections.binarySearch(repeatedDB, imageSelected.getId_obra()) >= 0);
            //actualiza en la vista los datos de la obra.
            view.getImageText().setText(imageSelected.getNombre_obra());
            solution = atDAO.selectId(imageSelected.getId_autor());
            //esta lista recogera los autores que se mostraran en los botones de la ventana.
            List<Author> authorsNames = new ArrayList<>();
            authorsNames.add(solution);
            //distinto de la solucion coge 3 mas.
            authorsNames.addAll(atDAO.selectNotEquals(solution.getId_autor(), 3));
            //desordena para que se muestre en un orden distinto cada vez.
            Collections.shuffle(authorsNames);
            for (int i = 0; i < view.getOptions().size(); i++) {
                view.getOptions().get(i).setText(authorsNames.get(i).getNombre_autor());
            }
            setIcon();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(view, "Error de sintaxis", "Error", JOptionPane.ERROR_MESSAGE);
            counter = 15;
        }
    }

    /**
     * setIcon: metodo que coloca una imagen proporcional que consigue de la
     * base de datos. Utiliza la utilidad de {@link ImagesSize#getProportionalDimensionImage(javax.swing.ImageIcon, java.awt.Dimension)
     * }
     *
     * @see ImagesSize
     */
    private void setIcon() {
        //aniade la imagen
        ImageIcon i = null;
        try {
            //pone url de imageSelected
            i = new ImageIcon(new URL(imageSelected.getImagen_obra()));
        } catch (MalformedURLException ex) {
            JOptionPane.showMessageDialog(view, "URL no encontrada", "Error", JOptionPane.ERROR_MESSAGE);
        }
        //consigue una imagen que mantiene la relacion de aspecto que la original pero ocupa el mayor espacio posible
        Image proportionalImage = ImagesSize.getProportionalDimensionImage(i, view.getPanelImages().getSize(), false);
        view.getImage().setIcon(new ImageIcon(proportionalImage));
    }

    /**
     * launch: metodo que se implementa de {@link GameControllers}. En este
     * metodo se lanza el juego y se pone visible la ventana.
     */
    @Override
    public void launch() {
        initGame();
        closeParentView();
        view.setVisible(true);
    }
}
