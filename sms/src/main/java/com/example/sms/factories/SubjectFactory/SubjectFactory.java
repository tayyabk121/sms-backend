package com.example.sms.factories.SubjectFactory;

import com.example.sms.util.requestType.SubjectRequestType;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** Factory class for managing SubjectOperations based on SubjectRequestType.
 * This class uses the @Component annotation to be recognized as a Spring bean
 * and @RequiredArgsConstructor to automatically generate a constructor for the
 * final fields. It initializes a map of SubjectRequestType to SubjectOperations
 * after the bean is constructed, allowing for easy retrieval of the appropriate
 * SubjectOperations implementation based on the request type. */
@Component
@RequiredArgsConstructor
public class SubjectFactory {
    
    /** List of SubjectOperations implementations that will be injected by Spring. */
    private final List<SubjectOperations> subjectOperations;
    
    /** Map to hold the association between SubjectRequestType and
     * SubjectOperations. */
    private final Map<SubjectRequestType,
            SubjectOperations> subjectOperationsMap = new HashMap<>();
    
    /** Initializes the subjectOperationsMap by iterating through the list of
     * SubjectOperations and putting them in the map with their corresponding
     * SubjectRequestType as the key. This method is annotated with @PostConstruct
     * to ensure it runs after the bean is fully constructed and dependencies are
     * injected. */
    @PostConstruct
    private void init() {
        subjectOperations.forEach(list ->
                subjectOperationsMap.put(list.getRequestType(),list));
    }
    
    /** Retrieves the SubjectOperations implementation associated with the given
     * SubjectRequestType from the subjectOperationsMap.
     *
     * @param requestType The SubjectRequestType for which to retrieve the
     * SubjectOperations.
     * @return The SubjectOperations implementation corresponding to the provided
     * requestType, or null if no matching implementation is found. */
    public SubjectOperations getOperation(
            final SubjectRequestType requestType){
        return subjectOperationsMap.get(requestType);
    }
}
