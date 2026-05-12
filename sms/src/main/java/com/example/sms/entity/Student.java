package com.example.sms.entity;

import com.example.sms.util.Gender;
import com.example.sms.util.StudentStatus;
import com.example.sms.util.Transport;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

/**
 * Entity class representing a Student in the school management system.
 * It contains details about the student, including their association with
 * a school class, admission number, personal details, and status.
 */
@Entity
@Table(name = "students")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Student {
   
    // Primary key for the Student entity, generated as a UUID string.
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    
    // Relation with SchoolClass
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "class_id", nullable = false)
    private SchoolClass schoolClass;
    
    // Unique admission number for the student.
    // This field is required and must be unique.
    @Column(name = "admission_no", unique = true, nullable = false)
    private String admissionNo;
    
    // First name of the student. This field is required.
    @Column(name = "first_name", nullable = false)
    private String firstName;
    
    // Last name of the student. This field is required.
    @Column(name = "last_name", nullable = false)
    private String lastName;
    
    // Date of birth of the student. This field is optional.
    @Column(name = "date_of_birth")
    private LocalDate dob;
    
    // Gender of the student, defined as an enum. This field is required.
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Gender gender;
    
    // Blood group of the student. This field is optional.
    @Column(name = "blood_group")
    private String bloodGroup;
    
    // Transport mode for the student, defined as an enum. This field is optional.
    @Enumerated(EnumType.STRING)
    private Transport transport;
    
    // Status of the student, defined as an enum.
    // This field is required and defaults to ACTIVE.
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StudentStatus status;
    
    // Date of admission for the student. This field is optional.
    @Column(name = "admitted_on")
    private LocalDate admittedOn;
}