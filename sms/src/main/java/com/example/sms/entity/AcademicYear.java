package com.example.sms.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(
        name = "academic_years",
        uniqueConstraints = {
                // Ensures only ONE record can be 'is_current = true' for a specific branch
                @UniqueConstraint(
                        name = "unique_current_year_per_branch",
                        columnNames = {"branch_id", "is_current"}
                )
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AcademicYear {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    
    // Correct Relation: Many Academic Years belong to one Branch
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "branch_id", nullable = false)
    private Branch branch;
    
    @Column(nullable = false)
    private String label; // e.g., "2025-26"
    
    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;
    
    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;
    
    @Column(name = "is_current", nullable = false)
    private boolean isCurrent = false;
}