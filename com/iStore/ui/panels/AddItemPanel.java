package com.iStore.ui.panels;

import java.awt.*;
import java.math.BigDecimal;

import javax.swing.*;

import com.iStore.dao.AdminDAO;
import com.iStore.model.Item;
import com.iStore.utils.SessionManager;

/**
 * Panel permettant d'ajouter un nouvel item à l'inventaire.
 * Cette interface graphique permet à l'utilisateur de saisir le nom, le prix et le stock de l'item.
 */
public class AddItemPanel extends JPanel {

    /**
     * Constructeur du panneau d'ajout d'Items.
     * 
     * @param cardLayout Le gestionnaire de disposition pour la navigation entre les panneaux.
     * @param mainPanel  Le panneau principal contenant tous les écrans de l'application.
     * @param sessionManager Le gestionnaire de session pour récupérer des informations de session.
     */
    public AddItemPanel(CardLayout cardLayout, JPanel mainPanel, SessionManager sessionManager) {
        AdminDAO adminDAO = new AdminDAO();
        setLayout(new GridLayout(4, 2, 10, 10));

        add(new JLabel("Name :"));
        JTextField nameField = new JTextField();
        add(nameField);

        add(new JLabel("Prix :"));
        JTextField priceField = new JTextField();
        add(priceField);

        add(new JLabel("Stock :"));
        JTextField stockField = new JTextField();
        add(stockField);

        JButton backButton = new JButton("Retour");
        backButton.addActionListener(e -> cardLayout.show(mainPanel, "DisplayItemForEmployee"));

        JButton addButton = new JButton("Ajouter");

        addButton.addActionListener(e -> {
            String Name = nameField.getText();
            BigDecimal Price = new BigDecimal(priceField.getText());
            int Stock = Integer.parseInt(stockField.getText());
            try {
                Item item = new Item(0, Name, Price, Stock);
                if (adminDAO.verifyItemName(Name)) {
                    JOptionPane.showMessageDialog(this, "Cet item existe déjà.", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
                if (adminDAO.createItem(item)) {
                    int itemID = adminDAO.getItemID(Name);
                    int storeID = adminDAO.getStoreID(sessionManager.getStoreName());
                    if (adminDAO.addInventory(itemID, storeID)) {
                        JOptionPane.showMessageDialog(this, "L'item " + item.getName() + " a été ajouté.", "iStore", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            } catch (Exception ex) {
                System.out.println("Erreur lors de la création de l'item : " + ex.getMessage());
            }
        });

        add(backButton);
        add(addButton);
    }
}
