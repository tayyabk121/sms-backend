package com.example.sms.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

/**
 * Response class for Academic Year related operations.
 * This class encapsulates the details of an academic year,
 * including its ID, branch, label, start and end dates,
 * current status, and any associated messages or lists of academic years.
 */
@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AcademicYearResponse {
    
    private String id;
    
    private BranchResponse branch;
    
    private String label; // e.g., "2025-26"
    
    private LocalDate startDate;
    
    private LocalDate endDate;
    
    private Boolean isCurrent;
    
    private String message;
    
    private List<AcademicYearResponse> academicYearResponseList;
    
}