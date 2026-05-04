package com.example.sms.entity;

import com.example.sms.util.FeeType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

/**
 * Represents the fee structure for a specific class and term.
 */
@Entity
@Table(name = "fee_structures")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FeeStructure {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    
    // Relation with Branch
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "branch_id", nullable = false)
    private Branch branch;
    
    // Relation with SchoolClass (Reserved keyword 'Class' avoided)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "School_class_id", nullable = false)
    private SchoolClass schoolClassId;
    
    // Relation with Academic Year
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "academic_year_id", nullable = false)
    private AcademicYear academicYear;
    
    @Column(nullable = false)
    private String term; // e.g., "Term 1"
    
    @Enumerated(EnumType.STRING)
    @Column(name = "fee_type", nullable = false)
    private FeeType feeType;
    
    @Column(nullable = false)
    private double amount;
    
    @Column(name = "due_date")
    private LocalDate dueDate;
    
}