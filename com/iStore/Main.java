package com.iStore;

import com.iStore.config.CreateTables;
import com.iStore.ui.GraphicInterface;

/**
 * Classe principale de l'application iStore.
 * Ce programme initialise la base de données, crée les tables nécessaires 
 * et lance l'interface graphique de l'application.
 */
public class Main {

    /**
     * Point d'entrée principal de l'application iStore.
     * Cette méthode initialise les tables de la base de données si elles n'existent pas
     * et lance l'interface graphique de l'application.
     *
     * @param args Les arguments de la ligne de commande (non utilisés ici).
     */
    public static void main(String[] args) {
        CreateTables createTables = new CreateTables();

        try {
            createTables.createTable();
        } catch (Exception e) {
            System.out.println("Erreur lors de la création des tables : " + e.getMessage());
        }

        GraphicInterface graphicInterface = new GraphicInterface();
        graphicInterface.myInterface();
        System.out.println("Interface graphique lancée !");
    }
}
