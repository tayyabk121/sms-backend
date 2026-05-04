package com.example.sms.entity;

import jakarta.persistence.*;
import lombok.*;

/**
 * Represents a subject taught in the school.
 */
@Entity
@Table(name = "subjects")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Subject {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    
    /**
     * Relationship with Branch.
     * Subjects can be specific to a branch.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "branch_id", nullable = false)
    private Branch branch;
    
    @Column(nullable = false)
    private String name; // e.g., "Mathematics", "Science"
}