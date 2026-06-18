package com.example.sms.model;

import com.example.sms.util.GradeEnum;
import jakarta.persistence.*;
import lombok.*;

/**
 * Entity class representing a Grade in the school management system.
 * It contains details about the grade, including its association with
 * a student, teacher assignment, academic year, term, marks, and grade.
 */
@Entity
@Table(name = "grades")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Grade {
   
    // Primary key for the Grade entity, generated as a UUID string.
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
    
    // Term for which the grade applies. This field is required.
    @Column(nullable = false)
    private String term; // e.g., "Term 1" or "Final Exam"
    
    // Marks obtained by the student. This field is required.
    @Column(nullable = false)
    private double marks;
    
    // Grade obtained by the student, defined as an enum. This field is required.
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private GradeEnum grade;
    
}