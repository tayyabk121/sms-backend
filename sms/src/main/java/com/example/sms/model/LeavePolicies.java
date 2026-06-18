package com.example.sms.model;

import com.example.sms.util.LeaveType;
import jakarta.persistence.*;
import lombok.*;

/**
 * Entity class representing Leave Policies in the school management system.
 * It contains details about the leave policies, including their association
 * with a branch, type of leave, days allowed, whether it is paid, and if it
 * can be carried forward.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "leave_policies")
public class LeavePolicies {
   
    // Primary key for the LeavePolicies entity, generated as a UUID string.
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    
    // Relation with Branch
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "branch_id", nullable = false)
    private Branch branch;
    
    // Type of leave, defined as an enum. This field is required.
    @Enumerated(EnumType.STRING)
    @Column(name = "leave_type", nullable = false)
    private LeaveType leaveType;
    
    // Number of days allowed for this type of leave. This field is required.
    @Column(name = "days_allowed", nullable = false)
    private int daysAllowed;
    
    // Indicates whether the leave is paid. This field is required.
    @Column(name = "is_paid", nullable = false)
    private boolean isPaid;
    
    // Indicates whether the leave can be carried forward to the next year.
    // This field is required.
    @Column(name = "carry_forward", nullable = false)
    private boolean carryForward = false;
}