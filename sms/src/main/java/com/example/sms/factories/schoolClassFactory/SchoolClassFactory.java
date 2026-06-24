package com.example.sms.factories.schoolClassFactory;

import com.example.sms.util.RequestType;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Factory class to get the appropriate SchoolClassOperation implementation
 * based on the RequestType.
 */
@Component
@RequiredArgsConstructor
public class SchoolClassFactory {
    
    /** List of all SchoolClassOperation implementations.
     *  Spring will automatically inject all beans that implement the
     *  SchoolClassOperation interface.
     */
    private final List<SchoolClassOperation> schoolClassOperations;
    
    /** Map to hold the relationship between RequestType
     *  and its corresponding SchoolClassOperation implementation.
     */
    private final Map<RequestType,
            SchoolClassOperation> schoolClassOperationMap = new HashMap<>();
    
    /** Method annotated with @PostConstruct to initialize the
     *  schoolClassOperationMap after all the SchoolClassOperation beans have
     *  been injected.
     *  It populates the map with the request type as the key and the
     *  corresponding operation as the value.
     */
    @PostConstruct
    private void putValues(){
        schoolClassOperations.forEach(schoolClassOperation ->
                schoolClassOperationMap.put(
                        schoolClassOperation.getRequestType(),
                        schoolClassOperation));
    }
    
    /** Method to retrieve the appropriate SchoolClassOperation implementation
     *  based on the provided SchoolClassRequestType.
     *
     * @param requestType The type of school class request for which the operation
     *                    is needed.
     * @return The corresponding SchoolClassOperation implementation for the
     *         given request type.
     */
    public SchoolClassOperation getOperation(
            final RequestType requestType){
        
        return schoolClassOperationMap.get(requestType);
    }
}
