package com.cleancode.domain;

/**
 * Value object representing contact information.
 * Encapsulates phone and email as related contact data.
 * Also provides notification functionality.
 */
public class ContactInformation {
    private final String phone;
    private final String email;

    public ContactInformation(String phone, String email) {
        if (phone == null || phone.trim().isEmpty()) {
            throw new IllegalArgumentException("Phone cannot be null or empty");
        }
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("Email cannot be null or empty");
        }
        if (!isValidEmail(email)) {
            throw new IllegalArgumentException("Invalid email format: " + email);
        }
        this.phone = phone.trim();
        this.email = email.trim();
    }

    private boolean isValidEmail(String email) {
        return email.contains("@") && email.contains(".");
    }

    public void sendEmailConfirmation(String subject, String message) {
        System.out.println("Email sent to " + email + ": " + subject);
    }

    public void sendSmsConfirmation(String message) {
        System.out.println("SMS sent to " + phone + ": " + message);
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return String.format("Contact{phone='%s', email='%s'}", phone, email);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactInformation that = (ContactInformation) o;
        return phone.equals(that.phone) && email.equals(that.email);
    }

    @Override
    public int hashCode() {
        return 31 * phone.hashCode() + email.hashCode();
    }
}
