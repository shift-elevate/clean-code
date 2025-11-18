package com.cleancode.domain;

/**
 * Enum representing user status.
 * Provides type safety and encapsulates status-related behavior.
 */
public enum UserStatus {
    ACTIVE, INACTIVE, SUSPENDED, PENDING;

    public boolean isActive() {
        return this == ACTIVE;
    }
}
