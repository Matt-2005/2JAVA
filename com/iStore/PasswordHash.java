package com.iStore;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Base64;

public class PasswordHash {
    public static String passwordHash(String Password) throws Exception {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);

        MessageDigest md = MessageDigest.getInstance("SHA-512");
        md.update(salt);

        byte[] PasswordHash = md.digest(Password.getBytes(StandardCharsets.UTF_8));

        return Base64.getEncoder().encodeToString(PasswordHash);
    }
}
