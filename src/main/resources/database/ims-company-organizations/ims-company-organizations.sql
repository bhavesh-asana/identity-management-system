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
 *   - This schema defines tables for managing company organization details.
 *   - It ensures data integrity with foreign key constraints and uses JSONB for flexible storage of unstructured data.
 *   - Key features include tracking employee roles, employment status, and organizational relationships, supporting comprehensive data management for corporate operations.
*/

-- This SQL script drops existing tables to avoid conflicts
DROP TABLE IF EXISTS ims_company_organizations CASCADE;

-- This SQL script creates a table for storing company organizations
CREATE TABLE ims_company_organizations
(
    id              INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name            VARCHAR(255) UNIQUE NOT NULL,       -- Stores the name of the organization
    description     TEXT,                                -- Optional description of the organization
    type            VARCHAR(255)        NOT NULL,       -- Stores the type of organization (e.g., LLC, Corporation)
    organization_id VARCHAR(50) UNIQUE  NOT NULL,       -- Unique identifier for the organization
    contact_info    JSONB,                              -- Stores contact information
    is_active       BOOLEAN   DEFAULT FALSE,             -- Indicates if the organization is currently active
    created_at      TIMESTAMP DEFAULT CURRENT_TIMESTAMP -- Adding created_at column to track record creation time
);
