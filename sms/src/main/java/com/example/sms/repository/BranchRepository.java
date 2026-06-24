package com.example.sms.repository;

import com.example.sms.model.Branch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for managing Branch entities in the database.
 * This interface extends JpaRepository, providing CRUD operations and
 * additional query methods for Branch entities.
 */
@Repository
public interface BranchRepository extends JpaRepository<Branch,String> {
}
