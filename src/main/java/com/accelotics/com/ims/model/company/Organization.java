package com.accelotics.com.ims.model.company;

import com.accelotics.com.ims.config.CustomIdGenerator;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Map;

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
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "organization_seq")
  @SequenceGenerator(name = "organization_seq", sequenceName = "ims_company_organizations_seq", allocationSize = 1)
  private Long id; // Unique identifier for the organization

  private String name; // Name of the organization
  private String type; // Type of the organization

  @Column(unique = true, nullable = false)
  private String organizationId; // Unique identifier for the organization, often used in external systems

  @ElementCollection
  @CollectionTable(name = "organization_contact_info", joinColumns = @JoinColumn(name = "organization_id"))
  @MapKeyColumn(name = "contact_type")
  @Column(name = "contact_value")
  private Map<String, String> contactInfo; // Contact information for the organization, stored as JSON or a string

  private LocalDateTime createdAt;

  /**
   * This method is called before the entity is persisted to the database.
   * It initializes the creation timestamp and generates a unique organization ID if not already set.
   */
  @PrePersist
  private void fieldsDesignator(){
    // Set the creation timestamp
    if (this.createdAt == null) {
      this.createdAt = LocalDateTime.now(); // Set the current timestamp
    }

    // Generate a unique organization ID if it is not already set
    if (this.organizationId == null || this.organizationId.isEmpty()) {
      this.organizationId = CustomIdGenerator.generateOrganizationId(this.name); // Generate a unique organization ID
    }
  }
}
