package com.cleancode.oopantipatterns.temporaryfield;

import com.cleancode.domain.Customer;
import com.cleancode.domain.OrderItem;

import java.util.List;

/**
 * Demonstrates the Temporary Field code smell.
 * Instance variables are only used in certain circumstances,
 * making the class interface confusing and the object's state unclear.
 *
 * Code Smell: Temporary Field
 * Problem: Fields initialized to null or zero are only populated during specific operations
 * Impact: Confusing class interface, unclear object state, higher bug risk
 */
public class TemporaryFieldSmell {

    /**
     * TEMPORARY FIELD CODE SMELL EXAMPLE
     *
     * OrderProcessor has three groups of temporary fields that are only
     * relevant during specific processing stages, yet they clutter the
     * class interface throughout the object's lifetime.
     *
     * Problems:
     * - Fields only used in certain methods (appliedDiscountCode, discountAmount, etc.)
     * - Fields initialized to null or zero in the constructor
     * - Confusing class interface with many unrelated fields
     * - Unclear object state - which fields are valid at any given moment?
     */

    // Core order fields
    private String orderId;
    private Customer customer;
    private List<OrderItem> items;
    private double totalAmount;

    // Temporary fields - only used during discount calculation
    private String appliedDiscountCode;
    private double discountAmount;

    // Temporary fields - only used during payment
    private boolean paymentProcessed;
    private String transactionId;

    // Temporary fields - only used during shipment
    private String shippingAddress;
    private String trackingNumber;

    public TemporaryFieldSmell(String orderId, Customer customer, List<OrderItem> items) {
        this.orderId = orderId;
        this.customer = customer;
        this.items = items;
        this.totalAmount = calculateBaseTotal();

        // Initialize temporary fields to default values
        this.appliedDiscountCode = null;
        this.discountAmount = 0.0;
        this.paymentProcessed = false;
        this.transactionId = null;
        this.shippingAddress = null;
        this.trackingNumber = null;
    }

    public double applyDiscount(String discountCode) {
        this.appliedDiscountCode = discountCode;
        this.discountAmount = calculateDiscount(discountCode);
        return this.totalAmount - this.discountAmount;
    }

    public boolean processPayment(String paymentMethod) {
        PaymentResult result = simulatePayment(paymentMethod);
        this.paymentProcessed = result.isSuccess();
        this.transactionId = result.getTransactionId();
        return this.paymentProcessed;
    }

    public void prepareShipment(String shippingAddress) {
        this.shippingAddress = shippingAddress;
        this.trackingNumber = "TRK" + orderId;
    }

    private double calculateDiscount(String discountCode) {
        if (discountCode == null) return 0.0;
        switch (discountCode) {
            case "SAVE10": return totalAmount * 0.10;
            case "SAVE20": return totalAmount * 0.20;
            default: return 0.0;
        }
    }

    private double calculateBaseTotal() {
        return items.stream()
                .mapToDouble(item -> item.getPrice() * item.getQuantity())
                .sum();
    }

    private PaymentResult simulatePayment(String paymentMethod) {
        // Simulate payment gateway response
        return new PaymentResult(true, "TXN-" + orderId);
    }

    public String getOrderId() { return orderId; }
    public double getTotalAmount() { return totalAmount; }
    public String getAppliedDiscountCode() { return appliedDiscountCode; }
    public double getDiscountAmount() { return discountAmount; }
    public boolean isPaymentProcessed() { return paymentProcessed; }
    public String getTransactionId() { return transactionId; }
    public String getShippingAddress() { return shippingAddress; }
    public String getTrackingNumber() { return trackingNumber; }
}
