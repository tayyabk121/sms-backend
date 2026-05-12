package com.example.sms.factories.academicYearFactory;

import com.example.sms.util.requestType.AcademicYearRequestType;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Factory class responsible for managing and providing instances of
 * AcademicYearOperations based on the type of academic year request.
 * It uses a map to associate each AcademicYearRequestType with its
 * corresponding AcademicYearOperations implementation.
 */
@Component
@RequiredArgsConstructor
public class AcademicYearFactory {
   
    /** List of AcademicYearOperations implementations to be injected by Spring. */
    private final List<AcademicYearOperations> academicYearOperations;
    
    /** Map to hold the association between AcademicYearRequestType and
     *  AcademicYearOperations implementations. */
    private final Map<AcademicYearRequestType,
            AcademicYearOperations> academicYearOperationsMap = new HashMap<>();
    
    /** Method annotated with @PostConstruct to populate the academicYearOperationsMap
     *  after the bean is constructed and dependencies are injected. It iterates
     *  through the list of AcademicYearOperations and puts each operation into
     *  the map with its corresponding request type as the key. */
    @PostConstruct
    private void putValues(){
        academicYearOperations.forEach(
                list -> academicYearOperationsMap
                        .put(list.getRequestType(),list));
    }
    
    /** Method to retrieve the appropriate AcademicYearOperations implementation
     *  based on the provided AcademicYearRequestType. It looks up the request
     *  type in the academicYearOperationsMap and returns the corresponding
     *  operation. If no operation is found for the given request type, it
     *  returns null. */
    public AcademicYearOperations getOperation(
            final AcademicYearRequestType requestType){
        return academicYearOperationsMap.get(requestType);
    }
}
