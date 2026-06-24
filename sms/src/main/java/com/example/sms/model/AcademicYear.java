package com.example.sms.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

/**
 * Entity class representing an Academic Year in the school management system.
 * It contains details about the academic year, including its label,
 * start and end dates, and whether it is the current academic year. Each
 * academic year is associated with a branch.
 */
@Entity
@Table(name = "academic_years")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AcademicYear {
    
    // Primary key for the AcademicYear entity, generated as a UUID string.
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    
    // Many-to-one relationship with the Branch entity, indicating that each
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "branch_id", nullable = false)
    private Branch branch;
    
    // Label for the academic year, such as "2025-26". This field is required.
    @Column(nullable = false)
    private String label; // e.g., "2025-26"
    
    // Start date of the academic year. This field is required.
    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;
    
    // End date of the academic year. This field is required.
    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;
    
    // Indicates whether this academic year is the current one.
    // This field is required and defaults to false.
    @Column(name = "is_current", nullable = false)
    private Boolean isCurrent = false;
}