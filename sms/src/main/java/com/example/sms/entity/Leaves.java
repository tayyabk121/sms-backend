package com.example.sms.entity;

import com.example.sms.util.LeavesStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

/**
 * Entity class representing a Leave in the school management system.
 * It contains details about the leave, including its association with
 * a staff member, leave policy, leave dates, reason for leave, status,
 * and whether it is a paid leave.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "leaves")
public class Leaves {
   
    // Primary key for the Leaves entity, generated as a UUID string.
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    
    // Relation with Staff
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "staff_id", nullable = false)
    private Staff staff;
    
    // Relation with LeavePolicies
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "leave_policy_id", nullable = false)
    private LeavePolicies leavePolicy;
    
    // Start date of the leave. This field is required.
    @Column(name = "from_date", nullable = false)
    private LocalDate fromDate;
    
    // End date of the leave. This field is required.
    @Column(name = "to_date", nullable = false)
    private LocalDate toDate;
    
    // Total number of days for the leave. This field is required.
    @Column(name = "total_days", nullable = false)
    private int totalDays;
    
    // Reason for the leave. This field is optional and can contain a longer text.
    @Column(columnDefinition = "TEXT")
    private String reason;
    
    // Status of the leave, defined as an enum.
    // This field is required and defaults to PENDING.
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private LeavesStatus status;
    
    // Indicates whether the leave is a paid leave. This field is required.
    @Column(name = "is_paid", nullable = false)
    private boolean isPaid = false;
}