package com.example.sms.entity;

import com.example.sms.util.Audience;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Represents an announcement in the school management system.
 * An announcement can be created by either a student or a staff member
 * and is intended for a specific audience.
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Announcement {
    /**
     * Unique identifier for the announcement, generated as a UUID.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    
    /**
     * The student who created the announcement, if applicable.
     * This is a one-to-many relationship, as a student can
     * create multiple announcements.
     */
    @ManyToOne
    @JoinColumn(name = "Branch_id")
    private Branch branchId;
    
    /**
     * The staff member who created the announcement, if applicable.
     * This is a one-to-many relationship, as a staff member can
     * create multiple announcements.
     */
    @ManyToOne
    @JoinColumn(name = "admin_user_id")
    private AdminUser adminUserId;
    
    /**
     * The title of the announcement. This field is required.
     */
    @Column(nullable = false)
    private String title;
    
    /**
     * The body of the announcement. This field is required.
     */
    @Column(nullable = false)
    private String body;
    
    /**
     * The intended audience for the announcement. This field is required
     * and can be one of the following: STUDENTS, STAFF, or ALL.
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Audience audience;
    
    /**
     * A flag indicating whether the announcement has been deleted. This field
     * is required and is set to false by default. When an announcement is
     * deleted, this flag is set to true instead of removing the record from
     * the database.
     */
    @Column(nullable = false)
    private boolean isDeleted = false;
    
    /**
     * The timestamp when the announcement was created. This field is required
     * and is automatically set to the current date and time when the announcement
     * is created.
     */
    @Column(nullable = false)
    private LocalDateTime createdAt;
}
