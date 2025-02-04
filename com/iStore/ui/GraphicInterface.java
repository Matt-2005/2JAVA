package com.iStore.ui;

import javax.swing.*;

import com.iStore.ui.panels.AddItemPanel;
import com.iStore.ui.panels.AddStorePanel;
import com.iStore.ui.panels.AddUserPanel;
import com.iStore.ui.panels.AdminPanel;
import com.iStore.ui.panels.DeleteStorePanel;
import com.iStore.ui.panels.DeleteUserPanel;
import com.iStore.ui.panels.DisplayStorePanel;
import com.iStore.ui.panels.DisplayUserPanel;
import com.iStore.ui.panels.EmployeePanel;
import com.iStore.ui.panels.LoginPanel;
import com.iStore.ui.panels.ManageWhatInventoryPanel;
import com.iStore.ui.panels.SignUpPanel;
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
        mainPanel.add(new LoginPanel(cardLayout, mainPanel, sessionManager), "Login");
        mainPanel.add(new SignUpPanel(cardLayout, mainPanel), "SignUp");
        mainPanel.add(new AdminPanel(cardLayout, mainPanel), "AdminDashboard");
        mainPanel.add(new EmployeePanel(cardLayout, mainPanel, sessionManager), "EmployeePanel");
        mainPanel.add(new UpdateUserForEmployeePanel(cardLayout, mainPanel, sessionManager), "UpdateUserForEmployee");
        mainPanel.add(new DeleteUserPanel(cardLayout, mainPanel), "DeleteUser");
        mainPanel.add(new AddItemPanel(cardLayout, mainPanel, sessionManager), "AddItem");
        mainPanel.add(new AddStorePanel(cardLayout, mainPanel), "StoreCreation");
        mainPanel.add(new AddUserPanel(cardLayout, mainPanel), "AddUser");
        mainPanel.add(new DeleteStorePanel(cardLayout, mainPanel), "DeleteStore");
        mainPanel.add(new DisplayStorePanel(cardLayout, mainPanel), "DisplayStore");
        mainPanel.add(new DisplayUserPanel(cardLayout, mainPanel), "DisplayUser");
        mainPanel.add(new ManageWhatInventoryPanel(cardLayout, mainPanel, sessionManager), "WhatInventoryToManage");
        mainPanel.add(new UpdateUserForAdminPanel(cardLayout, mainPanel, sessionManager), "UpdateUserForAdmin");
        mainPanel.add(new UpdateWhatUserPanel(cardLayout, mainPanel, sessionManager), "WhatUserToUpdate");
        

        frame.add(mainPanel);

        cardLayout.show(mainPanel, "Welcome");

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}


