package com.uasz.enseign.model;

public class PasswordValidator {

    public static boolean isPasswordValid(String password) {
        // Vérifie si le mot de passe respecte les critères de complexité
        return password.length() >= 8 && password.length() <= 10 &&
                containsDigit(password) && containsUpperCase(password);
    }

    private static boolean containsDigit(String password) {
        // Vérifie si le mot de passe contient au moins un chiffre
        return password.matches(".*\\d.*");
    }

    private static boolean containsUpperCase(String password) {
        // Vérifie si le mot de passe contient au moins une majuscule
        return password.matches(".*[A-Z].*");
    }
}
