package com.cleancode.dependencyproblems.circulardependencies;

import com.cleancode.domain.User;
import com.cleancode.domain.Order;
import java.util.List;

/**
 * Launcher class for Circular Dependencies code smell demonstration.
 * Demonstrates the Circular Dependencies code smell and its refactored solution.
 */
public class Launcher {
    
    public static void main(String[] args) {
        System.out.println("🔄 CIRCULAR DEPENDENCIES CODE SMELL DEMONSTRATION");
        System.out.println("================================================");
        
        // Demonstrate the code smell
        demonstrateCodeSmell();
        
        System.out.println("\n" + "=".repeat(50) + "\n");
        
        // Demonstrate the refactored solution
        demonstrateRefactoredSolution();
    }
    
    private static void demonstrateCodeSmell() {
        System.out.println("❌ CIRCULAR DEPENDENCIES CODE SMELL");
        
        // Show the problem with circular dependencies
        CircularDependenciesSmell.demonstrateCircularDependencyProblem();
    }
    
    private static void demonstrateRefactoredSolution() {
        System.out.println("✅ CIRCULAR DEPENDENCIES REFACTORED");
        
        // Show the refactored solution working
        CircularDependenciesRefactored.demonstrateRefactoredSolution();
    }
}
