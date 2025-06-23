package com.accelotics.com.ims.config;

import com.accelotics.com.ims.model.employee.Employee;

public class EmployeeConfig {

    public static String generateDisplayName(Employee employee) {
        StringBuilder nameBuilder = new StringBuilder();
        if (employee.getFirstName() != null) {
            nameBuilder.append(employee.getFirstName());
        }
        if (employee.getMiddleName() != null && !employee.getMiddleName().isEmpty()) {
            nameBuilder.append(" ").append(employee.getMiddleName());
        }
        if (employee.getLastName() != null) {
            nameBuilder.append(" ").append(employee.getLastName());
        }
        return nameBuilder.toString().trim();
    }
}