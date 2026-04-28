package com.example.sms.entity;

import com.example.sms.util.StaffAttendanceStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class StaffAttendance {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    
    @OneToMany
    @JoinColumn(name = "staff_id")
    private Staff staffId;
    
    @Column(nullable = false)
    private LocalDate date;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StaffAttendanceStatus status;
    
    private LocalTime checkInTime;
    
    private LocalTime checkOutTime;
    
    private String remarks;
}
