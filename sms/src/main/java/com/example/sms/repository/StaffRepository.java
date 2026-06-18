package com.example.sms.repository;

import com.example.sms.model.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/** Repository interface for managing Staff entities in the database. This
 * interface extends JpaRepository, providing basic CRUD operations and query
 * methods for Staff entities. It allows for easy access and manipulation of
 * Staff data in the database, including operations such as saving, finding,
 * updating, and deleting Staff records. The repository can be used by service
 * classes to perform database operations related to Staff entities. */
@Repository
public interface StaffRepository extends JpaRepository<Staff,String> {

    Optional<Staff> findTopByOrderByEmployeeNoDesc();
}
