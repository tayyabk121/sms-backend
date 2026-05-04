package com.example.sms.entity;

import com.example.sms.util.PayrollRunsStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

/**
 * Represents a payroll run for a specific month and year.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "payroll_runs")
public class PayrollRuns {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    
    // Relation with Branch
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "branch_id", nullable = false)
    private Branch branch;
    
    @Column(nullable = false)
    private String month; // e.g., "January"
    
    @Column(nullable = false)
    private int year; // e.g., 2024
    
    @Column(name = "total_working_days", nullable = false)
    private int totalWorkingDays;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PayrollRunsStatus status;
    
    @Column(name = "processed_at")
    private LocalDateTime processedAt;
    
    /**
     * The admin user who processed this payroll run.
     * Fixed: Changed from Payslip to AdminUser for correct logic.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "processed_by_admin_id")
    private AdminUser processedBy;
}