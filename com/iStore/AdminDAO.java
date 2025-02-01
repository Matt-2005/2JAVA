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

    public boolean deleteUser(String Pseudo) throws SQLException {
        String requeteSQL = "DELETE FROM USER WHERE PSEUDO = ?";

        try (Connection conn = DatabaseConfig.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(requeteSQL)) {
                pstmt.setString(1, Pseudo);
                int affectedRows = pstmt.executeUpdate();

                return affectedRows > 0;
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
