package com.example.sms.entity;

import com.example.sms.util.GradeEnum;
import jakarta.persistence.*;
import lombok.*;

/**
 * Represents a grade assigned to a student for a specific subject and term.
 */
@Entity
@Table(name = "grades")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Grade {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    
    // Relation with Student
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;
    
    // Relation with TeacherAssignment (Which teacher taught which subject)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_assignment_id", nullable = false)
    private TeacherAssignment teacherAssignment;
    
    // Relation with Academic Year
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "academic_year_id", nullable = false)
    private AcademicYear academicYear;
    
    @Column(nullable = false)
    private String term; // e.g., "Term 1" or "Final Exam"
    
    @Column(nullable = false)
    private double marks;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private GradeEnum grade;
    
}