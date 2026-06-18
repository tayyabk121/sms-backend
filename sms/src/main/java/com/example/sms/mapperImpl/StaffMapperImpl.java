package com.example.sms.mapperImpl;

import com.example.sms.model.Branch;
import com.example.sms.model.Staff;
import com.example.sms.mapper.StaffMapper;
import com.example.sms.request.StaffRequest;
import com.example.sms.response.StaffResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

/**
 * Implementation of the StaffMapper interface for mapping between
 * Staff entities and their corresponding request and response objects.
 * This class provides methods to convert between StaffRequest and Staff
 * entities, as well as between Staff entities and StaffResponse objects.
 */
@Log4j2
@Component
@RequiredArgsConstructor
public class StaffMapperImpl implements StaffMapper {
    
    @Override
    public Staff toEntity(StaffRequest request,
                          Branch branch,
                          String employeeNo) {
        return Staff.builder()
                .branch(branch)
                .employeeNo(employeeNo)
                .name(request.getName())
                .role(request.getRole())
                .phoneNumber(request.getPhoneNumber())
                .email(request.getEmail())
                .bankAccountNumber(request.getBankAccountNumber())
                .bankName(request.getBankName())
                .ifscCode(request.getIfscCode())
                .panCardNumber(request.getPanCardNumber())
                .status(request.getStatus())
                .joinedOn(LocalDate.now())
                .build();
    }
    
    @Override
    public StaffResponse toResponse(Staff staff) {
        return null;
    }
    
    @Override
    public Staff toUpdate(Staff staff, StaffRequest request) {
        return null;
    }
}
