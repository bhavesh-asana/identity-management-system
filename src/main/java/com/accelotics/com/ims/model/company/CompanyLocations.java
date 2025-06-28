package com.accelotics.com.ims.model.company;

import com.accelotics.com.ims.config.CustomFieldGenerator;
import com.accelotics.com.ims.model.utils.ContactInfo;
import com.accelotics.com.ims.model.company.utils.GeoCoordinates;
import io.hypersistence.utils.hibernate.type.json.JsonBinaryType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.Type;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;

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
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id; // Unique identifier for the location
  private String street1; // Primary street address
  private String street2; // Optional field
  private String city; // City of the location
  private String state; // State or region of the location
  private String postalCode; // Postal or ZIP code
  private String country; // Country of the location
  private String locationId; // Unique identifier for the location, can be used for external references
  private String locationType; // Type of location (e.g., office, warehouse, etc.)
  private Boolean isActive; // Indicates if the location is currently active or not

  @Type(JsonBinaryType.class)
  @JdbcTypeCode(SqlTypes.JSON)
  @Column(name = "geoCoordinates", columnDefinition = "jsonb")
  private GeoCoordinates geoCoordinates; // Geographic coordinates (latitude, longitude) of the location

  @Type(JsonBinaryType.class)
  @JdbcTypeCode(SqlTypes.JSON)
  @Column(name = "contactInfo", columnDefinition = "jsonb")
  private ContactInfo contactInfo;

  private LocalDateTime createdAt;

  /**
   * This method is called before the entity is persisted to the database.
   * It initializes the creation timestamp and generates a unique organization ID if not already set.
   */
  @PrePersist
  private void companyFieldsDesignator(){
    // Set the creation timestamp
    if (this.createdAt == null) {
      this.createdAt = LocalDateTime.now(); // Set the current timestamp
    }

    // Generate a unique organization ID if it is not already set
    if (this.locationId == null || this.locationId.isEmpty()) {
      this.locationId = CustomFieldGenerator.generateLocationId(); // Generate a unique organization ID
    }
  }
}
