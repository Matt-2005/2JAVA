package com.iStore;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
}
