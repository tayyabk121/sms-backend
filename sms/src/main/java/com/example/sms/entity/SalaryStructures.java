package com.example.sms.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

/**
 * Represents the salary components and structure for a specific staff member.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "salary_structures")
public class SalaryStructures {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    
    /**
     * One-to-One mapping with Staff.
     * Each staff member typically has one active salary structure.
     */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "staff_id", nullable = false)
    private Staff staff;
    
    @Column(name = "basic_pay", nullable = false)
    private Double basicPay;
    
    private double hra;
    
    private double da;
    
    @Column(name = "medical_allowance")
    private double medicalAllowance;
    
    @Column(name = "other_allowance")
    private double otherAllowance;
    
    @Column(name = "pf_deduction")
    private double pfDeduction;
    
    @Column(name = "tds_deduction")
    private double tdsDeduction;
    
    @Column(name = "other_deduction")
    private double otherDeduction;
    
    @Column(name = "working_days_per_month", nullable = false)
    private int workingDaysPerMonth;
    
    @Column(name = "effective_from", nullable = false)
    private LocalDate effectiveFrom;
    
}