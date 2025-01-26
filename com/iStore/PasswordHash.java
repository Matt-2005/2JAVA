package com.iStore;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Base64;

public class PasswordHash {

    public static class HashResults {
        private String Salt;
        private String hashedPassword;
    
        public HashResults(String Salt, String hashedPassword) {
            this.Salt = Salt;
            this.hashedPassword = hashedPassword;
        }
    
        public String getSalt() {
            return Salt;
        }
    
        public String getHashedPassword() {
            return hashedPassword;
        }
    }


    public static HashResults passwordHash(String Password) throws Exception {

        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        String saltBase64 = Base64.getEncoder().encodeToString(salt);

        MessageDigest md = MessageDigest.getInstance("SHA-512");        
        md.update(salt);

        byte[] PasswordHash = md.digest(Password.getBytes(StandardCharsets.UTF_8));

        String hashedPassword = Base64.getEncoder().encodeToString(PasswordHash);
        

        return new HashResults(saltBase64, hashedPassword);
    }




    
}
