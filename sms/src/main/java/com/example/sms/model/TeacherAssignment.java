package com.example.sms.model;

import jakarta.persistence.*;
import lombok.*;

/**
 * Entity class representing a Teacher Assignment in the school management system.
 * It contains details about the assignment of a teacher to a specific subject and
 * class for a given academic year. Each assignment is associated with a staff member,
 * subject, school class, and academic year. The unique constraint ensures that a teacher
 * cannot be assigned to the same subject and class more than once in the same academic year.
 */
@Entity
@Table(
        name = "teacher_assignments",
        uniqueConstraints = {
                // Prevent duplicate assignment: Same teacher, subject, class in same year
                @UniqueConstraint(
                        name = "unique_teacher_assignment",
                        columnNames = {"subject_id", "school_class_id", "staff_id", "academic_year_id"}
                )
        }
)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class TeacherAssignment {
   
    // Primary key for the TeacherAssignment entity, generated as a UUID string.
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    
    // Many-to-one relationship with the Subject entity,
    // indicating which subject is being taught.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subject_id", nullable = false)
    private Subject subject;
    
    // Many-to-one relationship with the SchoolClass entity,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "school_class_id", nullable = false)
    private SchoolClass schoolClass;
    
    // Many-to-one relationship with the Staff entity,
    // indicating which teacher is assigned.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "staff_id", nullable = false)
    private Staff staff;
    
    // Many-to-one relationship with the AcademicYear entity,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "academic_year_id", nullable = false)
    private AcademicYear academicYear;
}