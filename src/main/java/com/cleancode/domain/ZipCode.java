package com.cleancode.domain;

/**
 * Value object representing a US zip code.
 * Encapsulates validation logic and provides type safety.
 */
public class ZipCode {
    private final String value;

    public ZipCode(String zipCode) {
        if (!isValid(zipCode)) {
            throw new IllegalArgumentException("Invalid zip code format: " + zipCode);
        }
        this.value = zipCode;
    }

    private boolean isValid(String zipCode) {
        return zipCode != null && zipCode.matches("\\d{5}(-\\d{4})?");
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }
}
