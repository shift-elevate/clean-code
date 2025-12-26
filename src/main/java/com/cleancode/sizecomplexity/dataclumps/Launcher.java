package com.cleancode.sizecomplexity.dataclumps;

import com.cleancode.domain.*;

/**
 * Launcher for Data Clumps code smell demonstration.
 *
 * This launcher demonstrates:
 * - The Data Clumps code smell (scattered parameters)
 * - The Extract Class refactoring solution (value objects)
 */
public class Launcher {

    public static void main(String[] args) {
        System.out.println("🐛 DATA CLUMPS CODE SMELL DEMONSTRATION");
        System.out.println("========================================");

        // Demonstrate the code smell
        demonstrateCodeSmell();

        System.out.println("\n" + "=".repeat(50) + "\n");

        // Demonstrate the refactored solution
        demonstrateRefactoredSolution();
    }

    private static void demonstrateCodeSmell() {
        System.out.println("❌ DATA CLUMPS CODE SMELL");
        System.out.println("Problem: Same groups of parameters appear repeatedly\n");

        DataClumpsSmell patientManager = new DataClumpsSmell();

        // Register patient - 6 scattered parameters
        System.out.println("Registering patient with 6 parameters:");
        String result1 = patientManager.registerPatient(
                "John", "Doe",
                "555-123-4567", "john.doe@email.com",
                "Blue Cross Blue Shield", "BCBS123456"
        );
        System.out.println(result1);

        // Update contact - demographics + contact data clumps
        System.out.println("\nUpdating contact info with 4 parameters:");
        String result2 = patientManager.updateContactInfo(
                "John", "Doe",
                "555-111-2222", "john.doe.new@email.com"
        );
        System.out.println(result2);

        // Schedule appointment - demographics + contact + appointment data clumps
        System.out.println("\nScheduling appointment with 6 parameters:");
        String result3 = patientManager.scheduleAppointment(
                "John", "Doe",
                "555-111-2222", "john.doe.new@email.com",
                "2024-03-15 10:00 AM", "Annual Checkup"
        );
        System.out.println(result3);

        // Get summary - all data clumps together
        System.out.println("\nPatient summary:");
        String summary = patientManager.getPatientSummary(
                "John", "Doe",
                "555-111-2222", "john.doe.new@email.com",
                "Blue Cross Blue Shield", "BCBS123456"
        );
        System.out.println(summary);

        System.out.println("\n--- Issues with Data Clumps ---");
        System.out.println("⚠️  Long parameter lists (4-6 parameters per method)");
        System.out.println("⚠️  Same parameter groups repeated across methods");
        System.out.println("⚠️  Easy to pass parameters in wrong order");
        System.out.println("⚠️  Validation logic duplicated in each method");
        System.out.println("⚠️  Changes to data structure require updating all methods");
    }

    private static void demonstrateRefactoredSolution() {
        System.out.println("✅ DATA CLUMPS REFACTORED");
        System.out.println("Solution: Group related data into meaningful value objects\n");

        DataClumpsRefactored patientManager = new DataClumpsRefactored();

        // Create value objects once - reuse everywhere
        PatientDemographics demographics = new PatientDemographics("John", "Doe");
        ContactInformation contactInfo = new ContactInformation("555-123-4567", "john.doe@email.com");
        InsuranceDetails insurance = new InsuranceDetails("Blue Cross Blue Shield", "BCBS123456");

        // Register patient - 3 meaningful objects instead of 6 primitives
        System.out.println("Registering patient with 3 value objects:");
        String result1 = patientManager.registerPatient(demographics, contactInfo, insurance);
        System.out.println(result1);

        // Update contact - 2 objects instead of 4 primitives
        System.out.println("\nUpdating contact info with 2 value objects:");
        ContactInformation updatedContact = new ContactInformation("555-111-2222", "john.doe.new@email.com");
        String result2 = patientManager.updateContactInfo(demographics, updatedContact);
        System.out.println(result2);

        // Schedule appointment - 3 objects instead of 6 primitives
        System.out.println("\nScheduling appointment with 3 value objects:");
        Appointment appointment = new Appointment("2024-03-15 10:00 AM", "Annual Checkup");
        String result3 = patientManager.scheduleAppointment(demographics, updatedContact, appointment);
        System.out.println(result3);

        // Get summary - clean and organized
        System.out.println("\nPatient summary:");
        String summary = patientManager.getPatientSummary(demographics, updatedContact, insurance);
        System.out.println(summary);

        System.out.println("\n--- Benefits of Extract Class ---");
        System.out.println("✅ Reduced parameter lists: 3 objects instead of 6 primitives");
        System.out.println("✅ Related data grouped together logically");
        System.out.println("✅ Type-safe - can't pass wrong object type");
        System.out.println("✅ Validation centralized in value object constructors");
        System.out.println("✅ Self-documenting code - parameter types express intent");
        System.out.println("✅ Value objects can have meaningful behavior");
        System.out.println("✅ Changes to data structure isolated to value objects");
    }
}
