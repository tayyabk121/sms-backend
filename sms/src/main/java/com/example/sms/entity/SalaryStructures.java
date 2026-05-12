package com.example.sms.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

/**
 * Entity class representing a Salary Structure in the school management system.
 * It contains details about the salary structure, including its association with
 * a staff member, various pay and deduction components, working days per month,
 * and the effective date of the salary structure.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "salary_structures")
public class SalaryStructures {
   
    // Primary key for the SalaryStructures entity, generated as a UUID string.
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    
    // Relation with Staff
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "staff_id", nullable = false)
    private Staff staff;
    
    // Basic pay for the staff member. This field is required.
    @Column(name = "basic_pay", nullable = false)
    private Double basicPay;
    
    // House Rent Allowance for the staff member. This field is required.
    private double hra;
    
    // Dearness Allowance for the staff member. This field is required.
    private double da;
    
    // Medical Allowance for the staff member. This field is required.
    @Column(name = "medical_allowance")
    private double medicalAllowance;
    
    // Conveyance Allowance for the staff member. This field is required.
    @Column(name = "other_allowance")
    private double otherAllowance;
    
    // Provident Fund deduction for the staff member. This field is required.
    @Column(name = "pf_deduction")
    private double pfDeduction;
    
    // Tax Deducted at Source for the staff member. This field is required.
    @Column(name = "tds_deduction")
    private double tdsDeduction;
    
    // Other deductions for the staff member. This field is required.
    @Column(name = "other_deduction")
    private double otherDeduction;
    
    // Number of working days in a month for the staff member.
    // This field is required.
    @Column(name = "working_days_per_month", nullable = false)
    private int workingDaysPerMonth;
    
    // Effective date from which this salary structure is applicable.
    // This field is required.
    @Column(name = "effective_from", nullable = false)
    private LocalDate effectiveFrom;
    
}