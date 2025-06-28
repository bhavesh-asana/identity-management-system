package com.accelotics.com.ims.repository;

import com.accelotics.com.ims.model.employee.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
