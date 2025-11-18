package com.cleancode.domain;

/**
 * Value object representing a phone number.
 * Encapsulates validation logic and provides type safety.
 */
public class PhoneNumber {
    private final String value;

    public PhoneNumber(String phoneNumber) {
        if (!isValid(phoneNumber)) {
            throw new IllegalArgumentException("Invalid phone number format: " + phoneNumber);
        }
        this.value = phoneNumber;
    }

    private boolean isValid(String phoneNumber) {
        // E.164 format: + followed by 1-15 digits
        return phoneNumber != null && phoneNumber.matches("\\+[1-9]\\d{1,14}");
    }

    public String getValue() {
        return value;
    }

    public String getCountryCode() {
        if (value.startsWith("+") && value.length() >= 3) {
            return value.substring(1, 3);
        }
        return "US"; // Default assumption
    }

    @Override
    public String toString() {
        return value;
    }
}
