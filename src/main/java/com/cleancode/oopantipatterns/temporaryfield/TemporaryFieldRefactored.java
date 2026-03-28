package com.cleancode.oopantipatterns.temporaryfield;

import com.cleancode.domain.Customer;
import com.cleancode.domain.OrderItem;

import java.util.List;

/**
 * Demonstrates the Extract Class refactoring for the Temporary Field code smell.
 * Temporary fields are organized into dedicated classes that better represent
 * their purpose and only exist when their processing stage begins.
 *
 * Refactoring: Extract Class
 * Solution: Move temporary fields into dedicated classes (DiscountInfo, PaymentInfo, ShipmentInfo)
 * Benefits: Cleaner class interface, predictable object state, better field organization
 */
public class TemporaryFieldRefactored {

    /**
     * REFACTORED VERSION - EXTRACT CLASS PATTERN
     *
     * The constructor no longer initializes fields to null or zero.
     * Each info object is created only when its processing stage begins,
     * making the object's state clear and predictable at every stage.
     */

    // Core order fields
    private final String orderId;
    private final Customer customer;
    private final List<OrderItem> items;
    private final double totalAmount;

    // Extracted classes replace the scattered temporary fields
    private DiscountInfo discountInfo;
    private PaymentInfo paymentInfo;
    private ShipmentInfo shipmentInfo;

    public TemporaryFieldRefactored(String orderId, Customer customer, List<OrderItem> items) {
        this.orderId = orderId;
        this.customer = customer;
        this.items = items;
        this.totalAmount = calculateBaseTotal();
    }

    public double applyDiscount(String discountCode) {
        this.discountInfo = new DiscountInfo(discountCode, totalAmount);
        return totalAmount - discountInfo.getAmount();
    }

    public boolean processPayment(String paymentMethod) {
        PaymentResult result = simulatePayment(paymentMethod);
        this.paymentInfo = new PaymentInfo(paymentMethod, result);
        return paymentInfo.isProcessed();
    }

    public void prepareShipment(String shippingAddress) {
        this.shipmentInfo = new ShipmentInfo(orderId, shippingAddress);
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
    public DiscountInfo getDiscountInfo() { return discountInfo; }
    public PaymentInfo getPaymentInfo() { return paymentInfo; }
    public ShipmentInfo getShipmentInfo() { return shipmentInfo; }

    // -------------------------------------------------------------------------

    /**
     * Extracted class holding discount-related data.
     * Encapsulates discount calculation logic alongside its fields.
     */
    public static class DiscountInfo {
        private final String code;
        private final double amount;

        public DiscountInfo(String code, double orderTotal) {
            this.code = code;
            this.amount = calculateDiscount(code, orderTotal);
        }

        private double calculateDiscount(String code, double orderTotal) {
            if (code == null) return 0.0;
            switch (code) {
                case "SAVE10": return orderTotal * 0.10;
                case "SAVE20": return orderTotal * 0.20;
                default: return 0.0;
            }
        }

        public String getCode() { return code; }
        public double getAmount() { return amount; }

        @Override
        public String toString() {
            return String.format("Discount{code='%s', amount=%.2f}", code, amount);
        }
    }

    // -------------------------------------------------------------------------

    /**
     * Extracted class holding payment-related data.
     * Groups payment method, status, and transaction ID in one cohesive object.
     */
    public static class PaymentInfo {
        private final String method;
        private final boolean processed;
        private final String transactionId;

        public PaymentInfo(String method, PaymentResult result) {
            this.method = method;
            this.processed = result.isSuccess();
            this.transactionId = result.getTransactionId();
        }

        public String getMethod() { return method; }
        public boolean isProcessed() { return processed; }
        public String getTransactionId() { return transactionId; }

        @Override
        public String toString() {
            return String.format("Payment{method='%s', processed=%b, txId='%s'}", method, processed, transactionId);
        }
    }

    // -------------------------------------------------------------------------

    /**
     * Extracted class holding shipment-related data.
     * Groups shipping address and tracking number in one cohesive object.
     */
    public static class ShipmentInfo {
        private final String address;
        private final String trackingNumber;

        public ShipmentInfo(String orderId, String address) {
            this.address = address;
            this.trackingNumber = "TRK" + orderId;
        }

        public String getAddress() { return address; }
        public String getTrackingNumber() { return trackingNumber; }

        @Override
        public String toString() {
            return String.format("Shipment{address='%s', tracking='%s'}", address, trackingNumber);
        }
    }
}
