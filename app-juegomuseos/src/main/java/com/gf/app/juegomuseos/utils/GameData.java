/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gf.app.juegomuseos.utils;

import com.gf.app.juegomuseos.controller.SettingsController;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 * GameData: clase que proporciona la informacion necesaria para el
 * funcionamiento de la aplicacion. En este caso el texto de la ventana
 * {@link GUIInfo}, el estilo de la aplicacion y el volumen.
 *
 * @author priparno
 * @author fercaslu
 */
public class GameData {

    /**
     * infoHTML: tipo {@link File} que recoge el archivo AppInfo.html
     */
    private static File infoHTML = new File(".\\src\\main\\java\\com\\gf\\app\\juegomuseos\\resources\\AppInfo.html");

    /**
     * infoSettings: tipo {@link File} que recoge el archivo AppSettings.txt
     */
    private static File infoSettings = new File(".\\src\\main\\java\\com\\gf\\app\\juegomuseos\\resources\\AppSettings.txt");

    /**
     * DARK_LAF: constante que contiene el nombre de la clase del LaF oscuro.
     */
    public final static String DARK_LAF = "com.formdev.flatlaf.intellijthemes.FlatArcOrangeIJTheme";

    /**
     * LIGHT_LAF: constante que contiene el nombre de la clase del LaF claro.
     */
    public final static String LIGHT_LAF = "com.formdev.flatlaf.intellijthemes.FlatArcDarkOrangeIJTheme";

    /**
     * STYLE: carga cuando se inicia la aplicacion el estilo utilizado por
     * ultima vez.
     */
    public final static String STYLE = getInfoStyle()[1];

    /**
     * VOLUME: carga cuando se inicia la aplicacion el volumen utilizado por
     * ultima vez.
     */
    public final static float VOLUME = Float.parseFloat(getInfoVolume()[1]);

    /**
     * getInfoHtml: devuelve una cadena con todo el contenido del archivo
     * {@link #infoHTML}
     *
     * @return una cadena con el contenido en formato HTML de {@link #infoHTML}
     */
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
            System.err.println("Error de entrada salida.");
        }
        return html;
    }

    /**
     * getInfoStyle: devuelve un array de cadenas con todo el contenido del
     * estilo, es decir, el estilo seleccionado (Modo oscuro, Modo claro) y el
     * nombre de la clase del LaF obtenido del archivo {@link #infoSettings}.
     *
     * @return un array de cadenas con el contenido del estilo de
     * {@link #infoSettings}
     */
    public static String[] getInfoStyle() {
        String[] settings = new String[2];
        if (infoSettings == null) {
            System.out.println("NO EXISTE");
            return null;
        }
        try ( BufferedReader br = new BufferedReader(new FileReader(infoSettings))) {
            String sentence;
            while ((sentence = br.readLine()) != null) {
                if (sentence.startsWith("Modo")) {
                    settings = sentence.split("%");
                }
            }
        } catch (FileNotFoundException ex) {
            System.err.println("Archivo no encontrado.");
        } catch (IOException ex) {
            System.err.println("Error de entrada salida.");
        }
        return settings;
    }

    /**
     * updateInfoStyle: actualiza el estilo con el nuevo que se haya
     * seleccionado mediante {@link SettingsController}.
     *
     * @param newSettings la cadena de texto a actualizar con el formato
     * 'modo'%'estilo'
     */
    public static void updateInfoStyle(String newSettings) {
        //creo un temporal donde escribo todo para no perder informacion
        File tempSettings = new File(".\\src\\main\\java\\com\\gf\\app\\juegomuseos\\resources", "_temp.txt");
        try ( BufferedReader br = new BufferedReader(new FileReader(infoSettings))) {
            String sentence;
            try ( BufferedWriter bw = new BufferedWriter(new FileWriter(tempSettings, false))) {
                while ((sentence = br.readLine()) != null) {
                    bw.write(sentence);
                    bw.newLine();
                }
            }
        } catch (FileNotFoundException ex) {
            System.err.println("Archivo no encontrado.");
        } catch (IOException ex) {
            System.err.println("Error de entrada salida.");
        }
        //modifico la linea del estilo (empieza por Modo)
        try ( BufferedReader br = new BufferedReader(new FileReader(tempSettings))) {
            String sentence;
            try ( BufferedWriter bw = new BufferedWriter(new FileWriter(infoSettings, false))) {
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
            System.err.println("Archivo no encontrado.");
        } catch (IOException ex) {
            System.err.println("Error de entrada salida.");
        }
        tempSettings.delete();
    }

    /**
     * getInfoStyle: devuelve un array de cadenas con todo el contenido del
     * volumen, es decir, el numero del volumen seleccionado obtenido del
     * archivo {@link #infoSettings}.
     *
     * @return un array de cadenas con el contenido del volumen de
     * {@link #infoSettings}
     */
    public static String[] getInfoVolume() {
        String[] settings = new String[2];
        if (infoSettings == null) {
            System.out.println("NO EXISTE");
            return null;
        }
        try ( BufferedReader br = new BufferedReader(new FileReader(infoSettings))) {
            String sentence;
            while ((sentence = br.readLine()) != null) {
                if (sentence.startsWith("Volume")) {
                    settings = sentence.split("%");
                }
            }
        } catch (FileNotFoundException ex) {
            System.err.println("Archivo no encontrado.");
        } catch (IOException ex) {
            System.err.println("Error de entrada salida.");
        }
        return settings;
    }

    /**
     * updateInfoStyle: actualiza el volumen con el nuevo que se haya
     * seleccionado mediante {@link SettingsController}.
     *
     * @param newSettings la cadena de texto a actualizar con el formato
     * 'Volume'%'value'
     */
    public static void updateInfoVolume(String newSettings) {
        //creo un temporal donde escribo todo para no perder informacion
        File tempSettings = new File(".\\src\\main\\java\\com\\gf\\app\\juegomuseos\\resources", "_temp.txt");
        try ( BufferedReader br = new BufferedReader(new FileReader(infoSettings))) {
            String sentence;
            try ( BufferedWriter bw = new BufferedWriter(new FileWriter(tempSettings, false))) {
                while ((sentence = br.readLine()) != null) {
                    bw.write(sentence);
                    bw.newLine();
                }
            }
        } catch (FileNotFoundException ex) {
            System.err.println("Archivo no encontrado.");
        } catch (IOException ex) {
            System.err.println("Error de entrada salida.");
        }
        //modifico la linea del estilo (empieza por Volume)
        try ( BufferedReader br = new BufferedReader(new FileReader(tempSettings))) {
            String sentence;
            try ( BufferedWriter bw = new BufferedWriter(new FileWriter(infoSettings, false))) {
                while ((sentence = br.readLine()) != null) {
                    if (sentence.startsWith("Volume")) {
                        bw.write(newSettings);
                        bw.newLine();
                    } else {
                        bw.write(sentence);
                        bw.newLine();
                    }
                }
            }
        } catch (FileNotFoundException ex) {
            System.err.println("Archivo no encontrado.");
        } catch (IOException ex) {
            System.err.println("Error de entrada salida.");
        }
        tempSettings.delete();
    }

    /**
     *
     * @return
     */
    public static boolean isValid() {
        if (infoHTML.exists() && infoSettings.exists()) {
            return infoHTML.length() > 0 && infoSettings.length() > 0;
        } else {
            JOptionPane.showMessageDialog(null, "Error en los archivos de juego. Revise 'resources'", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

}
