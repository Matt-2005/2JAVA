package com.iStore;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DatabaseConfig {
    public static Connection getConnection() throws SQLException {
        final String URL = "jdbc:mysql://localhost:8889/iStore";
        final String USER = "root";
        final String PASSWORD = "root";
    
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
