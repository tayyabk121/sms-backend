package com.example.sms.entity;

import com.example.sms.util.Relation;
import jakarta.persistence.*;
import lombok.*;

/**
 * Entity class representing a Parent in the school management system.
 * It contains details about the parent, including their association with
 * a student, name, relation to the student, contact information, occupation,
 * and login credentials for accessing the parent portal.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "parents")
public class Parent {
   
    // Primary key for the Parent entity, generated as a UUID string.
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    
    // Relation with Student
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;
    
    // Name of the parent. This field is required.
    @Column(nullable = false)
    private String name;
    
    // Relation to the student, defined as an
    // enum (e.g., FATHER, MOTHER, GUARDIAN). This field is required.
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Relation relation;
    
    // Phone number of the parent. This field is required.
    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;
    
    // Email address of the parent. This field is required.
    @Column(nullable = false)
    private String email;
    
    // Occupation of the parent. This field is optional.
    private String occupation;
    
    // Indicates whether this parent is the primary contact for the student.
    @Column(name = "is_primary", nullable = false)
    private boolean isPrimary = false;
    
    // Login username for the parent portal. This field is required and must be unique.
    @Column(name = "login_username", unique = true, nullable = false)
    private String loginUsername;
    
    // Hashed password for the parent portal login. This field is required.
    @Column(name = "login_password_hash", nullable = false)
    private String loginPasswordHash;
}