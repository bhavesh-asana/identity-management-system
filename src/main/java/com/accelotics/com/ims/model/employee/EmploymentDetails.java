package com.accelotics.com.ims.model.employee;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ims_employment_details")
public class EmploymentDetails {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;

  @Column(unique = true, nullable = false)
  private String employeeWorkId; // Employee ID that is unique across the company

  @Column(nullable = false)
  private String employmentStatus; // Employment status

  @Column(nullable = false)
  private String role; // Employee's role

  private String jobType; // Type of job (e.g., FULL_TIME, PART_TIME, CONTRACT)

  private Date dateOfJoining; // Date when the employee joined

  private Date dateOfTermination; // Date when the employee was terminated (if applicable)

  private String relatedDocuments; // JSON or string field to store related documents

  private String organizationId; // Organization ID from ims_company_organizations

  private Integer workLocationId; // Work location ID from ims_company_locations

  private Integer managerId; // Manager's ID from ims_employee_information

  @Column(nullable = false)
  private Boolean priorEmployee = false; // Indicates if the employee was previously employed

}
