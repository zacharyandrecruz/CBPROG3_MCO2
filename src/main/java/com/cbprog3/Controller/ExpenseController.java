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

    public void updateDatabases(UserController uc, DatabaseController dbc){

        this.budgets = dbc.loadUserBudgets(uc.getCurrentUser().getUserID());
        this.expenses = dbc.loadUserExpenses(uc.getCurrentUser().getUserID());

    }
    
    private void initializeDefaultCategories() {
        categories.add("FOOD");
        categories.add("TRANSPORTATION");
        categories.add("ENTERTAINMENT");
        categories.add("UTILITY");
        categories.add("GROCERY");
        categories.add("SUBSCRIPTION");
        categories.add("RENT");
        categories.add("SHOPPING");
        categories.add("HEALTHCARE");
        categories.add("EDUCATION");
        categories.add("OTHER");
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
        
        float totalDays = dailyExpenses.size();
        
        float total = 0;
        for (float amount : dailyExpenses) {
            total += amount;
        }
        
        return total / totalDays;
    }
    
    public float computeMonthlyAverage() {
        ArrayList<Float> monthlyExpenses = getMonthlyExpenses();
        if (monthlyExpenses.isEmpty()) return 0;
        
        float totalMonths = monthlyExpenses.size();
        
        float total = 0;
        for (float amount : monthlyExpenses) {
            total += amount;
        }
        
        return total / totalMonths;
    }
    
    public ArrayList<Float> getDailyExpenses() {
        ArrayList<Float> expensePerDay = new ArrayList<>();
        float totalDays = 0;
        float currentTotal = -1;
        String currentDay = "";
        
        for(Expense e : expenses){
            if(!e.getExpenseDateTime().getDateString().equals(currentDay)){
                if(currentTotal != -1){
                    expensePerDay.add(currentTotal);
                }
                currentDay = e.getExpenseDateTime().getDateString();
                currentTotal = e.getExpenseAmount();
                totalDays += 1;
            }else{
                currentTotal += e.getExpenseAmount();
            }
        }
        
        if(currentTotal != -1){
            expensePerDay.add(currentTotal);
        }
        return expensePerDay;
    }
    
    public ArrayList<String> getDailyExpensesDate() {
        ArrayList<String> expenseDay = new ArrayList<>();
        float totalDays = 0;
        float currentTotal = -1;
        String currentDay = "";
        
        for(Expense e : expenses){
            if(!e.getExpenseDateTime().getDateString().equals(currentDay)){
                if(currentTotal != -1){
                    expenseDay.add(currentDay);
                }
                currentDay = e.getExpenseDateTime().getDateString();
                currentTotal = e.getExpenseAmount();
                totalDays += 1;
            }else{
                currentTotal += e.getExpenseAmount();
            }
        }
        
        if(currentTotal != -1){
            expenseDay.add(currentDay);
        }
        return expenseDay;
    }

    public ArrayList<Float> getMonthlyExpenses() {
        ArrayList<Float> expensePerMonth = new ArrayList<>();
        float totalMonths = 0;
        float currentTotal = -1;
        String currentMonth = "";
        String currentYear = "";
        
        for(Expense e : expenses){
            if(!e.getExpenseDateTime().getMonth().equals(currentMonth) || !e.getExpenseDateTime().getYear().equals(currentYear)){
                if(currentTotal != -1){
                    expensePerMonth.add(currentTotal);
                }
                currentMonth = e.getExpenseDateTime().getMonth();
                currentYear = e.getExpenseDateTime().getYear();
                currentTotal = e.getExpenseAmount();
                totalMonths += 1;
            }else{
                currentTotal += e.getExpenseAmount();
            }
        }
        
        if(currentTotal != -1){
            expensePerMonth.add(currentTotal);
        }
        return expensePerMonth;
    }
    
    public ArrayList<String> getMonthlyExpensesDate() {
        ArrayList<String> expenseMonth = new ArrayList<>();
        float totalMonths = 0;
        float currentTotal = -1;
        String currentDate = "";
        String currentMonth = "";
        String currentYear = "";
        
        for(Expense e : expenses){
            if(!e.getExpenseDateTime().getMonth().equals(currentMonth) || !e.getExpenseDateTime().getYear().equals(currentYear)){
                if(currentTotal != -1){
                    expenseMonth.add(currentDate);
                }
                currentMonth = e.getExpenseDateTime().getMonth();
                currentYear = e.getExpenseDateTime().getYear();
                currentDate = e.getExpenseDateTime().getDateString();
                currentTotal = e.getExpenseAmount();
                totalMonths += 1;
            }else{
                currentTotal += e.getExpenseAmount();
            }
        }
        
        if(currentTotal != -1){
            expenseMonth.add(currentDate);
        }
        return expenseMonth;
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