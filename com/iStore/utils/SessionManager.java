package com.iStore.utils;

/**
 * Classe utilitaire pour la gestion de session dans l'application iStore.
 * Elle stocke des informations temporaires sur l'utilisateur connecté, l'e-mail à mettre à jour,
 * et le nom du magasin associé à la session en cours.
 */
public class SessionManager {
    private String currentEmailAccoutConnected;
    private String emailToUpdate;
    private String storeName;

    /**
     * Obtient l'adresse e-mail du compte actuellement connecté.
     *
     * @return L'adresse e-mail de l'utilisateur connecté.
     */
    public String getCurrentEmailAccoutConnected() {
        return currentEmailAccoutConnected;
    }

    /**
     * Définit l'adresse e-mail du compte actuellement connecté.
     *
     * @param currentEmailAccoutConnected L'adresse e-mail de l'utilisateur à connecter.
     */
    public void setCurrentEmailAccoutConnected(String currentEmailAccoutConnected) {
        this.currentEmailAccoutConnected = currentEmailAccoutConnected;
    }

    /**
     * Obtient l'adresse e-mail de l'utilisateur à mettre à jour.
     *
     * @return L'adresse e-mail de l'utilisateur à mettre à jour.
     */
    public String getEmailToUpdate() {
        return emailToUpdate;
    }

    /**
     * Définit l'adresse e-mail de l'utilisateur à mettre à jour.
     *
     * @param email L'adresse e-mail de l'utilisateur à mettre à jour.
     */
    public void setEmailToUpdate(String email) {
        emailToUpdate = email;
    }

    /**
     * Obtient le nom du magasin associé à la session actuelle.
     *
     * @return Le nom du magasin.
     */
    public String getStoreName() {
        return storeName;
    }

    /**
     * Définit le nom du magasin associé à la session actuelle.
     *
     * @param name Le nom du magasin.
     */
    public void setStoreName(String name) {
        storeName = name;
    }
}
