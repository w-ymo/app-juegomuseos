/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */
package com.gf.app.juegomuseos;

import com.gf.app.juegomuseos.controller.GregFernandezController;
import com.gf.app.juegomuseos.controller.MuseumsTFController;
import com.gf.app.juegomuseos.controller.WhoIsController;
import com.gf.app.juegomuseos.dao.ArtworkDAO;
import com.gf.app.juegomuseos.dao.AuthorDAO;
import com.gf.app.juegomuseos.views.GUIGregorioFernandez;
import com.gf.app.juegomuseos.views.GUIMuseumsTF;
import com.gf.app.juegomuseos.views.GUIWhoIs;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author priparno
 */
public class AppJuegomuseos {

    public static void main(String[] args) {
//        GUIWhoIs wi = new GUIWhoIs();
//        WhoIsController wi_contr = new WhoIsController(wi);
//
//        GUIGregorioFernandez gf = new GUIGregorioFernandez();
//        GregFernandezController gf_contr = new GregFernandezController(gf);

        GUIMuseumsTF mtf = new GUIMuseumsTF();
        MuseumsTFController mtf_contr = new MuseumsTFController(mtf);

//        AuthorDAO at = new AuthorDAO();
//        ArtworkDAO aw = new ArtworkDAO();
//        try {
//            System.out.println(aw.selectSimilar("piedad", at.getIdGregorioFernandez()));
//        } catch (SQLException ex) {
//            Logger.getLogger(AppJuegomuseos.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }
}
