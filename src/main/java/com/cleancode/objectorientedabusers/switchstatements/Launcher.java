package com.cleancode.objectorientedabusers.switchstatements;

/**
 * Launcher class for Switch Statements code smell demonstration.
 * Demonstrates the Switch Statements code smell and its refactored solution.
 */
public class Launcher {
    
    public static void main(String[] args) {
        System.out.println("🐛 SWITCH STATEMENTS CODE SMELL DEMONSTRATION");
        System.out.println("=============================================");
        
        // Demonstrate the code smell
        demonstrateCodeSmell();
        
        System.out.println("\n" + "=".repeat(50) + "\n");
        
        // Demonstrate the refactored solution
        demonstrateRefactoredSolution();
    }
    
    private static void demonstrateCodeSmell() {
        System.out.println("❌ SWITCH STATEMENTS CODE SMELL");
        
        SwitchStatementsSmell processor = new SwitchStatementsSmell();
        
        System.out.println("Processing Regular Customer:");
        processor.processCustomer("REGULAR", 100.0);
        
        System.out.println("\nProcessing Premium Customer:");
        processor.processCustomer("PREMIUM", 200.0);
        
        System.out.println("\nProcessing VIP Customer:");
        processor.processCustomer("VIP", 300.0);
    }
    
    private static void demonstrateRefactoredSolution() {
        System.out.println("✅ SWITCH STATEMENTS REFACTORED");
        
        SwitchStatementsRefactored processor = new SwitchStatementsRefactored();
        
        System.out.println("Processing Regular Customer:");
        SwitchStatementsRefactored.RegularCustomer regularCustomer = new SwitchStatementsRefactored.RegularCustomer("John Doe");
        processor.processCustomer(regularCustomer, 100.0);
        
        System.out.println("\nProcessing Premium Customer:");
        SwitchStatementsRefactored.PremiumCustomer premiumCustomer = new SwitchStatementsRefactored.PremiumCustomer("Jane Smith");
        processor.processCustomer(premiumCustomer, 200.0);
        
        System.out.println("\nProcessing VIP Customer:");
        SwitchStatementsRefactored.VipCustomer vipCustomer = new SwitchStatementsRefactored.VipCustomer("Bob Wilson");
        processor.processCustomer(vipCustomer, 300.0);
    }
}
