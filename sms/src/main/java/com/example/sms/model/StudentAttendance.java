package com.example.sms.model;

import com.example.sms.util.StudentAttendanceStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

/**
 * Entity class representing Student Attendance in the school management system.
 * It contains details about the attendance of students, including their
 * association with a student, date of attendance, attendance status, and any
 * remarks related to the attendance.
 */
@Entity
@Table(name = "student_attendance", indexes = {
        @Index(name = "idx_student_attendance_date", columnList = "date")
})
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentAttendance {
   
    // Primary key for the StudentAttendance entity, generated as a UUID string.
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    
    // Relation with Student
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;
    
    // Date of attendance. This field is required and indexed for
    // efficient querying.
    @Column(nullable = false)
    private LocalDate date;
    
    // Attendance status, defined as an enum. This field is required and
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StudentAttendanceStatus status;
    
    // Remarks related to the attendance. This field is optional and can contain
    @Column(columnDefinition = "TEXT")
    private String remarks;
}