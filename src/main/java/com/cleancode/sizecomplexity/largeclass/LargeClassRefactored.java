package com.cleancode.sizecomplexity.largeclass;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Demonstrates the Extract Class refactoring solution.
 * This shows how to break down a large class into smaller, focused classes.
 * 
 * Refactoring: Extract Class
 * Solution: Break down large classes into smaller, focused classes with single responsibilities
 * Benefits: Single Responsibility Principle, improved testability, better reusability
 */
public class LargeClassRefactored {
    
    /**
     * REFACTORED VERSION - EXTRACT CLASS PATTERN
     * 
     * The original large class has been broken down into smaller, focused classes:
     * - CustomerProfile: Handles customer data and personal information
     * - AccountManager: Manages account operations and balance
     * - OrderHistory: Tracks order history and spending
     * - NotificationManager: Handles all notification preferences and sending
     */
    
    private CustomerProfile customerProfile;
    private AccountManager accountManager;
    private OrderHistory orderHistory;
    private NotificationManager notificationManager;
    
    public LargeClassRefactored(String customerId) {
        this.customerProfile = new CustomerProfile(customerId);
        this.accountManager = new AccountManager();
        this.orderHistory = new OrderHistory();
        this.notificationManager = new NotificationManager();
    }
    
    public void updatePersonalInfo(String firstName, String lastName, String email) {
        customerProfile.updatePersonalInfo(firstName, lastName, email);
    }
    
    public void updateAddress(String address, String city, String state, String zipCode, String country) {
        customerProfile.updateAddress(address, city, state, zipCode, country);
    }
    
    public void processPayment(double amount) {
        accountManager.processPayment(amount);
        notificationManager.sendPaymentConfirmation();
    }
    
    public void addFunds(double amount) {
        accountManager.addFunds(amount);
        notificationManager.sendBalanceUpdateNotification();
    }
    
    public void addOrder(String orderId, double amount) {
        orderHistory.addOrder(orderId, amount);
        accountManager.updateLoyaltyPoints(amount);
        notificationManager.sendOrderConfirmation(orderId);
    }
    
    public void updateNotificationPreferences(boolean email, boolean sms, boolean push) {
        notificationManager.updateNotificationPreferences(email, sms, push);
    }
    
    // Delegate getters to appropriate classes
    public String getCustomerId() { return customerProfile.getCustomerId(); }
    public String getEmail() { return customerProfile.getEmail(); }
    public double getAccountBalance() { return accountManager.getAccountBalance(); }
    public List<String> getOrderHistory() { return orderHistory.getOrderHistory(); }
    public double calculateAverageOrderValue() { return orderHistory.calculateAverageOrderValue(); }
}

/**
 * Focused class for customer profile data
 */
class CustomerProfile {
    private String customerId;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String address;
    private String city;
    private String state;
    private String zipCode;
    private String country;
    
    public CustomerProfile(String customerId) {
        this.customerId = customerId;
    }
    
    public void updatePersonalInfo(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        validateEmail(email);
        logPersonalInfoUpdate();
    }
    
    public void updateAddress(String address, String city, String state, String zipCode, String country) {
        this.address = address;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.country = country;
        validateAddress();
        logAddressUpdate();
    }
    
    private void validateEmail(String email) {
        if (email == null || !email.contains("@")) {
            throw new IllegalArgumentException("Invalid email format");
        }
    }
    
    private void validateAddress() {
        if (address == null || address.trim().isEmpty()) {
            throw new IllegalArgumentException("Address cannot be empty");
        }
    }
    
    private void logPersonalInfoUpdate() {
        System.out.println("Personal info updated for customer: " + customerId);
    }
    
    private void logAddressUpdate() {
        System.out.println("Address updated for customer: " + customerId);
    }
    
    // Getters
    public String getCustomerId() { return customerId; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }
    public String getAddress() { return address; }
    public String getCity() { return city; }
    public String getState() { return state; }
    public String getZipCode() { return zipCode; }
    public String getCountry() { return country; }
}

/**
 * Focused class for account operations
 */
class AccountManager {
    private double accountBalance;
    private String accountStatus;
    private Date accountCreatedDate;
    private Date lastLoginDate;
    private int loyaltyPoints;
    
    public AccountManager() {
        this.accountBalance = 0.0;
        this.loyaltyPoints = 0;
        this.accountStatus = "ACTIVE";
        this.accountCreatedDate = new Date();
    }
    
    public void processPayment(double amount) {
        if (accountBalance >= amount) {
            accountBalance -= amount;
            logTransaction(amount);
        } else {
            throw new RuntimeException("Insufficient account balance");
        }
    }
    
    public void addFunds(double amount) {
        accountBalance += amount;
        logTransaction(amount);
    }
    
    public void updateAccountStatus(String status) {
        this.accountStatus = status;
        logStatusChange(status);
    }
    
    public void updateLoyaltyPoints(double amount) {
        loyaltyPoints += (int) (amount * 0.1); // 10% of amount as points
    }
    
    private void logTransaction(double amount) {
        System.out.println("Transaction logged: $" + amount);
    }
    
    private void logStatusChange(String status) {
        System.out.println("Account status changed to: " + status);
    }
    
    // Getters
    public double getAccountBalance() { return accountBalance; }
    public String getAccountStatus() { return accountStatus; }
    public Date getAccountCreatedDate() { return accountCreatedDate; }
    public Date getLastLoginDate() { return lastLoginDate; }
    public int getLoyaltyPoints() { return loyaltyPoints; }
}

/**
 * Focused class for order history
 */
class OrderHistory {
    private List<String> orderHistory;
    private double totalSpent;
    private Date lastOrderDate;
    
    public OrderHistory() {
        this.orderHistory = new ArrayList<>();
        this.totalSpent = 0.0;
    }
    
    public void addOrder(String orderId, double amount) {
        orderHistory.add(orderId);
        totalSpent += amount;
        lastOrderDate = new Date();
    }
    
    public List<String> getOrderHistory() {
        return new ArrayList<>(orderHistory);
    }
    
    public double calculateAverageOrderValue() {
        if (orderHistory.isEmpty()) {
            return 0.0;
        }
        return totalSpent / orderHistory.size();
    }
    
    // Getters
    public double getTotalSpent() { return totalSpent; }
    public Date getLastOrderDate() { return lastOrderDate; }
}

/**
 * Focused class for notifications
 */
class NotificationManager {
    private boolean emailNotifications;
    private boolean smsNotifications;
    private boolean pushNotifications;
    private String preferredLanguage;
    
    public NotificationManager() {
        this.emailNotifications = true;
        this.smsNotifications = false;
        this.pushNotifications = true;
        this.preferredLanguage = "EN";
    }
    
    public void updateNotificationPreferences(boolean email, boolean sms, boolean push) {
        this.emailNotifications = email;
        this.smsNotifications = sms;
        this.pushNotifications = push;
        logPreferenceUpdate();
    }
    
    public void sendNotification(String message, String type) {
        if ("EMAIL".equals(type) && emailNotifications) {
            sendEmailNotification(message);
        } else if ("SMS".equals(type) && smsNotifications) {
            sendSMSNotification(message);
        } else if ("PUSH".equals(type) && pushNotifications) {
            sendPushNotification(message);
        }
    }
    
    public void sendPaymentConfirmation() {
        sendNotification("Payment processed successfully", "EMAIL");
    }
    
    public void sendBalanceUpdateNotification() {
        sendNotification("Account balance updated", "EMAIL");
    }
    
    public void sendOrderConfirmation(String orderId) {
        sendNotification("Order " + orderId + " confirmed", "EMAIL");
    }
    
    private void logPreferenceUpdate() {
        System.out.println("Notification preferences updated");
    }
    
    private void sendEmailNotification(String message) {
        System.out.println("Email sent: " + message);
    }
    
    private void sendSMSNotification(String message) {
        System.out.println("SMS sent: " + message);
    }
    
    private void sendPushNotification(String message) {
        System.out.println("Push notification sent: " + message);
    }
    
    // Getters
    public boolean isEmailNotifications() { return emailNotifications; }
    public boolean isSmsNotifications() { return smsNotifications; }
    public boolean isPushNotifications() { return pushNotifications; }
    public String getPreferredLanguage() { return preferredLanguage; }
}

