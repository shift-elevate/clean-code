package com.cleancode.sizecomplexity.longparameterlist;

import com.cleancode.domain.User;

import java.time.LocalDate;

/**
 * Demonstrates the refactored solution for Long Parameter List code smell.
 * This class shows how to use Introduce Parameter Object to group related parameters.
 * 
 * Refactoring: Introduce Parameter Object
 * Solution: Group related parameters into cohesive objects
 * Benefit: Improved readability, reduced parameter order dependency, better cohesion
 */
public class LongParameterListRefactored {
    
    /**
     * Parameter object for user registration data
     */
    public static class UserRegistrationData {
        private final String firstName;
        private final String lastName;
        private final String email;
        private final String phoneNumber;
        private final Address address;
        private final LocalDate dateOfBirth;
        private final String password;
        private final boolean isActive;
        
        public UserRegistrationData(
            String firstName, 
            String lastName, 
            String email, 
            String phoneNumber, 
            Address address,
            LocalDate dateOfBirth,
            String password,
            boolean isActive
        ) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.email = email;
            this.phoneNumber = phoneNumber;
            this.address = address;
            this.dateOfBirth = dateOfBirth;
            this.password = password;
            this.isActive = isActive;
        }
        
        // Getters
        public String getFirstName() { return firstName; }
        public String getLastName() { return lastName; }
        public String getEmail() { return email; }
        public String getPhoneNumber() { return phoneNumber; }
        public Address getAddress() { return address; }
        public LocalDate getDateOfBirth() { return dateOfBirth; }
        public String getPassword() { return password; }
        public boolean isActive() { return isActive; }
    }
    
    /**
     * Parameter object for address information
     */
    public static class Address {
        private final String street;
        private final String city;
        private final String state;
        private final String zipCode;
        private final String country;
        
        public Address(String street, String city, String state, String zipCode, String country) {
            this.street = street;
            this.city = city;
            this.state = state;
            this.zipCode = zipCode;
            this.country = country;
        }
        
        // Getters
        public String getStreet() { return street; }
        public String getCity() { return city; }
        public String getState() { return state; }
        public String getZipCode() { return zipCode; }
        public String getCountry() { return country; }
        
        @Override
        public String toString() {
            return street + ", " + city + ", " + state + " " + zipCode + ", " + country;
        }
    }
    
    /**
     * REFACTORED SOLUTION: INTRODUCE PARAMETER OBJECT
     * 
     * This method now takes a single parameter object instead of 12 individual parameters.
     * The method signature is much clearer and the parameter order is no longer critical.
     * 
     * Benefits:
     * - Single parameter makes the method signature easy to read
     * - No parameter order dependency
     * - Related data is grouped logically
     * - Clear intent through parameter object name
     */
    public User createUser(UserRegistrationData registrationData) {
        validateRegistrationData(registrationData);
        
        User user = new User();
        user.setFirstName(registrationData.getFirstName());
        user.setLastName(registrationData.getLastName());
        user.setEmail(registrationData.getEmail());
        user.setPhoneNumber(registrationData.getPhoneNumber());
        user.setAddress(registrationData.getAddress().getStreet());
        user.setCity(registrationData.getAddress().getCity());
        user.setState(registrationData.getAddress().getState());
        user.setZipCode(registrationData.getAddress().getZipCode());
        user.setCountry(registrationData.getAddress().getCountry());
        user.setDateOfBirth(registrationData.getDateOfBirth());
        user.setPassword(registrationData.getPassword());
        user.setActive(registrationData.isActive());
        
        // Simulate saving to database
        System.out.println("User created: " + user.getFirstName() + " " + user.getLastName());
        
        return user;
    }
    
    /**
     * REFACTORED SOLUTION: INTRODUCE PARAMETER OBJECT
     * 
     * This method now takes parameter objects instead of individual parameters.
     * The method signature is much cleaner and easier to understand.
     */
    public void updateUserProfile(Long userId, UserRegistrationData profileData) {
        // Similar refactored logic
        System.out.println("Updating user profile for ID: " + userId);
        System.out.println("Name: " + profileData.getFirstName() + " " + profileData.getLastName());
        System.out.println("Email: " + profileData.getEmail());
        System.out.println("Address: " + profileData.getAddress());
    }
    
    /**
     * Validation method for registration data
     */
    private void validateRegistrationData(UserRegistrationData data) {
        if (data.getFirstName() == null || data.getFirstName().trim().isEmpty()) {
            throw new IllegalArgumentException("First name is required");
        }
        if (data.getLastName() == null || data.getLastName().trim().isEmpty()) {
            throw new IllegalArgumentException("Last name is required");
        }
        if (data.getEmail() == null || !data.getEmail().matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            throw new IllegalArgumentException("Valid email is required");
        }
        if (data.getPhoneNumber() == null || data.getPhoneNumber().trim().isEmpty()) {
            throw new IllegalArgumentException("Phone number is required");
        }
        if (data.getAddress() == null) {
            throw new IllegalArgumentException("Address is required");
        }
        if (data.getDateOfBirth() == null) {
            throw new IllegalArgumentException("Date of birth is required");
        }
        if (data.getPassword() == null || data.getPassword().length() < 8) {
            throw new IllegalArgumentException("Password must be at least 8 characters");
        }
    }
}
