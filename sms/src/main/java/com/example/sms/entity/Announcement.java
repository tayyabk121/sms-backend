package com.example.sms.entity;

import com.example.sms.util.Audience;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

/**
 * Represents an announcement in the school management system.
 */
@Entity
@Table(name = "announcements")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Announcement {
    
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
    
    @Column(nullable = false)
    private String title;
    
    @Column(nullable = false, columnDefinition = "TEXT")
    private String body;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Audience audience;
    
    @Column(name = "is_deleted", nullable = false)
    private boolean isDeleted = false;
    
    @CreationTimestamp // Automates setting the timestamp on create
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
}