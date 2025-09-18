package com.cleancode.dependencyproblems.circulardependencies;

import com.cleancode.domain.User;
import com.cleancode.domain.Order;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Demonstrates the refactored solution for Circular Dependencies using Dependency Inversion.
 * This class shows how to break circular dependencies by introducing interfaces and abstractions.
 * 
 * Refactoring: Dependency Inversion
 * Solution: Extract interfaces to break circular dependencies
 * Benefits: Enables independent instantiation, improves testability, promotes loose coupling
 */
public class CircularDependenciesRefactored {
    
    /**
     * Interface that represents the specific functionality needed by OrderService.
     * This breaks the circular dependency by providing an abstraction.
     */
    public interface UserOrderNotifier {
        void updateUserOrderCount(User user);
    }
    
    /**
     * REFACTORED USER SERVICE
     * 
     * This service implements the UserOrderNotifier interface,
     * allowing OrderService to depend on the interface rather than the concrete class.
     * 
     * Benefits:
     * - Can be instantiated independently
     * - Implements a focused interface
     * - Follows the Dependency Inversion Principle
     * - Enables easy testing with mocks
     */
    public static class UserService implements UserOrderNotifier {
        private OrderService orderService;
        
        public UserService() {
            this.orderService = new OrderService(this);
        }
        
        public User createUser(String name) {
            User user = new User();
            user.setFirstName(name);
            user.setActive(true);
            orderService.initializeUserOrders(user);
            return user;
        }
        
        public List<Order> getUserOrders(User user) {
            return orderService.getOrdersByUser(user);
        }
        
        @Override
        public void updateUserOrderCount(User user) {
            // This method can now be called by OrderService through the interface
            System.out.println("Updating order count for user: " + user.getFirstName());
        }
    }
    
    /**
     * REFACTORED ORDER SERVICE
     * 
     * This service depends on the UserOrderNotifier interface instead of the concrete UserService.
     * This breaks the circular dependency and enables independent instantiation.
     * 
     * Benefits:
     * - Depends on abstraction, not concrete implementation
     * - Can be instantiated with any implementation of UserOrderNotifier
     * - Follows the Dependency Inversion Principle
     * - Enables easy testing with mock implementations
     */
    public static class OrderService {
        private final UserOrderNotifier userOrderNotifier;
        
        public OrderService(UserOrderNotifier userOrderNotifier) {
            this.userOrderNotifier = userOrderNotifier;
        }
        
        public void initializeUserOrders(User user) {
            Order defaultOrder = new Order("ORDER_" + UUID.randomUUID().toString().substring(0, 8), null);
            userOrderNotifier.updateUserOrderCount(user);
        }
        
        public List<Order> getOrdersByUser(User user) {
            return new ArrayList<>();
        }
    }
    
    /**
     * This method demonstrates the refactored solution working correctly.
     * It shows how the circular dependency is resolved and services can be used.
     */
    public static void demonstrateRefactoredSolution() {
        System.out.println("✅ CIRCULAR DEPENDENCY RESOLVED");
        
        // Create UserService - this now works without circular dependency
        UserService userService = new UserService();
        
        // Create a user - this will automatically initialize orders
        User user = userService.createUser("John Doe");
        
        // Get user orders
        List<Order> orders = userService.getUserOrders(user);
        
        System.out.println("User: " + user.getFirstName());
        System.out.println("Active: " + user.isActive());
        System.out.println("Orders: " + orders.size());
    }
}
