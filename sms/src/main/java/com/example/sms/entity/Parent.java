package com.example.sms.entity;

import com.example.sms.util.Relation;
import jakarta.persistence.*;
import lombok.*;

/**
 * Represents a parent or guardian of a student in the school management system.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "parents")
public class Parent {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    
    /**
     * The student associated with this parent.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;
    
    @Column(nullable = false)
    private String name;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Relation relation;
    
    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;
    
    @Column(nullable = false)
    private String email;
    
    private String occupation;
    
    @Column(name = "is_primary", nullable = false)
    private boolean isPrimary = false;
    
    @Column(name = "login_username", unique = true, nullable = false)
    private String loginUsername;
    
    @Column(name = "login_password_hash", nullable = false)
    private String loginPasswordHash;
}