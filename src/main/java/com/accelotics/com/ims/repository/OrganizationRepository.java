package com.accelotics.com.ims.repository;

import com.accelotics.com.ims.model.company.Organization;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrganizationRepository extends JpaRepository<Organization, Long> {
}
