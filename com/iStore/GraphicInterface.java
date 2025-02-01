package com.iStore;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.sql.*;
import java.util.*;
import java.util.List;

public class GraphicInterface {
    private JFrame frame;
    private JPanel mainPanel;
    private CardLayout cardLayout;
    private String emailToUpdate;
    private String currentEmailAccountConnected;

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
        mainPanel.add(createStorePanel(), "StoreCreation");
        mainPanel.add(createDisplayStoresPanel(), "DisplayStore");
        mainPanel.add(createDeleteStorePanel(), "DeleteStore");
        mainPanel.add(createAddUserPanel(), "AddUser");
        mainPanel.add(createDeleteUserPanel(), "DeleteUser");
        mainPanel.add(createDisplayUserPanel(), "DisplayUser");
        mainPanel.add(createUpdateWhatUserPanel(), "WhatUserToUpdate");
        mainPanel.add(createUpdateUserPanelForAdmin(), "UpdateUserForAdmin");
        mainPanel.add(createUpdateUserPanelForEmployee(), "UpdateUserForEmployee");


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

        JButton backButton = new JButton("Retour");
        backButton.addActionListener(e -> cardLayout.show(mainPanel, "Welcome"));

        JButton loginButton = new JButton("Se connecter");
        loginButton.addActionListener(e -> {
            String email = emailField.getText();
            currentEmailAccountConnected = email;
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
                    JOptionPane.showMessageDialog(panel, "Identifiants incorrects.", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(panel, "Erreur lors de la connexion : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        });

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
        panel.setLayout(new GridLayout(4, 1, 10, 10));

        JLabel titleLabel = new JLabel("Panneau d'Administration", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        panel.add(titleLabel);

        // Section Gestion des Magasins
        JPanel storePanel = new JPanel();
        storePanel.setBorder(BorderFactory.createTitledBorder("Gestion des Magasins"));
        JButton createStoreBtn = new JButton("Créer un magasin");
        createStoreBtn.addActionListener(e -> cardLayout.show(mainPanel, "StoreCreation"));
        JButton deleteStoreBtn = new JButton("Supprimer un magasin");
        deleteStoreBtn.addActionListener(e -> cardLayout.show(mainPanel, "DeleteStore"));
        JButton listStoreBtn = new JButton("Voir les magasins");
        listStoreBtn.addActionListener(e -> cardLayout.show(mainPanel, "DisplayStore"));
        storePanel.add(createStoreBtn);
        storePanel.add(deleteStoreBtn);
        storePanel.add(listStoreBtn);

        // Section Gestion des Employés
        JPanel employeePanel = new JPanel();
        employeePanel.setBorder(BorderFactory.createTitledBorder("Gestion des Employés"));
        JButton addEmployeeBtn = new JButton("Ajouter un employé");
        addEmployeeBtn.addActionListener(e -> cardLayout.show(mainPanel, "AddUser"));
        JButton removeEmployeeBtn = new JButton("Supprimer un employé");
        removeEmployeeBtn.addActionListener(e -> cardLayout.show(mainPanel, "DeleteUser"));
        JButton listEmployeeBtn = new JButton("Voir les employés");
        listEmployeeBtn.addActionListener(e -> cardLayout.show(mainPanel, "DisplayUser"));
        JButton updateUserButton = new JButton("Mettre à jour un utilisateur");
        updateUserButton.addActionListener(e -> cardLayout.show(mainPanel, "WhatUserToUpdate"));
        employeePanel.add(addEmployeeBtn);
        employeePanel.add(removeEmployeeBtn);
        employeePanel.add(listEmployeeBtn);
        employeePanel.add(updateUserButton);

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
        AdminDAO adminDAO = new AdminDAO();
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Titre
        JLabel titleLabel = new JLabel("Panneau Employé", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        panel.add(titleLabel, BorderLayout.NORTH);

        // Définition des colonnes
        String[] columnNames = {"ID", "Nom", "Email", "Rôle"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0); // Modèle de tableau vide

        List<User> employees = new ArrayList<>();
        
        String requeteSQL = "SELECT ID, EMAIL, PSEUDO, ROLE FROM USER";
        try (Connection conn = DatabaseConfig.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(requeteSQL);
            ResultSet rs = pstmt.executeQuery()) {

                while (rs.next()) {
                    int Id = rs.getInt("ID");
                    String Pseudo = rs.getString("PSEUDO");
                    String Email = rs.getString("EMAIL");
                    String Role = rs.getString("ROLE");

                    employees.add(new User(Id, Email, Pseudo, "", "", Role));
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(panel, "Erreur SQL : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        
        // **Remplir le tableau avec les données de la liste**
        for (User user : employees) {
            model.addRow(new Object[]{user.getId(), user.getPseudo(), user.getEmail(), user.getRole()});
        }

        // Création du JTable avec le modèle dynamique
        JTable employeeTable = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(employeeTable);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Panel pour les boutons
        JPanel buttonPanel = new JPanel();
        JButton updateEmployeeBtn = new JButton("Modifier mon compte");
        updateEmployeeBtn.addActionListener(e -> {
            cardLayout.show(mainPanel, "UpdateUserForEmployee");
            emailToUpdate = currentEmailAccountConnected;
        });

        JButton removeEmployeeBtn = new JButton("Supprimer mon compte");
        removeEmployeeBtn.addActionListener(e -> {
            cardLayout.show(mainPanel, "Welcome");
            emailToUpdate = currentEmailAccountConnected;
            try {
                if (adminDAO.deleteUser(emailToUpdate)) {
                    JOptionPane.showMessageDialog(panel, "Votre compte a bien été supprimé", "iStore", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(panel, "Erreur lors de la création du magasin.", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                System.out.println("Erreur lors de la suppression de votre compte : " + ex.getMessage());
            }

            
        });
        JButton backButton = new JButton("Retour");

        // Bouton Retour vers AdminDashboard
        backButton.addActionListener(e -> cardLayout.show(mainPanel, "Welcome"));

        // Ajouter les boutons au panel des boutons
        buttonPanel.add(updateEmployeeBtn);
        buttonPanel.add(removeEmployeeBtn);
        buttonPanel.add(backButton);

        panel.add(buttonPanel, BorderLayout.SOUTH);
        

        return panel;
    }

    private JPanel createStorePanel() {
        AdminDAO adminDAO = new AdminDAO();
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 2, 10, 10));

        panel.add(new JLabel("Nom du magasin :"));

        JTextField nameField = new JTextField();
        panel.add(nameField);

        JButton backButton = new JButton("Retour");
        backButton.addActionListener(e -> cardLayout.show(mainPanel, "AdminDashboard"));
        panel.add(backButton);
        
        JButton createButton = new JButton("Créer le Magasin");
        createButton.addActionListener(e -> {
            String Name = nameField.getText();
            try {
                if (!adminDAO.verifyName(Name)) {
                    if (adminDAO.createStore(0, Name)) {
                        JOptionPane.showMessageDialog(panel, "Magasin " + Name + " créé avec succès !.", "iStore", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(panel, "Erreur lors de la création du magasin.", "Erreur", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(panel, "Le nom est déja utilisé.", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
                
            } catch (Exception ex) {
                System.out.println("Erreur lors de la création du magasin : " + ex.getMessage());
            }
        });
        
        panel.add(createButton);

        return panel;
    }

    private JPanel createDisplayStoresPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        // Titre
        JLabel titleLabel = new JLabel("Panneau Magasins", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        panel.add(titleLabel, BorderLayout.NORTH);

        // Définition des colonnes
        String[] columnNames = {"ID", "Nom"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0); // Modèle de tableau vide

        List<Store> stores = new ArrayList<>();
        
        String requeteSQL = "SELECT ID, NAME FROM STORE";
        try (Connection conn = DatabaseConfig.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(requeteSQL);
            ResultSet rs = pstmt.executeQuery()) {

                while (rs.next()) {
                    int Id = rs.getInt("ID");
                    String Name = rs.getString("NAME");

                    stores.add(new Store(Id, Name));
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(panel, "Erreur SQL : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        
        // **Remplir le tableau avec les données de la liste**
        for (Store store : stores) {
            model.addRow(new Object[]{store.getId(), store.getName()});
        }

        // Création du JTable avec le modèle dynamique
        JTable employeeTable = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(employeeTable);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Panel pour les boutons
        JPanel buttonPanel = new JPanel();
        JButton backButton = new JButton("Retour");

        // Bouton Retour vers AdminDashboard
        backButton.addActionListener(e -> cardLayout.show(mainPanel, "AdminDashboard"));

        // Ajouter les boutons au panel des boutons
        buttonPanel.add(backButton);

        panel.add(buttonPanel, BorderLayout.SOUTH);
        

        return panel;
    } 

    private JPanel createDeleteStorePanel() {
        AdminDAO adminDAO = new AdminDAO();
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 3, 10, 10));
        // Titre
        JLabel titleLabel = new JLabel("Liste des magasins", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        panel.add(titleLabel, BorderLayout.NORTH);

        // Définition des colonnes
        String[] columnNames = {"ID", "Nom"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0); // Modèle de tableau vide

        List<Store> stores = new ArrayList<>();
        
        String requeteSQL = "SELECT ID, NAME FROM STORE";
        try (Connection conn = DatabaseConfig.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(requeteSQL);
            ResultSet rs = pstmt.executeQuery()) {

                while (rs.next()) {
                    int Id = rs.getInt("ID");
                    String Name = rs.getString("NAME");

                    stores.add(new Store(Id, Name));
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(panel, "Erreur SQL : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        
        // **Remplir le tableau avec les données de la liste**
        for (Store store : stores) {
            model.addRow(new Object[]{store.getId(), store.getName()});
        }

        // Création du JTable avec le modèle dynamique
        JTable employeeTable = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(employeeTable);
        panel.add(scrollPane, BorderLayout.CENTER);

        panel.add(new JLabel("Nom du magasin a suprimer :", JLabel.CENTER));
        JTextField nameField = new JTextField();
        panel.add(nameField);
        // Panel pour les boutons
        JPanel buttonPanel = new JPanel();
        JButton backButton = new JButton("Retour");
        JButton validationButton = new JButton("Suprimer");
        
        // Bouton Retour vers AdminDashboard
        backButton.addActionListener(e -> cardLayout.show(mainPanel, "AdminDashboard"));
        validationButton.addActionListener(e -> {
            try {
                String Name = nameField.getText();
                if (adminDAO.deleteStore(Name)) {
                    JOptionPane.showMessageDialog(panel, "Magasin " + Name + " supprimé avec succès !.", "iStore", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(panel, "Erreur lors de la suppression du magasin.", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                System.out.println("Erreur lors de la suppression du magasin : " + ex.getMessage());
            }
            
        });

        // Ajouter les boutons au panel des boutons
        buttonPanel.add(backButton);
        buttonPanel.add(validationButton);

        panel.add(buttonPanel, BorderLayout.CENTER);
        

        return panel;
    } 

    private JPanel createAddUserPanel() {
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
        backButton.addActionListener(e -> cardLayout.show(mainPanel, "AdminDashboard"));

        JButton signUpButton = new JButton("S'inscrire");

        signUpButton.addActionListener(e -> {
            String Email = emailField.getText();
            String Pseudo = pseudoField.getText();
            String Password = new String(passwordField.getPassword());
            try{
                PasswordHash.HashResults hashResults = PasswordHash.passwordHash(Password);
    
                User user = new User(0, Email, Pseudo, hashResults.getHashedPassword(), hashResults.getSalt(), null);
    
                if (userDAO.verifyEmail(Email)) {
                    JOptionPane.showMessageDialog(panel, "Email déja utilisé, veuillez vous connecter.", "Erreur", JOptionPane.ERROR_MESSAGE);

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

    private JPanel createDeleteUserPanel() {
        AdminDAO adminDAO = new AdminDAO();
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 3, 10, 10));
        // Titre
        JLabel titleLabel = new JLabel("Liste des utilisateurs", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        panel.add(titleLabel, BorderLayout.NORTH);

        // Définition des colonnes
        String[] columnNames = {"ID", "Email", "Pseudo", "Rôle"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0); // Modèle de tableau vide

        List<User> employees = new ArrayList<>();
        
        String requeteSQL = "SELECT ID, EMAIL, PSEUDO, ROLE FROM USER";
        try (Connection conn = DatabaseConfig.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(requeteSQL);
            ResultSet rs = pstmt.executeQuery()) {

                while (rs.next()) {
                    int Id = rs.getInt("ID");
                    String Email = rs.getString("EMAIL");
                    String Pseudo = rs.getString("PSEUDO");
                    String Role = rs.getString("ROLE");

                    employees.add(new User(Id, Email, Pseudo, "", "", Role));
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(panel, "Erreur SQL : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        
        // **Remplir le tableau avec les données de la liste**
        for (User user : employees) {
            model.addRow(new Object[]{user.getId(), user.getEmail(), user.getPseudo(), user.getRole()});
        }

        // Création du JTable avec le modèle dynamique
        JTable employeeTable = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(employeeTable);
        panel.add(scrollPane, BorderLayout.CENTER);

        panel.add(new JLabel("Email de l'utilisateur à suprimer :", JLabel.CENTER));
        JTextField emailField = new JTextField();
        panel.add(emailField);
        // Panel pour les boutons
        JPanel buttonPanel = new JPanel();
        JButton backButton = new JButton("Retour");
        JButton validationButton = new JButton("Suprimer");
        
        // Bouton Retour vers AdminDashboard
        backButton.addActionListener(e -> cardLayout.show(mainPanel, "AdminDashboard"));
        validationButton.addActionListener(e -> {
            try {
                String Email = emailField.getText();
                if (adminDAO.deleteUser(Email)) {
                    JOptionPane.showMessageDialog(panel, "Utilisateur " + Email + " supprimé avec succès !.", "iStore", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(panel, "Erreur lors de la suppression de l'utilisateur.", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                System.out.println("Erreur lors de la suppression de l'utilisateur : " + ex.getMessage());
            }
            
        });

        // Ajouter les boutons au panel des boutons
        buttonPanel.add(backButton);
        buttonPanel.add(validationButton);

        panel.add(buttonPanel, BorderLayout.CENTER);
        

        return panel;
    }

    private JPanel createDisplayUserPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        // Titre
        JLabel titleLabel = new JLabel("Panneau Utilisateurs", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        panel.add(titleLabel, BorderLayout.NORTH);

        // Définition des colonnes
        String[] columnNames = {"ID", "Email", "Pseudo", "Rôle"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0); // Modèle de tableau vide

        List<User> employees = new ArrayList<>();
        
        String requeteSQL = "SELECT ID, EMAIL, PSEUDO, ROLE FROM USER";
        try (Connection conn = DatabaseConfig.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(requeteSQL);
            ResultSet rs = pstmt.executeQuery()) {

                while (rs.next()) {
                    int Id = rs.getInt("ID");
                    String Email = rs.getString("EMAIL");
                    String Pseudo = rs.getString("PSEUDO");
                    String Role = rs.getString("ROLE");

                    employees.add(new User(Id, Email, Pseudo, "", "", Role));
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(panel, "Erreur SQL : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        
        // **Remplir le tableau avec les données de la liste**
        for (User user : employees) {
            model.addRow(new Object[]{user.getId(), user.getEmail(), user.getPseudo(), user.getRole()});
        }

        // Création du JTable avec le modèle dynamique
        JTable employeeTable = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(employeeTable);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Panel pour les boutons
        JPanel buttonPanel = new JPanel();
        JButton backButton = new JButton("Retour");

        // Bouton Retour vers AdminDashboard
        backButton.addActionListener(e -> cardLayout.show(mainPanel, "AdminDashboard"));

        // Ajouter les boutons au panel des boutons
        buttonPanel.add(backButton);

        panel.add(buttonPanel, BorderLayout.SOUTH);
        

        return panel;
    }

    private JPanel createUpdateWhatUserPanel() {
        UserDAO userDAO = new UserDAO();
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 3, 10, 10));
        // Titre
        JLabel titleLabel = new JLabel("Liste des utilisateurs", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        panel.add(titleLabel, BorderLayout.NORTH);

        // Définition des colonnes
        String[] columnNames = {"ID", "Email", "Pseudo", "Rôle"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0); // Modèle de tableau vide

        List<User> employees = new ArrayList<>();
        
        String requeteSQL = "SELECT ID, EMAIL, PSEUDO, ROLE FROM USER";
        try (Connection conn = DatabaseConfig.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(requeteSQL);
            ResultSet rs = pstmt.executeQuery()) {

                while (rs.next()) {
                    int Id = rs.getInt("ID");
                    String Email = rs.getString("EMAIL");
                    String Pseudo = rs.getString("PSEUDO");
                    String Role = rs.getString("ROLE");

                    employees.add(new User(Id, Email, Pseudo, "", "", Role));
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(panel, "Erreur SQL : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        
        // **Remplir le tableau avec les données de la liste**
        for (User user : employees) {
            model.addRow(new Object[]{user.getId(), user.getEmail(), user.getPseudo(), user.getRole()});
        }

        // Création du JTable avec le modèle dynamique
        JTable employeeTable = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(employeeTable);
        panel.add(scrollPane, BorderLayout.CENTER);

        panel.add(new JLabel("Email de l'utilisateur à suprimer :", JLabel.CENTER));
        JTextField emailField = new JTextField();
        panel.add(emailField);
        // Panel pour les boutons
        JPanel buttonPanel = new JPanel();
        JButton backButton = new JButton("Retour");
        JButton updateButton = new JButton("Mettre à jour");
        
        // Bouton Retour vers AdminDashboard
        backButton.addActionListener(e -> cardLayout.show(mainPanel, "AdminDashboard"));
        updateButton.addActionListener(e -> {
            try {
                String Email = emailField.getText();
                if (!userDAO.verifyEmail(Email)) {
                    JOptionPane.showMessageDialog(panel, "Cet utilisateur n'existe pas. Veuillez saisir un autre email", "Erreur", JOptionPane.ERROR_MESSAGE);
                } else {
                    emailToUpdate = Email;
                    cardLayout.show(mainPanel, "UpdateUserForAdmin");
                }
            } catch (Exception ex) {
                System.out.println("Erreur lors de la suppression de l'utilisateur : " + ex.getMessage());
            }
            
        });

        // Ajouter les boutons au panel des boutons
        buttonPanel.add(backButton);
        buttonPanel.add(updateButton);

        panel.add(buttonPanel, BorderLayout.CENTER);
        

        return panel;
    }

    private JPanel createUpdateUserPanelForAdmin() {
        AdminDAO adminDAO = new AdminDAO();
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2, 10, 10));
        
        panel.add(new JLabel("Email :"));
        JTextField emailField = new JTextField();
        panel.add(emailField);

        panel.add(new JLabel("Pseudo :"));
        JTextField pseudoField = new JTextField();
        panel.add(pseudoField);

        panel.add(new JLabel("Role :"));
        JTextField roleField = new JTextField();
        panel.add(roleField);

        panel.add(new JLabel("Mot de passe :"));
        JPasswordField passwordField = new JPasswordField();
        panel.add(passwordField);
        JButton backButton = new JButton("Retour");
        backButton.addActionListener(e -> cardLayout.show(mainPanel, "WhatUserToUpdate"));
        

        JButton signUpButton = new JButton("Mettre à jour");

        signUpButton.addActionListener(e -> {
            String newEmail = emailField.getText();
            String newPseudo = pseudoField.getText();
            String newRole = roleField.getText();
            String newPassword = new String(passwordField.getPassword());
            try{
                PasswordHash.HashResults hashResults = PasswordHash.passwordHash(newPassword);
                if (adminDAO.updateUser(emailToUpdate, newEmail, newPseudo, hashResults.getHashedPassword(), hashResults.getSalt(), newRole)) {
                    JOptionPane.showMessageDialog(panel, "L'utilisateur " + newPseudo + " à été mis a jour avec succès !.", "iStore", JOptionPane.INFORMATION_MESSAGE);
                }
                
            } catch (Exception ex) {
                System.out.println("Erreur lors de la mise a jour de l'utilisateur : " + ex.getMessage());
            }
        });

        panel.add(backButton);
        panel.add(signUpButton);

        return panel;
    }

    private JPanel createUpdateUserPanelForEmployee() {
        AdminDAO adminDAO = new AdminDAO();
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2, 10, 10));
        
        panel.add(new JLabel("Email :"));
        JTextField emailField = new JTextField();
        panel.add(emailField);

        panel.add(new JLabel("Pseudo :"));
        JTextField pseudoField = new JTextField();
        panel.add(pseudoField);

        panel.add(new JLabel("Role :"));
        JTextField roleField = new JTextField();
        panel.add(roleField);

        panel.add(new JLabel("Mot de passe :"));
        JPasswordField passwordField = new JPasswordField();
        panel.add(passwordField);
        JButton backButton = new JButton("Retour");
        backButton.addActionListener(e -> cardLayout.show(mainPanel, "EmployeePanel"));
        

        JButton signUpButton = new JButton("Mettre à jour");

        signUpButton.addActionListener(e -> {
            String newEmail = emailField.getText();
            String newPseudo = pseudoField.getText();
            String newRole = roleField.getText();
            String newPassword = new String(passwordField.getPassword());
            try{
                PasswordHash.HashResults hashResults = PasswordHash.passwordHash(newPassword);
                if (adminDAO.updateUser(emailToUpdate, newEmail, newPseudo, hashResults.getHashedPassword(), hashResults.getSalt(), newRole)) {
                    JOptionPane.showMessageDialog(panel, "L'utilisateur " + newPseudo + " à été mis a jour avec succès !.", "iStore", JOptionPane.INFORMATION_MESSAGE);
                }
                
            } catch (Exception ex) {
                System.out.println("Erreur lors de la mise a jour de l'utilisateur : " + ex.getMessage());
            }
        });

        panel.add(backButton);
        panel.add(signUpButton);

        return panel;
    }

    

    
}
