package com.cleancode.sizecomplexity.longmethod;

import com.cleancode.domain.Order;
import com.cleancode.domain.OrderItem;
import com.cleancode.domain.OrderStatus;

/**
 * Demonstrates the Extract Method refactoring solution.
 * This class shows how to break down a long method into smaller, focused methods.
 * 
 * Refactoring: Extract Method
 * Solution: Break down long method into smaller methods with single responsibilities
 * Benefits: Improved readability, enhanced testability, better maintainability
 */
public class LongMethodRefactored {
    
    /**
     * REFACTORED VERSION - EXTRACT METHOD PATTERN
     * 
     * This method now reads like a high-level workflow, making it easy to understand
     * the overall process. Each step is delegated to a focused method.
     */
    public void processOrder(Order order) {
        validateOrder(order);
        double total = calculateOrderTotal(order);
        double discountedTotal = applyDiscounts(order, total);
        updateOrderStatus(order, discountedTotal);
        saveOrder(order);
    }
    
    /**
     * Extracted method: Handles order validation
     * Single responsibility: Validate order data
     */
    private void validateOrder(Order order) {
        if (order == null) {
            throw new IllegalArgumentException("Order cannot be null");
        }
        if (order.getItems().isEmpty()) {
            throw new IllegalArgumentException("Order must contain at least one item");
        }
        if (order.getCustomer() == null) {
            throw new IllegalArgumentException("Order must have a customer");
        }
    }
    
    /**
     * Extracted method: Calculates order total
     * Single responsibility: Calculate total from order items
     */
    private double calculateOrderTotal(Order order) {
        double total = 0.0;
        for (OrderItem item : order.getItems()) {
            total += calculateItemTotal(item);
        }
        return total;
    }
    
    /**
     * Extracted method: Calculates individual item total
     * Single responsibility: Calculate total for a single item
     */
    private double calculateItemTotal(OrderItem item) {
        return item.getPrice() * item.getQuantity();
    }
    
    /**
     * Extracted method: Applies discounts
     * Single responsibility: Apply discount rules
     */
    private double applyDiscounts(Order order, double total) {
        double discountedTotal = total;
        
        if (order.getCustomer().isPremium()) {
            discountedTotal *= 0.9; // 10% discount for premium customers
        }
        if (discountedTotal > 100.0) {
            discountedTotal *= 0.95; // 5% discount for orders over $100
        }
        
        return discountedTotal;
    }
    
    /**
     * Extracted method: Updates order status
     * Single responsibility: Update order with final total and status
     */
    private void updateOrderStatus(Order order, double total) {
        order.setTotal(total);
        order.setStatus(OrderStatus.PROCESSED);
    }
    
    /**
     * Extracted method: Saves order
     * Single responsibility: Persist order to database
     */
    private void saveOrder(Order order) {
        System.out.println("Order processed and saved: " + order.getOrderId());
    }
}
