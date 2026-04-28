package com.example.sms.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(
        name = "teacher_assignments",
        uniqueConstraints = {
                // Prevent duplicate assignment in same academic year
                @UniqueConstraint(
                        name = "unique_teacher_assignment",
                        columnNames = {"subject_id", "class_id", "staff_id", "academic_year_id"}
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
    private UUID id;
    
    @Column(name = "subject_id", nullable = false)
    private Subject subjectId;
    
    @Column(name = "class_id", nullable = false)
    private Class classId;
    
    @Column(name = "staff_id", nullable = false)
    private Staff staffId;
    
    @Column(name = "academic_year_id", nullable = false)
    private AcademicYear academicYearId;
}
