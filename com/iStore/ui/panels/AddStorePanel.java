package com.iStore.ui.panels;

import java.awt.*;

import javax.swing.*;

import com.iStore.dao.AdminDAO;

/**
 * Panneau permettant d'ajouter un magasin.
 * Cette interface permet a l'administrateur de saisir le nom du magasin pour le créer.
 */
public class AddStorePanel extends JPanel {

    /**
     * Constructeur du panneau d'ajout de magasin.
     *
     * @param cardLayout Le gestionnaire de disposition pour la navigation entre les panneaux.
     * @param mainPanel  Le panneau principal contenant tous les écrans de l'application.
     */
    public AddStorePanel(CardLayout cardLayout, JPanel mainPanel) {
        AdminDAO adminDAO = new AdminDAO();
        setLayout(new GridLayout(2, 2, 10, 10));

        add(new JLabel("Nom du magasin :"));

        JTextField nameField = new JTextField();
        add(nameField);

        JButton backButton = new JButton("Retour");
        backButton.addActionListener(e -> cardLayout.show(mainPanel, "AdminDashboard"));
        add(backButton);
        
        JButton createButton = new JButton("Créer le Magasin");
        createButton.addActionListener(e -> {
            String Name = nameField.getText();
            try {
                if (!adminDAO.verifyName(Name)) {
                    if (adminDAO.createStore(0, Name)) {
                        JOptionPane.showMessageDialog(this, "Magasin " + Name + " créé avec succès !.", "iStore", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(this, "Erreur lors de la création du magasin.", "Erreur", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Le nom est déja utilisé.", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
                
            } catch (Exception ex) {
                System.out.println("Erreur lors de la création du magasin : " + ex.getMessage());
            }
        });
        
        add(createButton);
    }
}
