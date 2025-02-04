package com.iStore.ui.panels;

import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.iStore.config.DatabaseConfig;
import com.iStore.model.Store;

public class DisplayStorePanel extends JPanel{
    public DisplayStorePanel (CardLayout cardLayout,  JPanel mainPanel) {
        setLayout(new BorderLayout());
        // Titre
        JLabel titleLabel = new JLabel("Panneau Magasins", JLabel.CENTER);
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
