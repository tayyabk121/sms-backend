package com.example.sms.entity;

import com.example.sms.util.Audience;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

/**
 * Entity class representing an Announcement in the school management system.
 * It contains details about the announcement, including its title, body,
 * audience, and timestamps. Each announcement is associated with a branch
 * and an admin user who created it.
 */
@Entity
@Table(name = "announcements")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Announcement {
    
    // Primary key for the Announcement entity, generated as a UUID string.
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    
    // Relationship with Branch
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "branch_id", nullable = false)
    private Branch branch;
    
    // Relationship with AdminUser (who created the announcement)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "admin_user_id", nullable = false)
    private AdminUser adminUser;
    
    // Title of the announcement. This field is required.
    @Column(nullable = false)
    private String title;
    
    // Body of the announcement. This field is required and can be a long text.
    @Column(nullable = false, columnDefinition = "TEXT")
    private String body;
    
    // Audience for the announcement, defined as an enum. This field is required.
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Audience audience;
    
    // Indicates whether the announcement is deleted.
    // This field is required and defaults to false.
    @Column(name = "is_deleted", nullable = false)
    private boolean isDeleted = false;
    
    // Timestamp for when the announcement was created. This field is automatically
    @CreationTimestamp // Automates setting the timestamp on create
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
}