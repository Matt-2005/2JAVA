package com.iStore.ui.panels;

import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.iStore.config.DatabaseConfig;
import com.iStore.dao.AdminDAO;
import com.iStore.model.User;

public class DisplayUserPanel extends JPanel{
    public DisplayUserPanel(CardLayout cardLayout, JPanel mainPanel) {
        AdminDAO adminDAO = new AdminDAO();
        setLayout(new BorderLayout());
        // Titre
        JLabel titleLabel = new JLabel("Panneau Utilisateurs", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        add(titleLabel, BorderLayout.NORTH);

        // Définition des colonnes
        String[] columnNames = {"ID", "Email", "Pseudo", "Rôle", "Magasin"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0); // Modèle de tableau vide
        model.setRowCount(0);
        List<User> employees = new ArrayList<>();
        
        String requeteSQL = "SELECT ID, EMAIL, PSEUDO, ROLE, STORE_ID FROM USER";
        try (Connection conn = DatabaseConfig.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(requeteSQL);
            ResultSet rs = pstmt.executeQuery()) {

                while (rs.next()) {
                    int Id = rs.getInt("ID");
                    String Email = rs.getString("EMAIL");
                    String Pseudo = rs.getString("PSEUDO");
                    String Role = rs.getString("ROLE");
                    int storeID = rs.getInt("STORE_ID");

                    String storeName = adminDAO.getStoreName(storeID); 

                    employees.add(new User(Id, Email, Pseudo, "", "", Role, storeID));

                    model.addRow(new Object[]{Id, Email, Pseudo, Role, storeName});
                }
                
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Erreur SQL : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        
        // Création du JTable avec le modèle dynamique
        JTable employeeTable = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(employeeTable);
        add(scrollPane, BorderLayout.CENTER);

        // Panel pour les boutons
        JPanel buttonPanel = new JPanel();
        JButton backButton = new JButton("Retour");

        // Bouton Retour vers AdminDashboard
        backButton.addActionListener(e -> cardLayout.show(mainPanel, "AdminDashboard"));

        // Ajouter les boutons au panel des boutons
        buttonPanel.add(backButton);

        add(buttonPanel, BorderLayout.SOUTH);
    }
}
