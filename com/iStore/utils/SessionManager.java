package com.iStore.utils;

public class SessionManager {
    private String currentEmailAccoutConnected;
    private String emailToUpdate;
    private String storeName;

    public String getCurrentEmailAccoutConnected() {
        return currentEmailAccoutConnected;
    }

    public void setCurrentEmailAccoutConnected(String currentEmailAccoutConnected) {
        this.currentEmailAccoutConnected = currentEmailAccoutConnected;
    }

    public String getEmailToUpdate() {
        return emailToUpdate;
    }

    public void setEmailToUpdate(String email) {
        emailToUpdate = email;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String name) {
        storeName = name;
    }
}
