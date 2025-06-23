package com.accelotics.com.ims.model.employee;

import com.accelotics.com.ims.config.EmployeeConfig;
import com.accelotics.com.ims.model.utils.Gender;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Transient;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ims_employee_information")
public class Employee {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;

  // Personal information
  @Column(nullable = false)
  private String firstName;

  private String middleName;

  @Column(nullable = false)
  private String lastName;

  @Column(nullable = false)
  private LocalDate dateOfBirth;

  @Column(nullable = false)
  private Gender gender;

  @Column(nullable = false)
  private String maritalStatus;

  private String address; // Address details stored as JSON

  @Column(nullable = false)
  private String parentalInformation; // Parental information stored as JSON

  @Column(nullable = false)
  private String emergencyContact; // Emergency contact details stored as JSON

  private String education; // Education details stored as JSON
  private String experience; // Work experience details stored as JSON

  @Column(nullable = false)
  private String financialInformation; // Financial information stored as JSON

  // Contact information
  @Column(nullable = false)
  private String email;

  @Column(nullable = false)
  private String phone;

  // Metadata
  @Column(nullable = false)
  private LocalDate createdAt; // Record creation time

  @Transient
  @Column(nullable = false)
  private String displayName;

  @PostLoad
  private void populateDisplayName() {
    this.displayName = EmployeeConfig.generateDisplayName(this);
  }
}
