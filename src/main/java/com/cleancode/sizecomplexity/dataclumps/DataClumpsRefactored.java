package com.cleancode.sizecomplexity.dataclumps;

import com.cleancode.domain.*;

/**
 * REFACTORED: Extract Class Solution
 *
 * This class demonstrates the Extract Class refactoring technique to eliminate
 * data clumps by grouping related data into meaningful objects.
 *
 * Improvements:
 * - Shorter parameter lists (3 objects instead of 6+ primitives)
 * - Related data grouped into cohesive value objects
 * - PatientDemographics encapsulates name data
 * - ContactInformation encapsulates phone and email
 * - InsuranceDetails encapsulates provider and policy
 * - Appointment encapsulates date and type
 * - Reduced coupling - changes to data structure isolated to value objects
 * - Self-documenting code - parameter types express intent
 * - Value objects enforce validation at construction time
 */
public class DataClumpsRefactored {

    /**
     * Register a new patient using value objects instead of scattered parameters.
     * Notice how the parameter list is reduced and more meaningful.
     */
    public String registerPatient(PatientDemographics demographics,
                                  ContactInformation contactInfo,
                                  InsuranceDetails insurance) {
        // Validation happens in value object constructors
        // No need to duplicate validation logic here

        Patient patient = new Patient(demographics, contactInfo, insurance);

        return String.format("Patient %s registered successfully",
                demographics.getFullName());
    }

    /**
     * Update contact information - cleaner signature with value objects.
     */
    public String updateContactInfo(PatientDemographics demographics,
                                    ContactInformation newContactInfo) {
        // Value objects ensure data is always valid

        return String.format("Contact info updated for %s",
                demographics.getFullName());
    }

    /**
     * Schedule appointment - organized parameters using value objects.
     */
    public String scheduleAppointment(PatientDemographics demographics,
                                      ContactInformation contactInfo,
                                      Appointment appointment) {
        // All validation handled by value objects

        String message = String.format("Appointment scheduled for %s on %s for %s",
                demographics.getFullName(),
                appointment.getDate(),
                appointment.getType());

        // Value objects can have behavior - contact info sends notifications
        contactInfo.sendEmailConfirmation("Appointment Confirmation", message);
        contactInfo.sendSmsConfirmation(message);

        return message;
    }

    /**
     * Get patient summary - clean and organized with value objects.
     */
    public String getPatientSummary(PatientDemographics demographics,
                                    ContactInformation contactInfo,
                                    InsuranceDetails insurance) {
        // Value objects provide formatted output
        return String.format("Patient: %s\nContact: %s, %s\nInsurance: %s",
                demographics.getFullName(),
                contactInfo.getPhone(),
                contactInfo.getEmail(),
                insurance.getFormattedCoverage());
    }
}
