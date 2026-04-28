package com.example.sms.entity;

import com.example.sms.util.FeeType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

/**
 * Represents the fee structure for a specific class and term.
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FeeStructure {
    
    /**
     * Unique identifier for the fee structure.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    
    @OneToMany
    @JoinColumn(name = "branch_id", nullable = false)
    private Branch branchId;
    
    /**
     * The class associated with this fee structure.
     */
    @OneToOne
    @JoinColumn(name = "class_id")
    private  Class classId;
    
    @ManyToOne
    @JoinColumn(name = "academic_year_id")
    private AcademicYear academicYearId;
    
    /**
     * The term for which this fee structure applies (e.g., "Term 1", "Term 2").
     */
    @Column(nullable = false)
    private String term;
    
    /**
     * The type of fee (e.g., Tuition, Lab, Sports).
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FeeType feeType;
    
    /**
     * The amount to be paid for this fee structure.
     */
    @Column(nullable = false)
    private double amount;
    
    /**
     * The due date for the fee payment.
     */
    private LocalDate dueDate;
    
}
