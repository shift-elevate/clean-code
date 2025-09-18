package com.cleancode.dependencyproblems.featureenvy;

import com.cleancode.domain.Car;
import com.cleancode.domain.CarType;
import com.cleancode.domain.RentalCustomer;
import com.cleancode.domain.Rental;

/**
 * Launcher class for Feature Envy code smell demonstration.
 * Demonstrates the Feature Envy code smell and its refactored solution.
 */
public class Launcher {
    
    public static void main(String[] args) {
        System.out.println("🐛 FEATURE ENVY CODE SMELL DEMONSTRATION");
        System.out.println("=========================================");
        
        // Demonstrate the code smell
        demonstrateCodeSmell();
        
        System.out.println("\n" + "=".repeat(50) + "\n");
        
        // Demonstrate the refactored solution
        demonstrateRefactoredSolution();
    }
    
    private static void demonstrateCodeSmell() {
        System.out.println("❌ FEATURE ENVY CODE SMELL");
        
        FeatureEnvySmell calculator = new FeatureEnvySmell();
        
        // Create test data
        Car luxuryCar = new Car(CarType.LUXURY, 150.0, 6);
        RentalCustomer loyalCustomer = new RentalCustomer("CUST001", "John Doe", "john@example.com", 3);
        Rental rental = new Rental(luxuryCar, loyalCustomer, 5);
        
        // Calculate cost using the problematic method
        double cost = calculator.calculateRentalCost(rental);
        
        System.out.println("Rental Details:");
        System.out.println("- Car: " + luxuryCar);
        System.out.println("- Customer: " + loyalCustomer);
        System.out.println("- Days: " + rental.getDays());
        System.out.println();
        System.out.println("Calculated Cost: $" + String.format("%.2f", cost));
    }
    
    private static void demonstrateRefactoredSolution() {
        System.out.println("✅ FEATURE ENVY REFACTORED");
        
        FeatureEnvyRefactored calculator = new FeatureEnvyRefactored();
        
        // Create test data
        Car luxuryCar = new Car(CarType.LUXURY, 150.0, 6);
        RentalCustomer loyalCustomer = new RentalCustomer("CUST001", "John Doe", "john@example.com", 3);
        Rental rental = new Rental(luxuryCar, loyalCustomer, 5);
        
        // Calculate cost using the refactored method
        double cost = calculator.calculateRentalCost(rental);
        
        System.out.println("Rental Details:");
        System.out.println("- Car: " + luxuryCar);
        System.out.println("- Customer: " + loyalCustomer);
        System.out.println("- Days: " + rental.getDays());
        System.out.println();
        System.out.println("Calculated Cost: $" + String.format("%.2f", cost));
    }
}
