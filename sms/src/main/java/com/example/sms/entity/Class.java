package com.example.sms.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

/**
 * Represents a class in the school management system.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Class {
    
    /**
     * Unique identifier for the class, generated as a UUID.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    
    /**
     * The school to which this class belongs.
     */
    @ManyToOne
    @JoinColumn(name = "branch_id")
    private Branch branchId;
    
    @ManyToOne
    @JoinColumn(name = "academic_year_id")
    private AcademicYear academicYearId;
    
    /**
     * The name of the class (e.g., "Class 10A").
     */
    @Column(nullable = false)
    private String name;
    
    /**
     * The grade level of the class (e.g., 10).
     */
    @Column(nullable = false)
    private int gradeLevel;
    
    /**
     * The section of the class (e.g., "A", "B").
     */
    private String section;
}
