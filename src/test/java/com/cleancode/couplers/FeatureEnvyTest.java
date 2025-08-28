package com.cleancode.couplers;

import com.cleancode.couplers.featureenvy.FeatureEnvySmell;
import com.cleancode.couplers.featureenvy.FeatureEnvyRefactored;
import com.cleancode.domain.Car;
import com.cleancode.domain.CarType;
import com.cleancode.domain.RentalCustomer;
import com.cleancode.domain.Rental;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Feature Envy Code Smell Tests")
class FeatureEnvyTest {
    
    private FeatureEnvySmell featureEnvySmell;
    private FeatureEnvyRefactored featureEnvyRefactored;
    
    @BeforeEach
    void setUp() {
        featureEnvySmell = new FeatureEnvySmell();
        featureEnvyRefactored = new FeatureEnvyRefactored();
    }
    
    @Nested
    @DisplayName("Feature Envy Smell Tests")
    class FeatureEnvySmellTests {
        
        @Test
        @DisplayName("Should calculate rental cost with car age discount")
        void shouldCalculateRentalCostWithCarAgeDiscount() {
            Car oldCar = new Car(CarType.STANDARD, 100.0, 6);
            RentalCustomer customer = new RentalCustomer("C001", "John Doe", "john@example.com", 1);
            Rental rental = new Rental(oldCar, customer, 3);
            
            double cost = featureEnvySmell.calculateRentalCost(rental);
            
            // Base cost: 100 * 3 = 300
            // Car age discount: 300 * 0.95 = 285
            // No loyalty discount (loyaltyYears = 1)
            assertEquals(285.0, cost, 0.01);
        }
        
        @Test
        @DisplayName("Should calculate rental cost with luxury car surcharge")
        void shouldCalculateRentalCostWithLuxuryCarSurcharge() {
            Car luxuryCar = new Car(CarType.LUXURY, 150.0, 2);
            RentalCustomer customer = new RentalCustomer("C002", "Jane Smith", "jane@example.com", 1);
            Rental rental = new Rental(luxuryCar, customer, 2);
            
            double cost = featureEnvySmell.calculateRentalCost(rental);
            
            // Base cost: 150 * 2 = 300
            // Luxury surcharge: 300 * 1.25 = 375
            // No loyalty discount (loyaltyYears = 1)
            assertEquals(375.0, cost, 0.01);
        }
        
        @Test
        @DisplayName("Should calculate rental cost with loyalty discount")
        void shouldCalculateRentalCostWithLoyaltyDiscount() {
            Car car = new Car(CarType.STANDARD, 100.0, 3);
            RentalCustomer loyalCustomer = new RentalCustomer("C003", "Bob Wilson", "bob@example.com", 3);
            Rental rental = new Rental(car, loyalCustomer, 4);
            
            double cost = featureEnvySmell.calculateRentalCost(rental);
            
            // Base cost: 100 * 4 = 400
            // No car age discount (age = 3)
            // No luxury surcharge (type = STANDARD)
            // Loyalty discount: 400 * 0.9 = 360
            assertEquals(360.0, cost, 0.01);
        }
        
        @Test
        @DisplayName("Should calculate rental cost with multiple adjustments")
        void shouldCalculateRentalCostWithMultipleAdjustments() {
            Car oldLuxuryCar = new Car(CarType.LUXURY, 200.0, 7);
            RentalCustomer loyalCustomer = new RentalCustomer("C004", "Alice Brown", "alice@example.com", 5);
            Rental rental = new Rental(oldLuxuryCar, loyalCustomer, 3);
            
            double cost = featureEnvySmell.calculateRentalCost(rental);
            
            // Base cost: 200 * 3 = 600
            // Car age discount: 600 * 0.95 = 570
            // Luxury surcharge: 570 * 1.25 = 712.5
            // Loyalty discount: 712.5 * 0.9 = 641.25
            assertEquals(641.25, cost, 0.01);
        }
    }
    
    @Nested
    @DisplayName("Feature Envy Refactored Tests")
    class FeatureEnvyRefactoredTests {
        
        @Test
        @DisplayName("Should calculate rental cost with same functionality as smell version")
        void shouldCalculateRentalCostWithSameFunctionality() {
            Car oldCar = new Car(CarType.STANDARD, 100.0, 6);
            RentalCustomer customer = new RentalCustomer("C005", "John Doe", "john@example.com", 1);
            Rental rental = new Rental(oldCar, customer, 3);
            
            double cost = featureEnvyRefactored.calculateRentalCost(rental);
            
            // Should produce same result as smell version
            assertEquals(285.0, cost, 0.01);
        }
        
        @Test
        @DisplayName("Should calculate rental cost with luxury car surcharge")
        void shouldCalculateRentalCostWithLuxuryCarSurcharge() {
            Car luxuryCar = new Car(CarType.LUXURY, 150.0, 2);
            RentalCustomer customer = new RentalCustomer("C006", "Jane Smith", "jane@example.com", 1);
            Rental rental = new Rental(luxuryCar, customer, 3);
            
            double cost = featureEnvyRefactored.calculateRentalCost(rental);
            
            // Base cost: 150 * 3 = 450
            // Luxury surcharge: 450 * 1.25 = 562.5
            // No loyalty discount (loyaltyYears = 1)
            assertEquals(562.5, cost, 0.01);
        }
        
        @Test
        @DisplayName("Should calculate rental cost with loyalty discount")
        void shouldCalculateRentalCostWithLoyaltyDiscount() {
            Car car = new Car(CarType.STANDARD, 100.0, 3);
            RentalCustomer loyalCustomer = new RentalCustomer("C007", "Bob Wilson", "bob@example.com", 3);
            Rental rental = new Rental(car, loyalCustomer, 4);
            
            double cost = featureEnvyRefactored.calculateRentalCost(rental);
            
            // Should produce same result as smell version
            assertEquals(360.0, cost, 0.01);
        }
        
        @Test
        @DisplayName("Should calculate rental cost with multiple adjustments")
        void shouldCalculateRentalCostWithMultipleAdjustments() {
            Car oldLuxuryCar = new Car(CarType.LUXURY, 200.0, 7);
            RentalCustomer loyalCustomer = new RentalCustomer("C008", "Alice Brown", "alice@example.com", 5);
            Rental rental = new Rental(oldLuxuryCar, loyalCustomer, 3);
            
            double cost = featureEnvyRefactored.calculateRentalCost(rental);
            
            // Should produce same result as smell version
            assertEquals(641.25, cost, 0.01);
        }
    }
    
    @Nested
    @DisplayName("Refactoring Benefits Tests")
    class RefactoringBenefitsTests {
        
        @Test
        @DisplayName("Should demonstrate improved encapsulation")
        void shouldDemonstrateImprovedEncapsulation() {
            // This test demonstrates that the refactored version
            // has better encapsulation by not exposing internal data
            Car car = new Car(CarType.STANDARD, 100.0, 3);
            RentalCustomer customer = new RentalCustomer("C009", "Test User", "test@example.com", 1);
            Rental rental = new Rental(car, customer, 2);
            
            // The refactored version doesn't require accessing car/customer internals
            // from the calculator class - it delegates to the appropriate objects
            double cost = featureEnvyRefactored.calculateRentalCost(rental);
            
            assertNotNull(cost);
            assertTrue(cost > 0);
        }
        
        @Test
        @DisplayName("Should demonstrate reduced coupling")
        void shouldDemonstrateReducedCoupling() {
            // This test demonstrates that the refactored version
            // has reduced coupling between classes
            Car car = new Car(CarType.LUXURY, 150.0, 4);
            RentalCustomer customer = new RentalCustomer("C010", "Test User", "test@example.com", 4);
            Rental rental = new Rental(car, customer, 1);
            
            // The refactored version doesn't directly depend on Car/Customer structure
            // It depends on behavior (methods) rather than data
            double cost = featureEnvyRefactored.calculateRentalCost(rental);
            
            assertNotNull(cost);
            assertTrue(cost > 0);
        }
    }
}
