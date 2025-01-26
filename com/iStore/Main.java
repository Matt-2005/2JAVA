package com.iStore;

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
                userLogIn(userDAO, scanner);
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
        String Password = scanner.nextLine();

        


        try{
            PasswordHash.HashResults hashResults = PasswordHash.passwordHash(Password);

            User user = new User(0, Email, Pseudo, hashResults.getHashedPassword(), hashResults.getSalt(), null);

            if (userDAO.verifyEmail(Email)) {
                System.out.println("This email is existing. Please try with an auther email or try to sign in.");
            }
            userDAO.createUser(user);
        } catch (Exception e) {
            System.out.println("Erreur lors de la création du compte : " + e.getMessage());
        }
    }

    private static void userLogIn(UserDAO userDAO, Scanner scanner) {
        boolean login = false;
        try {
            while (!login) {
                System.out.print("Email : ");
                String Email = scanner.nextLine();
                System.out.print("Mot de passe : ");
                String Password = scanner.nextLine();

                if (userDAO.verifyEmail(Email)) {
                    if (userDAO.verifyAccout(Email, Password)) {
                        System.out.println("Connection reussie !");
                        login = true;
                    } else {
                        System.out.println("Le mot de passe est incorrect.");
                    }
                } else {
                    System.out.println("L'email ou le mot de passe sont incorrect.");
                }
            }
            
        } catch (Exception e) {
            System.out.println("Erreur lors de la connection : " + e.getMessage());
        }
    }
}

