package com.accelotics.com.ims.model.employee;

import com.accelotics.com.ims.config.CustomFieldGenerator;
import com.accelotics.com.ims.model.employee.utils.*;
import com.accelotics.com.ims.model.utils.ContactInfo;
import com.accelotics.com.ims.model.utils.Gender;
import io.hypersistence.utils.hibernate.type.json.JsonBinaryType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.Type;
import org.hibernate.type.SqlTypes;
import org.springframework.data.annotation.Transient;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ims_employee")
public class Employee {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  // Personal information
  private String firstName;
  private String middleName;
  private String lastName;
  private Gender gender;
  private LocalDate dateOfBirth;
  private String maritalStatus;

  @Type(JsonBinaryType.class)
  @JdbcTypeCode(SqlTypes.JSON)
  @Column(name = "contact_info", columnDefinition = "jsonb")
  private ContactInfo contactInfo;

  @Type(JsonBinaryType.class)
  @JdbcTypeCode(SqlTypes.JSON)
  @Column(name = "address", columnDefinition = "jsonb")
  private List<Address> address;

  @Type(JsonBinaryType.class)
  @JdbcTypeCode(SqlTypes.JSON)
  @Column(name = "parental_information", columnDefinition = "jsonb")
  private List<ParentalInformation> parentalInformation;

  @Type(JsonBinaryType.class)
  @JdbcTypeCode(SqlTypes.JSON)
  @Column(name = "education", columnDefinition = "jsonb")
  private List<Education> education;

  @Type(JsonBinaryType.class)
  @JdbcTypeCode(SqlTypes.JSON)
  @Column(name = "experience", columnDefinition = "jsonb")
  private List<Experience> experience;

  @Type(JsonBinaryType.class)
  @JdbcTypeCode(SqlTypes.JSON)
  @Column(name = "financial", columnDefinition = "jsonb")
  private List<Financial> financial;
  // Metadata
  @Column(nullable = false)
  private LocalDate createdAt; // Record creation time

  @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
  private List<Financial> financials;
  @Transient
  private String displayName;

  @PostLoad
  private void populateDisplayName() {
    this.displayName = CustomFieldGenerator.generateDisplayName(this);
  }
}
