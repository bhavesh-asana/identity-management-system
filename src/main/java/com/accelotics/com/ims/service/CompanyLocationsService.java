package com.accelotics.com.ims.service;

import com.accelotics.com.ims.model.company.CompanyLocations;
import com.accelotics.com.ims.repository.CompanyLocationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyLocationsService {

  @Autowired
  private CompanyLocationsRepository companyLocationsRepository;

  /**
   * Adds a new company location to the database.
   *
   * @param companyLocation the company location to add
   * @return the added company location
   */
  public CompanyLocations addCompanyLocation(CompanyLocations companyLocation) {
    return companyLocationsRepository.save(companyLocation);
  }

  /**
   * Updates an existing company location in the database.
   *
   * @param id                     the ID of the company location to update
   * @param updatedCompanyLocation the updated company location data
   * @return the updated company location
   */
  public CompanyLocations updateCompanyLocation(String id, CompanyLocations updatedCompanyLocation) {
    return companyLocationsRepository.findById(id).map(existingLocation -> {
      existingLocation.setStreet(updatedCompanyLocation.getStreet());
      existingLocation.setStreet2(updatedCompanyLocation.getStreet2());
      existingLocation.setCity(updatedCompanyLocation.getCity());
      existingLocation.setState(updatedCompanyLocation.getState());
      existingLocation.setCountry(updatedCompanyLocation.getCountry());
      existingLocation.setPostalCode(updatedCompanyLocation.getPostalCode());
      return companyLocationsRepository.save(existingLocation);
    }).orElseThrow(() -> new IllegalArgumentException("CompanyLocation with ID " + id + " not found."));
  }

  /**
   * Retrieves all company locations from the database.
   *
   * @return a list of all company locations
   */
  public List<CompanyLocations> getAllCompanyLocations() {
    return companyLocationsRepository.findAll();
  }

  /**
   * Deletes a company location by its ID.
   *
   * @param id the ID of the company location to delete
   */
  public void deleteCompanyLocationById(String id) {
    companyLocationsRepository.deleteById(id);
  }

  /**
   * Deletes all company locations from the database.
   */
  public void deleteAllCompanyLocations() {
    companyLocationsRepository.deleteAll();
  }
}
