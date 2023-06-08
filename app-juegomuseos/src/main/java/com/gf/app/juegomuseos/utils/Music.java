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

/**
 *
 * @author noelp
 */
public class Music {

    private static final File MUSIC = new File(".\\src\\main\\java\\com\\gf\\app\\juegomuseos\\resources\\song1.wav");
    private static AudioInputStream input;
    private static Clip clip;

    public static void start() {
        try {
            input = AudioSystem.getAudioInputStream(MUSIC);
            clip = AudioSystem.getClip();
            clip.open(input);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
            System.err.println("Problemitas");
        }
    }

    private static void setGain(float value) {
        FloatControl gainControl
                = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        gainControl.setValue(value); // Reduce volume by 10 decibels.
    }

    public static void setVolume(int value) {
        float min = -80;
        float max = 6;
        setGain(value);
    }

    public static void stop() {
        try {
            clip.stop();
            input.close();
        } catch (IOException ex) {
            System.err.println("Error");
        }
    }
}
