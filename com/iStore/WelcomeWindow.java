package com.iStore;

import java.awt.BorderLayout;

import javax.swing.*;
import javax.swing.border.Border;

public class WelcomeWindow {
    public void welcomeWindow() {
        JFrame frame = new JFrame("iStore - Welcome Page");

        JLabel label = new JLabel("Bienvenue dans iStore", JLabel.CENTER);

        JPanel panel = new JPanel();
        JButton loginBTN = new JButton("Login");
        JButton signUpBTN = new JButton("SignUp");
        panel.add(loginBTN);
        panel.add(signUpBTN);

        frame.add(label, BorderLayout.NORTH);
        frame.add(panel, BorderLayout.CENTER);

        frame.pack();
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}