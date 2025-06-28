package com.accelotics.com.ims.services;

import com.accelotics.com.ims.model.employee.Employee;
import com.accelotics.com.ims.model.employee.Financial;
import com.accelotics.com.ims.repository.EmployeeRepository;
import com.accelotics.com.ims.repository.FinancialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

  @Autowired
  private EmployeeRepository employeeRepository;

  // Create or update an employee
  public Employee saveEmployee(Employee employee) {
    return employeeRepository.save(employee);
  }

  // Retrieve all employees
  public List<Employee> getAllEmployees() {
    return employeeRepository.findAll();
  }

  // Retrieve an employee by ID
  public Optional<Employee> getEmployeeById(Long id) {
    return employeeRepository.findById(id);
  }

  // Delete an employee by ID
  public void deleteEmployeeById(Long id) {
    if (employeeRepository.existsById(id)) {
      employeeRepository.deleteById(id);
    } else {
      throw new RuntimeException("Employee not found");
    }
  }
}