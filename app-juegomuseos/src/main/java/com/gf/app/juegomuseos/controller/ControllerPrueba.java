/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gf.app.juegomuseos.controller;

import com.gf.app.juegomuseos.views.GUIWhoIs;
import com.gf.app.juegomuseos.views.NewJFrame;
import java.awt.event.ActionListener;

/**
 *
 * @author noelp
 */
public class ControllerPrueba {

    NewJFrame v;
    
    ActionListener al = (e) -> {
        v.dispose();
        WhoIsController c = new WhoIsController(new GUIWhoIs());
    };

    public ControllerPrueba(NewJFrame n) {
        this.v = n;
        v.getjButton1().addActionListener(al);
        v.setVisible(true);
    }
    
    
}
