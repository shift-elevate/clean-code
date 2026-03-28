package com.cleancode.oopantipatterns;

import com.cleancode.domain.Customer;
import com.cleancode.domain.OrderItem;
import com.cleancode.oopantipatterns.temporaryfield.TemporaryFieldSmell;
import com.cleancode.oopantipatterns.temporaryfield.TemporaryFieldRefactored;
import com.cleancode.oopantipatterns.temporaryfield.TemporaryFieldRefactored.DiscountInfo;
import com.cleancode.oopantipatterns.temporaryfield.TemporaryFieldRefactored.PaymentInfo;
import com.cleancode.oopantipatterns.temporaryfield.TemporaryFieldRefactored.ShipmentInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Temporary Field Code Smell Tests")
class TemporaryFieldTest {

    private Customer customer;
    private List<OrderItem> items;

    // Base total: 999.99 * 1 + 29.99 * 2 = 1059.97
    private static final double BASE_TOTAL = 1059.97;

    @BeforeEach
    void setUp() {
        customer = new Customer("C001", "John Doe", "john@example.com", false);
        items = List.of(
            new OrderItem("P001", "Laptop", 999.99, 1),
            new OrderItem("P002", "Mouse", 29.99, 2)
        );
    }

    @Nested
    @DisplayName("Temporary Field Smell Tests")
    class TemporaryFieldSmellTests {

        private TemporaryFieldSmell processor;

        @BeforeEach
        void setUp() {
            processor = new TemporaryFieldSmell("ORD-001", customer, items);
        }

        @Test
        @DisplayName("Should initialize temporary fields to null or zero in constructor")
        void shouldInitializeTemporaryFieldsToDefaultValues() {
            assertNull(processor.getAppliedDiscountCode());
            assertEquals(0.0, processor.getDiscountAmount(), 0.01);
            assertFalse(processor.isPaymentProcessed());
            assertNull(processor.getTransactionId());
            assertNull(processor.getShippingAddress());
            assertNull(processor.getTrackingNumber());
        }

        @Test
        @DisplayName("Should calculate base total from order items")
        void shouldCalculateBaseTotalFromOrderItems() {
            assertEquals(BASE_TOTAL, processor.getTotalAmount(), 0.01);
        }

        @Test
        @DisplayName("Should apply SAVE10 discount and populate temporary fields")
        void shouldApplySave10DiscountAndPopulateTemporaryFields() {
            double discountedTotal = processor.applyDiscount("SAVE10");

            // 10% of 1059.97 = 105.997
            assertEquals(105.997, processor.getDiscountAmount(), 0.01);
            assertEquals("SAVE10", processor.getAppliedDiscountCode());
            assertEquals(953.97, discountedTotal, 0.01);
        }

        @Test
        @DisplayName("Should apply SAVE20 discount and populate temporary fields")
        void shouldApplySave20DiscountAndPopulateTemporaryFields() {
            double discountedTotal = processor.applyDiscount("SAVE20");

            // 20% of 1059.97 = 211.994
            assertEquals(211.994, processor.getDiscountAmount(), 0.01);
            assertEquals("SAVE20", processor.getAppliedDiscountCode());
            assertEquals(847.976, discountedTotal, 0.01);
        }

        @Test
        @DisplayName("Should return zero discount for unknown discount code")
        void shouldReturnZeroDiscountForUnknownCode() {
            double discountedTotal = processor.applyDiscount("UNKNOWN");

            assertEquals(0.0, processor.getDiscountAmount(), 0.01);
            assertEquals(BASE_TOTAL, discountedTotal, 0.01);
        }

        @Test
        @DisplayName("Should process payment and populate temporary fields")
        void shouldProcessPaymentAndPopulateTemporaryFields() {
            boolean paid = processor.processPayment("CREDIT_CARD");

            assertTrue(paid);
            assertTrue(processor.isPaymentProcessed());
            assertNotNull(processor.getTransactionId());
            assertTrue(processor.getTransactionId().startsWith("TXN-"));
        }

        @Test
        @DisplayName("Should prepare shipment and populate temporary fields")
        void shouldPrepareShipmentAndPopulateTemporaryFields() {
            processor.prepareShipment("123 Main St, Springfield");

            assertEquals("123 Main St, Springfield", processor.getShippingAddress());
            assertEquals("TRKORD-001", processor.getTrackingNumber());
        }
    }

    @Nested
    @DisplayName("Temporary Field Refactored Tests")
    class TemporaryFieldRefactoredTests {

        private TemporaryFieldRefactored processor;

        @BeforeEach
        void setUp() {
            processor = new TemporaryFieldRefactored("ORD-002", customer, items);
        }

        @Test
        @DisplayName("Should have null info objects before each processing stage")
        void shouldHaveNullInfoObjectsBeforeEachProcessingStage() {
            assertNull(processor.getDiscountInfo());
            assertNull(processor.getPaymentInfo());
            assertNull(processor.getShipmentInfo());
        }

        @Test
        @DisplayName("Should calculate base total from order items")
        void shouldCalculateBaseTotalFromOrderItems() {
            assertEquals(BASE_TOTAL, processor.getTotalAmount(), 0.01);
        }

        @Test
        @DisplayName("Should create DiscountInfo with correct values when discount applied")
        void shouldCreateDiscountInfoWithCorrectValues() {
            double discountedTotal = processor.applyDiscount("SAVE10");

            DiscountInfo discountInfo = processor.getDiscountInfo();
            assertNotNull(discountInfo);
            assertEquals("SAVE10", discountInfo.getCode());
            assertEquals(105.997, discountInfo.getAmount(), 0.01);
            assertEquals(953.97, discountedTotal, 0.01);
        }

        @Test
        @DisplayName("Should create PaymentInfo with correct values when payment processed")
        void shouldCreatePaymentInfoWithCorrectValues() {
            boolean paid = processor.processPayment("CREDIT_CARD");

            PaymentInfo paymentInfo = processor.getPaymentInfo();
            assertNotNull(paymentInfo);
            assertTrue(paid);
            assertTrue(paymentInfo.isProcessed());
            assertEquals("CREDIT_CARD", paymentInfo.getMethod());
            assertNotNull(paymentInfo.getTransactionId());
            assertTrue(paymentInfo.getTransactionId().startsWith("TXN-"));
        }

        @Test
        @DisplayName("Should create ShipmentInfo with correct values when shipment prepared")
        void shouldCreateShipmentInfoWithCorrectValues() {
            processor.prepareShipment("123 Main St, Springfield");

            ShipmentInfo shipmentInfo = processor.getShipmentInfo();
            assertNotNull(shipmentInfo);
            assertEquals("123 Main St, Springfield", shipmentInfo.getAddress());
            assertEquals("TRKORD-002", shipmentInfo.getTrackingNumber());
        }

        @Test
        @DisplayName("Should format DiscountInfo toString correctly")
        void shouldFormatDiscountInfoToStringCorrectly() {
            processor.applyDiscount("SAVE10");

            String result = processor.getDiscountInfo().toString();
            assertTrue(result.contains("SAVE10"));
            assertTrue(result.contains("106.00")); // 105.997 formatted as %.2f
        }

        @Test
        @DisplayName("Should format PaymentInfo toString correctly")
        void shouldFormatPaymentInfoToStringCorrectly() {
            processor.processPayment("CREDIT_CARD");

            String result = processor.getPaymentInfo().toString();
            assertTrue(result.contains("CREDIT_CARD"));
            assertTrue(result.contains("true"));
        }

        @Test
        @DisplayName("Should format ShipmentInfo toString correctly")
        void shouldFormatShipmentInfoToStringCorrectly() {
            processor.prepareShipment("123 Main St, Springfield");

            String result = processor.getShipmentInfo().toString();
            assertTrue(result.contains("123 Main St, Springfield"));
            assertTrue(result.contains("TRKORD-002"));
        }
    }

    @Test
    @DisplayName("Should produce same discount results in both versions")
    void shouldProduceSameDiscountResultsInBothVersions() {
        TemporaryFieldSmell smell = new TemporaryFieldSmell("ORD-001", customer, items);
        TemporaryFieldRefactored refactored = new TemporaryFieldRefactored("ORD-001", customer, items);

        double smellTotal = smell.applyDiscount("SAVE10");
        double refactoredTotal = refactored.applyDiscount("SAVE10");

        assertEquals(smellTotal, refactoredTotal, 0.01);
        assertEquals(smell.getDiscountAmount(), refactored.getDiscountInfo().getAmount(), 0.01);
    }

    @Test
    @DisplayName("Should produce same payment results in both versions")
    void shouldProduceSamePaymentResultsInBothVersions() {
        TemporaryFieldSmell smell = new TemporaryFieldSmell("ORD-001", customer, items);
        TemporaryFieldRefactored refactored = new TemporaryFieldRefactored("ORD-001", customer, items);

        boolean smellPaid = smell.processPayment("CREDIT_CARD");
        boolean refactoredPaid = refactored.processPayment("CREDIT_CARD");

        assertEquals(smellPaid, refactoredPaid);
        assertEquals(smell.isPaymentProcessed(), refactored.getPaymentInfo().isProcessed());
    }

    @Test
    @DisplayName("Should produce same shipment results in both versions")
    void shouldProduceSameShipmentResultsInBothVersions() {
        TemporaryFieldSmell smell = new TemporaryFieldSmell("ORD-001", customer, items);
        TemporaryFieldRefactored refactored = new TemporaryFieldRefactored("ORD-001", customer, items);

        smell.prepareShipment("123 Main St, Springfield");
        refactored.prepareShipment("123 Main St, Springfield");

        assertEquals(smell.getShippingAddress(), refactored.getShipmentInfo().getAddress());
        assertEquals(smell.getTrackingNumber(), refactored.getShipmentInfo().getTrackingNumber());
    }
}
