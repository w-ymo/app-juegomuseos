/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gf.app.juegomuseos.controller;

import com.gf.app.juegomuseos.dao.MuseumDAO;
import com.gf.app.juegomuseos.models.Museum;
import com.gf.app.juegomuseos.views.GUIMuseumsTF;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JOptionPane;

/**
 *
 * @author fercaslu
 */
public class MuseumsTFController implements ActionListener {

    GUIMuseumsTF view;
    Random rndm = new Random();
    MuseumDAO mDAO = new MuseumDAO();

    private int counter;
    private int fails;
    private boolean proceed = true;

    private boolean accessType; //true -> recoge de BD, false -> recoge de array
    private Museum mSolution;

    private static final String[] FAKE_MUSEUMS = {"Museo de las Pastillas",
        "Museo de los Ladrillos", "Museo de las Vibraciones", "Museo Antropológico de Murcia",
        "Iglesia Don Rodrigo Díaz de Carreras", "Pinacoteca de Liberty City",
        "Galería de Arte de Central City", "Museo de Automoción Francesco Virgolini",
        "Museo de Escultura Samuel de Luque", "Museo de las Patas de Mueble"};

    private ArrayList<Integer> repeatedDB = new ArrayList<>();
    private ArrayList<Integer> repeatedFake = new ArrayList<>();

    public MuseumsTFController(GUIMuseumsTF view) {
        this.view = view;
        this.counter = 0;
        this.fails = 0;
        addListenerButtons();
        launchGame();
    }

    private void initGame() {
        accessType = rndm.nextBoolean();
        proceed = false;
        mSolution = new Museum();
        if (accessType) {
            Collections.sort(repeatedDB);
            do {
                try {
                    mSolution = mDAO.selectNum(1).get(0);
                    if (Collections.binarySearch(repeatedDB, mSolution.getId_museo()) < 0) {
                        repeatedDB.add(mSolution.getId_museo());
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            } while (Collections.binarySearch(repeatedDB, mSolution.getId_museo()) >= 0);

        } else {
            boolean found;
            do {
                int index = rndm.nextInt(0, FAKE_MUSEUMS.length);
                if (!repeatedFake.contains(index)) {
                    mSolution.setNombre_museo(FAKE_MUSEUMS[index]);
                    repeatedFake.add(index);
                    found = false;
                } else {
                    found = true;
                }
            } while (found);
        }
        view.getMuseumLabel().setText(mSolution.getNombre_museo());
        setLabelLength();
    }

    private void launchGame() {
        view.setVisible(true);
        while (counter <= 10) {
            if (proceed) {
                initGame();
            }
        }
        view.setVisible(false);
        JOptionPane.showMessageDialog(null, "Siguiente juego", "Juego terminado", JOptionPane.INFORMATION_MESSAGE);
    }

    private void addListenerButtons() {
        view.getTrueButton().addActionListener(this);
        view.getFalseButton().addActionListener(this);
    }

    private void guessedRight(JButton button) {
        try {
            Thread.sleep(2000);
            //JDialog
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
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
        try {
            JButton correctButton = null;

            if (button.equals(view.getTrueButton())) {
                correctButton = view.getFalseButton();
            } else if (button.equals(view.getFalseButton())) {
                correctButton = view.getTrueButton();
            }
            //JDialog
            Thread.sleep(2000);

            fails++;
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton b = (JButton) e.getSource();
        boolean gtOne = mSolution.getId_museo() >= 1;
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
        proceed = true;
        counter++;
    }

}
