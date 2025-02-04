package com.iStore.dao;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;
import com.iStore.config.DatabaseConfig;
import com.iStore.model.User;

/**
 * Classe UserDAO pour gérer les opérations liées aux utilisateurs dans la base de données.
 * Cette classe permet de créer des utilisateurs, de vérifier des comptes et de récupérer des informations utilisateur.
 */
public class UserDAO {

    /**
     * Vérifie s'il existe au moins un utilisateur dans la base de données.
     *
     * @return {@code true} s'il existe au moins un utilisateur, {@code false} sinon.
     * @throws SQLException si une erreur SQL survient lors de la requête.
     */
    public boolean firstUser() throws SQLException {
        String requeteSQL = "SELECT COUNT(*) FROM USER";

        try (Connection conn = DatabaseConfig.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(requeteSQL)) {
                ResultSet rs = pstmt.executeQuery();

                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            return false;
            }
    }

    /**
     * Crée un nouvel utilisateur dans la base de données.
     *
     * @param user L'objet {@link User} contenant les informations de l'utilisateur à créer.
     * @return {@code true} si l'utilisateur est créé avec succès, {@code false} sinon.
     * @throws SQLException si une erreur SQL survient lors de la création de l'utilisateur.
     */
    public boolean createUser(User user) throws SQLException {
        String requeteSQL = "INSERT INTO USER (EMAIL, PSEUDO, PASSWORD_HASH, SALT, ROLE, STORE_ID) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConfig.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(requeteSQL)) {
                pstmt.setString(1, user.getEmail());
                pstmt.setString(2, user.getPseudo());
                pstmt.setString(3, user.getPasswordHash());
                pstmt.setString(4, user.getSalt());
                pstmt.setString(5, user.getRole());
                pstmt.setInt(6, user.getStoreID());
                pstmt.executeUpdate();

                return true;
            }
    }

    /**
     * Vérifie si un e-mail est déjà enregistré dans la base de données.
     *
     * @param Email L'e-mail à vérifier.
     * @return {@code true} si l'e-mail existe déjà, {@code false} sinon.
     * @throws SQLException si une erreur SQL survient lors de la vérification de l'e-mail.
     */
    public boolean verifyEmail(String Email) throws SQLException {
        String requeteSQL = "SELECT COUNT(*) FROM USER WHERE EMAIL = ?";

        try (Connection conn = DatabaseConfig.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(requeteSQL)) {
                pstmt.setString(1, Email);
                ResultSet rs = pstmt.executeQuery();

                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
            return false;
    }

    /**
     * Vérifie les informations d'un compte utilisateur à partir de l'e-mail et du mot de passe.
     *
     * @param Email    L'e-mail de l'utilisateur.
     * @param Password Le mot de passe de l'utilisateur à vérifier.
     * @return Un objet {@link User} si la vérification est réussie, {@code null} sinon.
     * @throws Exception si une erreur de hachage ou SQL survient lors de la vérification du compte.
     */
    public User verifyAccount(String Email, String Password) throws Exception{
        String requeteSQL = "SELECT ID, EMAIL, PSEUDO, PASSWORD_HASH, SALT, ROLE, STORE_ID FROM USER WHERE EMAIL = ?";

        try (Connection conn = DatabaseConfig.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(requeteSQL)) {
                pstmt.setString(1, Email);
                ResultSet rs = pstmt.executeQuery();

                if (rs.next()) {
                    int storedID = rs.getInt("ID");
                    String storedEmail = rs.getString("EMAIL");
                    String storedPseudo = rs.getString("PSEUDO");
                    String storedPasswordHash = rs.getString("PASSWORD_HASH");
                    String storedSalt = rs.getString("SALT");
                    String storedRole = rs.getString("ROLE");
                    int storedStoreID = rs.getInt("STORE_ID");

                    byte[] salt = Base64.getDecoder().decode(storedSalt);
                    
                    MessageDigest md = MessageDigest.getInstance("SHA-512");
                    md.update(salt);
                    byte[] inputHashByte = md.digest(Password.getBytes(StandardCharsets.UTF_8));
                    String inputHashBase64 = Base64.getEncoder().encodeToString(inputHashByte);

                    if (inputHashBase64.equals(storedPasswordHash)) {
                        return new User(storedID, storedEmail, storedPseudo, storedPasswordHash, storedSalt, storedRole, storedStoreID);
                    }
                }
            }
            return null;
    }

    /**
     * Récupère l'ID du magasin associé à un utilisateur à partir de son e-mail.
     *
     * @param Email L'e-mail de l'utilisateur.
     * @return L'ID du magasin associé ou -1 si aucun magasin n'est trouvé.
     * @throws SQLException si une erreur SQL survient lors de la récupération de l'ID du magasin.
     */
    public int getUserStoreID(String Email) throws SQLException {
        String requeteSQL = "SELECT STORE_ID FROM USER WHERE EMAIL = ?";

        try (Connection conn = DatabaseConfig.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(requeteSQL)) {
                pstmt.setString(1, Email);

                ResultSet rs = pstmt.executeQuery();

                if (rs.next()) {
                    return rs.getInt("STORE_ID");
                }
            }
        return -1;
    }
}
