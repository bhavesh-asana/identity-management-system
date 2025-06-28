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
 *   - This schema defines tables for managing company locations, organizations, employee information, and employment details.
 *   - It ensures data integrity with foreign key constraints and uses JSONB for flexible storage of unstructured data.
 *   - Key features include tracking employee roles, employment status, and organizational relationships, supporting comprehensive data management for corporate operations.
*/

-- This SQL script drops existing tables to avoid conflicts
DROP TABLE IF EXISTS ims_company_locations,
    ims_company_organizations,
    ims_employee_information,
    ims_employment_details
    CASCADE;

DROP TABLE IF EXISTS ims_company_locations;

-- This SQL script creates a table for storing company locations
CREATE TABLE ims_company_locations
(
    id              SERIAL PRIMARY KEY,
    street1         VARCHAR(255)       NOT NULL,        -- Street address of the location
    street2         VARCHAR(255),                       -- Additional street address information (optional)
    city            VARCHAR(100)       NOT NULL,        -- City where the location is situated
    state           VARCHAR(100)       NOT NULL,        -- State or region of the location
    postal_code     VARCHAR(20)        NOT NULL,        -- Postal code for the location
    country         VARCHAR(100)       NOT NULL,        -- Country of the location
    location_id     VARCHAR(50) UNIQUE NOT NULL,        -- Unique identifier for the location
    location_type   VARCHAR(50)        NOT NULL,        -- Type of location (e.g., HEADQUARTERS, BRANCH)
    geo_coordinates JSONB,                              -- Stores geographical coordinates (latitude and longitude) in JSON format
    created_at      TIMESTAMP DEFAULT CURRENT_TIMESTAMP -- Adding created_at column to track record creation time
);

-- This SQL script creates a table for storing company organizations
CREATE TABLE ims_company_organizations
(
    id              SERIAL PRIMARY KEY,
    name            VARCHAR(255) UNIQUE NOT NULL, -- Stores the name of the organization
    type            VARCHAR(100)        NOT NULL, -- Stores the type of organization (e.g., LLC, Corporation)
    organization_id VARCHAR(50) UNIQUE  NOT NULL, -- Unique identifier for the organization
    contact_info    JSONB                         -- Stores contact information
);

DELETE
FROM organization_contact_info
WHERE organization_contact_info.organization_id = 4;
DELETE
FROM ims_company_organizations
WHERE id = 4;

DELETE
FROM organization_contact_info;
DELETE
FROM ims_company_organizations;

ALTER TABLE ims_company_organizations
    ADD COLUMN created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- Adding created_at column to track record creation time
    ADD CONSTRAINT unique_name UNIQUE (name); -- Ensuring unique combination of name and organization_id

CREATE TABLE organization_contact_info
(
    organization_id BIGINT       NOT NULL, -- Foreign key to ims_company_organizations
    contact_type    VARCHAR(255) NOT NULL, -- Key for the contact info
    contact_value   VARCHAR(255) NOT NULL, -- Value for the contact info
    PRIMARY KEY (organization_id, contact_type),
    FOREIGN KEY (organization_id) REFERENCES ims_company_organizations (id)
);

-- This SQL script creates a table for storing employee information with additional details
CREATE TABLE ims_employee_information
(
    id                    SERIAL PRIMARY KEY,
    first_name            VARCHAR(100)        NOT NULL,       -- Stores the first name of the employee
    middle_name           VARCHAR(100),                       -- Stores the middle name of the employee
    last_name             VARCHAR(100)        NOT NULL,       -- Stores the last name of the employee
    display_name          VARCHAR(100)        NOT NULL,       -- Stores the display name of the employee
    email                 VARCHAR(255) UNIQUE NOT NULL,       -- Stores the email address of the employee
    phone                 TEXT[]              NOT NULL,       -- Stores multiple phone numbers of the employee
    gender                VARCHAR(10),                        -- Stores the gender of the employee
    date_of_birth         DATE,                               -- Adding date of birth column to employee information
    marital_status        VARCHAR(20),                        -- Adding marital status column to employee information
    address               JSONB,                              -- Stores the address data (Unstructured - using JSONB for flexibility)
    parental_information  JSONB,                              -- Stores parental information (Unstructured - using JSONB for flexibility)
    emergency_contact     JSONB,                              -- Stores emergency contact information (Unstructured - using JSONB for flexibility)
    education             JSONB,                              -- Stores education details (Unstructured - using JSONB for flexibility)
    experience            JSONB,                              -- Stores work experience details (Unstructured - using JSONB for flexibility)
    financial_information JSONB,                              -- Stores financial information (Unstructured - using JSONB for flexibility)
    employment_details    VARCHAR,                            -- References employment details by employee work ID
    created_at            TIMESTAMP DEFAULT CURRENT_TIMESTAMP -- Adding created_at column to track record creation time
);

-- This SQL script creates a table for storing employee information with additional details
CREATE TABLE ims_employment_details
(
    id                  SERIAL PRIMARY KEY,                                                 -- Unique identifier for the employment details
    employee_work_id    VARCHAR(20) UNIQUE,                                                 -- Employee ID that is unique across the company
    employment_status   VARCHAR(20),                                                        -- Employment status
    role                VARCHAR(100),                                                       -- Employee's role
    job_type            VARCHAR(50),                                                        -- Type of job (e.g., FULL_TIME, PART_TIME, CONTRACT)
    date_of_joining     DATE,                                                               -- Date when the employee joined
    date_of_termination DATE,                                                               -- Date when the employee was terminated (if applicable)
    related_documents   JSONB,                                                              -- Stores related documents in JSON format (e.g., offer letter, contracts, agreements)
    organization_id     VARCHAR(50) REFERENCES ims_company_organizations (organization_id), -- Organization ID from ims_company_organizations
    work_location_id    INTEGER REFERENCES ims_company_locations (id),                      -- Work location ID from ims_company_locations
    manager_id          INTEGER REFERENCES ims_employee_information (id),                   -- Manager's ID from employee_information
    prior_employee      BOOLEAN DEFAULT FALSE,                                              -- Indicates if the employee was previously employed
    prior_employee_ids  TEXT[] UNIQUE                                                       -- Stores IDs of prior employees
);

-- Constraints to ensure data integrity
-- Add a foreign key constraint to link ims_employee_information and ims_employment_details
ALTER TABLE ims_employee_information
    ADD CONSTRAINT fk_employment_details
        FOREIGN KEY (employment_details) REFERENCES ims_employment_details (employee_work_id);

ALTER TABLE ims_employee_information
    ADD COLUMN date_of_birth  DATE, -- Adding date of birth column to employee information
    ADD COLUMN marital_status VARCHAR(20), -- Adding marital status column to employee information
    ADD COLUMN created_at     TIMESTAMP DEFAULT CURRENT_TIMESTAMP;
-- Adding created_at column to track record creation time


/*
 * This section resets the sequences for the tables to ensure that the IDs start from 1.
 */

DELETE FROM ims_company_locations;
-- Drop the existing sequence if necessary
DROP SEQUENCE IF EXISTS ims_company_locations_seq CASCADE;

-- Create a new sequence starting from 1
CREATE SEQUENCE ims_company_locations_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

-- Clear all rows
DELETE
FROM ims_company_locations;

-- Link the sequence to the id column
ALTER TABLE ims_company_locations
    ALTER COLUMN id SET DEFAULT nextval('ims_company_locations_seq');


-- Ensure the sequence is owned by the id column
ALTER SEQUENCE ims_company_locations_seq OWNED BY ims_company_locations.id;

-- Reset sequence if necessary
ALTER SEQUENCE ims_company_locations_seq RESTART WITH 1;


-- Drop the existing sequence if necessary
DROP SEQUENCE IF EXISTS ims_company_organizations_seq CASCADE;

-- Create a new sequence starting from 1
CREATE SEQUENCE ims_company_organizations_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

-- Clear all rows
DELETE
FROM ims_company_organizations;

-- Link the sequence to the id column
ALTER TABLE ims_company_organizations
    ALTER COLUMN id SET DEFAULT nextval('ims_company_organizations_seq');

-- Reset sequence if necessary
ALTER SEQUENCE ims_company_organizations_seq RESTART WITH 1;