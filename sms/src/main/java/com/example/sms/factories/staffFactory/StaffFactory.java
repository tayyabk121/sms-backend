package com.example.sms.factories.staffFactory;

import com.example.sms.util.RequestType;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** Factory class for managing StaffOperations based on RequestType.
 * This class uses the @Component annotation to be recognized as a Spring bean
 * and @RequiredArgsConstructor to automatically generate a constructor for the
 * final fields. It initializes a map of RequestType to StaffOperations
 * after the bean is constructed, allowing for easy retrieval of the appropriate
 * StaffOperations implementation based on the request type. */
@Component
@RequiredArgsConstructor
public class StaffFactory {
    
    /** List of StaffOperations implementations that will be injected by Spring. */
    private final List<StaffOperations> staffOperations;
    
    /** Map to hold the association between RequestType and
     * StaffOperations. */
    private final Map<RequestType,StaffOperations> staffOperationsMap =
            new HashMap<>();
    
    /** Initializes the staffOperationsMap by iterating through the list of
     * StaffOperations and putting them in the map with their corresponding
     * RequestType as the key. This method is annotated with @PostConstruct
     * to ensure it runs after the bean is fully constructed and dependencies are
     * injected. */
    @PostConstruct
    private void init(){
        staffOperations.forEach(list ->
                staffOperationsMap.put(list
                        .getRequestType(),list));
    }
    
    /** Retrieves the StaffOperations implementation associated with the given
     * RequestType from the staffOperationsMap.
     *
     * @param requestType The RequestType for which to retrieve the
     * StaffOperations.
     * @return The StaffOperations implementation corresponding to the provided
     * requestType, or null if no matching implementation is found. */
    public StaffOperations getOperation(
            final RequestType requestType){
        return staffOperationsMap.get(requestType);
    }
}
