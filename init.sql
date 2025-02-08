-- Create CareerConnectDB if it doesn't exist
IF NOT EXISTS (SELECT * FROM sys.databases WHERE name = 'CareerConnectDB')
    BEGIN
        CREATE DATABASE CareerConnectDB;
    END
GO

USE CareerConnectDB;
GO

