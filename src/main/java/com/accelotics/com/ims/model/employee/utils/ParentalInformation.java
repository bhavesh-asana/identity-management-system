package com.accelotics.com.ims.model.employee.utils;

import com.accelotics.com.ims.model.utils.ContactInfo;
import com.accelotics.com.ims.model.utils.Gender;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ParentalInformation {
  private String firstName;
  private String middleName;
  private String lastName;
  private Gender gender;
  private String typeOfRelationship;
  private ContactInfo contactInfo;
  private Address address;
}
