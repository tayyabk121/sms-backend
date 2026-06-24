package com.example.sms.factories.studentFactory;

import com.example.sms.util.RequestType;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class StudentFactory {
    
    private final List<StudentOperations> studentOperationsList;
    
    private final Map<RequestType,
            StudentOperations> studentOperationsMap = new HashMap<>();
    
    
    @PostConstruct
    private void putValue(){
        studentOperationsList.forEach(
                list -> studentOperationsMap
                        .put(list.getStudentRequestType(), list));
    }
    
    public StudentOperations getOperation(
            final RequestType requestType){
        return studentOperationsMap.get(requestType);
    }
}
