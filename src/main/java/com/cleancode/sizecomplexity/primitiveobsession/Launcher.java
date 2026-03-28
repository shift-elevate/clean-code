package com.cleancode.sizecomplexity.primitiveobsession;

import com.cleancode.domain.Email;
import com.cleancode.domain.Money;
import com.cleancode.domain.PhoneNumber;
import com.cleancode.domain.UserStatus;
import com.cleancode.domain.ZipCode;

/**
 * Launcher class for Primitive Obsession code smell demonstration.
 * Demonstrates the Primitive Obsession code smell and its refactored solution.
 */
public class Launcher {

    public static void main(String[] args) {
        System.out.println("🐛 PRIMITIVE OBSESSION CODE SMELL DEMONSTRATION");
        System.out.println("===============================================");

        // Demonstrate the code smell
        demonstrateCodeSmell();

        System.out.println("\n" + "=".repeat(50) + "\n");

        // Demonstrate the refactored solution
        demonstrateRefactoredSolution();
    }

    private static void demonstrateCodeSmell() {
        System.out.println("❌ PRIMITIVE OBSESSION CODE SMELL");
        System.out.println("Problem: Using primitive types for domain concepts\n");

        // Using primitive types - no validation at construction
        PrimitiveObsessionSmell user1 = new PrimitiveObsessionSmell(
                "john.doe@example.com",
                "+14155552671",
                "94105",
                75000.00,
                "ACTIVE"
        );

        System.out.println("Valid user created:");
        System.out.println(user1);
        System.out.println("Email valid? " + user1.isValidEmail());
        System.out.println("Phone valid? " + user1.isValidPhoneNumber());
        System.out.println("ZipCode valid? " + user1.isValidZipCode());
        System.out.println("Is active? " + user1.isActive());

        System.out.println("\n--- Issues with Primitive Obsession ---");

        // Problem 1: Can create invalid users - no validation at construction
        PrimitiveObsessionSmell invalidUser = new PrimitiveObsessionSmell(
                "not-an-email",           // Invalid email
                "123",                    // Invalid phone
                "abc",                    // Invalid zip
                -5000.00,                 // Negative salary
                "ZOMBIE"                  // Invalid status
        );

        System.out.println("\nInvalid user created (no compile-time protection):");
        System.out.println(invalidUser);
        System.out.println("Email valid? " + invalidUser.isValidEmail());
        System.out.println("Phone valid? " + invalidUser.isValidPhoneNumber());
        System.out.println("ZipCode valid? " + invalidUser.isValidZipCode());
        System.out.println("Is active? " + invalidUser.isActive());

        // Problem 2: Validation logic scattered across multiple methods
        System.out.println("\n⚠️  Validation must be called manually and can be forgotten!");
    }

    private static void demonstrateRefactoredSolution() {
        System.out.println("✅ PRIMITIVE OBSESSION REFACTORED");
        System.out.println("Solution: Replace primitives with meaningful value objects\n");

        // Using value objects - validation happens at construction
        PrimitiveObsessionRefactored user1 = new PrimitiveObsessionRefactored(
                new Email("jane.smith@example.com"),
                new PhoneNumber("+14155552672"),
                new ZipCode("94105"),
                new Money(85000.00),
                UserStatus.ACTIVE
        );

        System.out.println("Valid user created:");
        System.out.println(user1);
        System.out.println("Is active? " + user1.isActive());

        System.out.println("\n--- Benefits of Value Objects ---");

        // Benefit 1: Invalid objects cannot be created
        System.out.println("\n✅ Compile-time type safety:");
        System.out.println("   - Cannot pass wrong type (e.g., email as phone number)");
        System.out.println("   - IDE autocomplete helps with correct usage");

        System.out.println("\n✅ Validation at construction:");
        try {
            new Email("not-an-email");
        } catch (IllegalArgumentException e) {
            System.out.println("   - Invalid email rejected: " + e.getMessage());
        }

        try {
            new PhoneNumber("123");
        } catch (IllegalArgumentException e) {
            System.out.println("   - Invalid phone rejected: " + e.getMessage());
        }

        try {
            new ZipCode("abc");
        } catch (IllegalArgumentException e) {
            System.out.println("   - Invalid zip code rejected: " + e.getMessage());
        }

        try {
            new Money(-5000.00);
        } catch (IllegalArgumentException e) {
            System.out.println("   - Negative salary rejected: " + e.getMessage());
        }

        System.out.println("\n✅ Rich domain model:");
        Email email = new Email("support@example.com");
        System.out.println("   - Email domain extraction: " + email.getDomain());

        Money salary = new Money(90000.00);
        System.out.println("   - Money formatting: " + salary.getFormattedAmount());

        PhoneNumber phone = new PhoneNumber("+14155552673");
        System.out.println("   - Country code extraction: " + phone.getCountryCode());

        System.out.println("\n✅ Self-documenting code:");
        System.out.println("   - Parameter types clearly express intent");
        System.out.println("   - No confusion about what each parameter represents");
    }
}
