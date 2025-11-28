

-- Expense Tracker System Database
-- This script creates tables and populates them.

-- =========================================================
-- DDL: CREATE MASTER TABLES (Lookups/Entities)
-- =========================================================

-- Master Table 1: Users
CREATE TABLE Users (
  user_id INT AUTO_INCREMENT PRIMARY KEY,
  email VARCHAR(100),
  firstname VARCHAR(10),
  surname VARCHAR(10),
  password VARCHAR(100)
);

-- Master Table 2: Bank
CREATE TABLE Bank (
  bank_id INT AUTO_INCREMENT PRIMARY KEY,
  bank_name VARCHAR(20),
  bank_acc_num VARCHAR(20)
);

-- Master Table 3: Budget
CREATE TABLE Budget (
    budget_id INT AUTO_INCREMENT PRIMARY KEY, 
    budget_amt DECIMAL(13,2),
    budget_start DATE,
    budget_end DATE,
    budget_category VARCHAR(20)
);

-- Master Table 4: Expense
CREATE TABLE Expense (
    expense_id INT AUTO_INCREMENT PRIMARY KEY,
    expense_amt DECIMAL(13,2),
    expense_date DATE,
    expense_category VARCHAR(20)
);

-- Master Table 5: Digital Expense
CREATE TABLE Digital_Expense (
    expense_id INT,
    bank_id INT,
    expense_refnum VARCHAR(20),
    PRIMARY KEY (expense_id, bank_id),
    FOREIGN KEY (expense_id) REFERENCES Expense(expense_id),
    FOREIGN KEY (bank_id) REFERENCES Bank(bank_id)
);

-- =========================================================
-- DDL: CREATE TRANSACTIONAL/LINKING TABLES
-- =========================================================

-- Transactional Table 6: User_Budget
CREATE TABLE User_Budget (
    user_id INT,
    budget_id INT,
    PRIMARY KEY (user_id, budget_id),
    FOREIGN KEY (user_id) REFERENCES Users(user_id),
    FOREIGN KEY (budget_id) REFERENCES Budget(budget_id)
);

-- Transactional Table 7: User_Bank
CREATE TABLE User_Bank (
    user_id INT,
    bank_id INT,
    PRIMARY KEY (user_id, bank_id),
    FOREIGN KEY (user_id) REFERENCES Users(user_id),
    FOREIGN KEY (bank_id) REFERENCES Bank(bank_id)
);

-- Transactional Table 8: User_Expense
CREATE TABLE User_Expense (
    user_id INT,
    expense_id INT,
    PRIMARY KEY (user_id, expense_id),
    FOREIGN KEY (user_id) REFERENCES Users(user_id),
    FOREIGN KEY (expense_id) REFERENCES Expense(expense_id)
);

-- =========================================================
-- DML: EXISTING SAMPLE DATA
-- =========================================================

-- 1. User
INSERT INTO Users (email, firstname, surname, password) VALUES
('user123@email.com', 'Juan', 'Dela Cruz', 'password123');

-- 2. Bank 
INSERT INTO Bank (bank_name, bank_acc_num) VALUES
('BPI', '111222333'),
('BDO', '444555666'), 
('VISA', '676767676'),
('MetroBank', '987654321');

-- 3. Budget
INSERT INTO Budget (budget_amt, budget_start, budget_end, budget_category) VALUES
-- DECEMBER 2025
(3000.00, '2025-12-01', '2025-12-31', 'FOOD'),
(4000.00, '2025-12-01', '2025-12-31', 'UTILITY'),
(3200.00, '2025-12-01', '2025-12-31', 'SUBSCRIPTION'), 
(2000.00, '2025-12-01', '2025-12-31', 'RENT'),
(10000.00, '2025-12-01', '2025-12-31', 'GROCERY'), 
(1200.00, '2025-12-01', '2025-12-31', 'TRANSPORTATION'),

-- JANUARY 2026
(3000.00, '2026-01-01', '2026-01-30', 'FOOD'),
(4000.00, '2026-01-01', '2026-01-30', 'UTILITY'),
(200.00, '2026-01-01', '2026-01-30', 'SUBSCRIPTION'), 
(2000.00, '2026-01-01', '2026-01-30', 'RENT'),
(10000.00, '2026-01-01', '2026-01-30', 'GROCERY'), 
(1200.00, '2026-01-01', '2026-01-30', 'TRANSPORTATION');

-- 4. Expense
-- DECEMBER 2025
INSERT INTO Expense (expense_amt, expense_date, expense_category) VALUES
-- FOOD (daily small meals)
(200.00, '2025-12-01', 'FOOD'),
(200.00, '2025-12-03', 'FOOD'),
(200.00, '2025-12-05', 'FOOD'),
(200.00, '2025-12-07', 'FOOD'),
(200.00, '2025-12-10', 'FOOD'),
(200.00, '2025-12-12', 'FOOD'),
(200.00, '2025-12-14', 'FOOD'),
(200.00, '2025-12-17', 'FOOD'),
(200.00, '2025-12-19', 'FOOD'),
(200.00, '2025-12-21', 'FOOD'),
(200.00, '2025-12-24', 'FOOD'),
(200.00, '2025-12-26', 'FOOD'),
(200.00, '2025-12-27', 'FOOD'),
(200.00, '2025-12-15', 'FOOD'),
(200.00, '2025-12-30', 'FOOD'),

-- TRANSPORTATION
(50.00, '2025-12-01', 'TRANSPORTATION'),
(50.00, '2025-12-03', 'TRANSPORTATION'),
(50.00, '2025-12-05', 'TRANSPORTATION'),
(50.00, '2025-12-07', 'TRANSPORTATION'),
(50.00, '2025-12-10', 'TRANSPORTATION'),
(50.00, '2025-12-12', 'TRANSPORTATION'),
(50.00, '2025-12-14', 'TRANSPORTATION'),
(50.00, '2025-12-17', 'TRANSPORTATION'),
(50.00, '2025-12-19', 'TRANSPORTATION'),
(50.00, '2025-12-21', 'TRANSPORTATION'),
(50.00, '2025-12-24', 'TRANSPORTATION'),
(50.00, '2025-12-26', 'TRANSPORTATION'),
(50.00, '2025-12-28', 'TRANSPORTATION'),
(50.00, '2025-12-15', 'TRANSPORTATION'),
(50.00, '2025-12-30', 'TRANSPORTATION'),

-- GROCERY (weekly)
(2500.00, '2025-12-01', 'GROCERY'),
(2500.00, '2025-12-08', 'GROCERY'),
(2500.00, '2025-12-15', 'GROCERY'),
(2500.00, '2025-12-22', 'GROCERY'),

-- UTILITY (all bills)
(1500.00, '2025-12-05', 'UTILITY'),
(700.00,  '2025-12-07', 'UTILITY'),
(1200.00, '2025-12-10', 'UTILITY'),
(600.00,  '2025-12-15', 'UTILITY'),

-- SUBSCRIPTION
(200.00, '2025-12-01', 'SUBSCRIPTION'),

-- RENT
(2000.00, '2025-12-01', 'RENT');

-- JANUARY 2026 (partial)
INSERT INTO Expense (expense_amt, expense_date, expense_category) VALUES
-- FOOD (daily small meals up to Jan 15)
(150.00, '2026-01-01', 'FOOD'),
(180.00, '2026-01-02', 'FOOD'),
(160.00, '2026-01-03', 'FOOD'),
(140.00, '2026-01-04', 'FOOD'),
(150.00, '2026-01-05', 'FOOD'),
(130.00, '2026-01-06', 'FOOD'),
(170.00, '2026-01-07', 'FOOD'),
(160.00, '2026-01-08', 'FOOD'),
(150.00, '2026-01-09', 'FOOD'),
(140.00, '2026-01-10', 'FOOD'),
(160.00, '2026-01-11', 'FOOD'),
(150.00, '2026-01-12', 'FOOD'),
(140.00, '2026-01-13', 'FOOD'),
(160.00, '2026-01-14', 'FOOD'),
(150.00, '2026-01-15', 'FOOD'),

-- TRANSPORTATION
(40.00, '2026-01-01', 'TRANSPORTATION'),
(50.00, '2026-01-02', 'TRANSPORTATION'),
(40.00, '2026-01-03', 'TRANSPORTATION'),
(40.00, '2026-01-04', 'TRANSPORTATION'),
(60.00, '2026-01-05', 'TRANSPORTATION'),
(40.00, '2026-01-06', 'TRANSPORTATION'),
(40.00, '2026-01-07', 'TRANSPORTATION'),
(40.00, '2026-01-08', 'TRANSPORTATION'),
(50.00, '2026-01-09', 'TRANSPORTATION'),
(40.00, '2026-01-10', 'TRANSPORTATION'),
(40.00, '2026-01-11', 'TRANSPORTATION'),
(40.00, '2026-01-12', 'TRANSPORTATION'),
(40.00, '2026-01-13', 'TRANSPORTATION'),
(40.00, '2026-01-14', 'TRANSPORTATION'),
(40.00, '2026-01-15', 'TRANSPORTATION'),

-- GROCERY (weekly)
(2500.00, '2026-01-03', 'GROCERY'),
(1500.00, '2026-01-10', 'GROCERY'),

-- UTILITY (some bills, partial)
(1500.00, '2026-01-05', 'UTILITY'),
(300.00, '2026-01-10', 'UTILITY'),

-- SUBSCRIPTION
(200.00, '2026-01-01', 'SUBSCRIPTION');

-- 5. Digital Expense - FIXED: Use valid expense_ids
-- Wait until all expenses are inserted, then link valid ones
INSERT INTO Digital_Expense (expense_id, bank_id, expense_refnum) 
SELECT e.expense_id, 
       CASE 
         WHEN e.expense_category = 'SUBSCRIPTION' THEN 1
         WHEN e.expense_amt = 200.00 THEN 2
         ELSE 3
       END,
       CONCAT('222200', LPAD(e.expense_id, 2, '0'))
FROM Expense e
WHERE e.expense_id BETWEEN 1 AND 10;  -- Only use existing expense_ids




-- POPULATING THE TRANSACTIONAL TABLES 


INSERT INTO User_Bank (user_id, bank_id)
SELECT user_id, bank_id FROM Users, Bank;

INSERT INTO User_Budget (user_id, budget_id)
SELECT user_id, budget_id FROM Users, Budget;

INSERT INTO User_Expense (user_id, expense_id)
SELECT user_id, expense_id FROM Users, Expense;




