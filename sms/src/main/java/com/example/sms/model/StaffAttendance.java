package com.example.sms.model;

import com.example.sms.util.StaffAttendanceStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Entity class representing Staff Attendance in the school management system.
 * It contains details about the attendance of staff members, including their
 * association with a staff member, date of attendance, attendance status,
 * check-in and check-out times, and any remarks related to the attendance.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "staff_attendance", indexes = {
        @Index(name = "idx_staff_attendance_date", columnList = "date")
})
public class StaffAttendance {
   
    // Primary key for the StaffAttendance entity, generated as a UUID string.
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id; // Consistent with other entities (String UUID)
    
    // Relation with Staff
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "staff_id", nullable = false)
    private Staff staff;
    
    // Date of attendance. This field is required and indexed for
    // efficient querying.
    @Column(nullable = false)
    private LocalDate date;
    
    // Attendance status, defined as an enum. This field is required and
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StaffAttendanceStatus status;
    
    // Check-in time for the staff member. This field is optional and can be
    @Column(name = "check_in_time")
    private LocalTime checkInTime;
    
    // Check-out time for the staff member. This field is optional and can be
    @Column(name = "check_out_time")
    private LocalTime checkOutTime;
    
    // Remarks related to the attendance. This field is optional and can contain
    @Column(columnDefinition = "TEXT")
    private String remarks;
}