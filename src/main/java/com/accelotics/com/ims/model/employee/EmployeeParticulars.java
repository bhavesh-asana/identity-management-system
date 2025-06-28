package com.accelotics.com.ims.model.employee;

import com.accelotics.com.ims.model.employee.utils.RelatedDocuments;
import com.accelotics.com.ims.model.employee.utils.TypeOfEmployment;
import io.hypersistence.utils.hibernate.type.json.JsonBinaryType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.Type;
import org.hibernate.type.SqlTypes;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ims_employee_particulars")
public class EmployeeParticulars {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private TypeOfEmployment typeOfEmployment;
  private String employeeId;
  private String designation;
  private Date dateOfJoining;
  private Date dateOfTermination;
  private Boolean isPriorEmployee;

  @ElementCollection
  private List<String> previousEmployeeIds;

  @Type(JsonBinaryType.class)
  @JdbcTypeCode(SqlTypes.JSON)
  @Column(name = "related_documents", columnDefinition = "jsonb")
  private List<RelatedDocuments> relatedDocuments;

}
