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

/**
 *Panneau permettant d'afficher les utilisateurs.
 */
public class DisplayUserPanel extends JPanel{
    /**
     * Constructeur du panneau d'affichage des utilisateur'.
     * @param cardLayout Le gestionnaire de disposition pour la navigation entre les panneaux.
     * @param mainPanel  Le panneau principal contenant tous les écrans de l'application.
     */
    public DisplayUserPanel(CardLayout cardLayout, JPanel mainPanel) {
        AdminDAO adminDAO = new AdminDAO();
        setLayout(new BorderLayout());
        JLabel titleLabel = new JLabel("Panneau Utilisateurs", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        add(titleLabel, BorderLayout.NORTH);

        String[] columnNames = {"ID", "Email", "Pseudo", "Rôle", "Magasin"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
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
        
        JTable employeeTable = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(employeeTable);
        add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton backButton = new JButton("Retour");

        backButton.addActionListener(e -> cardLayout.show(mainPanel, "AdminDashboard"));

        buttonPanel.add(backButton);

        add(buttonPanel, BorderLayout.SOUTH);
    }
}
