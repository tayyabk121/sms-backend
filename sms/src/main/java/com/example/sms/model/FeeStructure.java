package com.example.sms.model;

import com.example.sms.util.FeeType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

/**
 * Entity class representing a Fee Structure in the school management system.
 * It contains details about the fee structure, including its association with
 * a branch, school class, academic year, term, fee type, amount, and due date.
 */
@Entity
@Table(name = "fee_structures")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FeeStructure {
   
    // Primary key for the FeeStructure entity, generated as a UUID string.
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
    
    // Term for which the fee structure applies. This field is required.
    @Column(nullable = false)
    private String term; // e.g., "Term 1"
    
    // Type of fee, defined as an enum. This field is required.
    @Enumerated(EnumType.STRING)
    @Column(name = "fee_type", nullable = false)
    private FeeType feeType;
    
    // Amount for the fee structure. This field is required.
    @Column(nullable = false)
    private double amount;
    
    // Due date for the fee payment. This field is required.
    @Column(name = "due_date")
    private LocalDate dueDate;
    
}