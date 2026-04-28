package com.example.sms.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
/**
 * Represents a school group, which can encompass multiple branches.
 * Contains basic information about the school group such as name,
 * logo, and contact details.
 */
public class SchoolGroup {
    /**
     * Unique identifier for the school group, generated as a UUID.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    
    /**
     * The name of the school group (e.g., "ABC School Group").
     */
    @Column(nullable = false)
    private String name;
    
    /**
     * The URL of the school group's logo. This field is optional.
     */
    private String logoUrl;
    
    /**
     * The contact email address for the school group. This field is optional.
     */
    private String contactEmail;
    
    /**
     * The contact phone number for the school group. This field is optional.
     */
    private String contactPhone;
    
    /**
     * The address of the school group. This field is optional.
     */
    private String address;
}
