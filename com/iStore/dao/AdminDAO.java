package com.iStore.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.iStore.config.DatabaseConfig;
import com.iStore.model.Item;

/**
 * Classe AdminDAO pour gérer les opérations d'administration sur la base de données.
 * Cette classe permet de créer, lire, mettre à jour et supprimer des entités telles que les magasins, les articles, et les utilisateurs.
 */
public class AdminDAO {
    /**
     * Crée un nouveau magasin dans la base de données.
     *
     * @param ID   L'identifiant du magasin.
     * @param Name Le nom du magasin.
     * @return true si la création a réussi, false sinon.
     * @throws SQLException si une erreur SQL survient.
     */
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

    /**
     * Crée un nouvel article dans la base de données.
     *
     * @param item L'article à créer.
     * @return true si l'article est créé avec succès, false sinon.
     * @throws SQLException si une erreur SQL survient.
     */
    public boolean createItem(Item item) throws SQLException{
        String requeteSQL = "INSERT INTO ITEMS (NAME, PRICE, STOCK) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConfig.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(requeteSQL)) {
                pstmt.setString(1, item.getName());
                pstmt.setBigDecimal(2, item.getPrice());
                pstmt.setInt(3, item.getStock());
                pstmt.executeUpdate();

                return true;
        }
    }

    /**
     * Récupère l'ID d'un article à partir de son nom.
     *
     * @param Name Le nom de l'article.
     * @return L'ID de l'article ou -1 si non trouvé.
     * @throws SQLException si une erreur SQL survient.
     */
    public int getItemID(String Name) throws SQLException {
        String requeteSQL = "SELECT ID FROM ITEMS WHERE NAME = ?";

        try (Connection conn = DatabaseConfig.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(requeteSQL)) {
                pstmt.setString(1, Name);
                ResultSet rs = pstmt.executeQuery();

                if (rs.next()) {
                    return rs.getInt("ID");
                }
            }
        return -1;
    }

    /**
     * Récupère l'ID d'un magasin à partir de son nom.
     *
     * @param Name Le nom du magasin.
     * @return L'ID du magasin ou -1 si non trouvé.
     * @throws SQLException si une erreur SQL survient.
     */
    public int getStoreID(String Name) throws SQLException {
        String requeteSQL = "SELECT ID FROM STORE WHERE NAME = ?";

        try (Connection conn = DatabaseConfig.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(requeteSQL)) {
                pstmt.setString(1, Name);
                ResultSet rs = pstmt.executeQuery();

                if (rs.next()) {
                    return rs.getInt("ID");
                }
            }
        return -1;
    }

    /**
     * Récupère le nom d'un magasin à partir de son ID.
     *
     * @param ID L'ID du magasin.
     * @return Le nom du magasin ou une chaîne vide si non trouvé.
     * @throws SQLException si une erreur SQL survient.
     */
    public String getStoreName(int ID) throws SQLException {
        String requeteSQL = "SELECT NAME FROM STORE WHERE ID = ?";

        try (Connection conn = DatabaseConfig.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(requeteSQL)) {
                pstmt.setInt(1, ID);
                ResultSet rs = pstmt.executeQuery();

                if (rs.next()) {
                    return rs.getString("NAME");
                }
            }
        return "";
    }

    /**
     * Ajoute un article à l'inventaire d'un magasin.
     *
     * @param itemID  L'ID de l'article.
     * @param storeID L'ID du magasin.
     * @return true si l'ajout a réussi, false sinon.
     * @throws SQLException si une erreur SQL survient.
     */
    public boolean addInventory(int itemID, int storeID) throws SQLException {
        String requeteSQL = "INSERT INTO INVENTORY (ITEM_ID, STORE_ID) VALUES (?, ?)";

        try (Connection conn = DatabaseConfig.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(requeteSQL)) {
                pstmt.setInt(1, itemID);
                pstmt.setInt(2, storeID);
                int affectedRows = pstmt.executeUpdate();

                return affectedRows > 0;
            }
    }

    /**
     * Vérifie si un magasin existe par son nom.
     *
     * @param Name Le nom du magasin.
     * @return true si le magasin existe, false sinon.
     * @throws SQLException si une erreur SQL survient.
     */
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

    /**
     * Vérifie si un article existe par son nom.
     *
     * @param Name Le nom de l'article.
     * @return true si l'article existe, false sinon.
     * @throws SQLException si une erreur SQL survient.
     */
    public boolean verifyItemName(String Name) throws SQLException{
        String requeteSQL = "SELECT COUNT(*) FROM ITEMS WHERE NAME = ?";

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

    /**
     * Supprime un magasin de la base de données.
     *
     * @param Name Le nom du magasin à supprimer.
     * @return true si la suppression a réussi, false sinon.
     * @throws SQLException si une erreur SQL survient.
     */
    public boolean deleteStore(String Name) throws SQLException {
        String requeteSQL = "DELETE FROM STORE WHERE NAME = ?";

        try (Connection conn = DatabaseConfig.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(requeteSQL)) {
                pstmt.setString(1, Name);
                int affectedRows = pstmt.executeUpdate();

                return affectedRows > 0;
            }
    }

    /**
     * Supprime un utilisateur de la base de données par son e-mail.
     *
     * @param Email L'e-mail de l'utilisateur à supprimer.
     * @return true si la suppression a réussi, false sinon.
     * @throws SQLException si une erreur SQL survient.
     */
    public boolean deleteUser(String Email) throws SQLException {
        String requeteSQL = "DELETE FROM USER WHERE EMAIL = ?";

        try (Connection conn = DatabaseConfig.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(requeteSQL)) {
                pstmt.setString(1, Email);
                int affectedRows = pstmt.executeUpdate();

                return affectedRows > 0;
            }
    }

    /**
     * Supprime un article de l'inventaire et de la table des articles dans la base de données.
     * Cette méthode effectue deux opérations dans une transaction :
     * Suppression de l'article de la table INVENTORY pour un magasin spécifique.
     * Suppression de l'article de la table ITEMS si celui-ci n'est plus présent dans aucun inventaire.
     * En cas d'erreur lors de la suppression, la transaction est annulée (rollback).
     *  
     * @param itemName  Le nom de l'article à supprimer.
     * @param storeName Le nom du magasin associé à l'article.
     * @return {@code true} si l'article a été supprimé avec succès, {@code false} sinon.
     * @throws SQLException si une erreur SQL survient lors de la suppression.
     */ 
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
            conn.setAutoCommit(false); 
            try (PreparedStatement pstmt1 = conn.prepareStatement(deleteInventorySQL)) {
                pstmt1.setString(1, itemName);
                pstmt1.setString(2, storeName);
                pstmt1.executeUpdate();
            }
    
            try (PreparedStatement pstmt2 = conn.prepareStatement(deleteItemSQL)) {
                pstmt2.setString(1, itemName);
                int affectedRows = pstmt2.executeUpdate();
    
                conn.commit(); 
                return affectedRows > 0;
            } catch (SQLException e) {
                conn.rollback(); 
                throw e;
            }
        }
    }
    
    /**
     * Met à jour les informations d'un utilisateur dans la base de données.
     *
     * @param emailToUpdate L'email de l'utilisateur à mettre à jour.
     * @param Email         Le nouvel email de l'utilisateur.
     * @param Pseudo        Le nouveau pseudo de l'utilisateur.
     * @param PasswordHash  Le nouveau hash de mot de passe.
     * @param Salt          Le nouveau sel pour le mot de passe.
     * @param Role          Le rôle de l'utilisateur.
     * @return true si la mise à jour a réussi, false sinon.
     * @throws SQLException si une erreur SQL survient.
     */
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

    /**
     * Met à jour les informations d'un article dans la base de données.
     *
     * @param itemToUpdate Le nom de l'article à mettre à jour.
     * @param Name         Le nouveau nom de l'article.
     * @param Price        Le nouveau prix de l'article.
     * @param Stock        Le nouveau stock de l'article.
     * @return true si la mise à jour a réussi, false sinon.
     * @throws SQLException si une erreur SQL survient.
     */
    public boolean updateItem(String itemToUpdate, String Name, BigDecimal Price, int Stock) throws SQLException {
        String requeteSQL = "UPDATE ITEMS SET NAME = ?, PRICE = ?, STOCK = ? WHERE NAME = ?";

        try (Connection conn = DatabaseConfig.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(requeteSQL)) {
                pstmt.setString(1, Name);
                pstmt.setBigDecimal(2, Price);
                pstmt.setInt(3, Stock);
                pstmt.setString(4, itemToUpdate);
                int affectedRows = pstmt.executeUpdate();

                return affectedRows > 0;
            }
    }
}
