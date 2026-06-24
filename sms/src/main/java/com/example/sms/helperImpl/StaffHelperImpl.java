package com.example.sms.helperImpl;

import com.example.sms.model.Staff;
import com.example.sms.exception.DeleteFailedException;
import com.example.sms.exception.IdNotFoundException;
import com.example.sms.helper.StaffHelper;
import com.example.sms.repository.StaffRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

/**
 * Implementation of the StaffHelper interface, providing methods to
 * find and delete staff members.
 */
@Log4j2
@Component
@RequiredArgsConstructor
public class StaffHelperImpl implements StaffHelper {
   
    /** Repository for performing CRUD operations on Staff entities. */
    private final StaffRepository staffRepository;
    
    /**
     * Finds a staff member by their ID.
     *
     * @param id The ID of the staff member to find.
     * @return The Staff entity corresponding to the provided ID.
     * @throws IdNotFoundException if no staff member with the given ID is found.
     */
    @Override
    public Staff findById(String id) {
        return staffRepository.findById(id).orElseThrow(()->
                new IdNotFoundException("Staff with id: " + id + " not found"));
    }
    
    /**
     * Deletes a staff member from the repository.
     *
     * @param staff The Staff entity to be deleted.
     * @throws DeleteFailedException if the deletion operation fails.
     */
    @Override
    public void delete(Staff staff) {
        try {
        staffRepository.delete(staff);
        
        }catch (Exception e){
            throw new DeleteFailedException("Failed to delete staff with id: "
                    + staff.getId());
        }
    }
    
    /**
     * Generates a unique employee number for a new staff member. The format
     * of the employee number is "EMP-XXXXX", where "XXXXX" is a zero-padded
     * sequential number. The method retrieves the last assigned employee number
     * from the repository, increments it, and formats it accordingly.
     *
     * @return A unique employee number for the new staff member.
     */
    @Override
    public String EmployeeGenerate() {
        
        log.info("Generating employee number for new staff member");
        
        Staff lastStaff = staffRepository.findTopByOrderByEmployeeNoDesc()
                .orElse(null);
        
        String employeeNo = "EMP-00001";
        
        if(lastStaff != null){
            String lastCode = lastStaff.getEmployeeNo();
            int number = Integer.parseInt(lastCode.substring(4));
            employeeNo = String.format("EMP-%05d", number + 1);
        }
        
        log.info("Generated employee number: {}", employeeNo);
        
        return employeeNo;
    }
}
