package com.cleancode.sizecomplexity.magicnumbers;

import com.cleancode.domain.Customer;
import com.cleancode.domain.Order;
import com.cleancode.domain.OrderItem;

/**
 * Launcher class for Magic Numbers code smell demonstration.
 * Demonstrates the Magic Numbers code smell and its refactored solution.
 */
public class Launcher {
    
    public static void main(String[] args) {
        System.out.println("🔢 MAGIC NUMBERS CODE SMELL DEMONSTRATION");
        System.out.println("==========================================");
        
        // Demonstrate the code smell
        demonstrateCodeSmell();
        
        System.out.println("\n" + "=".repeat(50) + "\n");
        
        // Demonstrate the refactored solution
        demonstrateRefactoredSolution();
    }
    
    private static void demonstrateCodeSmell() {
        System.out.println("❌ MAGIC NUMBERS CODE SMELL");
        
        MagicNumbersSmell processor = new MagicNumbersSmell();
        
        // Create test data
        Customer customer = new Customer("C001", "John Doe", "john@example.com", true);
        Order order = new Order("O001", customer);
        order.addItem(new OrderItem("P001", "Laptop", 999.99, 1));
        order.addItem(new OrderItem("P002", "Mouse", 29.99, 2));
        order.setWeight(5.5); // Set weight for shipping calculation
        
        System.out.println("Before processing:");
        System.out.println("Order: " + order);
        System.out.println("Total: $" + order.getTotal());
        System.out.println("Weight: " + order.getWeight() + " lbs");
        
        // Calculate discount
        double discountedTotal = processor.calculateDiscount(order);
        System.out.println("Discounted Total: $" + discountedTotal);
        
        // Check free shipping eligibility
        boolean freeShipping = processor.isEligibleForFreeShipping(order);
        System.out.println("Free Shipping Eligible: " + freeShipping);
        
        // Calculate delivery days
        int standardDays = processor.calculateDeliveryDays("STANDARD");
        int expressDays = processor.calculateDeliveryDays("EXPRESS");
        System.out.println("Standard Delivery: " + standardDays + " days");
        System.out.println("Express Delivery: " + expressDays + " days");
    }
    
    private static void demonstrateRefactoredSolution() {
        System.out.println("✅ MAGIC NUMBERS REFACTORED");
        
        MagicNumbersRefactored processor = new MagicNumbersRefactored();
        
        // Create test data
        Customer customer = new Customer("C002", "Jane Smith", "jane@example.com", true);
        Order order = new Order("O002", customer);
        order.addItem(new OrderItem("P003", "Monitor", 299.99, 1));
        order.addItem(new OrderItem("P004", "Keyboard", 89.99, 1));
        order.setWeight(8.2); // Set weight for shipping calculation
        
        System.out.println("Before processing:");
        System.out.println("Order: " + order);
        System.out.println("Total: $" + order.getTotal());
        System.out.println("Weight: " + order.getWeight() + " lbs");
        
        // Calculate discount
        double discountedTotal = processor.calculateDiscount(order);
        System.out.println("Discounted Total: $" + discountedTotal);
        
        // Check free shipping eligibility
        boolean freeShipping = processor.isEligibleForFreeShipping(order);
        System.out.println("Free Shipping Eligible: " + freeShipping);
        
        // Calculate delivery days
        int standardDays = processor.calculateDeliveryDays("STANDARD");
        int expressDays = processor.calculateDeliveryDays("EXPRESS");
        System.out.println("Standard Delivery: " + standardDays + " days");
        System.out.println("Express Delivery: " + expressDays + " days");
        
        System.out.println("\n" + "=".repeat(50));
        System.out.println("🎯 REFACTORING BENEFITS:");
        System.out.println("✅ Constants with descriptive names make code self-documenting");
        System.out.println("✅ Business rules are centralized and easily modifiable");
        System.out.println("✅ Clear constant names reduce the likelihood of errors");
        System.out.println("✅ Code is more readable and maintainable");
    }
}
