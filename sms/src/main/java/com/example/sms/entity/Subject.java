package com.example.sms.entity;

import jakarta.persistence.*;
import lombok.*;

/**
 * Entity class representing a Subject in the school management system.
 * It contains details about the subject, including its association with a branch
 * and its name.
 */
@Entity
@Table(name = "subjects")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Subject {
   
    // Primary key for the Subject entity, generated as a UUID string.
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    
    // Relation with Branch
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "branch_id", nullable = false)
    private Branch branch;
    
    // Name of the subject, such as "Mathematics" or "Science".
    // This field is required.
    @Column(nullable = false)
    private String name; // e.g., "Mathematics", "Science"
}