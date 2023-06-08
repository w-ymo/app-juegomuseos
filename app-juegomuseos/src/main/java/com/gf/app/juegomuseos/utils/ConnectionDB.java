/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gf.app.juegomuseos.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * ConnectionDB: clase que permite la conexion con la base de datos.
 *
 * @author priparno
 * @author fercaslu
 */
public class ConnectionDB {

    /**
     * con: conector a la base de datos tipo {@link Connection}.
     */
    private static Connection con;
    /**
     * URL: la url de la base de datos.
     */
    private static final String URL = "jdbc:mysql://localhost:3306/dim_gf";
    /**
     * USER: el nombre del usuario.
     */
    private static final String USER = "root";
    /**
     * PASSWD: la contrasenia a utilizar.
     */
    private static final String PASSWD = "";

    /**
     * getConnection: permite la conexion con la base de datos devolviendo un
     * objeto {@link Connection}.
     *
     * @see Connection
     * @see DriverManager
     *
     * @return un objeto de tipo {@link Connection}
     * @throws SQLException si no consigue acceder a la base de datos
     */
    public static Connection getConnection() throws SQLException {
        con = DriverManager.getConnection(URL, USER, PASSWD);
        return con;
    }
}
