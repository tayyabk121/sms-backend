package com.example.sms.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(
        name = "academic_years",
        uniqueConstraints = {
                // Ensure only ONE current year per branch
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
    private UUID id;
    
    // FK → branches
    @Column(name = "branch_id", nullable = false)
    private Branch branchId;
    
    @Column(nullable = false)
    private String label; // e.g. 2025-26
    
    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;
    
    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;
    
    @Column(name = "is_current", nullable = false)
    private boolean isCurrent = false;
}
