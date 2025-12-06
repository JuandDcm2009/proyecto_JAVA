package com.ponscio.configuration;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

public class ConnectionDB {
    
    private static String URL = "jdbc:mysql://localhost:3307/crediya_db";
    private static String USER = "root";
    private static String PASSWORD = "admin";

    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
    
}
