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
import javax.swing.Timer;

/**
 *
 * @author fercaslu
 */
public class MuseumsTFController implements ActionListener, GameControllers {

    private GameControllers parent;
    private GUIMuseumsTF view;
    private Random rndm = new Random();
    private MuseumDAO mDAO = new MuseumDAO();

    private boolean mode;

    private int counter;
    private int fails;
    private Crono timer;

    private boolean accessType; //true -> recoge de BD, false -> recoge de array
    private Museum museumSolution;

    private static final String[] FAKE_MUSEUMS = {"Museo de las Pastillas",
        "Museo de los Ladrillos", "Museo de las Vibraciones", "Museo Antropológico de Murcia",
        "Iglesia Don Rodrigo Díaz de Carreras", "Pinacoteca de Liberty City",
        "Galería de Arte de Central City", "Museo de Automoción Francesco Virgolini",
        "Museo de Escultura Samuel de Luque", "Museo de las Patas de Mueble"};

    private ArrayList<Integer> repeatedDB = new ArrayList<>();
    private ArrayList<Integer> repeatedFake = new ArrayList<>();

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

    private void openMenu() {
        if (parent instanceof SelectGameController parentC) {
            parentC.getMainController().getView().setVisible(true);
        }
    }

    private void closeParentView() {
        if (parent instanceof SelectGameController parentC) {
            parentC.getView().setVisible(false);
        }else if(parent instanceof MainController parentC){
            parentC.getView().setVisible(false);
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
        accessType = rndm.nextBoolean();
        museumSolution = new Museum();
        if (accessType) {
            Collections.sort(repeatedDB);
            do {
                try {
                    museumSolution = mDAO.selectNum(1).get(0);
                    if (Collections.binarySearch(repeatedDB, museumSolution.getId_museo()) < 0) {
                        repeatedDB.add(museumSolution.getId_museo());
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            } while (Collections.binarySearch(repeatedDB, museumSolution.getId_museo()) >= 0);

        } else {
            boolean found;
            do {
                int index = rndm.nextInt(0, FAKE_MUSEUMS.length);
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

    private void addListenerButtons() {
        view.getTrueButton().addActionListener(this);
        view.getFalseButton().addActionListener(this);
    }

    private void guessedRight(JButton button) {
        ResultDialog rd = new ResultDialog(view, true);
        rd.initTimer();
        rd.setVisible(true);
    }

    private void setLabelLength() {
        FontMetrics fm = view.getMuseumLabel()
                .getFontMetrics(view.getMuseumLabel().getFont());

        int labelWidth = fm.stringWidth(view.getMuseumLabel().getText());

        view.getMuseumLabel().setPreferredSize(
                new Dimension(labelWidth, view.getMuseumLabel().getPreferredSize().height));
        view.getMuseumLabel().revalidate();
    }

    private void guessedWrong(JButton button) {
        JButton correctButton = null;
        if (button.equals(view.getTrueButton())) {
            correctButton = view.getFalseButton();
        } else if (button.equals(view.getFalseButton())) {
            correctButton = view.getTrueButton();
        }
        ResultDialog rd = new ResultDialog(view, false);
        rd.initTimer();
        rd.setVisible(true);
        fails++;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton b = (JButton) e.getSource();
        boolean gtOne = museumSolution.getId_museo() >= 1;
        if (b.equals(view.getTrueButton())) {
            if (gtOne) {
                guessedRight(b);
            } else {
                guessedWrong(b);
            }
        } else {
            if (!gtOne) {
                guessedRight(b);
            } else {
                guessedWrong(b);
            }
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
    }

    @Override
    public void launch() {
        view.setVisible(true);
        closeParentView();
        initGame();
    }

}
