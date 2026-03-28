package com.cleancode.oopantipatterns.temporaryfield;

/**
 * Represents the result of a payment processing operation.
 * Used to demonstrate the Temporary Field code smell and its refactoring.
 */
public class PaymentResult {
    private final boolean success;
    private final String transactionId;

    public PaymentResult(boolean success, String transactionId) {
        this.success = success;
        this.transactionId = transactionId;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getTransactionId() {
        return transactionId;
    }

    @Override
    public String toString() {
        return String.format("PaymentResult{success=%b, transactionId='%s'}", success, transactionId);
    }
}
