package com.accelotics.com.ims.service;

import com.accelotics.com.ims.model.employee.Employee;
import com.accelotics.com.ims.model.company.Organization;
import com.accelotics.com.ims.repository.CompanyLocationsRepository;
import com.accelotics.com.ims.repository.EmployeeRepository;
import com.accelotics.com.ims.repository.OrganizationRepository;
import org.springframework.stereotype.Service;

import com.accelotics.com.ims.model.employee.EmployeeAddress;
import com.accelotics.com.ims.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

  @Autowired
  private EmployeeRepository employeeRepository;

  @Autowired
  private AddressRepository addressRepository;

  @Autowired
  private OrganizationRepository organizationRepository;

  @Autowired
  private CompanyLocationsRepository companyLocationsRepository;

  /**
   * Adds a new employee to the database.
   *
   * This method saves the provided `employee` object to the `EmployeeRepository`.
   * It also saves the associated `EmployeeAddress` and `Organization` if they are not null.
   *
   * @param employee the employee object to be added
   * @return the saved employee object
   */
  public Employee addPerson(Employee employee) {
//    EmployeeAddress employeeAddress = addressRepository.save(employee.getEmployeeAddress());
//    Organization organization = organizationRepository.save(employee.getOrganization());
//    organization = organizationRepository.save(organization);

    employee.setEmployeeAddress(employee.getEmployeeAddress());
    employee.setOrganization(employee.getOrganization());

    return employeeRepository.save(employee);
  }

  /*
   * Retrieves a list of all employees from the database.
   *
   * This method fetches all `employee` objects from the `EmployeeRepository` and populates
   * their associated addresses by checking if the address exists in the `AddressRepository`.
   *
   * @return a list of `employee` objects with their addresses populated.
   */
  public List<Employee> getAllEmployees() {
    List<Employee> people = employeeRepository.findAll();
    return people.stream().peek(employee -> {

      if (employee.getEmployeeAddress() != null) {
        Optional<EmployeeAddress> address = addressRepository.findById(employee.getEmployeeAddress().getId());
        address.ifPresent(employee::setEmployeeAddress);
      }
      if (employee.getOrganization() != null) {
        Organization organization = employee.getOrganization();
        employee.setOrganization(organization);
      }

    }).collect(Collectors.toList());
  }

  /**
   * Deletes an employee by their ID.
   *
   * This method checks if the employee with the given ID exists in the database.
   * If it does, it deletes the employee; otherwise, it throws an exception.
   *
   * @param id the ID of the employee to be deleted
   */
  public void deleteEmployeeById(String id) {
      if (employeeRepository.existsById(id)) {
          employeeRepository.deleteById(id);
      } else {
          throw new IllegalArgumentException("employee with ID " + id + " does not exist.");
      }
  }

  /**
   * Deletes all employees from the database.
   *
   * This method clears all records from the `EmployeeRepository`.
   */
  public void deleteAllEmployees(){
    employeeRepository.deleteAll();
  }

}