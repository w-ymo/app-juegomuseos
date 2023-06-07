/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gf.app.juegomuseos.controller;

import com.gf.app.juegomuseos.views.GUIInfo;

/**
 *
 * @author priparno
 */
public class InfoController implements GameControllers{
    
    private GUIInfo view;

    public InfoController(GUIInfo view) {
        this.view = view;
        view.setVisible(true);
    }
    
    
    
}
