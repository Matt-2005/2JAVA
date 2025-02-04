package com.iStore.ui.panels;

import javax.swing.*;
import java.awt.*;

/**
 * Panneau permettant à l'utilisateur de choisir s'il veut se connecter ou s'inscrire.
 * Cette interface affiche un bouton login et un bouton signup.
 * C'est l'interface par défaut qui est chargée dans GraphicInterface.java.
 */
public class WelcomePanel extends JPanel {
        /**
     * Constructeur du panneau permettant à l'utilisateur de choisir s'il veut se connecter ou s'inscrire..
     * @param cardLayout Le gestionnaire de disposition pour la navigation entre les panneaux.
     * @param mainPanel  Le panneau principal contenant tous les écrans de l'application.
     */
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
