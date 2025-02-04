package com.iStore.ui.panels;

import javax.swing.*;
import java.awt.*;
import com.iStore.dao.UserDAO;
import com.iStore.model.User;
import com.iStore.utils.PasswordHash;

/**
 * Panneau permettant de s'inscrire.
 * Cette interface demande un email, un pseudo puis un mot de passe puis va inserer le tout dans la base de donnée.
 * Le mot de passe n'est pas directement stocké, c'est son hash et son sel(Salt) qui sont stocké pour ensuite pouvoir se connecter.
 */
public class SignUpPanel extends JPanel{
    /**
     * Constructeur du panneau d'inscription.
     * @param cardLayout Le gestionnaire de disposition pour la navigation entre les panneaux.
     * @param mainPanel  Le panneau principal contenant tous les écrans de l'application.
     */
    public SignUpPanel(CardLayout cardLayout, JPanel mainPanel) {
        UserDAO userDAO = new UserDAO();
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
        backButton.addActionListener(e -> cardLayout.show(mainPanel, "Welcome"));

        JButton signUpButton = new JButton("S'inscrire");

        signUpButton.addActionListener(e -> {
            String Email = emailField.getText();
            String Pseudo = pseudoField.getText();
            String Password = new String(passwordField.getPassword());
            try{
                PasswordHash.HashResults hashResults = PasswordHash.passwordHash(Password);
                if (!userDAO.firstUser()) {
                    User user = new User(0, Email, Pseudo, hashResults.getHashedPassword(), hashResults.getSalt(), "Admin", 1);
                    if (userDAO.verifyEmail(Email)) {
                        System.out.println("This email is existing. Please try with an auther email or try to sign in.");
                    }
                    if (userDAO.createUser(user)) {
                        if (user.getRole() == "Employé") {
                            cardLayout.show(mainPanel, "EmployeePanel");
                        } else {
                            cardLayout.show(mainPanel, "AdminDashboard");
                        }
                        
                    }
                } else {
                    User user = new User(0, Email, Pseudo, hashResults.getHashedPassword(), hashResults.getSalt(), "Employé", 1);
                    if (userDAO.verifyEmail(Email)) {
                        System.out.println("This email is existing. Please try with an auther email or try to sign in.");
                    }
                    if (userDAO.createUser(user)) {
                        if (user.getRole() == "Employé") {
                            cardLayout.show(mainPanel, "EmployeePanel");
                        } else {
                            cardLayout.show(mainPanel, "AdminDashboard");
                        }
                        
                    }
                }
            } catch (Exception ex) {
                System.out.println("Erreur lors de la création du compte : " + ex.getMessage());
            }
        });

        add(backButton);
        add(signUpButton);
    }
}
