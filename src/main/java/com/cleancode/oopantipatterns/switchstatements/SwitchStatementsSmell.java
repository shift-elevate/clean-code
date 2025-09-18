package com.cleancode.oopantipatterns.switchstatements;

/**
 * Demonstrates the Switch Statements code smell.
 * This class contains switch statements that handle different object types or behaviors.
 * 
 * Code Smell: Switch Statements
 * Problem: Switch statements handling object types violate the Open Closed Principle
 * Impact: Difficult to extend, code duplication, violates Open Closed Principle
 */
public class SwitchStatementsSmell {
    
    /**
     * SWITCH STATEMENTS CODE SMELL EXAMPLE
     * 
     * This class violates the Open Closed Principle by using switch statements
     * to handle different customer types. Adding new customer types requires
     * modifying existing code.
     * 
     * Problems:
     * - Multiple switch statements handling the same customer types
     * - Violates Open Closed Principle
     * - Code duplication across methods
     * - Difficult to extend with new customer types
     */
    public double calculateDiscount(String customerType, double amount) {
        switch (customerType) {
            case "REGULAR":
                return amount * 0.05; // 5% discount
            case "PREMIUM":
                return amount * 0.10; // 10% discount
            case "VIP":
                return amount * 0.15; // 15% discount
            default:
                return 0.0;
        }
    }
    
    public String getWelcomeMessage(String customerType) {
        switch (customerType) {
            case "REGULAR":
                return "Welcome! Enjoy your shopping.";
            case "PREMIUM":
                return "Welcome back! You have premium benefits.";
            case "VIP":
                return "Welcome VIP! Exclusive offers await you.";
            default:
                return "Welcome!";
        }
    }
    
    /**
     * Demonstrates the problem of repeated switch logic
     * across multiple methods with the same customer types.
     */
    public void processCustomer(String customerType, double amount) {
        double discount = calculateDiscount(customerType, amount);
        String message = getWelcomeMessage(customerType);
        
        System.out.println("Customer Type: " + customerType);
        System.out.println("Amount: $" + amount);
        System.out.println("Discount: $" + discount);
        System.out.println("Message: " + message);
        System.out.println("Final Amount: $" + (amount - discount));
    }
}
