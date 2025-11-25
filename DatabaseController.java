    import java.sql.*;
    import java.util.ArrayList;

    public class DatabaseController {
        private Connection connection;
        
        // Database connection details
        private static final String URL = "jdbc:mysql://localhost:3306/expense_tracker";
        private static final String USERNAME = "root";
        private static final String PASSWORD = "";
        
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
                System.out.println("✅ Database connected successfully!");
                
            } catch (ClassNotFoundException e) {
                System.err.println("❌ MySQL JDBC Driver not found!");
                e.printStackTrace();
            } catch (SQLException e) {
                System.err.println("❌ Database connection failed!");
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
                    System.out.println("✅ Database connection closed.");
                }
            } catch (SQLException e) {
                System.err.println("❌ Error closing database connection!");
                e.printStackTrace();
            }
        }
        
        // ========== USER OPERATIONS ==========
        
        /**
         * Loads user from database
         */
        public User loadUser(String userID) {
            String sql = "SELECT * FROM User WHERE user_id = ?";
            
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setString(1, userID);
                ResultSet rs = stmt.executeQuery();
                
                if (rs.next()) {
                    User user = new User(
                        rs.getString("user_id"),
                        rs.getString("email"),
                        rs.getString("firstname"),
                        "", // middle name - not in your schema
                        rs.getString("surname")
                    );
                    user.setUserPassword(rs.getString("password"));
                    
                    // Load user's banks
                    loadUserBanks(user);
                    return user;
                }
            } catch (SQLException e) {
                System.err.println("❌ Error loading user: " + e.getMessage());
            }
            return null;
        }
        
        /**
         * Saves user to database
         */
        public boolean saveUser(User user) {
            String sql = "INSERT INTO User (user_id, email, firstname, surname, password) VALUES (?, ?, ?, ?, ?)";
            
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setString(1, user.getUserID());
                stmt.setString(2, user.getUserEmail());
                stmt.setString(3, user.getFullName().split(" ")[0]); // first name
                stmt.setString(4, user.getSurname()); 
                stmt.setString(5, user.getUserPassword());
                
                int rowsAffected = stmt.executeUpdate();
                return rowsAffected > 0;
                
            } catch (SQLException e) {
                System.err.println("❌ Error saving user: " + e.getMessage());
                return false;
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
                stmt.setString(1, userID);
                ResultSet rs = stmt.executeQuery();
                
                while (rs.next()) {
                    // Convert SQL Date to DateTime
                    DateTime start = sqlDateToDateTime(rs.getDate("budget_start"));
                    DateTime end = sqlDateToDateTime(rs.getDate("budget_end"));
                    
                    Budget budget = new Budget(
                        rs.getString("budget_id"),
                        rs.getFloat("budget_amt"),
                        start,
                        end,
                        rs.getString("budget_category")
                    );
                    budgets.add(budget);
                }
            } catch (SQLException e) {
                System.err.println("❌ Error loading budgets: " + e.getMessage());
            }
            return budgets;
        }
        
        /**
         * Saves budget to database and links to user
         */
        public boolean saveBudget(Budget budget, String userID) {
            String budgetSQL = "INSERT INTO Budget (budget_id, budget_amt, budget_start, budget_end, budget_category) VALUES (?, ?, ?, ?, ?)";
            String userBudgetSQL = "INSERT INTO User_Budget (user_id, budget_id) VALUES (?, ?)";
            
            try {
                // Start transaction
                connection.setAutoCommit(false);
                
                // Insert into Budget table
                try (PreparedStatement budgetStmt = connection.prepareStatement(budgetSQL)) {
                    budgetStmt.setString(1, budget.getBudgetID());
                    budgetStmt.setFloat(2, budget.getBudgetAmt());
                    budgetStmt.setDate(3, java.sql.Date.valueOf(dateTimeToSQLDate(budget.getBudgetStart())));
                    budgetStmt.setDate(4, java.sql.Date.valueOf(dateTimeToSQLDate(budget.getBudgetEnd())));
                    budgetStmt.setString(5, budget.getBudgetCategory());
                    budgetStmt.executeUpdate();
                }
                
                // Link to user
                try (PreparedStatement userBudgetStmt = connection.prepareStatement(userBudgetSQL)) {
                    userBudgetStmt.setString(1, userID);
                    userBudgetStmt.setString(2, budget.getBudgetID());
                    userBudgetStmt.executeUpdate();
                }
                
                connection.commit();
                return true;
                
            } catch (SQLException e) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    System.err.println("❌ Error during rollback: " + ex.getMessage());
                }
                System.err.println("❌ Error saving budget: " + e.getMessage());
                return false;
            } finally {
                try {
                    connection.setAutoCommit(true);
                } catch (SQLException e) {
                    System.err.println("❌ Error resetting auto-commit: " + e.getMessage());
                }
            }
        }
        
        // ========== EXPENSE OPERATIONS ==========
        
        /**
         * Loads all expenses for a user
         */
        public ArrayList<Expense> loadUserExpenses(String userID) {
            ArrayList<Expense> expenses = new ArrayList<>();
            String sql = "SELECT e.*, de.bank_id, de.expense_refnum, br.expense_receiver_acc_no, b.bank_name, b.bank_acc_num " +
                        "FROM Expense e " +
                        "JOIN User_Expense ue ON e.expense_id = ue.expense_id " +
                        "LEFT JOIN Digital_Expense de ON e.expense_id = de.expense_id " +
                        "LEFT JOIN Bank b ON de.bank_id = b.bank_id " +
                        "LEFT JOIN Bank_Receiver br ON b.bank_id = br.bank_id " +
                        "WHERE ue.user_id = ?";
            
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setString(1, userID);
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
                            rs.getString("expense_receiver_acc_no"),
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
                System.err.println("❌ Error loading expenses: " + e.getMessage());
            }
            return expenses;
        }
        
        /**
         * Saves expense to database and links to user
         */
        public boolean saveExpense(Expense expense, String userID) {
            String expenseSQL = "INSERT INTO Expense (expense_id, expense_amt, expense_date, expense_category) VALUES (?, ?, ?, ?)";
            String userExpenseSQL = "INSERT INTO User_Expense (user_id, expense_id) VALUES (?, ?)";
            String digitalExpenseSQL = "INSERT INTO Digital_Expense (expense_id, bank_id, expense_refnum) VALUES (?, ?, ?)";
            
            try {
                connection.setAutoCommit(false);
                
                // Insert into Expense table
                try (PreparedStatement expenseStmt = connection.prepareStatement(expenseSQL)) {
                    expenseStmt.setString(1, expense.getExpenseID());
                    expenseStmt.setFloat(2, expense.getExpenseAmount());
                    expenseStmt.setDate(3, java.sql.Date.valueOf(dateTimeToSQLDate(expense.getExpenseDateTime())));
                    expenseStmt.setString(4, expense.getExpenseCategory());
                    expenseStmt.executeUpdate();
                }
                
                // Link to user
                try (PreparedStatement userExpenseStmt = connection.prepareStatement(userExpenseSQL)) {
                    userExpenseStmt.setString(1, userID);
                    userExpenseStmt.setString(2, expense.getExpenseID());
                    userExpenseStmt.executeUpdate();
                }
                
                // If it's a digital expense, insert into Digital_Expense table
                if (expense.getExpenseBank() != null) {
                    String bankId = getBankId(expense.getExpenseBank());                    
                    try (PreparedStatement digitalStmt = connection.prepareStatement(digitalExpenseSQL)) {
                        digitalStmt.setString(1, expense.getExpenseID());
                        digitalStmt.setString(2, bankId);
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
                    System.err.println("❌ Error during rollback: " + ex.getMessage());
                }
                System.err.println("❌ Error saving expense: " + e.getMessage());
                return false;
            } finally {
                try {
                    connection.setAutoCommit(true);
                } catch (SQLException e) {
                    System.err.println("❌ Error resetting auto-commit: " + e.getMessage());
                }
            }
        }
        
        // ========== HELPER METHODS ==========
        
        private void loadUserBanks(User user) {
            String sql = "SELECT b.* FROM Bank b " +
                        "JOIN User_Bank ub ON b.bank_id = ub.bank_id " +
                        "WHERE ub.user_id = ?";
            
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setString(1, user.getUserID());
                ResultSet rs = stmt.executeQuery();
                
                while (rs.next()) {
                    user.addBank(rs.getString("bank_name"), rs.getString("bank_acc_num"));
                }
            } catch (SQLException e) {
                System.err.println("❌ Error loading user banks: " + e.getMessage());
            }
        }

        private String getBankId(Bank bank) {
            if (bank == null) return null;
        
            // Map based on the bank data from the SQL script
            switch(bank.getBankName().toUpperCase()) {
                case "BPI" -> {
                    return "0001";
                }
                case "BDO" -> {
                    return "0002";
                }
                case "VISA" -> {
                    return "0003";
                }
                case "METROBANK" -> {
                    return "0004";
                }
                default -> {
                    System.err.println(" Unknown bank: " + bank.getBankName());
                    return null;
                }
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
            System.out.println("✅ Linked " + rowsAffected + " banks to user " + userID);
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("❌ Error linking banks to user: " + e.getMessage());
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
            System.out.println("✅ Linked " + rowsAffected + " budgets to user " + userID);
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("❌ Error linking budgets to user: " + e.getMessage());
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
            System.out.println("✅ Linked " + rowsAffected + " expenses to user " + userID);
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("❌ Error linking expenses to user: " + e.getMessage());
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
            System.err.println("❌ Error linking bank to user: " + e.getMessage());
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
            System.err.println("❌ Error linking budget to user: " + e.getMessage());
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
                System.err.println("❌ Error linking expense to user: " + e.getMessage());
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
                    System.out.println("✅ All relationships initialized for user " + userID);
                    return true;
                } else {
                    connection.rollback();
                    System.err.println("❌ Failed to initialize all relationships");
                    return false;
                }
            } catch (SQLException e) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    System.err.println("❌ Error during rollback: " + ex.getMessage());
                }
                System.err.println("❌ Error initializing relationships: " + e.getMessage());
                return false;
            } finally {
                try {
                    connection.setAutoCommit(true);
                } catch (SQLException e) {
                    System.err.println("❌ Error resetting auto-commit: " + e.getMessage());
                }
            }
        }
    }