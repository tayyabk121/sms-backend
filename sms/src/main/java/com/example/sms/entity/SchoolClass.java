package com.example.sms.entity;

import jakarta.persistence.*;
import lombok.*;

/**
 * Represents a class in the school management system.
 * Renamed to SchoolClass because 'Class' is a reserved keyword in Java.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "school_classes") // Standard plural naming
public class SchoolClass {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    
    /**
     * Relationship with Branch.
     * Many classes can belong to a single branch.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "branch_id", nullable = false)
    private Branch branch;
    
    /**
     * Relationship with AcademicYear.
     * Each class is tied to a specific academic session.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "academic_year_id", nullable = false)
    private AcademicYear academicYear;
    
    @Column(nullable = false)
    private String name; // e.g., "Class 10-A"
    
    @Column(name = "grade_level", nullable = false)
    private int gradeLevel; // e.g., 10
    
    @Column(name = "section")
    private String section; // e.g., "A"
}