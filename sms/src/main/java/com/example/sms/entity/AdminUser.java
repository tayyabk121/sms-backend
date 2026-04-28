package com.example.sms.entity;

import com.example.sms.util.AdminRole;
import com.example.sms.util.AdminStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class AdminUser {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    
    @OneToMany
    @JoinColumn(name = "school_group_id")
    private SchoolGroup schoolGroupId;
    
    @OneToMany
    @JoinColumn(name = "Branch_id")
    private Branch branchId;
    
    @Column(nullable = false)
    private String name;
    
    @Column(nullable = false, unique = true)
    private String email;
    
    @Column(nullable = false)
    private String passwordHash;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AdminRole role;
    
    private String permissions;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AdminStatus status;

}
