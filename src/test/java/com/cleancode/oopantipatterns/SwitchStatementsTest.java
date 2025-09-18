package com.cleancode.oopantipatterns;

import com.cleancode.oopantipatterns.switchstatements.SwitchStatementsSmell;
import com.cleancode.oopantipatterns.switchstatements.SwitchStatementsRefactored;
import com.cleancode.oopantipatterns.switchstatements.SwitchStatementsRefactored.Customer;
import com.cleancode.oopantipatterns.switchstatements.SwitchStatementsRefactored.RegularCustomer;
import com.cleancode.oopantipatterns.switchstatements.SwitchStatementsRefactored.PremiumCustomer;
import com.cleancode.oopantipatterns.switchstatements.SwitchStatementsRefactored.VipCustomer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Switch Statements Code Smell Tests")
class SwitchStatementsTest {
    
    private SwitchStatementsSmell switchStatementsSmell;
    private SwitchStatementsRefactored switchStatementsRefactored;
    
    @BeforeEach
    void setUp() {
        switchStatementsSmell = new SwitchStatementsSmell();
        switchStatementsRefactored = new SwitchStatementsRefactored();
    }
    
    @Nested
    @DisplayName("Switch Statements Smell Tests")
    class SwitchStatementsSmellTests {
        
        @Test
        @DisplayName("Should calculate discount for different customer types")
        void shouldCalculateDiscountForDifferentCustomerTypes() {
            assertEquals(5.0, switchStatementsSmell.calculateDiscount("REGULAR", 100.0), 0.01);
            assertEquals(10.0, switchStatementsSmell.calculateDiscount("PREMIUM", 100.0), 0.01);
            assertEquals(15.0, switchStatementsSmell.calculateDiscount("VIP", 100.0), 0.01);
            assertEquals(0.0, switchStatementsSmell.calculateDiscount("UNKNOWN", 100.0), 0.01);
        }
        
        @Test
        @DisplayName("Should get welcome message for different customer types")
        void shouldGetWelcomeMessageForDifferentCustomerTypes() {
            assertEquals("Welcome! Enjoy your shopping.", switchStatementsSmell.getWelcomeMessage("REGULAR"));
            assertEquals("Welcome back! You have premium benefits.", switchStatementsSmell.getWelcomeMessage("PREMIUM"));
            assertEquals("Welcome VIP! Exclusive offers await you.", switchStatementsSmell.getWelcomeMessage("VIP"));
            assertEquals("Welcome!", switchStatementsSmell.getWelcomeMessage("UNKNOWN"));
        }
    }
    
    @Nested
    @DisplayName("Switch Statements Refactored Tests")
    class SwitchStatementsRefactoredTests {
        
        @Test
        @DisplayName("Should calculate discount using polymorphism")
        void shouldCalculateDiscountUsingPolymorphism() {
            Customer regular = new RegularCustomer("John Doe");
            Customer premium = new PremiumCustomer("Jane Smith");
            Customer vip = new VipCustomer("Bob Wilson");
            
            assertEquals(5.0, switchStatementsRefactored.calculateDiscount(regular, 100.0), 0.01);
            assertEquals(10.0, switchStatementsRefactored.calculateDiscount(premium, 100.0), 0.01);
            assertEquals(15.0, switchStatementsRefactored.calculateDiscount(vip, 100.0), 0.01);
        }
        
        @Test
        @DisplayName("Should get welcome message using polymorphism")
        void shouldGetWelcomeMessageUsingPolymorphism() {
            Customer regular = new RegularCustomer("John Doe");
            Customer premium = new PremiumCustomer("Jane Smith");
            Customer vip = new VipCustomer("Bob Wilson");
            
            assertEquals("Welcome! Enjoy your shopping.", switchStatementsRefactored.getWelcomeMessage(regular));
            assertEquals("Welcome back! You have premium benefits.", switchStatementsRefactored.getWelcomeMessage(premium));
            assertEquals("Welcome VIP! Exclusive offers await you.", switchStatementsRefactored.getWelcomeMessage(vip));
        }
        
        @Test
        @DisplayName("Should demonstrate extensibility with new customer type")
        void shouldDemonstrateExtensibilityWithNewCustomerType() {
            Customer newCustomerType = new Customer("Test Customer") {
                @Override
                public double calculateDiscount(double amount) {
                    return amount * 0.20;
                }
                
                @Override
                public String getWelcomeMessage() {
                    return "Welcome new customer type!";
                }
            };
            
            assertEquals(20.0, switchStatementsRefactored.calculateDiscount(newCustomerType, 100.0), 0.01);
            assertEquals("Welcome new customer type!", switchStatementsRefactored.getWelcomeMessage(newCustomerType));
        }
    }
    
    @Test
    @DisplayName("Should produce same results for both approaches")
    void shouldProduceSameResultsForBothApproaches() {
        String[] customerTypes = {"REGULAR", "PREMIUM", "VIP"};
        double amount = 150.0;
        
        for (String customerType : customerTypes) {
            double switchDiscount = switchStatementsSmell.calculateDiscount(customerType, amount);
            String switchMessage = switchStatementsSmell.getWelcomeMessage(customerType);
            
            Customer customer = createCustomerFromType(customerType, "Test Customer");
            double polyDiscount = switchStatementsRefactored.calculateDiscount(customer, amount);
            String polyMessage = switchStatementsRefactored.getWelcomeMessage(customer);
            
            assertEquals(switchDiscount, polyDiscount, 0.01);
            assertEquals(switchMessage, polyMessage);
        }
    }
    
    private Customer createCustomerFromType(String customerType, String name) {
        switch (customerType) {
            case "REGULAR":
                return new RegularCustomer(name);
            case "PREMIUM":
                return new PremiumCustomer(name);
            case "VIP":
                return new VipCustomer(name);
            default:
                throw new IllegalArgumentException("Unknown customer type: " + customerType);
        }
    }
}
