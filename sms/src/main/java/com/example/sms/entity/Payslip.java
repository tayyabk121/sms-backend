package com.example.sms.entity;

import com.example.sms.util.PaymentStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Represents a payslip generated for a staff member during a payroll run.
 * Contains details about the salary components, deductions, and payment status.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Payslip {
    
    /**
     * Unique identifier for the payslip, generated as a UUID.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    
    /**
     * The payroll run associated with this payslip.
     */
    @ManyToOne
    @JoinColumn(name = "payroll_run_id")
    private PayrollRuns payrollRunsId;
    
    /**
     * The staff member for whom this payslip is generated.
     */
    @ManyToOne
    @JoinColumn(name = "staff_id")
    private Staff staffId;
    
    /**
     * The basic pay component of the salary.
     */
    private double basicPay;
    
    /**
     * The gross pay calculated before deductions.
     */
    private double grossPay;
    
    /**
     * The provident fund (PF) deduction from the salary.
     */
    private double pfDeduction;
    
    /**
     * The tax deducted at source (TDS) from the salary.
     */
    private double tdsDeduction;
    
    /**
     * Any other deductions from the salary.
     */
    private double otherDeduction;
    
    /**
     * The total number of working days in the month for which
     * the payslip is generated.
     */
    private int daysPresent;
    
    /**
     * The total number of days the staff member was absent during
     * the month for which the payslip is generated.
     */
    private int daysAbsent;
    
    /**
     * The total number of unpaid leave days used by the staff member
     * during the month for which the payslip is generated.
     */
    private int unpaidLeaveUsed;
    
    /**
     * The total number of paid leave days used by the staff member
     * during the month for which the payslip is generated.
     */
    private double levelDeduction;
    
    /**
     * The net pay calculated after all allowances and deductions.
     */
    @Column(nullable = false)
    private double netPay;
    
    /**
     * The current payment status of the payslip (e.g., PENDING, PAID, FAILED).
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentStatus paymentStatus;
    
    /**
     * The date and time when the payment was made, if applicable.
     */
    private String transactionRef;
    
    /**
     * The date and time when the payment was made, if applicable.
     */
    private LocalDateTime paidAt;
}
