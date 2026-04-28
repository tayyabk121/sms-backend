package com.example.sms.entity;

import com.example.sms.util.StaffRole;
import com.example.sms.util.StaffStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Staff {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    
    @ManyToOne
    @JoinColumn(name = "branch_id")
    private Branch branchId;
    
    @Column(unique = true, nullable = false)
    private String employeeNo;
    
    @Column(nullable = false)
    private String name;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StaffRole role;
    
    private String phoneNumber;
    
    private String email;
    
    private String bankAccountNumber;
    
    private String bankName;
    
    private String ifscCode;
    
    private String panCardNumber;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StaffStatus status;
    
    private LocalDate joinedOn;
}
