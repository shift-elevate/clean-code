package com.cleancode.sizecomplexity;

import com.cleancode.sizecomplexity.longparameterlist.LongParameterListSmell;
import com.cleancode.sizecomplexity.longparameterlist.LongParameterListRefactored;
import com.cleancode.domain.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for Long Parameter List code smell and refactoring examples.
 * Demonstrates the problems with long parameter lists and the benefits of Introduce Parameter Object.
 */
public class LongParameterListTest {
    
    private LongParameterListSmell smellProcessor;
    private LongParameterListRefactored refactoredProcessor;
    
    @BeforeEach
    void setUp() {
        smellProcessor = new LongParameterListSmell();
        refactoredProcessor = new LongParameterListRefactored();
    }
    
    @Test
    void testLongParameterListSmell_CreateUser() {
        // Test the problematic long parameter list method
        User user = smellProcessor.createUser(
            "John", 
            "Doe", 
            "john@example.com", 
            "555-123-4567", 
            "123 Main St", 
            "New York", 
            "NY", 
            "10001", 
            "USA",
            LocalDate.of(1990, 5, 15),
            "password123",
            true
        );
        
        assertNotNull(user);
        assertEquals("John", user.getFirstName());
        assertEquals("Doe", user.getLastName());
        assertEquals("john@example.com", user.getEmail());
        assertTrue(user.isActive());
    }
    
    @Test
    void testLongParameterListRefactored_CreateUser() {
        // Test the refactored solution using parameter objects
        LongParameterListRefactored.Address address = new LongParameterListRefactored.Address(
            "456 Oak Ave", "Los Angeles", "CA", "90210", "USA"
        );
        
        LongParameterListRefactored.UserRegistrationData userData = 
            new LongParameterListRefactored.UserRegistrationData(
                "Jane", 
                "Smith", 
                "jane@example.com", 
                "555-987-6543", 
                address,
                LocalDate.of(1985, 8, 20),
                "password456",
                false
            );
        
        User user = refactoredProcessor.createUser(userData);
        
        assertNotNull(user);
        assertEquals("Jane", user.getFirstName());
        assertEquals("Smith", user.getLastName());
        assertEquals("jane@example.com", user.getEmail());
        assertFalse(user.isActive());
    }
    
    @Test
    void testLongParameterListSmell_ValidationFailure() {
        // Test validation failure with long parameter list
        assertThrows(IllegalArgumentException.class, () -> {
            smellProcessor.createUser(
                null, // Invalid first name
                "Doe", 
                "john@example.com", 
                "555-123-4567", 
                "123 Main St", 
                "New York", 
                "NY", 
                "10001", 
                "USA",
                LocalDate.of(1990, 5, 15),
                "password123",
                true
            );
        });
    }
    
    @Test
    void testLongParameterListRefactored_ValidationFailure() {
        // Test validation failure with parameter objects
        LongParameterListRefactored.Address address = new LongParameterListRefactored.Address(
            "456 Oak Ave", "Los Angeles", "CA", "90210", "USA"
        );
        
        LongParameterListRefactored.UserRegistrationData userData = 
            new LongParameterListRefactored.UserRegistrationData(
                null, // Invalid first name
                "Smith", 
                "jane@example.com", 
                "555-987-6543", 
                address,
                LocalDate.of(1985, 8, 20),
                "password456",
                false
            );
        
        assertThrows(IllegalArgumentException.class, () -> {
            refactoredProcessor.createUser(userData);
        });
    }
    
    @Test
    void testParameterObjectBenefits() {
        // Demonstrate the benefits of parameter objects
        
        // With long parameter list - hard to read and error-prone
        User user1 = smellProcessor.createUser(
            "Bob", 
            "Wilson", 
            "bob@example.com", 
            "555-111-2222", 
            "789 Pine St", 
            "San Francisco", 
            "CA", 
            "94102", 
            "USA",
            LocalDate.of(1980, 3, 10),
            "password789",
            true
        );
        
        // With parameter objects - clear and readable
        LongParameterListRefactored.Address address = new LongParameterListRefactored.Address(
            "789 Pine St", "San Francisco", "CA", "94102", "USA"
        );
        
        LongParameterListRefactored.UserRegistrationData userData = 
            new LongParameterListRefactored.UserRegistrationData(
                "Bob", 
                "Wilson", 
                "bob@example.com", 
                "555-111-2222", 
                address,
                LocalDate.of(1980, 3, 10),
                "password789",
                true
            );
        
        User user2 = refactoredProcessor.createUser(userData);
        
        // Both should produce the same result
        assertEquals(user1.getFirstName(), user2.getFirstName());
        assertEquals(user1.getLastName(), user2.getLastName());
        assertEquals(user1.getEmail(), user2.getEmail());
        assertEquals(user1.isActive(), user2.isActive());
    }
}
