package com.example.sms.response;


import com.example.sms.entity.Branch;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AcademicYearResponse {
    
    private String id;
    
    private Branch branch;
    
    private String label; // e.g., "2025-26"
    
    private LocalDate startDate;
    
    private LocalDate endDate;
    
    private boolean isCurrent;
    
    private String message;
    
}