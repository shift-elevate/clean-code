package com.cleancode.dependencyproblems.featureenvy;

import com.cleancode.domain.Car;
import com.cleancode.domain.CarType;
import com.cleancode.domain.RentalCustomer;
import com.cleancode.domain.Rental;

/**
 * Demonstrates the Move Method refactoring solution for Feature Envy.
 * This class shows how to resolve Feature Envy by moving methods to the classes that contain the data they operate on.
 * 
 * Refactoring: Move Method
 * Solution: Move methods to classes that contain the data they operate on most frequently
 * Benefits: Improved encapsulation, reduced coupling, better maintainability
 */
public class FeatureEnvyRefactored {
    
    /**
     * REFACTORED VERSION - MOVE METHOD PATTERN
     * 
     * This method now simply delegates to the Rental class, which is the natural home
     * for the calculation logic since it contains all the relevant data.
     */
    public double calculateRentalCost(Rental rental) {
        return rental.calculateTotalCost();
    }
}
