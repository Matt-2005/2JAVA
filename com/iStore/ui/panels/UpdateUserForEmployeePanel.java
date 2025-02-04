package com.iStore.ui.panels;

import java.awt.*;
import javax.swing.*;
import com.iStore.dao.AdminDAO;
import com.iStore.utils.PasswordHash;
import com.iStore.utils.SessionManager;

/**
 * Panneau permettant à l'employé de mettre son compte à jour.
 * Cette interface permet de rentrer l'email, le pseudo et le mot de passe pour mettre à jour son compte.
 */
public class UpdateUserForEmployeePanel extends JPanel{
    /**
     * Constructeur du panneau pour mette à jour un utilisateur.
     * 
     * @param cardLayout Le gestionnaire de disposition pour la navigation entre les panneaux.
     * @param mainPanel  Le panneau principal contenant tous les écrans de l'application.
     * @param sessionManager Le gestionnaire de session pour récupérer des informations de session.
     */
    public UpdateUserForEmployeePanel(CardLayout cardLayout, JPanel mainPanel, SessionManager sessionManager) {
        AdminDAO adminDAO = new AdminDAO();
        setLayout(new GridLayout(4, 2, 10, 10));
        
        add(new JLabel("Email :"));
        JTextField emailField = new JTextField();
        add(emailField);

        add(new JLabel("Pseudo :"));
        JTextField pseudoField = new JTextField();
        add(pseudoField);

        add(new JLabel("Mot de passe :"));
        JPasswordField passwordField = new JPasswordField();
        add(passwordField);
        JButton backButton = new JButton("Retour");
        backButton.addActionListener(e -> cardLayout.show(mainPanel, "EmployeePanel"));
        

        JButton signUpButton = new JButton("Mettre à jour");

        signUpButton.addActionListener(e -> {
            String newEmail = emailField.getText();
            String newPseudo = pseudoField.getText();
            String newRole = "Employé";
            String newPassword = new String(passwordField.getPassword());
            try{
                PasswordHash.HashResults hashResults = PasswordHash.passwordHash(newPassword);
                if (adminDAO.updateUser(sessionManager.getEmailToUpdate(), newEmail, newPseudo, hashResults.getHashedPassword(), hashResults.getSalt(), newRole)) {
                    JOptionPane.showMessageDialog(this, "L'utilisateur " + newPseudo + " à été mis a jour avec succès !.", "iStore", JOptionPane.INFORMATION_MESSAGE);
                }
                
            } catch (Exception ex) {
                System.out.println("Erreur lors de la mise a jour de l'utilisateur : " + ex.getMessage());
            }
        });

        add(backButton);
        add(signUpButton);
    }
}
