package com.cbprog3.View;

import com.cbprog3.Controller.DatabaseController;
import com.cbprog3.Controller.ExpenseController;
import com.cbprog3.Controller.UserController;

public class App 
{
    public static void main( String[] args )
    {

        boolean running = true;

        DatabaseController dbc = new DatabaseController();
        UserController uc = new UserController(dbc.loadUser("1"));
        ExpenseController ec = new ExpenseController();

        LoginMenu lm = new LoginMenu(uc);
        MainMenu mm = new MainMenu();
        lm.setVisible(true);

        System.out.println("Login Menu Opened!");

        while(running){

            if(lm.getLoginStatus() && lm.isVisible()){
                System.out.println("Switching to Main Menu!");
                lm.setVisible(false);
                mm.setVisible(true);
            }

            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }
}
