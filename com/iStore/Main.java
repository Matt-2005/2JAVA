package com.iStore;

import com.iStore.config.CreateTables;
import com.iStore.ui.GraphicInterface;

// import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        CreateTables createTables = new CreateTables();

        try{
            createTables.createTable();
        } catch (Exception e) {
            System.out.println("Erreur lors de la création des tables : " + e.getMessage());
        }

        GraphicInterface graphicInterface = new GraphicInterface();
        graphicInterface.myInterface();
        System.out.println("Interface graphique lancée !");
    }
}

