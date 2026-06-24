package com.example.sms.mapper;

import com.example.sms.model.Branch;
import com.example.sms.model.Staff;
import com.example.sms.request.StaffRequest;
import com.example.sms.response.StaffResponse;

/**
 * Mapper interface for Staff entity.
 * This interface will be used to define methods for mapping between
 * StaffRequest and StaffResponse DTOs, as well as mapping between Staff
 * entity and its corresponding DTOs.
 */
public interface StaffMapper {
    
    /** Method to convert a StaffRequest object to a Staff entity.
     *
     * @param request The StaffRequest containing the details of the staff
     * member to be created or updated.
     * @param branch The Branch entity associated with the staff member, used
     * to set the branch details in the Staff entity.
     * @return A Staff entity populated with the details from the StaffRequest
     * and associated Branch. */
    Staff toEntity(StaffRequest request, Branch branch,
                   String employeeNo);
    
    /** Method to convert a Staff entity to a StaffResponse object.
     *
     * @param staff The Staff entity containing the details of the staff
     * member.
     * @return A StaffResponse object populated with the details from the
     * Staff entity. */
    StaffResponse toResponse(Staff staff);
    
    /** Method to update an existing Staff entity with details from aStaffRequest.
     * @param staff The existing Staff entity to be updated.
     * @param request The StaffRequest containing the new details for the staff member.
     * @return The updated Staff entity with the new details from the request. */
    Staff toUpdate(Staff staff, StaffRequest request);
}
