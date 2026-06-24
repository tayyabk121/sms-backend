package com.example.sms.repository;

import com.example.sms.model.SchoolGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * SchoolGroupRepository is a Spring Data JPA repository interface for managing
 * SchoolGroup entities in the database. It extends JpaRepository, providing
 * CRUD operations and additional query methods for SchoolGroup entities.
 */
@Repository
public interface SchoolGroupRepository extends
        JpaRepository<SchoolGroup, String> {
    
}
