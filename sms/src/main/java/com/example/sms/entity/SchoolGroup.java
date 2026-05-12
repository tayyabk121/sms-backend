package com.example.sms.entity;

import jakarta.persistence.*;
import lombok.*;

/**
 * Entity class representing a School Group in the school management system.
 * It contains details about the school group, including its name, logo URL,
 * contact email, phone number, and address. Each school group can have multiple
 * branches associated with it.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "school_groups")
public class SchoolGroup {
    
    // Primary key for the SchoolGroup entity, generated as a UUID string.
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    
    // Name of the school group. This field is required and must be unique.
    @Column(nullable = false, unique = true)
    private String name;
    
    // URL of the school group's logo. This field is optional.
    @Column(name = "logo_url")
    private String logoUrl;
    
    // Contact email for the school group.
    // This field is required and must be unique.
    @Column(name = "email", nullable = false, unique = true)
    private String email;
    
    // Contact phone number for the school group.
    // This field is required and must be unique.
    @Column(name = "phone_number", nullable = false, unique = true)
    private String phoneNumber;
    
    // Address of the school group. This field is required.
    @Column(columnDefinition = "TEXT")
    private String address;
}