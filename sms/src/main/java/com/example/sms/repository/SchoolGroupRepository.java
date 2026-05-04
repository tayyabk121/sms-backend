package com.example.sms.repository;

import com.example.sms.entity.SchoolGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface SchoolGroupRepository extends
        JpaRepository<SchoolGroup, String> {
    
    Optional<SchoolGroup> findByName (String name);
}
