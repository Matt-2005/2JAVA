package com.iStore;

public class User {
    private int Id;
    private String Email;
    private String Pseudo;
    private String hashedPassword;
    private String Salt;
    private String Role;

    public User(int Id, String Email, String Pseudo, String hashedPassword, String Salt, String Role) {
        this.Id = Id;
        this.Email = Email;
        this.Pseudo = Pseudo;
        this.hashedPassword = hashedPassword;
        this.Salt = Salt;
        this.Role = Role;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getPseudo() {
        return Pseudo;
    }

    public void setPseudo(String Pseudo) {
        this.Pseudo = Pseudo;
    }

    public String getPasswordHash() {
        return hashedPassword;
    }

    public void setPasswordHash(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    public String getSalt() {
        return Salt;
    }

    public void setSalt(String Salt) {
        this.Salt = Salt;
    }

    public String getRole() {
        return Role;
    }

    public void setRole(String Role) {
        this.Role = Role;
    }
}
