package com.example.sms.entity;

import com.example.sms.util.AdminRole;
import com.example.sms.util.AdminStatus;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "admin_users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class AdminUser {
    
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
    
    @Column(nullable = false)
    private String name;
    
    @Column(nullable = false, unique = true)
    private String email;
    
    @Column(name = "password_hash", nullable = false)
    private String passwordHash;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AdminRole role;
    
    @Column(columnDefinition = "TEXT") // Permissions usually lambi hoti hain
    private String permissions;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AdminStatus status;
    
}