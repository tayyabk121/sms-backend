package com.example.sms.factories.schoolGroupFactory;

import com.example.sms.util.requestType.SchoolGroupRequestType;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
/**
 * Factory class to handle operations related to school group requests.
 * This class implements the SchoolGroupOperations interface
 * and provides implementations for the defined methods.
 */
public class SchoolGroupFactory {
    
    /** List of operations that can be performed on school group requests. */
    private final List<SchoolGroupOperations> schoolGroupOperations;
    
    /** Map to associate request types with their corresponding operations. */
    private final Map<SchoolGroupRequestType,
            SchoolGroupOperations> schoolGroupOperationsMap = new HashMap<>();
    
    /**
     * Method for initializing the operation map after the bean is constructed.
     * It populates the map with the available operations,
     * based on their request types.
     */
    @PostConstruct
    private void putValues(){
        schoolGroupOperations.forEach(
                list -> schoolGroupOperationsMap
                        .put(list.getSchoolGroupRequestType(),list));
    }
    
    /**
     * Method to perform the operation based on the request type.
     * It retrieves the appropriate operation from the map and executes it.
     *
     * @param requestType the type of the request indicating which operation to
     *                    perform.
     * @return SchoolGroupOperations containing the result of the
     * operation.
     */
    public SchoolGroupOperations getOperation(
            final SchoolGroupRequestType requestType){
        return schoolGroupOperationsMap.get(requestType);
    }
}
