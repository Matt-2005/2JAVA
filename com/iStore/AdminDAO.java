package com.iStore;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminDAO {
    public boolean createStore(int ID, String Name) throws SQLException{
        String requeteSQL = "INSERT INTO STORE (ID, NAME) VALUES (?, ?)";

        try (Connection conn = DatabaseConfig.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(requeteSQL)) {
                pstmt.setInt(1, ID);
                pstmt.setString(2, Name);
                pstmt.executeUpdate();

                return true;
        }
    }

    public boolean verifyName(String Name) throws SQLException{
        String requeteSQL = "SELECT COUNT(*) FROM STORE WHERE NAME = ?";

        try (Connection conn = DatabaseConfig.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(requeteSQL)) {
                pstmt.setString(1, Name);
                ResultSet rs = pstmt.executeQuery();

                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        return false;
    }

    public boolean deleteStore(String Name) throws SQLException {
        String requeteSQL = "DELETE FROM STORE WHERE NAME = ?";

        try (Connection conn = DatabaseConfig.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(requeteSQL)) {
                pstmt.setString(1, Name);
                int affectedRows = pstmt.executeUpdate();

                return affectedRows > 0;
            }
    }

    public boolean deleteUser(String Email) throws SQLException {
        String requeteSQL = "DELETE FROM USER WHERE EMAIL = ?";

        try (Connection conn = DatabaseConfig.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(requeteSQL)) {
                pstmt.setString(1, Email);
                int affectedRows = pstmt.executeUpdate();

                return affectedRows > 0;
            }
    }

    public boolean deleteItem(String itemName, String storeName) throws SQLException {
        String deleteInventorySQL = """
            DELETE INV 
            FROM INVENTORY INV
            INNER JOIN ITEMS I ON I.ID = INV.ITEM_ID
            INNER JOIN STORE S ON INV.STORE_ID = S.ID
            WHERE I.NAME = ? AND S.NAME = ?
        """;
    
        String deleteItemSQL = """
            DELETE FROM ITEMS
            WHERE NAME = ? AND ID NOT IN (SELECT ITEM_ID FROM INVENTORY)
        """;
    
        try (Connection conn = DatabaseConfig.getConnection()) {
            conn.setAutoCommit(false); // Début de la transaction
    
            // 1️⃣ Suppression dans INVENTORY
            try (PreparedStatement pstmt1 = conn.prepareStatement(deleteInventorySQL)) {
                pstmt1.setString(1, itemName);
                pstmt1.setString(2, storeName);
                pstmt1.executeUpdate();
            }
    
            // 2️⃣ Suppression dans ITEMS
            try (PreparedStatement pstmt2 = conn.prepareStatement(deleteItemSQL)) {
                pstmt2.setString(1, itemName);
                int affectedRows = pstmt2.executeUpdate();
    
                conn.commit(); // Valider la transaction
                return affectedRows > 0;
            } catch (SQLException e) {
                conn.rollback(); // En cas d'erreur, annuler la transaction
                throw e;
            }
        }
    }
    

    public boolean updateUser(String emailToUpdate, String Email, String Pseudo, String PasswordHash, String Salt, String Role) throws SQLException{
        String requeteSQL = "UPDATE USER SET EMAIL = ?, PSEUDO = ?, PASSWORD_HASH = ?, SALT = ?, ROLE = ? WHERE EMAIL = ?";

        try (Connection conn = DatabaseConfig.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(requeteSQL)) {
                pstmt.setString(1, Email);
                pstmt.setString(2, Pseudo);
                pstmt.setString(3, PasswordHash);
                pstmt.setString(4, Salt);
                pstmt.setString(5, Role);
                pstmt.setString(6, emailToUpdate);
                int affectedRows = pstmt.executeUpdate();

                return affectedRows > 0;
            }
    }

    


}
