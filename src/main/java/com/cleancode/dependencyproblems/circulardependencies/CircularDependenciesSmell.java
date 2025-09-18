package com.cleancode.dependencyproblems.circulardependencies;

import com.cleancode.domain.User;
import com.cleancode.domain.Order;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Demonstrates the Circular Dependencies code smell.
 * This class shows how two services can create a circular dependency that prevents instantiation.
 * 
 * Code Smell: Circular Dependencies
 * Problem: Two classes depend on each other, making it impossible to instantiate either
 * Impact: Prevents compilation, creates tight coupling, violates dependency inversion principle
 */
public class CircularDependenciesSmell {
    
    /**
     * CIRCULAR DEPENDENCY CODE SMELL EXAMPLE
     * 
     * This demonstrates a circular dependency where:
     * - UserService depends on OrderService
     * - OrderService depends on UserService
     * 
     * Problems:
     * - Neither service can be instantiated independently
     * - Creates tight coupling between services
     * - Violates the Dependency Inversion Principle
     * - Makes testing difficult
     * - Prevents modular design
     */
    public static class UserService {
        private OrderService orderService;
        
        public UserService(OrderService orderService) {
            this.orderService = orderService;
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
        
        public void updateUserOrderCount(User user) {
            // This method would be called by OrderService
            // but we can't show it working due to circular dependency
        }
    }
    
    public static class OrderService {
        private UserService userService;
        
        public OrderService(UserService userService) {
            this.userService = userService; // Circular dependency!
        }
        
        public void initializeUserOrders(User user) {
            Order defaultOrder = new Order("ORDER_" + UUID.randomUUID().toString().substring(0, 8), null);
            userService.updateUserOrderCount(user);
        }
        
        public List<Order> getOrdersByUser(User user) {
            return new ArrayList<>();
        }
    }
    
    /**
     * This method demonstrates the circular dependency problem.
     * It will fail to compile or run because neither service can be instantiated.
     */
    public static void demonstrateCircularDependencyProblem() {
        System.out.println("❌ CIRCULAR DEPENDENCY PROBLEM");
        System.out.println("Cannot instantiate UserService without OrderService");
        System.out.println("Cannot instantiate OrderService without UserService");
        System.out.println("This creates a chicken-and-egg problem!");
        
        // This code would fail:
        // UserService userService = new UserService(new OrderService(???));
        // OrderService orderService = new OrderService(new UserService(???));
        
        System.out.println("Error: Circular dependency prevents instantiation");
    }
}
