package com.cleancode.domain;

/**
 * Value object representing patient demographic information.
 * Encapsulates first and last name as a cohesive unit.
 */
public class PatientDemographics {
    private final String firstName;
    private final String lastName;

    public PatientDemographics(String firstName, String lastName) {
        if (firstName == null || firstName.trim().isEmpty()) {
            throw new IllegalArgumentException("First name cannot be null or empty");
        }
        if (lastName == null || lastName.trim().isEmpty()) {
            throw new IllegalArgumentException("Last name cannot be null or empty");
        }
        this.firstName = firstName.trim();
        this.lastName = lastName.trim();
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public String toString() {
        return String.format("Patient{name='%s %s'}", firstName, lastName);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PatientDemographics that = (PatientDemographics) o;
        return firstName.equals(that.firstName) && lastName.equals(that.lastName);
    }

    @Override
    public int hashCode() {
        return 31 * firstName.hashCode() + lastName.hashCode();
    }
}
