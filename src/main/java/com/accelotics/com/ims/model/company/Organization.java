package com.accelotics.com.ims.model.company;

import com.accelotics.com.ims.config.CustomFieldGenerator;
import com.accelotics.com.ims.model.utils.ContactInfo;
import io.hypersistence.utils.hibernate.type.json.JsonBinaryType;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.Type;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;

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
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id; // Unique identifier for the organization

  private String name; // Name of the organization
  private String type; // Type of the organization

  private String organizationId; // Unique identifier for the organization, often used in external systems

  @Type(JsonBinaryType.class)
  @JdbcTypeCode(SqlTypes.JSON)
  @Column(name = "contactInfo", columnDefinition = "jsonb")
  private ContactInfo contactInfo; // Contact information for the organization, stored as JSON or a string

  private LocalDateTime createdAt;

  /**
   * This method is called before the entity is persisted to the database.
   * It initializes the creation timestamp and generates a unique organization ID if not already set.
   */
  @PrePersist
  private void organizationFieldsDesignator(){
    // Set the creation timestamp
    if (this.createdAt == null) {
      this.createdAt = LocalDateTime.now(); // Set the current timestamp
    }

    // Generate a unique organization ID if it is not already set
    if (this.organizationId == null || this.organizationId.isEmpty()) {
      this.organizationId = CustomFieldGenerator.generateOrganizationId(this.name); // Generate a unique organization ID
    }
  }
}
