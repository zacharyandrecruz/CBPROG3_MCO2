package com.cbprog3.Controller;

import com.cbprog3.Model.*;
import java.util.ArrayList;

public class ExpenseController {
    private ArrayList<Budget> budgets;
    private ArrayList<Expense> expenses;
    private ArrayList<String> categories;
    
    public ExpenseController() {
        this.budgets = new ArrayList<>();
        this.expenses = new ArrayList<>();
        this.categories = new ArrayList<>();
        initializeDefaultCategories();
    }
    
    private void initializeDefaultCategories() {
        categories.add("Food");
        categories.add("Transportation");
        categories.add("Entertainment");
        categories.add("Utilities");
        categories.add("Shopping");
        categories.add("Healthcare");
        categories.add("Education");
        categories.add("Other");
    }
    
    // Budget Management
    public Budget setBudget(float amount, DateTime start, DateTime end, String category) {
        String budgetID = generateID("BUDG", budgets.size() + 1, 6);
        Budget budget;
        
        if (category != null && !category.isEmpty()) {
            budget = new Budget(budgetID, amount, start, end, category);
        } else {
            budget = new Budget(budgetID, amount, start, end);
        }
        
        budgets.add(budget);
        return budget;
    }
    
    public Budget getBudget(int index) {
        if (index >= 0 && index < budgets.size()) {
            return budgets.get(index);
        }
        return null;
    }
    
    public ArrayList<Budget> getAllBudgets() {
        return new ArrayList<>(budgets);
    }
    
    // Expense Management
    public Expense recordExpense(float amount, DateTime date, String bankName, 
                               String bankAccNum, String refNum, String receiverAccNo, String category) {
        String expenseID = generateID("EXPN", expenses.size() + 1, 6);
        Expense expense;
        
        if (bankName != null && !bankName.isEmpty()) {
            // Digital expense
            if (category != null && !category.isEmpty()) {
                expense = new Expense(expenseID, bankName, bankAccNum, amount, "PHP", 
                                    refNum, receiverAccNo, date, category);
            } else {
                expense = new Expense(expenseID, bankName, bankAccNum, amount, "PHP", 
                                    refNum, receiverAccNo, date);
            }
        } else {
            // Cash expense
            if (category != null && !category.isEmpty()) {
                expense = new Expense(expenseID, amount, "PHP", date, category);
            } else {
                expense = new Expense(expenseID, amount, "PHP", date);
            }
        }
        
        expenses.add(expense);
        return expense;
    }
    
    public ArrayList<Expense> getAllExpenses() {
        return new ArrayList<>(expenses);
    }
    
    // Calculations and Analytics
    public float computeDailyAve() {
        ArrayList<Float> dailyExpenses = getDailyExpenses();
        if (dailyExpenses.isEmpty()) return 0;
        
        float totalDays = dailyExpenses.get(0);
        dailyExpenses.remove(0);
        
        float total = 0;
        for (float amount : dailyExpenses) {
            total += amount;
        }
        
        return total / totalDays;
    }
    
    public float computeMonthlyAverage() {
        ArrayList<Float> monthlyExpenses = getMonthlyExpenses();
        if (monthlyExpenses.isEmpty()) return 0;
        
        float totalMonths = monthlyExpenses.get(0);
        monthlyExpenses.remove(0);
        
        float total = 0;
        for (float amount : monthlyExpenses) {
            total += amount;
        }
        
        return total / totalMonths;
    }
    
    public ArrayList<Float> getDailyExpenses() {
        // Your existing getDailyExpenses() logic here
        ArrayList<Float> expensePerDay = new ArrayList<>();
        // ... implementation from ExpenseTracker
        return expensePerDay;
    }
    
    public ArrayList<Float> getMonthlyExpenses() {
        // Your existing getMonthlyExpenses() logic here
        ArrayList<Float> expensePerMonth = new ArrayList<>();
        // ... implementation from ExpenseTracker
        return expensePerMonth;
    }
    
    public float getTotalCategoryExpense(String category) {
        float total = 0;
        for (Expense expense : expenses) {
            if (category == null) {
                if (expense.getExpenseCategory() == null) {
                    total += expense.getExpenseAmount();
                }
            } else {
                if (category.equals(expense.getExpenseCategory())) {
                    total += expense.getExpenseAmount();
                }
            }
        }
        return total;
    }
    
    public float getTotalExpenses() {
        float total = 0;
        for (Expense expense : expenses) {
            total += expense.getExpenseAmount();
        }
        return total;
    }
    
    // Category Management
    public ArrayList<String> getCategories() {
        return new ArrayList<>(categories);
    }
    
    public void addCategory(String category) {
        if (!categories.contains(category)) {
            categories.add(category);
        }
    }
    
    // Helper Methods
    private String generateID(String prefix, int idNum, int numOfDigits) {
        String id = prefix;
        String numString = Integer.toString(idNum);
        
        for (int i = numString.length(); i < numOfDigits; i++) {
            id += "0";
        }
        
        id += numString;
        return id;
    }
}