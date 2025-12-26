package com.cleancode.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Domain class representing an order in the e-commerce system.
 * Used to demonstrate code smells and refactoring techniques.
 */
public class Order {
    private String orderId;
    private Customer customer;
    private List<OrderItem> items;
    private double total;
    private OrderStatus status;
    private double weight;
    
    public Order(String orderId, Customer customer) {
        this.orderId = orderId;
        this.customer = customer;
        this.items = new ArrayList<>();
        this.status = OrderStatus.PENDING;
        this.weight = 0.0; // Default weight
    }
    
    public String getOrderId() {
        return orderId;
    }
    
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
    
    public Customer getCustomer() {
        return customer;
    }
    
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    
    public List<OrderItem> getItems() {
        return new ArrayList<>(items); // Return defensive copy
    }
    
    public void addItem(OrderItem item) {
        this.items.add(item);
    }
    
    public double getTotal() {
        return total;
    }
    
    public void setTotal(double total) {
        this.total = total;
    }
    
    public double getWeight() {
        return weight;
    }
    
    public void setWeight(double weight) {
        this.weight = weight;
    }
    
    public OrderStatus getStatus() {
        return status;
    }
    
    public void setStatus(OrderStatus status) {
        this.status = status;
    }
    
    @Override
    public String toString() {
        return "Order{" +
                "orderId='" + orderId + '\'' +
                ", customer=" + customer +
                ", items=" + items +
                ", total=" + total +
                ", status=" + status +
                '}';
    }
}
