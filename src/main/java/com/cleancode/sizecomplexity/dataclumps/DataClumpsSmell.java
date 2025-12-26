package com.cleancode.sizecomplexity.dataclumps;

/**
 * CODE SMELL: Data Clumps
 *
 * This class demonstrates the Data Clumps code smell where the same group of
 * parameters appears together repeatedly across multiple methods.
 *
 * Problems:
 * - Long parameter lists (6+ parameters in some methods)
 * - Same groups of parameters repeated across methods
 * - firstName, lastName appear together everywhere
 * - phone, email appear together everywhere
 * - insuranceProvider, policyNumber appear together everywhere
 * - Tight coupling between methods due to shared parameter sets
 * - Changes to data structure require updating multiple method signatures
 */
public class DataClumpsSmell {

    /**
     * Register a new patient with scattered parameters.
     * Notice the long parameter list with data clumps.
     */
    public String registerPatient(String firstName, String lastName,
                                  String phone, String email,
                                  String insuranceProvider, String policyNumber) {
        // Validate demographics data clump
        if (firstName == null || firstName.trim().isEmpty()) {
            throw new IllegalArgumentException("First name is required");
        }
        if (lastName == null || lastName.trim().isEmpty()) {
            throw new IllegalArgumentException("Last name is required");
        }

        // Validate contact data clump
        if (phone == null || phone.trim().isEmpty()) {
            throw new IllegalArgumentException("Phone is required");
        }
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("Email is required");
        }

        // Validate insurance data clump
        if (insuranceProvider == null || insuranceProvider.trim().isEmpty()) {
            throw new IllegalArgumentException("Insurance provider is required");
        }
        if (policyNumber == null || policyNumber.trim().isEmpty()) {
            throw new IllegalArgumentException("Policy number is required");
        }

        return String.format("Patient %s %s registered successfully", firstName, lastName);
    }

    /**
     * Update contact information - same contact data clump appears again.
     */
    public String updateContactInfo(String firstName, String lastName,
                                    String newPhone, String newEmail) {
        // Validate demographics data clump (again!)
        if (firstName == null || firstName.trim().isEmpty()) {
            throw new IllegalArgumentException("First name is required");
        }
        if (lastName == null || lastName.trim().isEmpty()) {
            throw new IllegalArgumentException("Last name is required");
        }

        // Validate contact data clump (again!)
        if (newPhone == null || newPhone.trim().isEmpty()) {
            throw new IllegalArgumentException("Phone is required");
        }
        if (newEmail == null || newEmail.trim().isEmpty()) {
            throw new IllegalArgumentException("Email is required");
        }

        return String.format("Contact info updated for %s %s", firstName, lastName);
    }

    /**
     * Schedule appointment - demographics, contact, and appointment data clumps all together.
     */
    public String scheduleAppointment(String firstName, String lastName,
                                      String phone, String email,
                                      String appointmentDate, String appointmentType) {
        // Validate demographics data clump (yet again!)
        if (firstName == null || firstName.trim().isEmpty()) {
            throw new IllegalArgumentException("First name is required");
        }
        if (lastName == null || lastName.trim().isEmpty()) {
            throw new IllegalArgumentException("Last name is required");
        }

        // Validate appointment data clump
        if (appointmentDate == null || appointmentDate.trim().isEmpty()) {
            throw new IllegalArgumentException("Appointment date is required");
        }
        if (appointmentType == null || appointmentType.trim().isEmpty()) {
            throw new IllegalArgumentException("Appointment type is required");
        }

        String message = String.format("Appointment scheduled for %s %s on %s for %s",
                firstName, lastName, appointmentDate, appointmentType);

        // Send confirmations using contact data clump
        sendEmailConfirmation(email, "Appointment Confirmation", message);
        sendSmsConfirmation(phone, message);

        return message;
    }

    /**
     * Get patient summary - all data clumps appear together.
     */
    public String getPatientSummary(String firstName, String lastName,
                                    String phone, String email,
                                    String insuranceProvider, String policyNumber) {
        return String.format("Patient: %s %s\nContact: %s, %s\nInsurance: %s (Policy: %s)",
                firstName, lastName, phone, email, insuranceProvider, policyNumber);
    }

    // Helper methods that also suffer from data clumps
    private void sendEmailConfirmation(String email, String subject, String message) {
        System.out.println("Email sent to " + email + ": " + subject);
    }

    private void sendSmsConfirmation(String phone, String message) {
        System.out.println("SMS sent to " + phone + ": " + message);
    }
}
