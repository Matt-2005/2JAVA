package com.iStore;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        CreateTables createTables = new CreateTables();
        Scanner scanner = new Scanner(System.in);
        UserDAO userDAO = new UserDAO();

        try{
            createTables.createTable();
        } catch (Exception e) {
            System.out.println("Erreur lors de l'execution : " + e.getMessage());
        }

        displayMenu();
        int choise = scanner.nextInt();
        scanner.nextLine();

        switch (choise) {
            case 1:
                userSignIn(userDAO, scanner);
                break;
            case 2:
                System.out.println("Connection...");
                break;
        
            default:
            System.out.println("Option invalide !");
        }
    }


    private static void displayMenu() {
        System.out.println("Que voulez vous faire? ");
        System.out.println("1 - Créer un compte");
        System.out.println("2 - Se connecter");
        System.out.print("> ");
    }

    private static void userSignIn(UserDAO userDAO, Scanner scanner) {
        System.out.print("Email : ");
        String Email = scanner.nextLine();
        System.out.print("Pseudo : ");
        String Pseudo = scanner.nextLine();
        System.out.print("Mot de passe : ");
        String PasswordHash = scanner.nextLine();

        User user = new User(0, Email, Pseudo, PasswordHash, null);

        try{
            userDAO.createUser(user);
        } catch (SQLException e) {
            System.out.println("Erreur lors de la création du compte : " + e.getMessage());
        }
    }
}

