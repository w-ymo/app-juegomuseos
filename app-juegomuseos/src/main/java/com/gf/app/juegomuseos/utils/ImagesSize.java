/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gf.app.juegomuseos.utils;

import java.awt.Dimension;
import java.awt.Image;
import javax.swing.ImageIcon;


/**
 *
 * @author noelp
 */
public class ImagesSize {
    
    public static Image getProportionalDimensionImage(ImageIcon i, Dimension gapSize) {
        int x = i.getIconWidth();
        int y = i.getIconHeight();
        Dimension newDimension = new Dimension();
//        if (x > y) {
//            newDimension.setSize(gapSize.getWidth(), (int) (gapSize.getWidth()*y/x));
//        } else {
            newDimension.setSize((int) (gapSize.getHeight()*x/y), gapSize.getHeight());
        //}
        return i.getImage().getScaledInstance(newDimension.width, newDimension.height, Image.SCALE_DEFAULT);
    }

}
