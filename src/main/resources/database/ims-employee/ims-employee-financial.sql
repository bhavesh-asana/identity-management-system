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
DROP TABLE IF EXISTS ims_employee_financial CASCADE;

-- This SQL script creates a table for storing employee financial information
CREATE TABLE ims_employee_financial
(
    id            SERIAL PRIMARY KEY,
    bankName      VARCHAR(100)       NOT NULL,        -- Stores the name of the bank
    branchName    VARCHAR(100)       NOT NULL,        -- Stores the name of the bank branch
    accountType   VARCHAR(50)        NOT NULL,        -- Stores the type of bank account (e.g., Savings, Current)
    accountNumber VARCHAR(50) UNIQUE NOT NULL,        -- Stores the bank account number
    ifscCode      VARCHAR(20) UNIQUE NOT NULL,        -- Stores the IFSC code for the bank account
    swiftCode     VARCHAR(20) UNIQUE,                 -- Stores the SWIFT code for international transactions
    routingNumber VARCHAR(20) UNIQUE,                 -- Stores the routing number for the bank account
    created_at    TIMESTAMP DEFAULT CURRENT_TIMESTAMP -- Adding created_at column to track record creation time
);

/* This constraint ensures that the accountType column contains a valid type of bank account.
 * Valid values are: 'SAVINGS', 'CURRENT', 'FIXED DEPOSIT', 'RECURRING DEPOSIT', 'OTHER'.
 * This helps maintain data integrity for account types.
 */
ALTER TABLE ims_employee_financial
    ADD CONSTRAINT chk_account_type CHECK (ims_employee_financial.accountType IN
                                           ('SAVINGS', 'CURRENT', 'FIXED DEPOSIT', 'RECURRING DEPOSIT', 'OTHER'));