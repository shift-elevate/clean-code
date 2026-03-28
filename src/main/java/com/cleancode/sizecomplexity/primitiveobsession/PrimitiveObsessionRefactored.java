package com.cleancode.sizecomplexity.primitiveobsession;

import com.cleancode.domain.Email;
import com.cleancode.domain.Money;
import com.cleancode.domain.PhoneNumber;
import com.cleancode.domain.UserStatus;
import com.cleancode.domain.ZipCode;

/**
 * Refactored solution for Primitive Obsession code smell.
 * This class uses meaningful value objects instead of primitive types.
 *
 * Refactoring: Replace Primitive with Object
 * Benefits: Rich domain model, type safety, encapsulated validation
 * Result: More expressive code that's easier to maintain
 */
public class PrimitiveObsessionRefactored {
    private Email email;
    private PhoneNumber phoneNumber;
    private ZipCode zipCode;
    private Money salary;
    private UserStatus status;

    /**
     * REFACTORED SOLUTION: Value objects ensure validity at construction time
     * No need for separate validation methods - invalid objects cannot exist
     */
    public PrimitiveObsessionRefactored(Email email, PhoneNumber phoneNumber, ZipCode zipCode,
                                        Money salary, UserStatus status) {
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.zipCode = zipCode;
        this.salary = salary;
        this.status = status;
    }

    /**
     * REFACTORED SOLUTION: Business logic delegates to value objects
     * Type safety prevents passing wrong values
     */
    public boolean isActive() {
        return status.isActive();
    }

    public Email getEmail() {
        return email;
    }

    public PhoneNumber getPhoneNumber() {
        return phoneNumber;
    }

    public ZipCode getZipCode() {
        return zipCode;
    }

    public Money getSalary() {
        return salary;
    }

    public UserStatus getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "User{" +
                "email=" + email +
                ", phoneNumber=" + phoneNumber +
                ", zipCode=" + zipCode +
                ", salary=" + salary +
                ", status=" + status +
                '}';
    }
}
