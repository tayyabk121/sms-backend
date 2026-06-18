package com.example.sms.factories.userFactory;

import com.example.sms.util.RequestType;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Factory class to manage UserOperations based on RequestType.
 */
@Component
@RequiredArgsConstructor
public class UserFactory {
   
    /**
     * List of UserOperations implementations injected by Spring.
     */
    private final List<UserOperations> userOperations;
    
    /**
     * Map to hold RequestType to UserOperations mapping for quick lookup.
     */
    private final Map<RequestType, UserOperations>
            userOperationsMap = new HashMap<>();
    
    /**
     * Initializes the userOperationsMap after the bean is constructed.
     * This method populates the map with RequestType as key and corresponding
     * UserOperations implementation as value.
     */
    @PostConstruct
    private void init(){
        userOperations.forEach(list ->
                userOperationsMap.put(list.getRequestType(),list));
    }
    
    /**
     * Retrieves the UserOperations implementation based on the provided RequestType.
     *
     * @param requestType The type of request for which the operation is needed.
     * @return The corresponding UserOperations implementation.
     */
    public UserOperations getOperation(
            final RequestType requestType){
        return userOperationsMap.get(requestType);
    }
}
