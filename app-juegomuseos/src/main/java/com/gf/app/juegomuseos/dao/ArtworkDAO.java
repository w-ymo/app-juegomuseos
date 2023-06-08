/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gf.app.juegomuseos.dao;

import com.gf.app.juegomuseos.models.Artwork;
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
public class ArtworkDAO {

    public List<Artwork> selectAll() throws SQLException {
        String sql = "SELECT * FROM obras";
        List<Artwork> list = new ArrayList<>();
        try ( Connection con = ConnectionDB.getConnection()) {
            Statement s = con.prepareStatement(sql);
            try ( ResultSet rs = s.executeQuery(sql)) {
                while (rs.next()) {
                    Artwork a = new Artwork();
                    a.setId_obra(rs.getInt("id_obra"));
                    a.setNombre_obra(rs.getString("nombre_obra"));
                    a.setClave_obra(rs.getString("clave_obra"));
                    a.setDescripcion_obra(rs.getString("descripcion_obra"));
                    a.setDisciplina(rs.getString("disciplina"));
                    a.setImagen_obra(rs.getString("imagen_obra"));
                    a.setId_autor(rs.getInt("id_autor"));
                    a.setId_museo(rs.getInt("id_museo"));
                    a.setLatitud(rs.getDouble("latitud"));
                    a.setLongitud(rs.getDouble("longitud"));
                    list.add(a);
                }
            }
        }
        return list;
    }

    public Artwork selectId(int id) throws SQLException {
        String sql = "SELECT * FROM obras WHERE id_obra=" + id;
        Artwork a = new Artwork();
        try ( Connection con = ConnectionDB.getConnection()) {
            Statement s = con.prepareStatement(sql);
            try ( ResultSet rs = s.executeQuery(sql)) {
                if (rs.next()) {
                    a.setId_obra(rs.getInt("id_obra"));
                    a.setNombre_obra(rs.getString("nombre_obra"));
                    a.setClave_obra(rs.getString("clave_obra"));
                    a.setDescripcion_obra(rs.getString("descripcion_obra"));
                    a.setDisciplina(rs.getString("disciplina"));
                    a.setImagen_obra(rs.getString("imagen_obra"));
                    a.setId_autor(rs.getInt("id_autor"));
                    a.setId_museo(rs.getInt("id_museo"));
                    a.setLatitud(rs.getDouble("latitud"));
                    a.setLongitud(rs.getDouble("longitud"));
                }
            }
        }
        return a;
    }

    public List<Artwork> selectNum(int num) throws SQLException {
        List<Artwork> fullList = selectAll();
        Collections.shuffle(fullList);
        List<Artwork> partialList = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            partialList.add(fullList.get(i));
        }
        return partialList;
    }

    public List<Artwork> selectIdAuthor(int id) throws SQLException {
        String sql = "SELECT * FROM obras WHERE id_autor=" + id;
        List<Artwork> list = new ArrayList<>();
        try ( Connection con = ConnectionDB.getConnection()) {
            Statement s = con.prepareStatement(sql);
            try ( ResultSet rs = s.executeQuery(sql)) {
                while (rs.next()) {
                    Artwork a = new Artwork();
                    a.setId_obra(rs.getInt("id_obra"));
                    a.setNombre_obra(rs.getString("nombre_obra"));
                    a.setClave_obra(rs.getString("clave_obra"));
                    a.setDescripcion_obra(rs.getString("descripcion_obra"));
                    a.setDisciplina(rs.getString("disciplina"));
                    a.setImagen_obra(rs.getString("imagen_obra"));
                    a.setId_autor(rs.getInt("id_autor"));
                    a.setId_museo(rs.getInt("id_museo"));
                    a.setLatitud(rs.getDouble("latitud"));
                    a.setLongitud(rs.getDouble("longitud"));
                    list.add(a);
                }
            }
        }
        return list;
    }

    public List<Artwork> selectSimilar(String key) throws SQLException {
        String sql = "SELECT * FROM obras WHERE clave_obra='" + key + "'";
        List<Artwork> list = new ArrayList<>();
        try ( Connection con = ConnectionDB.getConnection()) {
            Statement s = con.prepareStatement(sql);
            try ( ResultSet rs = s.executeQuery(sql)) {
                while (rs.next()) {
                    Artwork a = new Artwork();
                    a.setId_obra(rs.getInt("id_obra"));
                    a.setNombre_obra(rs.getString("nombre_obra"));
                    a.setClave_obra(rs.getString("clave_obra"));
                    a.setDescripcion_obra(rs.getString("descripcion_obra"));
                    a.setDisciplina(rs.getString("disciplina"));
                    a.setImagen_obra(rs.getString("imagen_obra"));
                    a.setId_autor(rs.getInt("id_autor"));
                    a.setId_museo(rs.getInt("id_museo"));
                    a.setLatitud(rs.getDouble("latitud"));
                    a.setLongitud(rs.getDouble("longitud"));
                    list.add(a);
                }
            }
        }
        return list;
    }

    public List<Artwork> selectSimilar(String key, int id_author) throws SQLException {
        String sql = "SELECT * FROM obras WHERE clave_obra='" + key + "' and id_autor<>" + id_author;
        List<Artwork> list = new ArrayList<>();
        try ( Connection con = ConnectionDB.getConnection()) {
            Statement s = con.prepareStatement(sql);
            try ( ResultSet rs = s.executeQuery(sql)) {
                while (rs.next()) {
                    Artwork a = new Artwork();
                    a.setId_obra(rs.getInt("id_obra"));
                    a.setNombre_obra(rs.getString("nombre_obra"));
                    a.setClave_obra(rs.getString("clave_obra"));
                    a.setDescripcion_obra(rs.getString("descripcion_obra"));
                    a.setDisciplina(rs.getString("disciplina"));
                    a.setImagen_obra(rs.getString("imagen_obra"));
                    a.setId_autor(rs.getInt("id_autor"));
                    a.setId_museo(rs.getInt("id_museo"));
                    a.setLatitud(rs.getDouble("latitud"));
                    a.setLongitud(rs.getDouble("longitud"));
                    list.add(a);
                }
            }
        }
        return list;

    }
}
