package com.example.sms.mapperImpl;

import com.example.sms.entity.AcademicYear;
import com.example.sms.entity.Branch;
import com.example.sms.entity.SchoolGroup;
import com.example.sms.mapper.AcademicYearMapper;
import com.example.sms.mapper.BranchMapper;
import com.example.sms.mapper.SchoolGroupMapper;
import com.example.sms.request.BranchRequest;
import com.example.sms.response.AcademicYearResponse;
import com.example.sms.response.BranchResponse;
import com.example.sms.response.SchoolGroupResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation of the BranchMapper interface for converting between Branch
 * entities and their corresponding request and response objects. This class
 * provides methods for mapping a BranchRequest to a Branch entity, updating an
 * existing Branch entity with details from a request, and converting a Branch
 * entity to a BranchResponse.
 */
@Log4j2
@Component
@RequiredArgsConstructor
public class BranchMapperImpl implements BranchMapper {
   
    /** The SchoolGroupMapper is used to convert between SchoolGroup entities and
     * their corresponding request and response objects. It is injected into this
     * class to facilitate the mapping of SchoolGroup data when converting Branch
     * entities. */
    private final SchoolGroupMapper schoolGroupMapper;
    
    /** The AcademicYearMapper is used to convert between AcademicYear entities and
     * their corresponding request and response objects. It is injected into this
     * class to facilitate the mapping of AcademicYear data when converting Branch
     * entities. */
    private final AcademicYearMapper academicYearMapper;
    
    /** Converts a BranchRequest to a Branch entity, associating it with the
     * specified SchoolGroup. This method takes the details from the BranchRequest
     * and creates a new Branch entity, setting its properties based on the request
     * data and linking it to the provided SchoolGroup. If the request includes
     * AcademicYear data, it also creates AcademicYear entities and associates them
     * with the Branch. */
    @Override
    public Branch toEntity(BranchRequest request,
                           SchoolGroup schoolGroup) {
        
            
        Branch branch = Branch.builder()
                .schoolGroupId(schoolGroup)
                .name(request.getName())
                .address(request.getAddress())
                .city(request.getCity())
                .phoneNumber(request.getPhoneNumber())
                .email(request.getEmail())
                .status(request.getStatus())
                .build();
        
        if (request.getAcademicYear() != null) {
            List<AcademicYear> years = request.getAcademicYear().stream()
                    .map(yearReq -> {
                        return AcademicYear.builder()
                                .label(yearReq.getLabel())
                                .startDate(yearReq.getStartDate())
                                .endDate(yearReq.getEndDate()) // <--- YEH LINE MISSING HO SAKTI HAI
                                .branch(branch) // Relationship set karein
                                .isCurrent(yearReq.getIsCurrent())
                                .build();
                    })
                    .collect(Collectors.toList());
            branch.setAcademicYearId(years);
        }
        
        return branch;
    }
    
    /** Updates an existing Branch entity with details from a BranchRequest, while
     * maintaining the association with the specified SchoolGroup. This method
     * checks each field in the BranchRequest and updates the corresponding field in
     * the Branch entity if the request field is not null or empty. It also ensures
     * that the association with the SchoolGroup is maintained if a new SchoolGroup
     * ID is provided in the request. */
    @Override
    public Branch toUpdate(BranchRequest request, Branch branch,
                           SchoolGroup schoolGroup) {
        
        if (request.getSchoolGroupId() != null
                && !request.getSchoolGroupId().isEmpty()) {
            branch.setSchoolGroupId(schoolGroup);
        }
        
        if(request.getName() != null && !request.getName().isEmpty()) {
            branch.setName(request.getName());
        }
        
        if(request.getAddress() != null && !request.getAddress().isEmpty()) {
            branch.setAddress(request.getAddress());
        }
        
        if(request.getCity() != null && !request.getCity().isEmpty()) {
            branch.setCity(request.getCity());
        }
        
        if(request.getPhoneNumber() != null && !request.getPhoneNumber().isEmpty()) {
            branch.setPhoneNumber(request.getPhoneNumber());
        }
        
        if(request.getEmail() != null && !request.getEmail().isEmpty()) {
            branch.setEmail(request.getEmail());
        }
        
        if(request.getStatus() != null &&
                !request.getStatus().toString().isEmpty()) {
            branch.setStatus(request.getStatus());
        }
        
        
        return branch;
    }
    
    /** Converts a Branch entity to a BranchResponse, which can be returned to the
     * client. This method takes the details from the Branch entity and creates a
     * BranchResponse object, setting its properties based on the entity data. It
     * also converts the associated SchoolGroup and AcademicYear entities to their
     * corresponding response objects using the injected mappers. */
    @Override
    public BranchResponse toResponse(
            Branch branch) {
        
        SchoolGroupResponse schoolGroupResponse = schoolGroupMapper.toResponse(
                branch.getSchoolGroupId());
        
        List<AcademicYearResponse> academicYearList = branch.getAcademicYearId()
                .stream().map(academicYearMapper::toResponse).toList();
        
        return BranchResponse.builder()
                .id(branch.getId())
                .schoolGroupId(schoolGroupResponse)
                .name(branch.getName())
                .address(branch.getAddress())
                .city(branch.getCity())
                .phoneNumber(branch.getPhoneNumber())
                .email(branch.getEmail())
                .academicYearId(academicYearList)
                .status(branch.getStatus())
                .build();
    }
}
