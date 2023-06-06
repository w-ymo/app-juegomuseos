/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gf.app.juegomuseos.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author noelp
 */
public class GetData {

    public static File infoHTML = new File(".\\src\\main\\java\\com\\gf\\app\\juegomuseos\\resources\\AppInfo.html");

    public static String getInfoHtml() {
        String html = "";
        if (infoHTML == null) {
            System.out.println("NO EXISTE");
            return null;
        }
        try ( BufferedReader br = new BufferedReader(new FileReader(infoHTML))) {
            String frase;
            while ((frase = br.readLine()) != null) {
                html += frase + "\n";
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return html;
    }

}
