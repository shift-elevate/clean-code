package com.cleancode.sizecomplexity.longmethod;

import com.cleancode.domain.Order;
import com.cleancode.domain.OrderItem;
import com.cleancode.domain.OrderStatus;

/**
 * Demonstrates the Long Method code smell.
 * This class contains a method that is too long and handles multiple responsibilities.
 * 
 * Code Smell: Long Method
 * Problem: Method exceeds 20+ lines and handles multiple concerns
 * Impact: Reduced readability, difficult testing, higher bug risk
 */
public class LongMethodSmell {
    
    /**
     * LONG METHOD CODE SMELL EXAMPLE
     * 
     * This method violates the Single Responsibility Principle by handling:
     * - Order validation
     * - Total calculation
     * - Discount application
     * - Order status updates
     * 
     * Problems:
     * - 40+ lines of code
     * - Multiple levels of nesting
     * - Difficult to test individual behaviors
     * - Hard to understand at a glance
     */
    public void processOrder(Order order) {
        // Validate order
        if (order == null) {
            throw new IllegalArgumentException("Order cannot be null");
        }
        if (order.getItems().isEmpty()) {
            throw new IllegalArgumentException("Order must contain at least one item");
        }
        if (order.getCustomer() == null) {
            throw new IllegalArgumentException("Order must have a customer");
        }
        
        // Calculate total
        double total = 0.0;
        for (OrderItem item : order.getItems()) {
            double itemPrice = item.getPrice();
            int quantity = item.getQuantity();
            double itemTotal = itemPrice * quantity;
            total += itemTotal;
        }
        
        // Apply discounts
        if (order.getCustomer().isPremium()) {
            total = total * 0.9; // 10% discount for premium customers
        }
        if (total > 100.0) {
            total = total * 0.95; // 5% discount for orders over $100
        }
        
        // Update order
        order.setTotal(total);
        order.setStatus(OrderStatus.PROCESSED);
        
        // Simulate saving to database
        System.out.println("Order processed and saved: " + order.getOrderId());
    }
}
