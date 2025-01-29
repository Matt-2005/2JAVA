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
        frame.setSize(600, 400);

        // Initialisation du CardLayout
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // Ajouter les différentes pages
        mainPanel.add(createWelcomePanel(), "Welcome");
        mainPanel.add(createLoginPanel(), "Login");
        mainPanel.add(createSignUpPanel(), "SignUp");
        mainPanel.add(createAdminPanel(), "AdminDashboard");
        mainPanel.add(createEmployeePanel(), "EmployeePanel");

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
        UserDAO userDAO = new UserDAO();
        JPanel panel = new JPanel();
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
                User user = userDAO.verifyAccount(email, password);
                if (user != null) {
                    if (user.getRole().equals("Admin")) {
                        cardLayout.show(mainPanel, "AdminDashboard");
                    } else {
                        System.out.print(user.getRole() + "l'erreur est la");
                        cardLayout.show(mainPanel, "EmployeePanel");
                    } 
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
                    if (user.getRole() == "Employé") {
                        cardLayout.show(mainPanel, "EmployeePanel");
                    } else {
                        cardLayout.show(mainPanel, "AdminDashboard");
                    }
                    
                }
                
            } catch (Exception ex) {
                System.out.println("Erreur lors de la création du compte : " + ex.getMessage());
            }
        });

        panel.add(backButton);
        panel.add(signUpButton);

        return panel;
    }

    private JPanel createAdminPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1, 10, 10));

        JLabel titleLabel = new JLabel("Panneau d'Administration", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        panel.add(titleLabel);

        // Section Gestion des Magasins
        JPanel storePanel = new JPanel();
        storePanel.setBorder(BorderFactory.createTitledBorder("Gestion des Magasins"));
        JButton createStoreBtn = new JButton("Créer un magasin");
        JButton deleteStoreBtn = new JButton("Supprimer un magasin");
        JButton listStoreBtn = new JButton("Voir les magasins");
        storePanel.add(createStoreBtn);
        storePanel.add(deleteStoreBtn);
        storePanel.add(listStoreBtn);

        // Section Gestion des Employés
        JPanel employeePanel = new JPanel();
        employeePanel.setBorder(BorderFactory.createTitledBorder("Gestion des Employés"));
        JButton addEmployeeBtn = new JButton("Ajouter un employé");
        JButton removeEmployeeBtn = new JButton("Supprimer un employé");
        JButton listEmployeeBtn = new JButton("Voir les employés");
        employeePanel.add(addEmployeeBtn);
        employeePanel.add(removeEmployeeBtn);
        employeePanel.add(listEmployeeBtn);

        // Section Gestion de l'Inventaire
        JPanel inventoryPanel = new JPanel();
        inventoryPanel.setBorder(BorderFactory.createTitledBorder("Gestion de l'Inventaire"));
        JButton manageInventoryBtn = new JButton("Gérer l'inventaire");
        inventoryPanel.add(manageInventoryBtn);

        JButton backButton = new JButton("Retour");
        backButton.addActionListener(e -> cardLayout.show(mainPanel, "Welcome"));
        inventoryPanel.add(backButton);



        // Ajouter les sections au panel principal
        panel.add(storePanel);
        panel.add(employeePanel);
        panel.add(inventoryPanel);

        return panel;
    }

    private JPanel createEmployeePanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
    
        // Titre
        JLabel titleLabel = new JLabel("Panneau Employé", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        panel.add(titleLabel, BorderLayout.NORTH);
    
        // Tableau pour lister les employés (vide pour le moment)
        String[] columnNames = {"ID", "Nom", "Email", "Rôle"};
        Object[][] data = {}; // À remplir avec des données réelles plus tard
        JTable employeeTable = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(employeeTable);
        panel.add(scrollPane, BorderLayout.CENTER);
    
        // Panel pour les boutons
        JPanel buttonPanel = new JPanel();
        JButton addEmployeeBtn = new JButton("Ajouter un employé");
        JButton removeEmployeeBtn = new JButton("Supprimer un employé");
        JButton backButton = new JButton("Retour");
    
        // Bouton Retour vers AdminDashboard
        backButton.addActionListener(e -> cardLayout.show(mainPanel, "Welcome"));
    
        // Ajouter les boutons au panel des boutons
        buttonPanel.add(addEmployeeBtn);
        buttonPanel.add(removeEmployeeBtn);
        buttonPanel.add(backButton);
    
        panel.add(buttonPanel, BorderLayout.SOUTH);
    
        return panel;
    }
    
}
