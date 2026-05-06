package com.example.sms.entity;

import jakarta.persistence.*;
import lombok.*;

/**
 * Represents a school group, which can encompass multiple branches.
 * Contains basic information about the school group such as name,
 * logo, and contact details.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "school_groups")
public class SchoolGroup {
    
    /**
     * Unique identifier for the school group, generated as a UUID.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    
    /**
     * The name of the school group (e.g., "ABC School Group").
     */
    @Column(nullable = false, unique = true)
    private String name;
    
    /**
     * The URL of the school group's logo.
     */
    @Column(name = "logo_url")
    private String logoUrl;
    
    /**
     * The contact email address for the school group.
     */
    @Column(name = "email", nullable = false, unique = true)
    private String email;
    
    /**
     * The contact phone number for the school group.
     */
    @Column(name = "phone_number", nullable = false, unique = true)
    private String phoneNumber;
    
    /**
     * The address of the school group.
     */
    @Column(columnDefinition = "TEXT")
    private String address;
}