/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gf.app.juegomuseos.controller;

import com.gf.app.juegomuseos.dao.MuseumDAO;
import com.gf.app.juegomuseos.models.Museum;
import com.gf.app.juegomuseos.utils.Crono;
import com.gf.app.juegomuseos.utils.GameConstants;
import com.gf.app.juegomuseos.views.GUIGregorioFernandez;
import com.gf.app.juegomuseos.views.GUIMuseumsTF;
import java.awt.Dimension;
import java.awt.FontMetrics;
import com.gf.app.juegomuseos.views.ResultDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JOptionPane;

/**
 * MuseumsTFController: es el controlador de la ventana {@link MuseumsTFController}.
 * Desde ella se lanza el juego y se controlan los fallos y aciertos. Implementa de
 * {@link GameControllers}. Se muestra una ventana con 2 botones y el nombre del museo. 
 * El objetivo adivinar si existe o no el museo.
 * 
 * @see GUIMuseumsTF
 * @see GameControllers
 * 
 * @author fercaslu
 */
public class MuseumsTFController implements ActionListener, GameControllers {

    /**
     * view: es la vista del juego
     */
    private GUIMuseumsTF view;
    
    /**
     * parent: es el controlador padre, el que le llama.
     */
    private GameControllers parent;
    
    /**
     * mDAO: es la clase de acceso a la base de datos para obtener los museos.
     */
    private MuseumDAO mDAO = new MuseumDAO();

    /**
     * rndm: sirve para elegir de donde se obtiene el museo. Si elige la obtención
     * desde el array de los museos falsos también indicará la posición en dicho array.
     */
    private Random rndm = new Random();
    
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
     * fails: es el contrador de fallos que posteriormente se anyadira al tiempo.
     */
    private int fails;
    
    /**
     * timer: de tipo {@link Crono}, es el cronometro que marcara el tiempo.
     */
    private Crono timer;

    /**
     * accessType: booleano que indica de donde recoge el museo.
     * <br>
     * true -> recoge el museo de la base de datos.
     * <br>
     * false -> recoge el museo del array de museos falsos.
     */
    private boolean accessType;
    
    /**
     * museumSolution: de tipo {@link Museum}, museo recogido con el que se 
     * comprobara si existe o no.
     * 
     * @see Museum
     */
    private Museum museumSolution;

    /**
     * FAKE_MUSEUMS: array de nombres de museos falsos.
     */
    private static final String[] FAKE_MUSEUMS = {"Museo de las Pastillas",
        "Museo de los Ladrillos", "Museo de las Vibraciones", "Museo Antropológico de Murcia",
        "Iglesia Don Rodrigo Díaz de Carreras", "Pinacoteca de Liberty City",
        "Galería de Arte de Central City", "Museo de Automoción Francesco Virgolini",
        "Museo de Escultura Samuel de Luque", "Museo de las Patas de Mueble"};

    /**
     * repeatedDB: lista de enteros que almacena el id del museo recogido de la 
     * base de datos.
     */
    private ArrayList<Integer> repeatedDB = new ArrayList<>();
    
    /**
     * repeatedFake: lista de enteros que almacena la posicion en el array de 
     * museos falsos.
     */
    private ArrayList<Integer> repeatedFake = new ArrayList<>();

    //CONSTRUCTOR
    /**
     * MuseumsTFController: es el constructor del controlador. Para que funcione 
     * correctamente necesita, la vista del juego, el controlador padre 
     * y a que modo corresponde.
     * 
     * @see GUIMuseumsTF
     * @see GameControllers
     * @see MainController
     * @see SelectGameController
     * @see GameConstants
     * 
     * @param view la vista del juego {@link GUIMuseumsTF}
     * @param parent el controlador padre {@link GameControllers}, que siempre
     * sera {@link MainController} o {@link SelectGameController}
     * @param mode un booleano que indica el modo de juego en el que se esta 
     * llamando al controlador:
     * {@link GameConstants#COMP_MODE} para el modo competitivo y
     * {@link GameConstants#FREE_MODE} para el modo libre.
     */
    public MuseumsTFController(GUIMuseumsTF view, GameControllers parent, boolean mode) {
        this.view = view;
        this.counter = 0;
        this.mode = mode;
        this.parent = parent;
        getGameData();
        if (mode == GameConstants.COMP_MODE) {
            this.timer.setTextTime(view.getTextTime());
        }
        addListenerButtons();
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
        }else if(parent instanceof MainController parentC){
            parentC.getView().setVisible(false);
        }
    }
    
    /**
     * getGameData: recoge los datos del controlador padre, que son los datos de
     * la partida
     */
    private void getGameData() {
        if (parent instanceof MainController parentC) {
            this.fails = parentC.getFails();
            this.timer = parentC.getTimer();
        }
    }
    
    /**
     * setGameData: actualiza los datos del controlador padre para que los 
     * siguientes juegos puedan acceder a ellos
     */
    private void setGameData() {
        if (parent instanceof MainController parentC) {
            parentC.setFails(fails);
        }
    }
    
    /**
     * addListenerButtons: anyade a los botones de la ventana los escuchadores 
     * para manejar las acciones del usuario.
     * 
     * @see ActionListener
     */
    private void addListenerButtons() {
        view.getExistButton().addActionListener(this);
        view.getNotExistButton().addActionListener(this);
    }

    /**
     * initGame: anyade datos a la ventana y permite el juego.
     */
    private void initGame() {
        //asigna un nuevo booleano
        accessType = rndm.nextBoolean();
        museumSolution = new Museum();
        //recoge y asigna un objeto museo completo desde la base de datos o recoge y asigna unicamente el nombre de un museo desde el array de nombres
        if (accessType) {
            Collections.sort(repeatedDB);
            do {
                try {
                    museumSolution = mDAO.selectNum(1).get(0);
                    if (Collections.binarySearch(repeatedDB, museumSolution.getId_museo()) < 0) {
                        repeatedDB.add(museumSolution.getId_museo());
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(view, "Error de sintaxis", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } while (Collections.binarySearch(repeatedDB, museumSolution.getId_museo()) >= 0);

        } else {
            //found: bandera true -> nombre de museo repetido, false -> nombre de museo no repetido
            boolean found;
            do {
                int index = rndm.nextInt(0, FAKE_MUSEUMS.length);
                //comprueba si el nombre recogido esta repetido
                if (!repeatedFake.contains(index)) {
                    museumSolution.setNombre_museo(FAKE_MUSEUMS[index]);
                    repeatedFake.add(index);
                    found = false;
                } else {
                    found = true;
                }
            } while (found);
        }
        view.getMuseumLabel().setText(museumSolution.getNombre_museo());
        setLabelLength();
    }

    /**
     * setLabelLength: cambia el tamanyo del JLabel nombre del museo dependiendo de su longitud.
     */
    private void setLabelLength() {
        FontMetrics fm = view.getMuseumLabel()
                .getFontMetrics(view.getMuseumLabel().getFont());

        int labelWidth = fm.stringWidth(view.getMuseumLabel().getText());

        view.getMuseumLabel().setPreferredSize(
                new Dimension(labelWidth, view.getMuseumLabel().getPreferredSize().height));
        view.getMuseumLabel().revalidate();
    }
    
    /**
     * guessedRight: si el boton pulsado es el correcto aparecera un 
     * {@link ResultDialog} con el texto "Correcto".
     * 
     * @see ResultDialog
     */
    private void guessedRight(JButton button) {
        ResultDialog rd = new ResultDialog(view, true);
        rd.initTimer();
        rd.setVisible(true);
    }
    
    /**
     * guessedWrong: si el boton pulsado es el incorrecto aparecera un 
     * {@link ResultDialog}  con el texto "Incorrecto".
     * 
     * @see ResultDialog
     */
    private void guessedWrong(JButton button) {
        JButton correctButton = null;
        if (button.equals(view.getExistButton())) {
            correctButton = view.getNotExistButton();
        } else if (button.equals(view.getNotExistButton())) {
            correctButton = view.getExistButton();
        }
        ResultDialog rd = new ResultDialog(view, false);
        rd.initTimer();
        rd.setVisible(true);
        fails++;
    }

    /**
     * actionPerformed: cuando se presiona uno de los botones, si coincide con 
     * la solucion mostrara un mensaje correcto y si no mostrara uno incorrecto.
     * El metodo para deducir cual es ccrrecto es comprobar si ese museo existe 
     * en la base de datos, es decir, que su id sea mayor o igual que 1.
     * 
     * @param ActionEvent
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton b = (JButton) e.getSource();
        boolean greaterThanOne = museumSolution.getId_museo() >= 1;
        if (b.equals(view.getExistButton())) {
            if (greaterThanOne) {
                guessedRight(b);
            } else {
                guessedWrong(b);
            }
        } else {
            if (!greaterThanOne) {
                guessedRight(b);
            } else {
                guessedWrong(b);
            }
        }
        counter++;
        //llama a initGame si no se ha llegado a 10 intentos, si se ha llegado dependiendo del modo se llamara al siguiente juego o al menu
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
    }

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
