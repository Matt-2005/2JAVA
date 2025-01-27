package com.iStore;

import javax.swing.*;
import java.awt.*;

public class GraphicInterface {
    private JFrame frame;
    private JPanel mainPanel;
    private CardLayout cardLayout;

    public void myInterface() {
        // Initialisation de la fenêtre principale
        frame = new JFrame("iStore App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        // Initialisation du CardLayout
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // Ajouter les différentes pages
        mainPanel.add(createWelcomePanel(), "Welcome");
        mainPanel.add(createLoginPanel(), "Login");
        mainPanel.add(createSignUpPanel(), "SignUp");

        // Ajouter le panneau principal à la fenêtre
        frame.add(mainPanel);
        frame.setVisible(true);
    }

    private JPanel createWelcomePanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JLabel welcomeLabel = new JLabel("Bienvenue dans iStore", JLabel.CENTER);
        panel.add(welcomeLabel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        JButton loginButton = new JButton("Login");
        JButton signUpButton = new JButton("SignUp");

        // Écouteurs pour naviguer entre les pages
        loginButton.addActionListener(e -> cardLayout.show(mainPanel, "Login"));
        signUpButton.addActionListener(e -> cardLayout.show(mainPanel, "SignUp"));

        buttonPanel.add(loginButton);
        buttonPanel.add(signUpButton);
        panel.add(buttonPanel, BorderLayout.CENTER);

        return panel;
    }

    private JPanel createLoginPanel() {
        JPanel panel = new JPanel();
        UserDAO userDAO = new UserDAO();
        panel.setLayout(new GridLayout(3, 2, 10, 10));
    
        // Champs d'email et de mot de passe
        panel.add(new JLabel("Email :"));
        JTextField emailField = new JTextField();
        panel.add(emailField);
    
        panel.add(new JLabel("Mot de passe :"));
        JPasswordField passwordField = new JPasswordField();
        panel.add(passwordField);
    
        // Bouton "Retour"
        JButton backButton = new JButton("Retour");
        backButton.addActionListener(e -> cardLayout.show(mainPanel, "Welcome"));
    
        // Bouton "Se connecter"
        JButton loginButton = new JButton("Se connecter");
        loginButton.addActionListener(e -> {
            // Récupérer les données saisies
            String email = emailField.getText();
            String password = new String(passwordField.getPassword());
    
            // Afficher les données pour tester (tu peux les envoyer à ton backend ici)
            System.out.println("Email : " + email);
            System.out.println("Mot de passe : " + password);

            try {
                if (userDAO.verifyAccount(email, password)) {
                    JOptionPane.showMessageDialog(panel, "Connexion réussie !");
                    cardLayout.show(mainPanel, "Dashboard"); // Naviguer vers une autre page si besoin
                } else {
                    JOptionPane.showMessageDialog(panel, "Identifiants incorrects.", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(panel, "Erreur lors de la connexion : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        });


    
        // Ajouter les boutons au panneau
        panel.add(backButton);
        panel.add(loginButton);
    
        return panel;
    }
    

    private JPanel createSignUpPanel() {
        UserDAO userDAO = new UserDAO();
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2, 10, 10));

        panel.add(new JLabel("Email :"));
        JTextField emailField = new JTextField();
        panel.add(emailField);

        panel.add(new JLabel("Pseudo :"));
        JTextField pseudoField = new JTextField();
        panel.add(pseudoField);

        panel.add(new JLabel("Mot de passe :"));
        JPasswordField passwordField = new JPasswordField();
        panel.add(passwordField);

        JButton backButton = new JButton("Retour");
        backButton.addActionListener(e -> cardLayout.show(mainPanel, "Welcome"));
        JButton signUpButton = new JButton("S'inscrire");

        signUpButton.addActionListener(e -> {
            String Email = emailField.getText();
            String Pseudo = pseudoField.getText();
            String Password = new String(passwordField.getPassword());

            try{
                PasswordHash.HashResults hashResults = PasswordHash.passwordHash(Password);
    
                User user = new User(0, Email, Pseudo, hashResults.getHashedPassword(), hashResults.getSalt(), null);
    
                if (userDAO.verifyEmail(Email)) {
                    System.out.println("This email is existing. Please try with an auther email or try to sign in.");
                }
                if (userDAO.createUser(user)) {
                    JOptionPane.showMessageDialog(panel, "Bienvenue chez iStore !");
                }
                
            } catch (Exception ex) {
                System.out.println("Erreur lors de la création du compte : " + ex.getMessage());
            }
        });

        panel.add(backButton);
        panel.add(signUpButton);

        return panel;
    }
}
