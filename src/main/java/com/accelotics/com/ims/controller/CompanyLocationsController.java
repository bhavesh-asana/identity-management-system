package com.accelotics.com.ims.controller;

import com.accelotics.com.ims.model.CompanyLocations;
import com.accelotics.com.ims.service.CompanyLocationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/ims/organization/company-locations")
public class CompanyLocationsController {

  @Autowired
  private CompanyLocationsService companyLocationsService;

  @PostMapping("/add-company-location")
  public CompanyLocations createCompanyLocations(@RequestBody CompanyLocations companyLocations) {
    return companyLocationsService.addCompanyLocation(companyLocations);
  }

  @GetMapping("/list-all-company-locations")
  public List<CompanyLocations> getAllCompanyLocations() {
    return companyLocationsService.getAllCompanyLocations();
  }

}
