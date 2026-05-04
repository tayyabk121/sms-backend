package com.example.sms.factories.academicYearFactory;

import com.example.sms.util.requestType.AcademicYearRequestType;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class AcademicYearFactory {
    
    private final List<AcademicYearOperations> academicYearOperations;
    
    private final Map<AcademicYearRequestType,
            AcademicYearOperations> academicYearOperationsMap = new HashMap<>();
    
     
    @PostConstruct
    private void putValues(){
        academicYearOperations.forEach(
                list -> academicYearOperationsMap
                        .put(list.getRequestType(),list));
    }
    
    public AcademicYearOperations getOperation(
            final AcademicYearRequestType requestType){
        return academicYearOperationsMap.get(requestType);
    }
}
