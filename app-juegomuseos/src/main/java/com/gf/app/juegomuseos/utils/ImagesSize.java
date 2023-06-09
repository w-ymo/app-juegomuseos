/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gf.app.juegomuseos.utils;

import java.awt.Dimension;
import java.awt.Image;
import javax.swing.ImageIcon;

/**
 * ImagesSize: es una clase que permite operar con las imagenes.
 *
 * @author priparno
 * @author fercaslu
 */
public class ImagesSize {

    /**
     * getProportionalDimensionImage: devuelve una imagen que mantenga la
     * relacion de aspecto dependiendo de la dimension pasada por parametro para
     * que se vea de manera completa.
     *
     * @param i objeto de tipo {@link ImageIcon} con la imagen
     * @param gapSize objeto de tipo {@link Dimension} con las medidas donde se
     * colocara la imagen
     * @return una imagen reescalada de tipo {@link Image}
     */
    public static Image getProportionalDimensionImage(ImageIcon i, Dimension gapSize, boolean isGregorio) {
        int x = i.getIconWidth();
        int y = i.getIconHeight();
        Dimension newDimension = new Dimension();
        if (isGregorio) {
            if (x > y) {
                newDimension.setSize(gapSize.getWidth(), (int) (gapSize.getWidth() * y / x));
            } else {
                newDimension.setSize((int) (gapSize.getHeight() * x / y), gapSize.getHeight());
            }
        } else {
            newDimension.setSize((int) (gapSize.getHeight() * x / y), gapSize.getHeight());
        }
        return i.getImage().getScaledInstance(newDimension.width, newDimension.height, Image.SCALE_DEFAULT);
    }

}
