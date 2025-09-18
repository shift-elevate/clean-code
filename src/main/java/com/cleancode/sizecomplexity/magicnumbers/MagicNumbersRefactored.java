package com.cleancode.sizecomplexity.magicnumbers;

import com.cleancode.domain.Order;
import com.cleancode.domain.OrderItem;

/**
 * Demonstrates the Replace Magic Number with Named Constant refactoring solution.
 * This class shows how to replace literal values with descriptive constants.
 * 
 * Refactoring: Replace Magic Number with Named Constant
 * Solution: Replace literal values with descriptive constants that clearly communicate their purpose
 * Benefits: Improved readability, enhanced maintainability, reduced bug risk
 */
public class MagicNumbersRefactored {
    
    /**
     * REFACTORED VERSION - REPLACE MAGIC NUMBER WITH NAMED CONSTANT PATTERN
     * 
     * This class demonstrates how to replace magic numbers with descriptive constants
     * that make the code self-documenting and easier to maintain.
     */
    
    // Discount constants
    private static final double PREMIUM_CUSTOMER_DISCOUNT_RATE = 0.9; // 10% discount
    private static final double BULK_ORDER_DISCOUNT_RATE = 0.95; // 5% discount for orders over threshold
    private static final double BULK_ORDER_AMOUNT_THRESHOLD = 100.0; // $100 minimum for bulk discount
    
    // Shipping constants
    private static final double FREE_SHIPPING_THRESHOLD = 50.0; // $50 minimum
    private static final double MAX_FREE_SHIPPING_WEIGHT = 10.0; // 10 lbs maximum
    
    // Delivery time constants (in days)
    private static final int STANDARD_DELIVERY_DAYS = 5;
    private static final int EXPRESS_DELIVERY_DAYS = 2;
    private static final int OVERNIGHT_DELIVERY_DAYS = 1;
    private static final int DEFAULT_DELIVERY_DAYS = 7;
    
    public double calculateDiscount(Order order) {
        double total = calculateOrderTotal(order);
        
        if (order.getCustomer().isPremium()) {
            total = total * PREMIUM_CUSTOMER_DISCOUNT_RATE;
        }
        if (total > BULK_ORDER_AMOUNT_THRESHOLD) {
            total = total * BULK_ORDER_DISCOUNT_RATE;
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
        double total = calculateOrderTotal(order);
        return total >= FREE_SHIPPING_THRESHOLD && 
               order.getWeight() <= MAX_FREE_SHIPPING_WEIGHT;
    }
    
    public int calculateDeliveryDays(String shippingMethod) {
        switch (shippingMethod) {
            case "STANDARD":
                return STANDARD_DELIVERY_DAYS;
            case "EXPRESS":
                return EXPRESS_DELIVERY_DAYS;
            case "OVERNIGHT":
                return OVERNIGHT_DELIVERY_DAYS;
            default:
                return DEFAULT_DELIVERY_DAYS;
        }
    }
}
