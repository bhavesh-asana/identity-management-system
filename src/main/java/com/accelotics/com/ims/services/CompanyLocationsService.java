package com.accelotics.com.ims.services;

import com.accelotics.com.ims.model.company.CompanyLocations;
import com.accelotics.com.ims.repository.CompanyLocationsRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyLocationsService {

  @Autowired
  private CompanyLocationsRespository companyLocationsRepository;

  /**
   * Service for managing company locations.
   * Provides methods to create, read, update, and delete company location records.
   *
   * @return list of all company locations
   */
  public List<CompanyLocations> getAllLocations() {
    return companyLocationsRepository.findAll();
  }

  /**
   * Retrieves a specific company location by its ID.
   *
   * @param id the ID of the company location
   * @return the company location if found, or an empty Optional
   */
  public Optional<CompanyLocations> getLocationById(Long id) {
    return companyLocationsRepository.findById(id);
  }

  /**
   * Creates a new company location.
   *
   * @param location the company location to create
   * @return the created company location
   */
  public CompanyLocations createLocation(CompanyLocations location) {
    return companyLocationsRepository.save(location);
  }

  /**
   * Updates an existing company location.
   *
   * @param id       the ID of the company location to update
   * @param location the updated company location details
   * @return the updated company location, or throws an exception if the ID does not exist
   */
  public CompanyLocations updateLocation(Long id, CompanyLocations location) {
    if (companyLocationsRepository.existsById(id)) {
      location.setId(id);
      return companyLocationsRepository.save(location);
    }
    throw new RuntimeException("Location not found");
  }

  /**
   * Deletes a company location by its ID.
   *
   * @param id the ID of the company location to delete
   * @throws RuntimeException if the location with the given ID does not exist
   */
  public void deleteLocation(Long id) {
    if (companyLocationsRepository.existsById(id)) {
      companyLocationsRepository.deleteById(id);
    } else {
      throw new RuntimeException("Location not found");
    }
  }
}
