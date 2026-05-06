package com.example.sms.factories.branchFactory;

import com.example.sms.util.requestType.BranchRequestType;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class BranchFactory {
    
    private final List<BranchOperation> branchOperations;
    
    private final Map<BranchRequestType,
            BranchOperation> branchOperationMap = new HashMap<>();
    
    @PostConstruct
    private void putValues(){
        branchOperations.forEach(
                list -> branchOperationMap
                        .put(list.getRequestType(),list));
        
    }
    
    public BranchOperation getOperation(
            final BranchRequestType requestType) {
        
        return branchOperationMap.get(requestType);
    }
}
