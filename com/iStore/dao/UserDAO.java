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


public class UserDAO {
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
