package com.iStore.ui.panels;

import java.awt.*;
import java.math.BigDecimal;

import javax.swing.*;

import com.iStore.dao.AdminDAO;
import com.iStore.model.Item;

public class UpdateItemPanel extends JPanel{
    public UpdateItemPanel(CardLayout cardLayout, JPanel mainPanel) {
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

        JButton updateButton = new JButton("Mettre à jour");

        updateButton.addActionListener(e -> {
            String Name = nameField.getText();
            BigDecimal Price = new BigDecimal(priceField.getText());
            int Stock = Integer.parseInt(stockField.getText());
            try{
                if (adminDAO.verifyItemName(Name)) {
                    Item item = new Item(0, Name, Price, Stock);
                    if (adminDAO.updateItem(Name, Name, Price, Stock)) {
                        JOptionPane.showMessageDialog(this, "L'item " + item.getName() + " à été mis à jour.", "iStore", JOptionPane.INFORMATION_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Item inexistant", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
                
                
            } catch (Exception ex) {
                System.out.println("Erreur lors de la mise a jour de l'item : " + ex.getMessage());
            }
        });

        add(backButton);
        add(updateButton);
    }
}
