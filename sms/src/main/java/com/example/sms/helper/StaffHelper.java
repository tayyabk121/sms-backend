package com.example.sms.helper;

import com.example.sms.model.Staff;

/**
 * Interface defining helper methods for staff-related operations in the
 * school management system. This interface can be implemented to provide
 * utility functions that assist in handling staff data, such as validation,
 * formatting, or any other common tasks related to staff management.
 */
public interface StaffHelper {
    
    /** Finds a staff member by their unique identifier.
     *
     * @param id The unique identifier of the staff member to be found.
     * @return A Staff object representing the staff member with
     * the specified ID, or null if not found. */
    Staff findById(String id);
    
    /** Deletes a staff member from the system.
     *
     * @param staff The Staff object representing the staff member
     * to be deleted. */
    void delete(Staff staff);
    
    /** Generates a unique employee number for a staff member.
     *
     * @return A String representing the generated employee number. */
    String EmployeeGenerate();
}
