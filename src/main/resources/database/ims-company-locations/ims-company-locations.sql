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
 *   - This schema defines tables for managing company locations details.
 *   - It ensures data integrity with foreign key constraints and uses JSONB for flexible storage of unstructured data.
 *   - Key features include tracking employee roles, employment status, and organizational relationships, supporting comprehensive data management for corporate operations.
*/

-- This SQL script drops existing tables to avoid conflicts
DROP TABLE IF EXISTS ims_company_locations CASCADE;

-- This SQL script creates a table for storing company locations
CREATE TABLE ims_company_locations
(
    id              INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    street1         VARCHAR(255)       NOT NULL,        -- Street address of the location
    street2         VARCHAR(255),                       -- Additional street address information (optional)
    city            VARCHAR(100)       NOT NULL,        -- City where the location is situated
    state           VARCHAR(100)       NOT NULL,        -- State or region of the location
    postal_code     VARCHAR(20)        NOT NULL,        -- Postal code for the location
    country         VARCHAR(100)       NOT NULL,        -- Country of the location
    location_id     VARCHAR(50) UNIQUE NOT NULL,        -- Unique identifier for the location
    location_type   VARCHAR(50)        NOT NULL,        -- Type of location (e.g., HEADQUARTERS, BRANCH)
    geo_coordinates JSONB,                              -- Stores geographical coordinates (latitude and longitude) in JSON format
    contact_info    JSONB,                              -- Stores contact information in JSON format
    is_active       BOOLEAN   DEFAULT FALSE,             -- Indicates if the location is currently active
    created_at      TIMESTAMP DEFAULT CURRENT_TIMESTAMP -- Adding created_at column to track record creation time
);

-- Adding a check constraint to ensure valid location types
/*
 * This constraint ensures that the location_type column only contains valid values.
 * Valid values are: 'HEADQUARTERS', 'BRANCH OFFICE', 'DEVELOPMENT CENTER'.
 * HEADQUARTERS is the main office, BRANCH OFFICE is a secondary business zone, and DEVELOPMENT CENTER is for R&D activities.
 */
ALTER TABLE ims_company_locations
    ADD CONSTRAINT chk_location_type CHECK ( location_type IN ('HEADQUARTERS', 'BRANCH OFFICE', 'DEVELOPMENT CENTER'));