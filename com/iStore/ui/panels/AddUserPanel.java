package com.iStore.ui.panels;

import java.awt.*;

import javax.swing.*;

import com.iStore.dao.AdminDAO;
import com.iStore.dao.UserDAO;
import com.iStore.model.User;
import com.iStore.utils.PasswordHash;

/**
 * Panneau permettant d'ajouter un utilisateur.
 * Cette internface permet à l'administrateur de rentrer l'email, le pseudo, le mot de passe puis le magasin auquel sera affecté ce nouvel employé.
 * Le mot de passe n'est pas directement stocké dans la base de donnée, il est d'abord hashé puis stocké avec le sel(Salt) qui va avec pour pouvoir le comparé lors d'une connection.
 */
public class AddUserPanel extends JPanel{
    /**
     * Constructeur du panneau d'ajout d'utilisateur.
     * @param cardLayout Le gestionnaire de disposition pour la navigation entre les panneaux.
     * @param mainPanel  Le panneau principal contenant tous les écrans de l'application.
     */
    public AddUserPanel(CardLayout cardLayout, JPanel mainPanel) {
        UserDAO userDAO = new UserDAO();
        AdminDAO adminDAO = new AdminDAO();
        setLayout(new GridLayout(5, 2, 10, 10));

        add(new JLabel("Email :"));
        JTextField emailField = new JTextField();
        add(emailField);

        add(new JLabel("Pseudo :"));
        JTextField pseudoField = new JTextField();
        add(pseudoField);

        add(new JLabel("Mot de passe :"));
        JPasswordField passwordField = new JPasswordField();
        add(passwordField);

        add(new JLabel("Magasin :"));
        JTextField storeField = new JTextField();
        add(storeField);

        JButton backButton = new JButton("Retour");
        backButton.addActionListener(e -> cardLayout.show(mainPanel, "AdminDashboard"));

        JButton signUpButton = new JButton("Ajouter");

        signUpButton.addActionListener(e -> {
            String Email = emailField.getText();
            String Pseudo = pseudoField.getText();
            String Password = new String(passwordField.getPassword());
            String storeNameField = storeField.getText();
            
            try{
                int storeID = adminDAO.getStoreID(storeNameField); 
                PasswordHash.HashResults hashResults = PasswordHash.passwordHash(Password);
    
                User user = new User(0, Email, Pseudo, hashResults.getHashedPassword(), hashResults.getSalt(), "Employé", storeID);
    
                if (userDAO.verifyEmail(Email)) {
                    JOptionPane.showMessageDialog(this, "Email déja utilisé, veuillez vous connecter.", "Erreur", JOptionPane.ERROR_MESSAGE);

                }
                if (!adminDAO.verifyName(storeNameField)) {
                    System.out.println(adminDAO.verifyName(storeNameField));
                    JOptionPane.showMessageDialog(this, "Magasin inconnu", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
                if (userDAO.createUser(user)) {
                    JOptionPane.showMessageDialog(this, "Utilisateur " + Email + " créé avec succès !.", "iStore", JOptionPane.INFORMATION_MESSAGE);
                }
                
            } catch (Exception ex) {
                System.out.println("Erreur lors de la création du compte : " + ex.getMessage());
            }
        });

        add(backButton);
        add(signUpButton);
    }
}
