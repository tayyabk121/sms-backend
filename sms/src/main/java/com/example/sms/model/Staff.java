package com.example.sms.model;

import com.example.sms.util.StaffRole;
import com.example.sms.util.StaffStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;

/**
 * Entity class representing a Staff member in the school management system.
 * It contains details about the staff member, including their association with
 * a branch, employee number, name, role, contact information, bank details,
 * employment status, and joining date.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "staff")
public class Staff {
   
    // Primary key for the Staff entity, generated as a UUID string.
    // This is consistent with other entities in the system.
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id; // Consistent with other entities
    
    // Many-to-one relationship with the Branch entity, indicating that each
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "branch_id", nullable = false)
    private Branch branch;
    
    // Unique employee number for the staff member. This field is required and
    @Column(name = "employee_no", unique = true, nullable = false)
    private String employeeNo;
    
    // Name of the staff member. This field is required.
    @Column(nullable = false)
    private String name;
    
    // Role of the staff member, defined as an enum. This field is required.
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StaffRole role;
    
    // Phone number of the staff member. This field is optional.
    @Pattern(
            regexp = "^[6-9]\\d{9}$",
            message = "Phone number must be a valid 10-digit number starting"
                    +" with 6, 7, 8, or 9"
    )
    @Column(name = "phone_number")
    private String phoneNumber;
    
        // Email address of the staff member.
        // This field is optional but must be unique if provided.
    @Email(message = "Invalid email format")
    @Size(max = 100, message = "Email cannot exceed 100 characters")
    @Column(unique = true)
    private String email;
    
    // Bank account number of the staff member. This field is optional.
    @Pattern(
            regexp = "^\\d{9,18}$",
            message = "Bank account number must be between 9 and 18 digits"
    )
    @Column(name = "bank_account_number")
    private String bankAccountNumber;
    
    // Name of the bank where the staff member holds an account.
    // This field is optional.
    @Size(min = 2, max = 100, message = "Bank name must be between"
            +"2 and 100 characters")
    @Column(name = "bank_name")
    private String bankName;
    
    // IFSC code of the bank branch where the staff member holds an account.
    @Pattern(
            regexp = "^[A-Z]{4}0[A-Z0-9]{6}$",
            message = "Invalid IFSC code "
    )
    @Column(name = "ifsc_code")
    private String ifscCode;
    
    // PAN card number of the staff member. This field is optional.
    @Pattern(
            regexp = "^[A-Z]{5}[0-9]{4}[A-Z]$",
            message = "Invalid PAN card number format"
    )
    @Column(name = "pan_card_number")
    private String panCardNumber;
    
    // Aadhar card number of the staff member. This field is optional.
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StaffStatus status;
    
    // Joining date of the staff member. This field is optional.
    @Column(name = "joined_on")
    private LocalDate joinedOn;
}