package com.example.sms.entity;

import com.example.sms.util.StudentAttendanceStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

/**
 * Entity representing daily attendance records for students.
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
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    
    /**
     * The student whose attendance is being recorded.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;
    
    @Column(nullable = false)
    private LocalDate date;
    
    /**
     * Attendance status (e.g., PRESENT, ABSENT, LATE, EXCUSED).
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StudentAttendanceStatus status;
    
    @Column(columnDefinition = "TEXT")
    private String remarks;
}