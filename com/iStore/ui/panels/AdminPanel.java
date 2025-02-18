package com.iStore.ui.panels;

import javax.swing.*;
import java.awt.*;

/**
 * Panneau d'affichage pour les Administrateurs.
 * Cette interface permet a l'administrateur de gérer les magasins et les employés.
 * Pour la gestion des magasins, l'administrateur peut en créer, en supprimer, les afficher et gerer leur inventaires.
 * Pour la gestion des employés, l'administrateur peut en ajouter(en les affiliant à un magasin), en supprimer, les afficher puis mettre à jour un employé.
 */
public class AdminPanel extends JPanel{
    /**
     * Constructeur du panneau d'administration.
     * @param cardLayout Le gestionnaire de disposition pour la navigation entre les panneaux.
     * @param mainPanel  Le panneau principal contenant tous les écrans de l'application.
     */
    public AdminPanel(CardLayout cardLayout, JPanel mainPanel) {
        setLayout(new GridLayout(4, 1, 10, 10));

        JLabel titleLabel = new JLabel("Panneau d'Administration", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        add(titleLabel);

        //Gestion des Magasins
        JPanel storePanel = new JPanel();
        storePanel.setBorder(BorderFactory.createTitledBorder("Gestion des Magasins"));
        JButton createStoreBtn = new JButton("Créer un magasin");
        createStoreBtn.addActionListener(e -> cardLayout.show(mainPanel, "StoreCreation"));
        JButton deleteStoreBtn = new JButton("Supprimer un magasin");
        deleteStoreBtn.addActionListener(e -> cardLayout.show(mainPanel, "DeleteStore"));
        JButton listStoreBtn = new JButton("Voir les magasins");
        listStoreBtn.addActionListener(e -> cardLayout.show(mainPanel, "DisplayStore"));
        JButton manageInventoryBtn = new JButton("Gérer les inventaires");
        manageInventoryBtn.addActionListener(e -> cardLayout.show(mainPanel, "WhatInventoryToManage"));
        storePanel.add(createStoreBtn);
        storePanel.add(deleteStoreBtn);
        storePanel.add(listStoreBtn);
        storePanel.add(manageInventoryBtn);

        //Gestion des Employés
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

        JPanel backButtonPanel = new JPanel();
        JButton backButton = new JButton("Retour");
        backButton.addActionListener(e -> cardLayout.show(mainPanel, "Welcome"));
        backButtonPanel.add(backButton);

        add(storePanel);
        add(employeePanel);
        add(backButtonPanel);
    }
}
