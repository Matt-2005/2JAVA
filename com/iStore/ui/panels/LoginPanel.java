package com.iStore.ui.panels;

import javax.swing.*;
import java.awt.*;
import com.iStore.dao.UserDAO;
import com.iStore.model.User;
import com.iStore.utils.SessionManager;

/**
 * Panneau permettant de se connecter.
 * Cette interface demande un email et un mot de passe.
 * Elle va ensuite comparer l'email et le mot de passe grace a userDAO.verifyAccount() en recupérant le hash et le sel(Salt) dans la base de donnée.
 */
public class LoginPanel extends JPanel {
    /**
     * Constructeur du panneau pour se connecter.
     * 
     * @param cardLayout Le gestionnaire de disposition pour la navigation entre les panneaux.
     * @param mainPanel  Le panneau principal contenant tous les écrans de l'application.
     * @param sessionManager Le gestionnaire de session pour récupérer des informations de session.
     */
    public LoginPanel(CardLayout cardLayout, JPanel mainPanel, SessionManager sessionManager) {
        UserDAO userDAO = new UserDAO();
        setLayout(new GridLayout(3, 2, 10, 10));

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
