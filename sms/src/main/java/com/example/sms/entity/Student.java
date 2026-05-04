package com.example.sms.entity;

import com.example.sms.util.Gender;
import com.example.sms.util.StudentStatus;
import com.example.sms.util.Transport;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "students")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Student {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    
    /**
     * Relationship with SchoolClass.
     * Changed to @ManyToOne because many students belong to one class.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "class_id", nullable = false)
    private SchoolClass schoolClass;
    
    /**
     * Admission number or Enrollment ID.
     */
    @Column(name = "admission_no", unique = true, nullable = false)
    private String admissionNo;
    
    @Column(name = "first_name", nullable = false)
    private String firstName;
    
    @Column(name = "last_name", nullable = false)
    private String lastName;
    
    @Column(name = "date_of_birth")
    private LocalDate dob;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Gender gender;
    
    @Column(name = "blood_group")
    private String bloodGroup;
    
    @Enumerated(EnumType.STRING)
    private Transport transport;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StudentStatus status;
    
    @Column(name = "admitted_on")
    private LocalDate admittedOn;
}