package com.gdms.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for Payment functionality.
 */
class PaymentTest {
    private Payment payment;

    @BeforeEach
    void setUp() {
        payment = new Payment(
            1,
            101,
            99.99,
            "Credit Card",
            "Monthly membership fee"
        );
    }

    @Test
    void testPaymentCreation() {
        assertNotNull(payment);
        assertEquals(1, payment.getPaymentId());
        assertEquals(101, payment.getMemberId());
        assertEquals(99.99, payment.getAmount());
        assertEquals("Credit Card", payment.getPaymentMethod());
        assertEquals("Monthly membership fee", payment.getDescription());
        assertEquals(LocalDate.now(), payment.getPaymentDate());
        assertEquals("Pending", payment.getStatus());
    }

    @Test
    void testProcessPayment() {
        String transactionId = "TXN123456";
        assertTrue(payment.processPayment(transactionId));
        assertEquals("Completed", payment.getStatus());
        assertEquals(transactionId, payment.getTransactionId());
    }

    @Test
    void testRefundPayment() {
        // Cannot refund a pending payment
        assertFalse(payment.refundPayment("Customer request"));
        assertEquals("Pending", payment.getStatus());

        // Process payment first
        payment.processPayment("TXN123456");
        
        // Now refund should work
        assertTrue(payment.refundPayment("Customer request"));
        assertEquals("Refunded", payment.getStatus());
        assertTrue(payment.getDescription().contains("Refunded: Customer request"));
    }

    @Test
    void testGenerateReceipt() {
        payment.processPayment("TXN123456");
        String receipt = payment.generateReceipt();
        
        assertTrue(receipt.contains("Payment ID: 1"));
        assertTrue(receipt.contains("Member ID: 101"));
        assertTrue(receipt.contains("Amount: $99.99"));
        assertTrue(receipt.contains("Status: Completed"));
        assertTrue(receipt.contains("Payment Method: Credit Card"));
        assertTrue(receipt.contains("Transaction ID: TXN123456"));
    }

    @Test
    void testEqualsAndHashCode() {
        Payment samePayment = new Payment(
            1,
            101,
            99.99,
            "Credit Card",
            "Monthly membership fee"
        );
        Payment differentPayment = new Payment(
            2,
            102,
            149.99,
            "PayPal",
            "Annual membership fee"
        );

        assertEquals(payment, samePayment);
        assertNotEquals(payment, differentPayment);
        assertEquals(payment.hashCode(), samePayment.hashCode());
        assertNotEquals(payment.hashCode(), differentPayment.hashCode());
    }

    @Test
    void testToString() {
        String paymentString = payment.toString();
        assertTrue(paymentString.contains("paymentId=1"));
        assertTrue(paymentString.contains("memberId=101"));
        assertTrue(paymentString.contains("amount=99.99"));
        assertTrue(paymentString.contains("status='Pending'"));
        assertTrue(paymentString.contains("paymentMethod='Credit Card'"));
    }

    @Test
    void testStatusTransitions() {
        // Initial status
        assertEquals("Pending", payment.getStatus());
        
        // Process payment
        payment.processPayment("TXN123456");
        assertEquals("Completed", payment.getStatus());
        
        // Refund payment
        payment.refundPayment("Customer request");
        assertEquals("Refunded", payment.getStatus());
    }

    @Test
    void testPaymentDateUpdate() {
        LocalDate newDate = LocalDate.now().plusDays(1);
        payment.setPaymentDate(newDate);
        assertEquals(newDate, payment.getPaymentDate());
    }

    @Test
    void testAmountUpdate() {
        double newAmount = 149.99;
        payment.setAmount(newAmount);
        assertEquals(newAmount, payment.getAmount());
    }
} 