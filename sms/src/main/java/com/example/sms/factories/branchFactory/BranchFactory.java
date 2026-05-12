package com.example.sms.factories.branchFactory;

import com.example.sms.util.requestType.BranchRequestType;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Factory class responsible for managing and providing the appropriate
 * BranchOperation implementations based on the BranchRequestType.
 * It initializes a mapping of request types to their corresponding
 * operations and allows retrieval of the correct operation for a given
 * request type.
 */
@Component
@RequiredArgsConstructor
public class BranchFactory {
   
    /** List of BranchOperation implementations that will be injected by Spring. */
    private final List<BranchOperation> branchOperations;
    
    /** Map to hold the association between BranchRequestType and BranchOperation. */
    private final Map<BranchRequestType,
            BranchOperation> branchOperationMap = new HashMap<>();
    
    /**
     * Initializes the branchOperationMap by populating it with the
     * BranchOperation implementations from the branchOperations list.
     * This method is called after the bean is constructed and dependencies
     * are injected.
     */
    @PostConstruct
    private void putValues(){
        branchOperations.forEach(
                branchOperation -> branchOperationMap
                        .put(branchOperation.getRequestType(),branchOperation));
        
    }
    
    /**
     * Retrieves the BranchOperation implementation corresponding to the
     * given BranchRequestType.
     *
     * @param requestType The type of branch request for which the operation
     * is needed.
     * @return The BranchOperation associated with the provided request type,
     * or null if no operation is found for that type.
     */
    public BranchOperation getOperation(
            final BranchRequestType requestType) {
        
        return branchOperationMap.get(requestType);
    }
}
