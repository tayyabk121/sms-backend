package com.example.sms.factories.schoolClassFactory;

import com.example.sms.model.SchoolClass;
import com.example.sms.mapper.AcademicYearMapper;
import com.example.sms.mapper.BranchMapper;
import com.example.sms.mapper.SchoolClassMapper;
import com.example.sms.repository.SchoolClassRepository;
import com.example.sms.request.SchoolClassRequest;
import com.example.sms.response.AcademicYearResponse;
import com.example.sms.response.BranchResponse;
import com.example.sms.response.SchoolClassResponse;
import com.example.sms.util.RequestType;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Service class responsible for handling the retrieval of all school classes.
 * It implements the SchoolClassOperation interface to define the specific
 * operation for finding all school classes.
 */
@Log4j2
@Service
@RequiredArgsConstructor
public class FindAllSchoolClassService implements SchoolClassOperation{
    
    /** Repository for performing CRUD operations on SchoolClass entities. */
    private final SchoolClassRepository schoolClassRepository;
    
    /** Mapper for converting between SchoolClass entities and
     * SchoolClassResponse objects. */
    private final SchoolClassMapper schoolClassMapper;
    
    /** Mapper for converting between Branch entities and BranchResponse objects,
     * used to include branch details in the school class responses. */
    private final BranchMapper branchMapper;
    
    /** Mapper for converting between AcademicYear entities and AcademicYearResponse objects,
     * used to include academic year details in the school class responses. */
    private final AcademicYearMapper academicYearMapper;
    
    
    private final List<SchoolClassResponse> schoolClassResponseList =
            new ArrayList<>();
    
    /**
     * Returns the type of school class request this service handles,
     * which is FIND_ALL.
     *
     * @return RequestType.FIND_ALL
     */
    @Override
    public RequestType getRequestType() {
        return RequestType.FIND_ALL;
    }
    
    /**
     * Performs the operation to find all school classes.
     * It retrieves all school class entities from the repository,
     * maps them to response objects, and returns a response containing
     * the list of school classes.
     *
     * @param request The SchoolClassRequest (not used in this operation).
     * @return A SchoolClassResponse containing the list of all school classes.
     */
    @Override
    public SchoolClassResponse performOperation(SchoolClassRequest request) {
        
        log.info("Finding all school classes");
        
        List<SchoolClass> list = schoolClassRepository.findAll();
        
        
        for (SchoolClass schoolClass : list){
            
            log.info("Mapping school class with ID: {}", schoolClass.getId());
            
            BranchResponse branchResponse = branchMapper.toResponse(
                    schoolClass.getBranch(), null, null);
            
            AcademicYearResponse academicYearResponse = academicYearMapper
                    .toResponse(
                            schoolClass.getAcademicYear(), null);
            
            SchoolClassResponse schoolClassResponse = schoolClassMapper.
                    toResponse(schoolClass, branchResponse,
                    academicYearResponse);
            
            schoolClassResponseList.add(schoolClassResponse);
            
        }
        
        return SchoolClassResponse.builder()
                .schoolClassResponseList(schoolClassResponseList)
                .build();
    }
}
