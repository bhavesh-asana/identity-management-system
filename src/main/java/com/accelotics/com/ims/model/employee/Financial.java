package com.accelotics.com.ims.model.employee;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ims_employee_financial")
public class Financial {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String bankName;
  private String branchName;
  private String accountType;
  private String accountNumber;
  private String ifscCode;
  private String swiftCode;
  private String routingNumber;
  private Boolean isPrimaryAccount;

  @ManyToOne
  @JoinColumn(name = "employee_ref_id", nullable = false)
  private Employee employee;
}