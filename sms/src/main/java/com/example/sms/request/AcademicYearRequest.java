package com.example.sms.request;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class AcademicYearRequest {
    
    private String branch;
    
    private String label; // e.g., "2025-26"
    
    private LocalDate startDate;
    
    private LocalDate endDate;
    
    private boolean isCurrent;
}
