package com.accelotics.com.ims.services;

import com.accelotics.com.ims.model.company.CompanyLocations;
import com.accelotics.com.ims.model.company.Organization;
import com.accelotics.com.ims.repository.OrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrganizationService {
  
  @Autowired
  private OrganizationRepository organizationRepository;
  
  public List<Organization> getAllOrganizations() {
    return organizationRepository.findAll();
  }

  /**
   * Retrieves a specific company location by its ID.
   *
   * @param id the ID of the company location
   * @return the company location if found, or an empty Optional
   */
  public Optional<Organization> getOrganizationById(Long id) {
    return organizationRepository.findById(id);
  }

  /**
   * Creates an organization.
   *
   * @param organization the company location to create
   * @return the created company location
   */
  public Organization createOrganization(Organization organization) {
    return organizationRepository.save(organization);
  }

  /**
   * Updates an existing company location.
   *
   * @param id       the ID of the company location to update
   * @param organization the updated company location details
   * @return the updated company location, or throws an exception if the ID does not exist
   */
  public Organization updateOrganization(Long id, Organization organization) {
    if (organizationRepository.existsById(id)) {
      organization.setId(id);
      return organizationRepository.save(organization);
    }
    throw new RuntimeException("Organization not found");
  }

  /**
   * Deletes a company location by its ID.
   *
   * @param id the ID of the company location to delete
   * @throws RuntimeException if the location with the given ID does not exist
   */
  public void deleteOrganization(Long id) {
    if (organizationRepository.existsById(id)) {
      organizationRepository.deleteById(id);
    } else {
      throw new RuntimeException("Location not found");
    }
  }
  
}
