package com.iStore.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Classe de configuration de la base de données pour l'application iStore.
 * Fournit une méthode pour établir une connexion à la base de données MySQL.
 */
public class DatabaseConfig {
    /**
     * Établit une connexion à la base de données MySQL.
     *
     * @return Une connexion active à la base de données.
     * @throws SQLException si une erreur survient lors de la tentative de connexion à la base de données.
     */
    public static Connection getConnection() throws SQLException {
        final String URL = "jdbc:mysql://localhost:8889/iStore";
        final String USER = "root";
        final String PASSWORD = "root";
    
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
