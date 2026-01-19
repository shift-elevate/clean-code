package com.cleancode.sizecomplexity.hardcodedstrings;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for Hardcoded Strings code smell and its refactored solution.
 */
@DisplayName("Hardcoded Strings Code Smell Tests")
class HardcodedStringsTest {

    @Nested
    @DisplayName("Hardcoded Strings Smell Tests")
    class HardcodedStringsSmellTests {
        private HardcodedStringsSmell userService;

        @BeforeEach
        void setUp() {
            userService = new HardcodedStringsSmell();
        }

        @Test
        @DisplayName("Should create user with valid role")
        void shouldCreateUserWithValidRole() {
            String result = userService.createUser("johndoe", "john@example.com", "USER");

            assertTrue(result.contains("johndoe"));
            assertTrue(result.contains("USER"));
            assertTrue(result.contains("ACTIVE"));
        }

        @Test
        @DisplayName("Should throw exception for invalid role")
        void shouldThrowExceptionForInvalidRole() {
            Exception exception = assertThrows(IllegalArgumentException.class, () ->
                    userService.createUser("johndoe", "john@example.com", "INVALID")
            );
            assertEquals("Invalid role. Must be ADMIN, USER, or MODERATOR", exception.getMessage());
        }

        @Test
        @DisplayName("Should update user status with valid status")
        void shouldUpdateUserStatusWithValidStatus() {
            String result = userService.updateUserStatus("johndoe", "SUSPENDED");

            assertTrue(result.contains("johndoe"));
            assertTrue(result.contains("SUSPENDED"));
        }

        @Test
        @DisplayName("Should throw exception for invalid status")
        void shouldThrowExceptionForInvalidStatus() {
            Exception exception = assertThrows(IllegalArgumentException.class, () ->
                    userService.updateUserStatus("johndoe", "INVALID")
            );
            assertEquals("Invalid status. Must be ACTIVE, INACTIVE, or SUSPENDED", exception.getMessage());
        }

        @Test
        @DisplayName("Should correctly identify admin role")
        void shouldCorrectlyIdentifyAdminRole() {
            assertTrue(userService.isAdmin("ADMIN"));
            assertFalse(userService.isAdmin("USER"));
            assertFalse(userService.isAdmin("MODERATOR"));
        }

        @Test
        @DisplayName("Should correctly identify active status")
        void shouldCorrectlyIdentifyActiveStatus() {
            assertTrue(userService.isActive("ACTIVE"));
            assertFalse(userService.isActive("INACTIVE"));
            assertFalse(userService.isActive("SUSPENDED"));
        }
    }

    @Nested
    @DisplayName("Hardcoded Strings Refactored Tests")
    class HardcodedStringsRefactoredTests {
        private HardcodedStringsRefactored userService;

        @BeforeEach
        void setUp() {
            userService = new HardcodedStringsRefactored();
        }

        @Test
        @DisplayName("Should create user with typesafe role enum")
        void shouldCreateUserWithTypesafeRole() {
            String result = userService.createUser("johndoe", "john@example.com", UserRole.USER.getValue());

            assertTrue(result.contains("johndoe"));
            assertTrue(result.contains("USER"));
            assertTrue(result.contains("ACTIVE"));
        }

        @Test
        @DisplayName("Should throw exception for invalid role with descriptive message")
        void shouldThrowExceptionForInvalidRole() {
            Exception exception = assertThrows(IllegalArgumentException.class, () ->
                    userService.createUser("johndoe", "john@example.com", "INVALID")
            );
            assertEquals("Invalid role. Must be ADMIN, USER, or MODERATOR", exception.getMessage());
        }

        @Test
        @DisplayName("Should update user status with typesafe status enum")
        void shouldUpdateUserStatusWithTypesafeStatus() {
            String result = userService.updateUserStatus("johndoe", UserStatus.SUSPENDED.getValue());

            assertTrue(result.contains("johndoe"));
            assertTrue(result.contains("SUSPENDED"));
        }

        @Test
        @DisplayName("Should throw exception for invalid status with descriptive message")
        void shouldThrowExceptionForInvalidStatus() {
            Exception exception = assertThrows(IllegalArgumentException.class, () ->
                    userService.updateUserStatus("johndoe", "INVALID")
            );
            assertEquals("Invalid status: INVALID", exception.getMessage());
        }

        @Test
        @DisplayName("Should correctly identify admin role using enum")
        void shouldCorrectlyIdentifyAdminRoleUsingEnum() {
            assertTrue(userService.isAdmin(UserRole.ADMIN.getValue()));
            assertFalse(userService.isAdmin(UserRole.USER.getValue()));
            assertFalse(userService.isAdmin(UserRole.MODERATOR.getValue()));
        }

        @Test
        @DisplayName("Should correctly identify active status using enum")
        void shouldCorrectlyIdentifyActiveStatusUsingEnum() {
            assertTrue(userService.isActive(UserStatus.ACTIVE.getValue()));
            assertFalse(userService.isActive(UserStatus.INACTIVE.getValue()));
            assertFalse(userService.isActive(UserStatus.SUSPENDED.getValue()));
        }
    }

    @Nested
    @DisplayName("UserRole Enum Tests")
    class UserRoleEnumTests {

        @Test
        @DisplayName("Should validate valid roles")
        void shouldValidateValidRoles() {
            assertTrue(UserRole.isValid("ADMIN"));
            assertTrue(UserRole.isValid("USER"));
            assertTrue(UserRole.isValid("MODERATOR"));
        }

        @Test
        @DisplayName("Should reject invalid roles")
        void shouldRejectInvalidRoles() {
            assertFalse(UserRole.isValid("INVALID"));
            assertFalse(UserRole.isValid("admin")); // case-sensitive
            assertFalse(UserRole.isValid(""));
            assertFalse(UserRole.isValid(null));
        }

        @Test
        @DisplayName("Should convert string to enum")
        void shouldConvertStringToEnum() {
            assertEquals(UserRole.ADMIN, UserRole.fromString("ADMIN"));
            assertEquals(UserRole.USER, UserRole.fromString("USER"));
            assertEquals(UserRole.MODERATOR, UserRole.fromString("MODERATOR"));
        }

        @Test
        @DisplayName("Should throw exception for invalid role string")
        void shouldThrowExceptionForInvalidRoleString() {
            Exception exception = assertThrows(IllegalArgumentException.class, () ->
                    UserRole.fromString("INVALID")
            );
            assertEquals("Invalid role: INVALID", exception.getMessage());
        }

        @Test
        @DisplayName("Should return correct value from getValue")
        void shouldReturnCorrectValue() {
            assertEquals("ADMIN", UserRole.ADMIN.getValue());
            assertEquals("USER", UserRole.USER.getValue());
            assertEquals("MODERATOR", UserRole.MODERATOR.getValue());
        }
    }

    @Nested
    @DisplayName("UserStatus Enum Tests")
    class UserStatusEnumTests {

        @Test
        @DisplayName("Should validate valid statuses")
        void shouldValidateValidStatuses() {
            assertTrue(UserStatus.isValid("ACTIVE"));
            assertTrue(UserStatus.isValid("INACTIVE"));
            assertTrue(UserStatus.isValid("SUSPENDED"));
        }

        @Test
        @DisplayName("Should reject invalid statuses")
        void shouldRejectInvalidStatuses() {
            assertFalse(UserStatus.isValid("INVALID"));
            assertFalse(UserStatus.isValid("active")); // case-sensitive
            assertFalse(UserStatus.isValid(""));
            assertFalse(UserStatus.isValid(null));
        }

        @Test
        @DisplayName("Should convert string to enum")
        void shouldConvertStringToEnum() {
            assertEquals(UserStatus.ACTIVE, UserStatus.fromString("ACTIVE"));
            assertEquals(UserStatus.INACTIVE, UserStatus.fromString("INACTIVE"));
            assertEquals(UserStatus.SUSPENDED, UserStatus.fromString("SUSPENDED"));
        }

        @Test
        @DisplayName("Should throw exception for invalid status string")
        void shouldThrowExceptionForInvalidStatusString() {
            Exception exception = assertThrows(IllegalArgumentException.class, () ->
                    UserStatus.fromString("INVALID")
            );
            assertEquals("Invalid status: INVALID", exception.getMessage());
        }

        @Test
        @DisplayName("Should return correct value from getValue")
        void shouldReturnCorrectValue() {
            assertEquals("ACTIVE", UserStatus.ACTIVE.getValue());
            assertEquals("INACTIVE", UserStatus.INACTIVE.getValue());
            assertEquals("SUSPENDED", UserStatus.SUSPENDED.getValue());
        }
    }
}
