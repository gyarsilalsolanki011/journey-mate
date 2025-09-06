-- trips_db.sql
-- Schema + Sample Data for Trip entity

-- 1. Drop database if exists and create a new one
DROP DATABASE IF EXISTS trips_db;
CREATE DATABASE trips_db;
USE trips_db;

-- 2. Drop table if exists(safe for re-run)
DROP TABLE IF EXISTS trips;

-- 3. Create trips table
CREATE TABLE trips(
    id INT AUTO_INCREMENT PRIMARY KEY,
    destination varchar(100) NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    price DECIMAL(10, 2) NOT NULL CHECK (price >= 0),
    trip_status ENUM('PLANNED', 'ONGOING', 'COMPLETED') DEFAULT 'PLANNED'
);

-- 4. Insert sample data
INSERT INTO trips (destination, start_date, end_date, price, trip_status) VALUES
('Paris', '2025-09-10', '2025-09-02', 1200.50, 'PLANNED'),
('New York', '2025-10-01', '2025-10-08', 1500.00, 'ONGOING'),
('Tokyo', '2025-07-15', '2025-07-25', 2000.00, 'COMPLETED'),
('Bali', '2025-12-01', '2025-12-10', 950.00, 'PLANNED'),
('London', '2026-01-05', '2026-01-12', 1300.75, 'PLANNED');

-- 5. Additional sample data
INSERT INTO trips (destination, start_date, end_date, price, trip_status) VALUES
('Sydney', '2025-11-20', '2025-11-30', 1800.00, 'PLANNED'),
('Rome', '2025-08-05', '2025-08-15', 1100.00, 'COMPLETED'),
('Cape Town', '2025-09-25', '2025-10-05', 1600.00, 'ONGOING'),
('Dubai', '2025-12-15', '2025-12-22', 1400.00, 'PLANNED'),
('Amsterdam', '2026-02-10', '2026-02-17', 1250.00, 'PLANNED');



