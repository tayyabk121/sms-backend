package com.example.sms.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class SalaryStructures {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    
    @OneToOne
    @JoinColumn(name = "staff_id")
    private Staff staffId;
    
    @Column(nullable = false)
    private Double basicPay;
    
    private double hra;
    
    private double da;
    
    private double medicalAllowance;
    
    private double otherAllowance;
    
    private double pfDeduction;
    
    private double tdsDeduction;
    
    private double otherDeduction;
    
    @Column(nullable = false)
    private int workingDaysPerMonth;
    
    @Column(nullable = false)
    private LocalDate effectiveFrom;
    
}
