package com.example.sms.entity;

import com.example.sms.util.PaymentStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

/**
 * Entity class representing a Payslip in the school management system.
 * It contains details about the payslip, including its association with a
 * payroll run and a staff member, as well as various pay and deduction
 * details, payment status, and transaction information.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "payslips")
public class Payslip {
   
    // Primary key for the Payslip entity, generated as a UUID string.
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    
    // Relation with PayrollRuns
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "payroll_run_id", nullable = false)
    private PayrollRuns payrollRun;
    
    // Relation with Staff
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "staff_id", nullable = false)
    private Staff staff;
    
    // Basic pay for the staff member. This field is required.
    @Column(name = "basic_pay")
    private double basicPay;
    
    // Allowances for the staff member. This field is required.
    @Column(name = "gross_pay")
    private double grossPay;
    
    // Deductions for the staff member. This field is required.
    @Column(name = "pf_deduction")
    private double pfDeduction;
    
    // Deductions for the staff member. This field is required.
    @Column(name = "tds_deduction")
    private double tdsDeduction;
    
    // Deductions for the staff member. This field is required.
    @Column(name = "other_deduction")
    private double otherDeduction;
    
    // Number of days the staff member was present in the month.
    // This field is required.
    @Column(name = "days_present")
    private int daysPresent;
    
    // Number of days the staff member was absent in the month.
    // This field is required.
    @Column(name = "days_absent")
    private int daysAbsent;
    
    // Number of paid leave days used by the staff member in the month.
    @Column(name = "unpaid_leave_used")
    private int unpaidLeaveUsed;
    
    // Deductions based on the staff member's level or position.
    // This field is required.
    @Column(name = "level_deduction")
    private double levelDeduction;
    
    // Net pay for the staff member after all deductions. This field is required.
    @Column(name = "net_pay", nullable = false)
    private double netPay;
    
    // Status of the payment, defined as an enum.
    // This field is required and defaults to PENDING.
    @Enumerated(EnumType.STRING)
    @Column(name = "payment_status", nullable = false)
    private PaymentStatus paymentStatus;
    
    // Transaction reference for the payment,
    // which can be used for tracking and reconciliation.
    @Column(name = "transaction_ref")
    private String transactionRef;
    
    // Timestamp for when the payment was made.
    // This field can be null if not paid yet.
    @Column(name = "paid_at")
    private LocalDateTime paidAt;
}