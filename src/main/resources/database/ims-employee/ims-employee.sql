/*
 * Project: Identity Management System (IMS)
 * Version: 1.0
 * Date: 2023-10-01
 * License: MIT
 *
 * Database: PostgreSQL (version 15.0)
 * Schema: ims
 * Host: Azure PostgreSQL
 *
 * Author: Bhavesh Asanabada
 *
 * Description:
 *   - This schema defines tables for managing company Employee details.
 *   - It ensures data integrity with foreign key constraints and uses JSONB for flexible storage of unstructured data.
 *   - Key features include tracking employee roles, employment status, and organizational relationships, supporting comprehensive data management for corporate operations.
 */

-- This SQL script drops existing tables to avoid conflicts
DROP TABLE IF EXISTS ims_employee CASCADE;

-- This SQL script creates a table for storing employee information
CREATE TABLE ims_employee
(
    id                   SERIAL PRIMARY KEY,
    first_name           VARCHAR(100) NOT NULL,                                           -- Stores the first name of the employee
    middle_name          VARCHAR(100),                                                    -- Stores the middle name of the employee
    last_name            VARCHAR(100) NOT NULL,                                           -- Stores the last name of the employee
    display_name         VARCHAR(200),                                                    -- Stores the display name of the employee
    gender               VARCHAR(10),                                                     -- Stores the gender of the employee
    date_of_birth        DATE,                                                            -- Adding date of birth column to employee information
    marital_status       VARCHAR(20),                                                     -- Adding marital status column to employee information
    contact_info         JSONB,                                                           -- Stores contact information (Unstructured - using JSONB for flexibility)
    address              JSONB,                                                           -- Stores the address data (Unstructured - using JSONB for flexibility)
    parental_information JSONB,                                                           -- Stores parental information (Unstructured - using JSONB for flexibility)
    education            JSONB,                                                           -- Stores education details (Unstructured - using JSONB for flexibility)
    experience           JSONB,                                                           -- Stores work experience details (Unstructured - using JSONB for flexibility)
--     financial            JSONB,                              -- Stores financial information (Unstructured - using JSONB for flexibility)
--     employment_details    VARCHAR,                            -- References employment details by employee work ID
    created_at           TIMESTAMP DEFAULT CURRENT_TIMESTAMP,                             -- Adding created_at column to track record creation time
    financial_ref_id     INTEGER REFERENCES ims_employee_financial (id) ON DELETE CASCADE -- Foreign key to financial information table
);

/*
 * This constraint ensures that the address JSONB column contains a valid type_of_address.
 * Valid values are: 'LIVING ADDRESS', 'PERMANENT ADDRESS', 'OTHER'.
 * This helps maintain data integrity for address types.
 */
ALTER TABLE ims_employee
    ADD CONSTRAINT chk_address_type CHECK ( ims_employee.address ->> 'type_of_address' IN
                                            ('LIVING ADDRESS', 'PERMANENT ADDRESS', 'BUSINESS', 'OTHER')),
    ADD CONSTRAINT chk_relationship_type CHECK ( ims_employee.parental_information ->> 'type_of_relationship' IN
                                                 ('FATHER', 'MOTHER', 'SON', 'DAUGHTER', 'GUARDIAN'));