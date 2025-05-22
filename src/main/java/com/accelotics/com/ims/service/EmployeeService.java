package com.accelotics.com.ims.service;

import org.springframework.stereotype.Service;

import com.accelotics.com.ims.model.Address;
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

  public Person addPerson(Person person) {
    return personRepository.save(person);
  }

  public List<Person> getAllEmployees() {
    List<Person> people = personRepository.findAll();
    return people.stream().peek(person -> {
      if (person.getAddress() != null) {
        Optional<Address> address = addressRepository.findById(person.getAddress().getId());
        address.ifPresent(person::setAddress);
      }
    }).collect(Collectors.toList());
  }

  public Person getPersonWithAddress(String personId) {
    // Fetch the Person document
    Person person = personRepository.findById(personId)
        .orElseThrow(() -> new RuntimeException("Person not found"));

    // Fetch the referenced Address document
    if (person.getAddress() != null) {
      Optional<Address> address = addressRepository.findById(person.getAddress().getId());
      address.ifPresent(person::setAddress);
    }

    return person;
  }
}