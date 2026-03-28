package com.cleancode.oopantipatterns.temporaryfield;

import com.cleancode.domain.Customer;
import com.cleancode.domain.OrderItem;

import java.util.List;

/**
 * Launcher class for Temporary Field code smell demonstration.
 * Demonstrates the Temporary Field code smell and its Extract Class refactoring.
 */
public class Launcher {

    public static void main(String[] args) {
        System.out.println("🐛 TEMPORARY FIELD CODE SMELL DEMONSTRATION");
        System.out.println("===========================================");

        // Demonstrate the code smell
        demonstrateCodeSmell();

        System.out.println("\n" + "=".repeat(50) + "\n");

        // Demonstrate the refactored solution
        demonstrateRefactoredSolution();
    }

    private static void demonstrateCodeSmell() {
        System.out.println("❌ TEMPORARY FIELD CODE SMELL");

        Customer customer = new Customer("C001", "johndoe", "johndoe@example.com", false);
        List<OrderItem> items = List.of(
            new OrderItem("P001", "Laptop", 999.99, 1),
            new OrderItem("P002", "Mouse", 29.99, 2)
        );

        TemporaryFieldSmell processor = new TemporaryFieldSmell("ORD-001", customer, items);
        System.out.println("Base total: $" + processor.getTotalAmount());

        // Temporary fields scattered across the class lifecycle
        double discountedTotal = processor.applyDiscount("SAVE10");
        System.out.println("After discount: $" + discountedTotal);
        System.out.println("Discount code: " + processor.getAppliedDiscountCode());
        System.out.println("Discount amount: $" + String.format("%.2f", processor.getDiscountAmount()));

        boolean paid = processor.processPayment("CREDIT_CARD");
        System.out.println("\nPayment processed: " + paid);
        System.out.println("Transaction ID: " + processor.getTransactionId());

        processor.prepareShipment("123 Main St, Springfield");
        System.out.println("\nShipping address: " + processor.getShippingAddress());
        System.out.println("Tracking number: " + processor.getTrackingNumber());
    }

    private static void demonstrateRefactoredSolution() {
        System.out.println("✅ TEMPORARY FIELD REFACTORED");

        Customer customer = new Customer("C002", "johndoe", "johndoe@example.com", false);
        List<OrderItem> items = List.of(
            new OrderItem("P001", "Laptop", 999.99, 1),
            new OrderItem("P002", "Mouse", 29.99, 2)
        );

        TemporaryFieldRefactored processor = new TemporaryFieldRefactored("ORD-002", customer, items);
        System.out.println("Base total: $" + processor.getTotalAmount());

        // Temporary state organized into focused objects
        double discountedTotal = processor.applyDiscount("SAVE10");
        System.out.println("After discount: $" + discountedTotal);
        System.out.println("Discount applied: " + processor.getDiscountInfo());

        boolean paid = processor.processPayment("CREDIT_CARD");
        System.out.println("\nPayment processed: " + paid);
        System.out.println("Payment details: " + processor.getPaymentInfo());

        processor.prepareShipment("123 Main St, Springfield");
        System.out.println("\nShipment prepared: " + processor.getShipmentInfo());
    }
}
