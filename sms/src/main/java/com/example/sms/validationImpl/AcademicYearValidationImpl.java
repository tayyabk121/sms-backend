package com.example.sms.validationImpl;

import com.example.sms.entity.AcademicYear;
import com.example.sms.exception.AcademicYearIdNotFoundException;
import com.example.sms.exception.DeleteFailedException;
import com.example.sms.repository.AcademicYearRepository;
import com.example.sms.validation.AcademicYearValidation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

@Log4j2
@Component
@RequiredArgsConstructor
public class AcademicYearValidationImpl implements AcademicYearValidation {
    
    private final AcademicYearRepository academicYearRepository;
    
    @Override
    public AcademicYear findById(String id) {
        
        return academicYearRepository.findById(id).orElseThrow(()->
                new AcademicYearIdNotFoundException(
                        "Academic Year with id " + id + " not found"));
    }
    
    @Override
    public void delete(AcademicYear academicYear) {
        
        try{
            academicYearRepository.delete(academicYear);
        }catch (Exception e){
            throw new DeleteFailedException(
                    "Failed to delete Academic Year with label "
                    + academicYear.getLabel() + ": " + e.getMessage());
        }
    }
}
