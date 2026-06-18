package com.example.sms.model;

import jakarta.persistence.*;
import lombok.*;

/**
 * Entity class representing a School Class in the school management system.
 * It contains details about the class, including its association with a branch
 * and an academic year, as well as its name, grade level, and section.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "school_classes") // Standard plural naming
public class SchoolClass {
   
    // Primary key for the SchoolClass entity, generated as a UUID string.
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    
    // Relationship with Branch.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "branch_id", nullable = false)
    private Branch branch;
    
    // Relationship with Academic Year.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "academic_year_id", nullable = false)
    private AcademicYear academicYear;
    
    // Name of the class, such as "Class 10-A". This field is required.
    @Column(nullable = false)
    private String name; // e.g., "Class 10-A"
    
    // Grade level of the class, such as 10 for "Class 10-A".
    // This field is required.
    @Column(name = "grade_level", nullable = false)
    private Integer gradeLevel; // e.g., 10
    
    // Section of the class, such as "A" for "Class 10-A". This
    @Column(name = "section")
    private String section; // e.g., "A"
}