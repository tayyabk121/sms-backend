package com.example.sms.entity;

import com.example.sms.util.LeaveType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

/**
 * Represents the leave policies for a school, defining the types of leaves available,
 * the number of days allowed, and whether they are paid or can be carried forward.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class LeavePolicies {
    
    /**
     * Unique identifier for the leave policy.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    
    /**
     * The school to which this leave policy applies.
     */
    @OneToMany
    @JoinColumn(name = "branch_id")
    private Branch branchId;
    
    /**
     * The type of leave (e.g., sick leave, casual leave, etc.).
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private LeaveType leaveType;
    
    /**
     * The number of days allowed for this type of leave.
     */
    @Column(nullable = false)
    private int daysAllowed;
    
    /**
     * Indicates whether this type of leave is paid.
     */
    @Column(nullable = false)
    private boolean isPaid;
    
    /**
     * Indicates whether unused leave days can be carried forward to the next year.
     */
    @Column(nullable = false)
    private boolean carryForward = false;
}
