package com.iStore;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {
    public void createUser(User user) throws SQLException {
        String sql = "INSERT INTO USER (EMAIL, PSEUDO, PASSWORD_HASH) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConfig.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, user.getEmail());
                pstmt.setString(2, user.getPseudo());
                pstmt.setString(3, user.getPasswordHash());
                pstmt.executeUpdate();

                System.out.println("Compte créé avec succès !");

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
}
