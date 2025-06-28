package com.accelotics.com.ims.services;

import com.accelotics.com.ims.model.employee.Employee;
import com.accelotics.com.ims.model.employee.Financial;
import com.accelotics.com.ims.repository.EmployeeRepository;
import com.accelotics.com.ims.repository.FinancialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FinancialService {

  @Autowired
  private EmployeeRepository employeeRepository;

  @Autowired
  private FinancialRepository financialRepository;

  // Create or update financial information
  public Financial addFinancialInfoOfEmployee(Financial financial) {
    return financialRepository.save(financial);
  }
}
