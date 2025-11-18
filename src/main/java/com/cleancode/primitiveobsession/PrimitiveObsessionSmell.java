package com.cleancode.primitiveobsession;

/**
 * Demonstrates the Primitive Obsession code smell.
 * This class uses primitive types instead of meaningful objects to represent domain concepts.
 *
 * Code Smell: Primitive Obsession
 * Problem: Primitive types used for domain concepts instead of small objects
 * Impact: Scattered validation logic, reduced expressiveness, higher bug risk
 */
public class PrimitiveObsessionSmell {
    private String email;
    private String phoneNumber;
    private String zipCode;
    private double salary;
    private String status;

    public PrimitiveObsessionSmell(String email, String phoneNumber, String zipCode,
                                   double salary, String status) {
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.zipCode = zipCode;
        this.salary = salary;
        this.status = status;
    }

    /**
     * PRIMITIVE OBSESSION SMELL: Validation scattered across multiple methods
     * Each primitive field requires its own validation method
     */
    public boolean isValidEmail() {
        return email != null && email.contains("@") && email.contains(".");
    }

    public boolean isValidPhoneNumber() {
        return phoneNumber != null && phoneNumber.matches("\\+[1-9]\\d{1,14}");
    }

    public boolean isValidZipCode() {
        return zipCode != null && zipCode.matches("\\d{5}(-\\d{4})?");
    }

    /**
     * PRIMITIVE OBSESSION SMELL: Business logic mixed with primitives
     * Status is just a string - no type safety
     */
    public boolean isActive() {
        return "ACTIVE".equals(status);
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getZipCode() {
        return zipCode;
    }

    public double getSalary() {
        return salary;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", salary=" + salary +
                ", status='" + status + '\'' +
                '}';
    }
}
