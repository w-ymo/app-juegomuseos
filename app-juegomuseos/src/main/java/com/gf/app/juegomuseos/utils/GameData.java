/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gf.app.juegomuseos.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author noelp
 */
public class GameData {

    private static File infoHTML = new File(".\\src\\main\\java\\com\\gf\\app\\juegomuseos\\resources\\AppInfo.html");

    private static File infoStyle = new File(".\\src\\main\\java\\com\\gf\\app\\juegomuseos\\resources\\AppSettings.txt");

    public final static String DARK_LAF = "com.formdev.flatlaf.intellijthemes.FlatArcOrangeIJTheme";

    public final static String LIGHT_LAF = "com.formdev.flatlaf.intellijthemes.FlatArcDarkOrangeIJTheme";

    public final static String STYLE = getInfoStyle()[1];

    public static String getInfoHtml() {
        String html = "";
        if (infoHTML == null) {
            System.out.println("NO EXISTE");
            return null;
        }
        try ( BufferedReader br = new BufferedReader(new FileReader(infoHTML))) {
            String sentence;
            while ((sentence = br.readLine()) != null) {
                html += sentence + "\n";
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return html;
    }

    public static String[] getInfoStyle() {
        String[] settings = new String[2];
        if (infoStyle == null) {
            System.out.println("NO EXISTE");
            return null;
        }
        try ( BufferedReader br = new BufferedReader(new FileReader(infoStyle))) {
            String sentence;
            while ((sentence = br.readLine()) != null) {
                if (sentence.startsWith("Modo")) {
                    settings = sentence.split("-");
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(GameData.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(GameData.class.getName()).log(Level.SEVERE, null, ex);
        }
        return settings;
    }

    public static void updateInfoStyle(String newSettings) {
        File tempSettings = new File(".\\src\\main\\java\\com\\gf\\app\\juegomuseos\\resources", "_temp.txt");
        try ( BufferedReader br = new BufferedReader(new FileReader(infoStyle))) {
            String sentence;
            try ( BufferedWriter bw = new BufferedWriter(new FileWriter(tempSettings, false))) {
                while ((sentence = br.readLine()) != null) {
                    bw.write(sentence);
                    bw.newLine();
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(GameData.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(GameData.class.getName()).log(Level.SEVERE, null, ex);
        }
        try ( BufferedReader br = new BufferedReader(new FileReader(tempSettings))) {
            String sentence;
            try ( BufferedWriter bw = new BufferedWriter(new FileWriter(infoStyle, false))) {
                while ((sentence = br.readLine()) != null) {
                    if (sentence.startsWith("Modo")) {
                        bw.write(newSettings);
                        bw.newLine();
                    } else {
                        bw.write(sentence);
                        bw.newLine();
                    }
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(GameData.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(GameData.class.getName()).log(Level.SEVERE, null, ex);
        }
        tempSettings.delete();
    }
}
