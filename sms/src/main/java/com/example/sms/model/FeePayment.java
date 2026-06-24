package com.example.sms.model;

import com.example.sms.util.FeePaymentStatus;
import com.example.sms.util.PaymentMethod;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

/**
 * Entity class representing a Fee Payment in the school management system.
 * It contains details about the fee payment, including the student who made
 * the payment, the fee structure it is associated with, the amount paid,
 * payment method, transaction ID, payment timestamp, and payment status.
 */
@Entity
@Table(name = "fee_payments")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FeePayment { // Singular name is standard for Java classes
    
    // Primary key for the FeePayment entity, generated as a UUID string.
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    
    // Many-to-one relationship with the Student entity,
    // indicating that each fee payment
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;
    
    // Many-to-one relationship with the FeeStructure entity,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fee_structure_id", nullable = false)
    private FeeStructure feeStructure;
    
    // Amount paid for the fee. This field is required.
    @Column(name = "amount_paid", nullable = false)
    private double amountPaid;
    
    // Payment method used for the fee payment, defined as an enum.
    // This field is required.
    @Enumerated(EnumType.STRING)
    @Column(name = "payment_method", nullable = false)
    private PaymentMethod paymentMethod;
    
    // Transaction ID for the payment, which can be used for tracking and reconciliation.
    @Column(name = "transaction_id")
    private String transactionId;
    
    // Timestamp for when the fee payment was made. This field is automatically
    @CreationTimestamp
    @Column(name = "paid_at", updatable = false)
    private LocalDateTime paidAt;
    
    // Status of the fee payment, defined as an enum. This field is required.
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FeePaymentStatus status;
}