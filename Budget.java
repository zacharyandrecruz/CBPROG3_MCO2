/**
 * The Budget class represents a financial budget with a specific amount, time period, and optional category.
 * It is used to set spending limits for tracking and comparing against actual expenses.
 * 
 * <p>This class supports both categorized budgets (for specific expense types) and general budgets
 * (for overall spending across all categories).
 * 
 * @author Magdaluyo, Alaine Carlo R.
 * @author Cruz, Zachary Andre A.
 * @version 0.2
 * 
 */

public class Budget {
    private String budgetID;
    private float budgetAmt;
    private DateTime budgetStart;
    private DateTime budgetEnd;
    private String budgetCategory;
    
    /**
     * Constructs a complete Budget object with category specification.
     * Used for budgets that target specific expense categories.
     * 
     * @param budgetID the unique identifier for the budget
     * @param amount the total budget amount
     * @param start the start date and time of the budget period
     * @param end the end date and time of the budget period
     * @param category the expense category this budget applies to
     */
    public Budget(String budgetID, float amount, DateTime start, DateTime end, String category) {
        this.budgetID = budgetID;
        this.budgetAmt = amount;
        this.budgetStart = start;
        this.budgetEnd = end;
        this.budgetCategory = category;
    }
    
    /**
     * Constructs a general Budget object without category specification.
     * Used for overall spending budgets that apply to all expenses.
     * 
     * @param budgetID the unique identifier for the budget
     * @param amount the total budget amount
     * @param start the start date and time of the budget period
     * @param end the end date and time of the budget period
     */
    public Budget(String budgetID, float amount, DateTime start, DateTime end) {
        this.budgetID = budgetID;
        this.budgetAmt = amount;
        this.budgetStart = start;
        this.budgetEnd = end;
        this.budgetCategory = null; 
    }
    
    // Getters
    
    /**
     * Returns the unique identifier of the budget.
     * 
     * @return the budget ID as a String
     */
    public String getBudgetID(){
        return this.budgetID;
    }

    /**
     * Returns the total budget amount.
     * 
     * @return the budget amount as a float
     */
    public float getBudgetAmt() {
        return this.budgetAmt;
    }
    
    /**
     * Sets a new budget amount.
     * 
     * @param amount the new budget amount to set
     */
    public void setBudgetAmt(float amount) {
        this.budgetAmt = amount;
    }
    
    /**
     * Returns the start date and time of the budget period.
     * 
     * @return the budget start DateTime object
     */
    public DateTime getBudgetStart() {
        return this.budgetStart;
    }
    
    /**
     * Returns the end date and time of the budget period.
     * 
     * @return the budget end DateTime object
     */
    public DateTime getBudgetEnd() {
        return this.budgetEnd;
    }
    
    /**
     * Returns the category this budget applies to.
     * 
     * @return the budget category as a String, or null for general budgets
     */
    public String getBudgetCategory() {
        return this.budgetCategory;
    }
    
    /**
     * Checks if this budget has a specific category assigned.
     * 
     * @return true if the budget has a non-null, non-empty category, false otherwise
     */
    public boolean hasCategory() {
        return this.budgetCategory != null && !budgetCategory.isEmpty();
    }
}