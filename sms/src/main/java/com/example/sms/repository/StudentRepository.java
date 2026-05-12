package com.example.sms.repository;

import com.example.sms.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for managing Student entities in the database.
 * It extends JpaRepository, providing CRUD operations and additional
 * query methods for Student entities.
 */
@Repository
public interface StudentRepository extends JpaRepository<Student, String> {
}
