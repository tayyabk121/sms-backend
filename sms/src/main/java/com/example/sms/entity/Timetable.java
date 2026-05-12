package com.example.sms.entity;

import com.example.sms.util.DayOfWeek;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;

/**
 * Entity class representing a Timetable entry in the school management system.
 * It contains details about the timetable, including its association with a
 * teacher assignment, day of the week, start and end times, and room information.
 * The class also includes constraints to prevent scheduling conflicts for teachers.
 */
@Entity
@Table(
        name = "timetable",
        uniqueConstraints = {
                // Prevent same teacher assignment being double booked at same time
                @UniqueConstraint(
                        name = "unique_teacher_schedule",
                        columnNames = {"teacher_assignment_id", "day_of_week", "start_time"}
                )
        },
        indexes = {
                @Index(name = "idx_day_time", columnList = "day_of_week, start_time"),
                @Index(name = "idx_teacher_assignment", columnList = "teacher_assignment_id")
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Timetable {
   
    // Primary key for the Timetable entity, generated as a UUID string.
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    
    // Relation with TeacherAssignment (Which teacher taught which subject)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_assignment_id", nullable = false)
    private TeacherAssignment teacherAssignment;
    
    // Day of the week for the timetable entry, defined as an enum.
    // This field is required.
    @Enumerated(EnumType.STRING)
    @Column(name = "day_of_week", nullable = false)
    private DayOfWeek dayOfWeek;
    
    // Start time for the timetable entry. This field is required.
    @Column(name = "start_time", nullable = false)
    private LocalTime startTime;
    
    // End time for the timetable entry. This field is required.
    @Column(name = "end_time", nullable = false)
    private LocalTime endTime;
    
    // Room information for the timetable entry.
    // This field is optional and can contain
    private String room; // Room number or Hall name
}