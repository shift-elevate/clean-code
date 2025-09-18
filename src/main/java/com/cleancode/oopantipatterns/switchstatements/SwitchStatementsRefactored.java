package com.cleancode.oopantipatterns.switchstatements;

/**
 * Demonstrates the Replace Conditional with Polymorphism refactoring solution.
 * This class shows how to eliminate switch statements using inheritance and polymorphism.
 * 
 * Refactoring: Replace Conditional with Polymorphism
 * Solution: Create hierarchy of classes to handle different behaviors
 * Benefits: Follows Open Closed Principle, eliminates code duplication, improves maintainability
 */
public class SwitchStatementsRefactored {
    
    /**
     * REFACTORED VERSION - REPLACE CONDITIONAL WITH POLYMORPHISM PATTERN
     * 
     * This class now uses polymorphism to handle different customer types,
     * making it extensible without modifying existing code.
     */
    
    // Abstract base class for customer behavior
    public abstract static class Customer {
        protected String name;
        
        public Customer(String name) {
            this.name = name;
        }
        
        public abstract double calculateDiscount(double amount);
        public abstract String getWelcomeMessage();
    }
    
    // Concrete implementation for regular customers
    public static class RegularCustomer extends Customer {
        public RegularCustomer(String name) {
            super(name);
        }
        
        @Override
        public double calculateDiscount(double amount) {
            return amount * 0.05; // 5% discount
        }
        
        @Override
        public String getWelcomeMessage() {
            return "Welcome! Enjoy your shopping.";
        }
    }
    
    // Concrete implementation for premium customers
    public static class PremiumCustomer extends Customer {
        public PremiumCustomer(String name) {
            super(name);
        }
        
        @Override
        public double calculateDiscount(double amount) {
            return amount * 0.10; // 10% discount
        }
        
        @Override
        public String getWelcomeMessage() {
            return "Welcome back! You have premium benefits.";
        }
    }
    
    // Concrete implementation for VIP customers
    public static class VipCustomer extends Customer {
        public VipCustomer(String name) {
            super(name);
        }
        
        @Override
        public double calculateDiscount(double amount) {
            return amount * 0.15; // 15% discount
        }
        
        @Override
        public String getWelcomeMessage() {
            return "Welcome VIP! Exclusive offers await you.";
        }
    }
    
    /**
     * Refactored payment processor that uses polymorphism
     * instead of switch statements.
     */
    public double calculateDiscount(Customer customer, double amount) {
        return customer.calculateDiscount(amount);
    }
    
    public String getWelcomeMessage(Customer customer) {
        return customer.getWelcomeMessage();
    }
    
    /**
     * Demonstrates the refactored solution using polymorphism
     * instead of switch statements.
     */
    public void processCustomer(Customer customer, double amount) {
        double discount = calculateDiscount(customer, amount);
        String message = getWelcomeMessage(customer);
        
        System.out.println("Customer Type: " + customer.getClass().getSimpleName());
        System.out.println("Customer Name: " + customer.name);
        System.out.println("Amount: $" + amount);
        System.out.println("Discount: $" + discount);
        System.out.println("Message: " + message);
        System.out.println("Final Amount: $" + (amount - discount));
    }
}
