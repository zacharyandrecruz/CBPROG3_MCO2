package com.cbprog3.Controller;

import com.cbprog3.Model.*;

public class UserController {
    private User currentUser;
    
    public boolean login(String email, String password) {
        if (currentUser != null && 
            currentUser.getUserEmail().equals(email) && 
            currentUser.getUserPassword().equals(password)) {
            return true;
        }
        return false;
    }
    
    public boolean register(String userID, String email, String firstName, 
                          String midName, String surname, String password) {
        User newUser = new User(userID, email, firstName, midName, surname);
        boolean passwordSet = newUser.setUserPassword(password);
        
        if (passwordSet) {
            currentUser = newUser;
            return true;
        }
        return false;
    }
    
    public void addBankAccount(String bankName, String bankAccNum) {
        if (currentUser != null) {
            currentUser.addBank(bankName, bankAccNum);
        }
    }
    
    public boolean removeBankAccount(String bankAccNum) {
        if (currentUser != null) {
            return currentUser.removeBank(bankAccNum);
        }
        return false;
    }
    
    public User getCurrentUser() {
        return currentUser;
    }
}