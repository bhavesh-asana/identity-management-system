package com.accelotics.com.ims.service;

import com.accelotics.com.ims.model.CompanyLocations;
import com.accelotics.com.ims.repository.CompanyLocationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyLocationsService {

  @Autowired
  private CompanyLocationsRepository companyLocationsRepository;

  public CompanyLocations addCompanyLocation(CompanyLocations companyLocation) {
    return companyLocationsRepository.save(companyLocation);
  }

  public List<CompanyLocations> getAllCompanyLocations() {
    return companyLocationsRepository.findAll();
  }
}
