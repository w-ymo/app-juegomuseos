/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gf.app.juegomuseos.utils;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

/**
 * CountyExtractor: es la clase desde la que se lanza el metodo 
 * {@link CountryExtractor#getCountryName(double, double)} que recoge el nombre
 * de un pais por sus coordenadas.
 *
 * @see CountryExtractor#getCountryName(double, double) 
 * 
 * @author fercaslu
 */
public class CountryExtractor {

    /**
     * getCountryName: metodo que recibe como parametros la latitud y la 
     * longitud de forma decimal y utilizando Nominatim de OpenStreetMap se extrae
     * el nombre del pais al que pertenecen dichas coordenadas.
     * 
     * @param latitude 
     * @param longitude  
     */
    public static String getCountryName(double latitude, double longitude) {
        if (longitude > 180) {
            longitude -= 360;
        } else if (longitude < -180) {
            longitude += 360;
        }
        try {
            //url de solicitud de geocodificación inversa a Nominatim
            String urlString = "https://nominatim.openstreetmap.org/reverse?format=jsonv2&lat=" + latitude + "&lon=" + longitude + "&accept-language=es";

            //solicitud http
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            //lectura de json
            InputStream inputStream = conn.getInputStream();
            Scanner scanner = new Scanner(inputStream).useDelimiter("\\A");
               String response = scanner.hasNext() ? scanner.next() : "";

            //analisis y formateo de respuesa y extraccion de nombre de pais
            String countryName = response.contains("\"country\":\"") ? response.split("\"country\":\"")[1].split("\"")[0] : "";

            //cierre de conexiones y devolucion de nombre de pais
            scanner.close();
            inputStream.close();
            conn.disconnect();
            return countryName;
            
        } catch (IOException e) {
            e.printStackTrace();

        }
        return null;
    }
}
