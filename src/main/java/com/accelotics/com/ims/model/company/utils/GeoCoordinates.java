package com.accelotics.com.ims.model.company.utils;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GeoCoordinates {
  private double latitude;
  private double longitude;
  private String googleMapsUrl;
}
