package com.example.sms.entity;

import com.example.sms.util.GradeEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

/**
 * Represents a grade assigned to a student for a specific subject and term.
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Grade {
    
    /**
     * Unique identifier for the grade record, generated as a UUID.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    
    /**
     * The student associated with this grade. This is a many-to-one
     * relationship, as a student can have multiple grades.
     */
   @OneToMany
   @JoinColumn(name = "student_id")
   private Student studentId;
   
    /**
     * The teacher assignment associated with this grade. This is a
     * many-to-one relationship, as a teacher can assign grades to
     * multiple students for different subjects and terms.
     */
   @OneToMany
   @JoinColumn(name = "teacher_assignment_id")
   private TeacherAssignment teacherAssignmentId;
   
   /**
    * The academic year for which the grade is assigned. This is a
    * many-to-one relationship, as an academic year can have multiple
    * grades assigned to different students and subjects.
    */
   @OneToMany
   @JoinColumn(name = "academic_year_id")
   private AcademicYear academicYearId;
    
    /**
     * The term for which the grade is assigned (e.g., "Fall 2023").
     * This field is required
     */
   @Column(nullable = false)
   private String term;
   
   /**
    * The marks obtained by the student for the subject in the specified term.
    * This field is required and should be a positive value.
    */
   private double marks;
   
   /**
    * The grade assigned based on the marks obtained. This is an enum
    * that categorizes the performance of the student (e.g., A, B, C, etc.).
    * This field is required.
    */
   @Enumerated(EnumType.STRING)
   private GradeEnum grade;
   
}
