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

public class DeleteUserPanel extends JPanel{
    public DeleteUserPanel(CardLayout cardLayout, JPanel mainPanel) {
        AdminDAO adminDAO = new AdminDAO();
        setLayout(new GridLayout(3, 3, 10, 10));
        // Titre
        JLabel titleLabel = new JLabel("Liste des utilisateurs", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        add(titleLabel, BorderLayout.NORTH);

        // Définition des colonnes
        String[] columnNames = {"ID", "Email", "Pseudo", "Rôle"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0); // Modèle de tableau vide

        List<User> employees = new ArrayList<>();
        
        String requeteSQL = "SELECT ID, EMAIL, PSEUDO, ROLE FROM USER";
        try (Connection conn = DatabaseConfig.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(requeteSQL);
            ResultSet rs = pstmt.executeQuery()) {

                while (rs.next()) {
                    int Id = rs.getInt("ID");
                    String Email = rs.getString("EMAIL");
                    String Pseudo = rs.getString("PSEUDO");
                    String Role = rs.getString("ROLE");

                    employees.add(new User(Id, Email, Pseudo, "", "", Role, -1));
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Erreur SQL : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        
        // **Remplir le tableau avec les données de la liste**
        for (User user : employees) {
            model.addRow(new Object[]{user.getId(), user.getEmail(), user.getPseudo(), user.getRole()});
        }

        // Création du JTable avec le modèle dynamique
        JTable employeeTable = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(employeeTable);
        add(scrollPane, BorderLayout.CENTER);

        add(new JLabel("Email de l'utilisateur à suprimer :", JLabel.CENTER));
        JTextField emailField = new JTextField();
        add(emailField);
        // Panel pour les boutons
        JPanel buttonPanel = new JPanel();
        JButton backButton = new JButton("Retour");
        JButton validationButton = new JButton("Suprimer");
        
        // Bouton Retour vers AdminDashboard
        backButton.addActionListener(e -> cardLayout.show(mainPanel, "AdminDashboard"));
        validationButton.addActionListener(e -> {
            try {
                String Email = emailField.getText();
                if (adminDAO.deleteUser(Email)) {
                    JOptionPane.showMessageDialog(this, "Utilisateur " + Email + " supprimé avec succès !.", "iStore", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "Erreur lors de la suppression de l'utilisateur.", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                System.out.println("Erreur lors de la suppression de l'utilisateur : " + ex.getMessage());
            }
            
        });

        // Ajouter les boutons au panel des boutons
        buttonPanel.add(backButton);
        buttonPanel.add(validationButton);

        add(buttonPanel, BorderLayout.CENTER);
    }
}
