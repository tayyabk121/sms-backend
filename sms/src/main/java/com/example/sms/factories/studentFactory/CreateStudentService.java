package com.example.sms.factories.studentFactory;

import com.example.sms.entity.Student;
import com.example.sms.exception.StudentFailedException;
import com.example.sms.mapper.StudentMapper;
import com.example.sms.repository.StudentRepository;
import com.example.sms.request.StudentRequest;
import com.example.sms.response.StudentResponse;
import com.example.sms.util.requestType.StudentRequestType;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class CreateStudentService implements StudentOperations {
    
    private final StudentRepository studentRepository;
    
    private final StudentMapper studentMapper;
    
    
    @Override
    public StudentRequestType getStudentRequestType() {
        return StudentRequestType.CREATE;
    }
    
    @Override
    public StudentResponse performOperation(StudentRequest request) {
        log.info("Performing student operation for request: {}",
                request);
        
        Student entity = studentMapper.toEntity(request);
        
        try {
            studentRepository.save(entity);
        }catch (Exception e){
            throw new StudentFailedException("Failed to create student: "
                    + e.getMessage());
        }
        return StudentResponse.builder()
                .message("Created Student Profile Successful")
                .build();
    }
}
