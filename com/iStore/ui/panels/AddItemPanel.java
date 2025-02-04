package com.iStore.ui.panels;

import java.awt.*;
import java.math.BigDecimal;

import javax.swing.*;

import com.iStore.dao.AdminDAO;
import com.iStore.model.Item;
import com.iStore.utils.SessionManager;


public class AddItemPanel extends JPanel{
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
            try{
    
                Item item = new Item(0, Name, Price, Stock);
                if (adminDAO.verifyItemName(Name)) {
                    JOptionPane.showMessageDialog(this, "Cet item existe déja.", "Erreur", JOptionPane.ERROR_MESSAGE);

                }
                if (adminDAO.createItem(item)) {
                    int itemID = adminDAO.getItemID(Name);
                    int storeID = adminDAO.getStoreID(sessionManager.getStoreName());
                    if (adminDAO.addInventory(itemID, storeID)) {
                        JOptionPane.showMessageDialog(this, "L'item " + item.getName() + " à été ajouté.", "iStore", JOptionPane.INFORMATION_MESSAGE);
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
