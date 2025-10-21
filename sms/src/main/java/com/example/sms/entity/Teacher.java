package com.example.sms.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "teachers")
@Data
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String teacherId;
    private String fullName;

    @ManyToOne
    @JoinColumn(name = "branch_id")
    private Branch branch;

    @ManyToOne
    @JoinColumn(name = "grade_id")
    private Grade grade;
}
