package com.iStore.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * Classe utilitaire pour le hachage des mots de passe dans l'application iStore.
 * Fournit des méthodes pour générer des hachages sécurisés à l'aide de l'algorithme SHA-512 et un sel aléatoire.
 */
public class PasswordHash {

    /**
     * Classe interne pour encapsuler les résultats du hachage de mot de passe.
     * Contient le sel généré et le mot de passe haché.
     */
    public static class HashResults {
        private String Salt;
        private String hashedPassword;

        /**
         * Constructeur pour initialiser les résultats du hachage.
         *
         * @param Salt           Le sel utilisé pour le hachage, encodé en Base64.
         * @param hashedPassword Le mot de passe haché, encodé en Base64.
         */
        public HashResults(String Salt, String hashedPassword) {
            this.Salt = Salt;
            this.hashedPassword = hashedPassword;
        }

        /**
         * Obtient le sel utilisé pour le hachage.
         *
         * @return Le sel encodé en Base64.
         */
        public String getSalt() {
            return Salt;
        }

        /**
         * Obtient le mot de passe haché.
         *
         * @return Le mot de passe haché, encodé en Base64.
         */
        public String getHashedPassword() {
            return hashedPassword;
        }
    }

    /**
     * Hache un mot de passe en utilisant l'algorithme SHA-512 et un sel aléatoire.
     *
     * @param Password Le mot de passe en texte clair à hacher.
     * @return Un objet {@link HashResults} contenant le sel et le mot de passe haché, tous deux encodés en Base64.
     * @throws Exception si une erreur survient lors de l'utilisation de l'algorithme de hachage.
     */
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
