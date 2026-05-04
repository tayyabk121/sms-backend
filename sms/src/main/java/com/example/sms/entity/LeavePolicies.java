package com.example.sms.entity;

import com.example.sms.util.LeaveType;
import jakarta.persistence.*;
import lombok.*;

/**
 * Represents the leave policies for a school, defining the types of leaves available,
 * the number of days allowed, and whether they are paid or can be carried forward.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "leave_policies")
public class LeavePolicies {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    
    // Relation with Branch
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "branch_id", nullable = false)
    private Branch branch;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "leave_type", nullable = false)
    private LeaveType leaveType;
    
    @Column(name = "days_allowed", nullable = false)
    private int daysAllowed;
    
    @Column(name = "is_paid", nullable = false)
    private boolean isPaid;
    
    @Column(name = "carry_forward", nullable = false)
    private boolean carryForward = false;
}