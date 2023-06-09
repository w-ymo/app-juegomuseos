/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */
package com.gf.app.juegomuseos;

import com.gf.app.juegomuseos.controller.MainController;
import com.gf.app.juegomuseos.views.GUIPrincipal;

/**
 * AppJuegomuseos: clase que contiene el main y lanza la aplicacion.
 * 
 * @author priparno
 * @author fercaslu
 */
public class AppJuegomuseos {

    public static void main(String[] args) {
        MainController mc = new MainController(new GUIPrincipal());
    }
}   
