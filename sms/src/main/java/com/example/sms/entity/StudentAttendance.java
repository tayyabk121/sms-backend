package com.example.sms.entity;

import com.example.sms.util.StudentAttendanceStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentAttendance {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    
    @OneToMany
    @JoinColumn(name = "student_id")
    private Student studentId;
    
    @Column(nullable = false)
    private LocalDate date;
    
    @Column(nullable = false)
    private StudentAttendanceStatus status;
    
    private String remarks;
    
}
