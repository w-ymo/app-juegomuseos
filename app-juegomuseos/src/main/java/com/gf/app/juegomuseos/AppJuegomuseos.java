/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */
package com.gf.app.juegomuseos;

import com.gf.app.juegomuseos.controller.GregFernandezController;
import com.gf.app.juegomuseos.controller.WhoIsController;
import com.gf.app.juegomuseos.views.GUIGregorioFernandez;
import com.gf.app.juegomuseos.views.GUIWhoIs;

/**
 *
 * @author priparno
 */
public class AppJuegomuseos {

    public static void main(String[] args) {
        GUIWhoIs wi = new GUIWhoIs();
        WhoIsController wi_contr = new WhoIsController(wi);
//
//        GUIGregorioFernandez gf = new GUIGregorioFernandez();
//        GregFernandezController gf_contr = new GregFernandezController(gf);
    }
}
