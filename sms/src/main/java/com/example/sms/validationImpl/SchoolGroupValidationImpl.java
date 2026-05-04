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
    
    private final SchoolGroupMapper schoolGroupMapper;
    
    @Override
    public SchoolGroup FindById(String name) {
        
        return schoolGroupRepository.findById(name).orElseThrow(()->
                new SchoolGroupIdNotFoundException(
                        "School Group with name " + name + " not found"));
        
    }
    
//    @Override
//    public SchoolGroup create(SchoolGroup schoolGroup) {
//        return null;
//    }
//
    @Override
    public SchoolGroup update(SchoolGroupRequest request,
                              SchoolGroup schoolGroup) {
        
        SchoolGroup update = schoolGroupMapper.toUpdate(
                request, schoolGroup);
        
        return schoolGroupRepository.save(update);
        
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
    
    @Override
    public List<SchoolGroup> schoolGroupList() {
        return List.of();
    }
}
