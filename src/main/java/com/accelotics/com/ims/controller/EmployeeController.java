package com.accelotics.com.ims.controller;

import com.accelotics.com.ims.model.employee.Employee;
import com.accelotics.com.ims.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/ims")
public class EmployeeController {

  @Autowired
  private EmployeeService employeeService;

  @PostMapping("/add-employee")
  public Employee addPerson(@RequestBody Employee employee) {
    return employeeService.addPerson(employee);
  }

  /**
   * Handles HTTP GET requests to the `/all-employees` endpoint.
   *
   * This method retrieves a list of all `employee` objects along with their associated
   * addresses by delegating the call to the `EmployeeService` service.
   *
   * @return a list of `employee` objects with their addresses populated.
   */
  @GetMapping("/list-all-employees")
  public List<Employee> getAllEmployees() {
    return employeeService.getAllEmployees();
  }

  /**
   * Handles HTTP GET requests to the `/employee/{id}` endpoint.
   *
   * This method retrieves a `employee` object by its ID from the database.
   *
   * @param id the ID of the `employee` to retrieve.
   * @return the `employee` object with the specified ID, or null if not found.
   */
  @DeleteMapping("/delete-employee/{id}")
  public void deleteEmployeeById(@PathVariable String id) {
    employeeService.deleteEmployeeById(id);
  }

  /**
   * Handles HTTP DELETE requests to the `/delete-all-employees` endpoint.
   *
   * This method deletes all `employee` objects from the database.
   */
  @DeleteMapping("/delete-all-employees")
  public void deleteAllEmployees() {
    employeeService.deleteAllEmployees();
  }

}
