package com.accelotics.com.ims.controller;

import com.accelotics.com.ims.model.Person;
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
  public Person addPerson(@RequestBody Person person) {
    return employeeService.addPerson(person);
  }

  /**
   * Handles HTTP GET requests to the `/all-employees` endpoint.
   *
   * This method retrieves a list of all `Person` objects along with their associated
   * addresses by delegating the call to the `EmployeeService` service.
   *
   * @return a list of `Person` objects with their addresses populated.
   */
  @GetMapping("/list-all-employees")
  public List<Person> getAllEmployees() {
    return employeeService.getAllEmployees();
  }

}
