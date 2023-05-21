/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gf.app.juegomuseos.dao;

import com.gf.app.juegomuseos.models.Country;
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
public class CountryDAO {

    public List<Country> selectAll() throws SQLException {
        String sql = "SELECT * FROM paises";
        List<Country> list = new ArrayList<>();
        try ( Connection con = ConnectionDB.getConnection()) {
            Statement s = con.prepareStatement(sql);
            try ( ResultSet rs = s.executeQuery(sql)) {
                while (rs.next()) {
                    Country c = new Country();
                    c.setId_pais(rs.getInt("id_pais"));
                    c.setNombre_pais(rs.getString("nombre_pais"));
                }
            }
        }
        return list;
    }

    public Country selectId(int id) throws SQLException {
        String sql = "SELECT * FROM paises WHERE id_pais=" + id;
        Country c = new Country();
        try ( Connection con = ConnectionDB.getConnection()) {
            Statement s = con.prepareStatement(sql);
            try ( ResultSet rs = s.executeQuery(sql)) {
                if (rs.next()) {
                    c.setId_pais(rs.getInt("id_pais"));
                    c.setNombre_pais(rs.getString("nombre_pais"));
                }
            }
        }
        return c;
    }
    
    public List<Country> selectNum(int num) throws SQLException {
        List<Country> fullList = selectAll();
        Collections.shuffle(fullList);
        List<Country> partialList = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            partialList.add(fullList.get(i));
        }
        return partialList;
    }

}
