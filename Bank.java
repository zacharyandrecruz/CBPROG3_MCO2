/**
 * The Bank class represents a financial institution and account information
 * for tracking digital transactions in the expense tracking system.
 * 
 * <p>This class stores basic bank information including the bank name
 * and account number, which can be associated with digital expenses
 * to provide additional transaction context.
 * 
 * @author Magdaluyo, Alaine Carlo R.
 * @author Cruz, Zachary Andre A.
 * @version 0.2
 * 
 */

public class Bank {
    private String bankName;
    private String bankAccNum;
    
    /**
     * Constructs a new Bank object with the specified name and account number.
     * 
     * @param name the name of the bank or financial institution
     * @param accNum the bank account number
     */
    public Bank(String name, String accNum) {
        this.bankName = name;
        this.bankAccNum = accNum;
    }
    
    /**
     * Returns the name of the bank.
     * 
     * @return the bank name as a String
     */
    public String getBankName() {
        return bankName;
    }
    
    /**
     * Returns the bank account number.
     * 
     * @return the bank account number as a String
     */
    public String getBankAccNum() {
        return bankAccNum;
    }
    
    /**
     * Returns a formatted string containing both bank name and account number.
     * The format is "BankName - AccountNumber".
     * 
     * @return a formatted string representation of the bank information
     */
    public String stringInfo() {
        return bankName + " - " + bankAccNum;
    }
}