package com.cleancode.sizecomplexity.hardcodedstrings;

/**
 * Typesafe enum for user statuses.
 *
 * Benefits:
 * - Compile-time type safety
 * - No typos possible
 * - IDE auto-completion support
 * - Centralized status definitions
 */
public enum UserStatus {
    ACTIVE,
    INACTIVE,
    SUSPENDED;

    public String getValue() {
        return name();
    }

    public static boolean isValid(String status) {
        for (UserStatus s : values()) {
            if (s.name().equals(status)) {
                return true;
            }
        }
        return false;
    }

    public static UserStatus fromString(String status) {
        for (UserStatus s : values()) {
            if (s.name().equals(status)) {
                return s;
            }
        }
        throw new IllegalArgumentException("Invalid status: " + status);
    }
}
