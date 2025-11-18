package com.cleancode.domain;

/**
 * Value object representing money.
 * Encapsulates validation logic and provides type safety.
 */
public class Money {
    private final double amount;

    public Money(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount cannot be negative");
        }
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }

    public String getFormattedAmount() {
        return String.format("$%.2f", amount);
    }

    @Override
    public String toString() {
        return getFormattedAmount();
    }
}
