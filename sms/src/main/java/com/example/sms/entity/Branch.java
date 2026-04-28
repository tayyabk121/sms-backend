package com.example.sms.entity;

import com.example.sms.util.BranchStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Branch {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    
    @OneToMany
    @JoinColumn(name = "school_group_id", nullable = false)
    private UUID schoolGroupId;
    
    @Column(nullable = false)
    private String name;
    
    private String address;
    
    private String city;
    
    private String phone;
    
    private String email;
    
    @OneToMany
    @JoinColumn(name = "academic_year_id")
    private AcademicYear academicYearId;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BranchStatus status;
}
