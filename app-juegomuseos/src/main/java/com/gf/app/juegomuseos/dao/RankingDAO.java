/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gf.app.juegomuseos.dao;

import com.gf.app.juegomuseos.models.Ranking;
import com.gf.app.juegomuseos.utils.ConnectionDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * MuseumDAO: es la clase que permite el acceso a la base de datos. En
 * especifico a la tabla 'ranking'.
 *
 * @author priparno
 * @author fercaslu
 */
public class RankingDAO {

    /**
     * selectAll: devuelve una lista de todos los elementos de ranking de la
     * base de datos.
     *
     * @return una lista de objeto {@link Ranking}
     * @throws SQLException si no consigue acceder a la base de datos (error
     * sintactico o base de datos no encontrada)
     */
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

    /**
     * selectId: devuelve un objeto {@link Ranking} que tenga el mismo
     * identificador que el pasado por parametro.
     *
     * @param id un entero que representa el id del ranking
     * @return un objeto {@link Ranking}
     * @throws SQLException si no consigue acceder a la base de datos (error
     * sintactico o base de datos no encontrada)
     */
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

    /**
     * selectNum: devuelve una lista de {@link Ranking} aleatoria dependiendo
     * del numero introducido por parametro. La lista contendra tantos elementos
     * como el numero pasado por parametro.
     *
     * @param num un entero para el numero de rankings que tiene la lista
     * @return una lista de {@link Ranking}
     * @throws SQLException si no consigue acceder a la base de datos (error
     * sintactico o base de datos no encontrada)
     */
    public List<Ranking> selectNum(int num) throws SQLException {
        List<Ranking> fullList = selectAll();
        Collections.shuffle(fullList);
        List<Ranking> partialList = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            partialList.add(fullList.get(i));
        }
        return partialList;
    }

    /**
     * insert: se inserta en la base de datos un ranking tipo {@link Ranking}
     * pasado por parametro.
     *
     * @param r un objeto de tipo {@link Ranking}
     * @return true -> se ha efectuado correctamente, false -> no se ha
     * efectuado correctamente
     * @throws SQLException si no consigue acceder a la base de datos (error
     * sintactico o base de datos no encontrada)
     */
    public boolean insert(Ranking r) throws SQLException {
        try ( Connection con = ConnectionDB.getConnection()) {
            PreparedStatement ps = con.prepareStatement("INSERT INTO ranking (nombre_usuario,puntuacion) VALUES (?,?)");
            ps.setString(1, r.getNombre_usuario());
            ps.setString(2, r.getPuntuacion());
            if (ps.executeUpdate() > 0) {
                return true;
            }
        }
        return false;
    }
}
