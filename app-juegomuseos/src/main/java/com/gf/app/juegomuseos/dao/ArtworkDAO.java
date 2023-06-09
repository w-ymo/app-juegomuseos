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
 * ArtworkDAO: es la clase que permite el acceso a la base de datos. En
 * especifico a la tabla 'obras'.
 *
 * @author priparno
 * @author fercaslu
 */
public class ArtworkDAO {

    /**
     * selectAll: devuelve una lista de todos los elementos de obras de la base
     * de datos.
     *
     * @return una lista de objeto {@link Artwork}
     * @throws SQLException si no consigue acceder a la base de datos (error
     * sintactico o base de datos no encontrada)
     */
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

    /**
     * selectId: devuelve un objeto {@link Artwork} que tenga el mismo
     * identificador que el pasado por parametro.
     *
     * @param id un entero que representa el id de la obra
     * @return un objeto {@link Artwork}
     * @throws SQLException si no consigue acceder a la base de datos (error
     * sintactico o base de datos no encontrada)
     */
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

    /**
     * selectNum: devuelve una lista de {@link Artwork} aleatoria dependiendo
     * del numero introducido por parametro. La lista contendra tantos elementos
     * como el numero pasado por parametro.
     *
     * @param num un entero para el numero de obras que tiene la lista
     * @return una lista de {@link Artwork}
     * @throws SQLException si no consigue acceder a la base de datos (error
     * sintactico o base de datos no encontrada)
     */
    public List<Artwork> selectNum(int num) throws SQLException {
        List<Artwork> fullList = selectAll();
        Collections.shuffle(fullList);
        List<Artwork> partialList = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            partialList.add(fullList.get(i));
        }
        return partialList;
    }

    /**
     * selectIdAuthor: devuelve una lista de {@link Artwork} cuando el id del
     * autor sea el pasado por parametro.
     *
     * @param id un entero que representa el id del autor
     * @return una lista de {@link Artwork}
     * @throws SQLException si no consigue acceder a la base de datos (error
     * sintactico o base de datos no encontrada)
     */
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

    /**
     * selectSimilar: devuelve una lista de {@link Artwork} que contiene las
     * obras similares segun el parametro key. En la base de datos se almacena
     * un campo 'clave' que refleja la palabra o palabras clave de la obra en
     * cuestion.
     *
     * @param key una cadena que representa el valor clave a comparar
     * @return una lista de {@link Artwork}
     * @throws SQLException si no consigue acceder a la base de datos (error
     * sintactico o base de datos no encontrada)
     */
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

    /**
     * selectSimilar: devuelve una lista de {@link Artwork} que contiene las
     * obras similares segun el parametro key y que no coinciden con el autor
     * que se ve reflejado en el entero pasado como parametro. En la base de
     * datos se almacena un campo 'clave' que refleja la palabra o palabras
     * clave de la obra en cuestion.
     *
     * @param key una cadena que representa el valor clave a comparar
     * @param id_author un entero que representa el id de autor, el cual no
     * tiene que coincidir.
     * @return una lista de {@link Artwork}
     * @throws SQLException si no consigue acceder a la base de datos (error
     * sintactico o base de datos no encontrada)
     */
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
