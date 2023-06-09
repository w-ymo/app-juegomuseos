/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.gf.app.juegomuseos.utils;

import com.gf.app.juegomuseos.controller.MainController;
import com.gf.app.juegomuseos.controller.RankingController;
import com.gf.app.juegomuseos.controller.SelectGameController;
import com.gf.app.juegomuseos.views.GUIPrincipal;
import com.gf.app.juegomuseos.views.GUIRanking;
import com.gf.app.juegomuseos.views.GUISelectGame;
import java.awt.Dimension;
import java.awt.Toolkit;

/**
 * GameConstants: es una interfaz que contiene diversas constantes para el
 * funcionamiento del juego.
 *
 * @author priparno
 * @author fercaslu
 */
public interface GameConstants {

    /**
     * FREE_MODE: es un booleano que indica el modo libre.
     */
    public static final boolean FREE_MODE = false;

    /**
     * COMP_MODE: es un booleano que indica el modo competitivo.
     */
    public static final boolean COMP_MODE = true;

    public static final String WHOIS_CODE = "WH";

    public static final String TRUEFALSE_CODE = "TF";

    public static final String GREGORIO_CODE = "GF";

    public static final String MAPGAME_CODE = "MP";

    /**
     * MAIN_MENU_OPTIONS: es un array de cadenas de caracteres que indica las 
     * opciones presentadas en la ventana principal de la aplicacion.
     * 
     * @see GUIPrincipal
     * @see MainController
     */
    public static final String[] MAIN_MENU_OPTIONS = {"MODO YINCANA", "MODO LIBRE", "RANKING", "INFORMACION", "AJUSTES", "SALIR"};

    /**
     * GAMES_NAMES: es un array de cadenas de caracteres que indica los juegos 
     * disponibles en la ventana del modo libre de la aplicacion.
     * 
     * @see GUISelectGame
     * @see SelectGameController
     */
    public static final String[] GAMES_NAMES = {"¿Quién lo hizo?", "Verdadero/Falso de museos", "Gregorio Fernández", "Coloca en el mapa"};

    /**
     * GAMES_CODES: es un array de cadenas de caracteres que contiene el codigo
     * de los juegos.
     * 
     * @see GUISelectGame
     * @see SelectGameController
     */
    public static final String[] GAMES_CODES = {WHOIS_CODE, TRUEFALSE_CODE, GREGORIO_CODE, MAPGAME_CODE};

    /**
     * SCREEN_SIZE: es la dimension de tipo {@link Dimension} de la pantalla.
     */
    public static final Dimension SCREEN_SIZE = Toolkit.getDefaultToolkit().getScreenSize();

    /**
     * RANKING_COLUMNS: es un array de cadenas de caracteres que contriene los 
     * nombres de las columnas del ranking.
     * 
     * @see GUIRanking
     * @see RankingController
     */
    public static final String[] RANKING_COLUMNS = {"Nombre", "Puntuación"};

}
