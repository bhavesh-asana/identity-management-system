package com.accelotics.com.ims.controller;

import com.accelotics.com.ims.model.company.CompanyLocations;
import com.accelotics.com.ims.service.CompanyLocationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/ims/organization/company-locations")
public class CompanyLocationsController {

  @Autowired
  private CompanyLocationsService companyLocationsService;

  /**
   * Handles HTTP POST requests to the `/add-company-location` endpoint.
   *
   * This method creates a new `CompanyLocations` object and saves it to the database.
   *
   * @param companyLocations the `CompanyLocations` object to be created.
   * @return the created `CompanyLocations` object.
   */
  @PostMapping("/add-company-location")
  public CompanyLocations createCompanyLocations(@RequestBody CompanyLocations companyLocations) {
    return companyLocationsService.addCompanyLocation(companyLocations);
  }

  /**
   * Handles HTTP PUT requests to the `/update-company-location/{id}` endpoint.
   *
   * This method updates an existing `CompanyLocations` object in the database.
   *
   * @param id the ID of the `CompanyLocations` object to be updated.
   * @param updatedCompanyLocation the updated `CompanyLocations` object.
   * @return the updated `CompanyLocations` object.
   */
  @PutMapping("/update-company-location/{id}")
  public CompanyLocations updateCompanyLocation(@PathVariable String id, @RequestBody CompanyLocations updatedCompanyLocation) {
      return companyLocationsService.updateCompanyLocation(id, updatedCompanyLocation);
  }

  /**
   * Handles HTTP GET requests to the `/list-all-company-locations` endpoint.
   *
   * This method retrieves a list of all `CompanyLocations` objects from the database.
   *
   * @return a list of `CompanyLocations` objects.
   */
  @GetMapping("/list-all-company-locations")
  public List<CompanyLocations> getAllCompanyLocations() {
    return companyLocationsService.getAllCompanyLocations();
  }

  /**
   * Handles HTTP DELETE requests to the `/delete-company-location/{id}` endpoint.
   *
   * This method deletes a `CompanyLocations` object by its ID from the database.
   *
   * @param id the ID of the `CompanyLocations` object to be deleted.
   */
  @DeleteMapping("/delete-company-location/{id}")
  public void deleteCompanyLocationById(@PathVariable String id) {
    companyLocationsService.deleteCompanyLocationById(id);
  }

  /**
   * Handles HTTP DELETE requests to the `/delete-all-company-locations` endpoint.
   *
   * This method deletes all `CompanyLocations` objects from the database.
   */
  @DeleteMapping("/delete-all-company-locations")
  public void deleteAllCompanyLocations() {
    companyLocationsService.deleteAllCompanyLocations();
  }

}
