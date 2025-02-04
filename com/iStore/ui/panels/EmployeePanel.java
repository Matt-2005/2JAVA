package com.iStore.ui.panels;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import com.iStore.config.DatabaseConfig;
import com.iStore.dao.AdminDAO;
import com.iStore.dao.UserDAO;
import com.iStore.model.User;
import com.iStore.utils.SessionManager;

/**
 * Panneau d'affichage pour les employés.
 * Cette interface affiche un tableau avec tous les utilisateurs ainsi que des boutons pour l'employé connecter permettant de gérer son compte, de le supprimer ou de gérer son magasin.
 * Pour la gestion du compte, l'employé peut changer son email, son pseudo et son mot de passe.
 * Pour la gestion du magasin, l'employé pourra gérer le magasin auquel il est affilié.
 */
public class EmployeePanel extends JPanel{
    /**
     * Constructeur du panneau d'affichage pour les employés.
     * 
     * @param cardLayout Le gestionnaire de disposition pour la navigation entre les panneaux.
     * @param mainPanel  Le panneau principal contenant tous les écrans de l'application.
     * @param sessionManager Le gestionnaire de session pour récupérer des informations de session.
     */
    public EmployeePanel(CardLayout cardLayout, JPanel mainPanel, SessionManager sessionManager) {
        AdminDAO adminDAO = new AdminDAO();
        UserDAO userDAO = new UserDAO();
        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Panneau Employé", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        add(titleLabel, BorderLayout.NORTH);

        String[] columnNames = {"ID", "Nom", "Email", "Rôle", "Magasin"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        model.setRowCount(0);
        List<User> employees = new ArrayList<>();
        
        String requeteSQL = "SELECT ID, EMAIL, PSEUDO, ROLE, STORE_ID FROM USER";
        try (Connection conn = DatabaseConfig.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(requeteSQL);
            ResultSet rs = pstmt.executeQuery()) {

                while (rs.next()) {
                    int Id = rs.getInt("ID");
                    String Pseudo = rs.getString("PSEUDO");
                    String Email = rs.getString("EMAIL");
                    String Role = rs.getString("ROLE");
                    int storeID = rs.getInt("STORE_ID");

                    employees.add(new User(Id, Email, Pseudo, "", "", Role, storeID));

                    String storeNameFromDB = adminDAO.getStoreName(storeID);

                    model.addRow(new Object[]{Id, Pseudo, Email, Role, storeNameFromDB});

                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Erreur SQL : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        

        JTable employeeTable = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(employeeTable);
        add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton updateEmployeeBtn = new JButton("Modifier mon compte");
        updateEmployeeBtn.addActionListener(e -> {
            cardLayout.show(mainPanel, "UpdateUserForEmployee");
            sessionManager.setEmailToUpdate(sessionManager.getCurrentEmailAccoutConnected());
        });

        JButton removeEmployeeBtn = new JButton("Supprimer mon compte");
        removeEmployeeBtn.addActionListener(e -> {
            cardLayout.show(mainPanel, "Welcome");
            sessionManager.setEmailToUpdate(sessionManager.getCurrentEmailAccoutConnected());
            try {
                if (adminDAO.deleteUser(sessionManager.getEmailToUpdate())) {
                    JOptionPane.showMessageDialog(this, "Votre compte a bien été supprimé", "iStore", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "Erreur lors de la création du magasin.", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                System.out.println("Erreur lors de la suppression de votre compte : " + ex.getMessage());
            }

            
        });
        JButton backButton = new JButton("Retour");
        JButton manageStore = new JButton("Gérer mon magasin");
        manageStore.addActionListener(e -> {
            try {
                int storeID = userDAO.getUserStoreID(sessionManager.getCurrentEmailAccoutConnected());
                sessionManager.setStoreName(adminDAO.getStoreName(storeID));    
                mainPanel.add(new ManageInventory(cardLayout, mainPanel, sessionManager), "DisplayItemForEmployee");
                cardLayout.show(mainPanel, "DisplayItemForEmployee");
            } catch (Exception ex) {
                System.out.println("Erreur lors de la recupération du storeID : " + ex.getMessage());
            }

        });

        backButton.addActionListener(e -> cardLayout.show(mainPanel, "Welcome"));

        buttonPanel.add(updateEmployeeBtn);
        buttonPanel.add(removeEmployeeBtn);
        buttonPanel.add(manageStore);
        buttonPanel.add(backButton);

        add(buttonPanel, BorderLayout.SOUTH);
    }
}
