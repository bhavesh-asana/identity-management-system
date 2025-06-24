package com.accelotics.com.ims.model.company;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents a company's location details.
 * This class is used to store information about the physical locations of a company offices.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ims_company_locations")
public class CompanyLocations {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "company_locations_seq")
  @SequenceGenerator(name = "company_locations_seq", sequenceName = "ims_company_locations_seq", allocationSize = 1)
  private Long id; // Unique identifier for the location
  private String street; // Primary street address
  private String street2; // Optional field
  private String city; // City of the location
  private String state; // State or region of the location
  private String postalCode; // Postal or ZIP code
  private String country; // Country of the location

}
