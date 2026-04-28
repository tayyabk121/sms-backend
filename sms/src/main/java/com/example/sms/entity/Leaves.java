package com.example.sms.entity;

import com.example.sms.util.LeavesStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

/**
 * Represents a leave request made by a staff member.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Leaves {
    
    /**
     * Unique identifier for the leave request.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    
    /**
     * The staff member who made the leave request.
     */
    @ManyToOne
    @JoinColumn(name = "staff_id")
    private Staff staffId;
    
    /**
     * The leave policy associated with this leave request.
     */
    @ManyToOne
    @JoinColumn(name = "leave_policies_id")
    private LeavePolicies leavePoliciesId;
    
    /**
     * The start date of the leave.
     */
    @Column(nullable = false)
    private LocalDate fromDate;
    
    /**
     * The end date of the leave.
     */
    @Column(nullable = false)
    private LocalDate toDate;
    
    /**
     * The total number of days for the leave.
     */
    @Column(nullable = false)
    private int totalDays;
    
    /**
     * The reason for the leave request.
     */
    private String reason;
    
    /**
     * The current status of the leave request (e.g., PENDING, APPROVED, REJECTED).
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private LeavesStatus status;
    
    /**
     * Indicates whether the leave has been paid or not.
     */
    private boolean isPaid;
}
