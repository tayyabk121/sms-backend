package com.example.sms.model;

import com.example.sms.util.BranchStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Entity class representing a Branch in the school management system.
 * It contains details about the branch, including its name, address,
 * contact information, and status. Each branch is associated with a
 * school group and can have multiple academic years.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Branch {
   
    // Primary key for the Branch entity, generated as a UUID string.
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    
    // Many-to-one relationship with the SchoolGroup entity, indicating that each
    @ManyToOne
    @JoinColumn(name = "school_group_id", nullable = false)
    private SchoolGroup schoolGroupId;
    
    // Name of the branch. This field is required.
    @Column(nullable = false)
    private String name;
    
    // Address of the branch. This field is required.
    private String address;
    
    // City where the branch is located. This field is required.
    private String city;
    
    // State where the branch is located. This field is required.
    private String phoneNumber;
    
    // Email address for the branch. This field is required and must be unique.
    private String email;
    
    // One-to-many relationship with the AcademicYear entity,
    // indicating that a branch can have multiple academic years.
    // CascadeType.ALL ensures that related academic years are
    // persisted, updated, and deleted along with the branch.
    // orphanRemoval = true ensures that if an academic year is removed
    // from the list, it will be deleted from the database.
    @OneToMany(mappedBy = "branch", cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<AcademicYear> academicYearId = new ArrayList<>();
    
    // Status of the branch, defined as an enum. This field is required.
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BranchStatus status;
}
