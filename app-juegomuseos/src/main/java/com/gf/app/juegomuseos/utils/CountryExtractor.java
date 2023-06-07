/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gf.app.juegomuseos.utils;

/**
 *
 * @author Luis
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class CountryExtractor {

    public static String getCountryName(double latitude, double longitude) {
        if (longitude > 180) {
            longitude -= 360;
        } else if (longitude < -180) {
            longitude += 360;
        }
        try {
            // URL de la solicitud de geocodificación inversa a Nominatim
            String urlString = "https://nominatim.openstreetmap.org/reverse?format=jsonv2&lat=" + latitude + "&lon=" + longitude + "&accept-language=es";

            // Realizar la solicitud HTTP
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            // Leer la respuesta JSON
            InputStream inputStream = conn.getInputStream();
            Scanner scanner = new Scanner(inputStream).useDelimiter("");
               String response = scanner.hasNext() ? scanner.next() : "";

            // Analizar la respuesta JSON y obtener el nombre del país
            String countryName = response.contains("\"country\":\"") ? response.split("\"country\":\"")[1].split("\"")[0] : "";

               // Cerrar las conexiones y devolver el nombre del país
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
