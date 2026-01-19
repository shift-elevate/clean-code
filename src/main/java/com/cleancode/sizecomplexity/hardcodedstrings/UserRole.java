package com.cleancode.sizecomplexity.hardcodedstrings;

/**
 * Typesafe enum for user roles.
 *
 * Benefits:
 * - Compile-time type safety
 * - No typos possible
 * - IDE auto-completion support
 * - Centralized role definitions
 */
public enum UserRole {
    ADMIN,
    USER,
    MODERATOR;

    public String getValue() {
        return name();
    }

    public static boolean isValid(String role) {
        for (UserRole r : values()) {
            if (r.name().equals(role)) {
                return true;
            }
        }
        return false;
    }

    public static UserRole fromString(String role) {
        for (UserRole r : values()) {
            if (r.name().equals(role)) {
                return r;
            }
        }
        throw new IllegalArgumentException("Invalid role: " + role);
    }
}
