package com.iStore;

// import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        CreateTables createTables = new CreateTables();

        try{
            createTables.createTable();
        } catch (Exception e) {
            System.out.println("Erreur lors de la création des tables : " + e.getMessage());
        }

        GraphicInterface welcomeWindow = new GraphicInterface();
        welcomeWindow.myInterface();
        System.out.println("Interface graphique lancée !");
    }
}

