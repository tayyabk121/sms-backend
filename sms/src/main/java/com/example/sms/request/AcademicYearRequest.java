package com.example.sms.request;


import com.example.sms.util.requestType.AcademicYearRequestType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class AcademicYearRequest {
    
    private String id;
    
    private String branchId;
    
    private String label; // e.g., "2025-26"
    
    private LocalDate startDate;
    
    private LocalDate endDate;
    
    private boolean isCurrent;
    
    private AcademicYearRequestType academicYearRequestType;
    
}
