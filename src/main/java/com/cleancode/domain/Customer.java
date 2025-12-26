package com.cleancode.domain;

/**
 * Domain class representing a customer in the e-commerce system.
 * Used to demonstrate code smells and refactoring techniques.
 */
public class Customer {
    private String customerId;
    private String name;
    private String email;
    private boolean premium;
    
    public Customer(String customerId, String name, String email, boolean premium) {
        this.customerId = customerId;
        this.name = name;
        this.email = email;
        this.premium = premium;
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
    
    public boolean isPremium() {
        return premium;
    }
    
    public void setPremium(boolean premium) {
        this.premium = premium;
    }
    
    @Override
    public String toString() {
        return "Customer{" +
                "customerId='" + customerId + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", premium=" + premium +
                '}';
    }
}
