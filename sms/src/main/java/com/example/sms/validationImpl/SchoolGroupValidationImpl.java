package com.example.sms.validationImpl;

import com.example.sms.entity.SchoolGroup;
import com.example.sms.exception.DeleteFailedException;
import com.example.sms.exception.SchoolGroupIdNotFoundException;
import com.example.sms.mapper.SchoolGroupMapper;
import com.example.sms.repository.SchoolGroupRepository;
import com.example.sms.request.SchoolGroupRequest;
import com.example.sms.validation.SchoolGroupValidation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Log4j2
@Component
@RequiredArgsConstructor
public class SchoolGroupValidationImpl implements SchoolGroupValidation {
    
    private final SchoolGroupRepository schoolGroupRepository;
    
    @Override
    public SchoolGroup findById(String id) {
        
        return schoolGroupRepository.findById(id).orElseThrow(()->
                new SchoolGroupIdNotFoundException(
                        "School Group with id " + id + " not found"));
        
    }
    
    @Override
    public void delete(SchoolGroup schoolGroup) {
        try {
            schoolGroupRepository.delete(schoolGroup);
        }catch (Exception e){
            throw new DeleteFailedException(
                    "Failed to delete School Group with name "
                    + schoolGroup.getName() + ": " + e.getMessage());
        }
    }
}
