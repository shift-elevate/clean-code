package com.cleancode.couplers.featureenvy;

import com.cleancode.domain.Car;
import com.cleancode.domain.CarType;
import com.cleancode.domain.RentalCustomer;
import com.cleancode.domain.Rental;

/**
 * Demonstrates the Feature Envy code smell as shown in the article.
 * This class contains a method that is more interested in another class's data than its own.
 * 
 * Code Smell: Feature Envy
 * Problem: Method uses more data from another class than from its own class
 * Impact: Violates encapsulation, creates tight coupling, reduces maintainability
 */
public class RentalCalculator {
    
    private static final double LOYALTY_DISCOUNT_RATE = 0.9;
    private static final double CAR_AGE_DISCOUNT_RATE = 0.95;
    private static final double LUXURY_CAR_SURCHARGE_RATE = 1.25;
    private static final int LOYALTY_THRESHOLD_YEARS = 2;
    private static final int CAR_AGE_THRESHOLD_YEARS = 5;
    
    /**
     * FEATURE ENVY CODE SMELL EXAMPLE
     * 
     * This method violates encapsulation by extensively accessing data from Car and Customer objects.
     * The method is more interested in Car and Customer data than its own RentalCalculator data.
     * 
     * Problems:
     * - Multiple getter calls to Car object (getDailyRate, getType, getAge)
     * - Multiple getter calls to Customer object (getLoyaltyYears)
     * - Method seems to belong more to Rental class where this data is accessible
     * - Violates Tell-Don't-Ask principle
     * - Creates tight coupling between RentalCalculator and Car/Customer
     */
    public double calculateRentalCost(Rental rental) {
        double baseCost = 0.0;
        
        // Feature Envy: This method is more interested in Car's data
        Car car = rental.getCar();
        double dailyRate = car.getDailyRate();
        CarType carType = car.getType();
        int carAge = car.getAge();
        
        // Feature Envy: This method is more interested in Customer's data
        RentalCustomer customer = rental.getCustomer();
        int loyaltyYears = customer.getLoyaltyYears();
        
        // Calculate base cost
        int rentalDays = rental.getDays();
        baseCost = dailyRate * rentalDays;
        
        // Apply car-specific adjustments
        if (carAge > CAR_AGE_THRESHOLD_YEARS) {
            baseCost *= CAR_AGE_DISCOUNT_RATE; // 5% discount for older cars
        }
        if (CarType.LUXURY.equals(carType)) {
            baseCost *= LUXURY_CAR_SURCHARGE_RATE; // 25% surcharge for luxury cars
        }
        
        // Apply customer-specific discounts
        if (loyaltyYears > LOYALTY_THRESHOLD_YEARS) {
            baseCost *= LOYALTY_DISCOUNT_RATE; // 10% loyalty discount
        }
        
        return baseCost;
    }
}
