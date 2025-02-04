package com.iStore.model;

/**
 * Classe représentant un utilisateur dans l'application iStore.
 * Un utilisateur possède des informations d'identification, des rôles et est associé à un magasin spécifique.
 */
public class User {
    private int Id;
    private String Email;
    private String Pseudo;
    private String hashedPassword;
    private String Salt;
    private String Role;
    private int storeID;

    /**
     * Constructeur pour créer un nouvel utilisateur.
     *
     * @param Id             L'identifiant unique de l'utilisateur.
     * @param Email          L'adresse e-mail de l'utilisateur.
     * @param Pseudo         Le pseudonyme de l'utilisateur.
     * @param hashedPassword Le mot de passe haché de l'utilisateur.
     * @param Salt           Le sel utilisé pour le hachage du mot de passe.
     * @param Role           Le rôle de l'utilisateur (par exemple : "Admin", "Employé").
     * @param storeID        L'identifiant du magasin auquel l'utilisateur est associé.
     */
    public User(int Id, String Email, String Pseudo, String hashedPassword, String Salt, String Role, int storeID) {
        this.Id = Id;
        this.Email = Email;
        this.Pseudo = Pseudo;
        this.hashedPassword = hashedPassword;
        this.Salt = Salt;
        this.Role = Role;
        this.storeID = storeID;
    }

    /**
     * Obtient l'identifiant de l'utilisateur.
     *
     * @return L'identifiant de l'utilisateur.
     */
    public int getId() {
        return Id;
    }

    /**
     * Définit l'identifiant de l'utilisateur.
     *
     * @param Id Le nouvel identifiant de l'utilisateur.
     */
    public void setId(int Id) {
        this.Id = Id;
    }

    /**
     * Obtient l'adresse e-mail de l'utilisateur.
     *
     * @return L'adresse e-mail de l'utilisateur.
     */
    public String getEmail() {
        return Email;
    }

    /**
     * Définit l'adresse e-mail de l'utilisateur.
     *
     * @param Email La nouvelle adresse e-mail de l'utilisateur.
     */
    public void setEmail(String Email) {
        this.Email = Email;
    }

    /**
     * Obtient le pseudonyme de l'utilisateur.
     *
     * @return Le pseudonyme de l'utilisateur.
     */
    public String getPseudo() {
        return Pseudo;
    }

    /**
     * Définit le pseudonyme de l'utilisateur.
     *
     * @param Pseudo Le nouveau pseudonyme de l'utilisateur.
     */
    public void setPseudo(String Pseudo) {
        this.Pseudo = Pseudo;
    }

    /**
     * Obtient le mot de passe haché de l'utilisateur.
     *
     * @return Le mot de passe haché de l'utilisateur.
     */
    public String getPasswordHash() {
        return hashedPassword;
    }

    /**
     * Définit le mot de passe haché de l'utilisateur.
     *
     * @param hashedPassword Le nouveau mot de passe haché de l'utilisateur.
     */
    public void setPasswordHash(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    /**
     * Obtient le sel utilisé pour le hachage du mot de passe.
     *
     * @return Le sel de l'utilisateur.
     */
    public String getSalt() {
        return Salt;
    }

    /**
     * Définit le sel utilisé pour le hachage du mot de passe.
     *
     * @param Salt Le nouveau sel de l'utilisateur.
     */
    public void setSalt(String Salt) {
        this.Salt = Salt;
    }

    /**
     * Obtient le rôle de l'utilisateur (par exemple : "Admin", "Employé").
     *
     * @return Le rôle de l'utilisateur.
     */
    public String getRole() {
        return Role;
    }

    /**
     * Définit le rôle de l'utilisateur.
     *
     * @param Role Le nouveau rôle de l'utilisateur.
     */
    public void setRole(String Role) {
        this.Role = Role;
    }

    /**
     * Obtient l'identifiant du magasin associé à l'utilisateur.
     *
     * @return L'identifiant du magasin.
     */
    public int getStoreID() {
        return storeID;
    }

    /**
     * Définit l'identifiant du magasin associé à l'utilisateur.
     *
     * @param storeID Le nouvel identifiant du magasin.
     */
    public void setStoreID(int storeID) {
        this.storeID = storeID;
    }
}
