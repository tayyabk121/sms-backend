package com.example.sms.repository;

import com.example.sms.entity.Jwt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JwtRepository extends JpaRepository<Jwt,Long> {
    Optional<Jwt> findByJwt(String token);
}
