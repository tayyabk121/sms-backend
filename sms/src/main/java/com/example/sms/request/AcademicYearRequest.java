package com.example.sms.request;


import com.example.sms.util.RequestType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

/**
 * Represents a request for creating or updating an academic year.
 * This class contains fields for the academic year's ID, branch ID,
 * label, start and end dates, current status, and the type of request
 * being made (e.g., create or update).
 */
@Getter
@Setter
public class AcademicYearRequest {
    
    private String id;
    
    private String branchId;
    
    private String label; // e.g., "2025-26"
    
    private LocalDate startDate;
    
    private LocalDate endDate;
    
    private Boolean isCurrent;
    
    private RequestType requestType;
    
}
