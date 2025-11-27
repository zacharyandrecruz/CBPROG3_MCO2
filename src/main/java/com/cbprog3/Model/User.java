package com.cbprog3.Model;

/**
 * The User class represents the users of the expense tracking system.
 * It stores user information, authentication details, and associated bank accounts.
 * 
 * <p>This class manages user profile information including personal details,
 * password security, and bank account associations for tracking digital transactions.
 *
 * @author Magdaluyo, Alaine Carlo R.
 * @author Cruz, Zachary Andre A.
 * @version 0.2
 * 
 */
import java.util.ArrayList;

public class User {
    private String userID;
    private String userEmail;
    private String firstName;
    private String midName;
    private String surname;
    private String userPassword;
    private ArrayList<Bank> userBanks;
    
    /**
     * Constructs a new User object with basic profile information.
     * The user password is initially null and must be set separately.
     * 
     * @param userID the unique identifier for the user
     * @param email the user's email address
     * @param fName the user's first name
     * @param mName the user's middle name (can be null or empty)
     * @param sName the user's surname/last name
     */
    public User(String userID, String email, String fName, String mName, String sName) {
        this.userID = userID;
        this.userEmail = email;
        this.firstName = fName;
        this.midName = mName;
        this.surname = sName;
        this.userPassword = null;
        this.userBanks = new ArrayList<>(); //for storing banks at addBank
    }
    
    /**
     * Returns the user's unique identifier.
     * 
     * @return the user ID as a String
     */
    public String getUserID() {
        return userID;
    }
    
    /**
     * Sets the user's unique identifier.
     * 
     * @param userID the new user ID to set
     */
    public void setUserID(String userID) {
        this.userID = userID;
    }
    
    /**
     * Returns the user's email address.
     * 
     * @return the user email as a String
     */
    public String getUserEmail() {
        return userEmail;
    }
    
    /**
     * Sets the user's email address.
     * 
     * @param email the new email address to set
     */
    public void setUserEmail(String email) {
        this.userEmail = email;
    }
    
    /**
     * Returns the user's current password.
     * 
     * @return the user password as a String, or null if not set
     */
    public String getUserPassword() {
        return userPassword;
    }
    
    /**
     * Sets or changes the user's password with security validation.
     * Password must be at least 6 characters long and different from the current password.
     * 
     * @param pw the new password to set
     * @return true if password was successfully set, false if validation failed
     */
    public boolean setUserPassword(String pw) {

        if(this.userPassword != null){
            if (pw != this.userPassword && pw.length() >= 6) { //if the char length is lower than 6 and password is not equal to previous password, return false
                this.userPassword = pw;
                return true;
            }
        }else{
            if(pw.length() >= 6){
                this.userPassword = pw;
                return true;
            }
        }

        return false; 
        
    }

    // Add these to your User class:

    /**
     * Returns the user's first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Returns the user's surname
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Returns the user's middle name
     */
    public String getMidName() {
        return midName;
    }
    
    /**
     * Adds a new bank account to the user's profile.
     * 
     * @param bankName the name of the bank
     * @param bankAccNum the bank account number
     */
    public void addBank(String bankName, String bankAccNum) {
        Bank newBank = new Bank(bankName, bankAccNum);
        userBanks.add(newBank);
    }
    
    /**
     * Removes a bank account from the user's profile using the account number.
     * 
     * @param bankAccNum the bank account number to remove
     * @return true if the bank was successfully removed, false if not found
     */
    public boolean removeBank(String bankAccNum) {
        for (int i = 0; i < userBanks.size(); i++) {
            if (userBanks.get(i).getBankAccNum().equals(bankAccNum)) {
                userBanks.remove(i);
                return true;
            }
        }
        return false;
    }

    // Additional helper methods 

    /**
     * Returns the user's full name in formatted form.
     * Includes middle name if available, otherwise returns first and last name only.
     * 
     * @return the full name as a formatted String
     */
    public String getFullName() {
        if (midName != null && !midName.isEmpty()) {
            return firstName + " " + midName + " " + surname;
        } else {
            return firstName + " " + surname;
        }
    } 
    
    /**
     * Returns a copy of the user's bank accounts list.
     * 
     * @return a new ArrayList containing the user's Bank objects
     */
    public ArrayList<Bank> getUserBanks() {
        return new ArrayList<>(userBanks); 
    }
    
    /**
     * Returns the number of bank accounts associated with the user.
     * 
     * @return the count of bank accounts as an integer
     */
    public int getBankCount() {
        return userBanks.size();
    }
    
    /**
     * Checks if the user has a specific bank account by account number.
     * 
     * @param bankAccNum the bank account number to search for
     * @return true if the bank account exists, false otherwise
     */
    public boolean hasBank(String bankAccNum) {
        for (Bank bank : userBanks) {
            if (bank.getBankAccNum().equals(bankAccNum)) {
                return true;
            }
        }
        return false;
    }
}