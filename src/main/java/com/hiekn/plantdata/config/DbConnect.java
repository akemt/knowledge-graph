package com.hiekn.plantdata.config;

import java.sql.Connection;

import java.sql.DriverManager;

import java.sql.SQLException;

/**
 * 建立数据库连接
 */
public class DbConnect {

    Connection conn = null;

    public Connection getConnetion() {

        try {

            conn = DriverManager.getConnection("jdbc:neo4j:http://localhost:7474/", "neo4j", "123456");

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return conn;

    }
}
