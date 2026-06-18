package com.example.sms.model;

import com.example.sms.util.AdminStatus;
import com.example.sms.util.StaffRole;
import com.example.sms.util.UserRole;
import jakarta.persistence.*;
import lombok.*;

/**
 * Entity class representing an Admin User in the school management system.
 * It contains details about the admin user, including their name, email,
 * password hash, role, permissions, and status. Each admin user is associated
 * with a school group and a branch.
 */
@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class User {
    
    // Primary key for the AdminUser entity, generated as a UUID string.
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    
    // Relation with SchoolGroup
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "school_group_id")
    private SchoolGroup schoolGroup;
    
    // Relation with Branch
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "branch_id")
    private Branch branch;
    
    // Name of the admin user. This field is required.
//    @Column(nullable = false)
    private String name;
    
    // Email of the admin user. This field is required and must be unique.
    @Column(unique = true)
    private String email;
    
    // Phone number of the admin user. This field is required and must be unique.
    @Column(unique = true)
    private String phoneNumber;
    
    // Hashed password of the admin user. This field is required.
//    @Column(name = "password_hash", nullable = false)
    private String passwordHash;
    
    // Role of the admin user, defined as an enum. This field is required.
    @Enumerated(EnumType.STRING)
//    @Column(nullable = false)
    private UserRole role;
    
    // Permissions assigned to the admin user, stored as a text field.
    // This field is required.
    @Column(columnDefinition = "TEXT")
    private String permissions;
    
    // Status of the admin user, defined as an enum. This field is required.
    @Enumerated(EnumType.STRING)
//    @Column(nullable = false)
    private AdminStatus status;
    
    @Enumerated(EnumType.STRING)
//    @Column(nullable = false)
    private StaffRole staffRole;
    
}