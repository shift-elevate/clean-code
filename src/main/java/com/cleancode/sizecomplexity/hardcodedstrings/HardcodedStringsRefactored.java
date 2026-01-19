package com.cleancode.sizecomplexity.hardcodedstrings;

/**
 * Demonstrates the Replace Magic String with Named Constant refactoring solution.
 * This class shows how to replace literal strings with typesafe enums.
 *
 * Refactoring: Replace Magic String with Named Constant
 * Solution: Replace literal strings with typesafe enums that clearly communicate their purpose
 * Benefits: Compile-time safety, IDE support, centralized definitions, no typos
 */
public class HardcodedStringsRefactored {

    /**
     * REFACTORED VERSION - REPLACE MAGIC STRING WITH NAMED CONSTANT PATTERN
     *
     * This class demonstrates how to replace hardcoded strings with typesafe enums
     * that make the code self-documenting and easier to maintain.
     */

    private static final String ERROR_INVALID_ROLE = "Invalid role. Must be ADMIN, USER, or MODERATOR";

    /**
     * Create a new user with typesafe role validation.
     * Notice how enums replace scattered string literals.
     */
    public String createUser(String username, String email, String role) {
        // Typesafe validation using enum
        if (!UserRole.isValid(role)) {
            throw new IllegalArgumentException(ERROR_INVALID_ROLE);
        }

        // Typesafe status using enum
        String status = UserStatus.ACTIVE.getValue();

        String result = String.format("User %s created with role %s and status %s",
                username, role, status);

        sendEmail(email, "Welcome to Our Platform",
                "Welcome " + username + "! Your account has been created.");

        return result;
    }

    /**
     * Update user status with typesafe status validation.
     * Enum provides compile-time safety and eliminates typos.
     */
    public String updateUserStatus(String username, String newStatus) {
        // Typesafe validation - throws descriptive exception for invalid values
        UserStatus status = UserStatus.fromString(newStatus);

        String result = String.format("User %s status updated to %s", username, status.getValue());

        // Typesafe comparison using enum
        if (status == UserStatus.SUSPENDED) {
            sendEmail(getEmailForUser(username), "Account Suspended",
                    "Your account has been suspended.");
        }

        return result;
    }

    /**
     * Check if user has admin role - typesafe comparison.
     */
    public boolean isAdmin(String role) {
        // Typesafe check using enum
        return UserRole.ADMIN.getValue().equals(role);
    }

    /**
     * Check if user is active - typesafe status check.
     */
    public boolean isActive(String status) {
        // Typesafe check using enum
        return UserStatus.ACTIVE.getValue().equals(status);
    }

    // Helper methods
    private void sendEmail(String email, String subject, String message) {
        System.out.println("Email sent to " + email + ": " + subject);
    }

    private String getEmailForUser(String username) {
        return username + "@example.com";
    }
}
