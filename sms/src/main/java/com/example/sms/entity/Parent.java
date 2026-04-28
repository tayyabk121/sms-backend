package com.example.sms.entity;

import com.example.sms.util.Relation;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

/**
 * Represents a parent or guardian of a student in the school management system.
 * Each parent is associated with a student and has contact information and login credentials.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Parent {
    
    /**
     * Unique identifier for the parent, generated as a UUID.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    
    /**
     * The student associated with this parent. This is a many-to-one relationship,
     * as one student can have multiple parents or guardians.
     */
    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student studentId;
    
    /**
     * The name of the parent or guardian. This field is required.
     */
    @Column(nullable = false)
    private String name;
    
    /**
     * The relation of the parent to the student
     * (e.g., Father, Mother, Guardian). This field is required.
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Relation relation;
    
    /**
     * The phone number of the parent or guardian. This field is required.
     */
    private String phoneNumber;
    
    /**
     * The email address of the parent or guardian. This field is required.
     */
    private String email;
    
    /**
     * The occupation of the parent or guardian. This field is optional.
     */
    private String occupation;
    
    /**
     * Indicates whether this parent is the primary contact for the student.
     * This field is required and should be true for only one parent per student.
     */
    @Column(nullable = false)
    private boolean isPrimary;
    
    /**
     * The username for the parent's login credentials.
     * This field is required and must be unique.
     */
    @Column(unique = true)
    private String loginUsername;
    
    /**
     * The password for the parent's login credentials. This field is required.
     * In a real application, this should be stored securely (e.g., hashed).
     */
    private String loginPasswordHash;
}
