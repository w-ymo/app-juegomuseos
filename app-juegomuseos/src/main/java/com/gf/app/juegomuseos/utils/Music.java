/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gf.app.juegomuseos.utils;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JOptionPane;

/**
 * Music: en esta clase se dan las herramientas necesarias para introducir
 * musica al juego.
 *
 * @author priparno
 */
public class Music {

    /**
     * MUSIC: es el archivo donde esta song1.wav
     */
    private static final File MUSIC = new File(".\\src\\main\\java\\com\\gf\\app\\juegomuseos\\resources\\song1.wav");
    /**
     * input: el flujo de sonido
     */
    private static AudioInputStream input;
    /**
     * clip: representa el archivo
     */
    private static Clip clip;

    /**
     * start: inicia la musica.
     */
    public static void start() {
        try {
            //creo un nuevo flujo de datos
            input = AudioSystem.getAudioInputStream(MUSIC);
            //recojo el flujo para poder manipularlo
            clip = AudioSystem.getClip();
            clip.open(input);
            //va estar en bucle de manera indefinida
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            //inicia la cancion
            clip.start();
            setVolume(GameData.VOLUME);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException | IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(null, "Error de archivo de audio. Revise 'resources'", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * setVolume: metodo que da volumen al {@link #clip}
     *
     * @param value un float que representa el volumen (min -80f, max 6f)
     */
    public static void setVolume(float value) {
        FloatControl gainControl
                = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        gainControl.setValue(value);
    }

    /**
     * stop: para el flujo de musica.
     */
    public static void stop() {
        try {
            clip.stop();
            input.close();
        } catch (IOException ex) {
            System.err.println("Error de entrada salida.");
        }
    }

    /**
     * isValid: si no existe el archivo o esta corrupto no deja correr la
     * aplicacion.
     *
     * @return true -> el archivo es valido, false -> el archivo no es valido
     */
    public static boolean isValid() {
        if (MUSIC.exists()) {
            return MUSIC.length() > 0;
        } else {
            JOptionPane.showMessageDialog(null, "Error en los archivos de sonido. Revise 'resources'", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
}
