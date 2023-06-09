/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gf.app.juegomuseos.utils;

import com.gf.app.juegomuseos.views.GUIWhoIs;
import java.awt.Color;

/**
 * Colors: una interfaz que contiene los colores que se utilizaran en la
 * aplicacion.
 *
 * @author fercaslu
 * @author priparno
 */
public interface Colors {

    public static final Color GREEN = (new Color(0, 128, 0));
    
    public static final Color RED = (new Color(204, 0, 0));
    
    public static final Color CARROT = (new Color(255, 140, 25));
    
    public static final Color MALACHITE = (new Color(0, 204, 102));
    
    public static final Color DODGER = (new Color(25, 140, 255));
    
    public static final Color LAVENDER = (new Color(166, 77, 255));
    
    public static final Color ONYX = (new Color(9, 0, 51));
    
    public static final Color THEME_ORANGE = new Color(245, 131, 20);

    /**
     * BUTTONS_COLORS: es un array con los colores para la ventana
     * {@link GUIWhoIs}
     */
    public static final Color[] BUTTONS_COLORS = {Colors.CARROT, Colors.MALACHITE, Colors.DODGER, Colors.LAVENDER};
}
