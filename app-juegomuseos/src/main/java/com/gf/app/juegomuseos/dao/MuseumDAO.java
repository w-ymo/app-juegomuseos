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
 * MuseumDAO: es la clase que permite el acceso a la base de datos. En
 * especifico a la tabla 'museos'.
 *
 * @author priparno
 * @author fercaslu
 */
public class MuseumDAO {

    /**
     * selectAll: devuelve una lista de todos los elementos de museos de la base
     * de datos.
     *
     * @return una lista de objeto {@link Museum}
     * @throws SQLException si no consigue acceder a la base de datos (error
     * sintactico o base de datos no encontrada)
     */
    public List<Museum> selectAll() throws SQLException {
        String sql = "SELECT * FROM museos";
        List<Museum> list = new ArrayList<>();
        try ( Connection con = ConnectionDB.getConnection()) {
            Statement s = con.prepareStatement(sql);
            try ( ResultSet rs = s.executeQuery(sql)) {
                while (rs.next()) {
                    Museum m = new Museum();
                    m.setId_museo(rs.getInt("id_museo"));
                    m.setNombre_museo(rs.getString("nombre_museo"));
                    m.setId_pais(rs.getInt("id_pais"));
                    list.add(m);
                }
            }
        }
        return list;
    }

    /**
     * selectId: devuelve un objeto {@link Museum} que tenga el mismo
     * identificador que el pasado por parametro.
     *
     * @param id un entero que representa el id del museo
     * @return un objeto {@link Museum}
     * @throws SQLException si no consigue acceder a la base de datos (error
     * sintactico o base de datos no encontrada)
     */
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

    /**
     * selectNum: devuelve una lista de {@link Museum} aleatoria dependiendo del
     * numero introducido por parametro. La lista contendra tantos elementos
     * como el numero pasado por parametro.
     *
     * @param num un entero para el numero de museos que tiene la lista
     * @return una lista de {@link Museum}
     * @throws SQLException si no consigue acceder a la base de datos (error
     * sintactico o base de datos no encontrada)
     */
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
