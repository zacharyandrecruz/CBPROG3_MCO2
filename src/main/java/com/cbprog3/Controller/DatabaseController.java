package com.cbprog3.Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.cbprog3.Model.Bank;
import com.cbprog3.Model.Budget;
import com.cbprog3.Model.DateTime;
import com.cbprog3.Model.Expense;
import com.cbprog3.Model.User;

public class DatabaseController {
    private Connection connection;
    
    private static final String URL = "jdbc:mysql://localhost:3306/mco2_db";
    private static final String USERNAME = "acee";
    private static final String PASSWORD = "09212005";
    
    public DatabaseController() {
        connectToDatabase();
    }
    
    /**
     * Establishes connection to the database
     */
    private void connectToDatabase() {
        try {
            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // Establish connection
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("Database connected successfully!");
            
        } catch (ClassNotFoundException e) {
            System.err.println("MySQL JDBC Driver not found!");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Database connection failed!");
            e.printStackTrace();
        }
    }
    
    /**
     * Closes database connection
     */
    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Database connection closed.");
            }
        } catch (SQLException e) {
            System.err.println("Error closing database connection!");
            e.printStackTrace();
        }
    }
    
    // ========== USER OPERATIONS ==========
    
    /**
     * Loads user from database
     */
    public User loadUser(String userID) {
        String sql = "SELECT * FROM Users WHERE user_id = ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, Integer.parseInt(userID)); // comment : user_id is INT in database
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                User user = new User(
                    rs.getString("user_id"), // This will be converted to String
                    rs.getString("email"),
                    rs.getString("firstname"),
                    "", // middle name but it is not in our database
                    rs.getString("surname")
                );
                user.setUserPassword(rs.getString("password"));
                
                // Load user's banks
                loadUserBanks(user);
                System.out.println("Loaded User: " + user.getFullName());
                return user;
            }
        } catch (SQLException e) {
            System.err.println("Error loading user: " + e.getMessage());
        }
        return null;
    }

    
    /**
     * Saves user to database
     */
    public boolean saveUser(User user) {
        // REMINDER : Let database auto-generate user_id, removed user_id from INSERT
        String sql = "INSERT INTO Users (email, firstname, surname, password) VALUES (?, ?, ?, ?)";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            // changes : Use getFirstName() instead of splitting full name
            stmt.setString(1, user.getUserEmail());
            stmt.setString(2, user.getFirstName());
            stmt.setString(3, user.getSurname());
            stmt.setString(4, user.getUserPassword());
            
            int rowsAffected = stmt.executeUpdate();
            
            // Get the auto-generated user_id
            if (rowsAffected > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int generatedId = generatedKeys.getInt(1);
                        user.setUserID(String.valueOf(generatedId));
                    }
                }
                return true;
            }
            return false;
            
        } catch (SQLException e) {
            System.err.println("Error saving user: " + e.getMessage());
            return false;
        }
    }
    

    /**
     * Updates user information in database
     */
    public boolean updateUser(User user) {
        String sql = "UPDATE Users SET email = ?, firstname = ?, surname = ?, password = ? WHERE user_id = ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, user.getUserEmail());
            stmt.setString(2, user.getFirstName());
            stmt.setString(3, user.getSurname());
            stmt.setString(4, user.getUserPassword());
            stmt.setInt(5, Integer.parseInt(user.getUserID()));
            
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            System.err.println("Error updating user: " + e.getMessage());
            return false;
        }
    }

     /**
     * Deletes user from database
     */
    public boolean deleteUser(String userID) {
        // First delete from linking tables due to foreign key constraints
        String[] deleteQueries = {
            "DELETE FROM User_Expense WHERE user_id = ?",
            "DELETE FROM User_Budget WHERE user_id = ?", 
            "DELETE FROM User_Bank WHERE user_id = ?",
            "DELETE FROM Users WHERE user_id = ?"
        };
        
        try {
            connection.setAutoCommit(false);
            
            for (String query : deleteQueries) {
                try (PreparedStatement stmt = connection.prepareStatement(query)) {
                    stmt.setInt(1, Integer.parseInt(userID));
                    stmt.executeUpdate();
                }
            }
            
            connection.commit();
            return true;
            
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                System.err.println("Error during rollback: " + ex.getMessage());
            }
            System.err.println("Error deleting user: " + e.getMessage());
            return false;
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                System.err.println("Error resetting auto-commit: " + e.getMessage());
            }
        }
    }
    
    
    // ========== BUDGET OPERATIONS ==========
    
    /**
     * Loads all budgets for a user
     */
    public ArrayList<Budget> loadUserBudgets(String userID) {
        ArrayList<Budget> budgets = new ArrayList<>();
        String sql = "SELECT b.* FROM Budget b " +
                    "JOIN User_Budget ub ON b.budget_id = ub.budget_id " +
                    "WHERE ub.user_id = ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, Integer.parseInt(userID)); // comment: user_id is INT
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                // Convert SQL Date to DateTime
                DateTime start = sqlDateToDateTime(rs.getDate("budget_start"));
                DateTime end = sqlDateToDateTime(rs.getDate("budget_end"));
                
                Budget budget = new Budget(
                    rs.getString("budget_id"), // Converted INT to String
                    rs.getFloat("budget_amt"),
                    start,
                    end,
                    rs.getString("budget_category")
                );
                budgets.add(budget);
            }
        } catch (SQLException e) {
            System.err.println("Error loading budgets: " + e.getMessage());
        }
        return budgets;
    }
    
    /**
     * Saves budget to database and links to user
     */
    public boolean saveBudget(Budget budget, String userID) {
        // reminder : Let database auto-generate budget_id
        String budgetSQL = "INSERT INTO Budget (budget_amt, budget_start, budget_end, budget_category) VALUES (?, ?, ?, ?)";
        String userBudgetSQL = "INSERT INTO User_Budget (user_id, budget_id) VALUES (?, ?)";
        
        try {
            // Start transaction
            connection.setAutoCommit(false);
            
            int generatedBudgetId = -1;
            
            // Insert into Budget table
            try (PreparedStatement budgetStmt = connection.prepareStatement(budgetSQL, Statement.RETURN_GENERATED_KEYS)) {
                budgetStmt.setFloat(1, budget.getBudgetAmt());
                budgetStmt.setDate(2, java.sql.Date.valueOf(dateTimeToSQLDate(budget.getBudgetStart())));
                budgetStmt.setDate(3, java.sql.Date.valueOf(dateTimeToSQLDate(budget.getBudgetEnd())));
                budgetStmt.setString(4, budget.getBudgetCategory());
                budgetStmt.executeUpdate();
                
                // Get the auto-generated budget_id
                try (ResultSet generatedKeys = budgetStmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        generatedBudgetId = generatedKeys.getInt(1);
                        budget.setBudgetID(String.valueOf(generatedBudgetId)); // Update the budget object
                    }
                }
            }
            
            // Link to user
            try (PreparedStatement userBudgetStmt = connection.prepareStatement(userBudgetSQL)) {
                userBudgetStmt.setInt(1, Integer.parseInt(userID));
                userBudgetStmt.setInt(2, generatedBudgetId);
                userBudgetStmt.executeUpdate();
            }
            
            connection.commit();
            return true;
            
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                System.err.println("Error during rollback: " + ex.getMessage());
            }
            System.err.println("Error saving budget: " + e.getMessage());
            return false;
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                System.err.println("Error resetting auto-commit: " + e.getMessage());
            }
        }
    }

     /**
     * Updates budget information in database
     */
    public boolean updateBudget(Budget budget) {
        String sql = "UPDATE Budget SET budget_amt = ?, budget_start = ?, budget_end = ?, budget_category = ? WHERE budget_id = ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setFloat(1, budget.getBudgetAmt());
            stmt.setDate(2, java.sql.Date.valueOf(dateTimeToSQLDate(budget.getBudgetStart())));
            stmt.setDate(3, java.sql.Date.valueOf(dateTimeToSQLDate(budget.getBudgetEnd())));
            stmt.setString(4, budget.getBudgetCategory());
            stmt.setInt(5, Integer.parseInt(budget.getBudgetID()));
            
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            System.err.println("Error updating budget: " + e.getMessage());
            return false;
        }
    }

    /**
     * Deletes budget from database
     */
    public boolean deleteBudget(String budgetID) {
        // First delete from linking table due to foreign key constraints
        String[] deleteQueries = {
            "DELETE FROM User_Budget WHERE budget_id = ?",
            "DELETE FROM Budget WHERE budget_id = ?"
        };
        
        try {
            connection.setAutoCommit(false);
            
            for (String query : deleteQueries) {
                try (PreparedStatement stmt = connection.prepareStatement(query)) {
                    stmt.setInt(1, Integer.parseInt(budgetID));
                    stmt.executeUpdate();
                }
            }
            
            connection.commit();
            return true;
            
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                System.err.println("Error during rollback: " + ex.getMessage());
            }
            System.err.println("Error deleting budget: " + e.getMessage());
            return false;
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                System.err.println("Error resetting auto-commit: " + e.getMessage());
            }
        }
    }
    
    // ========== EXPENSE OPERATIONS ==========
    
    /**
     * Loads all expenses for a user
     */
    public ArrayList<Expense> loadUserExpenses(String userID) {
        ArrayList<Expense> expenses = new ArrayList<>();
        String sql = "SELECT e.*, de.bank_id, de.expense_refnum, b.bank_name, b.bank_acc_num " +
                    "FROM Expense e " +
                    "JOIN User_Expense ue ON e.expense_id = ue.expense_id " +
                    "LEFT JOIN Digital_Expense de ON e.expense_id = de.expense_id " +
                    "LEFT JOIN Bank b ON de.bank_id = b.bank_id " +
                    "WHERE ue.user_id = ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, Integer.parseInt(userID));
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                DateTime expenseDate = sqlDateToDateTime(rs.getDate("expense_date"));
                Expense expense;
                
                // Check if it's a digital expense
                String bankId = rs.getString("bank_id");
                if (bankId != null && !bankId.isEmpty()) {
                    // Digital expense
                    expense = new Expense(
                        rs.getString("expense_id"),
                        rs.getString("bank_name"),
                        rs.getString("bank_acc_num"),
                        rs.getFloat("expense_amt"),
                        "PHP", // Default currency
                        rs.getString("expense_refnum"),
                        "", // receiver account not in your schema
                        expenseDate,
                        rs.getString("expense_category")
                    );
                } else {
                    // Cash expense
                    if (rs.getString("expense_category") != null) {
                        expense = new Expense(
                            rs.getString("expense_id"),
                            rs.getFloat("expense_amt"),
                            "PHP",
                            expenseDate,
                            rs.getString("expense_category")
                        );
                    } else {
                        expense = new Expense(
                            rs.getString("expense_id"),
                            rs.getFloat("expense_amt"),
                            "PHP",
                            expenseDate
                        );
                    }
                }
                expenses.add(expense);
            }
        } catch (SQLException e) {
            System.err.println("Error loading expenses: " + e.getMessage());
        }
        return expenses;
    }
    
    /**
     * Saves expense to database and links to user
     */
    public boolean saveExpense(Expense expense, String userID) {
        // reminder : Let database auto-generate expense_id
        String expenseSQL = "INSERT INTO Expense (expense_amt, expense_date, expense_category) VALUES (?, ?, ?)";
        String userExpenseSQL = "INSERT INTO User_Expense (user_id, expense_id) VALUES (?, ?)";
        String digitalExpenseSQL = "INSERT INTO Digital_Expense (expense_id, bank_id, expense_refnum) VALUES (?, ?, ?)";
        
        try {
            connection.setAutoCommit(false);
            
            int generatedExpenseId = -1;
            
            // Insert into Expense table
            try (PreparedStatement expenseStmt = connection.prepareStatement(expenseSQL, Statement.RETURN_GENERATED_KEYS)) {
                expenseStmt.setFloat(1, expense.getExpenseAmount());
                expenseStmt.setDate(2, java.sql.Date.valueOf(dateTimeToSQLDate(expense.getExpenseDateTime())));
                expenseStmt.setString(3, expense.getExpenseCategory());
                expenseStmt.executeUpdate();
                
                // Get the auto-generated expense_id
                try (ResultSet generatedKeys = expenseStmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        generatedExpenseId = generatedKeys.getInt(1);
                        expense.setExpenseID(String.valueOf(generatedExpenseId)); // Update the expense object
                    }
                }
            }
            
            // Link to user
            try (PreparedStatement userExpenseStmt = connection.prepareStatement(userExpenseSQL)) {
                userExpenseStmt.setInt(1, Integer.parseInt(userID));
                userExpenseStmt.setInt(2, generatedExpenseId);
                userExpenseStmt.executeUpdate();
            }
            
            // If it's a digital expense, insert into Digital_Expense table
            if (expense.getExpenseBank() != null) {
                String bankId = getBankId(expense.getExpenseBank());                    
                try (PreparedStatement digitalStmt = connection.prepareStatement(digitalExpenseSQL)) {
                    digitalStmt.setInt(1, generatedExpenseId);
                    digitalStmt.setInt(2, Integer.parseInt(bankId));
                    digitalStmt.setString(3, expense.getExpenseRefNum());
                    digitalStmt.executeUpdate();
                }
            }
            
            connection.commit();
            return true;
            
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                System.err.println("Error during rollback: " + ex.getMessage());
            }
            System.err.println("Error saving expense: " + e.getMessage());
            return false;
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                System.err.println("Error resetting auto-commit: " + e.getMessage());
            }
        }
    }

    /**
     * Updates expense information in database
     */
    public boolean updateExpense(Expense expense) {
        String expenseSQL = "UPDATE Expense SET expense_amt = ?, expense_date = ?, expense_category = ? WHERE expense_id = ?";
        String digitalExpenseSQL = "UPDATE Digital_Expense SET bank_id = ?, expense_refnum = ? WHERE expense_id = ?";
        String deleteDigitalSQL = "DELETE FROM Digital_Expense WHERE expense_id = ?";
        
        try {
            connection.setAutoCommit(false);
            
            // Update main expense table
            try (PreparedStatement expenseStmt = connection.prepareStatement(expenseSQL)) {
                expenseStmt.setFloat(1, expense.getExpenseAmount());
                expenseStmt.setDate(2, java.sql.Date.valueOf(dateTimeToSQLDate(expense.getExpenseDateTime())));
                expenseStmt.setString(3, expense.getExpenseCategory());
                expenseStmt.setInt(4, Integer.parseInt(expense.getExpenseID()));
                expenseStmt.executeUpdate();
            }
            
            // Handle digital expense updates
            if (expense.getExpenseBank() != null) {
                // It's a digital expense, update or insert
                String bankId = getBankId(expense.getExpenseBank());
                try (PreparedStatement digitalStmt = connection.prepareStatement(digitalExpenseSQL)) {
                    digitalStmt.setInt(1, Integer.parseInt(bankId));
                    digitalStmt.setString(2, expense.getExpenseRefNum());
                    digitalStmt.setInt(3, Integer.parseInt(expense.getExpenseID()));
                    int rowsAffected = digitalStmt.executeUpdate();
                    
                    // If no rows affected, insert new digital expense record
                    if (rowsAffected == 0) {
                        String insertDigitalSQL = "INSERT INTO Digital_Expense (expense_id, bank_id, expense_refnum) VALUES (?, ?, ?)";
                        try (PreparedStatement insertStmt = connection.prepareStatement(insertDigitalSQL)) {
                            insertStmt.setInt(1, Integer.parseInt(expense.getExpenseID()));
                            insertStmt.setInt(2, Integer.parseInt(bankId));
                            insertStmt.setString(3, expense.getExpenseRefNum());
                            insertStmt.executeUpdate();
                        }
                    }
                }
            } else {
                // It's a cash expense, remove from digital expense table if exists
                try (PreparedStatement deleteStmt = connection.prepareStatement(deleteDigitalSQL)) {
                    deleteStmt.setInt(1, Integer.parseInt(expense.getExpenseID()));
                    deleteStmt.executeUpdate();
                }
            }
            
            connection.commit();
            return true;
            
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                System.err.println("Error during rollback: " + ex.getMessage());
            }
            System.err.println("Error updating expense: " + e.getMessage());
            return false;
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                System.err.println("Error resetting auto-commit: " + e.getMessage());
            }
        }
    }

    /**
     * Deletes expense from database
     */
    public boolean deleteExpense(String expenseID) {
        // First delete from linking and digital tables due to foreign key constraints
        String[] deleteQueries = {
            "DELETE FROM Digital_Expense WHERE expense_id = ?",
            "DELETE FROM User_Expense WHERE expense_id = ?",
            "DELETE FROM Expense WHERE expense_id = ?"
        };
        
        try {
            connection.setAutoCommit(false);
            
            for (String query : deleteQueries) {
                try (PreparedStatement stmt = connection.prepareStatement(query)) {
                    stmt.setInt(1, Integer.parseInt(expenseID));
                    stmt.executeUpdate();
                }
            }
            
            connection.commit();
            return true;
            
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                System.err.println("Error during rollback: " + ex.getMessage());
            }
            System.err.println("Error deleting expense: " + e.getMessage());
            return false;
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                System.err.println("Error resetting auto-commit: " + e.getMessage());
            }
        }
    }
    
    // ========== HELPER METHODS ==========
    
    private void loadUserBanks(User user) {
        String sql = "SELECT b.* FROM Bank b " +
                    "JOIN User_Bank ub ON b.bank_id = ub.bank_id " +
                    "WHERE ub.user_id = ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, Integer.parseInt(user.getUserID()));
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                user.addBank(rs.getString("bank_name"), rs.getString("bank_acc_num"));
            }
        } catch (SQLException e) {
            System.err.println("Error loading user banks: " + e.getMessage());
        }
    }

    private String getBankId(Bank bank) {
    if (bank == null) return null;
    
    String sql = "SELECT bank_id FROM Bank WHERE bank_name = ? AND bank_acc_num = ?";
    
    try (PreparedStatement stmt = connection.prepareStatement(sql)) {
        stmt.setString(1, bank.getBankName());
        stmt.setString(2, bank.getBankAccNum());
        ResultSet rs = stmt.executeQuery();
        
        if (rs.next()) {
            return String.valueOf(rs.getInt("bank_id"));
        } else {
            System.err.println("Bank not found: " + bank.getBankName() + " - " + bank.getBankAccNum());
            return null;
        }
    } catch (SQLException e) {
        System.err.println("Error getting bank ID: " + e.getMessage());
        return null;
    }
}
    
    private DateTime sqlDateToDateTime(java.sql.Date sqlDate) {
        if (sqlDate == null) return null;
        
        String dateStr = sqlDate.toString(); // Format: YYYY-MM-DD
        String[] parts = dateStr.split("-");
        return new DateTime(parts[0], parts[1], parts[2], "00", "00");
    }
    
    private String dateTimeToSQLDate(DateTime dateTime) {
        return String.format("%s-%s-%s", 
            dateTime.getYear(), 
            dateTime.getMonth(), 
            dateTime.getDay());
    }
    
    /**
     * Test database connection
     */
    public boolean testConnection() {
        try {
            return connection != null && !connection.isClosed();
        } catch (SQLException e) {
            return false;
        }
    }

        // ========== RELATIONSHIP OPERATIONS ==========

    /**
     * Links all banks to a user (for the INSERT SELECT FROM user, bank scenario)
     */
    public boolean linkAllBanksToUser(String userID) {
        String sql = "INSERT INTO User_Bank (user_id, bank_id) " +
                    "SELECT ?, bank_id FROM Bank";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, userID);
            int rowsAffected = stmt.executeUpdate();
            System.out.println("Linked " + rowsAffected + " banks to user " + userID);
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Error linking banks to user: " + e.getMessage());
            return false;
        }
    }

    /**
     * Links all budgets to a user (for the INSERT SELECT FROM user, budget scenario)
     */
    public boolean linkAllBudgetsToUser(String userID) {
        String sql = "INSERT INTO User_Budget (user_id, budget_id) " +
                    "SELECT ?, budget_id FROM Budget";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, userID);
            int rowsAffected = stmt.executeUpdate();
            System.out.println("Linked " + rowsAffected + " budgets to user " + userID);
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Error linking budgets to user: " + e.getMessage());
            return false;
        }
    }

    /**
     * Links all expenses to a user (for the INSERT SELECT FROM user, expense scenario)
     */
    public boolean linkAllExpensesToUser(String userID) {
        String sql = "INSERT INTO User_Expense (user_id, expense_id) " +
                    "SELECT ?, expense_id FROM Expense";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, userID);
            int rowsAffected = stmt.executeUpdate();
            System.out.println("Linked " + rowsAffected + " expenses to user " + userID);
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Error linking expenses to user: " + e.getMessage());
            return false;
        }
    }

    /**
     * Links a specific bank to a user
     */
    public boolean linkBankToUser(String userID, String bankID) {
        String sql = "INSERT INTO User_Bank (user_id, bank_id) VALUES (?, ?)";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, userID);
            stmt.setString(2, bankID);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Error linking bank to user: " + e.getMessage());
            return false;
        }
    }

    /**
     * Links a specific budget to a user
     */
    public boolean linkBudgetToUser(String userID, String budgetID) {
        String sql = "INSERT INTO User_Budget (user_id, budget_id) VALUES (?, ?)";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, userID);
            stmt.setString(2, budgetID);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Error linking budget to user: " + e.getMessage());
            return false;
        }
    }

        /**
         * Links a specific expense to a user
         */
        public boolean linkExpenseToUser(String userID, String expenseID) {
            String sql = "INSERT INTO User_Expense (user_id, expense_id) VALUES (?, ?)";
            
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setString(1, userID);
                stmt.setString(2, expenseID);
                int rowsAffected = stmt.executeUpdate();
                return rowsAffected > 0;
            } catch (SQLException e) {
                System.err.println(" Error linking expense to user: " + e.getMessage());
                return false;
            }
        }

        /**
         * Initializes all relationships for a user (run this after database setup)
         */
        public boolean initializeUserRelationships(String userID) {
            try {
                connection.setAutoCommit(false);
                
                boolean banksLinked = linkAllBanksToUser(userID);
                boolean budgetsLinked = linkAllBudgetsToUser(userID);
                boolean expensesLinked = linkAllExpensesToUser(userID);
                
                if (banksLinked && budgetsLinked && expensesLinked) {
                    connection.commit();
                    System.out.println(" All relationships initialized for user " + userID);
                    return true;
                } else {
                    connection.rollback();
                    System.err.println("Failed to initialize all relationships");
                    return false;
                }
            } catch (SQLException e) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    System.err.println(" Error during rollback: " + ex.getMessage());
                }
                System.err.println("Error initializing relationships: " + e.getMessage());
                return false;
            } finally {
                try {
                    connection.setAutoCommit(true);
                } catch (SQLException e) {
                    System.err.println("Error resetting auto-commit: " + e.getMessage());
                }
            }
        }
    }