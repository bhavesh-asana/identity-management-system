package com.accelotics.com.ims.controller;

import com.accelotics.com.ims.model.company.CompanyLocations;
import com.accelotics.com.ims.services.CompanyLocationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/company-locations")
public class CompanyLocationsController {

  @Autowired
  private CompanyLocationsService service;

  /**
   * Controller for managing company locations.
   * Provides endpoints to create, read, update, and delete company location records.
   *
   * @return list of all company locations
   */
  @GetMapping
  public ResponseEntity<List<CompanyLocations>> getAllLocations() {
    return ResponseEntity.ok(service.getAllLocations());
  }

  /**
   * Retrieves a specific company location by its ID.
   *
   * @param id the ID of the company location
   * @return the company location if found, or a 404 Not Found response
   */
  @GetMapping("/{id}")
  public ResponseEntity<CompanyLocations> getLocationById(@PathVariable Long id) {
    return service.getLocationById(id)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }

  /**
   * Creates a new company location.
   *
   * @param location the company location to create
   * @return the created company location
   */
  @PostMapping
  public ResponseEntity<CompanyLocations> createLocation(@RequestBody CompanyLocations location) {
    return ResponseEntity.ok(service.createLocation(location));
  }

  /**
   * Updates an existing company location.
   *
   * @param id       the ID of the company location to update
   * @param location the updated company location details
   * @return the updated company location, or a 404 Not Found response if the ID does not exist
   */
  @PutMapping("/{id}")
  public ResponseEntity<CompanyLocations> updateLocation(@PathVariable Long id, @RequestBody CompanyLocations location) {
    try {
      return ResponseEntity.ok(service.updateLocation(id, location));
    } catch (RuntimeException e) {
      return ResponseEntity.notFound().build();
    }
  }

  /**
   * Deletes a company location by its ID.
   *
   * @param id the ID of the company location to delete
   * @return a 204 No Content response if successful, or a 404 Not Found response if the ID does not exist
   */
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteLocation(@PathVariable Long id) {
    try {
      service.deleteLocation(id);
      return ResponseEntity.noContent().build();
    } catch (RuntimeException e) {
      return ResponseEntity.notFound().build();
    }
  }
}
