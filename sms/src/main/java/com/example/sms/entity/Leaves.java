package com.example.sms.entity;

import com.example.sms.util.LeavesStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

/**
 * Represents a leave request made by a staff member.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "leaves")
public class Leaves {
    
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
    
    @Column(name = "from_date", nullable = false)
    private LocalDate fromDate;
    
    @Column(name = "to_date", nullable = false)
    private LocalDate toDate;
    
    @Column(name = "total_days", nullable = false)
    private int totalDays;
    
    @Column(columnDefinition = "TEXT")
    private String reason;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private LeavesStatus status;
    
    @Column(name = "is_paid", nullable = false)
    private boolean isPaid = false;
}