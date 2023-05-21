/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gf.app.juegomuseos.dao;

import com.gf.app.juegomuseos.models.Author;
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
 * @author priparno
 */
public class AuthorDAO {

    public List<Author> selectAll() throws SQLException {
        String sql = "SELECT * FROM autores";
        List<Author> lista = new ArrayList<>();
        try ( Connection con = ConnectionDB.getConnection()) {
            Statement s = con.prepareStatement(sql);
            try ( ResultSet rs = s.executeQuery(sql)) {
                while (rs.next()) {
                    Author a = new Author();
                    a.setId_autor(rs.getInt("id_autor"));
                    a.setNombre_autor(rs.getString("nombre_autor"));
                    a.setId_pais(rs.getInt("id_pais"));
                }
            }
        }
        return lista;
    }

    public Author selectId(int id) throws SQLException {
        String sql = "SELECT * FROM autores WHERE id_autor=" + id;
        Author a = new Author();
        try ( Connection con = ConnectionDB.getConnection()) {
            Statement s = con.prepareStatement(sql);
            try ( ResultSet rs = s.executeQuery(sql)) {
                if(rs.next()) {
                    a.setId_autor(rs.getInt("id_autor"));
                    a.setNombre_autor(rs.getString("nombre_autor"));
                    a.setId_pais(rs.getInt("id_pais"));
                }
            }
        }
        return a;
    }
    
    public List<Author> selectNum(int num) throws SQLException {
        List<Author> fullList = selectAll();
        Collections.shuffle(fullList);
        List<Author> partialList = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            partialList.add(fullList.get(i));
        }
        return partialList;
    }

}
