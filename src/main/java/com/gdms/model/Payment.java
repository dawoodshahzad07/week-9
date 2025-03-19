package com.gdms.model;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Represents a payment in the system.
 * Manages payment details, status, and receipt generation.
 */
public class Payment {
    private int paymentId;
    private int memberId;
    private double amount;
    private LocalDate paymentDate;
    private String status; // Pending, Completed, Failed, Refunded
    private String paymentMethod;
    private String transactionId;
    private String description;

    /**
     * Constructor for creating a new payment.
     * @param paymentId Unique identifier for the payment
     * @param memberId ID of the member making the payment
     * @param amount Payment amount
     * @param paymentMethod Method of payment (e.g., "Credit Card", "PayPal")
     * @param description Payment description
     */
    public Payment(int paymentId, int memberId, double amount, String paymentMethod, String description) {
        this.paymentId = paymentId;
        this.memberId = memberId;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.description = description;
        this.paymentDate = LocalDate.now();
        this.status = "Pending";
    }

    // Getters and Setters
    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Processes the payment and updates its status.
     * @param transactionId ID from payment gateway
     * @return true if payment processed successfully, false otherwise
     */
    public boolean processPayment(String transactionId) {
        this.transactionId = transactionId;
        this.status = "Completed";
        return true;
    }

    /**
     * Refunds the payment.
     * @param reason Reason for refund
     * @return true if refund processed successfully, false otherwise
     */
    public boolean refundPayment(String reason) {
        if ("Completed".equals(this.status)) {
            this.status = "Refunded";
            this.description = "Refunded: " + reason;
            return true;
        }
        return false;
    }

    /**
     * Generates a receipt string for the payment.
     * @return Formatted receipt string
     */
    public String generateReceipt() {
        return String.format("""
            Receipt
            -------
            Payment ID: %d
            Member ID: %d
            Amount: $%.2f
            Date: %s
            Status: %s
            Payment Method: %s
            Transaction ID: %s
            Description: %s
            """,
            paymentId, memberId, amount, paymentDate, 
            status, paymentMethod, transactionId, description);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Payment payment)) return false;
        return paymentId == payment.paymentId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(paymentId);
    }

    @Override
    public String toString() {
        return "Payment{" +
                "paymentId=" + paymentId +
                ", memberId=" + memberId +
                ", amount=" + amount +
                ", paymentDate=" + paymentDate +
                ", status='" + status + '\'' +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", transactionId='" + transactionId + '\'' +
                '}';
    }
} 