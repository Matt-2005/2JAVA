package com.iStore.ui;

import javax.swing.*;

import com.iStore.ui.panels.AddItemPanel;
import com.iStore.ui.panels.AddStorePanel;
import com.iStore.ui.panels.AddUserPanel;
import com.iStore.ui.panels.AdminPanel;
import com.iStore.ui.panels.DeleteItemPanel;
import com.iStore.ui.panels.DeleteStorePanel;
import com.iStore.ui.panels.DeleteUserPanel;
import com.iStore.ui.panels.DisplayItemPanel;
import com.iStore.ui.panels.DisplayStorePanel;
import com.iStore.ui.panels.DisplayUserPanel;
import com.iStore.ui.panels.EmployeePanel;
import com.iStore.ui.panels.LoginPanel;
import com.iStore.ui.panels.ManageInventory;
import com.iStore.ui.panels.ManageWhatInventoryPanel;
import com.iStore.ui.panels.SignUpPanel;
import com.iStore.ui.panels.UpdateItemPanel;
import com.iStore.ui.panels.UpdateUserForAdminPanel;
import com.iStore.ui.panels.UpdateUserForEmployeePanel;
import com.iStore.ui.panels.UpdateWhatUserPanel;
import com.iStore.ui.panels.WelcomePanel;
import com.iStore.utils.SessionManager;

import java.awt.*;


public class GraphicInterface {
    private JFrame frame;

    public void myInterface() {
        frame = new JFrame("iStore App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        CardLayout cardLayout = new CardLayout();
        JPanel mainPanel = new JPanel(cardLayout);
        SessionManager sessionManager = new SessionManager();


        // Ajouter les différentes pages
        mainPanel.add(new WelcomePanel(cardLayout, mainPanel), "Welcome");
        mainPanel.add(new AddItemPanel(cardLayout, mainPanel, sessionManager), "AddItem");
        mainPanel.add(new AddStorePanel(cardLayout, mainPanel), "StoreCreation"); //AddStore
        mainPanel.add(new AddUserPanel(cardLayout, mainPanel), "AddUser");
        mainPanel.add(new AdminPanel(cardLayout, mainPanel), "AdminDashboard");
        mainPanel.add(new DeleteItemPanel(cardLayout, mainPanel, sessionManager), "DeleteItem");
        mainPanel.add(new DeleteStorePanel(cardLayout, mainPanel), "DeleteStore");
        mainPanel.add(new DeleteUserPanel(cardLayout, mainPanel), "DeleteUser");
        mainPanel.add(new DisplayItemPanel(cardLayout, mainPanel, sessionManager), "DisplayItem");
        mainPanel.add(new DisplayStorePanel(cardLayout, mainPanel), "DisplayStore");
        mainPanel.add(new DisplayUserPanel(cardLayout, mainPanel), "DisplayUser");
        mainPanel.add(new EmployeePanel(cardLayout, mainPanel, sessionManager), "EmployeePanel");
        mainPanel.add(new LoginPanel(cardLayout, mainPanel, sessionManager), "Login");
        mainPanel.add(new ManageInventory(cardLayout, mainPanel, sessionManager), "DisplayItemForEmployee");
        mainPanel.add(new ManageWhatInventoryPanel(cardLayout, mainPanel, sessionManager), "WhatInventoryToManage");
        mainPanel.add(new SignUpPanel(cardLayout, mainPanel), "SignUp");
        mainPanel.add(new UpdateItemPanel(cardLayout, mainPanel), "UpdateItem");
        mainPanel.add(new UpdateUserForAdminPanel(cardLayout, mainPanel, sessionManager), "UpdateUserForAdmin");
        mainPanel.add(new UpdateUserForEmployeePanel(cardLayout, mainPanel, sessionManager), "UpdateUserForEmployee");
        mainPanel.add(new UpdateWhatUserPanel(cardLayout, mainPanel, sessionManager), "WhatUserToUpdate");
        
        

        // mainPanel.add(createWelcomePanel(), "Welcome");
        // mainPanel.add(createLoginPanel(), "Login");
        // mainPanel.add(createSignUpPanel(), "SignUp");
        // mainPanel.add(createAdminPanel(), "AdminDashboard");
        // mainPanel.add(createEmployeePanel(), "EmployeePanel");
        // mainPanel.add(createStorePanel(), "StoreCreation");
        // mainPanel.add(createDisplayStoresPanel(), "DisplayStore");
        // mainPanel.add(createDeleteStorePanel(), "DeleteStore");
        // mainPanel.add(createAddUserPanel(), "AddUser");
        // mainPanel.add(createDeleteUserPanel(), "DeleteUser");
        // mainPanel.add(createDisplayUserPanel(), "DisplayUser");
        // mainPanel.add(createUpdateWhatUserPanel(), "WhatUserToUpdate");
        // mainPanel.add(createUpdateUserPanelForAdmin(), "UpdateUserForAdmin");
        // mainPanel.add(createUpdateUserPanelForEmployee(), "UpdateUserForEmployee");
        // mainPanel.add(createManageWhatInventory(), "WhatInventoryToManage");
        // mainPanel.add(createAddItemPanel(), "AddItem");
        // mainPanel.add(createEmployeePanel(), "DisplayItem");
        

        frame.add(mainPanel);

        // Afficher le WelcomePanel par défaut
        cardLayout.show(mainPanel, "Welcome");

        // Rendre la fenêtre visible
        frame.setLocationRelativeTo(null); // Centrer la fenêtre
        frame.setVisible(true);
    }

    // private JPanel createWelcomePanel() {
    //     JPanel panel = new JPanel();
    //     panel.setLayout(new BorderLayout());

    //     JLabel welcomeLabel = new JLabel("Bienvenue dans iStore", JLabel.CENTER);
    //     panel.add(welcomeLabel, BorderLayout.NORTH);

    //     JPanel buttonPanel = new JPanel();
    //     JButton loginButton = new JButton("Login");
    //     JButton signUpButton = new JButton("SignUp");

    //     loginButton.addActionListener(e -> cardLayout.show(mainPanel, "Login"));
    //     signUpButton.addActionListener(e -> cardLayout.show(mainPanel, "SignUp"));

    //     buttonPanel.add(loginButton);
    //     buttonPanel.add(signUpButton);
    //     panel.add(buttonPanel, BorderLayout.CENTER);

    //     return panel;
    // }

    // private JPanel createLoginPanel() {
    //     UserDAO userDAO = new UserDAO();
    //     JPanel panel = new JPanel();
    //     panel.setLayout(new GridLayout(3, 2, 10, 10));

    //     // Champs d'email et de mot de passe
    //     panel.add(new JLabel("Email :"));
    //     final JTextField emailField = new JTextField();
    //     panel.add(emailField);

    //     panel.add(new JLabel("Mot de passe :"));
    //     JPasswordField passwordField = new JPasswordField();
    //     panel.add(passwordField);

    //     JButton backButton = new JButton("Retour");
    //     backButton.addActionListener(e -> cardLayout.show(mainPanel, "Welcome"));

    //     JButton loginButton = new JButton("Se connecter");
    //     loginButton.addActionListener(e -> {
    //         String email = emailField.getText();
    //         currentEmailAccountConnected = email;
    //         String password = new String(passwordField.getPassword());

    //         try {
    //             User user = userDAO.verifyAccount(email, password);
    //             if (user != null) {
    //                 if (user.getRole().equals("Admin")) {
    //                     cardLayout.show(mainPanel, "AdminDashboard");
    //                 } else {
    //                     cardLayout.show(mainPanel, "EmployeePanel");
    //                 } 
    //             } else {
    //                 JOptionPane.showMessageDialog(panel, "Identifiants incorrects.", "Erreur", JOptionPane.ERROR_MESSAGE);
    //             }
    //         } catch (Exception ex) {
    //             JOptionPane.showMessageDialog(panel, "Erreur lors de la connexion : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
    //         }
    //     });

    //     panel.add(backButton);
    //     panel.add(loginButton);

    //     return panel;
    // }

    // private JPanel createSignUpPanel() {
    //     UserDAO userDAO = new UserDAO();
    //     JPanel panel = new JPanel();
    //     panel.setLayout(new GridLayout(4, 2, 10, 10));

    //     panel.add(new JLabel("Email :"));
    //     JTextField emailField = new JTextField();
    //     panel.add(emailField);

    //     panel.add(new JLabel("Pseudo :"));
    //     JTextField pseudoField = new JTextField();
    //     panel.add(pseudoField);

    //     panel.add(new JLabel("Mot de passe :"));
    //     JPasswordField passwordField = new JPasswordField();
    //     panel.add(passwordField);

    //     JButton backButton = new JButton("Retour");
    //     backButton.addActionListener(e -> cardLayout.show(mainPanel, "Welcome"));

    //     JButton signUpButton = new JButton("S'inscrire");

    //     signUpButton.addActionListener(e -> {
    //         String Email = emailField.getText();
    //         String Pseudo = pseudoField.getText();
    //         String Password = new String(passwordField.getPassword());
    //         try{
    //             PasswordHash.HashResults hashResults = PasswordHash.passwordHash(Password);
    //             System.out.println(userDAO.firstUser());
    //             if (!userDAO.firstUser()) {
    //                 System.out.println("admin");
    //                 User user = new User(0, Email, Pseudo, hashResults.getHashedPassword(), hashResults.getSalt(), "Admin", 1);
    //                 if (userDAO.verifyEmail(Email)) {
    //                     System.out.println("This email is existing. Please try with an auther email or try to sign in.");
    //                 }
    //                 if (userDAO.createUser(user)) {
    //                     if (user.getRole() == "Employé") {
    //                         cardLayout.show(mainPanel, "EmployeePanel");
    //                     } else {
    //                         cardLayout.show(mainPanel, "AdminDashboard");
    //                     }
                        
    //                 }
    //             } else {
    //                 System.out.println("employé");
    //                 User user = new User(0, Email, Pseudo, hashResults.getHashedPassword(), hashResults.getSalt(), "Employé", 1);
    //                 if (userDAO.verifyEmail(Email)) {
    //                     System.out.println("This email is existing. Please try with an auther email or try to sign in.");
    //                 }
    //                 if (userDAO.createUser(user)) {
    //                     if (user.getRole() == "Employé") {
    //                         cardLayout.show(mainPanel, "EmployeePanel");
    //                     } else {
    //                         cardLayout.show(mainPanel, "AdminDashboard");
    //                     }
                        
    //                 }
    //             }
    
    

                
    //         } catch (Exception ex) {
    //             System.out.println("Erreur lors de la création du compte : " + ex.getMessage());
    //         }
    //     });

    //     panel.add(backButton);
    //     panel.add(signUpButton);

    //     return panel;
    // }

    // private JPanel createAdminPanel() {
    //     JPanel panel = new JPanel();
    //     panel.setLayout(new GridLayout(4, 1, 10, 10));

    //     JLabel titleLabel = new JLabel("Panneau d'Administration", JLabel.CENTER);
    //     titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
    //     panel.add(titleLabel);

    //     // Section Gestion des Magasins
    //     JPanel storePanel = new JPanel();
    //     storePanel.setBorder(BorderFactory.createTitledBorder("Gestion des Magasins"));
    //     JButton createStoreBtn = new JButton("Créer un magasin");
    //     createStoreBtn.addActionListener(e -> cardLayout.show(mainPanel, "StoreCreation"));
    //     JButton deleteStoreBtn = new JButton("Supprimer un magasin");
    //     deleteStoreBtn.addActionListener(e -> cardLayout.show(mainPanel, "DeleteStore"));
    //     JButton listStoreBtn = new JButton("Voir les magasins");
    //     listStoreBtn.addActionListener(e -> cardLayout.show(mainPanel, "DisplayStore"));
    //     JButton manageInventoryBtn = new JButton("Gérer les inventaires");
    //     manageInventoryBtn.addActionListener(e -> cardLayout.show(mainPanel, "WhatInventoryToManage"));
    //     storePanel.add(createStoreBtn);
    //     storePanel.add(deleteStoreBtn);
    //     storePanel.add(listStoreBtn);
    //     storePanel.add(manageInventoryBtn);

    //     // Section Gestion des Employés
    //     JPanel employeePanel = new JPanel();
    //     employeePanel.setBorder(BorderFactory.createTitledBorder("Gestion des Employés"));
    //     JButton addEmployeeBtn = new JButton("Ajouter un employé");
    //     addEmployeeBtn.addActionListener(e -> cardLayout.show(mainPanel, "AddUser"));
    //     JButton removeEmployeeBtn = new JButton("Supprimer un employé");
    //     removeEmployeeBtn.addActionListener(e -> cardLayout.show(mainPanel, "DeleteUser"));
    //     JButton listEmployeeBtn = new JButton("Voir les employés");
    //     listEmployeeBtn.addActionListener(e -> cardLayout.show(mainPanel, "DisplayUser"));
    //     JButton updateUserButton = new JButton("Mettre à jour un utilisateur");
    //     updateUserButton.addActionListener(e -> cardLayout.show(mainPanel, "WhatUserToUpdate"));
    //     employeePanel.add(addEmployeeBtn);
    //     employeePanel.add(removeEmployeeBtn);
    //     employeePanel.add(listEmployeeBtn);
    //     employeePanel.add(updateUserButton);

    //     // Section Gestion de l'Inventaire
    //     JPanel inventoryPanel = new JPanel();
    //     JButton backButton = new JButton("Retour");
    //     backButton.addActionListener(e -> cardLayout.show(mainPanel, "Welcome"));
    //     inventoryPanel.add(backButton);



    //     // Ajouter les sections au panel principal
    //     panel.add(storePanel);
    //     panel.add(employeePanel);
    //     panel.add(inventoryPanel);

    //     return panel;
    // }

    // private JPanel createEmployeePanel() {
    //     AdminDAO adminDAO = new AdminDAO();
    //     UserDAO userDAO = new UserDAO();
    //     JPanel panel = new JPanel();
    //     panel.setLayout(new BorderLayout());

    //     // Titre
    //     JLabel titleLabel = new JLabel("Panneau Employé", JLabel.CENTER);
    //     titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
    //     panel.add(titleLabel, BorderLayout.NORTH);

    //     // Définition des colonnes
    //     String[] columnNames = {"ID", "Nom", "Email", "Rôle", "Magasin"};
    //     DefaultTableModel model = new DefaultTableModel(columnNames, 0); // Modèle de tableau vide

    //     model.setRowCount(0);
    //     List<User> employees = new ArrayList<>();
        
    //     String requeteSQL = "SELECT ID, EMAIL, PSEUDO, ROLE, STORE_ID FROM USER";
    //     try (Connection conn = DatabaseConfig.getConnection();
    //         PreparedStatement pstmt = conn.prepareStatement(requeteSQL);
    //         ResultSet rs = pstmt.executeQuery()) {

    //             while (rs.next()) {
    //                 int Id = rs.getInt("ID");
    //                 String Pseudo = rs.getString("PSEUDO");
    //                 String Email = rs.getString("EMAIL");
    //                 String Role = rs.getString("ROLE");
    //                 int storeID = rs.getInt("STORE_ID");

    //                 employees.add(new User(Id, Email, Pseudo, "", "", Role, storeID));

    //                 String storeNameFromDB = adminDAO.getStoreName(storeID);

    //                 model.addRow(new Object[]{Id, Pseudo, Email, Role, storeNameFromDB});

    //             }
    //         } catch (SQLException e) {
    //             JOptionPane.showMessageDialog(panel, "Erreur SQL : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
    //         }
        

    //     // Création du JTable avec le modèle dynamique
    //     JTable employeeTable = new JTable(model);
    //     JScrollPane scrollPane = new JScrollPane(employeeTable);
    //     panel.add(scrollPane, BorderLayout.CENTER);

    //     // Panel pour les boutons
    //     JPanel buttonPanel = new JPanel();
    //     JButton updateEmployeeBtn = new JButton("Modifier mon compte");
    //     updateEmployeeBtn.addActionListener(e -> {
    //         cardLayout.show(mainPanel, "UpdateUserForEmployee");
    //         emailToUpdate = currentEmailAccountConnected;
    //     });

    //     JButton removeEmployeeBtn = new JButton("Supprimer mon compte");
    //     removeEmployeeBtn.addActionListener(e -> {
    //         cardLayout.show(mainPanel, "Welcome");
    //         emailToUpdate = currentEmailAccountConnected;
    //         try {
    //             if (adminDAO.deleteUser(emailToUpdate)) {
    //                 JOptionPane.showMessageDialog(panel, "Votre compte a bien été supprimé", "iStore", JOptionPane.INFORMATION_MESSAGE);
    //             } else {
    //                 JOptionPane.showMessageDialog(panel, "Erreur lors de la création du magasin.", "Erreur", JOptionPane.ERROR_MESSAGE);
    //             }
    //         } catch (Exception ex) {
    //             System.out.println("Erreur lors de la suppression de votre compte : " + ex.getMessage());
    //         }

            
    //     });
    //     JButton backButton = new JButton("Retour");
    //     JButton manageStore = new JButton("Gérer mon magasin");
    //     manageStore.addActionListener(e -> {
    //         try {
    //             int storeID = userDAO.getUserStoreID(currentEmailAccountConnected);
    //             storeName = adminDAO.getStoreName(storeID);
    //         } catch (Exception ex) {
    //             System.out.println("Erreur lors de la recupération du storeID : " + ex.getMessage());
    //         }
    //         mainPanel.add(createDisplayItemsEmployeePanel(), "DisplayItemForEmployee");
    //         cardLayout.show(mainPanel, "DisplayItemForEmployee");
    //     });

    //     // Bouton Retour vers AdminDashboard
    //     backButton.addActionListener(e -> cardLayout.show(mainPanel, "Welcome"));

    //     // Ajouter les boutons au panel des boutons
    //     buttonPanel.add(updateEmployeeBtn);
    //     buttonPanel.add(removeEmployeeBtn);
    //     buttonPanel.add(manageStore);
    //     buttonPanel.add(backButton);

    //     panel.add(buttonPanel, BorderLayout.SOUTH);
        

    //     return panel;
    // }

    // private JPanel createStorePanel() {
    //     AdminDAO adminDAO = new AdminDAO();
    //     JPanel panel = new JPanel();
    //     panel.setLayout(new GridLayout(2, 2, 10, 10));

    //     panel.add(new JLabel("Nom du magasin :"));

    //     JTextField nameField = new JTextField();
    //     panel.add(nameField);

    //     JButton backButton = new JButton("Retour");
    //     backButton.addActionListener(e -> cardLayout.show(mainPanel, "AdminDashboard"));
    //     panel.add(backButton);
        
    //     JButton createButton = new JButton("Créer le Magasin");
    //     createButton.addActionListener(e -> {
    //         String Name = nameField.getText();
    //         try {
    //             if (!adminDAO.verifyName(Name)) {
    //                 if (adminDAO.createStore(0, Name)) {
    //                     JOptionPane.showMessageDialog(panel, "Magasin " + Name + " créé avec succès !.", "iStore", JOptionPane.INFORMATION_MESSAGE);
    //                 } else {
    //                     JOptionPane.showMessageDialog(panel, "Erreur lors de la création du magasin.", "Erreur", JOptionPane.ERROR_MESSAGE);
    //                 }
    //             } else {
    //                 JOptionPane.showMessageDialog(panel, "Le nom est déja utilisé.", "Erreur", JOptionPane.ERROR_MESSAGE);
    //             }
                
    //         } catch (Exception ex) {
    //             System.out.println("Erreur lors de la création du magasin : " + ex.getMessage());
    //         }
    //     });
        
    //     panel.add(createButton);

    //     return panel;
    // }

    // private JPanel createDisplayStoresPanel() {
    //     JPanel panel = new JPanel();
    //     panel.setLayout(new BorderLayout());
    //     // Titre
    //     JLabel titleLabel = new JLabel("Panneau Magasins", JLabel.CENTER);
    //     titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
    //     panel.add(titleLabel, BorderLayout.NORTH);

    //     // Définition des colonnes
    //     String[] columnNames = {"ID", "Nom"};
    //     DefaultTableModel model = new DefaultTableModel(columnNames, 0); // Modèle de tableau vide

    //     List<Store> stores = new ArrayList<>();
        
    //     String requeteSQL = "SELECT ID, NAME FROM STORE";
    //     try (Connection conn = DatabaseConfig.getConnection();
    //         PreparedStatement pstmt = conn.prepareStatement(requeteSQL);
    //         ResultSet rs = pstmt.executeQuery()) {

    //             while (rs.next()) {
    //                 int Id = rs.getInt("ID");
    //                 String Name = rs.getString("NAME");

    //                 stores.add(new Store(Id, Name));
    //             }
    //         } catch (SQLException e) {
    //             JOptionPane.showMessageDialog(panel, "Erreur SQL : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
    //         }
        
    //     // **Remplir le tableau avec les données de la liste**
    //     for (Store store : stores) {
    //         model.addRow(new Object[]{store.getId(), store.getName()});
    //     }

    //     // Création du JTable avec le modèle dynamique
    //     JTable employeeTable = new JTable(model);
    //     JScrollPane scrollPane = new JScrollPane(employeeTable);
    //     panel.add(scrollPane, BorderLayout.CENTER);

    //     // Panel pour les boutons
    //     JPanel buttonPanel = new JPanel();
    //     JButton backButton = new JButton("Retour");

    //     // Bouton Retour vers AdminDashboard
    //     backButton.addActionListener(e -> cardLayout.show(mainPanel, "AdminDashboard"));

    //     // Ajouter les boutons au panel des boutons
    //     buttonPanel.add(backButton);

    //     panel.add(buttonPanel, BorderLayout.SOUTH);
        

    //     return panel;
    // } 

    // private JPanel createDeleteStorePanel() {
    //     AdminDAO adminDAO = new AdminDAO();
    //     JPanel panel = new JPanel();
    //     panel.setLayout(new GridLayout(3, 3, 10, 10));
    //     // Titre
    //     JLabel titleLabel = new JLabel("Liste des magasins", JLabel.CENTER);
    //     titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
    //     panel.add(titleLabel, BorderLayout.NORTH);

    //     // Définition des colonnes
    //     String[] columnNames = {"ID", "Nom"};
    //     DefaultTableModel model = new DefaultTableModel(columnNames, 0); // Modèle de tableau vide

    //     List<Store> stores = new ArrayList<>();
        
    //     String requeteSQL = "SELECT ID, NAME FROM STORE";
    //     try (Connection conn = DatabaseConfig.getConnection();
    //         PreparedStatement pstmt = conn.prepareStatement(requeteSQL);
    //         ResultSet rs = pstmt.executeQuery()) {

    //             while (rs.next()) {
    //                 int Id = rs.getInt("ID");
    //                 String Name = rs.getString("NAME");

    //                 stores.add(new Store(Id, Name));
    //             }
    //         } catch (SQLException e) {
    //             JOptionPane.showMessageDialog(panel, "Erreur SQL : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
    //         }
        
    //     // **Remplir le tableau avec les données de la liste**
    //     for (Store store : stores) {
    //         model.addRow(new Object[]{store.getId(), store.getName()});
    //     }

    //     // Création du JTable avec le modèle dynamique
    //     JTable employeeTable = new JTable(model);
    //     JScrollPane scrollPane = new JScrollPane(employeeTable);
    //     panel.add(scrollPane, BorderLayout.CENTER);

    //     panel.add(new JLabel("Nom du magasin a suprimer :", JLabel.CENTER));
    //     JTextField nameField = new JTextField();
    //     panel.add(nameField);
    //     // Panel pour les boutons
    //     JPanel buttonPanel = new JPanel();
    //     JButton backButton = new JButton("Retour");
    //     JButton validationButton = new JButton("Suprimer");
        
    //     // Bouton Retour vers AdminDashboard
    //     backButton.addActionListener(e -> cardLayout.show(mainPanel, "AdminDashboard"));
    //     validationButton.addActionListener(e -> {
    //         try {
    //             String Name = nameField.getText();
    //             if (adminDAO.deleteStore(Name)) {
    //                 JOptionPane.showMessageDialog(panel, "Magasin " + Name + " supprimé avec succès !.", "iStore", JOptionPane.INFORMATION_MESSAGE);
    //             } else {
    //                 JOptionPane.showMessageDialog(panel, "Erreur lors de la suppression du magasin.", "Erreur", JOptionPane.ERROR_MESSAGE);
    //             }
    //         } catch (Exception ex) {
    //             System.out.println("Erreur lors de la suppression du magasin : " + ex.getMessage());
    //         }
            
    //     });

    //     // Ajouter les boutons au panel des boutons
    //     buttonPanel.add(backButton);
    //     buttonPanel.add(validationButton);

    //     panel.add(buttonPanel, BorderLayout.CENTER);
        

    //     return panel;
    // } 

    // private JPanel createAddUserPanel() {
    //     UserDAO userDAO = new UserDAO();
    //     AdminDAO adminDAO = new AdminDAO();
    //     JPanel panel = new JPanel();
    //     panel.setLayout(new GridLayout(5, 2, 10, 10));

    //     panel.add(new JLabel("Email :"));
    //     JTextField emailField = new JTextField();
    //     panel.add(emailField);

    //     panel.add(new JLabel("Pseudo :"));
    //     JTextField pseudoField = new JTextField();
    //     panel.add(pseudoField);

    //     panel.add(new JLabel("Mot de passe :"));
    //     JPasswordField passwordField = new JPasswordField();
    //     panel.add(passwordField);

    //     panel.add(new JLabel("Magasin :"));
    //     JTextField storeField = new JTextField();
    //     panel.add(storeField);

    //     JButton backButton = new JButton("Retour");
    //     backButton.addActionListener(e -> cardLayout.show(mainPanel, "AdminDashboard"));

    //     JButton signUpButton = new JButton("Ajouter");

    //     signUpButton.addActionListener(e -> {
    //         String Email = emailField.getText();
    //         String Pseudo = pseudoField.getText();
    //         String Password = new String(passwordField.getPassword());
    //         String storeNameField = storeField.getText();
            
    //         try{
    //             int storeID = adminDAO.getStoreID(storeNameField); 
    //             PasswordHash.HashResults hashResults = PasswordHash.passwordHash(Password);
    
    //             User user = new User(0, Email, Pseudo, hashResults.getHashedPassword(), hashResults.getSalt(), "Employé", storeID);
    
    //             if (userDAO.verifyEmail(Email)) {
    //                 JOptionPane.showMessageDialog(panel, "Email déja utilisé, veuillez vous connecter.", "Erreur", JOptionPane.ERROR_MESSAGE);

    //             }
    //             if (!adminDAO.verifyName(storeNameField)) {
    //                 System.out.println(adminDAO.verifyName(storeNameField));
    //                 JOptionPane.showMessageDialog(panel, "Magasin inconnu", "Erreur", JOptionPane.ERROR_MESSAGE);
    //             }
    //             if (userDAO.createUser(user)) {
    //                 JOptionPane.showMessageDialog(panel, "Utilisateur " + Email + " créé avec succès !.", "iStore", JOptionPane.INFORMATION_MESSAGE);
    //             }
                
    //         } catch (Exception ex) {
    //             System.out.println("Erreur lors de la création du compte : " + ex.getMessage());
    //         }
    //     });

    //     panel.add(backButton);
    //     panel.add(signUpButton);

    //     return panel;
    // }

    // private JPanel createDeleteUserPanel() {
    //     AdminDAO adminDAO = new AdminDAO();
    //     JPanel panel = new JPanel();
    //     panel.setLayout(new GridLayout(3, 3, 10, 10));
    //     // Titre
    //     JLabel titleLabel = new JLabel("Liste des utilisateurs", JLabel.CENTER);
    //     titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
    //     panel.add(titleLabel, BorderLayout.NORTH);

    //     // Définition des colonnes
    //     String[] columnNames = {"ID", "Email", "Pseudo", "Rôle"};
    //     DefaultTableModel model = new DefaultTableModel(columnNames, 0); // Modèle de tableau vide

    //     List<User> employees = new ArrayList<>();
        
    //     String requeteSQL = "SELECT ID, EMAIL, PSEUDO, ROLE FROM USER";
    //     try (Connection conn = DatabaseConfig.getConnection();
    //         PreparedStatement pstmt = conn.prepareStatement(requeteSQL);
    //         ResultSet rs = pstmt.executeQuery()) {

    //             while (rs.next()) {
    //                 int Id = rs.getInt("ID");
    //                 String Email = rs.getString("EMAIL");
    //                 String Pseudo = rs.getString("PSEUDO");
    //                 String Role = rs.getString("ROLE");

    //                 employees.add(new User(Id, Email, Pseudo, "", "", Role, -1));
    //             }
    //         } catch (SQLException e) {
    //             JOptionPane.showMessageDialog(panel, "Erreur SQL : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
    //         }
        
    //     // **Remplir le tableau avec les données de la liste**
    //     for (User user : employees) {
    //         model.addRow(new Object[]{user.getId(), user.getEmail(), user.getPseudo(), user.getRole()});
    //     }

    //     // Création du JTable avec le modèle dynamique
    //     JTable employeeTable = new JTable(model);
    //     JScrollPane scrollPane = new JScrollPane(employeeTable);
    //     panel.add(scrollPane, BorderLayout.CENTER);

    //     panel.add(new JLabel("Email de l'utilisateur à suprimer :", JLabel.CENTER));
    //     JTextField emailField = new JTextField();
    //     panel.add(emailField);
    //     // Panel pour les boutons
    //     JPanel buttonPanel = new JPanel();
    //     JButton backButton = new JButton("Retour");
    //     JButton validationButton = new JButton("Suprimer");
        
    //     // Bouton Retour vers AdminDashboard
    //     backButton.addActionListener(e -> cardLayout.show(mainPanel, "AdminDashboard"));
    //     validationButton.addActionListener(e -> {
    //         try {
    //             String Email = emailField.getText();
    //             if (adminDAO.deleteUser(Email)) {
    //                 JOptionPane.showMessageDialog(panel, "Utilisateur " + Email + " supprimé avec succès !.", "iStore", JOptionPane.INFORMATION_MESSAGE);
    //             } else {
    //                 JOptionPane.showMessageDialog(panel, "Erreur lors de la suppression de l'utilisateur.", "Erreur", JOptionPane.ERROR_MESSAGE);
    //             }
    //         } catch (Exception ex) {
    //             System.out.println("Erreur lors de la suppression de l'utilisateur : " + ex.getMessage());
    //         }
            
    //     });

    //     // Ajouter les boutons au panel des boutons
    //     buttonPanel.add(backButton);
    //     buttonPanel.add(validationButton);

    //     panel.add(buttonPanel, BorderLayout.CENTER);
        

    //     return panel;
    // }

    // private JPanel createDisplayUserPanel() {
    //     AdminDAO adminDAO = new AdminDAO();
    //     JPanel panel = new JPanel();
    //     panel.setLayout(new BorderLayout());
    //     // Titre
    //     JLabel titleLabel = new JLabel("Panneau Utilisateurs", JLabel.CENTER);
    //     titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
    //     panel.add(titleLabel, BorderLayout.NORTH);

    //     // Définition des colonnes
    //     String[] columnNames = {"ID", "Email", "Pseudo", "Rôle", "Magasin"};
    //     DefaultTableModel model = new DefaultTableModel(columnNames, 0); // Modèle de tableau vide
    //     model.setRowCount(0);
    //     List<User> employees = new ArrayList<>();
        
    //     String requeteSQL = "SELECT ID, EMAIL, PSEUDO, ROLE, STORE_ID FROM USER";
    //     try (Connection conn = DatabaseConfig.getConnection();
    //         PreparedStatement pstmt = conn.prepareStatement(requeteSQL);
    //         ResultSet rs = pstmt.executeQuery()) {

    //             while (rs.next()) {
    //                 int Id = rs.getInt("ID");
    //                 String Email = rs.getString("EMAIL");
    //                 String Pseudo = rs.getString("PSEUDO");
    //                 String Role = rs.getString("ROLE");
    //                 int storeID = rs.getInt("STORE_ID");

    //                 String storeName = adminDAO.getStoreName(storeID); 

    //                 employees.add(new User(Id, Email, Pseudo, "", "", Role, storeID));

    //                 model.addRow(new Object[]{Id, Email, Pseudo, Role, storeName});
    //             }
                
    //         } catch (SQLException e) {
    //             JOptionPane.showMessageDialog(panel, "Erreur SQL : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
    //         }
        
    //     // Création du JTable avec le modèle dynamique
    //     JTable employeeTable = new JTable(model);
    //     JScrollPane scrollPane = new JScrollPane(employeeTable);
    //     panel.add(scrollPane, BorderLayout.CENTER);

    //     // Panel pour les boutons
    //     JPanel buttonPanel = new JPanel();
    //     JButton backButton = new JButton("Retour");

    //     // Bouton Retour vers AdminDashboard
    //     backButton.addActionListener(e -> cardLayout.show(mainPanel, "AdminDashboard"));

    //     // Ajouter les boutons au panel des boutons
    //     buttonPanel.add(backButton);

    //     panel.add(buttonPanel, BorderLayout.SOUTH);
        

    //     return panel;
    // }

    // private JPanel createUpdateWhatUserPanel() {
    //     UserDAO userDAO = new UserDAO();
    //     JPanel panel = new JPanel();
    //     panel.setLayout(new GridLayout(3, 3, 10, 10));
    //     // Titre
    //     JLabel titleLabel = new JLabel("Liste des utilisateurs", JLabel.CENTER);
    //     titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
    //     panel.add(titleLabel, BorderLayout.NORTH);

    //     // Définition des colonnes
    //     String[] columnNames = {"ID", "Email", "Pseudo", "Rôle"};
    //     DefaultTableModel model = new DefaultTableModel(columnNames, 0); // Modèle de tableau vide

    //     List<User> employees = new ArrayList<>();
        
    //     String requeteSQL = "SELECT ID, EMAIL, PSEUDO, ROLE FROM USER";
    //     try (Connection conn = DatabaseConfig.getConnection();
    //         PreparedStatement pstmt = conn.prepareStatement(requeteSQL);
    //         ResultSet rs = pstmt.executeQuery()) {

    //             while (rs.next()) {
    //                 int Id = rs.getInt("ID");
    //                 String Email = rs.getString("EMAIL");
    //                 String Pseudo = rs.getString("PSEUDO");
    //                 String Role = rs.getString("ROLE");

    //                 employees.add(new User(Id, Email, Pseudo, "", "", Role, -1));
    //             }
    //         } catch (SQLException e) {
    //             JOptionPane.showMessageDialog(panel, "Erreur SQL : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
    //         }
        
    //     // **Remplir le tableau avec les données de la liste**
    //     for (User user : employees) {
    //         model.addRow(new Object[]{user.getId(), user.getEmail(), user.getPseudo(), user.getRole()});
    //     }

    //     // Création du JTable avec le modèle dynamique
    //     JTable employeeTable = new JTable(model);
    //     JScrollPane scrollPane = new JScrollPane(employeeTable);
    //     panel.add(scrollPane, BorderLayout.CENTER);

    //     panel.add(new JLabel("Email de l'utilisateur à suprimer :", JLabel.CENTER));
    //     JTextField emailField = new JTextField();
    //     panel.add(emailField);
    //     // Panel pour les boutons
    //     JPanel buttonPanel = new JPanel();
    //     JButton backButton = new JButton("Retour");
    //     JButton updateButton = new JButton("Mettre à jour");
        
    //     // Bouton Retour vers AdminDashboard
    //     backButton.addActionListener(e -> cardLayout.show(mainPanel, "AdminDashboard"));
    //     updateButton.addActionListener(e -> {
    //         try {
    //             String Email = emailField.getText();
    //             if (!userDAO.verifyEmail(Email)) {
    //                 JOptionPane.showMessageDialog(panel, "Cet utilisateur n'existe pas. Veuillez saisir un autre email", "Erreur", JOptionPane.ERROR_MESSAGE);
    //             } else {
    //                 emailToUpdate = Email;
    //                 cardLayout.show(mainPanel, "UpdateUserForAdmin");
    //             }
    //         } catch (Exception ex) {
    //             System.out.println("Erreur lors de la suppression de l'utilisateur : " + ex.getMessage());
    //         }
            
    //     });

    //     // Ajouter les boutons au panel des boutons
    //     buttonPanel.add(backButton);
    //     buttonPanel.add(updateButton);

    //     panel.add(buttonPanel, BorderLayout.CENTER);
        

    //     return panel;
    // }

    // private JPanel createUpdateUserPanelForAdmin() {
    //     AdminDAO adminDAO = new AdminDAO();
    //     JPanel panel = new JPanel();
    //     panel.setLayout(new GridLayout(5, 2, 10, 10));
        
    //     panel.add(new JLabel("Email :"));
    //     JTextField emailField = new JTextField();
    //     panel.add(emailField);

    //     panel.add(new JLabel("Pseudo :"));
    //     JTextField pseudoField = new JTextField();
    //     panel.add(pseudoField);

    //     panel.add(new JLabel("Role :"));
    //     JTextField roleField = new JTextField();
    //     panel.add(roleField);

    //     panel.add(new JLabel("Mot de passe :"));
    //     JPasswordField passwordField = new JPasswordField();
    //     panel.add(passwordField);
    //     JButton backButton = new JButton("Retour");
    //     backButton.addActionListener(e -> cardLayout.show(mainPanel, "WhatUserToUpdate"));
        

    //     JButton signUpButton = new JButton("Mettre à jour");
    //     signUpButton.addActionListener(e -> {
    //         String newEmail = emailField.getText();
    //         String newPseudo = pseudoField.getText();
    //         String newRole = roleField.getText();
    //         String newPassword = new String(passwordField.getPassword());
    //         try{
    //             PasswordHash.HashResults hashResults = PasswordHash.passwordHash(newPassword);
    //             if (adminDAO.updateUser(emailToUpdate, newEmail, newPseudo, hashResults.getHashedPassword(), hashResults.getSalt(), newRole)) {
    //                 JOptionPane.showMessageDialog(panel, "L'utilisateur " + newPseudo + " à été mis a jour avec succès !.", "iStore", JOptionPane.INFORMATION_MESSAGE);
    //             }
                
    //         } catch (Exception ex) {
    //             System.out.println("Erreur lors de la mise a jour de l'utilisateur : " + ex.getMessage());
    //         }
    //     });

    //     panel.add(backButton);
    //     panel.add(signUpButton);

    //     return panel;
    // }

    // private JPanel createUpdateUserPanelForEmployee() {
    //     AdminDAO adminDAO = new AdminDAO();
    //     JPanel panel = new JPanel();
    //     panel.setLayout(new GridLayout(5, 2, 10, 10));
        
    //     panel.add(new JLabel("Email :"));
    //     JTextField emailField = new JTextField();
    //     panel.add(emailField);

    //     panel.add(new JLabel("Pseudo :"));
    //     JTextField pseudoField = new JTextField();
    //     panel.add(pseudoField);

    //     panel.add(new JLabel("Role :"));
    //     JTextField roleField = new JTextField();
    //     panel.add(roleField);

    //     panel.add(new JLabel("Mot de passe :"));
    //     JPasswordField passwordField = new JPasswordField();
    //     panel.add(passwordField);
    //     JButton backButton = new JButton("Retour");
    //     backButton.addActionListener(e -> cardLayout.show(mainPanel, "EmployeePanel"));
        

    //     JButton signUpButton = new JButton("Mettre à jour");

    //     signUpButton.addActionListener(e -> {
    //         String newEmail = emailField.getText();
    //         String newPseudo = pseudoField.getText();
    //         String newRole = roleField.getText();
    //         String newPassword = new String(passwordField.getPassword());
    //         try{
    //             PasswordHash.HashResults hashResults = PasswordHash.passwordHash(newPassword);
    //             if (adminDAO.updateUser(emailToUpdate, newEmail, newPseudo, hashResults.getHashedPassword(), hashResults.getSalt(), newRole)) {
    //                 JOptionPane.showMessageDialog(panel, "L'utilisateur " + newPseudo + " à été mis a jour avec succès !.", "iStore", JOptionPane.INFORMATION_MESSAGE);
    //             }
                
    //         } catch (Exception ex) {
    //             System.out.println("Erreur lors de la mise a jour de l'utilisateur : " + ex.getMessage());
    //         }
    //     });

    //     panel.add(backButton);
    //     panel.add(signUpButton);

    //     return panel;
    // }

    // private JPanel createManageWhatInventory() {
    //     AdminDAO adminDAO = new AdminDAO();
    //     JPanel panel = new JPanel();
    //     panel.setLayout(new GridLayout(3, 3, 10, 10));
    //     // Titre
    //     JLabel titleLabel = new JLabel("Liste des magasins", JLabel.CENTER);
    //     titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
    //     panel.add(titleLabel, BorderLayout.NORTH);

    //     // Définition des colonnes
    //     String[] columnNames = {"ID", "Nom"};
    //     DefaultTableModel model = new DefaultTableModel(columnNames, 0);

    //     List<Store> stores = new ArrayList<>();
        
    //     String requeteSQL = "SELECT ID, NAME FROM STORE";
    //     try (Connection conn = DatabaseConfig.getConnection();
    //         PreparedStatement pstmt = conn.prepareStatement(requeteSQL);
    //         ResultSet rs = pstmt.executeQuery()) {

    //             while (rs.next()) {
    //                 int Id = rs.getInt("ID");
    //                 String Name = rs.getString("NAME");

    //                 stores.add(new Store(Id, Name));
    //             }
    //         } catch (SQLException e) {
    //             JOptionPane.showMessageDialog(panel, "Erreur SQL : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
    //         }
        
    //     // **Remplir le tableau avec les données de la liste**
    //     for (Store store : stores) {
    //         model.addRow(new Object[]{store.getId(), store.getName()});
    //     }

    //     // Création du JTable avec le modèle dynamique
    //     JTable employeeTable = new JTable(model);
    //     JScrollPane scrollPane = new JScrollPane(employeeTable);
    //     panel.add(scrollPane, BorderLayout.CENTER);

    //     panel.add(new JLabel("Nom du magasin à gérer :", JLabel.CENTER));
    //     final JTextField nameField = new JTextField();
    //     panel.add(nameField);
    //     // Panel pour les boutons
    //     JPanel buttonPanel = new JPanel();
    //     JButton backButton = new JButton("Retour");
    //     JButton validationButton = new JButton("Ouvrir l'inventaire");
        
    //     // Bouton Retour vers AdminDashboard
    //     backButton.addActionListener(e -> cardLayout.show(mainPanel, "AdminDashboard"));
    //     validationButton.addActionListener(e -> {
    //         try {
    //             String Name = nameField.getText();
    //             if (adminDAO.verifyName(Name)) {
    //                 storeName = Name;
    //                 mainPanel.add(createDisplayItemsPanel(), "DisplayItem");
    //                 cardLayout.show(mainPanel, "DisplayItem");
    //             } else {
    //                 JOptionPane.showMessageDialog(panel, "Ce magasin n'existe pas", "Erreur", JOptionPane.ERROR_MESSAGE);
    //             }
    //         } catch (Exception ex) {
    //             System.out.println("Erreur lors de l'affiche des items : " + ex.getMessage());
    //         }

    //     });

    //     // Ajouter les boutons au panel des boutons
    //     buttonPanel.add(backButton);
    //     buttonPanel.add(validationButton);

    //     panel.add(buttonPanel, BorderLayout.CENTER);
        

    //     return panel;
    // }

    // private JPanel createDisplayItemsPanel() {
    //     JPanel panel = new JPanel();
    //     panel.setLayout(new BorderLayout());
    //     // Titre
    //     JLabel titleLabel = new JLabel("Panneau Inventaire", JLabel.CENTER);
    //     titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
    //     panel.add(titleLabel, BorderLayout.NORTH);

    //     // Définition des colonnes
    //     String[] columnNames = {"ID", "Name", "Price", "Stock"};
    //     DefaultTableModel model = new DefaultTableModel(columnNames, 0);

    //     List<Item> items = new ArrayList<>();

    //     String requeteSQL = """
    //         SELECT I.ID, I.NAME, I.PRICE, I.STOCK
    //         FROM ITEMS I
    //         INNER JOIN INVENTORY INV ON I.ID = INV.ITEM_ID
    //         INNER JOIN STORE S ON INV.STORE_ID = S.ID
    //         WHERE S.NAME = ?
    //     """;
    //     try (Connection conn = DatabaseConfig.getConnection();
    //         PreparedStatement pstmt = conn.prepareStatement(requeteSQL)) {
    //         pstmt.setString(1, storeName);
    //         try (ResultSet rs = pstmt.executeQuery()) {
    //             while (rs.next()) {
    //                 int id = rs.getInt("ID");
    //                 String name = rs.getString("NAME");
    //                 BigDecimal price = rs.getBigDecimal("PRICE"); 
    //                 int stock = rs.getInt("STOCK");

    //                 items.add(new Item(id, name, price, stock));
    //             }
    //         }
    //     } catch (SQLException e) {
    //         JOptionPane.showMessageDialog(panel, "Erreur SQL : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
    //     }

        
    //     // **Remplir le tableau avec les données de la liste**
    //     for (Item item : items) {
    //         model.addRow(new Object[]{item.getId(), item.getName(), item.getPrice(), item.getStock()});
    //     }

    //     // Création du JTable avec le modèle dynamique
    //     JTable employeeTable = new JTable(model);
    //     JScrollPane scrollPane = new JScrollPane(employeeTable);
    //     panel.add(scrollPane, BorderLayout.CENTER);

    //     // Panel pour les boutons
    //     JPanel buttonPanel = new JPanel();
    //     JButton backButton = new JButton("Retour");
    //     backButton.addActionListener(e -> cardLayout.show(mainPanel, "WhatInventoryToManage"));
    //     JButton addItem = new JButton("Ajouter un item");
    //     addItem.addActionListener(e -> cardLayout.show(mainPanel, "AddItem"));
    //     JButton deleteItem = new JButton("Supprimer un item");
    //     deleteItem.addActionListener(e -> {
    //         mainPanel.add(createDeleteItemPanel(), "DeleteItem");
    //         cardLayout.show(mainPanel, "DeleteItem");
    //     });
    //     JButton updateItem = new JButton("Mettre à jour un item");
    //     updateItem.addActionListener(e -> {
    //         mainPanel.add(createUpdateItemPanel(), "UpdateItem");
    //         cardLayout.show(mainPanel, "UpdateItem");
    //     });

    //     // Bouton Retour vers AdminDashboard

    //     // Ajouter les boutons au panel des boutons
    //     buttonPanel.add(addItem);
    //     buttonPanel.add(deleteItem);
    //     buttonPanel.add(updateItem);
    //     buttonPanel.add(backButton);



    //     panel.add(buttonPanel, BorderLayout.SOUTH);
        

    //     return panel;
    // }

    // private JPanel createDisplayItemsEmployeePanel() {
    //     JPanel panel = new JPanel();
    //     panel.setLayout(new BorderLayout());
    //     // Titre
    //     JLabel titleLabel = new JLabel("Panneau Inventaire", JLabel.CENTER);
    //     titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
    //     panel.add(titleLabel, BorderLayout.NORTH);

    //     // Définition des colonnes
    //     String[] columnNames = {"ID", "Name", "Price", "Stock"};
    //     DefaultTableModel model = new DefaultTableModel(columnNames, 0);

    //     List<Item> items = new ArrayList<>();

    //     String requeteSQL = """
    //         SELECT I.ID, I.NAME, I.PRICE, I.STOCK
    //         FROM ITEMS I
    //         INNER JOIN INVENTORY INV ON I.ID = INV.ITEM_ID
    //         INNER JOIN STORE S ON INV.STORE_ID = S.ID
    //         WHERE S.NAME = ?
    //     """;
    //     try (Connection conn = DatabaseConfig.getConnection();
    //         PreparedStatement pstmt = conn.prepareStatement(requeteSQL)) {
    //         pstmt.setString(1, storeName);
    //         try (ResultSet rs = pstmt.executeQuery()) {
    //             while (rs.next()) {
    //                 int id = rs.getInt("ID");
    //                 String name = rs.getString("NAME");
    //                 BigDecimal price = rs.getBigDecimal("PRICE"); 
    //                 int stock = rs.getInt("STOCK");

    //                 items.add(new Item(id, name, price, stock));
    //             }
    //         }
    //     } catch (SQLException e) {
    //         JOptionPane.showMessageDialog(panel, "Erreur SQL : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
    //     }

        
    //     // **Remplir le tableau avec les données de la liste**
    //     for (Item item : items) {
    //         model.addRow(new Object[]{item.getId(), item.getName(), item.getPrice(), item.getStock()});
    //     }

    //     // Création du JTable avec le modèle dynamique
    //     JTable employeeTable = new JTable(model);
    //     JScrollPane scrollPane = new JScrollPane(employeeTable);
    //     panel.add(scrollPane, BorderLayout.CENTER);

    //     // Panel pour les boutons
    //     JPanel buttonPanel = new JPanel();
    //     JButton backButton = new JButton("Retour");
    //     backButton.addActionListener(e -> cardLayout.show(mainPanel, "EmployeePanel"));
    //     JButton addItem = new JButton("Ajouter un item");
    //     addItem.addActionListener(e -> cardLayout.show(mainPanel, "AddItem"));
    //     JButton deleteItem = new JButton("Supprimer un item");
    //     deleteItem.addActionListener(e -> {
    //         mainPanel.add(createDeleteItemPanel(), "DeleteItem");
    //         cardLayout.show(mainPanel, "DeleteItem");
    //     });
    //     JButton updateItem = new JButton("Mettre à jour un item");
    //     updateItem.addActionListener(e -> {
    //         mainPanel.add(createUpdateItemPanel(), "UpdateItem");
    //         cardLayout.show(mainPanel, "UpdateItem");
    //     });

    //     // Bouton Retour vers AdminDashboard

    //     // Ajouter les boutons au panel des boutons
    //     buttonPanel.add(addItem);
    //     buttonPanel.add(deleteItem);
    //     buttonPanel.add(updateItem);
    //     buttonPanel.add(backButton);



    //     panel.add(buttonPanel, BorderLayout.SOUTH);
        

    //     return panel;
    // }

    // private JPanel createDeleteItemPanel() {
    //     AdminDAO adminDAO = new AdminDAO();
    //     JPanel panel = new JPanel();
    //     panel.setLayout(new GridLayout(3, 3, 10, 10));
    //     // Titre
    //     JLabel titleLabel = new JLabel("Liste des Items", JLabel.CENTER);
    //     titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
    //     panel.add(titleLabel, BorderLayout.NORTH);

    //     // Définition des colonnes
    //     String[] columnNames = {"ID", "Name", "Prix", "Stock"};
    //     DefaultTableModel model = new DefaultTableModel(columnNames, 0); // Modèle de tableau vide

    //     List<Item> items = new ArrayList<>();
        
    //     String requeteSQL = """
    //         SELECT I.ID, I.NAME, I.PRICE, I.STOCK
    //         FROM ITEMS I
    //         INNER JOIN INVENTORY INV ON I.ID = INV.ITEM_ID
    //         INNER JOIN STORE S ON INV.STORE_ID = S.ID
    //         WHERE S.NAME = ?
    //     """;
    //     try (Connection conn = DatabaseConfig.getConnection();
    //         PreparedStatement pstmt = conn.prepareStatement(requeteSQL)) {
    //         pstmt.setString(1, storeName);
    //         try (ResultSet rs = pstmt.executeQuery()) {
    //             while (rs.next()) {
    //                 int id = rs.getInt("ID");
    //                 String name = rs.getString("NAME");
    //                 BigDecimal price = rs.getBigDecimal("PRICE"); 
    //                 int stock = rs.getInt("STOCK");

    //                 items.add(new Item(id, name, price, stock));
    //             }
    //         }
    //     } catch (SQLException e) {
    //         JOptionPane.showMessageDialog(panel, "Erreur SQL : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
    //     }
        
    //     // **Remplir le tableau avec les données de la liste**
    //     for (Item item : items) {
    //         model.addRow(new Object[]{item.getId(), item.getName(), item.getPrice(), item.getStock()});
    //     }

    //     // Création du JTable avec le modèle dynamique
    //     JTable employeeTable = new JTable(model);
    //     JScrollPane scrollPane = new JScrollPane(employeeTable);
    //     panel.add(scrollPane, BorderLayout.CENTER);

    //     panel.add(new JLabel("Nom de l'item à supprimer :", JLabel.CENTER));
    //     JTextField nameField = new JTextField();
    //     panel.add(nameField);
    //     // Panel pour les boutons
    //     JPanel buttonPanel = new JPanel();
    //     JButton backButton = new JButton("Retour");
    //     JButton validationButton = new JButton("Suprimer");
        
    //     // Bouton Retour vers AdminDashboard
    //     backButton.addActionListener(e -> cardLayout.show(mainPanel, "DisplayItem"));
    //     validationButton.addActionListener(e -> {
    //         try {
    //             String Name = nameField.getText();
    //             if (adminDAO.deleteItem(Name, storeName)) {
    //                 JOptionPane.showMessageDialog(panel, "Item " + Name + " supprimé avec succès !.", "iStore", JOptionPane.INFORMATION_MESSAGE);
    //             } else {
    //                 JOptionPane.showMessageDialog(panel, "Erreur lors de la suppression de l'item.", "Erreur", JOptionPane.ERROR_MESSAGE);
    //             }
    //         } catch (Exception ex) {
    //             System.out.println("Erreur lors de la suppression de l'item : " + ex.getMessage());
    //         }
            
    //     });

    //     // Ajouter les boutons au panel des boutons
    //     buttonPanel.add(backButton);
    //     buttonPanel.add(validationButton);

    //     panel.add(buttonPanel, BorderLayout.CENTER);
        

    //     return panel;
    // }

    // private JPanel createAddItemPanel() {
    //     AdminDAO adminDAO = new AdminDAO();
    //     JPanel panel = new JPanel();
    //     panel.setLayout(new GridLayout(4, 2, 10, 10));

    //     panel.add(new JLabel("Name :"));
    //     JTextField nameField = new JTextField();
    //     panel.add(nameField);

    //     panel.add(new JLabel("Prix :"));
    //     JTextField priceField = new JTextField();
    //     panel.add(priceField);

    //     panel.add(new JLabel("Stock :"));
    //     JTextField stockField = new JTextField();
    //     panel.add(stockField);

    //     JButton backButton = new JButton("Retour");
    //     backButton.addActionListener(e -> cardLayout.show(mainPanel, "DisplayItem"));

    //     JButton addButton = new JButton("Ajouter");

    //     addButton.addActionListener(e -> {
    //         String Name = nameField.getText();
    //         BigDecimal Price = new BigDecimal(priceField.getText());
    //         int Stock = Integer.parseInt(stockField.getText());
    //         try{
    
    //             Item item = new Item(0, Name, Price, Stock);
    //             if (adminDAO.verifyItemName(Name)) {
    //                 JOptionPane.showMessageDialog(panel, "Cet item existe déja.", "Erreur", JOptionPane.ERROR_MESSAGE);

    //             }
    //             if (adminDAO.createItem(item)) {
    //                 int itemID = adminDAO.getItemID(Name);
    //                 int storeID = adminDAO.getStoreID(storeName);
    //                 if (adminDAO.addInventory(itemID, storeID)) {
    //                     JOptionPane.showMessageDialog(panel, "L'item " + item.getName() + " à été ajouté.", "iStore", JOptionPane.INFORMATION_MESSAGE);
    //                 }
    //             }
                
    //         } catch (Exception ex) {
    //             System.out.println("Erreur lors de la création de l'item : " + ex.getMessage());
    //         }
    //     });

    //     panel.add(backButton);
    //     panel.add(addButton);

    //     return panel;
    // }

    // private JPanel createUpdateItemPanel() {
    //     AdminDAO adminDAO = new AdminDAO();
    //     JPanel panel = new JPanel();
    //     panel.setLayout(new GridLayout(4, 2, 10, 10));

    //     panel.add(new JLabel("Name :"));
    //     JTextField nameField = new JTextField();
    //     panel.add(nameField);

    //     panel.add(new JLabel("Prix :"));
    //     JTextField priceField = new JTextField();
    //     panel.add(priceField);

    //     panel.add(new JLabel("Stock :"));
    //     JTextField stockField = new JTextField();
    //     panel.add(stockField);

    //     JButton backButton = new JButton("Retour");
    //     backButton.addActionListener(e -> cardLayout.show(mainPanel, "DisplayItem"));

    //     JButton addButton = new JButton("Mettre à jour");

    //     addButton.addActionListener(e -> {
    //         String Name = nameField.getText();
    //         BigDecimal Price = new BigDecimal(priceField.getText());
    //         int Stock = Integer.parseInt(stockField.getText());
    //         try{
    
    //             Item item = new Item(0, Name, Price, Stock);
    //             if (adminDAO.updateItem(Name, Name, Price, Stock)) {
    //                 JOptionPane.showMessageDialog(panel, "L'item " + item.getName() + " à été mis à jour.", "iStore", JOptionPane.INFORMATION_MESSAGE);
    //             }
                
    //         } catch (Exception ex) {
    //             System.out.println("Erreur lors de la création de l'item : " + ex.getMessage());
    //         }
    //     });

    //     panel.add(backButton);
    //     panel.add(addButton);

    //     return panel;
    // }

}


