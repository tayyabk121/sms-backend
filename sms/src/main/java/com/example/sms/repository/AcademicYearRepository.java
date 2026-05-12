package com.example.sms.repository;

import com.example.sms.entity.AcademicYear;
import com.example.sms.entity.Branch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for managing AcademicYear entities in the database.
 * It extends JpaRepository to provide CRUD operations and custom query methods.
 */
@Repository
public interface AcademicYearRepository extends
        JpaRepository<AcademicYear, String> {
    
}
