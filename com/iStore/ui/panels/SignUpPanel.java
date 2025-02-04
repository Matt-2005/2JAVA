package com.iStore.ui.panels;

import javax.swing.*;
import java.awt.*;

import com.iStore.dao.UserDAO;
import com.iStore.model.User;
import com.iStore.utils.PasswordHash;

public class SignUpPanel extends JPanel{
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
                System.out.println(userDAO.firstUser());
                if (!userDAO.firstUser()) {
                    System.out.println("admin");
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
                    System.out.println("employé");
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
