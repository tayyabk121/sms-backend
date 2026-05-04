package com.example.sms.entity;

import jakarta.persistence.*;
import lombok.*;

/**
 * Maps which teacher (Staff) is teaching which Subject to which Class
 * for a specific Academic Year.
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
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subject_id", nullable = false)
    private Subject subject;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "school_class_id", nullable = false)
    private SchoolClass schoolClass;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "staff_id", nullable = false)
    private Staff staff;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "academic_year_id", nullable = false)
    private AcademicYear academicYear;
}