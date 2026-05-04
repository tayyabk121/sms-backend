package com.example.sms.repository;

import com.example.sms.entity.AcademicYear;
import com.example.sms.entity.Branch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AcademicYearRepository extends
        JpaRepository<AcademicYear, String> {
    
    Branch findBranchByBranchId(String branchId);
}
