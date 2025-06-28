package com.accelotics.com.ims.model.employee.utils;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class RelatedDocuments {
  private String documentName;
  private String documentType;
  private String documentUrl;
  private String documentDescription;
  private Date uploadDate;
}
