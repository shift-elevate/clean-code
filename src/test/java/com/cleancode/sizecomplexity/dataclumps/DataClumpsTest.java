package com.cleancode.sizecomplexity.dataclumps;

import com.cleancode.domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for Data Clumps code smell and its refactored solution.
 */
@DisplayName("Data Clumps Code Smell Tests")
class DataClumpsTest {

    @Nested
    @DisplayName("Data Clumps Smell Tests")
    class DataClumpsSmellTests {
        private DataClumpsSmell patientManager;

        @BeforeEach
        void setUp() {
            patientManager = new DataClumpsSmell();
        }

        @Test
        @DisplayName("Should register patient with all required parameters")
        void shouldRegisterPatient() {
            String result = patientManager.registerPatient(
                    "John", "Doe",
                    "555-123-4567", "john.doe@email.com",
                    "Blue Cross", "BCBS123456"
            );

            assertTrue(result.contains("John Doe"));
            assertTrue(result.contains("registered successfully"));
        }

        @Test
        @DisplayName("Should throw exception when firstName is null during registration")
        void shouldThrowExceptionWhenFirstNameIsNull() {
            Exception exception = assertThrows(IllegalArgumentException.class, () ->
                    patientManager.registerPatient(
                            null, "Doe",
                            "555-123-4567", "john.doe@email.com",
                            "Blue Cross", "BCBS123456"
                    )
            );
            assertEquals("First name is required", exception.getMessage());
        }

        @Test
        @DisplayName("Should throw exception when email is null during registration")
        void shouldThrowExceptionWhenEmailIsNull() {
            Exception exception = assertThrows(IllegalArgumentException.class, () ->
                    patientManager.registerPatient(
                            "John", "Doe",
                            "555-123-4567", null,
                            "Blue Cross", "BCBS123456"
                    )
            );
            assertEquals("Email is required", exception.getMessage());
        }

        @Test
        @DisplayName("Should update contact information")
        void shouldUpdateContactInfo() {
            String result = patientManager.updateContactInfo(
                    "John", "Doe",
                    "555-111-2222", "john.new@email.com"
            );

            assertTrue(result.contains("John Doe"));
            assertTrue(result.contains("updated"));
        }

        @Test
        @DisplayName("Should schedule appointment with all parameters")
        void shouldScheduleAppointment() {
            String result = patientManager.scheduleAppointment(
                    "John", "Doe",
                    "555-123-4567", "john.doe@email.com",
                    "2024-03-15 10:00 AM", "Annual Checkup"
            );

            assertTrue(result.contains("John Doe"));
            assertTrue(result.contains("2024-03-15 10:00 AM"));
            assertTrue(result.contains("Annual Checkup"));
        }

        @Test
        @DisplayName("Should throw exception when appointment date is null")
        void shouldThrowExceptionWhenAppointmentDateIsNull() {
            Exception exception = assertThrows(IllegalArgumentException.class, () ->
                    patientManager.scheduleAppointment(
                            "John", "Doe",
                            "555-123-4567", "john.doe@email.com",
                            null, "Annual Checkup"
                    )
            );
            assertEquals("Appointment date is required", exception.getMessage());
        }

        @Test
        @DisplayName("Should get patient summary with all details")
        void shouldGetPatientSummary() {
            String summary = patientManager.getPatientSummary(
                    "John", "Doe",
                    "555-123-4567", "john.doe@email.com",
                    "Blue Cross", "BCBS123456"
            );

            assertTrue(summary.contains("John Doe"));
            assertTrue(summary.contains("555-123-4567"));
            assertTrue(summary.contains("john.doe@email.com"));
            assertTrue(summary.contains("Blue Cross"));
            assertTrue(summary.contains("BCBS123456"));
        }
    }

    @Nested
    @DisplayName("Data Clumps Refactored Tests")
    class DataClumpsRefactoredTests {
        private DataClumpsRefactored patientManager;
        private PatientDemographics demographics;
        private ContactInformation contactInfo;
        private InsuranceDetails insurance;

        @BeforeEach
        void setUp() {
            patientManager = new DataClumpsRefactored();
            demographics = new PatientDemographics("John", "Doe");
            contactInfo = new ContactInformation("555-123-4567", "john.doe@email.com");
            insurance = new InsuranceDetails("Blue Cross", "BCBS123456");
        }

        @Test
        @DisplayName("Should register patient with value objects")
        void shouldRegisterPatient() {
            String result = patientManager.registerPatient(demographics, contactInfo, insurance);

            assertTrue(result.contains("John Doe"));
            assertTrue(result.contains("registered successfully"));
        }

        @Test
        @DisplayName("Should benefit from value object validation at construction")
        void shouldBenefitFromValueObjectValidation() {
            // Value objects validate at construction time
            Exception exception = assertThrows(IllegalArgumentException.class, () ->
                    new PatientDemographics(null, "Doe")
            );
            assertTrue(exception.getMessage().contains("First name cannot be null"));
        }

        @Test
        @DisplayName("Should update contact information with value objects")
        void shouldUpdateContactInfo() {
            ContactInformation newContact = new ContactInformation("555-111-2222", "john.new@email.com");
            String result = patientManager.updateContactInfo(demographics, newContact);

            assertTrue(result.contains("John Doe"));
            assertTrue(result.contains("updated"));
        }

        @Test
        @DisplayName("Should schedule appointment with value objects")
        void shouldScheduleAppointment() {
            Appointment appointment = new Appointment("2024-03-15 10:00 AM", "Annual Checkup");
            String result = patientManager.scheduleAppointment(demographics, contactInfo, appointment);

            assertTrue(result.contains("John Doe"));
            assertTrue(result.contains("2024-03-15 10:00 AM"));
            assertTrue(result.contains("Annual Checkup"));
        }

        @Test
        @DisplayName("Should prevent invalid appointment creation")
        void shouldPreventInvalidAppointment() {
            Exception exception = assertThrows(IllegalArgumentException.class, () ->
                    new Appointment(null, "Annual Checkup")
            );
            assertEquals("Appointment date cannot be null or empty", exception.getMessage());
        }

        @Test
        @DisplayName("Should get patient summary with value objects")
        void shouldGetPatientSummary() {
            String summary = patientManager.getPatientSummary(demographics, contactInfo, insurance);

            assertTrue(summary.contains("John Doe"));
            assertTrue(summary.contains("555-123-4567"));
            assertTrue(summary.contains("john.doe@email.com"));
            assertTrue(summary.contains("Blue Cross"));
        }

        @Test
        @DisplayName("Should demonstrate value object immutability and reusability")
        void shouldDemonstrateValueObjectReusability() {
            // Same value objects can be reused across multiple operations
            String result1 = patientManager.registerPatient(demographics, contactInfo, insurance);
            String result2 = patientManager.getPatientSummary(demographics, contactInfo, insurance);

            assertTrue(result1.contains("John Doe"));
            assertTrue(result2.contains("John Doe"));
        }

        @Test
        @DisplayName("Should demonstrate type safety with value objects")
        void shouldDemonstrateTypeSafety() {
            // Compile-time type safety - can't pass wrong object type
            // This test demonstrates the benefit by showing correct usage
            Appointment appointment = new Appointment("2024-03-15 10:00 AM", "Annual Checkup");

            assertNotNull(appointment);
            assertEquals("2024-03-15 10:00 AM", appointment.getDate());
            assertEquals("Annual Checkup", appointment.getType());
        }
    }
}
