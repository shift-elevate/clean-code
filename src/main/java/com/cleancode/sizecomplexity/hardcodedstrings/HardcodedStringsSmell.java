package com.cleancode.sizecomplexity.hardcodedstrings;

/**
 * Demonstrates the Hardcoded Strings code smell.
 * This class contains literal string values scattered throughout code without clear meaning.
 *
 * Code Smell: Hardcoded Strings
 * Problem: Literal strings appear in code without clear meaning, prone to typos
 * Impact: Reduced maintainability, difficult to configure, higher bug risk
 */
public class HardcodedStringsSmell {

    /**
     * HARDCODED STRINGS CODE SMELL EXAMPLE
     *
     * This class demonstrates the Hardcoded Strings code smell with literal values
     * like "ADMIN", "USER", "ACTIVE" scattered throughout the code.
     *
     * Problems:
     * - Literal strings without clear meaning
     * - Repeated strings prone to typos
     * - Business rules hidden in string comparisons
     * - No compile-time safety for invalid values
     */

    /**
     * Create a new user with hardcoded role validation.
     * Notice the scattered string literals and repeated validation logic.
     */
    public String createUser(String username, String email, String role) {
        // Hardcoded role validation - strings repeated and error-prone
        if (!"ADMIN".equals(role) && !"USER".equals(role) && !"MODERATOR".equals(role)) {
            throw new IllegalArgumentException("Invalid role. Must be ADMIN, USER, or MODERATOR");
        }

        // Hardcoded status string
        String status = "ACTIVE";

        String result = String.format("User %s created with role %s and status %s",
                username, role, status);

        // Hardcoded email subject and message
        sendEmail(email, "Welcome to Our Platform",
                "Welcome " + username + "! Your account has been created.");

        return result;
    }

    /**
     * Update user status with hardcoded status validation.
     * Same pattern of scattered string literals.
     */
    public String updateUserStatus(String username, String newStatus) {
        // Hardcoded status validation - easy to make typos
        if (!"ACTIVE".equals(newStatus) && !"INACTIVE".equals(newStatus) && !"SUSPENDED".equals(newStatus)) {
            throw new IllegalArgumentException("Invalid status. Must be ACTIVE, INACTIVE, or SUSPENDED");
        }

        String result = String.format("User %s status updated to %s", username, newStatus);

        // Hardcoded status comparison
        if ("SUSPENDED".equals(newStatus)) {
            sendEmail(getEmailForUser(username), "Account Suspended",
                    "Your account has been suspended.");
        }

        return result;
    }

    /**
     * Check if user has admin role - more hardcoded strings.
     */
    public boolean isAdmin(String role) {
        // Hardcoded role check - typo "ADMN" would fail silently
        return "ADMIN".equals(role);
    }

    /**
     * Check if user is active - hardcoded status check.
     */
    public boolean isActive(String status) {
        // Hardcoded status check
        return "ACTIVE".equals(status);
    }

    // Helper methods
    private void sendEmail(String email, String subject, String message) {
        System.out.println("Email sent to " + email + ": " + subject);
    }

    private String getEmailForUser(String username) {
        return username + "@example.com";
    }
}
