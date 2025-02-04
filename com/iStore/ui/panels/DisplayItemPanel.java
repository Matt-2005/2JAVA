package com.iStore.ui.panels;

import java.awt.*;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.iStore.config.DatabaseConfig;
import com.iStore.model.Item;
import com.iStore.utils.SessionManager;

public class DisplayItemPanel extends JPanel{
    public DisplayItemPanel(CardLayout cardLayout, JPanel mainPanel, SessionManager sessionManager) {
        setLayout(new BorderLayout());
        // Titre
        JLabel titleLabel = new JLabel("Panneau Inventaire", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        add(titleLabel, BorderLayout.NORTH);

        // Définition des colonnes
        String[] columnNames = {"ID", "Name", "Price", "Stock"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        List<Item> items = new ArrayList<>();

        String requeteSQL = """
            SELECT I.ID, I.NAME, I.PRICE, I.STOCK
            FROM ITEMS I
            INNER JOIN INVENTORY INV ON I.ID = INV.ITEM_ID
            INNER JOIN STORE S ON INV.STORE_ID = S.ID
            WHERE S.NAME = ?
        """;
        try (Connection conn = DatabaseConfig.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(requeteSQL)) {
            pstmt.setString(1, sessionManager.getStoreName());
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("ID");
                    String name = rs.getString("NAME");
                    BigDecimal price = rs.getBigDecimal("PRICE"); 
                    int stock = rs.getInt("STOCK");

                    items.add(new Item(id, name, price, stock));
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erreur SQL : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }

        
        // **Remplir le tableau avec les données de la liste**
        for (Item item : items) {
            model.addRow(new Object[]{item.getId(), item.getName(), item.getPrice(), item.getStock()});
        }

        // Création du JTable avec le modèle dynamique
        JTable employeeTable = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(employeeTable);
        add(scrollPane, BorderLayout.CENTER);

        // Panel pour les boutons
        JPanel buttonPanel = new JPanel();
        JButton backButton = new JButton("Retour");
        backButton.addActionListener(e -> cardLayout.show(mainPanel, "WhatInventoryToManage"));
        JButton addItem = new JButton("Ajouter un item");
        addItem.addActionListener(e -> cardLayout.show(mainPanel, "AddItem"));
        JButton deleteItem = new JButton("Supprimer un item");
        deleteItem.addActionListener(e -> {
            cardLayout.show(mainPanel, "DeleteItem");
        });
        JButton updateItem = new JButton("Mettre à jour un item");
        updateItem.addActionListener(e -> {
            cardLayout.show(mainPanel, "UpdateItem");
        });

        // Bouton Retour vers AdminDashboard

        // Ajouter les boutons au panel des boutons
        buttonPanel.add(addItem);
        buttonPanel.add(deleteItem);
        buttonPanel.add(updateItem);
        buttonPanel.add(backButton);



        add(buttonPanel, BorderLayout.SOUTH);
    }
}
