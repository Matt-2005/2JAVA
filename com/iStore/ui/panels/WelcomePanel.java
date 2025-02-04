package com.iStore.ui.panels;

import javax.swing.*;
import java.awt.*;

public class WelcomePanel extends JPanel {
        public WelcomePanel(CardLayout cardLayout, JPanel mainPanel) {
                setLayout(new BorderLayout());
        
                JLabel welcomeLabel = new JLabel("Bienvenue dans iStore", JLabel.CENTER);
                add(welcomeLabel, BorderLayout.NORTH);
        
                JPanel buttonPanel = new JPanel();
                JButton loginButton = new JButton("Login");
                JButton signUpButton = new JButton("SignUp");
        
                loginButton.addActionListener(e -> cardLayout.show(mainPanel, "Login"));
                signUpButton.addActionListener(e -> cardLayout.show(mainPanel, "SignUp"));
        
                buttonPanel.add(loginButton);
                buttonPanel.add(signUpButton);
                add(buttonPanel, BorderLayout.CENTER);
        }
}
