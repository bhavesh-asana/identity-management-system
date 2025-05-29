package com.accelotics.com.ims.model.employee;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "addresses")
public class EmployeeAddress {

  @Id
  private String id;
  private String street;
  private String street2; // Optional field
  private String city;
  private String state;
  private String postalCode;
  private String country;

}
