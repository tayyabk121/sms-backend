package com.example.sms.entity;

import com.example.sms.util.PayrollRunsStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

/**
 * Entity class representing a Payroll Run in the school management system.
 * It contains details about the payroll run, including its association with
 * a branch, the month and year of the payroll, total working days, status,
 * processing timestamp, and the admin user who processed it.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "payroll_runs")
public class PayrollRuns {
   
    // Primary key for the PayrollRuns entity, generated as a UUID string.
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    
    // Relation with Branch
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "branch_id", nullable = false)
    private Branch branch;
    
    // Month of the payroll run, stored as a string (e.g., "January").
    // This field is required.
    @Column(nullable = false)
    private String month; // e.g., "January"
    
    // Year of the payroll run, stored as an integer (e.g., 2024).
    // This field is required.
    @Column(nullable = false)
    private int year; // e.g., 2024
    
    // Total working days in the month for this payroll run.
    // This field is required.
    @Column(name = "total_working_days", nullable = false)
    private int totalWorkingDays;
    
    // Status of the payroll run, defined as an enum. This field is required
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PayrollRunsStatus status;
    
    // Timestamp when the payroll run was processed.
    // This field is optional and can be null if not processed yet.
    @Column(name = "processed_at")
    private LocalDateTime processedAt;
    
    // Relation with AdminUser who processed the payroll run. This field is optional
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "processed_by_admin_id")
    private AdminUser processedBy;
}