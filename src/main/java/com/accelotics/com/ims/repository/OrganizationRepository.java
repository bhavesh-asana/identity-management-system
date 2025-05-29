package com.accelotics.com.ims.repository;

import com.accelotics.com.ims.model.company.Organization;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrganizationRepository extends MongoRepository<Organization, String> {
}
