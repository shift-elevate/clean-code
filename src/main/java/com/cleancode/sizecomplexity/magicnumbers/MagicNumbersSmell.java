package com.cleancode.sizecomplexity.magicnumbers;

import com.cleancode.domain.Order;
import com.cleancode.domain.OrderItem;

/**
 * Demonstrates the Magic Numbers code smell.
 * This class contains literal values scattered throughout code that lack clear meaning.
 * 
 * Code Smell: Magic Numbers
 * Problem: Literal numbers, strings, or other values appear in code without clear meaning or context
 * Impact: Reduced readability, difficult to maintain, higher bug risk
 */
public class MagicNumbersSmell {
    
    /**
     * MAGIC NUMBERS CODE SMELL EXAMPLE
     * 
     * This class demonstrates the Magic Numbers code smell with literal values
     * that have no clear meaning or context. These numbers represent business
     * rules and configuration values that should be clearly documented.
     * 
     * Problems:
     * - Literal numbers without clear meaning
     * - Business rules hidden in scattered numbers
     * - Code hard to understand and maintain
     * - Higher risk of bugs due to unclear values
     */
    
    public double calculateDiscount(Order order) {
        double total = calculateOrderTotal(order);
        
        // Magic Numbers: What do these values mean?
        if (order.getCustomer().isPremium()) {
            total = total * 0.9; // What is 0.9?
        }
        if (total > 100.0) {
            total = total * 0.95; // What is 100.0 and 0.95?
        }
        
        return total;
    }
    
    private double calculateOrderTotal(Order order) {
        double total = 0.0;
        for (OrderItem item : order.getItems()) {
            total += item.getPrice() * item.getQuantity();
        }
        return total;
    }
    
    public boolean isEligibleForFreeShipping(Order order) {
        // Magic Numbers: What do these values represent?
        double total = calculateOrderTotal(order);
        return total >= 50.0 && order.getWeight() <= 10.0;
    }
    
    public int calculateDeliveryDays(String shippingMethod) {
        switch (shippingMethod) {
            case "STANDARD":
                return 5; // What does 5 mean?
            case "EXPRESS":
                return 2; // What does 2 mean?
            case "OVERNIGHT":
                return 1; // What does 1 mean?
            default:
                return 7; // What does 7 mean?
        }
    }
}
