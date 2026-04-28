package com.example.sms.entity;

import com.example.sms.util.PayrollRunsStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Represents a payroll run for a specific month and year,
 * associated with a school. Contains details about the total working days,
 * status of the payroll run, and processing information.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class PayrollRuns {
    
    /**
     * Unique identifier for the payroll run, generated as a UUID.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    
    /**
     * The school associated with this payroll run.
     */
    @OneToMany
    @JoinColumn(name = "branch_id")
    private Branch branchId;
    
    /**
     * The month for which the payroll run is being processed (e.g., "January").
     */
    @Column(nullable = false)
    private String month;
    
    /**
     * The year for which the payroll run is being processed (e.g., 2024).
     */
    @Column(nullable = false)
    private int year;
    
    /**
     * The total number of working days in the specified month and year.
     */
    @Column(nullable = false)
    private int totalWorkingDays;
    
    /**
     * The status of the payroll run (e.g., PENDING, COMPLETED, FAILED).
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PayrollRunsStatus status;
    
    /**
     * The date and time when the payroll run was processed.
     */
    private LocalDateTime processedAt;
    
    /**
     * The payslip associated with this payroll run, indicating who processed it.
     */
    @OneToMany
    @JoinColumn(name = "Payslip_id")
    private Payslip processedBy;
}
