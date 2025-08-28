package com.cleancode.domain;

/**
 * Domain class representing a rental in the rental system.
 * Used to demonstrate Feature Envy code smell and Move Method refactoring.
 */
public class Rental {
    private Car car;
    private RentalCustomer customer;
    private int days;
    
    public Rental(Car car, RentalCustomer customer, int days) {
        this.car = car;
        this.customer = customer;
        this.days = days;
    }
    
    public Car getCar() {
        return car;
    }
    
    public RentalCustomer getCustomer() {
        return customer;
    }
    
    public int getDays() {
        return days;
    }
    
    /**
     * Calculates total cost using data from its own fields.
     * This eliminates Feature Envy by placing the method with the data it operates on.
     */
    public double calculateTotalCost() {
        double baseCost = car.calculateDailyCost() * days;
        return customer.applyDiscounts(baseCost);
    }
    
    public void setCar(Car car) {
        this.car = car;
    }
    
    public void setCustomer(RentalCustomer customer) {
        this.customer = customer;
    }
    
    public void setDays(int days) {
        this.days = days;
    }
    
    @Override
    public String toString() {
        return "Rental{" +
                "car=" + car +
                ", customer=" + customer +
                ", days=" + days +
                '}';
    }
}
