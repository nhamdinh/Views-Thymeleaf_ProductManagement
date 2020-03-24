package com.codegym.service;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBDao {
    protected static Connection initializeDatabase() throws Exception {
        String dbName, dbUsername, dbPassword, characterEncoding;
        Connection conn = null;
        try {
            String dbDriver = "com.mysql.jdbc.Driver";
            String dbURL = "jdbc:mysql://localhost:3306/";
            dbName = "Product_test";
            dbUsername = "root";
            dbPassword = "password1";
            characterEncoding = "?characterEncoding=UTF-8";

            Class.forName(dbDriver);
            conn = DriverManager.getConnection(dbURL + dbName + characterEncoding,
                    dbUsername,
                    dbPassword);
            System.out.println("da ket noi");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
}
