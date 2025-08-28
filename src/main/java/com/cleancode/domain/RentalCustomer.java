package com.cleancode.domain;

/**
 * Domain class representing a customer in the rental system.
 * Used specifically for Feature Envy code smell demonstration.
 */
public class RentalCustomer {
    private String customerId;
    private String name;
    private String email;
    private int loyaltyYears;
    
    public RentalCustomer(String customerId, String name, String email, int loyaltyYears) {
        this.customerId = customerId;
        this.name = name;
        this.email = email;
        this.loyaltyYears = loyaltyYears;
    }
    
    public String getCustomerId() {
        return customerId;
    }
    
    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public int getLoyaltyYears() {
        return loyaltyYears;
    }
    
    public void setLoyaltyYears(int loyaltyYears) {
        this.loyaltyYears = loyaltyYears;
    }
    
    /**
     * Applies customer-specific discounts.
     * This method belongs with the customer data it operates on.
     */
    public double applyDiscounts(double cost) {
        if (loyaltyYears > 2) { // Loyalty threshold
            return cost * 0.9; // 10% loyalty discount
        }
        return cost;
    }
    
    @Override
    public String toString() {
        return "RentalCustomer{" +
                "customerId='" + customerId + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", loyaltyYears=" + loyaltyYears +
                '}';
    }
}
