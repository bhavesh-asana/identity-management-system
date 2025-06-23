package com.accelotics.com.ims.model.company;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

/**
 * Represents an organization in the system.
 * This class is used to store information about the organization, including its name, type, and unique identifiers.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ims_company_organizations")
public class Organization {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id; // Unique identifier for the organization
  private String organizationName; // Name of the organization
  private String organizationType; // Type of the organization
  private String organizationId; // Unique identifier for the organization, often used in external systems

}
