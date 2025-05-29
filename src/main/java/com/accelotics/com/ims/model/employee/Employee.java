package com.accelotics.com.ims.model.employee;

import com.accelotics.com.ims.model.company.Organization;
import com.accelotics.com.ims.config.EmployeeConfig;
import com.accelotics.com.ims.model.utils.Gender;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "people")
public class Employee {

  @Id
  private String id;

  // Personal information
  private String firstName;
  private String middleName;
  private String lastName;
  private String dateOfBirth;
  private Gender gender;

  // Contact information
  private String email;
  private List<String> phone;

  // EmployeeAddress information
  private EmployeeAddress employeeAddress;

  // Company information
  private Organization organization;

  @Transient
  private String displayName;

  public String getDisplayName() {
    return EmployeeConfig.generateDisplayName(this);
  }

}
