package com.example.sms.factories.academicYearFactory;

import com.example.sms.request.AcademicYearRequest;
import com.example.sms.response.AcademicYearResponse;
import com.example.sms.util.RequestType;

/**
 * Interface defining the contract for operations related to academic years.
 * Each implementation of this interface will handle a specific type of
 * academic year request, such as creating, updating, or deleting an
 * academic year. The interface includes methods to get the type of request
 * and to perform the operation based on the provided request.
 */
public interface AcademicYearOperations {
   
    /** Method to retrieve the type of academic year request that this operation
     *  implementation handles. It returns an AcademicYearRequestType enum
     *  value indicating the specific type of request (e.g., CREATE, UPDATE,
     *  DELETE). */
    RequestType getRequestType();
    
    /** Method to perform the operation based on the provided AcademicYearRequest.
     *  It takes an AcademicYearRequest as input, processes it according to the
     *  specific operation (e.g., creating, updating, or deleting an academic
     *  year), and returns an AcademicYearResponse indicating the result of the
     *  operation. */
    AcademicYearResponse performOperation(AcademicYearRequest request);
}
