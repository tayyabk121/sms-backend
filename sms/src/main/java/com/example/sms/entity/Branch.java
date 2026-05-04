package com.example.sms.entity;

import com.example.sms.util.BranchStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Branch {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    
    @ManyToOne
    @JoinColumn(name = "school_group_id", nullable = false)
    private SchoolGroup schoolGroupId;
    
    @Column(nullable = false)
    private String name;
    
    private String address;
    
    private String city;
    
    private String phone;
    
    private String email;
    
    @OneToMany(mappedBy = "branch", cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<AcademicYear> academicYearId;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BranchStatus status;
}
