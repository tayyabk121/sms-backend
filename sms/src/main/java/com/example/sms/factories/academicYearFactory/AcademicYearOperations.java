package com.example.sms.factories.academicYearFactory;

import com.example.sms.request.AcademicYearRequest;
import com.example.sms.response.AcademicYearResponse;
import com.example.sms.util.requestType.AcademicYearRequestType;

public interface AcademicYearOperations {
    
    AcademicYearRequestType getRequestType();
    
    AcademicYearResponse performOperation(AcademicYearRequest request);
}
