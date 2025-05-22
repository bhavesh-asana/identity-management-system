package com.accelotics.com.ims.service;

import com.accelotics.com.ims.model.Address;
import com.accelotics.com.ims.model.Person;
import com.accelotics.com.ims.repository.PersonRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class EmployeeServiceTest {

  @Mock
  private PersonRepository personRepository;

  @InjectMocks
  private EmployeeService employeeService;

  public EmployeeServiceTest() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void testAddPersonWithAddress() {
    // Arrange
    Address address = new Address();
    address.setId("101");
    address.setStreet("123 Main St");
    address.setCity("Springfield");
    address.setState("IL");
    address.setPostalCode("62704");
    address.setCountry("USA");

    Person person = new Person();
    person.setId("1");
    person.setFirstName("John");
    person.setLastName("Doe");
    person.setEmail("john.doe@example.com");
    person.setAddress(address);

    // Mock the save method
    when(personRepository.save(person)).thenReturn(person);

    // Act
    Person savedPerson = employeeService.addPerson(person);

    // Assert
    assertNotNull(savedPerson);
    assertEquals("1", savedPerson.getId());
    assertEquals("John", savedPerson.getFirstName());
    assertEquals("Doe", savedPerson.getLastName());
    assertEquals("john.doe@example.com", savedPerson.getEmail());
    assertNotNull(savedPerson.getAddress());
    assertEquals("123 Main St", savedPerson.getAddress().getStreet());
    assertEquals("Springfield", savedPerson.getAddress().getCity());
    assertEquals("IL", savedPerson.getAddress().getState());
    assertEquals("62704", savedPerson.getAddress().getPostalCode());
    assertEquals("USA", savedPerson.getAddress().getCountry());

    // Verify the save method was called
    verify(personRepository, times(1)).save(person);
  }
}
