package com.iStore.ui.panels;

import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import com.iStore.config.DatabaseConfig;
import com.iStore.dao.AdminDAO;
import com.iStore.model.Store;
import com.iStore.utils.SessionManager;

/**
 * Panneau permettant à l'administrateur de choisir le magasin dont il veut gérer l'inventaire.
 * Cette interface affiche tous les magasins puis l'administrateur entre le nom de celui qu'il veut gérer.
 */
public class ManageWhatInventoryPanel extends JPanel{
    /**
     * Constructeur du panneau savoir quel inventaire afficher.
     * 
     * @param cardLayout Le gestionnaire de disposition pour la navigation entre les panneaux.
     * @param mainPanel  Le panneau principal contenant tous les écrans de l'application.
     * @param sessionManager Le gestionnaire de session pour récupérer des informations de session.
     */
    public ManageWhatInventoryPanel(CardLayout cardLayout, JPanel mainPanel, SessionManager sessionManager) {
        AdminDAO adminDAO = new AdminDAO();
        setLayout(new GridLayout(3, 3, 10, 10));
        JLabel titleLabel = new JLabel("Liste des magasins", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        add(titleLabel, BorderLayout.NORTH);

        String[] columnNames = {"ID", "Nom"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

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
        
        for (Store store : stores) {
            model.addRow(new Object[]{store.getId(), store.getName()});
        }

        JTable employeeTable = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(employeeTable);
        add(scrollPane, BorderLayout.CENTER);

        add(new JLabel("Nom du magasin à gérer :", JLabel.CENTER));
        final JTextField nameField = new JTextField();
        add(nameField);
        JPanel buttonPanel = new JPanel();
        JButton backButton = new JButton("Retour");
        JButton validationButton = new JButton("Ouvrir l'inventaire");
        
        backButton.addActionListener(e -> cardLayout.show(mainPanel, "AdminDashboard"));
        validationButton.addActionListener(e -> {
            try {
                String Name = nameField.getText();
                if (adminDAO.verifyName(Name)) {
                    sessionManager.setStoreName(Name);
                    mainPanel.add(new DisplayItemPanel(cardLayout, mainPanel, sessionManager), "DisplayItem");
                    cardLayout.show(mainPanel, "DisplayItem");
                } else {
                    JOptionPane.showMessageDialog(this, "Ce magasin n'existe pas", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                System.out.println("Erreur lors de l'affiche des items : " + ex.getMessage());
            }

        });

        buttonPanel.add(backButton);
        buttonPanel.add(validationButton);

        add(buttonPanel, BorderLayout.CENTER);
    }
}
