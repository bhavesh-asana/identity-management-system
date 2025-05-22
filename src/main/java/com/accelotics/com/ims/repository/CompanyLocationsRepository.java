package com.accelotics.com.ims.repository;

import com.accelotics.com.ims.model.CompanyLocations;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CompanyLocationsRepository extends MongoRepository<CompanyLocations, String> {
}
