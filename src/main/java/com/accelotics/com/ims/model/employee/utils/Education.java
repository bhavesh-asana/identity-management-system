package com.accelotics.com.ims.model.employee.utils;

import com.accelotics.com.ims.model.utils.Degree;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class Education {
  private Degree degree;
  private String fieldOfStudy;
  private String institutionName;
  private LocalDate startDate;
  private LocalDate endDate;
  private String grade;
  private String description;
  private Address address;
  private String achievements;
  private Boolean isOngoing;
}