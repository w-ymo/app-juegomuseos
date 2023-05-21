/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gf.app.juegomuseos.dao;

import com.gf.app.juegomuseos.models.Museum;
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
public class MuseumDAO {

    public List<Museum> selectAll() throws SQLException {
        String sql = "SELECT * FROM museos";
        List<Museum> lista = new ArrayList<>();
        try ( Connection con = ConnectionDB.getConnection()) {
            Statement s = con.prepareStatement(sql);
            try ( ResultSet rs = s.executeQuery(sql)) {
                while (rs.next()) {
                    Museum m = new Museum();
                    m.setId_museo(rs.getInt("id_museo"));
                    m.setNombre_museo(rs.getString("nombre_museo"));
                    m.setId_pais(rs.getInt("id_pais"));
                }
            }
        }
        return lista;
    }

    public Museum selectId(int id) throws SQLException {
        String sql = "SELECT * FROM museos WHERE id_museo=" + id;
        Museum m = new Museum();
        try ( Connection con = ConnectionDB.getConnection()) {
            Statement s = con.prepareStatement(sql);
            try ( ResultSet rs = s.executeQuery(sql)) {
                if (rs.next()) {
                    m.setId_museo(rs.getInt("id_museo"));
                    m.setNombre_museo(rs.getString("nombre_museo"));
                    m.setId_pais(rs.getInt("id_pais"));
                }
            }
        }
        return m;
    }
    
    public List<Museum> selectNum(int num) throws SQLException {
        List<Museum> fullList = selectAll();
        Collections.shuffle(fullList);
        List<Museum> partialList = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            partialList.add(fullList.get(i));
        }
        return partialList;
    }
}
