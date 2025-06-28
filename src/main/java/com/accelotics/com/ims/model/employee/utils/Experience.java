package com.accelotics.com.ims.model.employee.utils;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Experience {
    private String companyName;
    private String designation;
    private String startDate;
    private String endDate;
    private String description;
    private String location_state;
    private String location_city;
    private String location_country;
    private Boolean isPresent;
}
