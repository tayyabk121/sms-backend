package com.example.sms.entity;

import com.example.sms.util.FeePaymentStatus;
import com.example.sms.util.PaymentMethod;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

/**
 * Entity representing a fee payment made by a student.
 */
@Entity
@Table(name = "fee_payments")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FeePayment { // Singular name is standard for Java classes
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    
    // Har payment kisi ek student se linked hoti hai
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;
    
    // Har payment kisi specific fee structure (tuition, bus, etc.) ke against hoti hai
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fee_structure_id", nullable = false)
    private FeeStructure feeStructure;
    
    @Column(name = "amount_paid", nullable = false)
    private double amountPaid;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "payment_method", nullable = false)
    private PaymentMethod paymentMethod;
    
    @Column(name = "transaction_id")
    private String transactionId;
    
    @CreationTimestamp
    @Column(name = "paid_at", updatable = false)
    private LocalDateTime paidAt;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FeePaymentStatus status;
}