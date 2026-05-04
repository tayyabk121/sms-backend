package com.example.sms.entity;

import com.example.sms.util.StaffRole;
import com.example.sms.util.StaffStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "staff")
public class Staff {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id; // Consistent with other entities
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "branch_id", nullable = false)
    private Branch branch;
    
    @Column(name = "employee_no", unique = true, nullable = false)
    private String employeeNo;
    
    @Column(nullable = false)
    private String name;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StaffRole role;
    
    @Column(name = "phone_number")
    private String phoneNumber;
    
    @Column(unique = true)
    private String email;
    
    @Column(name = "bank_account_number")
    private String bankAccountNumber;
    
    @Column(name = "bank_name")
    private String bankName;
    
    @Column(name = "ifsc_code")
    private String ifscCode;
    
    @Column(name = "pan_card_number")
    private String panCardNumber;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StaffStatus status;
    
    @Column(name = "joined_on")
    private LocalDate joinedOn;
}