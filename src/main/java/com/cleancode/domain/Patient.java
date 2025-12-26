package com.cleancode.domain;

/**
 * Coordinator class that brings together patient-related value objects.
 * Demonstrates how extracted classes work together.
 */
public class Patient {
    private final PatientDemographics demographics;
    private ContactInformation contactInfo;
    private final InsuranceDetails insurance;

    public Patient(PatientDemographics demographics,
                   ContactInformation contactInfo,
                   InsuranceDetails insurance) {
        if (demographics == null) {
            throw new IllegalArgumentException("Demographics cannot be null");
        }
        if (contactInfo == null) {
            throw new IllegalArgumentException("Contact information cannot be null");
        }
        if (insurance == null) {
            throw new IllegalArgumentException("Insurance details cannot be null");
        }
        this.demographics = demographics;
        this.contactInfo = contactInfo;
        this.insurance = insurance;
    }

    // Constructor for creating patient without full contact info
    public Patient() {
        this.demographics = null;
        this.contactInfo = null;
        this.insurance = null;
    }

    public void updateContactInfo(ContactInformation newContactInfo) {
        if (newContactInfo == null) {
            throw new IllegalArgumentException("New contact information cannot be null");
        }
        this.contactInfo = newContactInfo;
    }

    public PatientDemographics getDemographics() {
        return demographics;
    }

    public ContactInformation getContactInfo() {
        return contactInfo;
    }

    public InsuranceDetails getInsurance() {
        return insurance;
    }

    @Override
    public String toString() {
        return String.format("Patient{demographics=%s, contact=%s, insurance=%s}",
                demographics, contactInfo, insurance);
    }
}
