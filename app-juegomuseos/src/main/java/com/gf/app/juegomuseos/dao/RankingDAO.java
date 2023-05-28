/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gf.app.juegomuseos.dao;

import com.gf.app.juegomuseos.models.Ranking;
import com.gf.app.juegomuseos.utils.ConnectionDB;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author noelp
 */
public class RankingDAO {
    
    public List<Ranking> selectAll() throws SQLException {
        String sql = "SELECT * FROM ranking";
        List<Ranking> list = new ArrayList<>();
        try ( Connection con = ConnectionDB.getConnection()) {
            Statement s = con.prepareStatement(sql);
            try ( ResultSet rs = s.executeQuery(sql)) {
                while (rs.next()) {
                    Ranking r = new Ranking();
                    r.setId_ranking(rs.getInt("id_ranking"));
                    r.setNombre_usuario(rs.getString("nombre_usuario"));
                    r.setPuntuacion(rs.getString("puntuacion"));
                    list.add(r);
                }
            }
        }
        return list;
    }

    public Ranking selectId(int id) throws SQLException {
        String sql = "SELECT * FROM ranking WHERE id_ranking=" + id;
        Ranking r = new Ranking();
        try ( Connection con = ConnectionDB.getConnection()) {
            Statement s = con.prepareStatement(sql);
            try ( ResultSet rs = s.executeQuery(sql)) {
                if (rs.next()) {
                    r.setId_ranking(rs.getInt("id_ranking"));
                    r.setNombre_usuario(rs.getString("nombre_usuario"));
                    r.setPuntuacion(rs.getString("puntuacion"));
                }
            }
        }
        return r;
    }
    
    public List<Ranking> selectNum(int num) throws SQLException {
        List<Ranking> fullList = selectAll();
        Collections.shuffle(fullList);
        List<Ranking> partialList = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            partialList.add(fullList.get(i));
        }
        return partialList;
    }
    
}
