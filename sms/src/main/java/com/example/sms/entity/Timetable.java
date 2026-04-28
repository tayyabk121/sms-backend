package com.example.sms.entity;

import com.example.sms.util.DayOfWeek;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;
import java.util.UUID;

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
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    
    // FK → teacher_assignments
    @Column(name = "teacher_assignment_id", nullable = false)
    private TeacherAssignment teacherAssignmentId;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "day_of_week", nullable = false)
    private DayOfWeek dayOfWeek;
    
    @Column(name = "start_time", nullable = false)
    private LocalTime startTime;
    
    @Column(name = "end_time", nullable = false)
    private LocalTime endTime;
    
    private String room;
}
