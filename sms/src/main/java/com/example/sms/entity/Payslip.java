package com.example.sms.entity;

import com.example.sms.util.PaymentStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

/**
 * Represents a payslip generated for a staff member during a payroll run.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "payslips")
public class Payslip {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    
    /**
     * The payroll run associated with this payslip.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "payroll_run_id", nullable = false)
    private PayrollRuns payrollRun;
    
    /**
     * The staff member for whom this payslip is generated.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "staff_id", nullable = false)
    private Staff staff;
    
    @Column(name = "basic_pay")
    private double basicPay;
    
    @Column(name = "gross_pay")
    private double grossPay;
    
    @Column(name = "pf_deduction")
    private double pfDeduction;
    
    @Column(name = "tds_deduction")
    private double tdsDeduction;
    
    @Column(name = "other_deduction")
    private double otherDeduction;
    
    @Column(name = "days_present")
    private int daysPresent;
    
    @Column(name = "days_absent")
    private int daysAbsent;
    
    @Column(name = "unpaid_leave_used")
    private int unpaidLeaveUsed;
    
    @Column(name = "level_deduction")
    private double levelDeduction;
    
    @Column(name = "net_pay", nullable = false)
    private double netPay;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "payment_status", nullable = false)
    private PaymentStatus paymentStatus;
    
    @Column(name = "transaction_ref")
    private String transactionRef;
    
    @Column(name = "paid_at")
    private LocalDateTime paidAt;
}