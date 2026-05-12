package com.example.sms.repository;

import com.example.sms.entity.SchoolGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

/**
 * SchoolGroupRepository is a Spring Data JPA repository interface for managing
 * SchoolGroup entities in the database. It extends JpaRepository, providing
 * CRUD operations and additional query methods for SchoolGroup entities.
 */
@Repository
public interface SchoolGroupRepository extends
        JpaRepository<SchoolGroup, String> {
    
}
