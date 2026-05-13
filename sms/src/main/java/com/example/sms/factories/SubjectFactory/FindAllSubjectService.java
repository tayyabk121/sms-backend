package com.example.sms.factories.SubjectFactory;

import com.example.sms.entity.Subject;
import com.example.sms.mapper.SubjectMapper;
import com.example.sms.repository.SubjectRepository;
import com.example.sms.request.SubjectRequest;
import com.example.sms.response.SubjectResponse;
import com.example.sms.util.requestType.SubjectRequestType;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class for handling the operation of finding all subjects in the school
 * management system. This class implements the SubjectOperations interface and
 * provides the logic to retrieve all Subject entities from the database, map
 * them to SubjectResponse objects, and return a list of these responses.
 */
@Log4j2
@RequiredArgsConstructor
@Service
public class FindAllSubjectService implements SubjectOperations{
   
    /** Repository for accessing Subject entities in the database. */
    private final SubjectRepository subjectRepository;
    
    /** Mapper for converting between Subject entities and DTOs. */
    private final SubjectMapper subjectMapper;
    
    /** Method to get the type of request this service handles, which is FIND_ALL in
     * this case. This method is used to identify the type of operation that this
     * service performs when processing a SubjectRequest. */
    @Override
    public SubjectRequestType getRequestType() {
        return SubjectRequestType.FIND_ALL;
    }
    
    /** Method to perform the operation of finding all subjects.
     * This method retrieves all Subject entities from the database using the
     * SubjectRepository, maps each Subject entity to a SubjectResponse using
     * the SubjectMapper, and returns a SubjectResponse containing a list of
     * all the subjects. The implementation uses Java Streams to efficiently
     * map the list of Subject entities to a list of SubjectResponse objects. */
    @Override
    public SubjectResponse performOperation(SubjectRequest request) {
        
        log.info("Performing FIND_ALL operation for subjects");
        
        List<Subject> list = subjectRepository.findAll();
        
        List<SubjectResponse> subjectResponseList = list.stream().map(
                subjectMapper::toResponse).toList();
        
        return SubjectResponse.builder()
                .subjectResponseList(subjectResponseList)
                .build();
    }
}
