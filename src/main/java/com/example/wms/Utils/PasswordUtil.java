package com.example.wms.Utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * Utility class for password encryption and verification using BCrypt
 */
@Component
public class PasswordUtil {

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    /**
     * Encrypts a raw password using BCrypt
     * @param rawPassword The plain text password to encrypt
     * @return The encrypted password hash
     */
    public static String encryptPassword(String rawPassword) {
        if (rawPassword == null || rawPassword.isEmpty()) {
            throw new IllegalArgumentException("Password cannot be null or empty");
        }
        return passwordEncoder.encode(rawPassword);
    }

    /**
     * Verifies if a raw password matches an encrypted password
     * @param rawPassword The plain text password to verify
     * @param encryptedPassword The encrypted password hash to compare against
     * @return true if passwords match, false otherwise
     */
    public static boolean verifyPassword(String rawPassword, String encryptedPassword) {
        if (rawPassword == null || encryptedPassword == null) {
            return false;
        }
        return passwordEncoder.matches(rawPassword, encryptedPassword);
    }

    /**
     * Checks if a password is already encrypted (BCrypt format)
     * @param password The password to check
     * @return true if the password appears to be BCrypt encrypted
     */
    public static boolean isEncrypted(String password) {
        if (password == null) {
            return false;
        }
        // BCrypt hashes start with $2a$, $2b$, or $2y$ and are 60 characters long
        return password.matches("^\\$2[ayb]\\$.{56}$");
    }
}
