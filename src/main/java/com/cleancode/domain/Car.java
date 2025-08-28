package com.cleancode.domain;

/**
 * Domain class representing a car in the rental system.
 * Used to demonstrate Feature Envy code smell and Move Method refactoring.
 */
public class Car {
    private CarType type;
    private double dailyRate;
    private int age;
    
    public Car(CarType type, double dailyRate, int age) {
        this.type = type;
        this.dailyRate = dailyRate;
        this.age = age;
    }
    
    public CarType getType() {
        return type;
    }
    
    public double getDailyRate() {
        return dailyRate;
    }
    
    public int getAge() {
        return age;
    }
    
    /**
     * Calculates daily cost with car-specific adjustments.
     * This method belongs with the car data it operates on.
     */
    public double calculateDailyCost() {
        double cost = dailyRate;
        
        // Apply car-specific adjustments
        if (age > 5) { // Car age threshold
            cost *= 0.95; // 5% discount for older cars
        }
        if (CarType.LUXURY.equals(type)) {
            cost *= 1.25; // 25% surcharge for luxury cars
        }
        
        return cost;
    }
    
    public void setType(CarType type) {
        this.type = type;
    }
    
    public void setDailyRate(double dailyRate) {
        this.dailyRate = dailyRate;
    }
    
    public void setAge(int age) {
        this.age = age;
    }
    
    @Override
    public String toString() {
        return "Car{" +
                "type=" + type +
                ", dailyRate=" + dailyRate +
                ", age=" + age +
                '}';
    }
}
