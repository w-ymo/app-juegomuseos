/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.gf.app.juegomuseos.utils;

import java.awt.Dimension;
import java.awt.Toolkit;

/**
 *
 * @author priparno
 */
public interface GameConstants {

    public static final boolean FREE_MODE = false;

    public static final boolean COMP_MODE = true;
    
    public static final String WHOIS_CODE = "WH";
    
    public static final String TRUEFALSE_CODE = "TF";
    
    public static final String GREGORIO_CODE = "GF";
    
    public static final String MAPGAME_CODE = "MP";

    public static final String[] MAIN_MENU_OPTIONS = {"MODO YINCANA", "MODO LIBRE", "INFORMACION", "AJUSTES", "SALIR"};

    public static final String[] GAMES_NAMES = {"¿De quién es?", "¿Cuál existe?", "¿Cuál es de Gregorio?", "Coloca en país"};
    
    public static final String[] GAMES_CODES = {WHOIS_CODE, TRUEFALSE_CODE, GREGORIO_CODE, MAPGAME_CODE};

    public static final Dimension SCREEN_SIZE = Toolkit.getDefaultToolkit().getScreenSize();

}
