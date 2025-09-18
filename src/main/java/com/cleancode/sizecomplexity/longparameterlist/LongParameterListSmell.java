package com.cleancode.sizecomplexity.longparameterlist;

import com.cleancode.domain.User;

import java.time.LocalDate;

/**
 * Demonstrates the Long Parameter List code smell.
 * This class contains methods with too many parameters, making them difficult to understand and maintain.
 * 
 * Code Smell: Long Parameter List
 * Problem: Methods with 4+ parameters are hard to read, understand, and maintain
 * Impact: Reduced readability, parameter order dependency, poor cohesion
 */
public class LongParameterListSmell {
    
    /**
     * LONG PARAMETER LIST CODE SMELL EXAMPLE
     * 
     * This method has 12 parameters, making it extremely difficult to call correctly.
     * The parameter order is critical, and it's easy to pass parameters in the wrong order.
     * The method is also doing too many things: validation, object creation, and persistence.
     * 
     * Problems:
     * - 12 parameters make the method signature hard to read
     * - Parameter order dependency increases chance of errors
     * - Related parameters are scattered across the parameter list
     * - Difficult to understand what data is required
     */
    public User createUser(
        String firstName, 
        String lastName, 
        String email, 
        String phoneNumber, 
        String address, 
        String city, 
        String state, 
        String zipCode, 
        String country,
        LocalDate dateOfBirth,
        String password,
        boolean isActive
    ) {
        // Validate all parameters
        if (firstName == null || firstName.trim().isEmpty()) {
            throw new IllegalArgumentException("First name is required");
        }
        if (lastName == null || lastName.trim().isEmpty()) {
            throw new IllegalArgumentException("Last name is required");
        }
        if (email == null || !email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            throw new IllegalArgumentException("Valid email is required");
        }
        if (phoneNumber == null || phoneNumber.trim().isEmpty()) {
            throw new IllegalArgumentException("Phone number is required");
        }
        if (address == null || address.trim().isEmpty()) {
            throw new IllegalArgumentException("Address is required");
        }
        if (city == null || city.trim().isEmpty()) {
            throw new IllegalArgumentException("City is required");
        }
        if (state == null || state.trim().isEmpty()) {
            throw new IllegalArgumentException("State is required");
        }
        if (zipCode == null || zipCode.trim().isEmpty()) {
            throw new IllegalArgumentException("Zip code is required");
        }
        if (country == null || country.trim().isEmpty()) {
            throw new IllegalArgumentException("Country is required");
        }
        if (dateOfBirth == null) {
            throw new IllegalArgumentException("Date of birth is required");
        }
        if (password == null || password.length() < 8) {
            throw new IllegalArgumentException("Password must be at least 8 characters");
        }
        
        // Create user object
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPhoneNumber(phoneNumber);
        user.setAddress(address);
        user.setCity(city);
        user.setState(state);
        user.setZipCode(zipCode);
        user.setCountry(country);
        user.setDateOfBirth(dateOfBirth);
        user.setPassword(password);
        user.setActive(isActive);
        
        // Simulate saving to database
        System.out.println("User created: " + user.getFirstName() + " " + user.getLastName());
        
        return user;
    }
    
    /**
     * Demonstrates the problem of repeated long parameter lists
     * across multiple methods with similar parameter groups.
     */
    public void updateUserProfile(
        Long userId,
        String firstName, 
        String lastName, 
        String email, 
        String phoneNumber, 
        String address, 
        String city, 
        String state, 
        String zipCode, 
        String country
    ) {
        // Similar validation and update logic
        System.out.println("Updating user profile for ID: " + userId);
        System.out.println("Name: " + firstName + " " + lastName);
        System.out.println("Email: " + email);
        System.out.println("Address: " + address + ", " + city + ", " + state + " " + zipCode + ", " + country);
    }
}
