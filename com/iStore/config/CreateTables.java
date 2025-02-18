package com.iStore.config;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Classe responsable de la création des tables nécessaires à l'application iStore dans la base de données.
 */
public class CreateTables {
        /**
     * Crée les tables de la base de données si elles n'existent pas déjà.
     * Les tables créées sont : USER, STORE, ITEMS, et INVENTORY.
     *
     * @throws SQLException si une erreur survient lors de l'exécution des requêtes SQL.
     */
        public void createTable() throws SQLException{
        String createUserTable = """
                CREATE TABLE IF NOT EXISTS USER (
                ID INT AUTO_INCREMENT PRIMARY KEY,
                EMAIL VARCHAR(255) NOT NULL UNIQUE,
                PSEUDO VARCHAR(100) NOT NULL, 
                PASSWORD_HASH VARCHAR(255) NOT NULL,
                SALT VARCHAR(100) NOT NULL,
                ROLE VARCHAR(100) NOT NULL DEFAULT 'Employé',
                STORE_ID INT NULL,
                FOREIGN KEY (STORE_ID) REFERENCES STORE(ID)
                );
                """;
        
        String createStoreTable = """
                CREATE TABLE IF NOT EXISTS STORE (
                ID INT AUTO_INCREMENT PRIMARY KEY,
                NAME VARCHAR(100) NOT NULL
                );
                """;

        String createItemTable = """
                CREATE TABLE IF NOT EXISTS ITEMS (
                ID INT AUTO_INCREMENT PRIMARY KEY,
                NAME VARCHAR(100) NOT NULL,
                PRICE DECIMAL(10,2) NOT NULL,
                STOCK INT NOT NULL
                );
                """;
        
        String createInventoryTable = """
                CREATE TABLE IF NOT EXISTS INVENTORY (
                ID INT AUTO_INCREMENT PRIMARY KEY,
                ITEM_ID INT,
                STORE_ID INT,
                FOREIGN KEY (ITEM_ID) REFERENCES ITEMS(ID),
                FOREIGN KEY (STORE_ID) REFERENCES STORE(ID)
                );
                """;

        try (Connection conn = DatabaseConfig.getConnection();
            Statement stmt = conn.createStatement()) {
                stmt.execute(createStoreTable);
                System.out.println("Table Store crée avec succès !");
                stmt.execute(createUserTable);
                System.out.println("Table User crée avec succès !");
                stmt.execute(createItemTable);
                System.out.println("Table Items crée avec succès !");
                stmt.execute(createInventoryTable);
                System.out.println("Table Inventory crée avec succès !");
            } catch (SQLException e) {
                System.err.println("Erreur lors de la création des tables : " + e.getMessage());
            }
    }
}
