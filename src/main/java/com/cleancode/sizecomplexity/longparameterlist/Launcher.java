package com.cleancode.sizecomplexity.longparameterlist;

import com.cleancode.domain.User;

import java.time.LocalDate;

/**
 * Launcher class for Long Parameter List code smell demonstration.
 * Demonstrates the Long Parameter List code smell and its refactored solution.
 */
public class Launcher {
    
    public static void main(String[] args) {
        System.out.println("🐛 LONG PARAMETER LIST CODE SMELL DEMONSTRATION");
        System.out.println("===============================================");
        
        // Demonstrate the code smell
        demonstrateCodeSmell();
        
        System.out.println("\n" + "=".repeat(50) + "\n");
        
        // Demonstrate the refactored solution
        demonstrateRefactoredSolution();
    }
    
    private static void demonstrateCodeSmell() {
        System.out.println("❌ LONG PARAMETER LIST CODE SMELL");
        
        LongParameterListSmell processor = new LongParameterListSmell();
        
        System.out.println("Creating user with 12 parameters:");
        User user = processor.createUser(
            "Alice", 
            "Johnson", 
            "alice@example.com", 
            "555-123-4567", 
            "123 Main St", 
            "New York", 
            "NY", 
            "10001", 
            "USA",
            LocalDate.of(1990, 5, 15),
            "password123",
            true
        );
        
        System.out.println("\nUpdating user profile with 10 parameters:");
        processor.updateUserProfile(
            1L,
            "Alice", 
            "Johnson", 
            "alice@example.com", 
            "555-123-4567", 
            "123 Main St", 
            "New York", 
            "NY", 
            "10001", 
            "USA"
        );
    }
    
    private static void demonstrateRefactoredSolution() {
        System.out.println("✅ LONG PARAMETER LIST REFACTORED");
        
        LongParameterListRefactored processor = new LongParameterListRefactored();
        
        System.out.println("Creating user with parameter object:");
        
        // Create parameter objects
        LongParameterListRefactored.Address address = new LongParameterListRefactored.Address(
            "456 Oak Ave", "Los Angeles", "CA", "90210", "USA"
        );
        
        LongParameterListRefactored.UserRegistrationData userData = 
            new LongParameterListRefactored.UserRegistrationData(
                "Bob", 
                "Wilson", 
                "bob@example.com", 
                "555-987-6543", 
                address,
                LocalDate.of(1985, 8, 20),
                "password456",
                false
            );
        
        User user = processor.createUser(userData);
        
        System.out.println("\nUpdating user profile with parameter object:");
        processor.updateUserProfile(2L, userData);
    }
}
