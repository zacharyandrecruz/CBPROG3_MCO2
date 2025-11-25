/**
 * The Expense class is used to show the financial expense transaction in the expense tracking system.
 * It can handle both cash and digital bank transactions.
 * 
 * <p>This class supports four different types of expense scenarios:
 * <ul>
 *   <li>Digital expenses with bank details and category</li>
 *   <li>Digital expenses with bank details but without category</li>
 *   <li>Cash expenses without bank details but with category</li>
 *   <li>Cash expenses without bank details and without category</li>
 * </ul>
 * 
 * @author Magdaluyo, Alaine Carlo R.
 * @author Cruz, Zachary Andre A.
 * @version 0.2
 * 
 */
public class Expense {
    private String expenseID;
    private float expenseAmount;
    private String expenseCurrency;
    private String expenseRefNum;
    private String expenseReceiverAccNo;
    private DateTime expenseDateTime;
    private String expenseCategory;
    private Bank expenseBank;
    
    
    /**
     * Constructs a complete Expense object with bank details and category.
     * Used for digital expenses that requires full transaction tracking.
     * 
     * @param expenseID the unique identifier for the expense
     * @param bankName the name of the bank where the transaction occurred
     * @param bankAccNum the account number used for the transaction
     * @param amount the monetary amount of the expense
     * @param currency the currency of the expense (e.g., "PHP")
     * @param refNum the transaction reference number
     * @param receiverAccNo the receiver's account number
     * @param dateTime the date and time of the expense
     * @param category the category classification of the expense
     */
    public Expense(String expenseID, String bankName, String bankAccNum, float amount, String currency, 
                  String refNum, String receiverAccNo, DateTime dateTime, String category) {
        this.expenseBank = new Bank(bankName, bankAccNum);
        this.expenseAmount = amount;
        this.expenseCurrency = currency;
        this.expenseRefNum = refNum;
        this.expenseReceiverAccNo = receiverAccNo;
        this.expenseDateTime = dateTime;
        this.expenseCategory = category;
    }
    
    
    /**
     * Constructs an Expense object with bank details but without category.
     * Used for digital expenses that doesn't require categorization.
     * 
     * @param expenseID the unique identifier for the expense
     * @param bankName the name of the bank where the transaction occurred
     * @param bankAccNum the account number used for the transaction
     * @param amount the monetary amount of the expense
     * @param currency the currency of the expense (e.g., "PHP")
     * @param refNum the transaction reference number
     * @param receiverAccNo the receiver's account number
     * @param dateTime the date and time of the expense
     */
    public Expense(String expenseID, String bankName, String bankAccNum, float amount, String currency, 
                  String refNum, String receiverAccNo, DateTime dateTime) {
        this(expenseID, bankName, bankAccNum, amount, currency, refNum, receiverAccNo, dateTime, null);
    }
    
    /**
     * Constructs an Expense object without bank details but with category.
     * Used for cash expenses that requires categorization.
     * 
     * @param expenseID the unique identifier for the expense
     * @param amount the monetary amount of the expense
     * @param currency the currency of the expense (e.g., "PHP")
     * @param dateTime the date and time of the expense
     * @param category the category classification of the expense
     */
    public Expense(String expenseID, float amount, String currency, DateTime dateTime, String category) {
        this.expenseAmount = amount;
        this.expenseCurrency = currency;
        this.expenseDateTime = dateTime;
        this.expenseCategory = category;
        this.expenseBank = null; // No bank for cash expenses
        this.expenseRefNum = null;
        this.expenseReceiverAccNo = null;
    }
    
    /**
     * Constructs a basic Expense object without bank details and without category.
     * Used for simple cash expenses that doesn't require detailed tracking.
     * 
     * @param expenseID the unique identifier for the expense
     * @param amount the monetary amount of the expense
     * @param currency the currency of the expense (e.g., "PHP")
     * @param dateTime the date and time of the expense
     */
    public Expense(String expenseID, float amount, String currency, DateTime dateTime) {
        this(expenseID, amount, currency, dateTime, null);
    }
    
    // Getters
    
    /**
     * Returns the monetary amount of the expense.
     * 
     * @return the expense amount as a float
     */
    public float getExpenseAmount() {
        return expenseAmount;
    }
    
    /**
     * Returns the currency in which the expense was made.
     * 
     * @return the expense currency as a String
     */
    public String getExpenseCurrency() {
        return expenseCurrency;
    }
    
    /**
     * Returns the reference number for digital transactions.
     * 
     * @return the reference number as a String, or null for cash expenses
     */
    public String getExpenseRefNum() {
        return expenseRefNum;
    }
    
    /**
     * Returns the receiver's account number for digital transactions.
     * 
     * @return the receiver's account number as a String, or null for cash expenses
     */
    public String getExpenseReceiverAccNo() {
        return expenseReceiverAccNo;
    }
    
    /**
     * Returns the date and time when the expense occurred.
     * 
     * @return the expense date and time as a DateTime object
     */
    public DateTime getExpenseDateTime() {
        return expenseDateTime;
    }
    
    /**
     * Returns the category classification of the expense.
     * 
     * @return the expense category as a String, or null if uncategorized
     */
    public String getExpenseCategory() {
        return expenseCategory;
    }
    
    /**
     * Returns the bank information for digital transactions.
     * 
     * @return the Bank object containing bank details, or null for cash expenses
     */
    public Bank getExpenseBank() {
        return expenseBank;
    }
    
    // Additional helper methods
    
    /**
     * Returns the unique identifier of the expense.
     * 
     * @return the expense ID as a String
     */
    public String getExpenseID() {
        return expenseID;
    }
    
    /**
     * Sets the unique identifier for the expense.
     * 
     * @param expenseID the new expense ID to set
     */
    public void setExpenseID(String expenseID) {
        this.expenseID = expenseID;
    }   
}