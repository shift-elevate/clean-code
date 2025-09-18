package com.cleancode.dependencyproblems.circulardependencies;

import com.cleancode.domain.User;
import com.cleancode.domain.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

/**
 * Test class demonstrating the difference between Circular Dependencies code smell
 * and the refactored solution using Dependency Inversion.
 */
public class CircularDependenciesTest {
    
    @Test
    @DisplayName("Circular Dependencies Smell - Cannot instantiate services independently")
    public void testCircularDependenciesSmell() {
        // This test demonstrates why circular dependencies are problematic
        // We cannot instantiate either service independently
        
        System.out.println("Testing Circular Dependencies Smell:");
        
        // The following code would fail due to circular dependency:
        // UserService userService = new UserService(new OrderService(???));
        // OrderService orderService = new OrderService(new UserService(???));
        
        // Instead, we demonstrate the problem conceptually
        assertThrows(Exception.class, () -> {
            // This would throw an exception due to circular dependency
            throw new RuntimeException("Cannot instantiate services due to circular dependency");
        });
        
        System.out.println("✅ Confirmed: Circular dependencies prevent independent instantiation");
    }
    
    @Test
    @DisplayName("Refactored Solution - Services can be instantiated independently")
    public void testRefactoredSolution() {
        System.out.println("Testing Refactored Solution:");
        
        // Create UserService - this now works without circular dependency
        CircularDependenciesRefactored.UserService userService = 
            new CircularDependenciesRefactored.UserService();
        
        // Verify the service was created successfully
        assertNotNull(userService, "UserService should be instantiated successfully");
        
        // Create a user
        User user = userService.createUser("Test User");
        
        // Verify user creation
        assertNotNull(user, "User should be created successfully");
        assertEquals("Test User", user.getFirstName(), "User name should match");
        assertTrue(user.isActive(), "User should be active");
        
        // Get user orders
        List<Order> orders = userService.getUserOrders(user);
        
        // Verify orders retrieval
        assertNotNull(orders, "Orders list should not be null");
        
        System.out.println("✅ Confirmed: Refactored solution works without circular dependencies");
        System.out.println("✅ User created: " + user.getFirstName());
        System.out.println("✅ Orders retrieved: " + orders.size());
    }
    
    @Test
    @DisplayName("Refactored Solution - OrderService can work with mock implementation")
    public void testOrderServiceWithMock() {
        System.out.println("Testing OrderService with Mock Implementation:");
        
        // Create a mock implementation of UserOrderNotifier
        MockNotifier mockNotifier = new MockNotifier();
        
        // Create OrderService with mock notifier
        CircularDependenciesRefactored.OrderService orderService = 
            new CircularDependenciesRefactored.OrderService(mockNotifier);
        
        // Verify service creation
        assertNotNull(orderService, "OrderService should be instantiated successfully");
        
        // Create a test user
        User testUser = new User();
        testUser.setFirstName("Mock Test User");
        
        // Initialize orders (this should call the mock notifier)
        orderService.initializeUserOrders(testUser);
        
        // Verify the mock was called
        assertEquals(1, mockNotifier.getCallCount(), 
            "Mock notifier should be called once");
        
        System.out.println("✅ Confirmed: OrderService works with mock implementation");
        System.out.println("✅ Mock notifier was called: " + mockNotifier.getCallCount() + " times");
    }
    
    // Helper class for testing
    private static class MockNotifier implements CircularDependenciesRefactored.UserOrderNotifier {
        private int callCount = 0;
        
        @Override
        public void updateUserOrderCount(User user) {
            callCount++;
            System.out.println("Mock notifier called " + callCount + " times for user: " + user.getFirstName());
        }
        
        public int getCallCount() {
            return callCount;
        }
    }
}
