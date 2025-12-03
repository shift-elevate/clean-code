package com.cleancode.sizecomplexity.largeclass;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Demonstrates the Large Class code smell.
 * This class has grown too large and tries to do too much, violating the Single Responsibility Principle.
 * 
 * Code Smell: Large Class
 * Problem: Class becomes too large and takes on too many responsibilities
 * Impact: Difficult to understand, hard to test and maintain, higher bug risk
 */
public class LargeClassSmell {
    
    /**
     * LARGE CLASS CODE SMELL EXAMPLE
     * 
     * This class demonstrates the Large Class code smell by trying to handle
     * multiple responsibilities: customer data, account management, order history,
     * and notifications. This violates the Single Responsibility Principle.
     * 
     * Problems:
     * - Too many fields and methods in one class
     * - Multiple unrelated responsibilities
     * - Difficult to understand and maintain
     * - Hard to test individual components
     * - Frequent changes affecting many methods
     */
    
    // Customer data fields
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
    
    // Account management fields
    private double accountBalance;
    private String accountStatus;
    private Date accountCreatedDate;
    private Date lastLoginDate;
    private int loyaltyPoints;
    
    // Order history fields
    private List<String> orderHistory;
    private double totalSpent;
    private Date lastOrderDate;
    
    // Notification preferences
    private boolean emailNotifications;
    private boolean smsNotifications;
    private boolean pushNotifications;
    private String preferredLanguage;
    
    public LargeClassSmell(String customerId) {
        this.customerId = customerId;
        this.orderHistory = new ArrayList<>();
        this.accountBalance = 0.0;
        this.totalSpent = 0.0;
        this.loyaltyPoints = 0;
        this.accountStatus = "ACTIVE";
        this.emailNotifications = true;
        this.smsNotifications = false;
        this.pushNotifications = true;
        this.preferredLanguage = "EN";
        this.accountCreatedDate = new Date();
    }
    
    // Customer data methods
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
    
    // Account management methods
    public void processPayment(double amount) {
        if (accountBalance >= amount) {
            accountBalance -= amount;
            updateLoyaltyPoints(amount);
            logTransaction(amount);
            sendPaymentConfirmation();
        } else {
            throw new RuntimeException("Insufficient account balance");
        }
    }
    
    public void addFunds(double amount) {
        accountBalance += amount;
        logTransaction(amount);
        sendBalanceUpdateNotification();
    }
    
    public void updateAccountStatus(String status) {
        this.accountStatus = status;
        logStatusChange(status);
        if ("SUSPENDED".equals(status)) {
            sendAccountSuspensionNotification();
        }
    }
    
    // Order history methods
    public void addOrder(String orderId, double amount) {
        orderHistory.add(orderId);
        totalSpent += amount;
        lastOrderDate = new Date();
        updateLoyaltyPoints(amount);
        sendOrderConfirmation(orderId);
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
    
    // Notification methods
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
    
    // Private helper methods (too many!)
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
    
    private void logTransaction(double amount) {
        System.out.println("Transaction logged: $" + amount + " for customer: " + customerId);
    }
    
    private void logStatusChange(String status) {
        System.out.println("Account status changed to: " + status + " for customer: " + customerId);
    }
    
    private void logPreferenceUpdate() {
        System.out.println("Notification preferences updated for customer: " + customerId);
    }
    
    private void updateLoyaltyPoints(double amount) {
        loyaltyPoints += (int) (amount * 0.1); // 10% of amount as points
    }
    
    private void sendPaymentConfirmation() {
        sendNotification("Payment processed successfully", "EMAIL");
    }
    
    private void sendBalanceUpdateNotification() {
        sendNotification("Account balance updated", "EMAIL");
    }
    
    private void sendAccountSuspensionNotification() {
        sendNotification("Account has been suspended", "EMAIL");
    }
    
    private void sendOrderConfirmation(String orderId) {
        sendNotification("Order " + orderId + " confirmed", "EMAIL");
    }
    
    private void sendEmailNotification(String message) {
        System.out.println("Email sent to " + email + ": " + message);
    }
    
    private void sendSMSNotification(String message) {
        System.out.println("SMS sent to " + phone + ": " + message);
    }
    
    private void sendPushNotification(String message) {
        System.out.println("Push notification sent: " + message);
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
    public double getAccountBalance() { return accountBalance; }
    public String getAccountStatus() { return accountStatus; }
    public Date getAccountCreatedDate() { return accountCreatedDate; }
    public Date getLastLoginDate() { return lastLoginDate; }
    public int getLoyaltyPoints() { return loyaltyPoints; }
    public double getTotalSpent() { return totalSpent; }
    public Date getLastOrderDate() { return lastOrderDate; }
    public boolean isEmailNotifications() { return emailNotifications; }
    public boolean isSmsNotifications() { return smsNotifications; }
    public boolean isPushNotifications() { return pushNotifications; }
    public String getPreferredLanguage() { return preferredLanguage; }
}

