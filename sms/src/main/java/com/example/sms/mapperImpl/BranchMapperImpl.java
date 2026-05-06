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


@Log4j2
@Component
@RequiredArgsConstructor
public class BranchMapperImpl implements BranchMapper {
    
    private final SchoolGroupMapper schoolGroupMapper;
    
    private final AcademicYearMapper academicYearMapper;
    
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
//                .academicYearId(request.getAcademicYear())
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
                                .build();
                    })
                    .collect(Collectors.toList());
            
            // 3. Branch mein list set karein
            branch.setAcademicYearId(years);
        }
        
        return branch;
    }
    
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
