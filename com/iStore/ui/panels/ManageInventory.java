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

/**
 * Panneau permettant à l'employé de gérer l'inventaire
 * Lorsque l'employé clique sur gérer mon magasin dans EmployeePanel.java, il va pouvoir voir tous les items du magasin auquel il est affilier puis les gérer.
 * Pour la gestion, il peut ajouter, supprimer et mettre à jour un item.
 */
public class ManageInventory extends JPanel{
    /**
     * Constructeur du panneau pour gérer l'inventaire de son magasin. (coté employé)
     * 
     * @param cardLayout Le gestionnaire de disposition pour la navigation entre les panneaux.
     * @param mainPanel  Le panneau principal contenant tous les écrans de l'application.
     * @param sessionManager Le gestionnaire de session pour récupérer des informations de session.
     */
    public ManageInventory(CardLayout cardLayout, JPanel mainPanel, SessionManager sessionManager) {
        setLayout(new BorderLayout());
        JLabel titleLabel = new JLabel("Panneau Inventaire", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        add(titleLabel, BorderLayout.NORTH);

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
        
        for (Item item : items) {
            model.addRow(new Object[]{item.getId(), item.getName(), item.getPrice(), item.getStock()});
        }

        JTable employeeTable = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(employeeTable);
        add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton backButton = new JButton("Retour");
        backButton.addActionListener(e -> cardLayout.show(mainPanel, "EmployeePanel"));
        JButton addItem = new JButton("Ajouter un item");
        addItem.addActionListener(e -> cardLayout.show(mainPanel, "AddItem"));
        JButton deleteItem = new JButton("Supprimer un item");
        deleteItem.addActionListener(e -> {
            mainPanel.add(new DeleteItemPanel(cardLayout, mainPanel, sessionManager), "DeleteItem");
            cardLayout.show(mainPanel, "DeleteItem");
        });
        JButton updateItem = new JButton("Mettre à jour un item");
        updateItem.addActionListener(e -> {
            mainPanel.add(new UpdateItemPanel(cardLayout, mainPanel), "UpdateItem");
            cardLayout.show(mainPanel, "UpdateItem");
        });

        buttonPanel.add(addItem);
        buttonPanel.add(deleteItem);
        buttonPanel.add(updateItem);
        buttonPanel.add(backButton);

        add(buttonPanel, BorderLayout.SOUTH);
    }
}
