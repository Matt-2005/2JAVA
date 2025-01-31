package com.iStore;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.xml.crypto.Data;

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
}
