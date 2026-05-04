package com.example.sms.entity;

import com.example.sms.util.StaffAttendanceStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

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
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id; // Consistent with other entities (String UUID)
    
    // Relation with Staff
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "staff_id", nullable = false)
    private Staff staff;
    
    @Column(nullable = false)
    private LocalDate date;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StaffAttendanceStatus status;
    
    @Column(name = "check_in_time")
    private LocalTime checkInTime;
    
    @Column(name = "check_out_time")
    private LocalTime checkOutTime;
    
    @Column(columnDefinition = "TEXT")
    private String remarks;
}