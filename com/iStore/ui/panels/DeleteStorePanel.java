package com.iStore.ui.panels;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;

import com.iStore.config.DatabaseConfig;
import com.iStore.dao.AdminDAO;
import com.iStore.model.Store;


public class DeleteStorePanel extends JPanel{
    public DeleteStorePanel(CardLayout cardLayout, JPanel mainPanel) {
        AdminDAO adminDAO = new AdminDAO();
        setLayout(new GridLayout(3, 3, 10, 10));
        // Titre
        JLabel titleLabel = new JLabel("Liste des magasins", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        add(titleLabel, BorderLayout.NORTH);

        // Définition des colonnes
        String[] columnNames = {"ID", "Nom"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0); // Modèle de tableau vide

        List<Store> stores = new ArrayList<>();
        
        String requeteSQL = "SELECT ID, NAME FROM STORE";
        try (Connection conn = DatabaseConfig.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(requeteSQL);
            ResultSet rs = pstmt.executeQuery()) {

                while (rs.next()) {
                    int Id = rs.getInt("ID");
                    String Name = rs.getString("NAME");

                    stores.add(new Store(Id, Name));
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Erreur SQL : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        
        // **Remplir le tableau avec les données de la liste**
        for (Store store : stores) {
            model.addRow(new Object[]{store.getId(), store.getName()});
        }

        // Création du JTable avec le modèle dynamique
        JTable employeeTable = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(employeeTable);
        add(scrollPane, BorderLayout.CENTER);

        add(new JLabel("Nom du magasin a suprimer :", JLabel.CENTER));
        JTextField nameField = new JTextField();
        add(nameField);
        // Panel pour les boutons
        JPanel buttonPanel = new JPanel();
        JButton backButton = new JButton("Retour");
        JButton validationButton = new JButton("Suprimer");
        
        // Bouton Retour vers AdminDashboard
        backButton.addActionListener(e -> cardLayout.show(mainPanel, "AdminDashboard"));
        validationButton.addActionListener(e -> {
            try {
                String Name = nameField.getText();
                if (adminDAO.deleteStore(Name)) {
                    JOptionPane.showMessageDialog(this, "Magasin " + Name + " supprimé avec succès !.", "iStore", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "Erreur lors de la suppression du magasin.", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                System.out.println("Erreur lors de la suppression du magasin : " + ex.getMessage());
            }
            
        });

        // Ajouter les boutons au panel des boutons
        buttonPanel.add(backButton);
        buttonPanel.add(validationButton);

        add(buttonPanel, BorderLayout.CENTER);
    }
}
