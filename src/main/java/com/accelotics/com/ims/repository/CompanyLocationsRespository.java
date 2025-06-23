package com.accelotics.com.ims.repository;

import com.accelotics.com.ims.model.company.CompanyLocations;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for managing company locations.
 * This interface extends JpaRepository to provide CRUD operations for CompanyLocations entities.
 */
public interface CompanyLocationsRespository extends JpaRepository<CompanyLocations, Long> {
}
