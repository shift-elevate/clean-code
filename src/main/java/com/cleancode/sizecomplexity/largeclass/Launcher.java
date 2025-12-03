package com.cleancode.sizecomplexity.largeclass;

/**
 * Launcher class for Large Class code smell demonstration.
 * Demonstrates the Large Class code smell and its refactored solution.
 */
public class Launcher {

    public static void main(String[] args) {
        System.out.println("🐛 LARGE CLASS CODE SMELL DEMONSTRATION");
        System.out.println("========================================");

        // Demonstrate the code smell
        demonstrateCodeSmell();

        System.out.println("\n" + "=".repeat(50) + "\n");

        // Demonstrate the refactored solution
        demonstrateRefactoredSolution();
    }
    
    private static void demonstrateCodeSmell() {
        System.out.println("❌ LARGE CLASS CODE SMELL");
        System.out.println("Problem: Class with too many responsibilities\n");

        LargeClassSmell customerManager = new LargeClassSmell("CUST001");
        
        // Customer data operations
        customerManager.updatePersonalInfo("John", "Doe", "john.doe@example.com");
        customerManager.updateAddress("123 Main St", "Springfield", "IL", "62701", "USA");
        
        // Account operations
        customerManager.addFunds(500.0);
        customerManager.processPayment(100.0);
        
        // Order operations
        customerManager.addOrder("ORDER001", 150.0);
        customerManager.addOrder("ORDER002", 75.0);
        
        // Notification operations
        customerManager.updateNotificationPreferences(true, false, true);
        customerManager.sendNotification("Welcome message", "EMAIL");
        
        System.out.println("\nResults:");
        System.out.println("Account Balance: $" + customerManager.getAccountBalance());
        System.out.println("Average Order Value: $" + customerManager.calculateAverageOrderValue());
        System.out.println("Loyalty Points: " + customerManager.getLoyaltyPoints());

        System.out.println("\n--- Issues with Large Class ---");
        System.out.println("⚠️  Too many fields and methods in one class");
        System.out.println("⚠️  Multiple unrelated responsibilities (profile, account, orders, notifications)");
        System.out.println("⚠️  Difficult to understand and maintain");
        System.out.println("⚠️  Hard to test individual components");
    }
    
    private static void demonstrateRefactoredSolution() {
        System.out.println("✅ LARGE CLASS REFACTORED");
        System.out.println("Solution: Extract focused classes with single responsibilities\n");

        LargeClassRefactored customerManager = new LargeClassRefactored("CUST002");
        
        // Same operations, but now delegated to appropriate classes
        customerManager.updatePersonalInfo("Jane", "Smith", "jane.smith@example.com");
        customerManager.updateAddress("456 Oak Ave", "Chicago", "IL", "60601", "USA");
        
        customerManager.addFunds(750.0);
        customerManager.processPayment(200.0);
        
        customerManager.addOrder("ORDER003", 120.0);
        customerManager.addOrder("ORDER004", 80.0);
        
        customerManager.updateNotificationPreferences(true, true, false);

        System.out.println("\nResults:");
        System.out.println("Account Balance: $" + customerManager.getAccountBalance());
        System.out.println("Average Order Value: $" + customerManager.calculateAverageOrderValue());

        System.out.println("\n--- Benefits of Extract Class ---");
        System.out.println("✅ Each class has a single, well-defined responsibility:");
        System.out.println("   - CustomerProfile: Manages personal information");
        System.out.println("   - AccountManager: Handles account operations");
        System.out.println("   - OrderHistory: Tracks orders and spending");
        System.out.println("   - NotificationManager: Manages notifications");
        System.out.println("\n✅ Easier to understand, test, and maintain");
        System.out.println("✅ Better reusability and modularity");
        System.out.println("✅ Follows Single Responsibility Principle");
    }
}

