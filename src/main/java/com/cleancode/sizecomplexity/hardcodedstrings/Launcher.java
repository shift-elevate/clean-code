package com.cleancode.sizecomplexity.hardcodedstrings;

/**
 * Launcher for Hardcoded Strings code smell demonstration.
 *
 * This launcher demonstrates:
 * - The Hardcoded Strings code smell (scattered string literals)
 * - The Replace Magic String with Named Constant refactoring (typesafe enums)
 */
public class Launcher {

    public static void main(String[] args) {
        System.out.println("📝 HARDCODED STRINGS CODE SMELL DEMONSTRATION");
        System.out.println("==============================================");

        // Demonstrate the code smell
        demonstrateCodeSmell();

        System.out.println("\n" + "=".repeat(50) + "\n");

        // Demonstrate the refactored solution
        demonstrateRefactoredSolution();
    }

    private static void demonstrateCodeSmell() {
        System.out.println("❌ HARDCODED STRINGS CODE SMELL");
        System.out.println("Problem: Literal strings scattered throughout code\n");

        HardcodedStringsSmell userService = new HardcodedStringsSmell();

        // Create user with hardcoded role string
        System.out.println("Creating user with hardcoded role \"USER\":");
        String result1 = userService.createUser("johndoe", "john@example.com", "USER");
        System.out.println(result1);

        // Update status with hardcoded status string
        System.out.println("\nUpdating status with hardcoded status \"SUSPENDED\":");
        String result2 = userService.updateUserStatus("johndoe", "SUSPENDED");
        System.out.println(result2);

        // Check admin role
        System.out.println("\nChecking admin role with hardcoded string:");
        System.out.println("Is ADMIN an admin? " + userService.isAdmin("ADMIN"));
        System.out.println("Is USER an admin? " + userService.isAdmin("USER"));

        // Demonstrate the typo problem
        System.out.println("\n--- Issues with Hardcoded Strings ---");
        System.out.println("⚠️  Typos like \"ADMN\" or \"ACTVE\" fail silently");
        System.out.println("⚠️  Same strings repeated in multiple places");
        System.out.println("⚠️  No compile-time safety for invalid values");
        System.out.println("⚠️  Difficult to find all usages when changing values");
        System.out.println("⚠️  Business rules hidden in string comparisons");
    }

    private static void demonstrateRefactoredSolution() {
        System.out.println("✅ HARDCODED STRINGS REFACTORED");
        System.out.println("Solution: Replace with typesafe enums\n");

        HardcodedStringsRefactored userService = new HardcodedStringsRefactored();

        // Create user with typesafe role enum
        System.out.println("Creating user with typesafe UserRole.USER:");
        String result1 = userService.createUser("johndoe", "john@example.com", UserRole.USER.getValue());
        System.out.println(result1);

        // Update status with typesafe status enum
        System.out.println("\nUpdating status with typesafe UserStatus.SUSPENDED:");
        String result2 = userService.updateUserStatus("johndoe", UserStatus.SUSPENDED.getValue());
        System.out.println(result2);

        // Check admin role with typesafe enum
        System.out.println("\nChecking admin role with typesafe enum:");
        System.out.println("Is ADMIN an admin? " + userService.isAdmin(UserRole.ADMIN.getValue()));
        System.out.println("Is USER an admin? " + userService.isAdmin(UserRole.USER.getValue()));

        // Demonstrate enum validation
        System.out.println("\nDemonstrating enum validation:");
        try {
            UserRole.fromString("INVALID_ROLE");
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid role caught: " + e.getMessage());
        }

        System.out.println("\n--- Benefits of Typesafe Enums ---");
        System.out.println("✅ Compile-time type safety");
        System.out.println("✅ IDE auto-completion support");
        System.out.println("✅ No typos possible - \"ADMN\" would be compile error");
        System.out.println("✅ Centralized definitions - change once, applies everywhere");
        System.out.println("✅ Self-documenting code - enum names express intent");
        System.out.println("✅ Easy to find all usages with IDE refactoring tools");
    }
}
