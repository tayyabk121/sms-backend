package com.example.sms.entity;

import jakarta.persistence.*;
import lombok.Data;

@Table(name = "students")
@Entity
@Data
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String studentId;
    private String fullName;
    private String fathersName;
    private String mothersName;
    private String gradeName;
    private String address;
    private String phoneNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "branch_id", nullable = false)
    private Branch branch;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "grade_id", nullable = false)
    private Grade grade;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_id", nullable = false)
    private Teacher teacher;
}
