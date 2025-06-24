package com.accelotics.com.ims.controller;

import com.accelotics.com.ims.model.company.Organization;
import com.accelotics.com.ims.services.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/organizations")
public class OrganizationsController {

  @Autowired
  private OrganizationService organizationService;

  /**
   * Controller for managing organizations.
   * Provides endpoints to create, read, update, and delete organization records.
   *
   * @return list of all organizations
   */
  @GetMapping
  public ResponseEntity<List<Organization>> getOrganizations() {
    return ResponseEntity.ok(organizationService.getAllOrganizations());
  }

  /**
   * Retrieves a specific organization by its ID.
   *
   * @param id the ID of the organization
   * @return the organization if found, or a 404 Not Found response
   */
  @GetMapping("/{id}")
  public ResponseEntity<Organization> getOrganizationById(@PathVariable Long id) {
    return organizationService.getOrganizationById(id)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }

  /**
   * Creates a new organization.
   *
   * @param organization the organization to create
   * @return the created organization
   */
  @PostMapping
  public ResponseEntity<Organization> createOrganization(@RequestBody Organization organization) {
    return ResponseEntity.ok(organizationService.createOrganization(organization));
  }

  /**
   * Updates an existing organization.
   *
   * @param id           the ID of the organization to update
   * @param organization the updated organization details
   * @return the updated organization, or a 404 Not Found response if the ID does not exist
   */
  @PutMapping("/{id}")
  public ResponseEntity<Organization> updateOrganization(@PathVariable Long id, @RequestBody Organization organization) {
    try {
      return ResponseEntity.ok(organizationService.updateOrganization(id, organization));
    } catch (RuntimeException e) {
      return ResponseEntity.notFound().build();
    }
  }

  /**
   * Deletes an organization by its ID.
   *
   * @param id the ID of the organization to delete
   * @return a 204 No Content response if successful, or a 404 Not Found response if the ID does not exist
   */
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteOrganization(@PathVariable Long id) {
    if (organizationService.getOrganizationById(id).isPresent()) {
      organizationService.deleteOrganization(id);
      return ResponseEntity.noContent().build();
    } else {
      return ResponseEntity.notFound().build();
    }
  }
}
