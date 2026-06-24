package com.example.sms.helper;

import com.example.sms.model.AcademicYear;

/**
 * Interface for validating operations related to AcademicYear entities.
 */
public interface AcademicYearHelper {
    
    /** Validates finding an AcademicYear by its ID.
     * @param id the ID of the AcademicYear to find
     * @return the AcademicYear entity if found,
     * otherwise null or an exception may be thrown
     */
    AcademicYear findById(String id);
    
    /** Validates saving an AcademicYear entity.
     * @param academicYear the AcademicYear entity to Delete*/
     void delete(AcademicYear academicYear);
}
