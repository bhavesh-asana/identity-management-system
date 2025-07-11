package com.accelotics.com.ims.model.employee.utils;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Address {
  private String street1;
  private String street2;
  private String city;
  private String state;
  private String postalCode;
  private String typeOfAddress;
}