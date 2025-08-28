package com.cleancode.bloaters.longmethod;

import com.cleancode.domain.Customer;
import com.cleancode.domain.Order;
import com.cleancode.domain.OrderItem;

/**
 * Launcher class for Long Method code smell demonstration.
 * Demonstrates the Long Method code smell and its refactored solution.
 */
public class Launcher {
    
    public static void main(String[] args) {
        System.out.println("🐛 LONG METHOD CODE SMELL DEMONSTRATION");
        System.out.println("=======================================");
        
        // Demonstrate the code smell
        demonstrateCodeSmell();
        
        System.out.println("\n" + "=".repeat(50) + "\n");
        
        // Demonstrate the refactored solution
        demonstrateRefactoredSolution();
    }
    
    private static void demonstrateCodeSmell() {
        System.out.println("❌ LONG METHOD CODE SMELL");
        
        LongMethodSmell processor = new LongMethodSmell();
        
        // Create test data
        Customer customer = new Customer("C001", "John Doe", "john@example.com", true);
        Order order = new Order("O001", customer);
        order.addItem(new OrderItem("P001", "Laptop", 999.99, 1));
        order.addItem(new OrderItem("P002", "Mouse", 29.99, 2));
        
        System.out.println("Before processing:");
        System.out.println("Order: " + order);
        System.out.println("Total: $" + order.getTotal());
        System.out.println("Status: " + order.getStatus());
        
        // Process order
        processor.processOrder(order);
        
        System.out.println("\nAfter processing:");
        System.out.println("Order: " + order);
        System.out.println("Total: $" + order.getTotal());
        System.out.println("Status: " + order.getStatus());
    }
    
    private static void demonstrateRefactoredSolution() {
        System.out.println("✅ LONG METHOD REFACTORED");
        
        LongMethodRefactored processor = new LongMethodRefactored();
        
        // Create test data
        Customer customer = new Customer("C002", "Jane Smith", "jane@example.com", true);
        Order order = new Order("O002", customer);
        order.addItem(new OrderItem("P003", "Monitor", 299.99, 1));
        order.addItem(new OrderItem("P004", "Keyboard", 89.99, 1));
        
        System.out.println("Before processing:");
        System.out.println("Order: " + order);
        System.out.println("Total: $" + order.getTotal());
        System.out.println("Status: " + order.getStatus());
        
        // Process order
        processor.processOrder(order);
        
        System.out.println("\nAfter processing:");
        System.out.println("Order: " + order);
        System.out.println("Total: $" + order.getTotal());
        System.out.println("Status: " + order.getStatus());
    }
}
