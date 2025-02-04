package com.iStore.ui.panels;

import java.awt.*;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import com.iStore.config.DatabaseConfig;
import com.iStore.dao.AdminDAO;
import com.iStore.model.Item;
import com.iStore.utils.SessionManager;

/**
 * Panneau permettant de supprimer un Item.
 * Cette interface permet à l'administrateur et a l'employé de supprimer un item. 
 * L'employé peut le faire seulement dans le magasin pour qui il travaille.
 * L'administrateur peut le faire pour tous les items de chaque magasin.
 */
public class DeleteItemPanel extends JPanel{
    /**
     * Constructeur du panneau de suppression d'Items.
     * @param cardLayout Le gestionnaire de disposition pour la navigation entre les panneaux.
     * @param mainPanel  Le panneau principal contenant tous les écrans de l'application.
     */
    public DeleteItemPanel(CardLayout cardLayout, JPanel mainPanel, SessionManager sessionManager) {
        AdminDAO adminDAO = new AdminDAO();
        setLayout(new GridLayout(3, 3, 10, 10));
        JLabel titleLabel = new JLabel("Liste des Items", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        add(titleLabel, BorderLayout.NORTH);

        String[] columnNames = {"ID", "Name", "Prix", "Stock"};
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

        add(new JLabel("Nom de l'item à supprimer :", JLabel.CENTER));
        JTextField nameField = new JTextField();
        add(nameField);
        JPanel buttonPanel = new JPanel();
        JButton backButton = new JButton("Retour");
        JButton validationButton = new JButton("Suprimer");
        
        backButton.addActionListener(e -> cardLayout.show(mainPanel, "DisplayItemForEmployee"));
        validationButton.addActionListener(e -> {
            try {
                String Name = nameField.getText();
                if (adminDAO.deleteItem(Name, sessionManager.getStoreName())) {
                    JOptionPane.showMessageDialog(this, "Item " + Name + " supprimé avec succès !.", "iStore", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "Erreur lors de la suppression de l'item.", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                System.out.println("Erreur lors de la suppression de l'item : " + ex.getMessage());
            }
            
        });

        buttonPanel.add(backButton);
        buttonPanel.add(validationButton);

        add(buttonPanel, BorderLayout.CENTER);
    }
}
