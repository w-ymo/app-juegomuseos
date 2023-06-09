/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gf.app.juegomuseos.utils;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.viewer.GeoPosition;
import org.jxmapviewer.painter.Painter;

/**
 * RoutePainter: dibuja una linea sobre un mapa de tipo
 * {@link JXMapViewer}. Implementa de {@link Painter}.
 *
 * @see Painter
 * @see JXMapViewer
 *
 * @author fercaslu
 */
public class RoutePainter implements Painter<JXMapViewer> {

    /**
     * antiAlias: determina si se renderiza el grafico de tipo {@link Graphics2D}
     * con antialiasing. Ve a la clase {@link RenderingHints} para la definicion
     * de las claves y los valores mas comunes.
     * 
     * @see Graphics2D
     * @see RenderingHints
     */
    private boolean antiAlias = true;

    /**
     * track: lista de tipo {@link List} que recoge las posiciones de tipo 
     * {@link GeoPosition}.
     */
    private List<GeoPosition> track;

    //CONSTRUCTOR
    /**
     * RoutePainter: es el constructor de la clase. Para que funcione 
     * correctamente necesita una lista de tipo {@link List} que recoja posiciones
     * de tipo {@link GeoPosition}.
     * 
     * @see List
     * @see GeoPosition
     * 
     * @param track
     */
    public RoutePainter(List<GeoPosition> track) {
        // copy the list so that changes in the 
        // original list do not have an effect here
        this.track = new ArrayList<GeoPosition>(track);
    }
    
    /**
     * paint: dibuja sobre el mapa de tipo {@link JXMapViewer} dos vece una linea
     * con {@link Graphics2D}, la primera es para el contorno y la segunda para 
     * el relleno.
     * 
     * @see Graphics2D
     * @see JXMapViewer
     * 
     * @param g2D
     * @param mapViewer
     * @param width 
     * @param height 
     */
    @Override
    public void paint(Graphics2D g2D, JXMapViewer mapViewer, int width, int height) {
        g2D = (Graphics2D) g2D.create();

        //convierte de viewport a mapa de bits del mundo
        Rectangle rect = mapViewer.getViewportBounds();
        g2D.translate(-rect.x, -rect.y);

        if (antiAlias) {
            g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        }

        //se realiza el dibujo
        g2D.setColor(Colors.ONYX);
        g2D.setStroke(new BasicStroke(4));

        drawRoute(g2D, mapViewer);

        //se realiza el dibujo de nuevo
        g2D.setColor(Color.blue.brighter());
        g2D.setStroke(new BasicStroke(2));

        drawRoute(g2D, mapViewer);

        g2D.dispose();
    }

    /**
     * drawRoute: dibuja la ruta utilizando los graficos de {@link Graphics2D} 
     * entre las posiciones de tipo {@link GeoPosition} sobre un mapa de tipo 
     * {@link JXMapViewer}.
     * 
     * @see GeoPosition
     * @see JXMapViewer
     * 
     * @param g2D 
     * @param mapViewer 
     */
    private void drawRoute(Graphics2D g2D, JXMapViewer mapViewer) {
        int lastX = 0;
        int lastY = 0;

        boolean first = true;

        for (GeoPosition gp : track) {
            //convierte geo-coordenadas a pixel
            Point2D pt = mapViewer.getTileFactory().geoToPixel(gp, mapViewer.getZoom());
            if (first) {
                first = false;
            } else {
                g2D.drawLine(lastX, lastY, (int) pt.getX(), (int) pt.getY());
            }

            lastX = (int) pt.getX();
            lastY = (int) pt.getY();
        }
    }
}
