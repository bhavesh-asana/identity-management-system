package com.accelotics.com.ims.model.company;

import com.accelotics.com.ims.model.utils.JobType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "organizations")
public class Organization {

  @Id
  private String id;
  private String organizationName;
  private String organizationType;
  private String organizationId;

  private String employeeRole;

  private JobType employeeJobType;

  private String employeeId;

  private String companyLocationId;

}
