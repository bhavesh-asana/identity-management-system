package com.accelotics.com.ims.config;

import com.accelotics.com.ims.model.employee.Employee;

import java.util.UUID;

public class CustomFieldGenerator {

    /**
     * Generates a unique Location ID in the format: ACLTS - XXXX
     * where XXXX is a random 4-digit UUID.
     *
     * @parm null
     * @return the generated Location ID
     */
    public static String generateLocationId() {
        // Generate a random 4-digit UUID
        String uuid = UUID.randomUUID().toString().substring(0, 4).toUpperCase();

        // Combine the prefix and UUID
        return String.format("ACLTS-%s", uuid);
    }

    /**
     * Generates a display name for an employee in the format: "FirstName MiddleName LastName"
     *
     * @param employee the Employee object containing personal information
     * @return the generated display name
     */
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

    /**
     * Generates an Organization ID in the format: ACLTS - abc - 1234
     *
     * @param organizationName the name of the organization
     * @return the generated Organization ID
     */
    public static String generateOrganizationId(String organizationName) {
        if (organizationName == null || organizationName.trim().isEmpty()) {
            throw new IllegalArgumentException("Organization name cannot be null or empty");
        }

        // Extract the first characters of all the words in the organization name
        String[] words = organizationName.trim().split("\\s+");
        StringBuilder nameAbbreviation = new StringBuilder();
        for (String word : words) {
            nameAbbreviation.append(word.charAt(0));
        }

        // Generate a random 4-digit UUID
        String uuid = UUID.randomUUID().toString().substring(0, 4).toUpperCase();

        // Combine the prefix, abbreviation, and UUID
        return String.format("ACLTS-%s-%s", nameAbbreviation.toString().toUpperCase(), uuid);
    }
}