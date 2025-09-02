package com.cleancode.bloaters;

import com.cleancode.bloaters.magicnumbers.MagicNumbersSmell;
import com.cleancode.bloaters.magicnumbers.MagicNumbersRefactored;
import com.cleancode.domain.Customer;
import com.cleancode.domain.Order;
import com.cleancode.domain.OrderItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Magic Numbers Code Smell Tests")
class MagicNumbersTest {
    
    private MagicNumbersSmell magicNumbersSmell;
    private MagicNumbersRefactored magicNumbersRefactored;
    
    @BeforeEach
    void setUp() {
        magicNumbersSmell = new MagicNumbersSmell();
        magicNumbersRefactored = new MagicNumbersRefactored();
    }
    
    @Nested
    @DisplayName("Magic Numbers Smell Tests")
    class MagicNumbersSmellTests {
        
        @Test
        @DisplayName("Should calculate discount with magic numbers")
        void shouldCalculateDiscountWithMagicNumbers() {
            Customer premiumCustomer = new Customer("C001", "John Doe", "john@example.com", true);
            Order order = new Order("O001", premiumCustomer);
            order.addItem(new OrderItem("P001", "Laptop", 999.99, 1));
            
            double discountedTotal = magicNumbersSmell.calculateDiscount(order);
            
            // 999.99 * 0.9 = 899.99 (10% premium discount)
            // 899.99 * 0.95 = 854.99 (5% bulk discount for orders over $100)
            assertEquals(854.99, discountedTotal, 0.01);
        }
        
        @Test
        @DisplayName("Should apply bulk order discount with magic numbers")
        void shouldApplyBulkOrderDiscountWithMagicNumbers() {
            Customer customer = new Customer("C002", "Jane Smith", "jane@example.com", false);
            Order order = new Order("O002", customer);
            order.addItem(new OrderItem("P002", "Monitor", 299.99, 1));
            
            double discountedTotal = magicNumbersSmell.calculateDiscount(order);
            
            // 299.99 * 0.95 = 284.99 (5% bulk discount for orders over $100)
            assertEquals(284.99, discountedTotal, 0.01);
        }
        
        @Test
        @DisplayName("Should check free shipping eligibility with magic numbers")
        void shouldCheckFreeShippingEligibilityWithMagicNumbers() {
            Customer customer = new Customer("C003", "Bob Johnson", "bob@example.com", false);
            Order order = new Order("O003", customer);
            order.addItem(new OrderItem("P003", "Keyboard", 89.99, 1));
            order.setWeight(5.0);
            
            boolean freeShipping = magicNumbersSmell.isEligibleForFreeShipping(order);
            
            // Order total: 89.99 >= 50.0 AND weight 5.0 <= 10.0
            // Both conditions are met, so free shipping should be eligible
            assertTrue(freeShipping);
        }
        
        @Test
        @DisplayName("Should calculate delivery days with magic numbers")
        void shouldCalculateDeliveryDaysWithMagicNumbers() {
            int standardDays = magicNumbersSmell.calculateDeliveryDays("STANDARD");
            int expressDays = magicNumbersSmell.calculateDeliveryDays("EXPRESS");
            int overnightDays = magicNumbersSmell.calculateDeliveryDays("OVERNIGHT");
            int defaultDays = magicNumbersSmell.calculateDeliveryDays("UNKNOWN");
            
            assertEquals(5, standardDays);
            assertEquals(2, expressDays);
            assertEquals(1, overnightDays);
            assertEquals(7, defaultDays);
        }
    }
    
    @Nested
    @DisplayName("Magic Numbers Refactored Tests")
    class MagicNumbersRefactoredTests {
        
        @Test
        @DisplayName("Should calculate discount with named constants")
        void shouldCalculateDiscountWithNamedConstants() {
            Customer premiumCustomer = new Customer("C004", "Alice Brown", "alice@example.com", true);
            Order order = new Order("O004", premiumCustomer);
            order.addItem(new OrderItem("P004", "Laptop", 999.99, 1));
            
            double discountedTotal = magicNumbersRefactored.calculateDiscount(order);
            
            // 999.99 * 0.9 = 899.99 (10% premium discount)
            // 899.99 * 0.95 = 854.99 (5% bulk discount for orders over $100)
            assertEquals(854.99, discountedTotal, 0.01);
        }
        
        @Test
        @DisplayName("Should apply bulk order discount with named constants")
        void shouldApplyBulkOrderDiscountWithNamedConstants() {
            Customer customer = new Customer("C005", "Charlie Wilson", "charlie@example.com", false);
            Order order = new Order("O005", customer);
            order.addItem(new OrderItem("P005", "Monitor", 299.99, 1));
            
            double discountedTotal = magicNumbersRefactored.calculateDiscount(order);
            
            // 299.99 * 0.95 = 284.99 (5% bulk discount for orders over $100)
            assertEquals(284.99, discountedTotal, 0.01);
        }
        
        @Test
        @DisplayName("Should check free shipping eligibility with named constants")
        void shouldCheckFreeShippingEligibilityWithNamedConstants() {
            Customer customer = new Customer("C006", "Diana Davis", "diana@example.com", false);
            Order order = new Order("O006", customer);
            order.addItem(new OrderItem("P006", "Keyboard", 89.99, 1));
            order.setWeight(5.0);
            
            boolean freeShipping = magicNumbersRefactored.isEligibleForFreeShipping(order);
            
            // Order total: 89.99 >= 50.0 AND weight 5.0 <= 10.0
            // Both conditions are met, so free shipping should be eligible
            assertTrue(freeShipping);
        }
        
        @Test
        @DisplayName("Should calculate delivery days with named constants")
        void shouldCalculateDeliveryDaysWithNamedConstants() {
            int standardDays = magicNumbersRefactored.calculateDeliveryDays("STANDARD");
            int expressDays = magicNumbersRefactored.calculateDeliveryDays("EXPRESS");
            int overnightDays = magicNumbersRefactored.calculateDeliveryDays("OVERNIGHT");
            int defaultDays = magicNumbersRefactored.calculateDeliveryDays("UNKNOWN");
            
            assertEquals(5, standardDays);
            assertEquals(2, expressDays);
            assertEquals(1, overnightDays);
            assertEquals(7, defaultDays);
        }
        
        @Test
        @DisplayName("Should produce same results as smell version")
        void shouldProduceSameResultsAsSmellVersion() {
            Customer customer = new Customer("C007", "Eve Miller", "eve@example.com", true);
            Order order = new Order("O007", customer);
            order.addItem(new OrderItem("P007", "Laptop", 999.99, 1));
            order.setWeight(8.0);
            
            double smellDiscount = magicNumbersSmell.calculateDiscount(order);
            double refactoredDiscount = magicNumbersRefactored.calculateDiscount(order);
            boolean smellShipping = magicNumbersSmell.isEligibleForFreeShipping(order);
            boolean refactoredShipping = magicNumbersRefactored.isEligibleForFreeShipping(order);
            
            assertEquals(smellDiscount, refactoredDiscount, 0.01);
            assertEquals(smellShipping, refactoredShipping);
        }
    }
}
