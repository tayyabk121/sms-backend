package com.example.sms.repository;

import com.example.sms.model.SchoolClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for managing SchoolClass entities in the database.
 * This interface extends JpaRepository, providing CRUD operations and
 * additional query methods for SchoolClass entities.
 */
@Repository
public interface SchoolClassRepository
        extends JpaRepository<SchoolClass, String> {
}
