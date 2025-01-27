package com.iStore;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;


public class UserDAO {
    public boolean createUser(User user) throws SQLException {
        String sql = "INSERT INTO USER (EMAIL, PSEUDO, PASSWORD_HASH, SALT) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConfig.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, user.getEmail());
                pstmt.setString(2, user.getPseudo());
                pstmt.setString(3, user.getPasswordHash());
                pstmt.setString(4, user.getSalt());
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

    public boolean verifyAccount(String Email, String Password) throws Exception{
        String requeteSQL = "SELECT PASSWORD_HASH, SALT FROM USER WHERE EMAIL = ?";

        try (Connection conn = DatabaseConfig.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(requeteSQL)) {
                pstmt.setString(1, Email);
                ResultSet rs = pstmt.executeQuery();

                if (rs.next()) {
                    String storedPasswordHash = rs.getString("PASSWORD_HASH");
                    String storedSalt = rs.getString("SALT");

                    byte[] salt = Base64.getDecoder().decode(storedSalt);
                    
                    MessageDigest md = MessageDigest.getInstance("SHA-512");
                    md.update(salt);
                    byte[] inputHashByte = md.digest(Password.getBytes(StandardCharsets.UTF_8));
                    String inputHashBase64 = Base64.getEncoder().encodeToString(inputHashByte);

                    return inputHashBase64.equals(storedPasswordHash);


                }
            } catch (Exception e) {
                System.out.println("Erreur lors de la verification du login : " + e.getMessage());
            }
            return false;
    }
}
