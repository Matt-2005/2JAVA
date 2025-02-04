package com.iStore.ui.panels;

import javax.swing.*;
import java.awt.*;

import com.iStore.dao.UserDAO;
import com.iStore.model.User;
import com.iStore.utils.SessionManager;

public class LoginPanel extends JPanel {
    public LoginPanel(CardLayout cardLayout, JPanel mainPanel) {
        UserDAO userDAO = new UserDAO();
        SessionManager sessionManager = new SessionManager();
        setLayout(new GridLayout(3, 2, 10, 10));

        // Champs d'email et de mot de passe
        add(new JLabel("Email :"));
        final JTextField emailField = new JTextField();
        add(emailField);

        add(new JLabel("Mot de passe :"));
        JPasswordField passwordField = new JPasswordField();
        add(passwordField);

        JButton backButton = new JButton("Retour");
        backButton.addActionListener(e -> cardLayout.show(mainPanel, "Welcome"));

        JButton loginButton = new JButton("Se connecter");
        loginButton.addActionListener(e -> {
            String email = emailField.getText();
            sessionManager.setCurrentEmailAccoutConnected(email);
            String password = new String(passwordField.getPassword());

            try {
                User user = userDAO.verifyAccount(email, password);
                if (user != null) {
                    if (user.getRole().equals("Admin")) {
                        cardLayout.show(mainPanel, "AdminDashboard");
                    } else {
                        cardLayout.show(mainPanel, "EmployeePanel");
                    } 
                } else {
                    JOptionPane.showMessageDialog(this, "Identifiants incorrects.", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erreur lors de la connexion : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        });

        add(backButton);
        add(loginButton);
    }
}
