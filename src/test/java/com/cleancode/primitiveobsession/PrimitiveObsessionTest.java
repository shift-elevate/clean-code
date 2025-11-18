package com.cleancode.primitiveobsession;

import com.cleancode.domain.Email;
import com.cleancode.domain.Money;
import com.cleancode.domain.PhoneNumber;
import com.cleancode.domain.UserStatus;
import com.cleancode.domain.ZipCode;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for Primitive Obsession code smell and refactoring.
 */
class PrimitiveObsessionTest {

    // Tests for Code Smell
    @Test
    void testPrimitiveObsessionSmell_ValidUser() {
        PrimitiveObsessionSmell user = new PrimitiveObsessionSmell(
                "john@example.com",
                "+14155552671",
                "94105",
                75000.00,
                "ACTIVE"
        );

        assertTrue(user.isValidEmail());
        assertTrue(user.isValidPhoneNumber());
        assertTrue(user.isValidZipCode());
        assertTrue(user.isActive());
    }

    @Test
    void testPrimitiveObsessionSmell_InvalidEmail() {
        PrimitiveObsessionSmell user = new PrimitiveObsessionSmell(
                "not-an-email",
                "+14155552671",
                "94105",
                75000.00,
                "ACTIVE"
        );

        assertFalse(user.isValidEmail());
    }

    @Test
    void testPrimitiveObsessionSmell_InvalidPhoneNumber() {
        PrimitiveObsessionSmell user = new PrimitiveObsessionSmell(
                "john@example.com",
                "123",
                "94105",
                75000.00,
                "ACTIVE"
        );

        assertFalse(user.isValidPhoneNumber());
    }

    @Test
    void testPrimitiveObsessionSmell_InvalidZipCode() {
        PrimitiveObsessionSmell user = new PrimitiveObsessionSmell(
                "john@example.com",
                "+14155552671",
                "abc",
                75000.00,
                "ACTIVE"
        );

        assertFalse(user.isValidZipCode());
    }

    @Test
    void testPrimitiveObsessionSmell_InactiveStatus() {
        PrimitiveObsessionSmell user = new PrimitiveObsessionSmell(
                "john@example.com",
                "+14155552671",
                "94105",
                75000.00,
                "INACTIVE"
        );

        assertFalse(user.isActive());
    }

    // Tests for Refactored Solution - Email
    @Test
    void testEmail_ValidEmail() {
        Email email = new Email("john@example.com");
        assertEquals("john@example.com", email.getValue());
        assertEquals("example.com", email.getDomain());
    }

    @Test
    void testEmail_InvalidEmail() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Email("not-an-email");
        });
    }

    @Test
    void testEmail_NullEmail() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Email(null);
        });
    }

    // Tests for Refactored Solution - PhoneNumber
    @Test
    void testPhoneNumber_ValidPhoneNumber() {
        PhoneNumber phone = new PhoneNumber("+14155552671");
        assertEquals("+14155552671", phone.getValue());
        assertEquals("14", phone.getCountryCode());
    }

    @Test
    void testPhoneNumber_InvalidPhoneNumber() {
        assertThrows(IllegalArgumentException.class, () -> {
            new PhoneNumber("123");
        });
    }

    @Test
    void testPhoneNumber_NullPhoneNumber() {
        assertThrows(IllegalArgumentException.class, () -> {
            new PhoneNumber(null);
        });
    }

    // Tests for Refactored Solution - ZipCode
    @Test
    void testZipCode_ValidZipCode() {
        ZipCode zipCode = new ZipCode("94105");
        assertEquals("94105", zipCode.getValue());
    }

    @Test
    void testZipCode_ValidZipCodeWithExtension() {
        ZipCode zipCode = new ZipCode("94105-1234");
        assertEquals("94105-1234", zipCode.getValue());
    }

    @Test
    void testZipCode_InvalidZipCode() {
        assertThrows(IllegalArgumentException.class, () -> {
            new ZipCode("abc");
        });
    }

    @Test
    void testZipCode_NullZipCode() {
        assertThrows(IllegalArgumentException.class, () -> {
            new ZipCode(null);
        });
    }

    // Tests for Refactored Solution - Money
    @Test
    void testMoney_ValidAmount() {
        Money money = new Money(75000.00);
        assertEquals(75000.00, money.getAmount());
        assertEquals("$75000.00", money.getFormattedAmount());
    }

    @Test
    void testMoney_NegativeAmount() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Money(-5000.00);
        });
    }

    @Test
    void testMoney_ZeroAmount() {
        Money money = new Money(0.0);
        assertEquals(0.0, money.getAmount());
    }

    // Tests for Refactored Solution - UserStatus
    @Test
    void testUserStatus_Active() {
        assertTrue(UserStatus.ACTIVE.isActive());
    }

    @Test
    void testUserStatus_Inactive() {
        assertFalse(UserStatus.INACTIVE.isActive());
    }

    @Test
    void testUserStatus_Suspended() {
        assertFalse(UserStatus.SUSPENDED.isActive());
    }

    @Test
    void testUserStatus_Pending() {
        assertFalse(UserStatus.PENDING.isActive());
    }

    // Tests for Refactored Solution - Complete User
    @Test
    void testPrimitiveObsessionRefactored_ValidUser() {
        PrimitiveObsessionRefactored user = new PrimitiveObsessionRefactored(
                new Email("john@example.com"),
                new PhoneNumber("+14155552671"),
                new ZipCode("94105"),
                new Money(75000.00),
                UserStatus.ACTIVE
        );

        assertTrue(user.isActive());
        assertEquals("john@example.com", user.getEmail().getValue());
        assertEquals("+14155552671", user.getPhoneNumber().getValue());
        assertEquals("94105", user.getZipCode().getValue());
        assertEquals(75000.00, user.getSalary().getAmount());
        assertEquals(UserStatus.ACTIVE, user.getStatus());
    }

    @Test
    void testPrimitiveObsessionRefactored_InactiveUser() {
        PrimitiveObsessionRefactored user = new PrimitiveObsessionRefactored(
                new Email("jane@example.com"),
                new PhoneNumber("+14155552672"),
                new ZipCode("94105"),
                new Money(85000.00),
                UserStatus.INACTIVE
        );

        assertFalse(user.isActive());
    }
}
