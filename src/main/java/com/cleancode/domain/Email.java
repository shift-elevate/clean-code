package com.cleancode.domain;

/**
 * Value object representing an email address.
 * Encapsulates validation logic and provides type safety.
 */
public class Email {
    private final String value;

    public Email(String email) {
        if (!isValid(email)) {
            throw new IllegalArgumentException("Invalid email format: " + email);
        }
        this.value = email;
    }

    private boolean isValid(String email) {
        return email != null && email.contains("@") && email.contains(".");
    }

    public String getValue() {
        return value;
    }

    public String getDomain() {
        return value.substring(value.indexOf("@") + 1);
    }

    @Override
    public String toString() {
        return value;
    }
}
