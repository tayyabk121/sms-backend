package com.example.sms.entity;

import com.example.sms.util.Gender;
import com.example.sms.util.StudentStatus;
import com.example.sms.util.Transport;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    
    @OneToOne
    @JoinColumn(name = "class_id")
    private Class classId;
    
    @Column(unique = true, nullable = false)
    private String addressNo;
    
    @Column(nullable = false)
    private String firstName;
    
    @Column(nullable = false)
    private String lastName;
    private LocalDate dob;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Gender gender;
    
    private String bloodGroup;
    
    @Enumerated(EnumType.STRING)
    private Transport transport;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StudentStatus status;
    
    private LocalDate admittedOn;
}
