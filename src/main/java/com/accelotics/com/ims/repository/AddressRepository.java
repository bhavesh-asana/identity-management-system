package com.accelotics.com.ims.repository;

import com.accelotics.com.ims.model.EmployeeAddress;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends MongoRepository<EmployeeAddress, String> {
}
