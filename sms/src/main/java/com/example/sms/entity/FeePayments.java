package com.example.sms.entity;

import com.example.sms.util.FeePaymentStatus;
import com.example.sms.util.PaymentMethod;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Entity representing fee payments made by students.
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FeePayments {
    
    /**
     * Unique identifier for each fee payment record.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    
    /**
     * Reference to the student making the payment.
     */
    @OneToMany
    @JoinColumn(name = "student_id")
    private Student studentId;
    
    /**
     * Reference to the fee structure associated with this payment.
     */
    @OneToMany
    @JoinColumn(name = "fee_structure_id")
    private FeeStructure feeStructureId;
    
    /**
     * The amount paid by the student.
     */
    @Column(nullable = false)
    private double amountPaid;
    
    /**
     * The method used for payment (e.g., Credit Card, Bank Transfer).
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentMethod paymentMethod;
    
    /**
     * Transaction ID provided by the payment gateway.
     */
    private String transactionId;
    
    /**
     * Timestamp when the payment was made.
     */
    private LocalDateTime paidAt;
    
    /**
     * Current status of the fee payment (e.g., PENDING, COMPLETED, FAILED).
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FeePaymentStatus status;
}
