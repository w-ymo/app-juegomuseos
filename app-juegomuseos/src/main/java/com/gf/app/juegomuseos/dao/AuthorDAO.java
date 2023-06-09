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
 * AuthorDAO: es la clase que permite el acceso a la base de datos. En
 * especifico a la tabla 'autores'.
 *
 * @author priparno
 * @author fercaslu
 */
public class AuthorDAO {

    /**
     * selectAll: devuelve una lista de todos los elementos de autores de la
     * base de datos.
     *
     * @return una lista de objeto {@link Author}
     * @throws SQLException si no consigue acceder a la base de datos (error
     * sintactico o base de datos no encontrada)
     */
    public List<Author> selectAll() throws SQLException {
        String sql = "SELECT * FROM autores";
        List<Author> list = new ArrayList<>();
        try ( Connection con = ConnectionDB.getConnection()) {
            Statement s = con.prepareStatement(sql);
            try ( ResultSet rs = s.executeQuery(sql)) {
                while (rs.next()) {
                    Author a = new Author();
                    a.setId_autor(rs.getInt("id_autor"));
                    a.setNombre_autor(rs.getString("nombre_autor"));
                    a.setId_pais(rs.getInt("id_pais"));
                    list.add(a);
                }
            }
        }
        return list;
    }

    /**
     * selectId: devuelve un objeto {@link Author} que tenga el mismo
     * identificador que el pasado por parametro.
     *
     * @param id un entero que representa el id del autor
     * @return un objeto {@link Artwork}
     * @throws SQLException si no consigue acceder a la base de datos (error
     * sintactico o base de datos no encontrada)
     */
    public Author selectId(int id) throws SQLException {
        String sql = "SELECT * FROM autores WHERE id_autor=" + id;
        Author a = new Author();
        try ( Connection con = ConnectionDB.getConnection()) {
            Statement s = con.prepareStatement(sql);
            try ( ResultSet rs = s.executeQuery(sql)) {
                if (rs.next()) {
                    a.setId_autor(rs.getInt("id_autor"));
                    a.setNombre_autor(rs.getString("nombre_autor"));
                    a.setId_pais(rs.getInt("id_pais"));
                }
            }
        }
        return a;
    }

    /**
     * selectNum: devuelve una lista de {@link Author} aleatoria dependiendo del
     * numero introducido por parametro. La lista contendra tantos elementos
     * como el numero pasado por parametro.
     *
     * @param num un entero para el numero de autores que tiene la lista
     * @return una lista de {@link Author}
     * @throws SQLException si no consigue acceder a la base de datos (error
     * sintactico o base de datos no encontrada)
     */
    public List<Author> selectNum(int num) throws SQLException {
        List<Author> fullList = selectAll();
        Collections.shuffle(fullList);
        List<Author> partialList = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            partialList.add(fullList.get(i));
        }
        return partialList;
    }

    /**
     * getIdGregorioFernandez: devuelve un entero que representa el
     * identificador del autor 'Gregorio Fernández' almacenado en la base de
     * datos.
     *
     * @return un entero que representa un id de autor
     * @throws SQLException si no consigue acceder a la base de datos (error
     * sintactico o base de datos no encontrada)
     */
    public int getIdGregorioFernandez() throws SQLException {
        String sql = "SELECT id_autor FROM autores WHERE nombre_autor='Gregorio Fernández'";
        int id = 0;
        try ( Connection con = ConnectionDB.getConnection()) {
            Statement s = con.prepareStatement(sql);
            try ( ResultSet rs = s.executeQuery(sql)) {
                if (rs.next()) {
                    id = rs.getInt("id_autor");
                }
            }
        }
        return id;
    }

    /**
     * selectNotEquals: devuelve una lista de {@link Author} que no coincidan
     * con el pasado por parametro. El parametro es el id del autor.
     *
     * @param id_author un entero que representa el id de un autor
     * @return una lista de {@link Author}
     * @throws SQLException si no consigue acceder a la base de datos (error
     * sintactico o base de datos no encontrada)
     */
    public List<Author> selectNotEquals(int id_author) throws SQLException {
        List<Author> fullList = new ArrayList<>();
        String sql = "SELECT * FROM autores WHERE id_autor<>" + id_author;
        try ( Connection con = ConnectionDB.getConnection()) {
            Statement s = con.prepareStatement(sql);
            try ( ResultSet rs = s.executeQuery(sql)) {
                while (rs.next()) {
                    Author a = new Author();
                    a.setId_autor(rs.getInt("id_autor"));
                    a.setNombre_autor(rs.getString("nombre_autor"));
                    a.setId_pais(rs.getInt("id_pais"));
                    fullList.add(a);
                }
            }
        }
        return fullList;
    }

    /**
     * selectNotEquals: devuelve una lista de {@link Author} que no coincidan
     * con el pasado por parametro.El parametro es el id del autor. Se cogeran
     * tantos elementos como el entero num pasado por parametro.
     *
     * @param id_author un entero que representa el id de un autor
     * @param num un entero que representa el numero de elementos seleccionados
     * @return una lista de {@link Author}
     * @throws SQLException si no consigue acceder a la base de datos (error
     * sintactico o base de datos no encontrada)
     */
    public List<Author> selectNotEquals(int id_author, int num) throws SQLException {
        List<Author> fullList = selectNotEquals(id_author);
        Collections.shuffle(fullList);
        List<Author> partialList = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            partialList.add(fullList.get(i));
        }
        return partialList;
    }

}
