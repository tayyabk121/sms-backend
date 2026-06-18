package com.example.sms.repository;

import com.example.sms.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for managing Subject entities in the database.
 * It extends JpaRepository to provide CRUD operations and query methods for
 * Subject entities.
 */
@Repository
public interface SubjectRepository extends JpaRepository<Subject,String> {
}
