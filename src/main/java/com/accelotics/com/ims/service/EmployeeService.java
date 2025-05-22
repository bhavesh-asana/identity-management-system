package com.accelotics.com.ims.service;

import com.accelotics.com.ims.model.Organization;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import com.accelotics.com.ims.model.EmployeeAddress;
import com.accelotics.com.ims.model.Person;
import com.accelotics.com.ims.repository.AddressRepository;
import com.accelotics.com.ims.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

  @Autowired
  private PersonRepository personRepository;

  @Autowired
  private AddressRepository addressRepository;
  @Autowired
  private MongoTemplate mongoTemplate;

  public Person addPerson(Person person) {

    /*
     * This method saves a `Person` object to the database.
     * It first checks if the `EmployeeAddress` associated with the person exists in the database.
     * If it does, it updates the address; otherwise, it creates a new address.
     * Finally, it saves the person object to the database.
     */
    EmployeeAddress employeeAddress = addressRepository.save(person.getEmployeeAddress());
    Organization organization = person.getOrganization();
    mongoTemplate.save(organization, "organizations");
    person.setEmployeeAddress(employeeAddress);
    person.setOrganization(organization);
    return personRepository.save(person);
  }

  /*
   * Retrieves a list of all employees from the database.
   *
   * This method fetches all `Person` objects from the `PersonRepository` and populates
   * their associated addresses by checking if the address exists in the `AddressRepository`.
   *
   * @return a list of `Person` objects with their addresses populated.
   */
  public List<Person> getAllEmployees() {
    List<Person> people = personRepository.findAll();
    return people.stream().peek(person -> {

      if (person.getEmployeeAddress() != null) {
        Optional<EmployeeAddress> address = addressRepository.findById(person.getEmployeeAddress().getId());
        address.ifPresent(person::setEmployeeAddress);
      }
      if (person.getOrganization() != null) {
        Organization organization = person.getOrganization();
        person.setOrganization(organization);
      }

    }).collect(Collectors.toList());
  }

}